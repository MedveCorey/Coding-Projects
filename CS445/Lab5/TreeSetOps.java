import java.io.*;
import java.util.*;

import javax.lang.model.element.Element;

public class TreeSetOps
{
	public static void main( String args[] ) throws Exception
	{
		BufferedReader infile1 = new BufferedReader( new FileReader( args[0] ) );
		BufferedReader infile2 = new BufferedReader( new FileReader( args[1] ) );

		TreeSet<String> set1 = loadSet( infile1 );
		TreeSet<String> set2 = loadSet( infile2 );
		printSet( "set1: ",set1 );
		printSet( "set2: ",set2 );

		TreeSet<String> union = union( set1, set2 );
		printSet( "\nunion: ", union );

		TreeSet<String> intersection = intersection( set1, set2 );
		printSet( "\nintersection: ",intersection );

		TreeSet<String> difference = difference( set1, set2 );
		printSet( "\ndifference: ",difference );

		TreeSet<String> xor = xor( set1, set2 );
		printSet("\nxor: ", xor );

		System.out.println( "\nSets Echoed after operations.");

		printSet( "set1: ", set1 );
		printSet( "set2: ", set2 );

	}// END MAIN

	// Y O U    W R I T E   T H I S     M E T H O D 

	static TreeSet<String> loadSet( BufferedReader infile ) throws Exception
	{
        TreeSet<String>loadSet = new TreeSet<String>();
		while(infile.ready())
		{
			String string_to_add =infile.readLine();
			if(loadSet.isEmpty())
			{
				loadSet.add(string_to_add);
			}
			if(!loadSet.contains(string_to_add))
			{
				loadSet.add(string_to_add);
			}
		}
		infile.close();
        return loadSet; 
	}

	// Y O U    W R I T E   T H I S     M E T H O D 
	static void printSet( String caption, TreeSet<String> set )
	{
		System.out.print(caption);
		for(String s: set)
			System.out.print(" "+s);
		System.out.println();
	}

	// Y O U    W R I T E   T H I S     M E T H O D 
	static TreeSet<String> union( TreeSet<String> set1, TreeSet<String> set2 )
	{
        TreeSet<String> union = new TreeSet<>();
		union.addAll(set1);
		union.addAll(set2);
		return union; 
	}

	static TreeSet<String> intersection( TreeSet<String> set1, TreeSet<String> set2 )
	{
        TreeSet<String> intersect = new TreeSet<>();
		intersect.addAll(set1);
		intersect.retainAll(set2);
		return intersect; 
	}

	static TreeSet<String> difference( TreeSet<String> set1, TreeSet<String> set2 )
	{
		TreeSet<String> difference = new TreeSet<>();
		difference.addAll(set1);
		difference.removeAll(set2);
		return difference;
	}

	static TreeSet<String> xor( TreeSet<String> set1, TreeSet<String> set2 )
	{
		return difference(union(set1, set2), intersection(set1, set2)); 
	}


} // END CLASS