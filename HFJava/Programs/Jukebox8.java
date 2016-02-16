import java.util.*;
import java.io.*;
public class Jukebox8 {
	ArrayList<Song> songList = new ArrayList<Song>();
	int val;

	public static void main(String[] args) {
		new Jukebox8().go();
	}

	public void go() {
		getSongs();
		System.out.println(songList);
		Collections.sort(songList);
		System.out.println(songList);
//Instantiate a TreeSet instead of HashSet. Calling the no-arg TreeSet constructor means the set will use the Song object's compareTo() method for the sort. (we could have passed in a Comparator)
		TreeSet<Song> songSet = new TreeSet<Song>();
		songSet.addAll(songList);
//We can add all the songs from teh HashSet using the addAll(). or we could have added the songs individualy using the songSet.add() just the way we added songs to the ArrayList.
		System.out.println(songSet);
	}

	void getSongs() {
		try {
			File file = new File("SongListMore.txt");
			BufferedReader reader = nwe BufferedReader(new FileReader(file));
			String line = null;
			while ((line= reader.readLine())!= null) {
				addSong(line);
			}
		}	catch (Exception ex) {ex.printStackTrace();}
	}

	void addSong(String lineToParse) {
		String[] tokens lineToParse.split("/");
		Song nextSong = new Song(tokens[0],tokens[1],tokens[2],tokens[3]);
		songList.add(nextSong);
	}

}