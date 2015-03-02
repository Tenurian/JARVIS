package asteroids;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Highscore implements Serializable{
	private int value = 0;
	
	public Highscore(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
}
