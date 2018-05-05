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
		AStarNode start = new AStarNode(start_v.getId());
		
		start.setfScore(getHeuristic(start_v, end_v));
		
		AStarNode end = new AStarNode(end_v.getId());
		openSet.add(start);
		AStarNode current = start;
		
		int steps = 0;
		while(!openSet.isEmpty()) {
			Collections.sort(openSet);
			current = openSet.get(0);
			
			if(steps > 5000) {
				break;
			}
			steps++;
			
			if(current == end) {
				break;
			}

			Vertex current_v = Main.vertexMap.get(current.getId());
			openSet.remove(current);
			closedSet.add(current);
			
			for(Edge edge : current_v.getEdges()) {
				Vertex neighbor_v = Main.graph[edge.getIndex()];
				AStarNode neighbor = new AStarNode(neighbor_v.getId());
				neighbor.setfScore(getHeuristic(neighbor_v, end_v));
				neighbor.setCameFromId(current);
				
				if(closedSet.contains(neighbor)) {
					continue;
				}

				openSet.add(neighbor);
				System.out.println(neighbor_v.getId());
				
				double tentative_gScore =  current.getgScore() + getHeuristic(start_v, neighbor_v);
				
				if(tentative_gScore >= neighbor.getgScore()) {
					continue;
				}

				neighbor.setgScore(tentative_gScore);
				
				int index = openSet.indexOf(neighbor);
				openSet.add(neighbor);
			}
		}
		
		ArrayList<Vertex> path = new ArrayList<>();
		path.add(Main.vertexMap.get(current.getId()));
		
		while(current.getCameFrom() != null) {
			current = current.getCameFrom();
			path.add(Main.vertexMap.get(current.getId()));
		}
		return path;
	}

	
	private double distance(Vertex s, Vertex g) {
		double dx = Main.getXFromLat(g.getLat()) - Main.getXFromLat(s.getLat());
		double dy = Main.getYFromLon(g.getLon()) - Main.getYFromLon(s.getLon());
		return Math.sqrt((dx * dx) + (dy * dy));
	}
	
	private double heuristic2(Vertex s, Vertex g) {
		double dx = Math.abs(Main.getXFromLat(g.getLat()) - Main.getXFromLat(s.getLat()));
		double dy = Math.abs(Main.getYFromLon(g.getLon()) - Main.getYFromLon(s.getLon()));
		return dx + dy;
	}
}
