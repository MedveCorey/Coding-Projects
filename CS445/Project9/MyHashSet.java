import java.io.*;
import java.util.*;

public class MyHashSet implements HS_Interface {
	private int numBuckets; // changes over life of the hashset due to resizing the array
	private Node[] bucketArray;
	private int size; // total # keys stored in set right now

	// THIS IS A TYPICAL AVERAGE BUCKET SIZE. IF YOU GET A LOT BIGGER THEN YOU ARE
	// MOVING AWAY FROM (1)
	private final int MAX_ACCEPTABLE_AVE_BUCKET_SIZE = 20; // **DO NOT CHANGE THIS NUMBER**

	public MyHashSet(int numBuckets) {
		size = 0;
		this.numBuckets = numBuckets;
		bucketArray = new Node[numBuckets]; // array of linked lists
		System.out.format(
				"IN CONSTRUCTOR: INITIAL TABLE LENGTH=%d RESIZE WILL OCCUR EVERY TIME AVE BUCKET LENGTH EXCEEDS %d\n",
				numBuckets, MAX_ACCEPTABLE_AVE_BUCKET_SIZE);
	}

	public boolean add(String key) {
		// your code here to add the key to the table and ++ your size variable
		if (contains(key)) {
			return false;
		}
		insertInOrder(key);
		++size; // you just added a key to one of the lists
		if (size > MAX_ACCEPTABLE_AVE_BUCKET_SIZE * this.numBuckets)
			upSize_ReHash_AllKeys(); // DOUBLE THE ARRAY .length THEN REHASH ALL KEYS
		return true;
	}

	public boolean contains(String key) {// You hash this key. goto that list. look for this key in that list
		for (Node cur = bucketArray[hashOf(key, numBuckets)]; cur != null; cur = cur.next) {
			if (cur.data.equals(key)) {
				return true;
			}
		}
		return false;
	}

	private void upSize_ReHash_AllKeys() {
		System.out.format("KEYS HASHED=%10d UPSIZING TABLE FROM %8d to %8d REHASHING ALL KEYS\n",
				size, bucketArray.length, bucketArray.length * 2);
		Node[] biggerArray = new Node[bucketArray.length * 2];
		this.numBuckets = biggerArray.length;

		/*
		 * FOR EACH LIST IN THE ARRAY
		 * FOR EACH NODE IN THAT LIST
		 * HASH THAT KEY INTO THE BIGGER TABLE
		 * BE SURE TO USE THE BIGGER .LENGTH AS THE MODULUS
		 */
		for (int i = 0; i < bucketArray.length; i++) {
			for (Node cur = bucketArray[i]; cur != null; cur = cur.next) {
				biggerArray[hashOf(cur.data, numBuckets)] = new Node(cur.data,
						biggerArray[hashOf(cur.data, numBuckets)]);
			}
		}

		bucketArray = biggerArray;
	}

	private int hashOf(String key, int numBuckets) {
		int runningTotal = 2039;
		byte[] values = key.getBytes();
		for (int i = 0; i < values.length; i++) {
			runningTotal = 31 * runningTotal + key.charAt(i);
		}
		return Math.abs(runningTotal) % numBuckets;

	}

	@Override
	public boolean remove(String key) {
		if (!contains(key)) {
			return false;
		}
		if (isEmpty()) {
			return false;
		}
		Node cur = bucketArray[hashOf(key, numBuckets)];
		Node prev = null;
		Node next = cur.next;
		while (!cur.data.equals(key)) {
			prev = cur;
			cur = cur.next;
			if (cur == null) {
				next = null;
			} else {
				next = cur.next;
			}
		}
		if (cur == bucketArray[hashOf(key, numBuckets)]) {
			removeAtFront(key);
			size--;
			return true;
		}
		if (next == null) {
			removeAtTail(key);
			size--;
			return true;
		} else if (cur != null) {
			prev.next = next;
			cur = null;
			size--;
			return true;
		}
		return false;
	}

	private boolean removeAtTail(String key) {
		Node cur = bucketArray[hashOf(key, numBuckets)];
		Node prev = null;
		if (bucketArray[hashOf(key, numBuckets)] == null) {
			return false;
		}
		while (cur.next != null) {
			prev = cur;
			cur = cur.next;
		}
		if (cur.next == bucketArray[hashOf(key, numBuckets)].next) {
			removeAtFront(key);
			return true;
		}
		return false;
	}

	private boolean removeAtFront(String key) {
		if (bucketArray[hashOf(key, numBuckets)] == null) {
			return false;
		}
		bucketArray[hashOf(key, numBuckets)] = bucketArray[hashOf(key, numBuckets)].next;
		return true;
	}
	public void insertInOrder(String key) {
		Node cur;

		// Special case for head node
		if (bucketArray[hashOf(key, numBuckets)] == null || bucketArray[hashOf(key, numBuckets)].data.compareTo(key) >= 0) {
			insertAtFront(key);
		} else {

			// Locate the node before point of insertion.
			cur = bucketArray[hashOf(key, numBuckets)];

			while (cur.next != null && cur.next.data.compareTo(key) < 0)
				cur = cur.next;

			cur.next = new Node(key, cur.next);
		}
	}

	private void insertAtFront(String key) {
		if(bucketArray[hashOf(key, numBuckets)] == null)
		{
			bucketArray[hashOf(key, numBuckets)] = new Node(key, null);	
		}
		bucketArray[hashOf(key, numBuckets)] = new Node(key, bucketArray[hashOf(key, numBuckets)]);
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return size() == 0;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub

	} // END OF UPSIZE & REHASH

} // END MyHashSet CLASS

class Node {
	String data;
	Node next;

	public Node(String data, Node next) {
		this.data = data;
		this.next = next;
	}
}
