package javaapplication3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

import javaapplication3.Graph;

public class JavaApplication3 {

	/**
	 * A test of the Dijkstra algorithm.
	 * Should print out node i with priority i.  Priority sum = 10.
	 */
	public static void test1() {
		Graph test = new Graph(5);
		test.addEdge(0, 1, 1);
		test.addEdge(1, 2, 1);
		test.addEdge(2, 3, 1);
		test.addEdge(3, 4, 1);
		Dijkstra foo = new Dijkstra(test);

		priorityQueue result = foo.ComputeShortestPaths(0);
		System.out.println(result.printQueue());
		System.out.println("Priority sum "+result.prioritySum()+"\n\n\n");
	}

	/**
	 * A test of the Dijkstra algorithm.
	 * Should print each node with priority 1, except node 2 that has priority 0.
	 * Priority sum = 4.
	 */
	public static void test2() {
		Graph test = new Graph(5);
		test.addEdge(2, 0, 1);
		test.addEdge(2, 1, 1);
		test.addEdge(2, 3, 1);
		test.addEdge(2, 4, 1);
		Dijkstra foo = new Dijkstra(test);

		priorityQueue result = foo.ComputeShortestPaths(2);
		System.out.println(result.printQueue());
		System.out.println("Priority sum "+result.prioritySum()+"\n\n\n");
	}

	/**
	 * Runs a random test of the Dijkstra code corresponding to the given test number.
	 * @param testNum The number of the test to run.  Giving the same number should result in the same test.
	 */
	public static int randomTest(int testNum) {
		Random rand = new Random(testNum);

		// Create the graph
		int verts = testNum;
		Graph test = new Graph(verts);
		// ... create a trivial spanning tree
		for (int ii=0; ii<verts-1; ii++)
			test.addEdge(ii, ii+1, 3);
		test.addEdge(verts-1,0,3);
		// ... create the rest of the graph
		for (int ii=0; ii< verts * verts / 10; ii++) {
			int vertexA = rand.nextInt(verts);
			int vertexB = rand.nextInt(verts);
			if (!test.adj(vertexA, vertexB))
				test.addEdge(vertexA, vertexB, rand.nextInt(verts)); // random edge with random weight
		}

		// Run Dijkstra's algorithm
		Dijkstra foo = new Dijkstra(test);
		priorityQueue result = foo.ComputeShortestPaths(rand.nextInt(verts)); // from a random source

		// Return the sum of the resulting priorities
		return result.prioritySum();

	}

	/**
	 * @param args
	 * @throws IOException 
	 * @throws NumberFormatException 
	 */
	public static void main(String[] args) throws NumberFormatException, IOException {
		int BUID;

		test1(); test2();
		System.out.print("Please enter the last four digits of your BUID:  ");
		BufferedReader in = new BufferedReader( new InputStreamReader (System.in));
		BUID = 10 + Integer.parseInt(in.readLine()) % 10; // bring it to a number between 10 and 19

		for (int ii=0; ii<20; ii++, BUID*=1.45)
			System.out.println(BUID+": "+randomTest(BUID));

	}

}
