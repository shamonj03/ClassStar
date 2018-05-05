package com;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
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
	

	public static Map<Long, Vertex> vertexMap = new HashMap<>();
	public static Map<Long, Way> wayMap = new HashMap<>();
	public static Vertex[] graph;
	
	public static void main(String[] args) throws IOException {
		Gson gson = new Gson();

		/*
		 * Init map data.
		 */
		graph = gson.fromJson(new FileReader("./graph.json"), Vertex[].class);

		Way[] ways = gson.fromJson(new FileReader("./ways.raw"), Way[].class);

		for (Vertex vertex : graph) {
			vertexMap.put(vertex.getId(), vertex);
		}

		for (Way way : ways) {
			wayMap.put(way.getId(), way);
		}

		
		/*
		 * Find some starting nodes based on name.
		 */
		long start = 0;
		long end = 0;
		for (Way way : wayMap.values()) {
			if (way.getName().equalsIgnoreCase("Coffman Memorial Union")) {
				start = way.getId();
			}
			if (way.getName().equalsIgnoreCase("robert h. bruininks hall")) {
				end = way.getId();
			}
		}

		System.out.println("Parent: " + start);
		long first = wayMap.get(start).getNodes()[0];
		long second = wayMap.get(end).getNodes()[0];

		/*
		 * Draw the start and end points
		 */
		BufferedImage img = drawGraph(graph);
		Graphics2D g = (Graphics2D) img.getGraphics();
		
		Vertex vStart = vertexMap.get(first);
		Vertex vEnd = vertexMap.get(second);
		
		g.setColor(Color.CYAN);
		g.fillRect(getXFromLat(vStart.getLat()) - 8, getYFromLon(vStart.getLon()) - 8, 16, 16);
		g.fillRect(getXFromLat(vEnd.getLat()) - 8, getYFromLon(vEnd.getLon()) - 8, 16, 16);
		
		/*
		 * Find and raw the path.
		 */
		AbstractPathFindingAlgorithm pathing = new BasicAstarPathFindingAlgorithm(new HaversineHeuristic());
		List<Vertex> path = pathing.findPath(vStart, vEnd);
		
		Vertex current = path.get(0);
		for(int i = 1; i < path.size(); i++) {
			Vertex v = path.get(i);
			System.out.println(v.getLat() + " " + v.getLon());
			g.drawLine(getXFromLat(current.getLat()), getYFromLon(current.getLon()), getXFromLat(v.getLat()), getYFromLon(v.getLon()));
			current = v;
		}

		/*
		 * Save to file
		 */
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


		g.setColor(Color.GRAY);
		for (Vertex vertex : graph) {
			int x = getXFromLat(vertex.getLat());
			int y = getYFromLon(vertex.getLon());

			for(Edge e : vertex.getEdges()) {
				Vertex vertex2 = graph[e.getIndex()];
				g.drawLine(x, y, getXFromLat(vertex2.getLat()), getYFromLon(vertex2.getLon()));
			}
		}

		g.setColor(Color.RED);
		for (Vertex vertex : graph) {
			int x = getXFromLat(vertex.getLat());
			int y = getYFromLon(vertex.getLon());
			
			g.fillRect(x, y, 1, 1);
			
		}
		//g.dispose();
		return img;
	}
	
	public static int getXFromLat(double lat) {
		return (int) ((MIN_LAT - lat) * SCALE_X);
	}
	
	public static int getYFromLon(double lon) {
		return (int) ((MIN_LON - lon) * SCALE_Y);
	}
	
}
