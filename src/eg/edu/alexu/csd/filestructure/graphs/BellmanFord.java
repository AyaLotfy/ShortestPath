package eg.edu.alexu.csd.filestructure.graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class BellmanFord {
	 private Map< Integer, Integer> verticesParent = new HashMap< Integer, Integer>();
	 private Map< Integer, Integer> verticesDistance = new HashMap< Integer, Integer>();
	 private boolean cycle = true;
	 private int[] distanceArr ;
	public boolean bellman(int src , int[] distances, ArrayList<Edge> edges,int numberOfVertices) {
		distanceArr = new int [numberOfVertices];
		for (int i = 0; i < numberOfVertices; i++) {
			if (i == src) {
				verticesDistance.put(i,  0);
				verticesParent.put(i, null);
			}else{
				verticesDistance.put(i,  Integer.MAX_VALUE);
				verticesParent.put(i, null);
			}
		} 
		for (int i = 0; i < numberOfVertices-1; i++) {
			for (Edge edge : edges) {
				int v =edge.getTo();
				int u = edge.getFrom();
				if(verticesDistance.get(v) > verticesDistance.get(u)+edge.getWeight()  &&  verticesDistance.get(u) != Integer.MAX_VALUE){
					verticesDistance.put(v,verticesDistance.get(u)+edge.getWeight());
					distanceArr[v]=verticesDistance.get(v);
					verticesParent.put(v, u);
				}
				 
			}
			
		}
		// check for negative cycle
		for (Edge edge : edges) {
			int v =edge.getTo();
			int u = edge.getFrom();
			if(verticesDistance.get(v) > verticesDistance.get(u)+ edge.getWeight() &&  verticesDistance.get(u) != Integer.MAX_VALUE){
			cycle = false;
			break;
			}
			 
		}
	 
		for (int i = 0; i < distanceArr.length; i++) {
			System.out.println(distanceArr[i]);
		}
		return  cycle;
	}
	public int [] returnDistance() {
		return distanceArr;
	}

}