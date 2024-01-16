import java.io.*;
import java.util.*;

public class CDLL_JosephusList<T> {
	private CDLL_Node<T> head; // pointer to the front (first) element of the list
	private int count = 0;

	// private Scanner kbd = new Scanner(System.in); // FOR DEBUGGING. See
	// executeRitual() method
	public CDLL_JosephusList() {
		head = null; // compiler does this anyway. just for emphasis
	}

	// LOAD LINKED LIST FORM INCOMING FILE

	public CDLL_JosephusList(String infileName) throws Exception {
		BufferedReader infile = new BufferedReader(new FileReader(infileName));
		while (infile.ready()) {
			@SuppressWarnings("unchecked")
			T data = (T) infile.readLine(); // CAST CUASES WARNING (WHICH WE CONVENIENTLY SUPPRESS)
			insertAtTail(data);
		}
		infile.close();
	}

	// ########################## Y O U W R I T E / F I L L I N T H E S E M E T H O
	// D S ########################

	// TACK ON NEW NODE AT END OF LIST
	@SuppressWarnings("unchecked")
	public void insertAtTail(T data) {
		CDLL_Node<T> newNode = new CDLL_Node<T>(data);
		if (head == null) {
			head = newNode;
			newNode.setNext(head);
			newNode.setPrev(head);
			return;
		}
		newNode.setNext(head);
		newNode.setPrev(head.getPrev());
		head.getPrev().setNext(newNode);
		head.setPrev(newNode);

	}

	public int size() {
		CDLL_Node<T> cur = head;
		int count = 0;
		do{
			cur = cur.getNext();
			count++;
		}while(cur != head);
		return count;
	}

	// RETURN REF TO THE FIRST NODE CONTAINING KEY. ELSE RETURN NULL
	public CDLL_Node<T> search(T key) {

		CDLL_Node<T> cur = head;
		if(head == null)
		{
			return null;
		}
		do{
			cur = cur.getNext();
			if(cur.getData().equals(key))
			{
				return cur;
			}	
		}while( cur != head);
		return null;
	}
	

	// RETURNS CONATENATION OF CLOCKWISE TRAVERSAL
	@SuppressWarnings("unchecked")
	public String toString() {
		String toString = "";
		CDLL_Node<T> last = head.getPrev();
		for (CDLL_Node<T> cur = head; cur.getNext() != head; cur = cur.getNext()) {
			toString += cur;
			if (cur.getNext() != head)
				toString += "<=>";
		}
		toString += last;
		return toString;

	}

	void removeNode(CDLL_Node<T> deadNode) {
		deadNode.getNext().setPrev(deadNode.getPrev());
		deadNode.getPrev().setNext(deadNode.getNext());
	}

	public void executeRitual(T first2Bdeleted, int skipCount) {
		if (size() <= 1)
			return;
		CDLL_Node<T> curr = search(first2Bdeleted);
		if (curr == null)
			return;

		// OK THERE ARE AT LEAST 2 NODES AND CURR IS SITING ON first2Bdeleted
		do {
			CDLL_Node<T> deadNode = curr;
			T deadName = deadNode.getData();

			// ** println( "stopping on curr.data to delete curr.data");
			System.out.println("stopping on " + curr.getData() + " to delete " + curr.getData());
			// BEFORE DOING ACTUAL DELETE DO THESE TWO THINGS

			// 1: you gotta move that curr off of the deadNode.
			// if skipCount positive do curr=curr.next esle do curr=curr.prev
			if (skipCount > 0)
				curr = curr.getNext();
			else
				curr = curr.getPrev();
			// 2: check to see if HEAD is pointing to the deadnode.
			// If so make head=curr
			if (head == deadNode)
				head = curr;
			// NOW DELETE THE DEADNODE
			removeNode(deadNode);
			// println("deleted. list now: + toString() ); // toString prints the
			System.out.println("deleted. list now: " + toString());
			// if the list size has reached 1 return YOU ARE DONE. RETURN RIGHT HERE
			if (size() == 1)
				return;
			// ** println("resuming at curr.data, skipping curr.data + skipCount-1 nodes
			// CLOCKWISE/COUNTERWISE after");
			if (skipCount > 0)
				System.out.println(
						"resuming at " + curr.getData() + ", skipping " + curr.data + " + " + (skipCount - 1) + " nodes CLOCKWISE after");
			else
				System.out.println("resuming at " + curr.getData() + ", skipping " + curr.data + " + "  + Math.abs(skipCount + 1)
						+ " nodes COUNTERCLOCKWISE after");
			// write loop that advances curr pointer skipCount times (be sure of CLOCKWISE
			// or COUNTER)
			if (skipCount > 0)
				for (int i = 0; i < skipCount; i++)
					curr = curr.getNext();
			else
				for (int i = 0; i > skipCount; i--)
					curr = curr.getPrev();
			// OPTIONAL HERE FOR DEBUGGING TO MAKE IT STOP AT BOTTOM OF LOOP
			//Scanner kbd = new Scanner(System.in);
			//String junk = kbd.nextLine();

		} while (size() > 1); // ACTUALLY COULD BE WHILE (TRUE) SINCE WE RETURN AS SOON AS SIZE READES 1

	}

	public class CDLL_Node<T> {
		private T data;
		private CDLL_Node<T> prev, next; // EACH CDLL_Node PTS TO ITS PREV & NEXT

		public CDLL_Node() {
			this(null, null, null); // 3 FIELDS TO INIT
		}

		public CDLL_Node(T data) {
			this(data, null, null);
		}

		public CDLL_Node(T data, CDLL_Node<T> prev, CDLL_Node<T> next) {
			setData(data);
			setPrev(prev);
			setNext(next);
		}

		public T getData() {
			return data;
		}

		public CDLL_Node<T> getPrev() {
			return prev;
		}

		public CDLL_Node<T> getNext() {
			return next;
		}

		public void setData(T data) {
			this.data = data;
		}

		public void setNext(CDLL_Node<T> next) {
			this.next = next;
		}

		public void setPrev(CDLL_Node<T> prev) {
			this.prev = prev;
		}

		public String toString() {
			return "" + getData();
		}

	} // EOF

} // END CDLL_LIST CLASS
