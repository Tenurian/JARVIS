package conversioncalc;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class weightConv extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4759856220835706925L;
	private static final double LB_TO_KG = 0.453592;
	private static final double KG_TO_LB = 2.20462;
	JComboBox impBox,metBox;
	JButton impButton,metButton;
	static JTextField impIn,metIn;
	
	@SuppressWarnings({ "unchecked", "unused", "rawtypes" })
	public weightConv(){
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
        String[] impOpt = {"Ounces (oz)","Pounds (lb)","Tons (tn)"};
        String[] metOpt = {"Milligrams (mg)","Grams (g)","Kilograms (kg)","Metric Tons (MT)"};
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
 //       add(DN_MD);
        add(DN_RT);
	}
	private class ButtonListener implements ActionListener{
        	public void actionPerformed(ActionEvent e){
        		if(e.getActionCommand().equals("imp_to_met")){
        			String n = impBox.getSelectedIndex()+""+metBox.getSelectedIndex();
        			double in = Double.parseDouble(impIn.getText());
        			double out;
        			switch(n){ //I got 99 problems but a switch ain't one, if you're having case problems I feel sorry for you son
        			case "00"://oz to mg
        				out = ((in*LB_TO_KG)/16.0)*1000000.0;
        				metIn.setText(out+"");
        				break;
        				
        			case "01"://oz to g
        				out = ((in*LB_TO_KG)/16)*1000;
        				metIn.setText(out+"");
        				break;
        				
        			case "02"://oz to kg
        				out = ((in*LB_TO_KG)/16);
        				metIn.setText(out+"");
        				break;
        				
        			case "03"://oz to MT
        				out = ((in*LB_TO_KG)/1000)/16;
        				metIn.setText(out+"");
        				break;
        				
        			case "10"://lb to mg
        				out = ((in*LB_TO_KG)*1000000);
        				metIn.setText(out+"");
        				break;
        				
        			case "11"://lb to g
        				out = ((in*LB_TO_KG)*1000);
        				metIn.setText(out+"");
        				break;
        				
        			case "12"://lb to kg
        				out = (in*LB_TO_KG);
        				metIn.setText(out+"");
        				break;
        				
        			case "13"://lb to MT
        				out = ((in*LB_TO_KG)/1000);
        				metIn.setText(out+"");
        				break;
        				
        			case "20"://tn to mg
        				out = ((in*LB_TO_KG)/2240)*1000000;
        				metIn.setText(out+"");
        				break;
        				
        			case "21"://tn to g
        				out = ((in*LB_TO_KG)/2240)*1000;
        				metIn.setText(out+"");
        				break;
        				
        			case "22"://tn to kg
        				out = ((in*LB_TO_KG)/2240);
        				metIn.setText(out+"");
        				break;
        				
        			case "23"://tn to MT
        				out = ((in*LB_TO_KG)/2240)/1000;
        				metIn.setText(out+"");
        				break;
        				
        			default:
        				break;
        			}
        		}else if(e.getActionCommand().equals("met_to_imp")){
        			String n = impBox.getSelectedIndex()+""+metBox.getSelectedIndex();
        			double in = Double.parseDouble(metIn.getText());
        			double out;
        			System.out.println(in);
        			switch(n){ //I got 99 problems but a switch ain't one, if you're having case problems I feel sorry for you son
        			case "00"://oz from mg
        				out = ((in*KG_TO_LB)/1000000)*16;
        				impIn.setText(out+"");
        				break;

        			case "01"://oz form g
        				out = ((in*KG_TO_LB)/1000)*16;
        				impIn.setText(out+"");
        				break;

        			case "02"://oz from kg
        				out = ((in*KG_TO_LB))*16;
        				impIn.setText(out+"");
        				break;

        			case "03"://oz from MT
        				out = ((in*KG_TO_LB)*1000)*16;
        				impIn.setText(out+"");
        				break;

        			case "10"://lb from mg
        				out = ((in*KG_TO_LB)/1000000);
        				impIn.setText(out+"");
        				break;

        			case "11"://lb from g
        				out = ((in*KG_TO_LB)/1000);
        				impIn.setText(out+"");
        				break;

        			case "12"://lb from kg
        				out = ((in*KG_TO_LB));
        				impIn.setText(out+"");
        				break;

        			case "13"://lb from MT
        				out = ((in*KG_TO_LB)*1000);
        				impIn.setText(out+"");
        				break;

        			case "20"://tn from mg
        				out = ((in*KG_TO_LB)/1000000)/2240;
        				impIn.setText(out+"");
        				break;

        			case "21"://tn form g
        				out = ((in*KG_TO_LB)/1000)/2240;
        				impIn.setText(out+"");
        				break;

        			case "22"://tn from kg
        				out = ((in*KG_TO_LB))/2240;
        				impIn.setText(out+"");
        				break;

        			case "23"://tn from MT
        				out = ((in*KG_TO_LB)*1000)/2240;
        				impIn.setText(out+"");
        				break;

        			default:
        				break;
        			}
        		}
        	}
        }
}
