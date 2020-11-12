import java.util.ArrayList;
import java.util.Collections;

/* The Player class contains a player's three hands, hand, faceUp
 * and faceDown are ArrayLists that contain a set of three cards each.
 * These arrays are accessed by the Game class to add cards and print 
 * the objects inside.
 * */
public class Player {
	
	private static ArrayList<Card> hand;
	private static ArrayList<Card> faceUp;
	private static ArrayList<Card> faceDown;
	
	public Player() {		
		hand = new ArrayList<>();
		faceUp = new ArrayList<>();
		faceDown = new ArrayList<>();
	}
	
	public void addCardToHand(Card card) {
		hand.add(card);
	}
	
	public ArrayList<Card> getHand() {
		return hand;
	}
	
	public ArrayList<Card> getFaceUp() {
		return faceUp;
	}
	
	public ArrayList<Card> getFaceDown() {
		return faceDown;
	}
	
	
	public void printHand(String type) {
		if(type.contains("hand") || type.contains("faceUp")) {
			System.out.println(type + ": ");
			switch(type) {
				case "hand":
					for(Card card : hand) {
						System.out.print(card.type + " of " + card.suit + " | ");
					}
					System.out.println();
					System.out.println();
					break;
				case "faceUp":					
					for(Card card : faceUp) {						
						System.out.print(card.type + " of " + card.suit + " | ");						
					}
					System.out.println();
					System.out.println();
					break;
			}
		}
		else {
			System.out.println("wrong type in printHand method");			
		}				
	}
	
	public int cardsRemaining() {
		return hand.size();
	}
	
	public Card getCardFromHand(int index) {
		return hand.get(index);
	}
	
	public void removeCardFromHand(int index) {
		hand.remove(index);
	}
	
	public void sortHand() {
		Collections.sort(hand);
	}
	
	public void addCardToFaceUp(Card card) {
		faceUp.add(card);
	}
	
	public void removeCardFromFaceUp(int index) {
		faceUp.remove(index);
	}
	
	public void addCardToFaceDown(Card card) {
		faceDown.add(card);
	}
	
	public void removeCardFromFaceDown(int index) {
		faceDown.remove(index);
	}

	public boolean canPlayerPlay(Card topCard) {
		for(Card card : hand) {
			if(card.number >= topCard.number) {
				return true;
			}
		}
		return false;
	}
	
	public int getHandSize() {
		return hand.size();
	}
}
