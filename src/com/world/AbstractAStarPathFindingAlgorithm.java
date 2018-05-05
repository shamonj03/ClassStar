package com.world;

import com.model.Node;
import com.model.Vertex;

public abstract class AbstractAStarPathFindingAlgorithm implements AbstractPathFindingAlgorithm {

	private AbstractHeuristic heuristic;
	
	AbstractAStarPathFindingAlgorithm(AbstractHeuristic heuristic) {
		this.heuristic = heuristic;
	}
	
	
	public double getHeuristic(Vertex start, Vertex end) {
		return heuristic.heuristic(start, end);
	}
}
