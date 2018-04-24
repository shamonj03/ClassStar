package com.world;

public interface AbstractHeuristic<T extends Node> {

	public double heuristic(T start, T end);
	
}
