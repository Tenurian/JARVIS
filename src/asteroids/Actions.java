package asteroids;

import java.util.TimerTask;

public class Actions extends TimerTask {

	public void run() {
		try{
			Main.main.refresh();
		}catch(IllegalStateException e){

		}
	}

}
