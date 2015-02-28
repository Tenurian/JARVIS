package mainmenu.weather;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class WeatherTest extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3582865895299483784L;
	private static String[] days = {"Sun","Mon","Tue","Wed","Thu","Fri","Sat","Sun","Mon","Tue","Wed","Thu","Fri","Sat"};
	public static  ArrayList<Image> images;
	public static JFrame frame = new JFrame();
	
//	public static void main(String[] args) throws IOException {
//		frame.setPreferredSize(new Dimension(750,100));
//		frame.setLayout(new GridLayout());
//		WeatherInfo w = new WeatherInfo();
//		Calendar c = Calendar.getInstance();
//		c.setTime(c.getTime());
//		int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
//		for(int i = 0; i < 7; i++){
//			String forecast = "";
//			forecast+= days[dayOfWeek-1+i];
//			forecast+=": \nTemp: "+w.getTemp(i)+"\nConditions: "+w.getDesc(i);
//			System.out.println(forecast);
//			JPanel dayPanel = new JPanel(new GridBagLayout());
//			GridBagConstraints x = new GridBagConstraints();
//			JLabel daylab = new JLabel(days[dayOfWeek-1+i]);
//			JLabel highlab = new JLabel("High: "+w.getMax(i));
//			JLabel lowlab = new JLabel("Low: "+w.getMin(i));
//			JLabel deslab = new JLabel("Conditions: "+w.getDesc(i));
//	        BufferedImage image = ImageIO.read((java.net.URL)w.getIconURL(i));
//	        JLabel img = new JLabel(new ImageIcon(image));
//	        x.gridx=0;
//	        x.gridy=0;
//	        dayPanel.add(daylab, x);
//	        x.gridx=1;
//	        dayPanel.add(highlab, x);
//	        x.gridx=0;
//	        x.gridy=1;
//	        dayPanel.add(img, x);
//	        x.gridx=1;
//	        dayPanel.add(lowlab, x);
//	        x.gridwidth=2;
//	        x.gridx=0;
//	        x.gridy=2;
//	        dayPanel.add(deslab, x);
//	        
//	        frame.add(dayPanel);
//		}
//		frame.pack();
//		frame.setVisible(true);
//	}
	
	public WeatherTest(){
	}
	
	public void init(LayoutManager lm){
		String[] days = {"Sun","Mon","Tue","Wed","Thu","Fri","Sat","Sun","Mon","Tue","Wed","Thu","Fri","Sat"};
		WeatherInfo w = new WeatherInfo();
		Calendar c = Calendar.getInstance();
		c.setTime(c.getTime());
		int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
		JPanel dayPanel = new JPanel(new GridBagLayout());
		for(int i = 0; i < 7; i++){
			String forecast = "";
			forecast+= days[dayOfWeek-1+i];
			forecast+=": \nTemp: "+w.getTemp(i)+"\nConditions: "+w.getDesc(i);
			System.out.println(forecast);
			GridBagConstraints x = new GridBagConstraints();
			JLabel daylab = new JLabel(days[dayOfWeek-1+i]);
			JLabel highlab = new JLabel("High: "+w.getMax(i));
			JLabel lowlab = new JLabel("Low: "+w.getMin(i));
			JLabel deslab = new JLabel("Conditions: "+w.getDesc(i));
	        BufferedImage image = null;
			try {
				image = ImageIO.read((java.net.URL)w.getIconURL(i));
			} catch (IOException e) {
				e.printStackTrace();
			}
	        JLabel img = new JLabel(new ImageIcon(image));
	        x.gridx=0;
	        x.gridy=0;
	        dayPanel.add(daylab, x);
	        x.gridx=1;
	        dayPanel.add(highlab, x);
	        x.gridx=0;
	        x.gridy=1;
	        dayPanel.add(img, x);
	        x.gridx=1;
	        dayPanel.add(lowlab, x);
	        x.gridwidth=2;
	        x.gridx=0;
	        x.gridy=2;
	        dayPanel.add(deslab, x);
			this.add(dayPanel);
		}
	}
}
