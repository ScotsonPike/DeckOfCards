import java.io.IOException;
import java.util.Scanner;

public class Game {
	
	Deck deck;
	Player player;
	static int handSize = 3;
	
	public Game(Deck deck, Player player) {
		this.deck = deck;
		this.player = player;
	}
	
	public void play(){
		System.out.println("-- NEW GAME STARTED -- WELCOME! --");
		Scanner scanner = new Scanner(System.in);
		while(true) {
			System.out.println("Swap a card in your hand with a face up card.");
			System.out.println("Do this in the form 'number space number'.");
			System.out.println("For example, to swap the first card in your hand with the second face up card, type '1 2'.");
			System.out.println("When you are done swapping, type 'exit': ");
			String input = scanner.nextLine();			
			String exit = "exit";
			if(input.equalsIgnoreCase(exit)) {
				break;
			}
			else {				
				String[] inputArr = input.split(" ");
				int handIndex = Integer.parseInt(inputArr[0]);
				int faceUpIndex = Integer.parseInt(inputArr[1]);
				changeCard((handIndex - 1), (faceUpIndex - 1));		
			}			
			player.printHand("hand");
			player.printHand("faceUp");			
		}		
		while(player.cardsRemaining() > 0) {
			System.out.println("Please Type: ");
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
		player.printHand("hand");
		player.printHand("faceUp");	
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
