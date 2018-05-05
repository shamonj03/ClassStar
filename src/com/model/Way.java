package com.model;

public class Way {

	private long id;
	private long[] nodes;
	String name;
	
	public Way(long id, String name, long[] nodes) {
		this.id = id;
		this.nodes = nodes;
		this.name = name;
	}
	
	public long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	public long[] getNodes() {
		return nodes;
	}
}
