package mainmenu.im;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.util.HashSet;

public class ChatServer extends Thread{

	private static final int PORT = 49196;
	private boolean serverRunning = true;
	private HashSet<String> names = new HashSet<String>();
	private HashSet<PrintWriter> writers = new HashSet<PrintWriter>();
	
	public void run(){
		System.out.println("Chat Server running!");
		ServerSocket listener;
		try {
			listener = new ServerSocket(PORT);
			try{
				while(serverRunning){
					new Handler(listener.accept(), names, writers).start();
				}
			}
			finally {
				listener.close();
			}
		} catch (IOException e) {
		}
	}
	
	public static void initialize() throws IOException {
		new ChatServer().start();
	}
}