package mainmenu.gui;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Jarvis extends JFrame {

	private JPanel buttonpanel, weatherpanel, favsitespanel;
	private JButton am, cc, cb;
	
	public Jarvis(){
		super("JARVIS - Personal Java Desktop Assistant v0.0.1");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setPreferredSize(new Dimension(1500,750));
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension d = tk.getScreenSize();
		this.pack();
		this.setResizable(false);
		this.setLocation((d.width/2)-(this.getWidth()/2), (d.height/2)-(this.getHeight()/2));
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		new Jarvis();
	}
}