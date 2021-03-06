package com.world;

import java.util.Comparator;

import com.model.Vertex;

public class AStarNode implements Comparable<AStarNode> {

	private Vertex vertex;
	private AStarNode cameFromId;
	private double gScore;
	private double fScore;

	public AStarNode(Vertex vertex) {
		this.vertex = vertex;
		this.cameFromId = null;
		this.gScore = Integer.MAX_VALUE;
		this.fScore = Integer.MAX_VALUE;
	}

	public void setCameFrom(AStarNode cameFromId) {
		this.cameFromId = cameFromId;
	}

	public void setfScore(double fScore) {
		this.fScore = fScore;
	}

	public void setgScore(double gScore) {
		this.gScore = gScore;
	}

	public AStarNode getCameFrom() {
		return cameFromId;
	}

	public double getfScore() {
		return fScore;
	}

	public double getgScore() {
		return gScore;
	}
	
	public Vertex getVertex() {
		return vertex;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof AStarNode) {
			AStarNode other = (AStarNode) obj;
			return other.getVertex().getId() == getVertex().getId();
		}
		return super.equals(obj);
	}


	@Override
	public int compareTo(AStarNode o) {
		if(getfScore() > o.getfScore()) {
			return 1;
		} else if(getfScore() < o.getfScore()) {
			return -1;
		}
		return 0;
	}

}