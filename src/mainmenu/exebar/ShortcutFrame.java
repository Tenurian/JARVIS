package mainmenu.exebar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import mainmenu.gui.Jarvis;

public class ShortcutFrame extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3408135068752385466L;
	private static ShortcutManager manager = ShortcutManager.loadShortcutManager("shortcuts");
	private static JScrollPane pane = null;
	private static JPanel container = new JPanel();
	private static JPanel buttonPanel = new JPanel();
	private static JButton add = new JButton("Add");

	public void initialize(){
		this.setLayout(new BorderLayout());

		if(Jarvis.COLOR_PANELS){
			this.setBackground(new Color(0,200,0));
			container.setBackground(new Color(0,200,0));
		}

		JLabel label = new JLabel("Your favorite programs:", JLabel.CENTER);
		container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
		pane = new JScrollPane(container);
		add.addActionListener(this);
		buttonPanel.add(add);
		for(Shortcut s : manager.getShortList()){
			container.add(s);
		}
		this.add(label, BorderLayout.NORTH);
		this.add(pane, BorderLayout.CENTER);
		this.add(buttonPanel, BorderLayout.SOUTH);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		String name = JOptionPane.showInputDialog(null, "What would you like to call your new shortcut?");
		String link = null;
		if(name!= null){
			if(!name.equalsIgnoreCase("")){
				//				link = JOptionPane.showInputDialog(null, "What is the actual URL to your shortcut?");
				JFileChooser chooser2 = new JFileChooser();
				FileNameExtensionFilter filter2 = new FileNameExtensionFilter("Executable files", "exe");
				chooser2.setFileFilter(filter2);
				int returnVal2 = chooser2.showOpenDialog(getParent());
				if(returnVal2 == JFileChooser.APPROVE_OPTION) {
					link = chooser2.getSelectedFile().getAbsolutePath();
				}
			}
		}
		if(name != null && link != null){
			if(!name.equalsIgnoreCase("") && !link.equalsIgnoreCase("")){
				Shortcut s = new Shortcut(name, link);
				s.setShortcutContainer(this);
				manager.getShortList().add(s);
				container.add(s);
				manager.saveShortcutManager("shortcuts");
				this.revalidate();
				this.repaint();
			}
		}
	}

	public ShortcutManager getManager(){
		return manager;
	}

	public void setManager(ShortcutManager manager){
		ShortcutFrame.manager = manager;
	}

	public void removeLink(Shortcut shortcut){
		manager.getShortList().remove(shortcut);
		manager.saveShortcutManager("shortcuts");
		container.remove(shortcut);
		this.revalidate();
		this.repaint();
		this.revalidate();
		this.repaint();
		container.repaint();
	}

}
