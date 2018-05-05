package com.world;

import com.model.Node;
import com.model.Vertex;

public interface AbstractHeuristic {

	public double heuristic(Vertex start, Vertex end);
	
}
