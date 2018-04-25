package com.control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.io.GoogleMapParser;
import com.io.google.types.Result;
import com.io.google.types.Results;
import com.model.ClassInformation;
import com.model.SphereNode;

public class ClassController {

	public final List<ClassInformation> classes = new ArrayList<>();
	
	public void addClass(String name) throws IOException {
		ClassInformation classInfo = new ClassInformation(name);
		
		Results results = GoogleMapParser.parseByName(name);
		
		for(Result result : results.results) {
			if(result.geometry != null) {
				classInfo.setLocation(new SphereNode(result.geometry.location.lat, result.geometry.location.lng));
			}
		}
		
		if(results.status.equals("OK")) {
			classes.add(classInfo);
		}
	}
}
