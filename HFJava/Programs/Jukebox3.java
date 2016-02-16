import java.util.*;
import java.io.*;

public class Jukebox3 {

	ArrayList<Song> songList = new ArrayList<Song>();

	public static void main (String[] args) {
		new Jukebox1().go();
	}

	public void go() {
		getSongs();
		System.out.println(songList);
		Collections.sort(songList);
		//call the static Collection sort() method
		System.out.println(songList);
	}

	void getSongs() {
		try {
			File file = new File("SongList.txt");
			//create a File SongList.txt
			BufferedReader reader = new BufferedReader(new FileReader(file));
			//Feed filereader into the buffered reader
			String line = null;
			while ((line= reader.readLine())!=null) {
				addSong(line);
				//Reads a line from the txt and outputs it to the addSong method
			}
		}	catch (Exception ex) {ex.printStackTrace();}
	}

	void addSong(String lineToParse) {
		String[] tokens = lineToParse.split("/");
		Song nextSong = new Song(tokens[0], tokens[1], tokens[2], tokens[3]);
		songList.add(nextSong);
	}
}