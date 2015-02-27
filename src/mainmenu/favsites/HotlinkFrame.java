package mainmenu.favsites;


import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class HotlinkFrame implements ActionListener {
	private static HotlinkManager manager = HotlinkManager.loadHotlinkManager("hotlinks");
	private static JFrame frame = new JFrame("Hotlinks");
	private static JScrollPane pane = null;
	private static JPanel container = new JPanel();
	private static JPanel buttonPanel = new JPanel();
	private static JButton add = new JButton("Add");
	
	public static void main(String[] args) {
		new HotlinkFrame().initialize();
	}
	
	public void initialize() {
		frame.setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300, 250);
		JLabel label = new JLabel("Your favorite sites:");
		container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
		pane = new JScrollPane(container);
		add.addActionListener(this);
		buttonPanel.add(add);
		for(Hotlink h : manager.getLinkList()) {
			container.add(h);
		}
		frame.setResizable(false);
		frame.add(label, BorderLayout.NORTH);
		frame.add(pane, BorderLayout.CENTER);
		frame.add(buttonPanel, BorderLayout.SOUTH);
		frame.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String name = JOptionPane.showInputDialog(null, "What would you like to call your new hotlink?");
		String link = JOptionPane.showInputDialog(null, "What is the actual URL to your hotlink?");
		Hotlink h = new Hotlink(name, link);
		manager.getLinkList().add(h);
		container.add(h);
		manager.saveHotlinkManager("hotlinks");
		frame.revalidate();
		frame.repaint();
	}

	public static HotlinkManager getManager() {
		return manager;
	}

	public static void setManager(HotlinkManager manager) {
		HotlinkFrame.manager = manager;
	}

	public static void removeLink(Hotlink hotlink) {
		manager.getLinkList().remove(hotlink);
		manager.saveHotlinkManager("hotlinks");
		container.remove(hotlink);
		frame.revalidate();
		frame.repaint();
	}
}
