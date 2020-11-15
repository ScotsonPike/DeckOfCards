import java.util.ArrayList;
import java.util.Stack;
import java.util.Random;
import java.util.Collections;

public class Deck {
	
	static int numberOfCards = 52;
	private static ArrayList<Card> deck;
	Random rand = new Random();
	
	public Deck() {
		deck = new ArrayList<Card>();
	}
	
	public void createDeck() {
		for(int i = 0; i < 4; i++) {
			Suit suit = null;
			switch(i) {
			  case 0:
			    suit = Suit.Spades;
			    break;
			  case 1:
				suit = Suit.Clubs;
			    break;
			  case 2:
				suit = Suit.Hearts;
				break;
			  case 3:
				suit = Suit.Diamonds;
				break;		    
			}			
			for(int j = 0; j < numberOfCards/4; j++) {
				Card card = new Card(suit, j+1);
				deck.add(card);
			}	
		}	
	}
	
	public void shuffle() {
		Collections.shuffle(deck);
	}
	
	public Card getCard() {
		Card card = deck.get(0);
		deck.remove(0);
		return card;
	}
	
	public int cardsRemaining() {
		return deck.size();
	}
}
