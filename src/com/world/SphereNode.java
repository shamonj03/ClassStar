package com.world;

public class SphereNode extends Node {
	
	private double latR;
	private double lonR;

	public SphereNode(double x, double y) {
		super(x, y);
		
		// Convert to radians to save some time...
		latR = Math.toRadians(x);
		lonR = Math.toRadians(y);
	}
	
	public double getLat() {
		return x;
	}
	
	public double getLon() {
		return y;
	}
	
	public double getLatR() {
		return latR;
	}
	
	public double getLonR() {
		return lonR;
	}
}
