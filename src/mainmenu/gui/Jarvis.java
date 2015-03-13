package mainmenu.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Calendar;

import javafx.scene.media.MediaException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

import mainmenu.audio.ControlsPanel;
import mainmenu.exebar.ShortcutFrame;
import mainmenu.favsites.HotlinkFrame;
import mainmenu.weather.WeatherInfo;

public class Jarvis extends JFrame implements ActionListener{

	private static final long serialVersionUID = 935456660030463827L;
	@SuppressWarnings("unused")
	private JPanel buttonpanel, ambientpanel, weatherpanel, favsitespanel, exepanel;
	private JButton am, cc, cb, as;
	private WeatherInfo w;
	public static final boolean COLOR_PANELS = false;
	public Jarvis(){
		super("JARVIS - Java Run Virtual Secretary v1.0.0.1");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setPreferredSize(new Dimension(1130,800));
		this.setMinimumSize(getPreferredSize());
		this.setLayout(new GridBagLayout());

		genbuttonpanel();
		//		genAmbientPanel();
		genweatherpanel();
		genfavsitespanel();

		genExepanel();

		GridBagConstraints c = new GridBagConstraints();
		c.gridheight = 1;
		c.gridy = 0;
		this.add(buttonpanel, c);
		c.gridy=1;
		c.fill = GridBagConstraints.BOTH;
		//		this.add(ambientpanel, c);
		c.weightx = 1.0;
		c.weighty = 1.0;
		c.gridheight = 3;
		c.gridy = 2;
		weatherpanel.setMaximumSize(new Dimension(1920, 50));
		weatherpanel.setPreferredSize(new Dimension(1920, 50));
		this.add(weatherpanel, c);
		c.gridheight = 1;
		c.gridy = 5;
		favsitespanel.setPreferredSize(new Dimension(1920, 250));
		favsitespanel.setMinimumSize(new Dimension(950, 250));
		favsitespanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		this.add(favsitespanel, c);

		c.gridy = 7;
		exepanel.setPreferredSize(new Dimension(1920, 250));
		exepanel.setMinimumSize(new Dimension(950, 250));
		exepanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));

		this.add(exepanel, c);


		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	public void genAmbientPanel(){
		try{
			ambientpanel = new ControlsPanel();
		}catch(MediaException me){
			System.out.println("unable to load media");
		}
	}

	public void genweatherpanel(){
		weatherpanel = new JPanel(new BorderLayout());
		try {
			//bort
			w = new WeatherInfo();
			JPanel weatherinnerpanel = new JPanel(new GridLayout());
			String[] days = {"Sun","Mon","Tue","Wed","Thu","Fri","Sat","Sun","Mon","Tue","Wed","Thu","Fri","Sat"};
			Calendar c = Calendar.getInstance();
			c.setTime(c.getTime());
			int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
			for(int i = 0; i < 7; i++){
				JPanel dayPanel = new JPanel(new GridBagLayout());
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
				weatherinnerpanel.add(dayPanel);
			}
			weatherpanel.add(new JLabel("Weather for "+w.getLocation(), JLabel.CENTER), BorderLayout.NORTH);
			weatherpanel.add(weatherinnerpanel);
			weatherinnerpanel.setBackground(new Color(0,126,164));
		} catch (IOException e1) {
			weatherpanel.add(new JLabel("Cannot get weather: Please check internet connection", JLabel.CENTER), BorderLayout.CENTER);
		}
		weatherpanel.setBackground(new Color(0,126,164));
	}

	public void updateWeather(){
		weatherpanel.removeAll();
		//		System.out.println(w.getLocation());
		JPanel weatherinnerpanel = new JPanel(new GridLayout());
		String[] days = {"Sun","Mon","Tue","Wed","Thu","Fri","Sat","Sun","Mon","Tue","Wed","Thu","Fri","Sat"};
		Calendar c = Calendar.getInstance();
		c.setTime(c.getTime());
		int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
		for(int i = 0; i < 7; i++){
			JPanel dayPanel = new JPanel(new GridBagLayout());
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
			weatherinnerpanel.add(dayPanel);
		}
		weatherpanel.add(new JLabel("Weather for "+w.getLocation(), JLabel.CENTER), BorderLayout.NORTH);
		weatherpanel.add(weatherinnerpanel);
		weatherinnerpanel.setBackground(new Color(0,126,164));
		weatherpanel.setBackground(new Color(0,126,164));
		weatherpanel.revalidate();
		weatherpanel.repaint();
		this.revalidate();
		this.repaint();
	}

	public void genfavsitespanel(){
		favsitespanel = new HotlinkFrame();
		((HotlinkFrame)favsitespanel).initialize();
		//		favsitespanel.setBackground(new Color(200,50,190));
	}

	public void genExepanel(){
		exepanel = new ShortcutFrame();
		((ShortcutFrame)exepanel).initialize();
		//		exepanel.setBackground(new Color(0,128,0));
	}

	public void genbuttonpanel(){
		buttonpanel = new JPanel();
		JButton locationset = new JButton("Change Location");
		locationset.setActionCommand("locationset");
		locationset.addActionListener(this);
		JButton chatbutton = new JButton("IM");
		chatbutton.setActionCommand("im");
		chatbutton.addActionListener(this);
		chatbutton.setToolTipText("Launches the Instant Messenger Client");

		am = new JButton("Account Manager");
		cc = new JButton("Conversion Calculator");
		cb = new JButton("Contact Book");
		as = new JButton("Play Asteroids");

		am.setActionCommand("launchaccountmanager");
		am.addActionListener(this);
		cb.setActionCommand("launchcontactbook");
		cb.addActionListener(this);
		cc.setActionCommand("launchconversioncalc");
		cc.addActionListener(this);
		as.setActionCommand("asteroids");
		as.addActionListener(this);

		buttonpanel.add(locationset);
		buttonpanel.add(am);
		buttonpanel.add(cb);
		buttonpanel.add(cc);
		buttonpanel.add(as);
		buttonpanel.add(chatbutton);
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
		case "asteroids":
			edu.csc150.main.Main.init();
			break;
		case "locationset":
			new mainmenu.weather.WeatherLocationSetter(w,this);
			break;
		case "im":
			int x = JOptionPane.showConfirmDialog(null, "Would you like to host the server?");
			if(x == JOptionPane.YES_OPTION){
				if(!mainmenu.im.ChatServer.isrunning){
					//run server
					try {
						mainmenu.im.ChatServer.initialize();
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(null, "Error starting the server.", "Server Start Incomplete", JOptionPane.ERROR_MESSAGE);
					}
				}else{
					JOptionPane.showMessageDialog(null, "Error starting the server.", "Server Already Running", JOptionPane.WARNING_MESSAGE);
				}
				//run client
				if(!mainmenu.im.ChatClient.frame.isVisible()){
					try {
						String localaddress = ""+InetAddress.getLocalHost()+"";
						localaddress = localaddress.substring(localaddress.indexOf("/")+1);
						JOptionPane.showMessageDialog(null, "Your IP is: "+localaddress+"", "Your Server IP", JOptionPane.INFORMATION_MESSAGE);
						mainmenu.im.ChatClient.first = true;
						mainmenu.im.ChatClient.initialize("localhost");
						mainmenu.im.ChatClient.first = true;
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(null, "Error starting the client.", "Client Start Incomplete", JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
					}
				}else{
					JOptionPane.showMessageDialog(null, "Error starting the client.", "Client Already Running", JOptionPane.WARNING_MESSAGE);
				}
			}else if(x == JOptionPane.NO_OPTION){
				if(!mainmenu.im.ChatClient.frame.isVisible()){
					//run client
					try {
						mainmenu.im.ChatClient.first = true;
						mainmenu.im.ChatClient.initialize();
						mainmenu.im.ChatClient.first = true;
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(null, "Error starting the client.", "Client Start Incomplete", JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
					}
				}else{
					JOptionPane.showMessageDialog(null, "Error starting the client.", "Client Already Running", JOptionPane.WARNING_MESSAGE);
				}
			}
			break;
		}
	}
}