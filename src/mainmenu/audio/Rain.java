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
		String rainurl = "https://dl-web.dropbox.com/get/Rain.mp3?_subject_uid=392468930&w=AABuG6_NBM18StU3hEI_W2375M7wGx8s3lF2CHL7PUciJQ";
		
		
		File file = new File(rainurl);
		System.out.println(file.toURI().toString());
		Media hit = new Media(file.toURI().toString());
		mediaPlayer = new MediaPlayer(hit);
		mediaPlayer.setStopTime(Duration.INDEFINITE);
		mediaPlayer.setVolume(1.0);
	}
	
	public MediaPlayer getMediaPlayer(){
		return mediaPlayer;
	}
}
