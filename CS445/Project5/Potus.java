import java.util.*;
import java.util.Map.Entry;
import java.io.*;

public class Potus
{
	public static void main( String[] args )  throws Exception
	{
		BufferedReader state2PresidentsFile = new BufferedReader( new FileReader("state2Presidents.txt") );
		TreeMap<String,TreeSet<String>> state2Presidents= new TreeMap<String,TreeSet<String>>();

		BufferedReader allPresidentsFile = new BufferedReader( new FileReader("allPresidents.txt") );
		TreeSet<String> allPresidents = new TreeSet<String>();

		BufferedReader allStatesFile = new BufferedReader( new FileReader("allStates.txt") );
		TreeSet<String> allStates = new TreeSet<String>();

		TreeMap<String, String> inverseMap = new TreeMap<String, String>();
		TreeSet<String> stateLess = new TreeSet<String>();
		TreeSet<String> presLess = new TreeSet<String>();

		while (state2PresidentsFile.ready())
		{
			String line = state2PresidentsFile.readLine();
			String[] tokens = line.split( " " );
			String state = tokens[0];
			TreeSet<String> presidents = new TreeSet<String>();
			for(int i = 1; i <tokens.length ; ++i)
			{
				presidents.add(tokens[i]);
				inverseMap.put(tokens[i], state);
			}
			state2Presidents.put(state, presidents);
		}
		state2PresidentsFile.close();
	
		while (allPresidentsFile.ready())
		{
			String line = allPresidentsFile.readLine();
			if(!inverseMap.containsKey(line))
				stateLess.add(line);
		}
		allPresidentsFile.close();
		
		while(allStatesFile.ready())
		{
			String line = allStatesFile.readLine();
			if(!state2Presidents.containsKey(line))
				presLess.add(line);
		}
		allStatesFile.close();

		System.out.println( "THESE STATES HAD THESE POTUS BORN IN THEM:\n");
		for(String state: state2Presidents.keySet())
		{
			System.out.print(state + " ");
			for(String president : state2Presidents.get(state))
				System.out.print(president + " ");
			System.out.println("");	
		}

		System.out.println( "\nLIST OF POTUS AND STATE THEY WERE BORN IN:\n");
		for(String invPres: inverseMap.keySet())
		{
			System.out.print(invPres + " ");
			System.out.print(inverseMap.get(invPres) + " ");
			System.out.println("");
		}
	
		System.out.println( "\nTHESE POTUS BORN BEFORE STATES WERE FORMED:\n");
		printTree(stateLess);

		System.out.println( "\nTHESE STATES HAD NO POTUS BORN IN THEM:\n");
		printTree(presLess);	
	} // END MAIN

	//       - - - - - - - - - - -  H E L P E R    M E T H O D S - - - - - - - -
	static void printTree(TreeSet<String> set){
		for(String key: set)
			System.out.println(key);
	}
	
}