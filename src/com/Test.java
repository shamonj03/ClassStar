package com;
import java.io.File;
import java.io.IOException;
import java.util.List;

import com.control.ClassController;
import com.io.GoogleMapParser;
import com.io.OSMParser;
import com.io.google.types.AddressComponent;
import com.io.google.types.Result;
import com.io.google.types.MapData;
import com.model.ClassInformation;
import com.model.SphereNode;
import com.world.BasicAstarPathFindingAlgorithm;
import com.world.HaversineHeuristic;

public class Test {
		
	public static void main(String[] args) throws IOException {
			// Read the nodes from the map into sphere nodes.
			List<SphereNode> list = OSMParser.read(new File("./map.osm"));
			
			// Example of initiating AStar
			BasicAstarPathFindingAlgorithm<SphereNode> haversine 
				= new BasicAstarPathFindingAlgorithm<>(new HaversineHeuristic());
			
			// Test heuristic.
			System.out.println(haversine.getHeuristic(list.get(0), list.get(1)));
			
			
			ClassController controller = new ClassController();
			controller.addClass("Ford Hall");
			controller.addClass("Keller Hall");
			controller.addClass("Bruininks Hall");
			controller.addClass("Vincent Hall");
			
			for(ClassInformation info : controller.classes) {
				// Example of additional params.
				MapData results = GoogleMapParser.parseByLocation(info.getLocation().getLat(), info.getLocation().getLon(), "result_type=premise");
				
				for(Result result : results.results) {
					System.out.println("Result(");
					
					if(result.address_components != null) {
						for(AddressComponent comp : result.address_components) {
					//		System.out.println(comp.toString());
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
}
