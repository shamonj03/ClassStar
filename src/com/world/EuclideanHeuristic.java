package com.world;

import com.model.Node;

public class EuclideanHeuristic implements AbstractHeuristic<Node> {

	@Override
	public double heuristic(Node start, Node end) {
		double dX = end.getX() - start.getX();
		double dY = end.getY() - start.getY();
		
		// We can omit the sqrt function. It's does nothing but cost more.
		return Math.pow(dX, 2.0) + Math.pow(dY, 2.0);
	}

}
