import java.util.*;

public class TestMap {
	public static void main (String[] args) {
		HashMap<String, Integer> scores = new HashMap<String,Integer>();
		//HashMap needs TWO type parameters - one for the key and one for the value. FIRST KEY SECOND VALUE

		scores.put("Kathy", 42);
		scores.put("Ber", 323);
		scores.put("sky", 420);
		//Use put() instead of add() , and now of course it takes 2 args
		System.out.println(scores);
		System.out.println(scores.get("Bert"));
		//The get() method takes a key, and returns the value
	}
}