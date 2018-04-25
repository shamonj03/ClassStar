package com.io.google.types;

public class AddressComponent {

	public String long_name;
	public String short_name;
	public String[] types;
	
	@Override
	public String toString() {
		String s = "";

		s += "AddressComponent(";
		s += "long_name: " + long_name + "\n";
		s += "short_name: " + short_name + "\n";
		
		s += "types: [";
		for(String type : types) {
			s += type + ", ";
		}
		s += "] ";
		s += ")\n";
		return s;
	}
}
