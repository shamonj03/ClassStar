package com.world;

import com.model.Node;

public interface AbstractHeuristic<T extends Node> {

	public double heuristic(T start, T end);
	
}
