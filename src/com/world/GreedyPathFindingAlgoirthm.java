package com.world;

import java.util.ArrayList;
import java.util.List;

import com.Main;
import com.model.Edge;
import com.model.Vertex;

public class GreedyPathFindingAlgoirthm implements AbstractPathFindingAlgorithm {

	@Override
	public ArrayList<Vertex> findPath(Vertex start, Vertex end) {
		
		List<Vertex> openSet = new ArrayList<>();
		List<Vertex> closedSet = new ArrayList<>();
		ArrayList<Vertex> path = new ArrayList<>();
		
		
		openSet.add(start);
		path.add(start);
		
		int steps = 0;
		while(!openSet.isEmpty()) {
			Vertex current = openSet.get(0);
			
			
			
			if(current.equals(end)) {
				break;
			}
			
			if(steps > 15000) {
				break;
			}
			steps++;

			System.out.println("Current: " + current.getId());	
			path.add(current);
			openSet.remove(current);
			closedSet.add(current);
			
			
			Vertex best = Main.graph[current.getEdges()[0].getIndex()];
			
			for(int i = 1; i < current.getEdges().length; i++) {
				Edge edge = current.getEdges()[i];
				Vertex neighbor_v = Main.graph[edge.getIndex()];
			
				
				System.out.println(edge.getIndex());
				if(closedSet.contains(neighbor_v)) {
					continue;
				}
				
				double distance = getDistance(best, end);	
				double neighbor_d = getDistance(neighbor_v, end);
				
				System.out.println("D1: " + distance);
				System.out.println("D2: " + neighbor_d);
				if(neighbor_d <= distance) {
					best = neighbor_v;
				}
			}
			openSet.add(best);
			
		}
		path.add(end);
		return path;
	}
	

	private double getDistance(Vertex s, Vertex g) {
		double dX = Main.getXFromLat(g.getLat()) - Main.getXFromLat(s.getLat());
		double dY = Main.getYFromLon(g.getLon()) - Main.getYFromLon(s.getLon());
	    return (dX*dX) + (dY*dY);
	}
	
}