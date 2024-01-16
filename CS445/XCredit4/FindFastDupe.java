import java.io.*;
import java.util.*;

public class FindFastDupe
{	public static void main (String[] args) throws Exception
	{
		BufferedReader stringFile = new BufferedReader( new FileReader( args[0] ) );
        TreeSet<String> words = new TreeSet<String>();
		while(stringFile.ready())
		{
			String line =  stringFile.readLine();
			if(words.contains(line))
			{
				System.out.println("FIRST DUPE '"+ line +"' FOUND AT LINE "+ (words.size()+1));
				break;
			}
			else
			{
				words.add(line);
			}
		}
		if(stringFile.readLine() == null)
		{
			System.out.println("NO DUPES FOUND");
		}
        



	} // END MAIN
} // END XC4