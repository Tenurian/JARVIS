package contactbook;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;

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
	
	public ContactBook(){
		super("Contact Book");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setPreferredSize(new Dimension(500,500));
		
		
		this.pack();
        this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {

	}

}
