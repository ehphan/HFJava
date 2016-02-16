class Song implements Comparable<Song> {
//usually these match, we're specifying the type that the implementing class can be compared against. This means taht Song objects can be compared to other Song objects, for the purpose of sorting.
	String title;
	String artist;
	String rating;
	String bpm;

	//The HashSet(or anyone else calling this method) sends it another Song.
	public boolean equals(Object aSong) {
		Song s = (Song) aSong;
		return getTitle().equals(s.getTitle());
		//The great news is taht title is a String, and Strings have an overridden equals() methdo. So all we have to do is ask one title if it's equal to the otehr song's title.
	}

	public int hashCode() {
		return title.hashCode();
		//Same deal, the String class has an overridden hashCdoe() method, so you can just return the result of calling hashCode() on the title. Notice how hashCode() and equals() are using the SAME IV.
	}

	//The sort() method sends a Song to compareTo() to see how that Song compares to the Song on which the method was invoked.
	public int compareTo(Song s) {
		return title.compareTo(s.getTitle());
		//simple we just pass the work on to the title String objects, since we know strings have a compareTo() method.
	}

	Song(String t, String a, String r, String b) {
		title = t;
		artist = a;
		rating = r;
		bpm = b;
	}

	public String getTitle() {
		return title;
	}

	public String getArtist() {
		return artist;
	}

	public String getRating() {
		return rating;
	}

	public String getBpm() {
		return bpm;
	}

	public String toString() {
		//We override toString(), because when you do a System.out.println(aSongObject), we want to see the title. When you do a System.out.println(aListOfSongs), it calls the toString() method of each element in the list.
		return title;
	}
}