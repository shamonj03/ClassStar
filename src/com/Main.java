package com;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.io.GoogleMapParser;
import com.io.OSMParser;
import com.io.google.Result;
import com.io.google.Results;
import com.io.google.types.AddressComponent;
import com.io.google.types.Geometry;
import com.world.BasicAstarPathFindingAlgorithm;
import com.world.HaversineHeuristic;
import com.world.SphereNode;

public class Main {
		
	public static void main(String[] args) throws IOException {
			// Read the nodes from the map into sphere nodes.
			ArrayList<SphereNode> list = OSMParser.read(new File("./map.osm"));
			
			// Example of initiating AStar
			BasicAstarPathFindingAlgorithm<SphereNode> haversine 
				= new BasicAstarPathFindingAlgorithm<>(new HaversineHeuristic());
			
			// Test heuristic.
			System.out.println(haversine.getHeuristic(list.get(0), list.get(1)));
			
			
			Results results = GoogleMapParser.parseByName("Ford Hall");
	
			for(Result result : results.results) {
				System.out.println("Result(");
				
				if(result.address_components != null) {
					for(AddressComponent comp : result.address_components) {
						System.out.println(comp.toString());
					}
				}
				

				if(result.formatted_address != null) {
					System.out.println("Formatted Address(" + result.formatted_address.toString() + ")\n");
				}
				

				if(result.geometry != null) {
					System.out.println(result.geometry.toString());
				}
			}
			System.out.println(")");
	}
}
