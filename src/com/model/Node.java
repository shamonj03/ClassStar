package com.model;

public class Node {

	protected final double x;
	protected final double y;
	protected final long id;
	
	public Node(long id, double x, double y) {
		this.id = id;
		this.x = x;
		this.y = y;
	}
	
	public long getId() {
		return id;
	}
	
	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Node) {
			Node other = (Node) obj;
			return x == other.x && y == other.y;
		}
		return super.equals(obj);
	}
	
	@Override
	public String toString() {
		return "Node(" + x + ", " + y + ")";
	}
}
