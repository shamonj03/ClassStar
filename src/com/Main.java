package com;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.io.OSMReader;
import com.world.BasicAstarPathFindingAlgorithm;
import com.world.HaversineHeuristic;
import com.world.SphereNode;

public class Main {
		
	public static void main(String[] args) throws IOException {
			OSMReader reader = new OSMReader();
			
			// Read the nodes from the map into sphere nodes.
			ArrayList<SphereNode> list = reader.read(new File("./map.osm"));
			
			// Example of initiating AStar
			BasicAstarPathFindingAlgorithm<SphereNode> haversine 
				= new BasicAstarPathFindingAlgorithm<>(new HaversineHeuristic());
			
			// Test heuristic.
			System.out.println(haversine.getHeuristic(list.get(0), list.get(1)));
	}
}
