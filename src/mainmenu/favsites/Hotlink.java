package mainmenu.favsites;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.Serializable;
import java.net.URISyntaxException;

import javax.swing.*;

@SuppressWarnings("serial")
public class Hotlink extends JPanel implements ActionListener, Serializable{
	private String link = "";
	private String linkName = "";
	private JButton go = new JButton("Go");
	private JButton del = new JButton("x");
	private JLabel name = new JLabel("Default");
	
	public Hotlink(String linkName, String link) {
		this.linkName = linkName;
		this.link = link;
		this.link.toLowerCase();
		while(this.linkName == null || this.linkName.isEmpty()) {
			this.linkName = JOptionPane.showInputDialog(null, "You must give your hotlink a name!");
		}
		while(this.link == null || !this.link.contains("www.")) {
			this.link = JOptionPane.showInputDialog(null, "Your link does not contain 'www.'!");
		} this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		name.setText(this.linkName);
		go.setHorizontalAlignment(SwingConstants.LEFT);
		name.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(go);
		//this.add(Box.createGlue());
		this.add(name);
		this.add(Box.createHorizontalGlue());
		this.add(del);
		go.addActionListener(this);
		del.addActionListener(this);
	}

	public void actionPerformed(ActionEvent i) {
		if(i.getSource() == go) {
			try {
				this.openUrl(link);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
		} else {
			HotlinkFrame.removeLink(this);
		}
	}
	
	public void openUrl(String url) throws IOException, URISyntaxException {
		if (java.awt.Desktop.isDesktopSupported()) {
			java.awt.Desktop desktop = java.awt.Desktop.getDesktop();

			if (desktop.isSupported(java.awt.Desktop.Action.BROWSE)) {
				java.net.URI uri = new java.net.URI(url);
				desktop.browse(uri);
			}
		}
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getLinkName() {
		return linkName;
	}

	public void setLinkName(String linkName) {
		this.linkName = linkName;
	}

}
