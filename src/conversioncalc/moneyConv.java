package conversioncalc;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class moneyConv extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5002849766201615446L;
	private static final double GBP_TO_USD = 1.62;
	private static final double USD_TO_GBP = 0.62;//change these to currency constants
	@SuppressWarnings("rawtypes")
	JComboBox impBox,metBox;
	JButton impButton,metButton;
	static JTextField impIn,metIn;
	
	@SuppressWarnings({ "unchecked", "rawtypes", "unused" })
	public moneyConv(){
		JPanel UP_RT = new JPanel();//upper right --
        JPanel UP_LT = new JPanel();//upper left  --
        JPanel DN_RT = new JPanel();//lower right --
        JPanel DN_LT = new JPanel();//lower left  --
        JPanel UP_MD = new JPanel();
        JPanel DN_MD = new JPanel();
        impButton = new JButton("Convert to Metric");
        metButton = new JButton("Convert to Imperial");
        impIn = new JTextField();
        metIn = new JTextField();
        String[] impOpt = {"Dollars (USD)"};
        String[] metOpt = {"Pound Sterling (GBP)"};
        impBox = new JComboBox(impOpt);
        metBox = new JComboBox(metOpt);
        
        
        DN_LT.setLayout(new BoxLayout(DN_LT,BoxLayout.Y_AXIS));
        DN_RT.setLayout(new BoxLayout(DN_RT,BoxLayout.Y_AXIS));
        UP_LT.add(impBox);
        UP_RT.add(metBox);
        DN_LT.add(impIn);
        DN_LT.add(impButton);
        DN_RT.add(metIn);
        DN_RT.add(metButton);
        impButton.setActionCommand("imp_to_met");
        impButton.addActionListener(new ButtonListener());
        metButton.setActionCommand("met_to_imp");
        metButton.addActionListener(new ButtonListener());
        
        setLayout(new GridLayout(2,2));
        
        add(UP_LT);
 //       add(UP_MD);
        add(UP_RT);
        add(DN_LT);
 //       add(DN_MD);
        add(DN_RT);
	}
	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			if(e.getActionCommand().equals("imp_to_met")){
				metIn.setText(""+Double.parseDouble(impIn.getText())*USD_TO_GBP);
			}else if(e.getActionCommand().equals("met_to_imp")){
				impIn.setText(""+Double.parseDouble(metIn.getText())*GBP_TO_USD);
			}
		}
	}
}
