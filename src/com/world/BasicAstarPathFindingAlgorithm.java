package com.world;
import java.util.ArrayList;

import com.model.Node;

public class BasicAstarPathFindingAlgorithm extends AbstractAStarPathFindingAlgorithm {

	public BasicAstarPathFindingAlgorithm(AbstractHeuristic heuristic) {
		super(heuristic);
	}

	@Override
	public ArrayList<Node> findPath(Node start, Node end) {
		return null;
	}

}
