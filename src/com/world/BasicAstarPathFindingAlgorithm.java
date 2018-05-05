package com.world;
import java.util.ArrayList;

import com.model.Node;
import com.model.Vertex;

public class BasicAstarPathFindingAlgorithm extends AbstractAStarPathFindingAlgorithm {

	public BasicAstarPathFindingAlgorithm(AbstractHeuristic heuristic) {
		super(heuristic);
	}

	@Override
	public ArrayList<Node> findPath(Vertex start, Vertex end) {
		return null;
	}

}
