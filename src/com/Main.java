package com;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.model.Vertex;
import com.model.Way;

public class Main {

	public static void main(String[] args) throws IOException {
		Gson gson = new Gson();
		
		Vertex[] graph = gson.fromJson(new FileReader("./graph.json"), Vertex[].class);
		
		Way[] ways = gson.fromJson(new FileReader("./ways.raw"), Way[].class);

		Map<Long, Vertex> graphMap = new HashMap<>();
		for(Vertex vertex : graph) {
			graphMap.put(vertex.getId(), vertex);
		}
		
		Map<Long, Way> wayMap = new HashMap<>();
		for(Way way : ways) {
			wayMap.put(way.getId(), way);
		}
		
		long parent = 0;
		for(Way way : wayMap.values()) {
			if(way.getName().equalsIgnoreCase("ford hall")) {
				parent = way.getId();
			}
		}
		
		System.out.println("Parent: " + parent);
		for(long id : wayMap.get(parent).getNodes()) {
			System.out.println(id + " = " + graphMap.get(id).getId());
		}
	}
}
