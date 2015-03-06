package mainmenu.im;

import java.io.IOException;
import java.util.TimerTask;

import static mainmenu.im.ChatClient.*;

public class RefreshScreen extends TimerTask {
	
	@Override
	public void run() {
		String line;
		try {
			line = in.readLine();
			System.out.println(line);
			
			if (line.startsWith("SUBMITNAME")){
				out.println(getName());
			}
			else if (line.startsWith("NAMEACCEPTED")){
				textField.setEditable(true);
			}
			else if (line.startsWith("MESSAGE")){
				messageArea.append(line.substring(8) + "\n");
			}
			else if (line.startsWith("NAMEINUSE")){
				//TODO Let user know The name is already taken!
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
		}

	}

}
