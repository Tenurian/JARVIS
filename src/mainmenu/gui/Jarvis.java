package mainmenu.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

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
		
		
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension d = tk.getScreenSize();
		this.pack();
		this.setLocation((d.width/2)-(this.getWidth()/2), (d.height/2)-(this.getHeight()/2));
		this.setVisible(true);
	}
	
	public void genweatherpanel(){
		weatherpanel = new JPanel();
		weatherpanel.setBackground(new Color(0,126,164));
	}
	
	public void genfavsitespanel(){
		favsitespanel = new JPanel();
		favsitespanel.setBackground(new Color(0,102,0));
	}
	
	public void genbuttonpanel(){
		buttonpanel = new JPanel();
		am = new JButton("Account Manager");
		cc = new JButton("Conversion Calculator");
		cb = new JButton("Contact Book");
		
		am.setActionCommand("launchaccountmanager");
		am.addActionListener(this);
		
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
		}
	}
}