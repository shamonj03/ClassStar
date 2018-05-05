package com.world;
import java.util.ArrayList;

import com.model.Node;
import com.model.Vertex;

public interface AbstractPathFindingAlgorithm {

	public ArrayList<Node> findPath(Vertex start, Vertex end);
	
}
