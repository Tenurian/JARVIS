package mainmenu.exebar;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

@SuppressWarnings("serial")
public class Shortcut extends JPanel implements ActionListener, Serializable{
	private String link = "";
	private String linkName = "";
	private JButton go = new JButton("Go");
	private JButton del = new JButton("x");
	private JLabel name = new JLabel("Default");
	private ShortcutFrame container;
	
	public Shortcut(String linkName, String link){
		this.setLayout(new BorderLayout());
		this.linkName = linkName;
		this.link = link;
		while(this.linkName == null || this.linkName.isEmpty()) {
			this.linkName = JOptionPane.showInputDialog(null, "You must give your shortcut a name!");
		}
		while(this.link == null || !this.link.contains(".exe")) {
			JOptionPane.showInternalMessageDialog(null, "Your link must be a .exe", "Invalid File Type", JOptionPane.ERROR_MESSAGE);
			JFileChooser chooser2 = new JFileChooser();
			FileNameExtensionFilter filter2 = new FileNameExtensionFilter("Executable files", "exe");
			chooser2.setFileFilter(filter2);
			int returnVal2 = chooser2.showOpenDialog(getParent());
			if(returnVal2 == JFileChooser.APPROVE_OPTION) {
				link = chooser2.getSelectedFile().getAbsolutePath();
			}
		}
		

		File file = new File(link);
		Icon icon = FileSystemView.getFileSystemView().getSystemIcon(file);
		name = new JLabel(linkName, icon, SwingConstants.LEFT);
		
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		name.setText(this.linkName);
		go.setHorizontalAlignment(SwingConstants.LEFT);
		name.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(go, BorderLayout.WEST);
		this.add(Box.createHorizontalGlue());
		this.add(name, BorderLayout.CENTER);
		this.add(Box.createHorizontalGlue());
		this.add(del, BorderLayout.EAST);
		go.addActionListener(this);
		del.addActionListener(this);
	}
	
	public void setShortcutContainer(ShortcutFrame container){
		this.container = container;
	}
	
	@Override
	public void actionPerformed(ActionEvent i) {
		if(i.getSource() == go){
			try {
				Runtime.getRuntime().exec(this.link);
			} catch (IOException e1) {
				JOptionPane.showInternalMessageDialog(null, "Executable file not found, removing shortcut", "Error 404", JOptionPane.ERROR_MESSAGE);
				container.removeLink(this);
			}
		}else{
			container.removeLink(this);
		}
	}

}
