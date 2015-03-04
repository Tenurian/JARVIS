package mainmenu.audio;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ControlsPanel extends JPanel implements ActionListener, ChangeListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6539310895797375686L;
	QuietMix qm = new QuietMix();
	Rain rain = new Rain();
	private JSlider rainvolume, qmvolume;
	private final int MIN_VOLUME = 0, MAX_VOLUME = 100, INIT_VOLUME = 50;
	private JPanel controlspanel;
	
	public ControlsPanel(){
		controlspanel = new JPanel();
		controlspanel.setLayout(new BoxLayout(controlspanel, BoxLayout.X_AXIS));
		JPanel rainpanel = new JPanel();
		rainpanel.setLayout(new BorderLayout());
		JCheckBox raincb = new JCheckBox("Rain");
		raincb.setActionCommand("rain");
		raincb.addActionListener(this);
		raincb.setSelected(false);
		rainvolume = new JSlider(MIN_VOLUME,MAX_VOLUME,INIT_VOLUME);
		rainvolume.setPreferredSize(new Dimension(200, 20));
		rainvolume.setEnabled(false);
		rainvolume.addChangeListener(this);
		rainpanel.add(raincb, BorderLayout.WEST);
		rainpanel.add(rainvolume, BorderLayout.EAST);
		
		
		JPanel qmpanel = new JPanel();
		qmpanel.setLayout(new BorderLayout());
		JCheckBox qmcb = new JCheckBox("Quiet Mix");
		qmcb.setActionCommand("qm");
		qmcb.addActionListener(this);
		qmcb.setSelected(false);
		qmvolume = new JSlider(MIN_VOLUME,MAX_VOLUME,INIT_VOLUME);
		qmvolume.setPreferredSize(new Dimension(200, 20));
		qmvolume.setEnabled(false);
		qmvolume.addChangeListener(this);
		qmpanel.add(qmcb, BorderLayout.WEST);
		qmpanel.add(qmvolume, BorderLayout.EAST);
		
		controlspanel.add(rainpanel);
		controlspanel.add(qmpanel);
		this.add(controlspanel);
	}
	
	public JPanel getControlsPanel(){
		return controlspanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		switch(e.getActionCommand()){
		case "qm":
			if(((JCheckBox)e.getSource()).isSelected()){
				qm.getMediaPlayer().play();
				qmvolume.setEnabled(true);
			}else{
				qm.getMediaPlayer().stop();
				qmvolume.setEnabled(false);
			}
			break;
		case "rain":
			if(((JCheckBox)e.getSource()).isSelected()){
				rain.getMediaPlayer().play();
				rainvolume.setEnabled(true);
			}else{
				rain.getMediaPlayer().stop();
				rainvolume.setEnabled(false);
			}
			break;
		}
	}

	@Override
	public void stateChanged(ChangeEvent s) {
		if(s.getSource() == rainvolume){
			rain.getMediaPlayer().setVolume(((double)rainvolume.getValue())/100d);
		}
		
		if(s.getSource() == qmvolume){
			qm.getMediaPlayer().setVolume(((double)qmvolume.getValue())/100d);
		}
	}

}
