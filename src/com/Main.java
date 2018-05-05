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
import com.world.AbstractPathFindingAlgorithm;
import com.world.BasicAstarPathFindingAlgorithm;
import com.world.HaversineHeuristic;

public class Main {

	private static final int WIDTH = 2808;
	private static final int HEIGHT = 2808;

	private static final float MIN_LAT = 44.9666000f;
	private static final float MIN_LON = -93.2514000f;
	private static final float MAX_LAT = 44.9794000f;
	private static final float MAX_LON = -93.2177000f;
	private static final float SCALE_X =  WIDTH / (MIN_LAT - MAX_LAT);
	private static final float SCALE_Y = HEIGHT / (MIN_LON - MAX_LON);
	
	public static final float RESOLUTION = SCALE_X / SCALE_Y;
	

	
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

		long start = 0;
		long end = 0;
		for (Way way : wayMap.values()) {
			if (way.getName().equalsIgnoreCase("ford hall")) {
				start = way.getId();
			}
			if (way.getName().equalsIgnoreCase("robert h. bruininks hall")) {
				end = way.getId();
			}
		}

		System.out.println("Parent: " + start);
		long first = wayMap.get(start).getNodes()[0];
		
		long second = wayMap.get(end).getNodes()[0];
		for (long id : wayMap.get(end).getNodes()) {
			System.out.println(id + " = " + graphMap.get(id).getId());
		}

		BufferedImage img = drawGraph(graph);
		Graphics2D g = (Graphics2D) img.getGraphics();
		
		Vertex vStart = graphMap.get(first);
		Vertex vEnd = graphMap.get(second);
		
		g.setColor(Color.CYAN);
		g.fillRect(getXFromLat(vStart.getLat()) - 8, getYFromLon(vStart.getLon()) - 8, 16, 16);
		g.fillRect(getXFromLat(vEnd.getLat()) - 8, getYFromLon(vEnd.getLon()) - 8, 16, 16);
		
		AbstractPathFindingAlgorithm pathing = new BasicAstarPathFindingAlgorithm(new HaversineHeuristic());
		pathing.findPath(vStart, vEnd);

		g.dispose();
		ImageIO.write(img, "png", new File("./graph.png"));
		
		//int WIDTH2 = (int) (800 * RESOLUTION);
		//int HEIGHT2 = (int) (800 * RESOLUTION);
		//System.out.println(WIDTH2);
		//System.out.println(HEIGHT2);
	}
	
	
	private static BufferedImage drawGraph(Vertex[] graph) throws IOException {
		BufferedImage img = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = (Graphics2D) img.getGraphics();


		for (Vertex vertex : graph) {
			int x = getXFromLat(vertex.getLat());
			int y = getYFromLon(vertex.getLon());

			g.setColor(Color.GRAY);
			for(Edge e : vertex.getEdges()) {
				Vertex vertex2 = graph[e.getIndex()];
				g.drawLine(x, y, getXFromLat(vertex2.getLat()), getYFromLon(vertex2.getLon()));
			}
			
			g.setColor(Color.RED);
			g.fillRect(x, y, 1, 1);
			
		}
		//g.dispose();
		return img;
	}
	
	private static int getXFromLat(double lat) {
		return (int) ((MIN_LAT - lat) * SCALE_X);
	}
	
	private static int getYFromLon(double lon) {
		return (int) ((MIN_LON - lon) * SCALE_Y);
	}
	
}
