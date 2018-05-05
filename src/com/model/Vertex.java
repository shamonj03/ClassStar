package com.model;

import com.google.gson.annotations.SerializedName;

public class Vertex {
    @SerializedName("la")
	private double lat;
    
    @SerializedName("lo")
	private double lon;

    @SerializedName("e")
	private Edge[] edges;
    
    private long id;
    
    public void setId(long id) {
		this.id = id;
	}
    
    public Edge[] getEdges() {
		return edges;
	}
    
    public double getLat() {
		return lat;
	}
    
    public double getLon() {
		return lon;
	}
    
    public long getId() {
		return id;
	}
}
