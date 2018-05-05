package com.world;
import java.util.ArrayList;

import com.model.Vertex;

public interface AbstractPathFindingAlgorithm {

	public ArrayList<Vertex> findPath(Vertex start, Vertex end);
	
}
