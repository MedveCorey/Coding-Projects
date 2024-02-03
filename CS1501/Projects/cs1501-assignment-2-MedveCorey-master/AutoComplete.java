
/**
 * An implementation of the AutoCompleteInterface using a DLB Trie.
 */

import java.util.ArrayList;

public class AutoComplete implements AutoCompleteInterface {

  private DLBNode root;
  private StringBuilder currentPrefix;
  private DLBNode currentNode;
  private boolean newWord;
  private DLBNode prevNode;
  // TODO: Add more instance variables as needed

  public AutoComplete() {
    root = null;
    currentPrefix = new StringBuilder();
    currentNode = null;
  }

  /**
   * Adds a word to the dictionary in O(alphabet size*word.length()) time
   * 
   * @param word the String to be added to the dictionary
   * @return true if add is successful, false if word already exists
   * @throws IllegalArgumentException if word is the empty string
   */
  public boolean add(String word) {

    if (word.length() == 0)
      throw new IllegalArgumentException("calls add() with a null key");
    newWord = false;
    root = add(root, word, 0);
    return newWord;
  }

  private DLBNode add(DLBNode x, String key, int pos) {
    DLBNode result = x;
    if (x == null) {
      result = new DLBNode(key.charAt(pos));
      newWord = true;

      if (pos < key.length() - 1) {

        result.child = add(x, key, pos + 1);
        /* TODO: Recurse on the child node */;
        result.child.parent = result;
      } else {
        result.isWord = true; // update

      }

    } else if (x.data == key.charAt(pos)) {

      if (pos < key.length() - 1) {

        /* TODO: Recurse on the child node */;
        result.parent = x;
        result.child = add(x.child, key, pos + 1);

        // result.val = val; //update
      } else {
        if (!result.isWord)
          result.isWord = true;
        newWord = true;
      }

    } else {
      /* TODO: Recurse on the sibling node */;
      result.previousSibling = x;

      result.nextSibling = add(x.nextSibling, key, pos);

    }

    if (newWord && result.data == key.charAt(pos)) {
      result.size++;
    }

    return result;
  }

  /**
   * appends the character c to the current prefix in O(alphabet size) time.
   * This method doesn't modify the dictionary.
   * 
   * @param c: the character to append
   * @return true if the current prefix after appending c is a prefix to a word
   *         in the dictionary and false otherwise
   */
  public boolean advance(char c) {

    // TODO: implement this method
    // create original state for retreat
    prevNode = currentNode;
    currentPrefix.append(c);

    if (currentNode != null) {
      currentNode = currentNode.child;
    }
    if (currentNode == null && (currentPrefix.length() == 1)) {
      currentNode = root;
    }
    // set the current node to the correct value
    while ((currentNode != null && currentNode.data != c)) {
      currentNode = currentNode.nextSibling;

    }

    if (currentNode == null || currentNode.data != c) {
      // no node found
      return false;
    }

    return true;
  }

  /**
   * removes the last character from the current prefix in O(alphabet size) time.
   * This
   * method doesn't modify the dictionary.
   * 
   * @throws IllegalStateException if the current prefix is the empty string
   */
  public void retreat() {
    if (currentPrefix.length() == 0) {
      throw new IllegalStateException("cannot retreat from an empty prefix");
    }
    currentPrefix.deleteCharAt(currentPrefix.length() - 1);
    if(currentPrefix.length() == 0){
      currentNode = null;
      return;
    }else{
      currentNode = prevNode;
      prevNode = prevNode.parent;
      while(prevNode.parent == null){
        prevNode = prevNode.previousSibling;
      }
      prevNode = prevNode.parent;
      currentNode = currentNode.parent;
    }
  }

  /**
   * resets the current prefix to the empty string in O(1) time
   */
  public void reset() {
    currentPrefix.setLength(0);
    currentNode = null;
  }

  /**
   * @return true if the current prefix is a word in the dictionary and false
   *         otherwise. The running time is O(1).
   */
  public boolean isWord() {
    if (currentNode != null && currentNode.isWord) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * adds the current prefix as a word to the dictionary (if not already a word)
   * The running time is O(alphabet size*length of the current prefix).
   */
  public void add() {
    // check if current prefix is already a word in the trie
    if (currentNode != null && currentNode.isWord) {
      return; // current prefix is already in the trie
    }
    root = add(root, currentPrefix.toString(), 0);
  }

  /**
   * @return the number of words in the dictionary that start with the current
   *         prefix (including the current prefix if it is a word). The running
   *         time is
   *         O(1).
   */
  public int getNumberOfPredictions() {
    if (currentNode == null) {
      return 0;
    } else {
      return currentNode.size;
    }
  }

  /**
   * retrieves one word prediction for the current prefix. The running time is
   * O(prediction.length())
   * 
   * @return a String or null if no predictions exist for the current prefix
   */
  public String retrievePrediction() {
    // TODO: implement this method
    StringBuilder predictString = new StringBuilder();

    DLBNode predictNode = currentNode;
    predictString.append(currentPrefix);

    while (predictNode != null && predictNode.size != 0) {
      if (predictNode.isWord) {
        // if predict is word return prediction
        return predictString.toString();
      } else {

        if (predictNode != null) {
          // go to child if none found
          if (predictNode.child != null) {
            predictNode = predictNode.child;

          }

          if (predictNode.nextSibling != null) {
            predictNode = predictNode.nextSibling;

          }
          // append prediction idea
          if (predictNode != null) {
            predictString.append(predictNode.data);
          }

        }
      }
    }
    return null;
  }

  /*
   * ==============================
   * Helper methods for debugging.
   * ==============================
   */

  // print the subtrie rooted at the node at the end of the start String
  public void printTrie(String start) {
    System.out.println("==================== START: DLB Trie Starting from \"" + start + "\" ====================");
    if (start.equals("")) {
      printTrie(root, 0);
    } else {
      DLBNode startNode = getNode(root, start, 0);
      if (startNode != null) {
        printTrie(startNode.child, 0);
      }
    }

    System.out.println("==================== END: DLB Trie Starting from \"" + start + "\" ====================");
  }

  // a helper method for printTrie
  private void printTrie(DLBNode node, int depth) {
    if (node != null) {
      for (int i = 0; i < depth; i++) {
        System.out.print(" ");
      }
      System.out.print(node.data);
      if (node.isWord) {
        System.out.print(" *");
      }
      System.out.println(" (" + node.size + ")");
      printTrie(node.child, depth + 1);
      printTrie(node.nextSibling, depth);
    }
  }

  // return a pointer to the node at the end of the start String
  // in O(start.length() - index)
  private DLBNode getNode(DLBNode node, String start, int index) {
    if (start.length() == 0) {
      return node;
    }
    DLBNode result = node;
    if (node != null) {
      if ((index < start.length() - 1) && (node.data == start.charAt(index))) {
        result = getNode(node.child, start, index + 1);
      } else if ((index == start.length() - 1) && (node.data == start.charAt(index))) {
        result = node;
      } else {
        result = getNode(node.nextSibling, start, index);
      }
    }
    return result;
  }

  // The DLB node class
  private class DLBNode {
    private char data;
    private int size;
    private boolean isWord;
    private DLBNode nextSibling;
    private DLBNode previousSibling;
    private DLBNode child;
    private DLBNode parent;

    private DLBNode(char data) {
      this.data = data;
      size = 0;
      isWord = false;
      nextSibling = previousSibling = child = parent = null;
    }
  }
}
