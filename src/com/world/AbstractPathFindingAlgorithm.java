package com.world;
import java.util.ArrayList;

public interface AbstractPathFindingAlgorithm<T extends Node> {

	public ArrayList<T> findPath(T start, T end);
	
}
