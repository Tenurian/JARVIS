package contactbook;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import accountmanager.accounts.Account;

public class ContactBook extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4464928524539466920L;

	private boolean saved;
	JPanel view, edit, add;
	TableModel viewmodel, editmodel;
	JTable viewtable, edittable;
	JTabbedPane mainpane;
	ContactBookProfile profile;
	
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
	public void actionPerformed(ActionEvent arg0) {

	}

}
