package com.world;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

import com.Main;
import com.model.Edge;
import com.model.Vertex;

public class BasicAstarPathFindingAlgorithm extends AbstractAStarPathFindingAlgorithm {

	public BasicAstarPathFindingAlgorithm(AbstractHeuristic heuristic) {
		super(heuristic);
	}

	@Override
	public ArrayList<Vertex> findPath(Vertex start_v, Vertex end_v) {
		Queue<AStarNode> closedSet = new PriorityQueue<>();
		Queue<AStarNode> openSet = new PriorityQueue<>();
		AStarNode start = new AStarNode(start_v);
		
		start.setfScore(getHeuristic(start_v, end_v));
		start.setgScore(0);

		AStarNode end = new AStarNode(end_v);
		AStarNode current = new AStarNode(start_v);
		
		openSet.add(start);
		
		int count = 0;
		while(!openSet.isEmpty()) {
			current = openSet.poll();
			
			if(count > 50000) {
				break;
			}
			
			if(current.equals(end)) {
				break;
			}

			Vertex current_v = current.getVertex();
			openSet.remove(current);
			closedSet.add(current);
			
			for(Edge edge : current_v.getEdges()) {
				Vertex neighbor_v = Main.graph[edge.getIndex()];
				AStarNode neighbor = new AStarNode(neighbor_v);
				count++;
				
				if(closedSet.contains(neighbor)) {
					continue;
				}

				if(!openSet.contains(neighbor)) {
					openSet.add(neighbor);
				}
				//System.out.println(neighbor_v.getId());
				
				double tentative_gScore = current.getgScore() + getDistance(start_v, neighbor_v);
				
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
	
	public double getHeuristic(Vertex s, Vertex g) {
		double dX = Math.abs(Main.getXFromLat(g.getLat()) - Main.getXFromLat(s.getLat()));
		double dY = Math.abs(Main.getYFromLon(g.getLon()) - Main.getYFromLon(s.getLon()));
	    return dX + dY;
	}
	
	private double getDistance(Vertex s, Vertex g) {
		double dX = Main.getXFromLat(g.getLat()) - Main.getXFromLat(s.getLat());
		double dY = Main.getYFromLon(g.getLon()) - Main.getYFromLon(s.getLon());
	    return (dX*dX) + (dY*dY);
	}
}