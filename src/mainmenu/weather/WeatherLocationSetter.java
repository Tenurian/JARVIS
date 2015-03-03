package mainmenu.weather;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.MutableComboBoxModel;

import mainmenu.gui.Jarvis;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class WeatherLocationSetter extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8906353616807304766L;
	private WeatherInfo w = new WeatherInfo();
	private String URL = "";
	private String[] states = {"STATE","Alabama","Alaska","Arizona","Arkansas","California","Colorado","Connecticut","Delaware","Florida","Georgia","Hawaii","Idaho","Illinois","Indiana","Iowa","Kansas","Kentucky","Louisiana","Maine","Maryland","Massachusetts","Michigan","Minnesota","Mississippi","Missouri","Montana","Nebraska","Nevada","New Hampshire","New Jersey","New Mexico","New York","North Carolina","North Dakota","Ohio","Oklahoma","Oregon","Pennsylvania","Rhode Island","South Carolina","South Dakota","Tennessee","Texas","Utah","Vermont","Virginia","Washington","West Virginia","Wisconsin","Wyoming"};
	private String[][] cities = {
			{"Birmingham","Montgomery","Mobile","Huntsville","Tuscaloosa"},
			{"Anchorage","Fairbanks","Juneau","Sitka","Ketchikan"},
			{"Phoenix","Tucson","Mesa","Chandler","Glendale"},
			{"Little Rock","Fort Smith","Fayetteville","Springdale","Jonesboro"},
			{"Los Angeles","San Diego","San Jose","San Francisco","Fresno","Sacramento"},
			{"Denver","Colorado Springs","Aurora","Fort Collins","Lakewood"},
			{"Bridgeport","New Haven","Stamford","Hartford","Waterbury"},
			{"Wilmington","Dover","Newark","Milford","Seaford"},
			{"Jacksonville","Miami","Tampa","St. Petersburg","Orlando","Tallahassee"},
			{"Atlanta","Augusta","Columbus","Savannah","Athens"},
			{"Honolulu","Hilo","Kailua","Kapolei","Kaneohe"},
			{"Boise	Nampa","Meridian","Idaho Falls","Pocatello"},
			{"Chicago","Aurora","Rockford","Joliet","Naperville","Springfield"},
			{"Indianapolis","Fort Wayne","Evansville","South Bend","Carmel"},
			{"Des Moines","Cedar Rapids","Davenport","Sioux City","Waterloo"},
			{"Wichita","Overland Park","Kansas City","Topeka","Olathe"},
			{"Louisville","Lexington","Bowling Green","Owensboro","Covington","Frankfort"},
			{"New Orleans","Baton Rouge","Shreveport","Lafayette","Lake Charles"},
			{"Portland","Lewiston","Bangor","South Portland","Auburn","Augusta"},
			{"Baltimore","Columbia","Germantown","Silver Spring","Waldorf","Annapolis"},
			{"Boston","Worcester","Springfield","Lowell","Cambridge"},
			{"Detroit","Grand Rapids","Warren","Sterling Heights","Ann Arbor","Lansing"},
			{"Minneapolis","Saint Paul","Rochester","Duluth","Bloomington"},
			{"Jackson","Gulfport","Hattiesburg","Southaven","Biloxi"},
			{"Kansas City","Saint Louis","Springfield","Independence","Columbia","Jefferson City"},
			{"Billings","Missoula	Great Falls	Bozeman	Butte	Helena"},
			{"Omaha","Lincoln","Bellevue","Grand Island","Kearney"},
			{"Las Vegas","Henderson","North Las Vegas","Reno","Sparks","Carson City"},
			{"Manchester","Nashua","Concord","Derry","Rochester"},
			{"Newark","Jersey City","Paterson","Elizabeth","Edison","Trenton"},
			{"Albuquerque","Las Cruces	Rio Rancho	Santa Fe	Roswell"},
			{"New York","Buffalo","Rochester","Yonkers","Syracuse","Albany"},
			{"Charlotte","Raleigh","Greensboro","Durham","Winston-Salem"},
			{"Fargo","Bismarck","Grand Forks","Minot","West Fargo"},
			{"Columbus","Cleveland","Cincinnati","Toledo","Akron"},
			{"Oklahoma City","Tulsa","Norman","Broken Arrow","Lawton"},
			{"Portland","Salem","Eugene","Gresham","Hillsboro"},
			{"Philadelphia","Pittsburgh","Allentown","Erie","Reading","Harrisburg"},
			{"Providence","Warwick","Cranston","Pawtucket","East Providence"},
			{"Columbia","Charleston","North Charleston","Mount Pleasant","Rock Hill"},
			{"Sioux Falls","Rapid City","Aberdeen","Brookings","Watertown","Pierre"},
			{"Memphis","Nashville","Knoxville","Chattanooga","Clarksville"},
			{"Houston","San Antonio","Dallas","Austin","Fort Worth"},
			{"Salt Lake City","West Valley City","Provo","West Jordan","Orem"},
			{"Burlington","Essex","South Burlington","Colchester","Rutland	Montpelier"},
			{"Virginia Beach","Norfolk","Chesapeake","Richmond","Newport News"},
			{"Seattle","Spokane","Tacoma","Vancouver","Bellevue","Olympia"},
			{"Charleston","Huntington","Parkersburg","Morgantown","Wheeling"},
			{"Milwaukee","Madison","Green Bay","Kenosha","Racine"},
			{"Cheyenne","Casper","Laramie","Gillette","Rock Springs"}};
	private JComboBox statecb, citycb;
	private MutableComboBoxModel citymodel;
	private JLabel centerlabel = new JLabel("",JLabel.CENTER);
	private JButton button;
	private Jarvis container = null;
	
	public WeatherLocationSetter(WeatherInfo w, Jarvis container){
		super("Choose your location");
		this.container = container;
		this.w = w;
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setPreferredSize(new Dimension(275,130));
		statecb = new JComboBox(states);
		statecb.addActionListener(this);
		citycb = new JComboBox();
		citycb.addItem("CITY");
		citycb.setEnabled(false);
		citycb.addActionListener(this);
		
		citymodel = (MutableComboBoxModel)citycb.getModel();
		
		JPanel combopanel = new JPanel();
		combopanel.setLayout(new BoxLayout(combopanel, BoxLayout.Y_AXIS));
		combopanel.add(statecb);
		combopanel.add(citycb);
		
		JPanel buttonpanel = new JPanel();
		button = new JButton("Update Location");
		button.setActionCommand("show");
		button.addActionListener(this);
		button.setEnabled(false);
		buttonpanel.add(button);
		JButton cancelbutton = new JButton("Cancel");
		cancelbutton.setActionCommand("cancel");
		cancelbutton.addActionListener(this);
		buttonpanel.add(cancelbutton);
		this.add(combopanel, BorderLayout.NORTH);
		this.add(centerlabel, BorderLayout.CENTER);
		this.add(buttonpanel, BorderLayout.SOUTH);

		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == statecb){
//			System.out.println(statecb.getSelectedItem()+","+statecb.getSelectedIndex());
			citycb.setEnabled(true);
			String[] bort = cities[statecb.getSelectedIndex()-1];
			citymodel = (MutableComboBoxModel)new JComboBox().getModel();
			for(int j = 0; j < bort.length; j++){
				citymodel.addElement(bort[j]);
			}
			citycb.setModel(citymodel);
			citycb.revalidate();
			citycb.repaint();
			citycb.repaint();
			this.revalidate();
			this.repaint();
		}

		if(e.getSource() == citycb){
			if(citycb.getSelectedIndex()!=-1){
				button.setEnabled(true);
			}
		}

		if(e.getActionCommand() == "show"){
			URL = "http://api.openweathermap.org/data/2.5/forecast/daily?q="+(((String)citycb.getSelectedItem()).replaceAll(" ", "+"))+"&units=imperial";
			w.setsURL(URL);
			w.setLocation((String)citycb.getSelectedItem());
			container.updateWeather();
			container.revalidate();
			container.repaint();
			this.dispose();
		}
		
		if(e.getActionCommand() == "cancel"){
			this.dispose();
		}
	}
}
