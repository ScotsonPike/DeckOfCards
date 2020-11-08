import java.util.ArrayList;

public class Hand {
	
	public ArrayList<Card> hand;
	
	public Hand() {
		hand = new ArrayList<>();
	}
	
	public void add(Card card) {
		hand.add(card);
	}
	
	public Card get(int index) {
		return hand.get(index);		
	}
	
	public void remove(int index) {
		hand.remove(index);
	}
}
