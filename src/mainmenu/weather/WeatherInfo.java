package mainmenu.weather;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class WeatherInfo {

	private String sURL = "http://api.openweathermap.org/data/2.5/forecast/daily?q=Salt+Lake+City&units=imperial";
	private String location = "Salt Lake City";
	private URL url;
	private HttpURLConnection request;
	private JsonParser jp = new JsonParser();
	private JsonElement root;
	private JsonObject rootobj;
	private JsonArray days;
	private String[] desc;
	private double[] temps;
	private double[] max;
	private double[] min;
	
	public WeatherInfo() throws IOException{
		try{
			url = new URL(sURL);
			request = (HttpURLConnection) url.openConnection();
			request.connect();
			root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
			rootobj = root.getAsJsonObject();
			days = rootobj.get("list").getAsJsonArray();
			temps = new double[days.size()];
			max = new double[days.size()];
			min = new double[days.size()];
			desc = new String[days.size()];
			setTemps();
		}
		catch(MalformedURLException e){
			e.printStackTrace();
		}
	}

	private void setTemps(){
		for (int i = 0; i < temps.length;i++){
			temps[i] = ((JsonObject) ((JsonObject) days.get(i)).get("temp")).get("day").getAsDouble();
			desc[i] = ((JsonObject) ((JsonArray) ((JsonObject) days.get(i)).get("weather")).get(0)).get("description").getAsString();
			max[i] = ((JsonObject) ((JsonObject) days.get(i)).get("temp")).get("max").getAsDouble();
			min[i] = ((JsonObject) ((JsonObject) days.get(i)).get("temp")).get("min").getAsDouble();
		}
	}
	
	public URL getIconURL(int day){
		String iconId = ((JsonObject) ((JsonArray) ((JsonObject) days.get(day)).get("weather")).get(0)).get("icon").getAsString();
		String url = "http://www.openweathermap.org/img/w/" + iconId + ".png";

		try {
			return new URL(url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public double getTemp(int day){
		return this.temps[day];
	}
	public double getMin(int day){
		return this.min[day];
	}
	public double getMax(int day){
		return this.max[day];
	}
	public String getDesc(int day){
		return this.desc[day];
	}
	public void setsURL(String sURL) {
		this.sURL = sURL;
		try{
			url = new URL(sURL);
			request = (HttpURLConnection) url.openConnection();
			request.connect();
			root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
			rootobj = root.getAsJsonObject();
			days = rootobj.get("list").getAsJsonArray();
			temps = new double[days.size()];
			max = new double[days.size()];
			min = new double[days.size()];
			desc = new String[days.size()];
			setTemps();
		}
		catch(MalformedURLException e){
			e.printStackTrace();
		}
		catch(IOException io){
			io.printStackTrace();
		}
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
}