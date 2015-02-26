package contactbook;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class ContactBook extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4464928524539466920L;
	
	JTextField anametf,acellnotf,aworknotf,apersemtf,aworkemtf,aaddresstf,enametf,ecellnotf,eworknotf,epersemtf,eworkemtf,eaddresstf;
	
	
	private boolean saved;
	JPanel view, edit, add;
	TableModel viewmodel, editmodel;
	JTable viewtable, edittable;
	JTabbedPane mainpane;
	ContactBookProfile profile = new ContactBookProfile();
	private String[] columnnames = {"Name","Cell #","Work #","Personal eMail","Work eMail","Address"};
	public ContactBook(){
		super("Contact Book");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setPreferredSize(new Dimension(500,500));
		
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

	private void generateViewPanel() {view = new JPanel();
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
	newb = new JButton("New");

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
		edit.add(tablepanel);
		edit.add(attribpanel);
		edit.add(buttonpanel);
	}

	private void generateAddPanel() {
		add = new JPanel();
//		add.setLayout(new BoxLayout(add, BoxLayout.Y_AXIS));
		JPanel  
		buttonpanel = new JPanel(), 
		namepanel = new JPanel(), 
		cellpanel = new JPanel(), 
		wknmpanel = new JPanel(), 
		persemailpanel = new JPanel(), 
		wkemailpanel = new JPanel(), 
		addresspanel = new JPanel();
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
		add.add(namepanel);
		add.add(cellpanel);
		add.add(wknmpanel);
		add.add(persemailpanel);
		add.add(wkemailpanel);
		add.add(addresspanel);
		add.add(buttonpanel);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

	}

}
