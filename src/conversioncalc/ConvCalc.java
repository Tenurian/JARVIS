package conversioncalc;

import java.awt.*;

import javax.swing.*;


public class ConvCalc extends JFrame{
    /**
	 * 
	 */
	private static final long serialVersionUID = -4356914280226298626L;
	private JPanel weightCard,lengthCard,moneyCard,TempCard;
	private JTabbedPane panel;

    public ConvCalc() {
        this.setTitle("Conversion Calculator");
        this.setPreferredSize(new Dimension(400,250));
        this.setResizable(false);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
//        ConvCalc p1 = new ConvCalc("Weight Converter");
//        p1.add(new weightConv());
//        ConvCalc p2 = new ConvCalc("Length Converter");
//        p2.add(new lengthConv());
//        ConvCalc p3 = new ConvCalc("Currency Converter");
//        p3.add(new moneyConv());
//        ConvCalc p4 = new ConvCalc("Temperature Converter");
//        p4.add(new tempConv());
        
        panel = new JTabbedPane();
        
        weightCard = new weightConv();
        lengthCard = new lengthConv();
        moneyCard = new moneyConv();
        TempCard = new tempConv();
        
        panel.add("Length",lengthCard);
        panel.add("Weight",weightCard);
        panel.add("Currency",moneyCard);
        panel.add("Temperature",TempCard);
        
        this.add(panel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        
        
        this.setPreferredSize(new Dimension(350, 150));
    }
}