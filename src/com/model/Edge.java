package com.model;

import com.google.gson.annotations.SerializedName;

public class Edge {
	@SerializedName("i")
	private int index;

	@SerializedName("w")
	private int weight;

	public int getIndex() {
		return index;
	}

	public int getWeight() {
		return weight;
	}
}
