package com.world;

import com.model.Vertex;

public class HaversineHeuristic implements AbstractHeuristic {

	@Override
	public double heuristic(Vertex start, Vertex end) {
		return hav(Math.toRadians(end.getX() - start.getX()))
				+ Math.cos(Math.toRadians(start.getX()))*Math.cos(Math.toRadians(end.getX()))
				* hav(Math.toRadians(end.getY() - start.getY()));
	}

	double hav(double radians) {
		return (1 - Math.cos(radians)) / 2.0f;
	}
}
