package mainmenu.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Calendar;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import mainmenu.favsites.HotlinkFrame;
import mainmenu.weather.WeatherInfo;

public class Jarvis extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 935456660030463827L;
	private JPanel buttonpanel, weatherpanel, favsitespanel;
	private JButton am, cc, cb;
	
	public Jarvis(){
		super("JARVIS - Personal Java Desktop Assistant v0.0.1");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setPreferredSize(new Dimension(1500,750));
		this.setLayout(new GridBagLayout());

		genbuttonpanel();
		genweatherpanel();
		genfavsitespanel();
		
		GridBagConstraints c = new GridBagConstraints();
		c.gridheight = 1;
		c.gridy = 0;
		this.add(buttonpanel, c);
		c.weightx = 1.0;
		c.weighty = 1.0;
		c.gridheight = 3;
		c.gridy = 1;
		c.fill = GridBagConstraints.BOTH;
		this.add(weatherpanel, c);
		c.gridheight = 3;
		c.gridy = 4;
		this.add(favsitespanel, c);
		
		
		this.pack();
        this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	public void genweatherpanel(){
		weatherpanel = new JPanel(new GridLayout());
		JPanel innerpanel = new JPanel();

		String[] days = {"Sun","Mon","Tue","Wed","Thu","Fri","Sat","Sun","Mon","Tue","Wed","Thu","Fri","Sat"};
		WeatherInfo w = new WeatherInfo();
		Calendar c = Calendar.getInstance();
		c.setTime(c.getTime());
		int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
		for(int i = 0; i < 7; i++){
			JPanel dayPanel = new JPanel(new GridBagLayout());
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
	        x.fill = GridBagConstraints.BOTH;
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
	        dayPanel.setBackground(new Color(0,126,164));
			weatherpanel.add(dayPanel);
		}
		weatherpanel.setBackground(new Color(0,126,164));
	}
	
	public void genfavsitespanel(){
		favsitespanel = new HotlinkFrame();
		((HotlinkFrame)favsitespanel).initialize();
	}
	
	public void genbuttonpanel(){
		buttonpanel = new JPanel();
		am = new JButton("Account Manager");
		cc = new JButton("Conversion Calculator");
		cb = new JButton("Contact Book");
		
		am.setActionCommand("launchaccountmanager");
		am.addActionListener(this);
		cb.setActionCommand("launchcontactbook");
		cb.addActionListener(this);
		cc.setActionCommand("launchconversioncalc");
		cc.addActionListener(this);
		
		buttonpanel.add(am);
		buttonpanel.add(cb);
		buttonpanel.add(cc);
	}
	
	public static void main(String[] args) {
		new Jarvis();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()){
		case "launchaccountmanager":
			new accountmanager.gui.AccountManagerGUI();
			break;
		case "launchconversioncalc":
			new conversioncalc.ConvCalc();
			break;
		case "launchcontactbook":
			new contactbook.ContactBook();
			break;
		}
	}
}