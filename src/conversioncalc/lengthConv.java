package conversioncalc;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class lengthConv extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 9037085090081373159L;
	private static final double FT_TO_M = 0.3048;
	private static final double M_TO_FT = 3.28084;//change these to length constants
	@SuppressWarnings("rawtypes")
	JComboBox impBox,metBox;
	JButton impButton,metButton;
	static JTextField impIn,metIn;
	
	@SuppressWarnings({ "unchecked", "rawtypes", "unused" })
	public lengthConv(){
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
        String[] impOpt = {"Inches (in)","Feet (ft)","Yards (yd)","Miles(Mi)"};
        String[] metOpt = {"Millimeters (mm)","Centimeter (cm)","Meters (m)","Kilometers (Km)"};
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
//        add(UP_MD);
        add(UP_RT);
        add(DN_LT);
//        add(DN_MD);
        add(DN_RT);
	}
	private class ButtonListener implements ActionListener{
		@SuppressWarnings("unused")
		public void actionPerformed(ActionEvent e){
			String out = "";
			if(e.getActionCommand().equals("imp_to_met")){
    			String n = impBox.getSelectedIndex()+""+metBox.getSelectedIndex();
    			switch(n){
    			case"00"://in>mm
    				metIn.setText(((Double.parseDouble(impIn.getText())/12)*FT_TO_M*1000)+"");
    				break;
    			case"01"://in>cm
    				metIn.setText(((Double.parseDouble(impIn.getText())/12)*FT_TO_M*100)+"");
    				break;
    			case"02"://in>m
    				metIn.setText(((Double.parseDouble(impIn.getText())/12)*FT_TO_M)+"");
    				break;
    			case"03"://in>km
    				metIn.setText(((Double.parseDouble(impIn.getText()))*FT_TO_M/1000)+"");
    				break;
    			case"10"://ft>mm
    				metIn.setText(((Double.parseDouble(impIn.getText()))*FT_TO_M*1000)+"");
    				break;
    			case"11"://ft>cm
    				metIn.setText(((Double.parseDouble(impIn.getText()))*FT_TO_M*100)+"");
    				break;
    			case"12"://ft>m
    				metIn.setText(((Double.parseDouble(impIn.getText()))*FT_TO_M)+"");
    				break;
    			case"13"://ft>km
    				metIn.setText(((Double.parseDouble(impIn.getText()))*FT_TO_M/1000)+"");
    				break;
    			case"20"://yd>mm
    				metIn.setText(((Double.parseDouble(impIn.getText())*3)*FT_TO_M*1000)+"");
    				break;
    			case"21"://yd>cm
    				metIn.setText(((Double.parseDouble(impIn.getText())*3)*FT_TO_M*100)+"");
    				break;
    			case"22"://yd>m
    				metIn.setText(((Double.parseDouble(impIn.getText())*3)*FT_TO_M)+"");
    				break;
    			case"23"://yd>km
    				metIn.setText(((Double.parseDouble(impIn.getText())*3)*FT_TO_M/1000)+"");
    				break;
    			case"30"://mi>mm
    				metIn.setText(((Double.parseDouble(impIn.getText())*5280)*FT_TO_M*1000)+"");
    				break;
    			case"31"://mi>cm
    				metIn.setText(((Double.parseDouble(impIn.getText())*5280)*FT_TO_M*100)+"");
    				break;
    			case"32"://mi>m
    				metIn.setText(((Double.parseDouble(impIn.getText())*5280)*FT_TO_M)+"");
    				break;
    			case"33"://mi>km
    				metIn.setText(((Double.parseDouble(impIn.getText())*5280)*FT_TO_M/1000)+"");
    				break;
    			default:
    				break;
    			}
				//add the conversion stuff here
			}else if(e.getActionCommand().equals("met_to_imp")){
    			String n = impBox.getSelectedIndex()+""+metBox.getSelectedIndex();
    			switch(n){
    			case"00"://in<mm
    				impIn.setText(((Double.parseDouble(metIn.getText())/1000)*M_TO_FT*12)+"");
    				break;
    			case"01"://in<cm
    				impIn.setText(((Double.parseDouble(metIn.getText())/100)*M_TO_FT*12)+"");
    				break;
    			case"02"://in<m
    				impIn.setText(((Double.parseDouble(metIn.getText()))*M_TO_FT*12)+"");
    				break;
    			case"03"://in<km
    				impIn.setText(((Double.parseDouble(metIn.getText())*1000)*M_TO_FT*12)+"");
    				break;
    			case"10"://ft<mm
    				impIn.setText(((Double.parseDouble(metIn.getText())/1000)*M_TO_FT)+"");
    				break;
    			case"11"://ft<cm
    				impIn.setText(((Double.parseDouble(metIn.getText())/100)*M_TO_FT)+"");
    				break;
    			case"12"://ft<m
    				impIn.setText(((Double.parseDouble(metIn.getText()))*M_TO_FT)+"");
    				break;
    			case"13"://ft<km
    				impIn.setText(((Double.parseDouble(metIn.getText())*1000)*M_TO_FT)+"");
    				break;
    			case"20"://yd<mm
    				impIn.setText(((Double.parseDouble(metIn.getText())/1000)*M_TO_FT/3)+"");
    				break;
    			case"21"://yd<cm
    				impIn.setText(((Double.parseDouble(metIn.getText())/100)*M_TO_FT/3)+"");
    				break;
    			case"22"://yd<m
    				impIn.setText(((Double.parseDouble(metIn.getText()))*M_TO_FT/3)+"");
    				break;
    			case"23"://yd<km
    				impIn.setText(((Double.parseDouble(metIn.getText())*1000)*M_TO_FT)/3+"");
    				break;
    			case"30"://mi<mm
    				impIn.setText(((Double.parseDouble(metIn.getText())/1000)*M_TO_FT/5280)+"");
    				break;
    			case"31"://mi<cm
    				impIn.setText(((Double.parseDouble(metIn.getText())/100)*M_TO_FT/5280)+"");
    				break;
    			case"32"://mi<m
    				impIn.setText(((Double.parseDouble(metIn.getText()))*M_TO_FT/5280)+"");
    				break;
    			case"33"://mi<km
    				impIn.setText(((Double.parseDouble(metIn.getText())*1000)*M_TO_FT/5280)+"");
    				break;
    			default:
    				break;
    			}
				//add conversion stuff here too
			}
		}
	}
}
