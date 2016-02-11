import java.util.*;
import java.io.*;

public class JukeBox6 {
	ArrayList<Song> songList = new ArrayList<Song>();
	public static void main (String[] args) {
		new Jukebox6().go();
	}
//Create a new inner class that implements Comparator(note that its type parameter matches the type we're going to compare, in this case song objects)
	class ArtistCompare implements Comparator<Song> {
		public int compare (Song one, Song two) {
			return one.getArtist().compareTo(two.getArtist());
			//We're letting the String variables (for artist) do the actual comparison, since Strings already know how to alphabetize themselves.
		}
	}

	public void go() {
		getSongs();
		System.out.println(songList);
		Collections.sort(songList);
		System.out.println(songList);

		HashSet<Song> songSet = new HashSet<Song>();
		//HashSet has a simple addAll() method that can take another collection and use it to populate the HashSet. It's the same as if we added each song one at a time.
		songSet.addAll(songList);
		System.out.println(songSet);
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

	//We've made sort-bytitle the default sort, by keeping teh compareTo() method in the Song use the titles. But anotehr way to design this would be to implement both the title sorting and artist sorting as inner Comparator classes, adn not have the Song implement Comparable at all. That means we'd always use the two-arg version of Collections.sort().
}