import java.io.*;

class DungeonGame implements Serializable {

	public int x = 3;
	transient long y = 4;
	private short z = 5;

	short getZ() {return z;}
	long getY() {return y;}
	int getX() {return x;}


}

class DungeonTest {

	public static void main (String[] args) {

		DungeonGame d = new DungeonGame();
		System.out.println(d.getX()+d.getY()+d.getZ());

		try {
			FileOutputStream fos = new FileOutputStream("dg.ser");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(d);
		}	catch (Exception e) {e.printStackTrace();}
		

		try {
			FileInputStream fis = new FileInputStream("dg.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);
			d = (DungeonGame) ois.readObject();
		}	catch(Exception ex) {ex.printStackTrace();}
		System.out.println(d.getX()+d.getY()+d.getZ());

	}


}