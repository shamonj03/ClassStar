package com.io;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import com.google.gson.Gson;
import com.io.google.Results;

public class GoogleMapParser {

	public static final String API_KEY = "AIzaSyCp3Bo-Z6xLXI-P2IfLxQRGsxiNXmItumE";
	
	public static Results parseByName(String name) throws IOException {
		Gson gson = new Gson();
		
		name = name.replaceAll(" ", "+");
		URL url = new URL("https://maps.googleapis.com/maps/api/geocode/json?address=name+Minneapolis&key="+API_KEY);
		Results results = gson.fromJson(new InputStreamReader(url.openStream()), Results.class);

		return results;
	}
	

	
	public static Results parseByLocation(double lat, double lon) throws IOException {
		Gson gson = new Gson();
		
		URL url = new URL("https://maps.googleapis.com/maps/api/geocode/json?latlng="+lat+","+lon+"&key="+API_KEY);
		Results results = gson.fromJson(new InputStreamReader(url.openStream()), Results.class);

		return results;
	}
}
