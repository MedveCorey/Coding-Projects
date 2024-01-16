import java.io.*;
import java.util.*;

public class GraphLL {
	private final int NO_EDGE = -1; // all real edges are positive
	private Edge[] G; // every G[i] is the head of a linked list, i.e ref to an Edge

	private int numEdges;

	public GraphLL(String graphFileName) throws Exception // since readFild doesn't handle them either
	{
		loadGraphFile(graphFileName);
	}

	///////////////////////////////////// LOAD GRAPH FILE ///////////////////////////////////////////////////////////////////////////////

	private void loadGraphFile(String graphFileName) throws Exception {
		Scanner graphFile = new Scanner(new File(graphFileName));
		int numNodes = graphFile.nextInt();
		G = new Edge[numNodes];
		numEdges = 0;

		// NOW LOOP THRU THE FILE READING EACH TRIPLET row col weight FROM THE LINE
		// DO an insertAtFront using g[SRC] as the head

		while (graphFile.hasNextInt()) {
			int src = graphFile.nextInt();
			int dest = graphFile.nextInt();
			int w = graphFile.nextInt();
			addEdge(src, dest, w);
		}
	} // END readGraphFile

	// GO TO G[src] AND DO INSERTATFRONT ON THAT 'head' POINTER I.E. G[src]
	private void addEdge(int src, int dest, int weight) {
		G[src] = new Edge(dest, weight, G[src]);
		++numEdges;
	}

	private boolean hasEdge(int src, int dest) {
		// GOTO G[src] AND WALK THAT LINKED LIST LOOKING FOR A NODE (EDGE) WITH DEST
		for (Edge curr = G[src]; curr != null; curr = curr.next) {
			if (curr.dest == dest) {
				return true;
			}
		}
		return false;

	}

	private int inDegree(int dest) // # of roads(edges) entering this city (node)
	{ // THE HARDER ONE
		int inDeg = 0;
		for (int r = 0; r < G.length; r++) {
			if (hasEdge(r, dest)) {
				inDeg++;
			}
		}
		return inDeg;
	}

	private int outDegree(int src) // # of roads(edges) leaving this city (src node #)
	{ // THE EASIER ONE
		int outDeg = 0;
		for (Edge cur = G[src]; cur != null; cur = cur.next) {
			outDeg++;
		}
		return outDeg;
	}

	private int degree(int node) // indegree + outdegree of this node #
	{
		return inDegree(node) + outDegree(node);
	}

	// PUBLIC METHODS. THIS CODE COPIED FROM THE GRAPH2D LAB AND USED AS IS. SINCE
	// IT IS NOT
	// DEPENDENT ON UNDERLYING DATA STRUCTURE AND RELIES ONLY ON CALLING THE 3
	// DEGREE FUNCTIONS

	public int maxOutDegree() {
		int maxOutDegree = 0;
		for (int r = 0; r < G.length; r++) {
			if (outDegree(r) > maxOutDegree) {
				maxOutDegree = outDegree(r);
			}
		}
		return maxOutDegree;
	}

	public int maxInDegree() {
		int maxInDegree = 0; // ASSUM 1STNODE HAS LARGEST OUTDEG
		for (int r = 0; r < G.length; r++) {
			if (inDegree(r) > maxInDegree) {
				maxInDegree = inDegree(r);
			}
		}
		return maxInDegree;
	}

	public int minOutDegree() {
		int minOutDegree = G.length + 1; // ASSUM 1STNODE HAS SMALLES OUTDEG
		for (int r = 0; r < G.length; r++) {
			if (outDegree(r) < minOutDegree) {
				minOutDegree = outDegree(r);
			}
		}
		return minOutDegree;
	}

	public int minInDegree() {
		int minInDegree = G.length + 1; // ASSUM 1STNODE HAS LARGEST OUTDEG
		for (int r = 0; r < G.length; r++) {
			if (inDegree(r) < minInDegree) {
				minInDegree = inDegree(r);
			}
		}
		return minInDegree;
	}

	public int maxDegree() {
		int maxDegree = 0;
		for (int r = 0; r < G.length; r++) {
			if (degree(r) > maxDegree) {
				maxDegree = degree(r);
			}
		}
		return maxDegree;
	}

	public int minDegree() {
		int minDegree = G.length + 1;
		for (int r = 0; r < G.length; r++) {
			if (degree(r) < minDegree) {
				minDegree = degree(r);
			}
		}
		return minDegree;
	}

	public void removeEdge(int src, int dest) { 
	try {
		if (G[src] == null) {
			throw new Exception();
		}
		if (src > G.length || src < 0) {
			throw new Exception();
		}
		if (dest < 0 || dest > G.length) {
			throw new Exception();
		}
		remove(src, dest);
	 } catch (Exception e) {
		 System.out.println("java.lang.Exception: Non Existent Edge Exception:removeEdge(" + src + "," + dest + ")");
		 System.exit(0);
	 }
	// ITS NOT THERE THROW AND CATCH AN EXCEPTION (SEE OUTPUT)

	} // E N D R E M O V E D G E

	// TOSTRING
	public String toString() {
		String the2String = "";
		for (int r = 0; r < G.length; r++) {
			the2String += r + ":";
			for (Edge cur = G[r]; cur != null; cur = cur.next) {
				the2String += " -> [" + cur.dest + "," + cur.weight + "]";
			}
			the2String += "\n";
		}
		return the2String;
	} // END TOSTRING
	public boolean remove(int src, int dest) throws Exception {
		
		Edge cur = G[src];
		Edge previous = null;
		Edge nextNode = cur.next;
		if (!hasEdge(src, dest)) {
			throw new Exception();
		}
		while (cur.dest != dest) {
			previous = cur;
			cur = cur.next;
			if (cur == null) {
				nextNode = null;
			} else {
				nextNode = cur.next;
			}
		}
		if (cur == G[src]) {
			removeAtFront(src);
			return true;
		}
		if (nextNode == null) {
			removeAtTail(src);
			return true;
		} else if (cur != null) {
			previous.next = nextNode;
			cur = null;
			return true;
		}
		return false;
	}

	public boolean removeAtTail(int src) // RETURNS TRUE IF THERE WAS NODE TO REMOVE
	{
		Edge cur = G[src];
		Edge previous = null;
		if (G[src] == null) {
			return false;
		}
		while (cur.next != null) {
			previous = cur;
			cur = cur.next;
		}
		if (cur.next == G[src].next) {
			removeAtFront(src);
			return true;
		}
		previous.next = null;
		return true;
	}

	public boolean removeAtFront(int src) // RETURNS TRUE IF THERE WAS NODE TO REMOVE
	{
		if (G[src] == null) {
			return false;
		}
		G[src] = G[src].next;
		return true;
	}
} // E N D G R A P H L L C L A S S

// - - - - Y O U M U S T F I L L I N T H E E D G E (think 'Node') C L A S S - -
// - -
// NOTHING PUBLIC. LET IF DEFAULT TO PACKAGE/PRIVATE LIKE WE DID IN OTHER LL
// ASSIGNMENTS
// SO THAT YOU DONT HAVE TO CALL SETTERS AND GETTERS IN YOUR GRAPHLL CODE.

class Edge {
	int dest, weight;
	Edge next;

	Edge(int dest, int weight, Edge next) {
		this.dest = dest;
		this.weight = weight;
		this.next = next;
	}
}
