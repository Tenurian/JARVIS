package accountmanager.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import accountmanager.accounts.*;
import accountmanager.fileio.AccountManager;
/**
 * The Main file for the Account Manager, contains the main method and the JFrame, and GUI elements
 * @author Tenurian
 *
 */
public class AccountManagerGUI extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7902517699264179235L;
	private static final String PATHNAME = ((System.getProperty("user.home") + "/Documents/").replace("\\", "/"));
	private AccountManager profile;
	private boolean saved;
	private String programurl = null;
	JPanel view, delete;
	JTabbedPane add;
	JPanel email, game, store, socmed, other;
	TableModel viewmodel, delmodel;
	JTable viewtable, deltable;
	JTabbedPane mainpanel;

	JTextField 
	accnmtf0 = new JTextField("", 10), //eml
	accnmtf1 = new JTextField("", 10), //gam
	accnmtf2 = new JTextField("", 10), //soc
	accnmtf3 = new JTextField("", 10), //str
	accnmtf4 = new JTextField("", 10), //oth
	editnametf = new JTextField("", 10),
	editpasstf = new JTextField("", 10),
	editlinktf = new JTextField("", 15),
	passtf0 = new JTextField("", 10),
	passtf1 = new JTextField("", 10),
	passtf2 = new JTextField("", 10),
	passtf3 = new JTextField("", 10),
	passtf4 = new JTextField("", 10),
	linktf0 = new JTextField("", 15),
	linktf2 = new JTextField("", 15),
	linktf3 = new JTextField("", 15),
	typetf = new JTextField("",10);

	JCheckBox 
	linkcb0 = new JCheckBox("URL:"),
	linkcb1 = new JCheckBox("Program:"),
	linkcb2 = new JCheckBox("URL:"),
	linkcb3 = new JCheckBox("URL:"),
	editcba = new JCheckBox("Program/Link:");

	JRadioButton urlRB = new JRadioButton("URL:"), srcRB = new JRadioButton("Program:");
	ButtonGroup group = new ButtonGroup();

	JButton gameChooserButton, editchooserbutton;

	JTextArea 
	descarea0 = new JTextArea(15,30),
	descarea1 = new JTextArea(15,30),
	descarea2 = new JTextArea(15,30),
	descarea3 = new JTextArea(15,30),
	descarea4 = new JTextArea(15,30),
	editdesc  = new JTextArea(1,30);


	boolean test = false;
	int asdf = 0;

	String[] columnnames = {"Userame","Password","Type","Description"};
	/**
	 * Basic Constructor, sets up the JFrame
	 */
	public AccountManagerGUI(){
		saved = false;
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setPreferredSize(new Dimension(450,460));

		profile = new AccountManager();

		mainpanel = new JTabbedPane();
		updateTables();
		mainpanel.setSelectedIndex(0);

		this.add(mainpanel);

		this.pack();
        this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	/**
	 * Calls the generation methods for the panels, then adds them to the main panel
	 */
	public void generatePanels(){
		generateViewPanel();
		generateDeletePanel();
		generateAddPanel();

		mainpanel.add("View", view);
		mainpanel.add("Edit",delete);
		mainpanel.add("Add", add);
	}

	/**
	 * Generates the "View" panel, sets up the table, JLabels, and JButtons
	 */
	public void generateViewPanel(){
		view = new JPanel();
		view.setLayout(new BoxLayout(view, BoxLayout.Y_AXIS));
		JPanel tablepanel = new JPanel(), buttonpanel = new JPanel();

		viewtable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		viewtable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		viewtable.getTableHeader().setReorderingAllowed(false);
		tablepanel.setSize(new Dimension(445, 300));

		tablepanel.setLayout(new BorderLayout());
		tablepanel.add(viewtable.getTableHeader(), BorderLayout.PAGE_START);
		tablepanel.add(viewtable, BorderLayout.CENTER);
		tablepanel.setSize(new Dimension(445, 300));

		view.add(tablepanel);

		JButton
		open = new JButton("Load"),
		saveas = new JButton("Save As"),
		save = new JButton("Save"),
		exit = new JButton("Exit"),
		newb = new JButton("New"),
		run = new JButton("Open");

		newb.setActionCommand("new");
		newb.setToolTipText("Creates a new Profile");
		newb.addActionListener(this);
		buttonpanel.add(newb);
		open.setActionCommand("open");
		open.setToolTipText("Choose a profile to open");
		open.addActionListener(this);
		buttonpanel.add(open);
		run.setActionCommand("run");
		run.setToolTipText("Opens the program or link associated to this account");
		run.addActionListener(this);
		buttonpanel.add(run);
		save.setActionCommand("save");
		save.setToolTipText("Saves the current accounts to a file and exits. If no file exists, one will be created.");
		save.addActionListener(this);
		buttonpanel.add(save);
		saveas.setActionCommand("saveas");
		saveas.setToolTipText("Saves the current accounts to a file. If no file exists, one will be created.");
		saveas.addActionListener(this);
		buttonpanel.add(saveas);
		exit.setActionCommand("exit");
		exit.setToolTipText("Exits the program without saving");
		exit.addActionListener(this);
		buttonpanel.add(exit);

		view.add(buttonpanel);
	}

	/**
	 * Generates the "Delete" panel, sets up the table, JLabels, and JButtons
	 */
	public void generateDeletePanel(){
		delete = new JPanel();
		delete.setLayout(new BoxLayout(delete, BoxLayout.Y_AXIS));
		JPanel tablepanel = new JPanel(), buttonpanel = new JPanel(), attribPanel = new JPanel(new BorderLayout());

		deltable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		deltable.getTableHeader().setReorderingAllowed(false);
		deltable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		tablepanel.setSize(new Dimension(445, 300));

		tablepanel.setLayout(new BorderLayout());
		tablepanel.add(deltable.getTableHeader(), BorderLayout.PAGE_START);
		tablepanel.add(deltable, BorderLayout.CENTER);
		tablepanel.setSize(new Dimension(445, 300));
		delete.add(tablepanel);

		JPanel topPanel = new JPanel(new BorderLayout()), userPanel = new JPanel(new BorderLayout()), passPanel = new JPanel(new BorderLayout());
		JPanel midpanel = new JPanel(new BorderLayout()), botpanel = new JPanel(), urlpan = new JPanel();
		JLabel userlab = new JLabel("Username:");
		userPanel.add(userlab, BorderLayout.WEST);
		userPanel.add(editnametf, BorderLayout.EAST);
		JLabel passLab = new JLabel("Password:");
		passPanel.add(passLab, BorderLayout.WEST);
		passPanel.add(editpasstf, BorderLayout.EAST);
		topPanel.add(userPanel, BorderLayout.WEST);
		topPanel.add(passPanel, BorderLayout.EAST);

		JLabel desclab = new JLabel("Description:");
		midpanel.add(desclab, BorderLayout.NORTH);
		midpanel.add(editdesc, BorderLayout.CENTER);

		botpanel.setLayout(new BoxLayout(botpanel, BoxLayout.Y_AXIS));
		urlpan.setLayout(new BoxLayout(urlpan, BoxLayout.X_AXIS));
		editcba.setActionCommand("editupdate2");
		editcba.addActionListener(this);
		botpanel.add(editcba);
		editchooserbutton = new JButton("Choose Program");
		editchooserbutton.setActionCommand("setprog");
		editchooserbutton.addActionListener(this);
		editchooserbutton.setEnabled(editcba.isSelected());
		editlinktf.setEditable(editcba.isSelected());
		JLabel lab = new JLabel("URL/Path:");
		urlpan.add(lab);
		urlpan.add(editlinktf);
		urlpan.add(editchooserbutton);
		botpanel.add(editcba);
		botpanel.add(urlpan);

		attribPanel.add(topPanel, BorderLayout.NORTH);
		attribPanel.add(midpanel, BorderLayout.CENTER);
		attribPanel.add(botpanel, BorderLayout.SOUTH);

		delete.add(attribPanel);

		JButton updatebutton = new JButton("Update"), deletebutton = new JButton("Delete"), exit = new JButton("Exit");
		updatebutton.setActionCommand("update");
		updatebutton.setToolTipText("Updates the selected account with the new attributes");
		updatebutton.addActionListener(this);
		buttonpanel.add(updatebutton);
		deletebutton.setActionCommand("delete");
		deletebutton.setToolTipText("Deletes the selected account from the file.");
		deletebutton.addActionListener(this);
		buttonpanel.add(deletebutton);
		exit.setActionCommand("exit");
		exit.setToolTipText("Exits the program without saving");
		exit.addActionListener(this);
		buttonpanel.add(exit);

		delete.add(buttonpanel);
	}

	/**
	 * Sets up the "Add" panel, generates the tabs for the different account types, then adds them to the add panel
	 */
	public void generateAddPanel(){
		add = new JTabbedPane();
		generateEmailPanel();
		generateGamePanel();
		generateSocialPanel();
		generateStorePanel();
		generateOtherPanel();

		add.add("EMail",email);
		add.add("Program",game);
		add.add("Social",socmed);
		add.add("Store",store);
		add.add("Other",other);
	}

	/**
	 * Generates the Add sub-panel for the "Other" account type
	 */
	private void generateOtherPanel() {
		other = new JPanel( new BorderLayout());
		JPanel toppan = new JPanel(new BorderLayout()), userpan = new JPanel(), passpan = new JPanel(), typepan = new JPanel(), descpan = new JPanel();
		JLabel accnm = new JLabel("Username:");
		userpan.add(accnm);
		userpan.add(accnmtf4);
		JLabel pass = new JLabel("Password:	");
		passpan.add(pass);
		passpan.add(passtf4);
		JLabel type = new JLabel("Type:	");
		typetf = new JTextField("",10);
		typepan.add(type);
		typepan.add(typetf);
		JLabel desc = new JLabel("Description:");
		descarea4.setWrapStyleWord(true);
		descpan.add(desc);
		descpan.add(descarea4);
		other.add(typepan, BorderLayout.NORTH);
		toppan.add(userpan, BorderLayout.WEST);
		toppan.add(passpan, BorderLayout.EAST);
		JPanel midpan = new JPanel(new BorderLayout());
		midpan.add(toppan, BorderLayout.NORTH);
		midpan.add(descpan, BorderLayout.SOUTH);
		other.add(midpan);
		JPanel buttonPanel = new JPanel();
		JButton add = new JButton("Add"), exit = new JButton("Exit");
		add.setToolTipText("Adds the account to the profile");
		add.setActionCommand("add");
		add.addActionListener(this);
		exit.setActionCommand("exit");
		exit.setToolTipText("Exits the program without saving");
		exit.addActionListener(this);
		buttonPanel.add(add);
		buttonPanel.add(exit);
		other.add(buttonPanel, BorderLayout.SOUTH);
	}

	/**
	 * Generates the Add sub-panel for the "Store" account type
	 */
	private void generateStorePanel() {
		store = new JPanel();
		JPanel userpan = new JPanel(), passpan = new JPanel();
		JLabel accnm = new JLabel("Username:");
		userpan.add(accnm);
		userpan.add(accnmtf3);
		JLabel pass = new JLabel("Password:");
		passpan.add(pass);
		passpan.add(passtf3);
		JLabel desc = new JLabel("Description:");
		descarea3.setWrapStyleWord(true);
		store.add(userpan);
		store.add(passpan);
		store.add(desc);
		store.add(descarea3);

		JPanel linkPanel = new JPanel();
		linkcb3 = new JCheckBox("URL: ");
		linkcb3.setActionCommand("url");
		linkcb3.addActionListener(this);
		linktf3 = new JTextField("http://www.neumont.edu/", 25);
		linktf3.setEditable(linkcb3.isSelected());
		linkPanel.add(linkcb3);
		linkPanel.add(linktf3);
		store.add(linkPanel);

		JPanel buttonPanel = new JPanel();
		JButton add = new JButton("Add"), exit = new JButton("Exit");
		add.setToolTipText("Adds the account to the profile");
		add.setActionCommand("add");
		add.addActionListener(this);
		exit.setActionCommand("exit");
		exit.setToolTipText("Exits the program without saving");
		exit.addActionListener(this);
		buttonPanel.add(add);
		buttonPanel.add(exit);
		store.add(buttonPanel);
	}

	/**
	 * Generates the Add sub-panel for the "SocMed" account type
	 */
	private void generateSocialPanel() {
		socmed = new JPanel();
		JPanel userpan = new JPanel(), passpan = new JPanel();
		JLabel accnm = new JLabel("Username:");
		userpan.add(accnm);
		userpan.add(accnmtf2);
		JLabel pass = new JLabel("Password:");
		passpan.add(pass);
		passpan.add(passtf2);
		JLabel desc = new JLabel("Description:");
		descarea2.setWrapStyleWord(true);
		socmed.add(userpan);
		socmed.add(passpan);
		socmed.add(desc);
		socmed.add(descarea2);

		JPanel linkPanel = new JPanel();
		linkcb2 = new JCheckBox("URL: ");
		linkcb2.setActionCommand("url");
		linkcb2.addActionListener(this);
		linktf2 = new JTextField("http://www.neumont.edu/", 25);
		linktf2.setEditable(linkcb2.isSelected());
		linkPanel.add(linkcb2);
		linkPanel.add(linktf2);
		socmed.add(linkPanel);

		JPanel buttonPanel = new JPanel();
		JButton add = new JButton("Add"), exit = new JButton("Exit");
		add.setToolTipText("Adds the account to the profile");
		add.setActionCommand("add");
		add.addActionListener(this);
		exit.setActionCommand("exit");
		exit.setToolTipText("Exits the program without saving");
		exit.addActionListener(this);
		buttonPanel.add(add);
		buttonPanel.add(exit);
		socmed.add(buttonPanel);
	}

	/**
	 * Generates the Add sub-panel for the "Game" account type
	 */
	private void generateGamePanel() {
		game = new JPanel();
		JPanel userpan = new JPanel(), passpan = new JPanel();
		JLabel accnm = new JLabel("Username:");
		userpan.add(accnm);
		userpan.add(accnmtf1);
		JLabel pass = new JLabel("Password:");
		passpan.add(pass);
		passpan.add(passtf1);
		JLabel desc = new JLabel("Description:");
		descarea1.setWrapStyleWord(true);
		game.add(userpan);
		game.add(passpan);
		game.add(desc);
		game.add(descarea1);

		JPanel linkPanel = new JPanel();
		linkcb1 = new JCheckBox("URL: ");
		linkcb1.setActionCommand("url");
		linkcb1.addActionListener(this);
		gameChooserButton = new JButton("Choose Program");
		gameChooserButton.setActionCommand("setprog");
		gameChooserButton.addActionListener(this);
		gameChooserButton.setEnabled(linkcb1.isSelected());
		linkPanel.add(linkcb1);
		linkPanel.add(gameChooserButton);
		game.add(linkPanel);

		JPanel buttonPanel = new JPanel();
		JButton add = new JButton("Add"), exit = new JButton("Exit");
		add.setToolTipText("Adds the account to the profile");
		add.setActionCommand("add");
		add.addActionListener(this);
		exit.setActionCommand("exit");
		exit.setToolTipText("Exits the program without saving");
		exit.addActionListener(this);
		buttonPanel.add(add);
		buttonPanel.add(exit);

		game.add(buttonPanel);
	}

	/**
	 * Generates the Add sub-panel for the "Email" account type
	 */
	private void generateEmailPanel() {
		email = new JPanel();
		JPanel userpan = new JPanel(), passpan = new JPanel();
		JLabel accnm = new JLabel("Username:");
		userpan.add(accnm);
		userpan.add(accnmtf0);
		JLabel pass = new JLabel("Password:");
		passpan.add(pass);
		passpan.add(passtf0);
		JLabel desc = new JLabel("Description:");
		email.add(userpan);
		email.add(passpan);
		email.add(desc);
		email.add(descarea0);

		JPanel linkPanel = new JPanel();
		linkcb0 = new JCheckBox("URL: ");
		linkcb0.setActionCommand("url");
		linkcb0.addActionListener(this);
		linktf0 = new JTextField("http://www.neumont.edu/", 25);
		linktf0.setEditable(linkcb0.isSelected());
		linkPanel.add(linkcb0);
		linkPanel.add(linktf0);
		email.add(linkPanel);

		JPanel buttonPanel = new JPanel();
		JButton add = new JButton("Add"), exit = new JButton("Exit");
		add.setToolTipText("Adds the account to the profile");
		add.setActionCommand("add");
		add.addActionListener(this);
		exit.setActionCommand("exit");
		exit.setToolTipText("Exits the program without saving");
		exit.addActionListener(this);
		buttonPanel.add(add);
		buttonPanel.add(exit);

		email.add(buttonPanel);

	}

	/**
	 * Updates the tables, makes the saved state false, and re-draws the frame.
	 */
	public void updateTables(){
		saved = false;
		Object[][] accs = new Object[profile.getAccountList().size()][4];
		int i = 0;
		for(Account a: profile.getAccountList()){
			accs[i][0]=a.getUsername();
			accs[i][1]=a.getPassword();
			accs[i][2]=a.getActType();
			accs[i][3]=a.getDesc();
			i++;
		}

		viewmodel = new DefaultTableModel(accs, columnnames);
		delmodel = new DefaultTableModel(accs, columnnames);
		viewtable = new JTable(viewmodel)
		{
			/**
			 * 
			 */
			private static final long serialVersionUID = 7469713748141341178L;

			public String getToolTipText( MouseEvent e )
			{
				String out = "";
				int row = rowAtPoint( e.getPoint() );
				int column = columnAtPoint( e.getPoint() );
				if(row != -1 && column != -1){
					Object value = getValueAt(row, column);
					out = value == null ? null : value.toString();
				}
				return out;
			}
		};
		deltable = new JTable(delmodel)
		{
			/**
			 * 
			 */
			private static final long serialVersionUID = 7691398939992393797L;

			public String getToolTipText( MouseEvent e )
			{
				String out = "";
				int row = rowAtPoint( e.getPoint() );
				int column = columnAtPoint( e.getPoint() );
				if(row != -1 && column != -1){
					Object value = getValueAt(row, column);
					out = value == null ? null : value.toString();
				}
				return out;
			}
		};
		mainpanel.removeAll();
		mainpanel.repaint();
		generatePanels();
		mainpanel.setSelectedIndex(0);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()){
		case "delete":
			try {
				profile.getAccountList().remove(deltable.getSelectedRow());
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(new JFrame(),"You must select an account before you can delete","No Account Selected",JOptionPane.WARNING_MESSAGE);
			}finally{
				updateTables();
				mainpanel.setSelectedIndex(1);
			}
			break;
		case "exit":
			if(JOptionPane.showConfirmDialog(new JFrame(), "Are you sure you want to exit?") == JOptionPane.YES_OPTION){
				if(viewtable.getRowCount() > 0 && !saved){
					int i = JOptionPane.showConfirmDialog(new JFrame(), "Do you want to save first?");
					if(i == JOptionPane.YES_OPTION){
						String filename = (String)JOptionPane.showInputDialog(new JFrame(),"Save as:","Save",JOptionPane.PLAIN_MESSAGE);
						if(filename !=null && filename != ""){
							profile.setPassword((String)JOptionPane.showInputDialog(new JFrame(),"Password:","Input Password",JOptionPane.PLAIN_MESSAGE));
							profile.saveAccountManager(PATHNAME + filename+".sav");
							saved = true;
							this.dispose();
						}
					}else if(i == JOptionPane.NO_OPTION){
						this.dispose();
					}
				}else{
					this.dispose();
				}
			}
			break;
		case "open":
			if(viewmodel.getRowCount() > 0 && !saved){
				if(JOptionPane.showConfirmDialog(new JFrame(), "Do you want to save first?") == JOptionPane.YES_OPTION){
					String filename = (String)JOptionPane.showInputDialog(new JFrame(),"Save as:","Save",JOptionPane.PLAIN_MESSAGE);
					if(filename !=null && filename != ""){
						profile.setPassword((String)JOptionPane.showInputDialog(new JFrame(),"Password:","Input Password",JOptionPane.PLAIN_MESSAGE));
						profile.saveAccountManager(PATHNAME + filename);
						saved = true;
					}
				}
			}
			JFileChooser chooser = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter("Save Files", "sav");
			chooser.setFileFilter(filter);
			int returnVal = chooser.showOpenDialog(getParent());
			if(returnVal == JFileChooser.APPROVE_OPTION) {
				//				System.out.println("You chose to open this file: " +chooser.getSelectedFile().getAbsolutePath());
				String password = (String)JOptionPane.showInputDialog(new JFrame(),"Password:","Input Password",JOptionPane.PLAIN_MESSAGE);
				profile = AccountManager.loadAccountManager(chooser.getSelectedFile().getAbsolutePath(), password);
				updateTables();
			}
			updateTables();
			mainpanel.setSelectedIndex(0);
			break;
		case "add":
			Account a;
			switch(add.getSelectedIndex()){
			case 0://email
				if(!(accnmtf0.getText() == null || passtf0.getText() == null || accnmtf0.getText().length() == 0 || passtf0.getText().length() == 0)){
					a = new Email(accnmtf0.getText(), passtf0.getText(), "email", descarea0.getText());

					if(linkcb0.isSelected()){
						a.setURL(linktf0.getText());
					}

					profile.getAccountList().add(a);
					updateTables();
					mainpanel.setSelectedIndex(0);
				}else{
					JOptionPane.showMessageDialog(new JFrame(),"Account Name and Password \nmust be filled out","Invalid Input",JOptionPane.ERROR_MESSAGE);
				}
				break;
			case 1://game
				if(!(accnmtf1.getText() == null || passtf1.getText() == null || accnmtf1.getText().length() == 0 || passtf1.getText().length() == 0)){
					a = new Game(accnmtf1.getText(), passtf1.getText(), "program", descarea1.getText());
					a.setURL(programurl);
					programurl = null;
					profile.getAccountList().add(a);
					updateTables();
					mainpanel.setSelectedIndex(0);
				}else{
					JOptionPane.showMessageDialog(new JFrame(),"Account Name and Password \nmust be filled out","Invalid Input",JOptionPane.ERROR_MESSAGE);
				}
				break;
			case 2://social
				if(!(accnmtf2.getText() == null || passtf2.getText() == null || accnmtf2.getText().length() == 0 || passtf2.getText().length() == 0)){
					a = new SocialMedia(accnmtf2.getText(), passtf2.getText(), "social", descarea2.getText());

					if(linkcb2.isSelected()){
						a.setURL(linktf2.getText());
					}

					profile.getAccountList().add(a);
					updateTables();
					mainpanel.setSelectedIndex(0);
				}else{
					JOptionPane.showMessageDialog(new JFrame(),"Account Name and Password \nmust be filled out","Invalid Input",JOptionPane.ERROR_MESSAGE);
				}
				break;
			case 3://store
				if(!(accnmtf3.getText() == null || passtf3.getText() == null || accnmtf3.getText().length() == 0 || passtf3.getText().length() == 0)){
					a = new Store(accnmtf3.getText(), passtf3.getText(), "store", descarea3.getText());

					if(linkcb3.isSelected()){
						a.setURL(linktf3.getText());
					}

					profile.getAccountList().add(a);
					updateTables();
					mainpanel.setSelectedIndex(0);
				}else{
					JOptionPane.showMessageDialog(new JFrame(),"Account Name and Password \nmust be filled out","Invalid Input",JOptionPane.ERROR_MESSAGE);
				}
				break;
			case 4://other
				if(!(accnmtf4.getText() == null || passtf4.getText() == null || accnmtf4.getText().length() == 0 || passtf4.getText().length() == 0 || typetf.getText() == null || typetf.getText().length() == 0)){
					a = new Email(accnmtf4.getText(), passtf4.getText(), typetf.getText(), descarea4.getText());
					profile.getAccountList().add(a);
					updateTables();
					mainpanel.setSelectedIndex(0);
				}else{
					JOptionPane.showMessageDialog(new JFrame(),"Account Name, Password, and Type \nmust be filled out","Invalid Input",JOptionPane.ERROR_MESSAGE);
				}
				break;
			}

			accnmtf0.setText("");
			accnmtf1.setText("");
			accnmtf2.setText("");
			accnmtf3.setText("");
			accnmtf4.setText("");
			passtf0.setText("");
			passtf1.setText("");
			passtf2.setText("");
			passtf3.setText("");
			passtf4.setText("");		
			typetf.setText("");
			descarea0.setText("");
			descarea1.setText("");
			descarea2.setText("");
			descarea3.setText("");
			descarea4.setText("");

			break;
		case "save":
			saved = true;
			profile.saveAccountManager();
			break;
		case "saveas":
			String filename = (String)JOptionPane.showInputDialog(new JFrame(),"Save as:","Save",JOptionPane.PLAIN_MESSAGE);
			if(filename !=null && filename != ""){
				profile.setPassword((String)JOptionPane.showInputDialog(new JFrame(),"Password:","Input Password",JOptionPane.PLAIN_MESSAGE));
				profile.saveAccountManager(PATHNAME + filename+".sav");
				saved = true;
			}
			break;
		case "new":
			profile = new AccountManager();
			updateTables();
			mainpanel.setSelectedIndex(2);
			break;

		case "url":
			if(e.getSource() == linkcb0){		//email
				linktf0.setEditable(linkcb0.isSelected());
			}else if(e.getSource() == linkcb1){	//game case, special stuff to enable file selection.
				gameChooserButton.setEnabled(linkcb1.isSelected());
			}else if(e.getSource() == linkcb2){	//SocialMedia
				linktf2.setEditable(linkcb2.isSelected());
			}else if(e.getSource() == linkcb3){	//Store
				linktf3.setEditable(linkcb3.isSelected());
			}
			break;
		case "run":
			try {
				if(viewtable.getSelectedRow() != -1){

					String url = ((Account)profile.getAccountList().get(viewtable.getSelectedRow())).getURL();

					if(url != null){

						if(((Account)profile.getAccountList().get(viewtable.getSelectedRow())).getActType() == "program"){
							try {
								Runtime.getRuntime().exec(url);
								System.out.println("Game profile");
							} catch (IOException e1) {

							}
						}else{
							openUrl(url);
						}
					}else{
						JOptionPane.showMessageDialog(new JFrame(),"No URL is associated with this account.","ERROR 404",JOptionPane.WARNING_MESSAGE);
					}
				}else{
					JOptionPane.showMessageDialog(new JFrame(),"You must select an account before you can run.","No Account Selected",JOptionPane.WARNING_MESSAGE);
				}

			} catch (Exception e1) {
				e1.printStackTrace();
			}finally{
				updateTables();
			}

			break;
		case "setprog":
			JFileChooser chooser2 = new JFileChooser();
			FileNameExtensionFilter filter2 = new FileNameExtensionFilter("Executable files", "exe");
			chooser2.setFileFilter(filter2);
			int returnVal2 = chooser2.showOpenDialog(getParent());
			if(returnVal2 == JFileChooser.APPROVE_OPTION) {
				programurl = chooser2.getSelectedFile().getAbsolutePath();
//				System.out.println(programurl);
			}
			editlinktf.setText(programurl);
			break;
		case "update":
			Account editing, ax;
			if(deltable.getSelectedRow() != -1){
				ax = profile.getAccountList().get(deltable.getSelectedRow());
				profile.getAccountList().remove(deltable.getSelectedRow());
				String type = ax.getActType();
				String outlink = null;
				String username, password;
				if(editcba.isSelected()){
					if(programurl == null){
						if(editlinktf.getText() != null){
							if(!editlinktf.getText().equals("")){
								System.out.println("Using a URL");
								outlink = editlinktf.getText();
							}
						}
					}else if(!programurl.equals("")){
						if(editlinktf.getText() == null){
							outlink = programurl;
							type = "program";
						}else if(editlinktf.getText().equals("")){
							outlink = programurl;
							type = "program";
						}else if(programurl.equals(editlinktf.getText())){
							System.out.println("Using a src");
							outlink = programurl;
							type = "program";
						}else{
							System.out.println("Using a URL");
							outlink = editlinktf.getText();
						}
					}
				}
				
				if(editnametf.getText() == null){
					username = ax.getUsername();
				}else if(editnametf.getText().equals("")){
					username = ax.getUsername();
				}else{
					username = editnametf.getText();
				}
				
				if(editpasstf.getText() == null){
					password = ax.getUsername();
				}else if(editpasstf.getText().equals("")){
					password = ax.getUsername();
				}else{
					password = editpasstf.getText();
				}
				
				editing = new Other(username, password, type , editdesc.getText());
				if(outlink != null){
					editing.setURL(outlink);
				}
				profile.getAccountList().add(editing);
				
				editnametf.setText("");
				editpasstf.setText("");
				editlinktf.setText("");
				programurl = null;
				editdesc.setText("");
				
				updateTables();
				mainpanel.setSelectedIndex(1);
			}
			break;
		case "editupdate2":
			editlinktf.setEditable(editcba.isSelected());
			editchooserbutton.setEnabled(editcba.isSelected());
			break;
		}
	}

	public void openUrl(String url) throws IOException, URISyntaxException {
		if(!url.contains("http://www.")){
			if(!url.contains("www.")){
				url = "http://www."+url;
			}else if(!url.contains("http://")){
				url = "http://"+url;
			}
		}
		
		if(java.awt.Desktop.isDesktopSupported() ) {
			java.awt.Desktop desktop = java.awt.Desktop.getDesktop();

			if(desktop.isSupported(java.awt.Desktop.Action.BROWSE) ) {
				java.net.URI uri = new java.net.URI(url);
				desktop.browse(uri);
			}
		}
	}
}