import java.io.*;
import java.net.*; //class socket is in java.net

public class DailyAdviceClient {

	public void go() {

		try {
			Socket s = new Socket ("127.0.0.1", 4242);

			InputStreamReader streamReader = new InputStreamReader(s.getInputStream());
			BufferedReader reader = new BufferedReader(streamReader);
			//Chain a BufferedReader to an InputStream Reader to the input stream from the socket.

			String advice = reader.readLine();
			System.out.println("Today you should: "+ advice);

			reader.close();
			//this closes all the streams
		}	catch (IOException ex) {ex.printStackTrace();}
	}

	public static void main (String[] args) {
		DailyAdviceClient client = new DailyAdviceClient();
		client.go();
	}
}