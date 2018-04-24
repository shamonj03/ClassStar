package com.world;

public class HaversineHeuristic implements AbstractHeuristic<SphereNode> {

	@Override
	public double heuristic(SphereNode start, SphereNode end) {
		
		return hav(end.getLatR() - start.getLatR())
				+ Math.cos(start.getLatR())*Math.cos(end.getLatR())
				* hav(end.getLonR() - start.getLonR());
	}

	double hav(double radians) {
		return (1 - Math.cos(radians)) / 2.0f;
	}
}
