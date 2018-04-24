package com.world;

public abstract class AbstractAStarPathFindingAlgorithm<T extends Node> implements AbstractPathFindingAlgorithm<T> {

	private AbstractHeuristic<T> heuristic;
	
	AbstractAStarPathFindingAlgorithm(AbstractHeuristic<T> heuristic) {
		this.heuristic = heuristic;
	}
	
	
	public double getHeuristic(T start, T end) {
		return heuristic.heuristic(start, end);
	}
}
