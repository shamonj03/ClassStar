package com.world;

import com.model.Node;

public abstract class AbstractAStarPathFindingAlgorithm implements AbstractPathFindingAlgorithm {

	private AbstractHeuristic heuristic;
	
	AbstractAStarPathFindingAlgorithm(AbstractHeuristic heuristic) {
		this.heuristic = heuristic;
	}
	
	
	public double getHeuristic(Node start, Node end) {
		return heuristic.heuristic(start, end);
	}
}
