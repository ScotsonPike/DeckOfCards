import java.util.ArrayList;
import java.util.Collections;

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
	
	
	public void printHand() {
		System.out.println("Hand:");
		for(Card card : hand) {
			System.out.println(card.type + " of " + card.suit);
		}
	}
	
	public void printFaceUp() {
		System.out.println("Face Up Cards:");
		for(Card card : faceUp) {
			System.out.println(card.type + " of " + card.suit);
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
	
	public void removeCardFromDown(int index) {
		faceDown.remove(index);
	}
}
