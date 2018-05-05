package com;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.model.Node;
import com.model.Vertex;
import com.model.Way;


public class Formatter {

	public static void main(String[] args) throws IOException {
		parseNodes();
		parseWays();
		
		

		Gson gson = new Gson();
		
		Vertex[] buildings = gson.fromJson(new FileReader("./buildings.raw"), Vertex[].class);
		Vertex[] roads = gson.fromJson(new FileReader("./roads.raw"), Vertex[].class);
		

		Node[] nodes = gson.fromJson(new FileReader("./nodes.raw"), Node[].class);
		//Way[] ways = gson.fromJson(new FileReader("./ways.raw"), Way[].class);
		
		System.out.println(buildings.length);
		System.out.println(roads.length);
		
		Vertex[] vertices = new Vertex[buildings.length + roads.length];
		
		System.arraycopy(buildings, 0, vertices, 0, buildings.length);
		System.arraycopy(roads, 0, vertices, buildings.length, roads.length);
		
		System.out.println((buildings.length + roads.length) + "=" + vertices.length);
		
		

		for(Node node : nodes) {
			for(Vertex vertex : buildings) {
				if(vertex.getLat() == node.getX()
						&& vertex.getLon() == node.getY()) {
					vertex.setId(node.getId());
				}
			}
		}
		
		GsonBuilder builder = new GsonBuilder();
		builder.setPrettyPrinting();

		try (BufferedWriter writer = new BufferedWriter(new FileWriter("buildings.json"))) {
		    gson = builder.create();
		    gson.toJson(buildings, writer);
		}
		
		

		for(Node node : nodes) {
			for(Vertex vertex : roads) {
				if(vertex.getLat() == node.getX()
						&& vertex.getLon() == node.getY()) {
					vertex.setId(node.getId());
				}
			}
		}
		
		try (BufferedWriter writer = new BufferedWriter(new FileWriter("roads.json"))) {
		    gson = builder.create();
		    gson.toJson(roads, writer);
		}
	}

	
	private static void parseWays() throws FileNotFoundException, IOException {

		List<Way> list = new ArrayList<>();
		try (BufferedReader reader = new BufferedReader(new FileReader("map.osm"))) {
			String line = "";

			long id = 0;
			while ((line = reader.readLine()) != null) {
				if(line.contains("<way")) {
					List<Long> refs = new ArrayList<>();
					String name = "";
					String[] tokens = line.split("way id=");
							
					if(tokens.length > 0) {
						String value = tokens[1];
						value = value.split(" ")[0];
						value = value.substring(1, value.length()-1);
						id = Long.parseLong(value);
					//	System.out.println("id: " + id);
					}
					
					
					// If the line ends with an unclosed tag, there is meta data after.
					// Not sure if this is useful data yet.
					if(line.endsWith("\">")) {
						while((line = reader.readLine()) != null) {
							if(line.contains("nd ref=")) {
								tokens = line.split("ref=");
								String value = tokens[1];
								value = value.substring(1, value.length()-3);
								//System.out.println(value);
								refs.add(Long.parseLong(value));
							}
							if(line.contains("tag k=\"name\"")) {
								tokens = line.split("v=");
								String value = tokens[1];
								value = value.substring(1, value.length()-3);
								//System.out.println(value);
							//	if(tokens.length > 0)
									System.out.println(value);
									name = value;
							}
							if(line.contains("</way>")) {
								long[] test = new long[refs.size()];
								for(int i = 0; i < test.length; i++) {
									test[i] = refs.get(i);
								}
								System.out.println("id: " + id);
								list.add(new Way(id, name, test));
								break;
							}
						//	System.out.println(line);
						}
					}
				}
			}
		}
		
		GsonBuilder builder = new GsonBuilder();
		builder.setPrettyPrinting();
		
		try (BufferedWriter writer = new BufferedWriter(new FileWriter("ways.raw"))) {
		    Gson gson = builder.create();
		    gson.toJson(list.toArray(), writer);
		}
	}
	
	
	private static void parseNodes() throws FileNotFoundException, IOException {

		List<Node> list = new ArrayList<>();
		try (BufferedReader reader = new BufferedReader(new FileReader("map.osm"))) {
			String line = "";

			while ((line = reader.readLine()) != null) {
				if(line.contains("<node")) {
					double lat = 0;
					double lon = 0;
					long id = 0;
					
					String[] tokens = line.split(" ");
					//System.out.println(line);
					for(int i = 1; i < tokens.length; i++) {
						String token = tokens[i].replaceAll("/", "").replaceAll(">", "");
						int start = token.indexOf("=") + 1;
						int end = token.length();
						String value = token.substring(start, end).replace("\"", "");
						
						if(start > 0) {
							String tokenId = token.substring(0, start-1);
							if(tokenId.equals("id")) {
							 id = Long.parseLong(value);
							} else if(tokenId.equals("lat")) {
								lat = Double.parseDouble(value);
							} else if(tokenId.equals("lon")) {
								lon = Double.parseDouble(value);
								//System.out.println(lat + ", " + lon);
								list.add(new Node(id, lat, lon));
							}
						}
					}
					
					// If the line ends with an unclosed tag, there is meta data after.
					// Not sure if this is useful data yet.
					if(line.endsWith("\">")) {
						while((line = reader.readLine()) != null) {
							if(line.contains("</node>")) {
								break;
							}
						//	System.out.println(line);
						}
					}
				}
			}
		}
		
		GsonBuilder builder = new GsonBuilder();
		builder.setPrettyPrinting();

		try (BufferedWriter writer = new BufferedWriter(new FileWriter("nodes.raw"))) {
		    Gson gson = builder.create();
		    gson.toJson(list.toArray(), writer);
		}
	}
}
