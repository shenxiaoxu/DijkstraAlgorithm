package javaapplication3;

/**
 * Implements Dijkstra's algorithm, along the lines of CLRS, p. 658.
 */

public class Dijkstra {
	// CONSTRUCTOR
	Dijkstra(Graph G) {
		// initialize data fields
		myGraph = G;
		myQ = new priorityQueue();
	}

	// METHODS

	/**
	 * Initializes algorithm with source <source>, as in CLRS, p. 648.
	 * @param source The source vertex for shortest path calculations.
	 */
	void initSingleSource(int source) {
		for (int ii=0; ii<myGraph.verts(); ii++)
			if (ii==source)
				myQ.addItem(source, 0); // the source vertex
			else
				myQ.addItem(ii, Integer.MAX_VALUE);
	}

	/**
	 * Relax the edge uu->vv, as in CLRS, p. 649
	 */
	void Relax(int uu, int vv) {
		if ((long) myQ.getPriority(vv) > (long) myQ.getPriority(uu) + myGraph.getWeight(uu, vv))
			myQ.setPriority(vv, myQ.getPriority(uu) + myGraph.getWeight(uu, vv));
	}

	/**
	 * Applies the Dijkstra algorithm to the graph.
	 * @param source The source node for shortest path calculations.
	 * @return A priority queue with all items and their shortest path weights from the source.
	 */
	priorityQueue ComputeShortestPaths(int source) {
		initSingleSource(source);

		while (!myQ.isEmpty()) {
			priorityQueue.ItemPri temp = myQ.extractMin(); // EXTRACT-MIN

			int uu = temp.itemNum; // UU = EXTRACT-MIN
			for (int vv=0; vv<myGraph.verts(); vv++) // FOR VV IN ADJ(UU)
				if (myGraph.adj(uu, vv))
					Relax(uu,vv);
		}

		return myQ;         /** The extracted items from the priority queue. */
	}

	// DATA FIELDS
	private Graph myGraph;     /** The graph on which Dijkstra's algorithm will run. */
	private priorityQueue myQ; /** The priority queue used by the algorithm. */
}
