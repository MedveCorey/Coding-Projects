/* This class was borrowed and modified as needed with permission from it's original author
   Mark Stelhik ( http:///www.cs.cmu.edu/~mjs ).  You can find Mark's original presentation of
   this material in the links to his S-01 15111,  and F-01 15113 courses on his home page.
*/

import java.io.*;
import java.util.*;

public class Graph 
{
	private final int NO_EDGE = -1; // all real edges are positive
	private int G[][];              // will point to a 2D array to hold our graph data

	private int numEdges;
	public Graph( String graphFileName ) throws Exception  // since readFild doesn't handle them either
	{
		loadGraphFile( graphFileName );
	}

	///////////////////////////////////// LOAD GRAPH FILE //////////////////////////////////////////
	//
	// FIRST NUMBER IN GRAPH FILE IS THE SQUARE DIMENSION OF OUR 2D ARRAY
	// THE REST OF THE LINES EACH CONTAIN A TRIPLET <ROW,COL,WEIGHT> REPRESENTING AN EDGE IN THE GRAPH

	private void loadGraphFile( String graphFileName ) throws Exception
	{
		Scanner graphFile = new Scanner( new File( graphFileName ) );

		int dimension = graphFile.nextInt();   	// THE OF OUR N x N GRAPH
		G = new int[dimension][dimension]; 		// N x N ARRAY OF ZEROS
		numEdges=0;

		// WRITE A LOOP THAT PUTS NO_EDGE VALUE EVERYWHERE EXCPT ON THE DIAGONAL
		for(int row = 0; row < G.length; row++)
		{
			for(int col = 0; col < G[row].length; col++)
			{
				if(row == col)
				{
					G[row][col] = 0;
				}
				else
				{
					G[row][col] = NO_EDGE;
				}
			}
		}
		// NOW LOOP THRU THE FILE READING EACH TRIPLET row col weight FROM THE LINE
		// USE row & col AS WHERE TO STORE THE weight
		// i.e. g[row][col] = w;

		while ( graphFile.hasNextInt() )
		{
			int row = graphFile.nextInt();
			int col = graphFile.nextInt();
			int w = graphFile.nextInt();
			addEdge(row,col,w);
		}

	} // END readGraphFile

	private void addEdge( int r, int c, int w )
	{
		G[r][c] = w;
		++numEdges; // only this method adds edges so we do increment counter here only
	}
	
  private boolean hasEdge(int fromNode, int toNode)
  {
    if(G[fromNode][toNode]>0)
	{
		return true;
	} 
	return false;
  }

	// IN DEGREE IS NUMBER OF ROADS INTO THIS CITY
	// NODE IS THE ROW COL#. IN DEGREE IS HOW MANY POSITIVE NUMBERS IN THAT COL
	private int inDegree(int node)
	{
		int in = 0;
		for(int row = 0; row < G.length; row++)
		{
			if(hasEdge(row, node))
			{
				in++;
			}
		}
		return in;
	}

	// OUT DEGREE IS NUMBER OF ROADS OUT OF THIS CITY
	// NODE IS THE ROW #. IN DEGREE IS HOW MANY POSITIVE NUMBERS IN THAT ROW
	private int outDegree(int node)
	{
		int out = 0;
		for(int col = 0; col < G[node].length; col++)
		{
			if(hasEdge(node, col))
			{
				out++;
			}
		}
		return out;
	}

	// DEGREE IS TOTAL NUMBER OF ROAD BOTH IN AND OUT OFR THE CITY 
	private int degree(int node)
	{
		return inDegree(node) + outDegree(node);
	}

	// PUBLIC METHODS 
	
	public int maxOutDegree()
	{
		int maxOut = 0;
		for(int row = 0; row < G.length; row++)
		{
			if(outDegree(row) > maxOut)
			{
				maxOut = outDegree(row);
			}
		}
		return maxOut;
	}

	public int maxInDegree()
	{
		int maxIn = 0;
		for(int col = 0; col < G.length; col++)
		{
			if(inDegree(col) > maxIn)
			{
				maxIn = inDegree(col);
			}
		}
		return maxIn;
	}

	public int minOutDegree()
	{
		int minOut = G.length+1;
		for(int row = 0; row < G.length; row++)
		{
			if(outDegree(row) < minOut)
			{
				minOut = outDegree(row);
			}
		}
		return minOut;
	}
	public int minInDegree()
	{
		int minIn = G.length + 1;
		for(int col = 0; col < G.length; col++)
		{
			if(inDegree(col) < minIn)
			{
				minIn = inDegree(col);
			}
		}
		return minIn;
	}	
	
	public int maxDegree()
	{
		int maxD = 0;
		for(int row = 0; row < G.length; row++)
		{
			if(maxD < degree(row))
			{
				maxD = degree(row);
			}
		}
		return maxD;
	}

	public int minDegree()
	{
		int minD = G.length+1;
		for(int row = 0; row < G.length; row++)
		{
			if(minD > degree(row))
			{
				minD = degree(row);
			}
		}
		return minD;
	}
	
	public void removeEdge(int fromNode, int toNode)
	{
		try {
			G[fromNode][toNode] = NO_EDGE;
			
		} catch (Exception e) {
			System.out.println("java.lang.Exception: Non Existent Edge Exception: removeEdge("+fromNode+","+toNode+")");
			System.exit(0);
		}
	}
	
	// TOSTRING
	public String toString()
	{	String the2String = "";
		for (int r=0 ; r < G.length ;++r )
		{
			for ( int c=0 ; c < G[r].length ; ++c )
				the2String += String.format("%3s",G[r][c] + " ");
			the2String += "\n";
		}
		return the2String;
	} // END TOSTRING

} //EOF