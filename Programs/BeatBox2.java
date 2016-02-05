import java.awt.*;
import javax.swing.*;
import javax.sound.midi.*;
import java.util.*;
import java.awt.event.*;
import java.io.*;

public class BeatBox2 {

	JPanel mainPanel;
	ArrayList<JCheckBox> checkboxList;
	//We store the checkboxes in an ArrayList
	Sequencer sequencer;
	Sequence sequence;
	Track track;
	JFrame theFrame;

	String[] instrumentNames = {"Bass Drum", "Closed Hi-Hat", "Open Hi-Hat", "Acoustic Snare", "Crash Cymbal", "Hand Clap", "High Tom", "Hi Bongo", "Maracas", "Whistle", "Low Conga", "Cowbell", "Vibraslap", "Low-mid Tom", "High agogo", "Open Hi Conga"};
	int[] instruments = {35,42,46,38,49,39,50,60,70,72,64,56,58,47,67,63};

	public static void main (String[] args) {
		new BeatBox2().buildGUI();
	}

	public void buildGUI() {
		theFrame = new JFrame("Hyper Beatbox yo");
		theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		BorderLayout layout = new BorderLayout();
		JPanel background = new JPanel(layout);
		background.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		//an 'empty border' gives us a margin between the edges of the panel and where the components are placed.

		checkboxList = new ArrayList<JCheckBox>();
		Box buttonBox = new Box(BoxLayout.Y_AXIS);

		JButton start = new JButton("Start");
		start.addActionListener(new MyStartListener());
		buttonBox.add(start);

		JButton stop = new JButton("Stop");
		stop.addActionListener(new MyStopListener());
		buttonBox.add(stop);

		JButton upTempo = new JButton("Tempo Up");
		upTempo.addActionListener(new MyUpTempoListener());
		buttonBox.add(upTempo);

		JButton downTempo = new JButton("Tempo Down");
		downTempo.addActionListener(new MyDownTempoListener());
		buttonBox.add(downTempo);

		JButton serializeIt = new JButton ("Save it");
		serializeIt.addActionListener(new MySendListener());
		buttonBox.add(serializeIt);

		JButton restore = new JButton ("restore");
		restore.addActionListener(new MyReadInListener());
		buttonBox.add(restore);

		Box nameBox = new Box(BoxLayout.Y_AXIS);
		for (int i = 0; i < 16; i++) {
			nameBox.add(new Label(instrumentNames[i]));
		}

		background.add(BorderLayout.EAST, buttonBox);
		background.add(BorderLayout.WEST, nameBox);

		theFrame.getContentPane().add(background);

		GridLayout grid = new GridLayout(16,16);
		grid.setVgap(1);
		grid.setHgap(2);
		mainPanel = new JPanel (grid);
		background.add(BorderLayout.CENTER,mainPanel);

		for (int i = 0; i < 256; i++) {
			JCheckBox c = new JCheckBox();
			c.setSelected(false);
			checkboxList.add(c);
			mainPanel.add(c);
		}
		//make the checkboxes, set them to 'false' and add them to the ArrayList AND to the GUI panel
		setUpMidi();

		theFrame.setBounds(50,50,300,300);
		theFrame.pack();
		theFrame.setVisible(true);
	}//close method

	public void setUpMidi() {
		try {
			sequencer = MidiSystem.getSequencer();
			sequencer.open();
			sequence = new Sequence(Sequence.PPQ,4);
			track = sequence.createTrack();
			sequencer.setTempoInBPM(120);
		}	catch (Exception e) {e.printStackTrace();}
	}//close method

