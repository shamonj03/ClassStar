package com.io;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.world.SphereNode;

public class OSMReader {

	public ArrayList<SphereNode> read(File file) throws IOException {
		if(!file.exists()) {
			throw new IOException("Cannot locate osm file.");
		}
		
		ArrayList<SphereNode> list = new ArrayList<>();
		
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			String line = "";
			
			while((line = reader.readLine()) != null) {
				if(line.contains("<node")) {
					double lat = 0;
					double lon = 0;
					
					String[] tokens = line.split(" ");
					//System.out.println(line);
					for(int i = 1; i < tokens.length; i++) {
						String token = tokens[i].replaceAll("/", "").replaceAll(">", "");
						int start = token.indexOf("=") + 1;
						int end = token.length();
						String value = token.substring(start, end).replace("\"", "");
						
						if(start > 0) {
							String tokenId = token.substring(0, start-1);
							if(tokenId.equals("lat")) {
								lat = Double.parseDouble(value);
							} else if(tokenId.equals("lon")) {
								lon = Double.parseDouble(value);
								//System.out.println(lat + ", " + lon);
								list.add(new SphereNode(lat, lon));
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
		return list;
	}
}
