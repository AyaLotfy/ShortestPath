package eg.edu.alexu.csd.filestructure.graphs;

public class Edge  {

	private int from;
	private int to;
	private int weight;

	public Edge(int from, int to, int weight) {
		this.from = from;
		this.to = to;
		this.weight = weight;
	}


	public int getFrom() {
		return from;
	}

	public void setFrom(Integer from) {
		this.from = from;
	}

	public int getTo() {
		return to;
	}

	public void setTo(Integer to) {
		this.to = to;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

}