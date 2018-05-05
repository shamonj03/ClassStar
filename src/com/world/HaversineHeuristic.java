package com.world;

import com.model.Node;

public class HaversineHeuristic implements AbstractHeuristic {

	@Override
	public double heuristic(Node start, Node end) {
		return hav(end.getX() - start.getX())
				+ Math.cos(start.getX())*Math.cos(end.getX())
				* hav(end.getY() - start.getY());
	}

	double hav(double radians) {
		return (1 - Math.cos(radians)) / 2.0f;
	}
}
