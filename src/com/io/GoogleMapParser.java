package com.io;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import com.google.gson.Gson;
import com.io.google.types.Results;

public class GoogleMapParser {

	public static final String API_KEY = "AIzaSyCp3Bo-Z6xLXI-P2IfLxQRGsxiNXmItumE";
	
	public static Results parseByName(String name, String... params) throws IOException {
		name = name.replaceAll(" ", "+");
		return getResult("address="+name+"+Minneapolis", params);
	}
	
	// https://developers.google.com/maps/documentation/geocoding/intro#ReverseGeocoding
	public static Results parseByLocation(double lat, double lon, String... params) throws IOException {
		return getResult("latlng="+lat+","+lon, params);
	}
	
	public static Results getResult(String searchType, String... params) throws IOException {
		Gson gson = new Gson();
		
		String request = "https://maps.googleapis.com/maps/api/geocode/json?" + searchType;

		for(int i = 0; i < params.length; i++) {
			request += "&" + params[i];
		}
		request += "&key=" + API_KEY;
		URL url = new URL(request);
		
		System.out.println(request);
		Results results = gson.fromJson(new InputStreamReader(url.openStream()), Results.class);

		return results;
	}
}
