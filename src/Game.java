import java.util.Scanner;

public class Game {
	
	Deck deck;
	Player player;
	static int handSize = 3;
	
	public Game(Deck deck, Player player) {
		this.deck = deck;
		this.player = player;
	}
	
	public void play() {
		Scanner scanner = new Scanner(System.in);
		while(true) {
			System.out.println("Please select a card (0-2)");
			String input = scanner.nextLine();			
			String input2 = scanner.nextLine();
			if(input.equals("f")) {
				break;
			}
			if(!input.contentEquals("f")) {
				int index = Integer.parseInt(input);
				int index2 = Integer.parseInt(input2);
				changeCard(index, index2);
			}						
			player.printHand();	
			player.printFaceUp();				
		}		
		while(player.cardsRemaining() > 0) {
			String input = scanner.nextLine();
			int index = Integer.parseInt(input);
			playCard(index);
		}
		System.out.println("Game over, man! Game over!");
		scanner.close();
	}
	
	public void deal() {
		deck.createDeck();
		deck.shuffle();
		for(int i = 0; i < handSize; i++) {
			player.addCardToHand(deck.getCard());
		}
		for(int i = 0; i < handSize; i++) {
			player.addCardToFaceUp(deck.getCard());
		}
		for(int i = 0; i < handSize; i++) {
			player.addCardToFaceDown(deck.getCard());
		}
		player.printHand();
		player.printFaceUp();
	}
	
	public void playCard(int index) {
		player.getCardFromHand(index);
		player.removeCardFromHand(index);
		player.addCardToHand(deck.getCard());
		player.sortHand();
	}
	
	public void changeCard(int handIndex, int faceUpIndex) {
		Card handCard = player.getHand().get(handIndex);
		Card faceUpCard = player.getFaceUp().get(faceUpIndex);		
		player.getHand().set(handIndex, faceUpCard);
		player.getFaceUp().set(faceUpIndex, handCard);
	}

}
