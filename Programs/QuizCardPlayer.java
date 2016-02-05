import java.util.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;

public class QuizCardPlayer {

	private JTextArea display;
	private JTextArea answer;
	private ArrayList<QuizCard> cardList;
	private QuizCard currentCard;
	private int currentCardIndex;
	private JFrame frame;
	private JButton nextButton;
	private boolean isShowAnswer;

	public static void main (String[] args) {
		QuizCardPlayer reader = new QuizcardPlayer();
		reader.go();
	}

	public void go() {

		frame = new JFrame ("Quiz Card Player");
		JPanel mainPanel = new JPanel();
		Font bigFont = new Font ("sanserif", Font.BOLD, 24);

		display = new JTextArea(10,20);
		display.setFont(bigFont);

		display.setLineWrap(true);
		display.setEditable(false);

		JScrollPane qScroller = new JScrollPane(display);
		qScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		qScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		nextButton = new JButton ("Show Question");
		mainPanel.add(qScroller);
		mainPanel.add(nextButton);
		nextButton.addActionListener(new NextCardListener());

		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu ("File");
		JMenuItem loadMenuItem = new JMenuItem("Load card set");
		loadMenuItem.addActionListener(new OpenMenuListener());
		fileMenu.add(loadMenuItem);
		menuBar.add(fileMenu);
		frame.setJMenuBar(menuBar);
		frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
		frame.setSize(640,500);
		frame.setVisible(true);
	}

	public class NextCardListener implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			if (isShowAnswer) {
				//show the answer because they've seen the question
				display.setText(currentCard.getAnswer());
				nextButton.setText("Next Card");
				isShowAnswer = false;
			}	else {
				//show the next question
				if (currentCardIndex < cardList.size()) {
					showNextCard();
				}	else {
					//there are no more cards
					display.setText("That was the last card");
					nextButton.setEnabled(false);
				}
			}
		}
	}
	//Check the isShowAnswer boolean flag to see if they're currently viewing a question or an answer, and do the appropriate thing depending on the answer.

	public class OpenMenuListener implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			JFileChooser fileOpen = new JFileChooser();
			fileOpen.showOpenDialog(frame);
			loadFile(fileOpen.getSelectedFile());
		}
	}
	//Bring up the file dialog box and let them navigate to and choose the file to open.

	private void loadFile(File file) {

		cardList = new ArrayList<QuizCard>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line = null;
			while ((line = reader.readLine()) != null) {
				makeCard(line);
				//Make a BufferedReader chained to a new FileReader, giving the FileReader the File object the user chose from the open file dialog.
				//Read a line at a time, passing the line to the makeCard() method that parses it and turns it into a real QuizCard and adds it to the ArrayList.
			}
			reader.close();

		}	catch (Exception ex) {
				System.out.println("couldn't read the card file");
				ex.printStackTrace();
		}
		//now time to start by showing the first card
		showNextCard();
	}

	private void makeCard (String lineToParse) {
		String[] result = lineToParse.split("/");
		//Each line of text corresponds to a single flashcard, but we have to parse out the question and answer as separate pieces. We use the String split() method to break the line into two tokens (one for the question, and one for the answer). We'll look at the split() method on the next page.
		QuizCard card = new QuizCard(result[0], result[1]);
		cardList.add(card);
		System.out.println("made a card");
	}

	private void showNextCard() {
		currentCard = cardList.get(currentCardIndex);
		currentCardIndex++;
		display.setText(currentCard.getQuestion());
		nextButton.setText("Show Answer");
		isShowAnswer = true;
	}
}