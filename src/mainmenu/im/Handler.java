package mainmenu.im;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashSet;

public class Handler extends Thread{

	private HashSet<String> names;
	private HashSet<PrintWriter> writers;

	private String name;
	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;

	public Handler(Socket socket, HashSet<String> names, HashSet<PrintWriter> writers){
		this.socket = socket;
		this.names = names;
		this.writers = writers;
	}

	public void run(){
		try{
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(), true);

			while (true){
				out.println("SUBMITNAME");
				name = in.readLine();
				if (name == null){
					return;
				}
				synchronized (names) {
					if (!names.contains(name)){
						names.add(name);
						break;
					}
					else{
						out.println("NAMEINUSE");
					}
				}
			}

			out.println("NAMEACCEPTED " + name);
			writers.add(out);
			
			while (true){
				String input = in.readLine();
				if (input == null){
					return;
				}
				if (input.equals("LOGOUT")){
					removeUser();
				}
				else if (!input.trim().equals("")){
					for (PrintWriter w: writers){
						w.println("MESSAGE " + name + ": " + input);
					}
				}
			}
		} catch (IOException e){
			System.out.println(e);
		}
	}
	
	private void removeUser(){
		if (name != null){
			names.remove(name);
		}
		if (out != null){
			writers.remove(out);
		}
		try{
			socket.close();
		} catch (IOException e){
		}
	}
}