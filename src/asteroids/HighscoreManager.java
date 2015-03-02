package asteroids;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.LinkedList;

@SuppressWarnings("serial")
public class HighscoreManager implements Serializable {
	private LinkedList<Highscore> highscores = new LinkedList<Highscore>();
	private static final String DOCSPATH = ((System.getProperty("user.home") + "/Documents/").replace("\\", "/"));
	
	public void addScore(Highscore score) {
		if(highscores.isEmpty()) {
			highscores.add(score);
		} else {
			for(int i = 0; i < highscores.size(); i++) {
				Highscore currentScore = highscores.get(i);
				if(score.getValue() > currentScore.getValue()) {
					highscores.add(i, score);
					break;
				}
			} if(highscores.size() > 10) {
				highscores.removeLast();
			}
		}
	}
	
	public void saveHighscores() {
		try {
			new File(DOCSPATH + "JARVIS/").mkdir();
			FileOutputStream file = new FileOutputStream(DOCSPATH + "JARVIS/"+"highscores.sav");
			ObjectOutputStream out = new ObjectOutputStream(file);
			out.writeObject(this);
			out.close();
			file.close();
		} catch(IOException i) {
			i.printStackTrace();
		}
	}
	
	public HighscoreManager loadHighscores() {
		HighscoreManager manager = new HighscoreManager();
		try {
			new File(DOCSPATH + "JARVIS/").mkdir();
			FileInputStream file = new FileInputStream(DOCSPATH + "JARVIS/"+"highscores.sav");
			ObjectInputStream in = new ObjectInputStream(file);
			manager = (HighscoreManager) in.readObject();
			in.close();
			file.close();
		} catch(IOException i) {
			System.out.println("File not found, will create a new one!");
		} catch(ClassNotFoundException c) {
			saveHighscores();
			System.err.println("Class not found");
		} return manager;
	}
	
	public LinkedList<Highscore> getHighscores() {
		return highscores;
	}

	public void setHighscores(LinkedList<Highscore> highscores) {
		this.highscores = highscores;
	}
	
}
