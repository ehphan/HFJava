import java.io.*;
import java.net.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SimpleChatClientA {

	JTextField outgoing;
	Socket sock;
	PrintWriter writer;

	public void go() {
		JFrame frame = new JFrame ("Simple Chat Client");
		JPanel mainPanel = new JPanel();
		outgoing = new JTextField(20);
		JButton sendButton = new JButton ("Send");
		sendButton.addActionListener(new SendButtonListener());
		mainPanel.add(outgoing);
		mainPanel.add(sendButton);
		frame.getContentPane().add(BorderLayout.CENTER, mainPanel);

		setUpNetworking();

		frame.setSize(400,500);
		frame.setVisible(true);
	}

	private void setUpNetworking() {
		try {
			sock = new Socket("127.0.0.1", 5000);
			writer = new PrintWriter(sock.getOutputStream());
			//This is where we make the Socket and the PrintWriter (its called from the go() method right before displaying the app GUI)
			System.out.println("networking established");
		} catch (IOException ex) {ex.printStackTrace();}
	}

	public class SendButtonListener implements ActionListener {
		public void actionPerformed (ActionEvent ev) {
			try {
				writer.println(outgoing.getText());
//Now we actually do the writing. Remember, the writer is chained to the input stream from the Socket, so whenever we do a println() , it goes over the network to the server.
				writer.flush();
			} catch (Exception ex) {ex.printStackTrace();}
			outgoing.setText("");
			outgoing.requestFocus();
		}
	}
	
	public static void main (String[] args) {
		SimpleChatClientA scc = new SimpleChatClientA ();
		scc.go();
	}	
}