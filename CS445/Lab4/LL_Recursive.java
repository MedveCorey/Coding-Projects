import java.io.*;
import java.util.*;

import javax.lang.model.util.ElementScanner14;

public class LL_Recursive<T>
{
	private Node<T> head;  // pointer to the front (first) element of the list

	public LL_Recursive()
	{
		head = null; // compiler does this anyway. just for emphasis
	}

	// INSERTS NEW NODE AT FRONT PUSHING EXISTING NODES BACK ONE PLACE
	public void insertAtFront(T data)
	{
		head = new Node<T>(data,head);
	}

 	// MUST USE (CALL) search() METHOD IN THIS CODE TO DETERMINE THE RETURN VALUE
 	// NO LOOPS ALLOWED NO RECURSION ALLOWED.  THE SEARCH WILL BE RECURSIVE THOUGH
 	public boolean contains( T key )
 	{
		if(search(key)==null)
		{
			return false;
		}
 		return search(key).data.equals(key);
	}

	// #####  W R I T E  (O R  R E-W R I T E)  T H E S E   M E T H O D S   R E C U R S I V E L Y ####
	// YOU MUST WRITE THIS USING RECURSION
	// YOU WILL HAVE TO WRITE A HELPER METHOD. THE HELPER WILL BE RECURSIVE
	public void insertAtTail(T data)
	{
		if(head == null)
        {
            insertAtFront(data);
            return;
        }
		insertAtTailHelper(data,head);
	}

	private void insertAtTailHelper(T data, Node<T> cur) {
		if(cur.next == null)
		{
			cur.next = new Node<T>(data,null); 
			return;
		}
		insertAtTailHelper(data,cur.next);
	}

	// YOU MUST WRITE THIS USING RECURSION
	// YOU WILL HAVE TO WRITE A HELPER METHOD. THE HELPER WILL BE RECURSIVE
	public int size()
	{
		return sizeHelper(head);
	}

	private int sizeHelper(Node<T> head) {
        if(head==null)
        {
            return 0;
        }
        return 1 + sizeHelper(head.next);
    }

    // USE THE TOSTRING AS OUR PRINT.  ***MUST RE-WRITE USING RECURSION***
	// YOU WILL HAVE TO WRITE A HELPER METHOD. THE HELPER WILL BE RECURSIVE
	public String toString()
	{
		String toString = "";
		toString = toString + toStringHelper(head);
		toString = toString.substring(0, toString.length()-1);
		return toString;
	}
	private String toStringHelper(Node<T> head)
	{
		if(head==null)
		{
			return " ";	
		}
		else if (head.next != null){
			return head.data + " -> " +toStringHelper(head.next);
		}
		else{
			return head.data+"\n";
		}	
	}

	// MUST BE RECURSIVE. YOU WILL HAVE TO WRITE A HELPER METHOD. THE HELPER WILL BE RECURSIVE
	public Node<T> search( T key )
	{
		return searchHelper(key, head);
	}

    private Node<T> searchHelper(T key, Node<T> head) {
		//System.out.println(head);
		if(head == null)
		{
			return null;
		}
    	if(head.data.equals(key))
		{
			return head;
		}
		return searchHelper(key, head.next);
    }
} //END OF LL_Recursive CLASS


///////////////////////////////////////////////////////////////////////////////////////////////////

class Node<T>
{ T data;
  Node<T> next;
  Node() { this( null, null ); }
  Node(T data){this( data, null ); }
  Node(T data, Node<T> next) { this.data=data; this.next=next; }
  public String toString() { return ""+data; }
} //END OF NODE CLASS