package com.world;
import java.util.ArrayList;

public class BasicAstarPathFindingAlgorithm<T extends Node> extends AbstractAStarPathFindingAlgorithm<T> {

	public BasicAstarPathFindingAlgorithm(AbstractHeuristic<T> heuristic) {
		super(heuristic);
	}

	@Override
	public ArrayList<T> findPath(T start, T end) {
		return null;
	}

}
