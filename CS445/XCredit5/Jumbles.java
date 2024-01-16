import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class Jumbles {
  public static void main(String[] args) throws Exception {
    BufferedReader dictionaryFile = new BufferedReader(new FileReader(args[0]));
    BufferedReader jumblesFile = new BufferedReader(new FileReader(args[1]));
    TreeMap<String, TreeSet<String>> lookupTable = new TreeMap<String, TreeSet<String>>();
    TreeSet<String> jumbles = new TreeSet<String>();
    TreeSet<String> dictWords = new TreeSet<String>();

    while (jumblesFile.ready()) {
      jumbles.add(jumblesFile.readLine());
    }
    jumblesFile.close();
    // create lookup table
    while (dictionaryFile.ready()) {

      String dWord = dictionaryFile.readLine();
      String dCanon = toCanonical(dWord);
      if (!lookupTable.containsKey(dCanon)) {
        lookupTable.put(dCanon, new TreeSet<String>());
      }
      dictWords = lookupTable.get(dCanon);
      dictWords.add(dWord);
      lookupTable.put(dCanon, dictWords);
    }
    dictionaryFile.close();
    for (String jWord : jumbles) {
      System.out.print(jWord);
      String jCanon = toCanonical(jWord);
      if (lookupTable.containsKey(jCanon)) {
        for (String s : lookupTable.get(jCanon)) {
          System.out.print(" " + s);
        }

      }
      System.out.println();
    }
  }

  public static String toCanonical(String s) // assume s = "zebra"
  {
    char[] letters = s.toCharArray(); // letters -> [z][e][b][r][a]
    Arrays.sort(letters); // now letters -gt; [a][b][e][r][z]
    return new String(letters); // read String API it has a constructor that accepts a char array and does the
                                // obvious with it
  }
}
