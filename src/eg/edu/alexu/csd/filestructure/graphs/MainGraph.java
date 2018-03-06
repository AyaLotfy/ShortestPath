package eg.edu.alexu.csd.filestructure.graphs;

import java.io.File;

public class MainGraph {
	public static void main(String[] args) {
		Graph graph = new Graph();
		File file = new File("file");
		graph.readGraph(file);
		System.out.println(graph.graph.size());
		System.out.println("-----------------------------------------------------------------");

		System.out.println(graph.size());
		System.out.println("-----------------------------------------------------------------");

		System.out.println(graph.getVertices());
		System.out.println("-----------------------------------------------------------------");

		for (int i = 0; i < graph.edges.size(); i++) {
			System.out.print(graph.edges.get(i).getFrom() + " ");
			System.out.print(graph.edges.get(i).getTo() + " ");
			System.out.println(graph.edges.get(i).getWeight() + " ");
		}
		System.out.println("-----------------------------------------------------------------");

		for (int i = 0; i < graph.graph.size(); i++) {
			for (int j = 0; j < graph.graph.get(i).size(); j++) {
				System.out.print(graph.graph.get(i).get(j).getFrom() + " ");
				System.out.print(graph.graph.get(i).get(j).getTo() + " ");
				System.out.print(graph.graph.get(i).get(j).getWeight() + "          ");
			}
			System.out.println();
		}
		System.out.println("-----------------------------------------------------------------");

		graph.getNeighbors(0);
		for (int i = 0; i < graph.neighbors.size(); i++) {
			System.out.print(graph.neighbors.get(i) + " ");
		}
		System.out.println();
		System.out.println("-----------------------------------------------------------------");

		graph.getNeighbors(1);
		for (int i = 0; i < graph.neighbors.size(); i++) {
			System.out.print(graph.neighbors.get(i) + " ");
		}
		System.out.println();
		System.out.println("-----------------------------------------------------------------");

		graph.getNeighbors(2);
		for (int i = 0; i < graph.neighbors.size(); i++) {
			System.out.print(graph.neighbors.get(i) + " ");
		}
		System.out.println();
		System.out.println("-----------------------------------------------------------------");

		graph.getNeighbors(3);
		for (int i = 0; i < graph.neighbors.size(); i++) {
			System.out.print(graph.neighbors.get(i) + " ");
		}
		System.out.println();
		System.out.println("-----------------------------------------------------------------");

		graph.getNeighbors(4);
		for (int i = 0; i < graph.neighbors.size(); i++) {
			System.out.print(graph.neighbors.get(i) + " ");
		}
		System.out.println();
		System.out.println("-----------------------------------------------------------------");

		graph.getNeighbors(5);
		for (int i = 0; i < graph.neighbors.size(); i++) {
			System.out.print(graph.neighbors.get(i) + " ");
		}
		System.out.println();
		System.out.println("-----------------------------------------------------------------");

	}
}
