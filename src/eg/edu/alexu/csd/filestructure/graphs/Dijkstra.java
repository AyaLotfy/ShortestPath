package eg.edu.alexu.csd.filestructure.graphs;

import java.util.HashMap;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Dijkstra {

	int distanceArrayGraph[];
	int[] parentGraph;
	int[] processedOrder;
	int[] wet;

	public void shortestPath(GraphOfVertices<Integer> graph, Vertex<Integer> sourceVertex) {
		BinaryMinHeap<Vertex<Integer>> minHeap = new BinaryMinHeap<>();

		Map<Vertex<Integer>, Integer> distance = new HashMap<>();

		Map<Vertex<Integer>, Vertex<Integer>> parent = new HashMap<>();
		processedOrder = new int[graph.getAllVertex().size()];
		distanceArrayGraph = new int[graph.getAllVertex().size()];
		wet = new int[graph.getAllVertex().size()];

		for (Vertex<Integer> vertex : graph.getAllVertex()) {
			minHeap.add(Integer.MAX_VALUE, vertex);
		}

		// for (int i = 0; i < processedOrder.length; i++) {
		// processedOrder[i]= i;
		// }

		minHeap.decrease(sourceVertex, 0);

		distance.put(sourceVertex, 0);
		int countProcess = 0;
		long test = sourceVertex.getId();
		// processedOrder[countProcess] = (int) sourceVertex.getId();
		// countProcess++;
		parent.put(sourceVertex, null);

		while (!minHeap.empty()) {
			BinaryMinHeap<Vertex<Integer>>.Node heapNode;
			heapNode = minHeap.extractMinNode();
			Vertex<Integer> current;
			current = heapNode.key;
			// System.out.println("id"+current.getId()+);

			// while (heapNode.weight < 0) {
			// minHeap.add(Integer.MAX_VALUE, current);
			// current = heapNode.key;
			//
			// }

			distance.put(current, heapNode.weight);
			processedOrder[countProcess] = (int) current.getId();
			countProcess++;
			
			//System.out.println("weight "+heapNode.weight +" id "+ current.getId());
			wet[(int) current.getId()] =heapNode.weight;
			
			
			for (Edge2<Integer> edge : current.getEdges()) {

				Vertex<Integer> adjacent = getVertexForEdge(current, edge);

				if (!minHeap.containsData(adjacent)) {
					continue;
				}

				int newDistance = distance.get(current) + edge.getWeight();

				if (minHeap.getWeight(adjacent) > newDistance && newDistance >= 0) {
					minHeap.decrease(adjacent, newDistance);
//					if (processedOrder[(int) adjacent.getId()] != 0 && (int) adjacent.getId() != 0) {
//						processedOrder[(int) adjacent.getId()] = (int) current.getId();
//					}
					parent.put(adjacent, current);
				}
			}
		}
		Set setOfKeys = distance.keySet();

		Iterator iterator = setOfKeys.iterator();
		while (iterator.hasNext()) {
			Vertex<Integer> key = (Vertex<Integer>) iterator.next();

			int value = distance.get(key);
			distanceArrayGraph[(int) key.getId()] = value;
		}

		return;
	}

	private Vertex<Integer> getVertexForEdge(Vertex<Integer> v, Edge2<Integer> e) {
		return e.getVertex1().equals(v) ? e.getVertex2() : e.getVertex1();
	}

	public static void main(String[] args) {
		System.out.println(Integer.MAX_VALUE);
	}
}
