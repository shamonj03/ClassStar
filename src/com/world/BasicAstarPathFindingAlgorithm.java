package com.world;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.Main;
import com.model.Edge;
import com.model.Vertex;

public class BasicAstarPathFindingAlgorithm extends AbstractAStarPathFindingAlgorithm {

	public BasicAstarPathFindingAlgorithm(AbstractHeuristic heuristic) {
		super(heuristic);
	}

	@Override
	public ArrayList<Vertex> findPath(Vertex start_v, Vertex end_v) {
		List<AStarNode> closedSet = new ArrayList<>();
		List<AStarNode> openSet = new ArrayList<>();
		AStarNode start = new AStarNode(start_v);
		
		start.setfScore(getHeuristic(start_v, end_v));
		start.setgScore(0);
		
		AStarNode end = new AStarNode(end_v);
		AStarNode current = start;
		
		openSet.add(start);
		
		int steps = 0;
		while(!openSet.isEmpty()) {
			Collections.sort(openSet);
			current = openSet.get(0);
			
			if(steps > 15000) {
				break;
			}
			steps++;
			
			if(current == end) {
				break;
			}

			Vertex current_v = current.getVertex();
			openSet.remove(current);
			closedSet.add(current);
			
			for(Edge edge : current_v.getEdges()) {
				Vertex neighbor_v = Main.graph[edge.getIndex()];
				AStarNode neighbor = new AStarNode(neighbor_v);
				
				if(closedSet.contains(neighbor)) {
					continue;
				}

				if(!openSet.contains(neighbor)) {
					openSet.add(neighbor);
				}
				//System.out.println(neighbor_v.getId());
				
				double tentative_gScore =  current.getgScore() + getDistanceFromLatLonInKm(current_v, neighbor_v);
				
				if(tentative_gScore >= neighbor.getgScore()) {
					continue;
				}

				neighbor.setgScore(tentative_gScore);
				neighbor.setfScore(neighbor.getgScore() + getHeuristic(neighbor_v, end_v));
				neighbor.setCameFrom(current);
				
				openSet.add(neighbor);
			}
		}
		
		ArrayList<Vertex> path = new ArrayList<>();
		path.add(current.getVertex());
		
		while(current.getCameFrom() != null) {
			current = current.getCameFrom();
			path.add(current.getVertex());
		}
		return path;
	}
	
	private double getDistanceFromLatLonInKm(Vertex s, Vertex g) {
	    double R = 6371; // Radius of the earth in km
	    double dLat = Math.toRadians(g.getLat()-s.getLat());  // deg2rad below
	    double dLon = Math.toRadians(g.getLon()-s.getLon()); 
	    double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
	            Math.cos(Math.toRadians(s.getLat())) * Math.cos(Math.toRadians(g.getLat())) * 
	            Math.sin(dLon/2) * Math.sin(dLon/2)
	    ; 
	    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a)); 
	    double d = R * c; // Distance in km
	    return d;
	}
}
