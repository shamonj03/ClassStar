package com.model;

public class ClassInformation {

	private String name;
	
	private SphereNode location;
	
	public ClassInformation(String name) {
		this.name = name;
	}
	
	public void setLocation(SphereNode location) {
		this.location = location;
	}

	public SphereNode getLocation() {
		return location;
	}
	
	public String getName() {
		return name;
	}
}
