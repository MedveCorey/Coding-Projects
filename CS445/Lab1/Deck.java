/*
	Deck class
*/

import java.util.*;
import java.io.*;

public class Deck
{
	private int[] deck;
	private final int MAX_DECK_SIZE = 20;
	public Deck( int numCards )
	{	if ( numCards%2 != 0 || numCards > MAX_DECK_SIZE ) 
		{
			System.out.format("\nINVALID DECK SIZE: (" + numCards + "). Must be an small even number <= %d\n", MAX_DECK_SIZE);
			System.exit(0);
		}
		deck = new int [numCards];
		for(int i = 0 ; i <deck.length; i++){
			deck[i] = i;
		}

	}
	
	public String toString()
	{
		String deckStr = "";
		for ( int i=0 ; i < deck.length ; ++i )
			deckStr += deck[i] + " ";
		return deckStr;
	}

	// ONLY WORKS ON DECK WITH EVEN NUMBER OF CARDS
	// MODIFIES THE MEMBER ARRAY DECK
	public void inShuffle()
	{
		int[] modDeck = new int[deck.length];
    	int startHalfOne =  0;
    	int startHalfTwo = deck.length / 2;
    	for (int i = 0; i < deck.length; i+=2){
        	modDeck[i] = deck[startHalfTwo];
        	modDeck[i+1] = deck[startHalfOne] ;
        	startHalfOne ++;
        	startHalfTwo ++;
		}
    	for (int i =0; i< deck.length; i++){
			deck[i] = modDeck[i];
		}
        
	}


	// ONLY WORKS ON DECK WITH EVEN NUMBER OF CARDS
	// MODIFIES THE MEMBER ARRAY DECK
	public void outShuffle()
	{
		int[] modDeck = new int[deck.length];
    	int startHalfOne =  0;
    	int startHalfTwo = deck.length / 2;
    	for (int i = 0; i < deck.length; i+=2){
        	modDeck[i] = deck[startHalfOne];
        	modDeck[i+1] = deck[startHalfTwo] ;
        	startHalfOne ++;
        	startHalfTwo ++;
		}
    	for (int i =0; i< deck.length; i++){
			deck[i] = modDeck[i];
		}
	}
	
	// RETURNS TRUE IF DECK IN ORIGINAL SORTED:  0 1 2 3 ...
	public boolean inSortedOrder()
	{
		for(int i = 0; i<deck.length-1;i++){
			if(deck[i]>deck[i+1]){
				return false;
			}
		}return true;
	}
	
}	// END DECK CLASS