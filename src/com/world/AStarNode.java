package com.world;

import java.util.Comparator;

public class AStarNode implements Comparable<AStarNode> {

	private long id;
	private AStarNode cameFromId;
	private double gScore;
	private double fScore;

	public AStarNode(long id) {
		this.id = id;
		this.cameFromId = null;
		this.gScore = -Integer.MAX_VALUE;
		this.fScore = -Integer.MAX_VALUE;
	}

	public void setCameFromId(AStarNode cameFromId) {
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

	public long getId() {
		return id;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof AStarNode) {
			AStarNode other = (AStarNode) obj;
			return other.getId() == getId();
		}
		return super.equals(obj);
	}


	@Override
	public int compareTo(AStarNode o) {
		return Double.compare(getfScore(), o.getfScore());
	}

}
