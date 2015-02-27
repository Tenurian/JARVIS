package mainmenu.weather;

import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class WeatherTest {
	private static String[] days = {"Sun","Mon","Tue","Wed","Thu","Fri","Sat","Sun","Mon","Tue","Wed","Thu","Fri","Sat"};
	public static  ArrayList<Image> images;
	public static JFrame frame = new JFrame();
	public static void main(String[] args) throws IOException {
		WeatherInfo w = new WeatherInfo();
		Calendar c = Calendar.getInstance();
		c.setTime(c.getTime());
		int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
		frame.setLayout(new BoxLayout(frame, BoxLayout.Y_AXIS));
		for(int i = 0; i < 7; i++){
			String forecast = "";
			forecast+= days[dayOfWeek-1+i];
			forecast+=": \nTemp: "+w.getTemp(i)+"\nConditions: "+w.getDesc(i);
			System.out.println(forecast);
			images.add(ImageIO.read(w.getIconURL(i)));
		}
	}
}
