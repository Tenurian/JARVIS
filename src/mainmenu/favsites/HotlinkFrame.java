package mainmenu.favsites;


import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class HotlinkFrame extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static HotlinkManager manager = HotlinkManager.loadHotlinkManager("hotlinks");
	private static JScrollPane pane = null;
	private static JPanel container = new JPanel();
	private static JPanel buttonPanel = new JPanel();
	private static JButton add = new JButton("Add");

	public void initialize() {
		this.setLayout(new BorderLayout());
		JLabel label = new JLabel("Your favorite sites:");
		container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
		pane = new JScrollPane(container);
		add.addActionListener(this);
		buttonPanel.add(add);
		for(Hotlink h : manager.getLinkList()) {
			container.add(h);
		}
		this.add(label, BorderLayout.NORTH);
		this.add(pane, BorderLayout.CENTER);
		this.add(buttonPanel, BorderLayout.SOUTH);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String name = JOptionPane.showInputDialog(null, "What would you like to call your new hotlink?");
		String link = null;
		if(name!= null){
			if(!name.equalsIgnoreCase("")){
				link = JOptionPane.showInputDialog(null, "What is the actual URL to your hotlink?");
			}
		}
		if(name != null && link != null){
			if(!name.equalsIgnoreCase("") && !link.equalsIgnoreCase("")){
				Hotlink h = new Hotlink(name, link);
				h.setHotlinkContainer(this);
				manager.getLinkList().add(h);
				container.add(h);
				manager.saveHotlinkManager("hotlinks");
				this.revalidate();
				this.repaint();
			}
		}
	}

	public HotlinkManager getManager() {
		return manager;
	}

	public void setManager(HotlinkManager manager) {
		HotlinkFrame.manager = manager;
	}

	public void removeLink(Hotlink hotlink) {
		manager.getLinkList().remove(hotlink);
		manager.saveHotlinkManager("hotlinks");
		container.remove(hotlink);
		this.revalidate();
		this.repaint();
		this.revalidate();
		this.repaint();
		container.repaint();
		//		this.getParent().repaint();
		//		this.getParent().revalidate();
		//		this.getParent().repaint();
	}
}
