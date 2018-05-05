package com.model;

public class Location {

	public double lat;
	public double lng;

	@Override
	public String toString() {
		String s = "";
		s += "Location(\n";
		s += "lat: " + lat + "\n";
		s += "lng" + lng + "";
		s += ")";
		return s;
	}
}
