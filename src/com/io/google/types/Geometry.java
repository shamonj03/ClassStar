package com.io.google.types;

import com.io.google.Result;

public class Geometry extends Result {

	public Location location;
	public String[] types;
	
	@Override
	public String toString() {
		return "Geometry(" + location + ")\n";
	}
	
}
