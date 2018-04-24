package com;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.io.OSMReader;
import com.io.google.Result;
import com.io.google.Results;
import com.io.google.types.AddressComponent;
import com.io.google.types.Geometry;
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
			
			
			Gson gson = new Gson();
			
			URL url = new URL("https://maps.googleapis.com/maps/api/geocode/json?address=Ford+Hall+Minneapolis&key=AIzaSyCp3Bo-Z6xLXI-P2IfLxQRGsxiNXmItumE");
			Results results = gson.fromJson(new InputStreamReader(url.openStream()), Results.class);
	
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
