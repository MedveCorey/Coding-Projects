import java.io.*;
import java.util.*;

public class L2_SetOps
{
	public static void main( String args[] ) throws Exception
	{
		BufferedReader infile1 = new BufferedReader( new FileReader( args[0] ) );
		BufferedReader infile2 = new BufferedReader( new FileReader( args[1] ) );

		String[] set1 = loadSet( infile1 );
		Arrays.sort( set1 );
		String[] set2 = loadSet( infile2 );
		Arrays.sort( set2 );
		printSet( "set1: ",set1 );
		printSet( "set2: ",set2 );

		String[] union = union( set1, set2 );
		Arrays.sort( union );
		printSet( "\nunion: ", union );


		String[] intersection = intersection( set1, set2 );
		Arrays.sort( intersection );
		printSet( "\nintersection: ",intersection );

		String[] difference = difference( set1, set2 );
		Arrays.sort( difference );
		printSet( "\ndifference: ",difference );

		String[] xor = xor( set1, set2 );
		Arrays.sort( xor );
		printSet("\nxor: ", xor );

		System.out.println( "\nSets Echoed after operations.");

		printSet( "set1: ", set1 );
		printSet( "set2: ", set2 );

	}// END MAIN

	// USE AS GIVEN - DO NOT MODIFY
	// CAVEAT: This method will not work *correctly* until you write a working doubleLength() method.

	static String[] loadSet( BufferedReader infile ) throws Exception
	{
		final int INITIAL_LENGTH = 5;
		int count=0;
		String[] set = new String[INITIAL_LENGTH];
		while( infile.ready() )
		{
				if (count >= set.length)
					set = doubleLength( set );
				set[ count++ ] = infile.readLine();
		}
		infile.close();
		return trimArray( set, count );
	}

	// USE AS GIVEN - DO NOT MODIFY
	static void printSet( String caption, String [] set )
	{
		System.out.print( caption );
		for ( String s : set )
			System.out.print( s + " " );
		System.out.println();
	}


	/* ###############################################################
		For each of the following set operations you must execute the following steps:
		1) dimension an array that is just big enough to handle the largest possible set for that operation.
		2) add the appropriate elements to the array as the operation prescribes.
		3) before returning the array, resize it to the exact size as the number of elements in it.
	*/

	static String[] union( String[] set1, String[] set2 )
	{
		
		String[] union = new String[set1.length+set2.length];
		for(int i = 0; i<set1.length;i++){
			union[i]=set1[i];
		}
		int count=set1.length;
		for(String s:set2){
			if(!contains(s, set1)){
				union[count++]=s;
			}

		}
		return trimArray(union, count);
	}

	static String[] intersection( String[] set1, String[] set2 )
	{
		String[] intersect = new String[set1.length+set2.length];
		int count = 0;
		for(String s:set2){
			if(contains(s,set1)){
				intersect[count++]=s;
			}
		}
		return trimArray(intersect, count);
	}

	static String[] difference( String[] set1, String[] set2 )
	{
		String[] difference = new String[set1.length+set2.length];
		int count = 0;
		for(String s:set1){
			if(!contains(s,set2)){
				difference[count++]=s;
			}
		}
		return trimArray(difference, count);
	}

	static String[] xor( String[] set1, String[] set2 )
	{
		
		return difference(union(set1, set2), intersection(set1, set2));
	}

	// return an array of length 2x with all data from the old array stored in the new array
	static String[] doubleLength( String[] old )
	{
		String[] newArray = new String[old.length*2];
		for(int i = 0; i<old.length;i++){
			newArray[i]=old[i];
		}
		return newArray;
	}

	// return an array of length==count with all data from the old array stored in the new array
	static String[] trimArray( String[] old, int count )
	{
		String[] trimmedArray =new String[count];
		System.arraycopy(old, 0, trimmedArray, 0, trimmedArray.length);
		return trimmedArray;
	}
	static boolean contains(String s,String[] set){
		for(String w:set){
			if(w.equals(s)){
				return true;
			}
		}return false;
	}

} // END CLASS