/*
	THE STARTER FILE FOR 445 XC1  MISSING NUMBER PROBLEM
*/

import java.util.*;
import java.io.*;

public class XC1 // YOU DONT NEED A LINKED LIST CLASS. YOUR MAIN METHOD IS YOUR 'LINKEDLIST' CLASS
{
	public static void main(String[] args) throws FileNotFoundException
	{	
		if ( args.length < 1 ) 
		{
			System.out.println("MISSING INPUT FILE ON CMD LINE\n");
			System.exit(0);
		}
		Scanner infile = new Scanner( new File( args[0] ) );
		
		// DO THE WHOLE PROGRAM INSIDE THIS LOOP
		Node<Integer> head = null;
        int max = 0;
        int natural_sum = 0;
        int list_sum = 0;
        int missing_num = 0;
		while ( infile.hasNextLine() ) // assume line ->  "1 9 2 8 3 10 7 4 6"  (i.e. its missing 5)
		{
			String[]tokens = infile.nextLine().split("\\s+"); // tokens -> ["1"]["9"]["2"]["8"]["3"]["10"]["7"]["4"]["6"]
			for( int i=0 ; i<tokens.length ; ++i )
			{
				int number = Integer.parseInt( tokens[i] ); // convert. "7" to 7
				System.out.print(number+" ");
				head= new Node<Integer>(number, head);
                list_sum +=number;
				if(number>max)
                    max = number;
			}
			natural_sum = ((max*(max+1))/2);
            missing_num = natural_sum - list_sum;
            System.out.print(" missing "+ missing_num);
            System.out.println(); 
            list_sum = 0;
			head=null;
		} // END WHILE THERE IS A ANOTHER LINE IN THE FILE 
	} // END MAIN
} // END CLASS XC1
class Node<Integer>
{   int data;
    Node<Integer> next;
    Node(int data){this( data, null ); }
    Node(int data, Node<Integer> next) { this.data=data; this.next=next; }
    public String toString() { return ""+data; }
} //END OF NODE CLASS
