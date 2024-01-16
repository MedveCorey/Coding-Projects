import java.io.*;
import java.util.*;

import javax.lang.model.util.ElementScanner14;

// NOTICE THE "<T extends Comparable<T>>"
// using <T extends Comparable<T>> in here means compiler wont let the code in main send in any T type
// that does not implement Comparable.  Now we do not have to cast the incoming key to a Comparable
// in our insertInOrder() method. Compiler now lets us call .compareTo off the dot on the incoming key
// without throwing an error.

public class LinkedList<T extends Comparable<T>> {
	private Node<T> head; // pointer to the front (first) element of the list

	public LinkedList() {
		head = null; // compiler does this anyway. just for emphasis
	}

	// LOAD LINKED LIST FROM INCOMING FILE
	@SuppressWarnings("unchecked")
	public LinkedList(String fileName, boolean orderedFlag) {
		head = null;
		try {
			BufferedReader infile = new BufferedReader(new FileReader(fileName));
			while (infile.ready()) {
				if (orderedFlag)
					insertInOrder((T) infile.readLine()); // WILL INSERT EACH ELEM INTO IT'S SORTED POSITION
				else
					insertAtTail((T) infile.readLine()); // TACK EVERY NEWELEM ONTO END OF LIST. ORIGINAL ORDER
															// PRESERVED
			}
			infile.close();
		} catch (Exception e) {
			System.out.println("FATAL ERROR CAUGHT IN C'TOR: " + e);
			System.exit(0);
		}
	}

	// -------------------------------------------------------------

	// inserts new elem at front of list - pushing old elements back one place

	public void insertAtFront(T data) {
		head = new Node<T>(data, head);
	}

	// we use toString as our print

	public String toString() {
		String toString = "";

		for (Node<T> curr = head; curr != null; curr = curr.next) {
			toString += curr.data; // WE ASSUME OUR T TYPE HAS toString() DEFINED
			if (curr.next != null)
				toString += " ";
		}

		return toString;
	}

	// ########################## Y O U W R I T E T H E S E M E T H O D S
	// ########################

	public int size() // OF COURSE MORE EFFICIENT to KEEP COUNTER BUT YOU MUST WRITE LOOP
	{
		int count = 0;
		Node<T> cur = head;
		while (cur != null) {
			cur = cur.next;
			count++;
		}
		return count;
	}

	public boolean empty() {
		return size() == 0;
	}

	public boolean contains(T key) {
		return search(key) != null;
	}

	public Node<T> search(T key) {
		Node<T> cur = head;
		// base case
		if (cur == null) {
			return null;
		}
		while (cur != null) {
			if (cur.data.equals(key)) {
				return cur;
			}
			cur = cur.next;
		}
		return null;
	}

	// TACK A NEW NODE (CABOOSE) ONTO THE END OF THE LIST
	public void insertAtTail(T data) {
		// edge case AKA base case IF THE LIST IS EMPTY

		if (head == null) {
			insertAtFront(data);
			return;
		}

		Node<T> cur = head;

		while (cur.next != null) {
			cur = cur.next;
		}

		// cur is now pointing at the tail node

		cur.next = new Node<T>(data, null);
	}

	// IF YOU DEFINE <T> at the top of this class as <T implements Comparable>
	// YOU DO NOT HAVE TO CAST TO COMPARABLE AND YOU DO NOT NEED TO SUPPRESS
	public void insertInOrder(T data) {
		Node<T> cur;

		// Special case for head node
		if (head == null || head.data.compareTo(data) >= 0) {
			insertAtFront(data);
		} else {

			// Locate the node before point of insertion.
			cur = head;

			while (cur.next != null && cur.next.data.compareTo(data) < 0)
				cur = cur.next;

			cur.next = new Node<T>(data, cur.next);
		}
	}

	public boolean remove(T key) {
		Node<T> nodeToRemove = search(key);
		Node<T> cur = head;
		Node<T> previous = null;
		Node<T> nextNode = cur.next;
		if (nodeToRemove == null) {
			return false;
		}
		while (cur != nodeToRemove) {
			previous = cur;
			cur = cur.next;
			if (cur == null) {
				nextNode = null;
			} else {
				nextNode = cur.next;
			}
		}
		if (cur == head) {
			removeAtFront();
			return true;
		}
		if (nextNode == null) {
			removeAtTail();
			return true;
		} else if (cur != null) {
			previous.next = nextNode;
			cur = null;
			return true;
		}
		return false;
	}

	public boolean removeAtTail() // RETURNS TRUE IF THERE WAS NODE TO REMOVE
	{
		Node<T> cur = head;
		Node<T> previous = null;
		if (head == null) {
			return false;
		}
		while (cur.next != null) {
			previous = cur;
			cur = cur.next;
		}
		if (cur.next == head.next) {
			removeAtFront();
			return true;
		}
		previous.next = null;
		return true;
	}

	public boolean removeAtFront() // RETURNS TRUE IF THERE WAS NODE TO REMOVE
	{
		if (head == null) {
			return false;
		}
		head = head.next;
		return true;
	}

	public LinkedList<T> union(LinkedList<T> other) {
		LinkedList<T> union = new LinkedList<T>();
		Node<T> cur = head;
		for (int i = 0; i < this.size(); i++) {
			union.insertAtTail(cur.data);
			cur = cur.next;
		}
		cur = other.head;
		for (int i = 0; i < other.size(); i++) {

			if (!union.contains(cur.data)) {
				union.insertInOrder(cur.data);
			}
			cur = cur.next;
		}

		return union;
	}

	public LinkedList<T> inter(LinkedList<T> other) {
		LinkedList<T> inter = new LinkedList<T>();
		Node<T> cur = other.head;
		for (int i = 0; i < other.size(); i++) {
			if (contains(cur.data)) {
				inter.insertInOrder(cur.data);
			}
			cur = cur.next;
		}
		return inter;
	}

	public LinkedList<T> diff(LinkedList<T> other) {
		LinkedList<T> diff = new LinkedList<T>();
		Node<T> cur = head;
		for (int i = 0; i < size(); i++) {
			if (!other.contains(cur.data)) {
				diff.insertInOrder(cur.data);
			}
			cur = cur.next;
		}

		return diff;
	}

	public LinkedList<T> xor(LinkedList<T> other) {
		return union(other).diff(inter(other));
	}

} // END LINKEDLIST CLASS

class Node<T extends Comparable<T>> // tells compiler our incoming T type implements Comparable

{
	T data;
	Node<T> next;

	Node() {
		this(null, null);
	}

	Node(T data) {
		this(data, null);
	}

	Node(T data, Node<T> next) {
		this.data = data;
		this.next = next;
	}

	public String toString() {
		return "" + data;
	}

} // EOF
