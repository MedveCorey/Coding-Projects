/*
	Deck class (for TopCardPlacer class of project #1
*/

import java.util.*;
import java.io.*;

public class Deck
{
	private int[] deck;
	private final int MAX_DECK_SIZE = 30;
	public Deck( int numCards )
	{	if ( numCards%2 != 0 || numCards > MAX_DECK_SIZE ) 
		{
			System.out.format("\nINVALID DECK SIZE: (" + numCards + "). Must be an small even number <= %d\n", MAX_DECK_SIZE);
			System.exit(0);
		}
		deck = new int[numCards];
		for ( int i=0 ; i<numCards ; i++ ) deck[i] = i;
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
	
	public String toBitString( int n ) 
	{
		int remainder = n;
		int power =(int)Math.floor(Math.log(n)/Math.log(2));
		char[] bitString = new char[power + 1];
		int stringPosition = 0;
		while(remainder != 0){
			int value = (int) Math.pow(2,power);
			if(remainder-value >= 0){
				bitString[stringPosition] = '1';
				stringPosition++;
				power--;
				remainder = remainder-value;
			}else{
				bitString[stringPosition] = '0';
				stringPosition++;
				power--;
			}
		}
		StringBuilder bitStringReturn = new StringBuilder();
		bitStringReturn.append(bitString);
		return bitStringReturn.toString(); // REPLACE WITH YOUR CODE
	}
	
}	// END DECK CLASS