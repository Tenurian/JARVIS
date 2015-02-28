package contactbook;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class ContactBook extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4464928524539466920L;

	JTextField 
	anametf,
	acellnotf,
	aworknotf,
	apersemtf,
	aworkemtf,
	aaddresstf,
	enametf,
	ecellnotf,
	eworknotf,
	epersemtf,
	eworkemtf,
	eaddresstf;

	private static final String PATHNAME = ((System.getProperty("user.home") + "/Documents/JARVIS/").replace("\\", "/"));
	private boolean saved, loaded;
	JPanel view, edit, add;
	TableModel viewmodel, editmodel, addmodel;
	JTable viewtable, edittable, addtable;
	JTabbedPane mainpane;
	ContactBookProfile profile = new ContactBookProfile();
	private String[] columnnames = {"Name","Cell #","Work #","Personal eMail","Work eMail","Address"};
	public ContactBook(){
		super("Contact Book");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setPreferredSize(new Dimension(600,500));

		loaded = false;

		mainpane = new JTabbedPane();
		updateTables();
		mainpane.setSelectedIndex(0);



		this.add(mainpane);

		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	private void updateTables() {
		saved = false;
		Object[][] accs = new Object[profile.getContactList().size()][6];
		int i = 0;
		for(Contact a: profile.getContactList()){
			accs[i][0]=a.getName();
			accs[i][1]=a.getCellnumber();
			accs[i][2]=a.getWorknumber();
			accs[i][3]=a.getPersonalemail();
			accs[i][4]=a.getWorkemail();
			accs[i][5]=a.getAddress();
			i++;
		}

		viewmodel = new DefaultTableModel(accs, columnnames);
		editmodel = new DefaultTableModel(accs, columnnames);
		addmodel = new DefaultTableModel(accs, columnnames);
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
		edittable = new JTable(editmodel)
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
		
		edittable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
		    @Override
		    public void valueChanged(ListSelectionEvent event) {
		        if (edittable.getSelectedRow() > -1) {
					Contact in = profile.getContactList().get(edittable.getSelectedRow());
					enametf.setText(in.getName());
					ecellnotf.setText(in.getCellnumber());
					eworknotf.setText(in.getWorknumber());
					epersemtf.setText(in.getPersonalemail());
					eworkemtf.setText(in.getWorkemail());
					eaddresstf.setText(in.getAddress());
		        }
		    }
		});
		
		addtable = new JTable(addmodel)
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
		
		mainpane.removeAll();
		mainpane.repaint();
		generatePanels();
		mainpane.setSelectedIndex(0);

	}

	private void generatePanels() {
		generateViewPanel();
		generateEditPanel();
		generateAddPanel();

		mainpane.add("View", view);
		mainpane.add("Edit",edit);
		mainpane.add("Add", add);

	}

	private void generateViewPanel() {
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
		saveas = new JButton("Save As"),
		save = new JButton("Save"),
		exit = new JButton("Exit"),
		newb = new JButton("New"),
		load = new JButton("Load");

		load.setActionCommand("load");
		load.setToolTipText("Loads an existing contact book");
		load.addActionListener(this);
		buttonpanel.add(load);
		newb.setActionCommand("new");
		newb.setToolTipText("Creates a new Profile");
		newb.addActionListener(this);
		buttonpanel.add(newb);
		save.setActionCommand("save");
		save.setToolTipText("Saves the current contacts to the loaded file");
		save.addActionListener(this);
		buttonpanel.add(save);
		saveas.setActionCommand("saveas");
		saveas.setToolTipText("Saves the current contacts to a specified file.");
		saveas.addActionListener(this);
		buttonpanel.add(saveas);
		exit.setActionCommand("exit");
		exit.setToolTipText("Exits the program without saving");
		exit.addActionListener(this);
		buttonpanel.add(exit);

		view.add(buttonpanel);

	}

	private void generateEditPanel() {
		edit = new JPanel();
		edit.setLayout(new BoxLayout(edit, BoxLayout.Y_AXIS));
		JPanel 
		tablepanel = new JPanel(),
		attribpanel = new JPanel(),
		buttonpanel = new JPanel(), 
		namepanel = new JPanel(), 
		cellpanel = new JPanel(), 
		wknmpanel = new JPanel(), 
		persemailpanel = new JPanel(), 
		wkemailpanel = new JPanel(), 
		addresspanel = new JPanel();


		edittable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		edittable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		edittable.getTableHeader().setReorderingAllowed(false);
		tablepanel.setSize(new Dimension(445, 300));

		tablepanel.setLayout(new BorderLayout());
		tablepanel.add(edittable.getTableHeader(), BorderLayout.PAGE_START);
		tablepanel.add(edittable, BorderLayout.CENTER);


		attribpanel.setLayout(new BoxLayout(attribpanel, BoxLayout.Y_AXIS));
		buttonpanel.setLayout(new BoxLayout(buttonpanel, BoxLayout.X_AXIS));
		namepanel.setLayout(new BoxLayout(namepanel, BoxLayout.X_AXIS));
		cellpanel.setLayout(new BoxLayout(cellpanel, BoxLayout.X_AXIS));
		wknmpanel.setLayout(new BoxLayout(wknmpanel, BoxLayout.X_AXIS));
		persemailpanel.setLayout(new BoxLayout(persemailpanel, BoxLayout.X_AXIS));
		wkemailpanel.setLayout(new BoxLayout(wkemailpanel, BoxLayout.X_AXIS));
		addresspanel.setLayout(new BoxLayout(addresspanel, BoxLayout.X_AXIS));
		JLabel
		namelabel = new JLabel("Name: "),
		celllabel = new JLabel("Cell #:"),
		worknumberlabel = new JLabel("Work #:"),
		personalemaillabel = new JLabel("Personal eMail:"),
		workemaillabel = new JLabel("Work eMail:"),
		addresslabel = new JLabel("Address:");
		enametf = new JTextField("",15);
		ecellnotf = new JTextField("",15);
		eworknotf = new JTextField("",15);
		epersemtf = new JTextField("",15);
		eworkemtf = new JTextField("",15);
		eaddresstf = new JTextField("",15);
		namepanel.add(namelabel);
		namepanel.add(enametf);
		cellpanel.add(celllabel);
		cellpanel.add(ecellnotf);
		wknmpanel.add(worknumberlabel);
		wknmpanel.add(eworknotf);
		persemailpanel.add(personalemaillabel);
		persemailpanel.add(epersemtf);
		wkemailpanel.add(workemaillabel);
		wkemailpanel.add(eworkemtf);
		addresspanel.add(addresslabel);
		addresspanel.add(eaddresstf);
		attribpanel.add(namepanel);
		attribpanel.add(cellpanel);
		attribpanel.add(wknmpanel);
		attribpanel.add(persemailpanel);
		attribpanel.add(wkemailpanel);
		attribpanel.add(addresspanel);

		JButton edit2 = new JButton("Update");
		edit2.setActionCommand("edit");
		edit2.setToolTipText("Updates the selected contact with the filled out fields");
		edit2.addActionListener(this);
		buttonpanel.add(edit2);
		JButton delete = new JButton("Delete");
		delete.setActionCommand("delete");
		delete.setToolTipText("Deletes the selected contact");
		delete.addActionListener(this);
		buttonpanel.add(delete);
		JButton exit = new JButton("Exit");
		exit.setActionCommand("exit");
		exit.setToolTipText("Exits the program");
		exit.addActionListener(this);
		buttonpanel.add(exit);

		edit.add(tablepanel);
		edit.add(attribpanel);
		edit.add(buttonpanel);
	}

	private void generateAddPanel() {
		add = new JPanel();
		add.setLayout(new BoxLayout(add, BoxLayout.Y_AXIS));
		JPanel 
		tablepanel = new JPanel(),
		attribpanel = new JPanel(),
		buttonpanel = new JPanel(), 
		namepanel = new JPanel(), 
		cellpanel = new JPanel(), 
		wknmpanel = new JPanel(), 
		persemailpanel = new JPanel(), 
		wkemailpanel = new JPanel(), 
		addresspanel = new JPanel();


		addtable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		addtable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		addtable.getTableHeader().setReorderingAllowed(false);
		tablepanel.setSize(new Dimension(445, 300));

		tablepanel.setLayout(new BorderLayout());
		tablepanel.add(addtable.getTableHeader(), BorderLayout.PAGE_START);
		tablepanel.add(addtable, BorderLayout.CENTER);


		attribpanel.setLayout(new BoxLayout(attribpanel, BoxLayout.Y_AXIS));
		buttonpanel.setLayout(new BoxLayout(buttonpanel, BoxLayout.X_AXIS));
		namepanel.setLayout(new BoxLayout(namepanel, BoxLayout.X_AXIS));
		cellpanel.setLayout(new BoxLayout(cellpanel, BoxLayout.X_AXIS));
		wknmpanel.setLayout(new BoxLayout(wknmpanel, BoxLayout.X_AXIS));
		persemailpanel.setLayout(new BoxLayout(persemailpanel, BoxLayout.X_AXIS));
		wkemailpanel.setLayout(new BoxLayout(wkemailpanel, BoxLayout.X_AXIS));
		addresspanel.setLayout(new BoxLayout(addresspanel, BoxLayout.X_AXIS));
		JLabel
		namelabel = new JLabel("Name: "),
		celllabel = new JLabel("Cell #:"),
		worknumberlabel = new JLabel("Work #:"),
		personalemaillabel = new JLabel("Personal eMail:"),
		workemaillabel = new JLabel("Work eMail:"),
		addresslabel = new JLabel("Address:");
		anametf = new JTextField("",15);
		acellnotf = new JTextField("",15);
		aworknotf = new JTextField("",15);
		apersemtf = new JTextField("",15);
		aworkemtf = new JTextField("",15);
		aaddresstf = new JTextField("",15);
		namepanel.add(namelabel);
		namepanel.add(anametf);
		cellpanel.add(celllabel);
		cellpanel.add(acellnotf);
		wknmpanel.add(worknumberlabel);
		wknmpanel.add(aworknotf);
		persemailpanel.add(personalemaillabel);
		persemailpanel.add(apersemtf);
		wkemailpanel.add(workemaillabel);
		wkemailpanel.add(aworkemtf);
		addresspanel.add(addresslabel);
		addresspanel.add(aaddresstf);
		attribpanel.add(namepanel);
		attribpanel.add(cellpanel);
		attribpanel.add(wknmpanel);
		attribpanel.add(persemailpanel);
		attribpanel.add(wkemailpanel);
		attribpanel.add(addresspanel);

		JButton add2 = new JButton("Add");
		add2.setActionCommand("add");
		add2.setToolTipText("Updates the selected contact with the filled out fields");
		add2.addActionListener(this);
		buttonpanel.add(add2);
		JButton exit = new JButton("Exit");
		exit.setActionCommand("exit");
		exit.setToolTipText("Exits the program");
		exit.addActionListener(this);
		buttonpanel.add(exit);

		add.add(tablepanel);
		add.add(attribpanel);
		add.add(buttonpanel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()){
		case "add":
			Contact c = new Contact(anametf.getText());
			String cellno = acellnotf.getText(), workno = aworknotf.getText();
			cellno = cellno.replaceAll("()- ", "");
			workno = workno.replaceAll("()- ", "");
			
			if(!cellno.equals("") && cellno.length()==10){
				cellno = "("+cellno.substring(0, 3)+") "+cellno.substring(3, 6)+"-"+cellno.substring(6, 9);
			}else{
				cellno = "INVALID_NUMBER";
			}
			if(!workno.equals("") && workno.length()==10){
				workno = "("+workno.substring(0, 3)+") "+workno.substring(3, 6)+"-"+workno.substring(6, 9);
			}else{
				workno = "INVALID_NUMBER";
			}

			c.setCellnumber(cellno);
			c.setWorknumber(workno);
			c.setPersonalemail(apersemtf.getText());
			c.setWorkemail(aworkemtf.getText());
			c.setAddress(aaddresstf.getText());
			profile.getContactList().add(c);
			updateTables();
			mainpane.setSelectedIndex(2);
			break;
		case "edit":
			Contact editing, ax;
			if(edittable.getSelectedRow() != -1){
				saved = false;
				ax = profile.getContactList().get(edittable.getSelectedRow());
				profile.getContactList().remove(edittable.getSelectedRow());
				String name = "", celln = "", workn = "", pemail = "", wemail = "", address = "";
				if(enametf.getText() != null){
					if(!enametf.getText().equals("")){
						name = enametf.getText();
					}else{
						name = ax.getName();
					}
				}
				if(ecellnotf.getText() != null){
					if(!ecellnotf.getText().equals("")){
						celln = ecellnotf.getText();
						celln = celln.replaceAll("()- ", "");
						celln = "("+celln.substring(0, 3)+") "+celln.substring(3, 6)+"-"+celln.substring(6, 9);
					}else{
						celln = ax.getCellnumber();
					}
				}
				if(eworknotf.getText() != null){
					if(!eworknotf.getText().equals("")){
						workn = eworknotf.getText();
						workn = workn.replaceAll("()- ", "");
						workn = "("+celln.substring(0, 3)+") "+workn.substring(3, 6)+"-"+workn.substring(6, 9);
					}else{
						workn = ax.getWorknumber();
					}
				}
				if(epersemtf.getText() != null){
					if(!epersemtf.getText().equals("")){
						pemail = epersemtf.getText();
					}else{
						pemail = ax.getPersonalemail();
					}
				}
				if(eworkemtf.getText() != null){
					if(!eworkemtf.getText().equals("")){
						wemail = eworkemtf.getText();
					}else{
						wemail = ax.getWorkemail();
					}
				}
				if(eaddresstf.getText() != null){
					if(!eaddresstf.getText().equals("")){
						address = eaddresstf.getText();
					}else{
						address = ax.getAddress();
					}
				}
				editing = new Contact(name, celln, workn, pemail, wemail, address);
				enametf.setText("");
				ecellnotf.setText("");
				eworknotf.setText("");
				epersemtf.setText("");
				eworkemtf.setText("");
				eaddresstf.setText("");
				profile.getContactList().add(editing);
				updateTables();
				mainpane.setSelectedIndex(1);
			}
			break;
		case "delete":
			try {
				profile.getContactList().remove(edittable.getSelectedRow());
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(new JFrame(),"You must select an account before you can delete","No Account Selected",JOptionPane.WARNING_MESSAGE);
			}finally{
				updateTables();
				mainpane.setSelectedIndex(1);
			}
			break;
		case "load":
			if(viewmodel.getRowCount() > 0 && !saved){
				if(JOptionPane.showConfirmDialog(new JFrame(), "Do you want to save first?") == JOptionPane.YES_OPTION){
					String filename = (String)JOptionPane.showInputDialog(new JFrame(),"Save as:","Save",JOptionPane.PLAIN_MESSAGE);
					if(filename !=null && filename != ""){
						profile.setPassword((String)JOptionPane.showInputDialog(new JFrame(),"Password:","Input Password",JOptionPane.PLAIN_MESSAGE));
						profile.saveContactProfile(PATHNAME + filename);
						saved = true;
					}
				}
			}
			JFileChooser chooser = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter("Contact Books", "cbk");
			chooser.setFileFilter(filter);
			int returnVal = chooser.showOpenDialog(getParent());
			if(returnVal == JFileChooser.APPROVE_OPTION) {
				String password = (String)JOptionPane.showInputDialog(new JFrame(),"Password:","Input Password",JOptionPane.PLAIN_MESSAGE);
				profile = ContactBookProfile.loadContactProfile(chooser.getSelectedFile().getAbsolutePath(), password);
				loaded = true;
				updateTables();
				saved = true;
			}
			updateTables();
			saved = true;
			mainpane.setSelectedIndex(0);
			break;
		case "quicksave":
			profile.saveContactProfile();
			saved = true;
			loaded = true;
			break;
		case "saveas":
			String filename = (String)JOptionPane.showInputDialog(new JFrame(),"Save as:","Save",JOptionPane.PLAIN_MESSAGE);
			if(filename !=null && filename != ""){
				profile.setPassword((String)JOptionPane.showInputDialog(new JFrame(),"Password:","Input Password",JOptionPane.PLAIN_MESSAGE));
				profile.saveContactProfile(PATHNAME + filename+".cbk");
				saved = true;
			}
			break;
		case "new":
			loaded = false;
			profile = new ContactBookProfile();
			updateTables();
			mainpane.setSelectedIndex(2);
			break;
		case "exit":
			if(JOptionPane.showConfirmDialog(new JFrame(), "Are you sure you want to exit?") == JOptionPane.YES_OPTION){
				if(viewtable.getRowCount() > 0 && !saved){
					int i = JOptionPane.showConfirmDialog(new JFrame(), "Do you want to save first?");
					if(i == JOptionPane.YES_OPTION){
						if(!loaded){
							String filename1 = (String)JOptionPane.showInputDialog(new JFrame(),"Save as:","Save",JOptionPane.PLAIN_MESSAGE);
							if(filename1 !=null && filename1 != ""){
								profile.setPassword((String)JOptionPane.showInputDialog(new JFrame(),"Password:","Input Password",JOptionPane.PLAIN_MESSAGE));
								profile.saveContactProfile(PATHNAME + filename1+".cbk");
								saved = true;
								this.dispose();
							}
						}else{
							profile.saveContactProfile();
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
		}
	}

}
