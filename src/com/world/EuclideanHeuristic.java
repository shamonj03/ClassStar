package com.world;

import com.model.Node;
import com.model.Vertex;

public class EuclideanHeuristic implements AbstractHeuristic {

	@Override
	public double heuristic(Vertex start, Vertex end) {
		double dX = end.getX() - start.getX();
		double dY = end.getY() - start.getY();
		
		// We can omit the sqrt function. It's does nothing but cost more.
		return Math.pow(dX, 2.0) + Math.pow(dY, 2.0);
	}

}
