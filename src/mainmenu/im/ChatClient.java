package mainmenu.im;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ChatClient implements ActionListener{
	
	public BufferedReader in;
	public PrintWriter out;
	public static JFrame frame = new JFrame("Chat Room Client");
	public static JTextField textField = new JTextField(40);
	public static JTextArea messageArea = new JTextArea(24, 40);
	public RefreshScreen rs = new RefreshScreen();
	public static GroupLayout layout = new GroupLayout(frame);
	public static final int RPS = 60;
	public static boolean first = false;
	private boolean isClientRunning = false;
	
	public ChatClient() {
		isClientRunning = true;
		frame.addWindowListener(new WindowStuff());
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
		textField.setEditable(true);
		messageArea.setEditable(false);
		messageArea.setBackground(Color.white);
		frame.getContentPane().add(new JScrollPane(messageArea), "Center");
		frame.getContentPane().add(textField, "South");
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
		System.out.println("Chat Client running!");
		String serverAddress = getServerAddress();
		Socket socket = new Socket(serverAddress, 49196);
		
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out = new PrintWriter(socket.getOutputStream(), true);
		
		rs.start();
		
		first = false;
		frame.repaint();
	}
	
	@SuppressWarnings("resource")
	public void run(String input) throws IOException {
		System.out.println("Chat Client running!");
		String serverAddress = input;
		Socket socket = new Socket(serverAddress, 49196);
		
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out = new PrintWriter(socket.getOutputStream(), true);

		if (!rs.isAlive()){
			rs.start();
		}
		else {
			System.out.println("ohhh snap!!");
		}
		first = false;
		frame.repaint();
	}
	
	public static void initialize(String input) throws IOException {
		new ChatClient().run(input);
	}
	
	public static void initialize() throws IOException {
		new ChatClient().run();
	}
	
	private class RefreshScreen extends Thread {
		
		@Override
		public void run() {
			while (isClientRunning){
			String line = "";
				try {
					line = in.readLine();
					System.out.println(line);
					
					if (!isClientRunning){
						out.println("LOGOUT");
					}
					else if (line.startsWith("SUBMITNAME")){
						out.println(ChatClient.getName());
					}
					else if (line.startsWith("NAMEACCEPTED")){
						textField.setEditable(true);
						messageArea.append(line.substring(13) + " has joined the chat room!\n");
						textField.setText("");
					}
					else if (line.startsWith("MESSAGE")){
						messageArea.append(line.substring(8) + "\n");
					}
					else if (line.startsWith("NAMEINUSE")){
						//TODO Let user know The name is already taken!
					}
				} 
				catch (IOException e) {
					System.out.println(e);
					break;
				}
			}
		}
	}
	
	private class WindowStuff extends WindowAdapter{
		
		@Override
		public void windowClosed(WindowEvent e){
			isClientRunning = false;
		}
	}
}