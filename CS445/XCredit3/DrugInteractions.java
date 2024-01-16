import java.util.*;
import java.util.Map.Entry;
import java.io.*;

public class DrugInteractions
{
	public static void main( String[] args ) throws Exception
	{
		BufferedReader foodDrug2CategoryFile = new BufferedReader( new FileReader( "foodDrug2Category.txt" ) );
		BufferedReader patient2FoodDrugFile = new BufferedReader( new FileReader( "patient2FoodDrug.txt") );
		BufferedReader dontMixFile = new BufferedReader( new FileReader( "dontMix.txt" ) );
		//Part 1
		TreeMap<String, TreeSet<String>> drugMap = new TreeMap<String, TreeSet<String>>();
		addLine(foodDrug2CategoryFile, drugMap);
		printMap(drugMap);
		System.out.println();
		// Part 2
		TreeMap<String, TreeSet<String>> patientMap = new TreeMap<String, TreeSet<String>>();
		addLine(patient2FoodDrugFile, patientMap);
		printMap(patientMap);
		System.out.println();
		// Part 3
		while(dontMixFile.ready())
		{
			String line = dontMixFile.readLine();
			String[] tokens = line.split(",");
			TreeSet<String> mixingDrugs = new TreeSet<String>();
			Set<Map.Entry<String, TreeSet<String>>> patients = patientMap.entrySet();
			for(Map.Entry<String, TreeSet<String>> patient:patients)
			{
				TreeSet<String> drugsTaken = patient.getValue();
				TreeSet<String> drugTypesTaken = new TreeSet<String>();
				for(String drug: drugsTaken)
				{
					Set<Map.Entry<String, TreeSet<String>>> entries = drugMap.entrySet();
					for(Map.Entry<String, TreeSet<String>> entry:entries)
					{
						if(entry.getValue().contains(drug))
						{
							drugTypesTaken.add(entry.getKey());
						}
					}
				}
				if(drugTypesTaken.contains(tokens[0])&&drugTypesTaken.contains(tokens[1]))
				{
					mixingDrugs.add(patient.getKey());
				}
			}
			dontMixFile.close();
			for(String s: mixingDrugs)
			System.out.println(s);
		}
	} // END MAIN
	static void addLine(BufferedReader file, TreeMap<String, TreeSet<String>> map) throws IOException
	{
		while(file.ready())
		{
			String line = file.readLine();
			String[] tokens = line.split(",");
			String key = tokens[0];
			TreeSet<String> values = new TreeSet<String>();
			for(int i = 1; i < tokens.length;i++)
			{
				values.add(tokens[i]);
			}
			map.put(key, values);
		}
		file.close();
	}
	static void printMap(TreeMap<String, TreeSet<String>> map)
	{
		for(String key: map.keySet())
		{
			System.out.print(key + " ");
			for(String value : map.get(key))
				System.out.print(value + " ");
			System.out.println("");	
		}
	}

} // END CLASS