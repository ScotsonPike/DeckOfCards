import java.util.ArrayList;
import java.util.Collections;

/* The Player class contains a player's three hands, hand, faceUp
 * and faceDown are ArrayLists that contain a set of three cards each.
 * These arrays are accessed by the Game class to add cards and print 
 * the objects inside.
 * */
public class Player {

	private ArrayList<Card> hand;
	private ArrayList<Card> faceUp;
	private ArrayList<Card> faceDown;
	private String name;

	public Player(String name) {
		this.setName(name);
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
		if (type.contains("hand") || type.contains("faceUp")) {
			System.out.println(type + ": ");
			switch (type) {
			case "hand":
				for (Card card : hand) {
					System.out.print(card.getType() + " of " + card.getSuit() + " | ");
				}
				System.out.println();
				System.out.println();
				break;
			case "faceUp":
				for (Card card : faceUp) {
					System.out.print(card.getType() + " of " + card.getSuit() + " | ");
				}
				System.out.println();
				System.out.println();
				break;
			}
		} else {
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

	public boolean canPlayerPlay(Card topCard, boolean playBelow) {
		for (Card card : hand) {
			if (playBelow == true) {
				if (card.getNumber() <= topCard.getNumber()) {
					return true;
				}
			} else if (card.getNumber() >= topCard.getNumber()) {
				return true;
			}
		}
		return false;
	}

	public int getHandSize() {
		return hand.size();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void printName() {
		System.out.println(getName());
	}
}
