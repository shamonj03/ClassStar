package com;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import com.google.gson.Gson;
import com.model.Edge;
import com.model.Vertex;
import com.model.Way;

public class Main {

	public static void main(String[] args) throws IOException {
		Gson gson = new Gson();

		Vertex[] graph = gson.fromJson(new FileReader("./graph.json"), Vertex[].class);

		Way[] ways = gson.fromJson(new FileReader("./ways.raw"), Way[].class);

		Map<Long, Vertex> graphMap = new HashMap<>();
		for (Vertex vertex : graph) {
			graphMap.put(vertex.getId(), vertex);
		}

		Map<Long, Way> wayMap = new HashMap<>();
		for (Way way : ways) {
			wayMap.put(way.getId(), way);
		}

		long parent = 0;
		for (Way way : wayMap.values()) {
			if (way.getName().equalsIgnoreCase("ford hall")) {
				parent = way.getId();
			}
		}

		System.out.println("Parent: " + parent);
		for (long id : wayMap.get(parent).getNodes()) {
			System.out.println(id + " = " + graphMap.get(id).getId());
		}

		drawGraph(graph);
	}
	
	
	static void drawGraph(Vertex[] graph) throws IOException {
		int width = 1600;
		int height = 1600;

		BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = (Graphics2D) img.getGraphics();

		float minlat = 44.9670000f;
		float minlon = -93.2490000f;
		float maxlat = 44.9806000f;
		float maxlon = -93.2153000f;

		float scale = Math.min(width / (minlat - maxlat), height / (minlon - maxlon));

		for (Vertex vertex : graph) {
			int x = width - (int) ((minlat - vertex.getLat()) * scale);
			int y = (int) ((minlon - vertex.getLon()) * scale);

			g.setColor(Color.GRAY);
			for(Edge e : vertex.getEdges()) {
				Vertex vertex2 = graph[e.getIndex()];
				int x2 = width - (int) ((minlat - vertex2.getLat()) * scale);// (int) (size * Math.cos(vertex.getLat()) *
				// Math.cos(vertex.getLon()));
				int y2 = (int) ((minlon - vertex2.getLon()) * scale);// (int) (size * Math.cos(vertex.getLat()) *

				g.drawLine(x, y, x2, y2);
			}
			
			g.setColor(Color.RED);
			g.fillRect(x, y, 1, 1);
			
		}
		g.dispose();
		ImageIO.write(img, "png", new File("./graph.png"));
	}
}
