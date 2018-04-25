package com.world;
import java.util.ArrayList;

import com.model.Node;

public interface AbstractPathFindingAlgorithm<T extends Node> {

	public ArrayList<T> findPath(T start, T end);
	
}