	public void buildTrackAndStart() {
		int[] trackList = null;
		//We'll make a 16-element array to hold the values for one instrument, across all 16 beats. If the instrument is supposed to play on tha beat, the value at that element will be the key. If that instrument is NOT supposed to play on that beat, put in a zero.

		sequence.deleteTrack(track);
		track = sequence.createTrack();
		//get rid of old track, create fresh one.

		for (int i = 0; i < 16; i++) {
			trackList = new int[16];

			int key = instruments[i];
			//set the 'key' that represents which instrument. The instruments array holds the actual MIDI numbers for each instrument.

			for (int j = 0; j < 16; j++) {

				JCheckBox jc = (JCheckBox) checkboxList.get(j + (16*i));
				if (jc.isSelected()) {
					trackList[j] = key;
				}	else {
					trackList[j] = 0;
				}
				//is the checkbox at this beat selected? If yes, put the key value in this slot in the array (the slot that represents this beat). Otherwise, the instrument is NOT supposed to play at this beat, so set it to zero.
			}//close inner loop

			makeTracks(trackList);
			track.add(makeEvent(176,1,127,0,16));
			//for this instrument, and for all 16 beats, make events and add them to the track.
		}//close outer

		track.add(makeEvent(192,9,1,0,15));
		//we always want to make sure that there IS an event at beat 16(it goes 0-15). Otherwise, the BeatBox might not go teh full 16 beats before it starts over.
		try{

			sequencer.setSequence(sequence);
			sequencer.setLoopCount(sequencer.LOOP_CONTINUOUSLY);
			sequencer.start();
			sequencer.setTempoInBPM(120);
		}	catch (Exception e) {e.printStackTrace();}
	}//close buildTrackAndStart method

	public class MyStartListener implements ActionListener {
		public void actionPerformed(ActionEvent a) {
			buildTrackAndStart();
		}
	}

	public class MyStopListener implements ActionListener {
		public void actionPerformed(ActionEvent a) {
			sequencer.stop();
		}
	}

	public class MyUpTempoListener implements ActionListener {
		public void actionPerformed(ActionEvent a) {
			float tempoFactor = sequencer.getTempoFactor();
				sequencer.setTempoFactor((float)(tempoFactor * 1.03));
		}
	}

	public class MyDownTempoListener implements ActionListener {
		public void actionPerformed(ActionEvent a) {
			float tempoFactor = sequencer.getTempoFactor();
				sequencer.setTempoFactor((float)(tempoFactor * .97));
		}
	}

	public class MySendListener implements ActionListener {

		public void actionPerformed(ActionEvent a) {

			boolean[] checkboxState = new boolean[256];
				//make a boolean array to hold the state of each checkbox

				for (int i = 0; i < 256; i++) {

					JCheckBox check = (JCheckBox) checkboxList.get(i);
					if (check.isSelected()) {
						checkboxState[i] = true;
					}
				}

			try {
				FileOutputStream fileStream = new FileOutputStream(new File("Checkbox.ser"));
				ObjectOutputStream os = new ObjectOutputStream (fileStream);
				os.writeObject(checkboxState);
			}	catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	public class MyReadInListener implements ActionListener {

		public void actionPerformed (ActionEvent a) {
			boolean[] checkboxState = null;
			try {
				FileInputStream fileIn = new FileInputStream(new File("Checkbox.ser"));
				ObjectInputStream is = new ObjectInputStream(fileIn);
				checkboxState = (boolean[])is.readObject();
				//read the single object in the file(the boolean array) and cast it back to a boolean array(remember,readObject() returns a reference of type Object)
			}	catch (Exception ex) {ex.printStackTrace();}

			for (int i = 0; i < 256 ; i++) {
				JCheckBox check = (JCheckBox) checkboxList.get(i);
				if (checkboxState[i]) {
					check.setSelected(true);
				}	else {
					check.setSelected(false);
				}
			}
			//Now restore the state of each of the checkboxes in the ArrayList of actual JCheckBox objects

			sequencer.stop();
			buildTrackAndStart();
		}
	}

	public void makeTracks(int[] list) {
	//This makes events for one instrument at a time, for all 16 beats. So it might get an int[] for the Bass drum, and each index in the array will hold either the key of that instrument, or a zero. If it's a zero, the instrument isn't supposed to play at that beat. Otehrwise make an event and add it to the track.

		for(int i = 0; i < 16; i++) {
			int key = list[i];

			if (key!= 0) {
				track.add(makeEvent(144,9,key,100,i));
				track.add(makeEvent(128,9,key,100,i+1));
			}
		}
	}

	public MidiEvent makeEvent (int comd, int chan, int one, int two, int tick) {
		MidiEvent event = null;
		try {
			ShortMessage a = new ShortMessage();
			a.setMessage(comd,chan,one,two);
			event = new MidiEvent(a, tick);
		}	catch (Exception e) {e.printStackTrace();}
		return event;
	}
}