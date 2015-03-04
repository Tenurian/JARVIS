package mainmenu.audio;

import java.io.File;

import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class Rain extends JFXPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8728095138302665292L;
	private MediaPlayer mediaPlayer;
	public Rain(){
		File file = new File("Rain.mp3");
		Media hit = new Media(file.toURI().toString());
		mediaPlayer = new MediaPlayer(hit);
		mediaPlayer.setStopTime(Duration.INDEFINITE);
		mediaPlayer.setVolume(1.0);
	}
	
	public MediaPlayer getMediaPlayer(){
		return mediaPlayer;
	}
}
