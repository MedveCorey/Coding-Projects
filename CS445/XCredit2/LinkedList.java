// 2021 FALL CS 445 LAB #3  STUDENT STARTER FILE

import java.io.*;
import java.util.*;

public class LinkedList<T extends Comparable<T>>
{
	private Node<T> head;  // pointer to the front (first) element of the list
	private Node<T> tail;  // pointer to the last elem of the list ( caboose or tail node)

	public LinkedList()
	{
		head = null; // compiler does this anyway. just for emphasis
		tail = head;
	}

	// USE THE TOSTRING AS OUR PRINT
	public String toString()
	{
		String toString = "";

		for (Node<T> curr = head; curr != null; curr = curr.next)
		{
			toString += curr.data;		// WE ASSUME OUR T TYPE HAS toString() DEFINED
			if (curr.next != null)
				toString += " -> ";
		}

		return (String) (toString + " ");
	}
	
	// ########################## Y O U   W R I T E   T H E S E    M E T H O D S  
	// . . .AND ANY SUPPORTING METHODS YOU NEED FOR THEM 

	// THIS VERSION JUST LOADS THE LISTS FROM THE FILE BEFORE THEY ARE MERGED
	public void insertAtTail( T data )
	{
		if(head == null)
		{
			insertAtFront(data);
			return;
		}
		Node<T> cur = head;
		
		while ( cur.next!= null )
		{
			cur = cur.next;
		}
		cur.next = new Node<T>(data, null);

	}
	public LinkedList<T> merge( LinkedList<T> other )  // think 'sorted union' but only 1 pass allowed
	{
		Node<T> headB = other.head;
		Node<T> headA = this.head;
		LinkedList<T> mergedList = new LinkedList<T>();

		while(headA != null && headB !=null)
		{
			if(headA.data.compareTo(headB.data)<=0)
			{
				mergedList.insertAtTail(headA.data);
				headA = headA.next;
			}
			else
			{
				mergedList.insertAtTail(headB.data);
				headB = headB.next;
			}
		}
		if(headA != null)
		{
			mergedList.insertAtTail(headA.data);
		}
		if(headB != null)
		{
			mergedList.insertAtTail(headB.data);
		}

		return mergedList;
	}

	public void insertAtFront(T data)
	{
		head = new Node<T>(data,head);
	}
} //END OF LINKEDLIST CLASS DEFINITION

// NODE CLASS
 class Node<T>
{
  T data;
  Node<T> next;

  Node(T data)
  {
    this( data, null );
  }
  Node(T data, Node<T> next)
  {
    this.data = data; 
    this.next = next; 
  }

  public String toString()
  {
	  return "" + data; // stringify the data
  } 
	 
} //EOF