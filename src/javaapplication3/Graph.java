package javaapplication3;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
/**
 * Implements a directed graph.
 * @author trachten
 */

public class Graph {
	// CONSTRUCTOR
	/**
	 * Constructs a graph with the given number of vertices.
	 * @param vertices The number of vertices in the initial graph.
	 */
	public Graph(int vertices) {
		numVertices=vertices;
		edgeList = new Vector<edge>();
                adjacencyList = new HashMap<Integer, Vector<edge>>();
	}

	// CLASSES

	/**
	 * An edge in the graph.
	 */
	public class edge {
		/**
		 * Creates an edge from <from> to <to>
		 * @param from The originating vertex for the edge (between 0 and numVertices-1 inclusive).
		 * @param to The concluding vertex for the edge (between 0 and numVertices-1 inclusive).
		 * @param wt The weight of the edge.
		 * @require wt >=0
		 */
		public edge(int from, int to, int wt) { fromVertex = from; toVertex=to; weight = wt;}

		public int fromVertex; /** This is the vertex from which the edge originates. */
		public int toVertex;   /** This is the vertex to which the edge connects. */
		public int weight;     /** The weight of the edge. */
	}

	// METHODS

	// ... QUERY
	/**
	 * @return true iff there is an edge from vertexA to vertexB
	 */
	/*public boolean adj(int vertexA, int vertexB) {
		for (int ii=0; ii<edgeList.size(); ii++)
			if (edgeList.get(ii).fromVertex == vertexA &&
			edgeList.get(ii).toVertex == vertexB)
				return true;
		return false;
	}*/
        public boolean adj(int vertexA, int vertexB){
            if(adjacencyList.containsKey(vertexA)){                
		for (int ii=0; ii< adjacencyList.get(vertexA).size(); ii++)
			if (adjacencyList.get(vertexA).get(ii).fromVertex == vertexA &&
			adjacencyList.get(vertexA).get(ii).toVertex == vertexB)
				return true;                
                return false;
            }else{
                return false;
            }            
        }

	/**
	 * @param vertexA The originating vertex
	 * @param vertexB The concluding vertex
	 * @return The weight of the directed edge from vertexA to vertexB
	 * @requires The edge from vertexA to vertexB must exist in the graph
	 */
	/*public int getWeight(int vertexA, int vertexB) {
		for (int ii=0; ii<edgeList.size(); ii++)
			if (edgeList.get(ii).fromVertex == vertexA &&
			edgeList.get(ii).toVertex == vertexB)
				return edgeList.get(ii).weight;
		return -1; // no weight found
	}*/
        public int getWeight(int vertexA, int vertexB){
		for (int ii=0; ii< adjacencyList.get(vertexA).size(); ii++)
			if (adjacencyList.get(vertexA).get(ii).fromVertex == vertexA &&
			adjacencyList.get(vertexA).get(ii).toVertex == vertexB)
				return adjacencyList.get(vertexA).get(ii).weight;                
                return -1;            
        }
	/**
	 * @return the number of vertices currently in the graph.
	 */
	public int verts() {
		return numVertices;
	}

	// ... MANIPULATION
	/**
	 * Adds to the graph a directed edge from <fromVertex> to <toVertex> with weight <wt>.
	 * @param fromVertex The originating vertex for the edge.
	 * @param toVertex The concluding vertex for the edge.
	 * @param wt The weight of the edge.
	 */
	/*public void addEdge(int fromVertex, int toVertex, int wt) {
		edgeList.add(new edge(fromVertex, toVertex, wt));
	}*/
        public void addEdge(int fromVertex, int toVertex, int wt){
            if(adjacencyList.containsKey(fromVertex)){
                Vector vector = adjacencyList.get(fromVertex);
                adjacencyList.remove(fromVertex);
                vector.add(new edge(fromVertex, toVertex, wt));
                adjacencyList.put(fromVertex, vector);
            }else{
                Vector vector = new Vector<edge>();
                vector.add(new edge(fromVertex, toVertex, wt));
                adjacencyList.put(fromVertex, vector);
            }
        }
	// DATA FIELDS
	private int numVertices; /** The number of vertices in the graph. */
	private Vector<edge> edgeList;   /** The list of edges for the graph. */
        //make an adjacency list instead of edgelist, then decrease time complexity of finding adjencency vertice from VE to E.
        //Adjacency list is map, key is vertices, value is a vector of adjacency edges. 
        //add-edge method: first find vertices, then add into correspondingly vector.
        private Map<Integer, Vector<edge>> adjacencyList;
}
