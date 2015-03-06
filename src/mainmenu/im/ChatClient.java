package mainmenu.im;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Timer;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ChatClient implements ActionListener{
	
	public static BufferedReader in;
	public static PrintWriter out;
	public static JFrame frame = new JFrame("Chat Room Client");
	public static JTextField textField = new JTextField(40);
	public static JTextArea messageArea = new JTextArea(8, 40);
	public static Timer refreshTimer = new Timer();
	public static final int RPS = 60;
	public static boolean first = false;
	
	public ChatClient() {
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
		textField.setEditable(true);
		messageArea.setEditable(false);
		messageArea.setBackground(Color.white);
		frame.getContentPane().add(textField, "South");
		frame.getContentPane().add(new JScrollPane(messageArea), "Center");
		frame.pack();
		frame.setLocationRelativeTo(null);
		textField.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		out.println(textField.getText());
		textField.setText("");
	}
	
	private String getServerAddress() {
		return JOptionPane.showInputDialog(frame, "Enter IP Address of the server: ", "Welcome to the Chat room!", JOptionPane.QUESTION_MESSAGE);
	}
	public static String getName(){
		return JOptionPane.showInputDialog(frame, "Choose a screen name: ", "Screen name Selection", JOptionPane.PLAIN_MESSAGE);
	}

	@SuppressWarnings("resource")
	public void run() throws IOException {
		String serverAddress = getServerAddress();
		Socket socket = new Socket(serverAddress, 49196);
		
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out = new PrintWriter(socket.getOutputStream(), true);

		refreshTimer.scheduleAtFixedRate(new RefreshScreen(), 0, 1000/RPS);
		first = false;
		frame.repaint();
	}
	
	@SuppressWarnings("resource")
	public void run(String input) throws IOException {
		String serverAddress = input;
		Socket socket = new Socket(serverAddress, 49196);
		
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out = new PrintWriter(socket.getOutputStream(), true);

		refreshTimer.scheduleAtFixedRate(new RefreshScreen(), 0, 1000/RPS);
		first = false;
		frame.repaint();
	}
	
	public static void initialize(String input) throws IOException {
		new ChatClient().run(input);
	}
	
	public static void initialize() throws IOException {
		new ChatClient().run();
	}
}