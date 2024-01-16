/*
	PrintSubSets.java
*/
import java.io.*;
import java.util.*;

public class Knapsack
{
	public static void main( String[] args ) throws FileNotFoundException
	{	int[] set = new int[16];
		int target;
		File file = new File(args[0]);
		Scanner scn = new Scanner(file);
		//load file into array
		for(int i =0;i<16;i++)
		{
			set[i] =scn.nextInt();			
		}
		target = scn.nextInt();
		scn.close();
		Arrays.sort(set);
		//check for numbers that will not work in array
		int[] cleaned_set = new int[set.length];
		for(int i=0,j=0;i<set.length;i++)
		{
			if(set[i]<target)
			{
				cleaned_set[j]=set[i];
				j++;
			}
		}
		
		set = cleaned_set;

		for ( int i=0 ; i<Math.pow(2, 16)-1 ; ++i )
		{	
			String subset = " ";
			String bitmap = toBitString( i, set.length );
			int total = 0;
			for(int b=0;b<=set.length-1;b++)
			{
				Character ch = bitmap.charAt(b);
				if(ch.equals('1'))
				{
					subset = set[b]+" "+subset;
					total += set[b];
				}
			}
			if(total == target)
			{
				System.out.println(subset);
			}
			
		}
	} // END MAIN

	// i.e number 31 converted to a width of 5 bits = "11111"
	//     nuumber 7 converted to a width of 5 bits = "00111"
	static String toBitString( int number, int width )
	{
		String bitstring = "";
		while (number > 0)
		{	if (number % 2 == 0)
				bitstring = "0" + bitstring;
			else
				bitstring = "1" + bitstring;
			number /= 2 ;
		}
		while ( bitstring.length() < width )
				bitstring = "0" + bitstring;
		return bitstring;
	}
} // END CLASS