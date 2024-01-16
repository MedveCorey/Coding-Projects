import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class Pacs
{	public static void main( String args[] ) throws Exception
	{	BufferedReader memberToPacsFile = new BufferedReader(new FileReader( "member2Pacs.txt"));
		BufferedReader AllPacsFile= new BufferedReader(new FileReader("allPacs.txt"));
		TreeSet<String> allPacs= new TreeSet<String>();
		while( AllPacsFile.ready())
			allPacs.add(AllPacsFile.readLine());			
		TreeMap<String, TreeSet<String>> pacToMembers = new TreeMap<String, TreeSet<String>>(); // THE MAP THAT GETS PRINTED
		for(String s: allPacs)
			pacToMembers.put(s, new TreeSet<String>());
		while(memberToPacsFile.ready())
	   	{
			String[] split_str = memberToPacsFile.readLine().split(" ");
			for(int i = 1; i < split_str.length; i++)
			{
				TreeSet<String> members = pacToMembers.get(split_str[i]);
				members.add(split_str[0]);
				pacToMembers.put(split_str[i], members);			
			}
	   	}
	   for (Entry<String, TreeSet<String>> entry : pacToMembers.entrySet()) 
	   {
			System.out.print(entry.getKey() +" ");
			TreeSet<String> value =  entry.getValue();
			for(String s: value)
			{
				System.out.print(s+ " ");
			}
			System.out.println();
			
	   }
   }
 // END MAIN
} // CLASS