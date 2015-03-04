package mainmenu.audio;

import java.io.File;

import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class QuietMix extends JFXPanel{ 

	/**
	 * 
	 */
	private static final long serialVersionUID = -2014395311651401980L;
	private MediaPlayer mediaPlayer;
	@SuppressWarnings("unused")
	public QuietMix(){
		File file = new File("./QuietMix.mp3");
		
		new File(((System.getProperty("user.home") + "/Documents/").replace("\\", "/")) + "JARVIS/").mkdir();
		
		String quietmixurl = "https://dl-web.dropbox.com/get/QuietMix.mp3?_subject_uid=392468930&w=AABZsas7gugcvVLgw-fcjMnmBcOjdHbMtkmhMao0nKuklg";
		
		
		Media hit = new Media(file.toURI().toString());
		mediaPlayer = new MediaPlayer(hit);
		mediaPlayer.setStopTime(Duration.INDEFINITE);
	}
	
	public MediaPlayer getMediaPlayer(){
		return mediaPlayer;
	}
}