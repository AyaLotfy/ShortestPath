package eg.edu.alexu.csd.filestructure.graphs;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class Graph implements IGraph {
	private int numberOfVertices;
	private int numberOfEdges;
	private ArrayList<Integer> vertices;
	ArrayList<Edge> edges;
	ArrayList<ArrayList<Edge>> graph;
	ArrayList<Integer> neighbors = new ArrayList<Integer>();
	private int[] parent;
	private int[] distances2;
	private int[] distancesbell;
	Dijkstra d = new Dijkstra();
	GraphOfVertices<Integer> graph2 = new GraphOfVertices<>(true);
	Vertex<Integer> sourceVertex;
	private BellmanFord bell = new BellmanFord();

	public Graph() {
		numberOfVertices = 0;
		numberOfEdges = 0;
		vertices = new ArrayList<Integer>();
		edges = new ArrayList<Edge>();
		graph = new ArrayList<ArrayList<Edge>>();
	}

	@Override
	public void readGraph(File file) {
		// TODO Auto-generated method stub
		try {
			Scanner scan = new Scanner(file);
			if (file.length() == 0) {
				throw new RuntimeException(
						"file is empty");/*
											 * check if file is empty
											 */
			} else {
				if (scan.hasNext()) {
					numberOfVertices = scan.nextInt();
				}
				Boolean[] visited = new Boolean[numberOfVertices];/*
																	 * this
																	 * array to
																	 * check if
																	 * the
																	 * vertex is
																	 * put in
																	 * array
																	 * contains
																	 * all
																	 * vertices
																	 * or not
																	 */
				for (int i = 0; i < numberOfVertices; i++) {
					visited[i] = false;
				}
				if (scan.hasNext()) {
					numberOfEdges = scan.nextInt();
				}
				vertices = new ArrayList<Integer>();
				edges = new ArrayList<Edge>();
				graph = new ArrayList<ArrayList<Edge>>();
				for (int i = 0; i < numberOfVertices; i++) {
					graph.add(new ArrayList<Edge>());
				}
				int iteration = 0;
				for (int i = 0; i < numberOfEdges && scan.hasNext(); i++) {
					int vertex1 = scan.nextInt();
					int vertex2 = scan.nextInt();
					if (vertex1 < 0
							|| vertex1 >= numberOfVertices) {/*
																 * check if
																 * vertex is
																 * positive
																 * number and
																 * less than
																 * vertices
																 * number
																 */
						throw new RuntimeException("the number should be positive number and < number of vertices");
					}
					if (vertex2 < 0 || vertex2 >= numberOfVertices) {
						throw new RuntimeException("the number should be positive number and < number of vertices");
					}
					int weight = scan.nextInt();
					edges.add(new Edge(vertex1, vertex2, weight));
					graph.get(vertex1).add(new Edge(vertex1, vertex2, weight));
					/*
					 * arrayList of arrayList to put all edges have the same
					 * vertex in arrayList its index is equal vertex
					 */
					iteration++;
					if (!visited[vertex1]) {
						vertices.add(vertex1);
						visited[vertex1] = true;
					}
					if (!visited[vertex2]) {
						vertices.add(vertex2);
						visited[vertex2] = true;
					}
				}
				// commented by aia
				// if (iteration != numberOfEdges) {/*
				// * check if graph is
				// * complete or not
				// */
				// throw new RuntimeException("graph is wrong");
				// }
			}
			scan.close();
		} catch (FileNotFoundException e) {
			throw new RuntimeException("file is not found");
		}
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return edges.size();
	}

	@Override
	public ArrayList<Integer> getVertices() {
		// TODO Auto-generated method stub
		return vertices;
	}

	@Override
	public ArrayList<Integer> getNeighbors(int v) {
		// TODO Auto-generated method stub
		neighbors = new ArrayList<Integer>();
		for (int i = 0; i < graph.get(v).size(); i++) {
			neighbors.add(graph.get(v).get(i).getTo());
		}
		return neighbors;
	}

	int wetArr[];

	@Override
	public void runDijkstra(int src, int[] distances) {
		for (int i = 0; i < edges.size(); i++) {
			graph2.addEdge(edges.get(i).getFrom(), edges.get(i).getTo(), edges.get(i).getWeight());

		}
		sourceVertex = graph2.getVertex(src);
		d.shortestPath(graph2, sourceVertex);
		distances = d.distanceArrayGraph;
		distances2 = d.distanceArrayGraph;
		wetArr = new int[distances.length];
		wetArr = d.wet;

	}

	@Override
	public ArrayList<Integer> getDijkstraProcessedOrder() {

		// d.shortestPath(graph2, sourceVertex);
		parent = d.processedOrder;

		return null;
	}

	@Override
	public boolean runBellmanFord(int src, int[] distances) {
		// TODO Auto-generated method stub
		boolean bellman = bell.bellman(src, distances, edges, getVertices().size());
		distancesbell = bell.returnDistance();

		return bellman;
	}

	void debug(String s) {
		System.out.println(s);
	}

	void debugint(int i) {
		System.out.println(i);
	}

	String outArrStr[];
	ArrayList<Integer> numsOut = new ArrayList<>();

	void readOut(File file) {
		int countline = 0;
		try {
			Scanner scan = new Scanner(file);
			outArrStr = new String[(int) file.length()];

			if (file.length() == 0) {
				throw new RuntimeException(
						"file is empty");/*
											 * check if file is empty
											 */
			} else {
				if (scan.hasNextLine()) {
					String s = scan.nextLine();
					outArrStr[countline] = s;
					countline++;
					String[] ss = s.split(" ");
					for (int i = 0; i < ss.length; i++) {
						numsOut.add(Integer.parseInt(ss[i]));
					}

				}
			}

			while (scan.hasNextLine()) {
				String s = scan.nextLine();
				outArrStr[countline] = s;
				countline++;
				String[] ss = s.split(" ");
				for (int i = 0; i < ss.length; i++) {
					numsOut.add(Integer.parseInt(ss[i]));
				}
			}

			// for (int i = 0; i < numsOut.size(); i++) {
			// System.out.println(numsOut.get(i));
			// }
			scan.close();

		} catch (Exception e) {
			System.out.println("not found file ouia");
		}

	}

	// ouia
	public static void main(String[] args) {
		Graph graph = new Graph();
		File fileout = new File("E:\\Downloads Folder\\DownLoad\\dijkstra_1_output.txt");
		graph.readOut(fileout);

		File file = new File("dijkstra_1.txt");
		graph.readGraph(file);
		int distances[] = new int[graph.numberOfVertices];
		graph.runDijkstra(0, distances);
		graph.debug("dij");

		for (int i = 0; i < graph.numberOfVertices; i++) {
			if (graph.distances2[i] != graph.numsOut.get(i)) {
				graph.debug(graph.distances2[i] + "* *" + graph.numsOut.get(i));
			} else {
				 graph.debug("doneeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
			}
		}
		graph.getDijkstraProcessedOrder();
		graph.debug("predec");
		for (int i = 0; i < graph.numberOfVertices; i++) {
			int m = i + graph.numberOfVertices;
			if (graph.parent[i] != graph.numsOut.get(m)) {

				if (graph.wetArr[graph.parent[i]] == graph.wetArr[graph.numsOut.get(m)]) {
					System.out.println("doneeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
				} else {
					graph.debug("ouia " + graph.parent[i] + "* *" + "fin " + graph.numsOut.get(m));
				}

			} else {
				 graph.debug("doneeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");

			}
		}

	}

}