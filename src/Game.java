import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.IntStream;

/*
 * The Game class loops through he primary methods of the card game
 * through deal(), handSetUp() and play().
 * */

public class Game {
	
	private Deck deck;
	private Player player;
	private static int handSize = 3;
	private ArrayList<Card> cardPile;
	private Scanner scanner;
	private boolean playBelow = false;
	private boolean missAGo = false;
		
	public Game(Deck deck, Player player) {
		// Constructor for Game class
		this.deck = deck;
		this.player = player;		
		cardPile = new ArrayList<>();
		scanner = new Scanner(System.in);
	}
	
	public void deal() {
		// 
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
		cardPile.add(deck.getCard());		
	}
	
	public void handSetUp() {
		//Loop while arranging cards in hand and faceUp cards
		System.out.println("Swap a card in your hand with a face up card.");
		System.out.println("Do this in the form 'number space number'.");
		System.out.println("For example, to swap the first card in your hand with the second face up card, type '1 2'.");
		System.out.println("When you are done swapping, type 'exit': ");
		while(true) {	
			player.printHand("hand");
			player.printHand("faceUp");	
			String input = scanner.nextLine().trim();		
			String exit = "exit";
			if(input.equalsIgnoreCase(exit)) {
				break;
			}
			else {	
				if(input.contains(" ")) {
					String[] inputArr = input.split(" ");
					int handIndex = Integer.parseInt(inputArr[0]);
					int faceUpIndex = Integer.parseInt(inputArr[1]);
					int handSize = player.getHandSize();
					if(between(handIndex, 1, 3) && between(faceUpIndex, 1, handSize)) {
						changeCard((handIndex - 1), (faceUpIndex - 1));	
					}
					else {
						System.out.println("Invalid input");
					}
				}
				else {
					System.out.println("Invalid input");
				}				
			}								
		}		
	}
	
	public void play(){
		// Main game loop method
		System.out.println("-- NEW GAME STARTED -- WELCOME! --");
		while(player.cardsRemaining() > 0) {
			Card topCard = getTopCard();			
			System.out.println("\nCurrent top card in play: " + topCard.printCard());
			player.sortHand();
			player.printHand("hand");
			System.out.println("Select a card to play: ");
			String input = scanner.nextLine();
			int index = Integer.parseInt(input);
			//Card playerCard = selectCard();
			if(between(index, 1, player.getHandSize())) {
				// Input is within hand size range.
				index = index-1;
				Card playerCard = player.getCardFromHand(index);
				if(checkCard(playerCard, topCard)) {
					// Player's hand contains a playable card
					if(playerCard.getMagic() != null) {
						// Card is magic
						magicRules(playerCard, topCard, index);					
					}
					else if(playBelow == true) {
						if(playerCard.getNumber() <= topCard.getNumber()){
							// Card can be played in order.
							playCard(index);
						}
					}
					else if(playerCard.getNumber() >= topCard.getNumber() ) {
						// Card can be played in order.
						playCard(index);
					}
					else {
						// Card is too low to be played
						System.out.println("Too Low");
					}
				}
				else {
					// Player's hand does not contain a card that can be played, player must pick up
					pickUp();
				}
			}
			else {
				System.out.println("Invalid input.");
			}	
			if(topCard.getNumber() != 9) {
				playBelow = false;
			}
			if(topCard.getNumber() != 8) {
				missAGo = false;
			}
		}
		System.out.println("Game over, man! Game over!");
		scanner.close();
	}
	
	private void magicRules(Card playerCard, Card topCard, int index) {		
		if(playerCard.getMagic().cardIsSequential() == false) {
			// Magic card can be played out of order
			switch(playerCard.getMagic()) {
				case startAgain:
					// start cardPile from 2
					playCard(index);
					break;
				case burn:
					// Play a burn card (10), empty cardPile, draw a card and select a new card to play.
					playCard(index);
					cardPile.clear();
					cardPile.add(selectCard());
					break;
				case seeThrough:
					// Play a see-through card (7), card's number is equal to previous card.
					playerCard.setNumber(topCard.getNumber());
					cardPile.add(playerCard);
					player.removeCardFromHand(index);
					if(player.getHandSize() < 3) {
						player.addCardToHand(deck.getCard());
						player.sortHand();
					}	
					break;
			default:
				break;
			}
		}
		else if(playerCard.getNumber() >= topCard.getNumber() ) {
		// Card can be played in order.
			switch(playerCard.getMagic()) {
				case missAGo:
					// Play a miss a go card (8)
					missAGo = true;
					playCard(index);
					break;
				case playBelow:
					// Play a play below card (9)
					playBelow = true;
					playCard(index);
					break;
			default:
				break;
			}
		}
	}
	
	private Card selectCard() {
		// Select any card from the players hand to play.
		Card playerCard = null;
		player.sortHand();
		player.printHand("hand");
		System.out.println("Select a card to play: ");
		String input = scanner.nextLine();
		int index = Integer.parseInt(input);
		while(playerCard == null) {
			if(between(index, 1, player.getHandSize())) {
				// Input is within hand size range.
				index = index-1;
				playerCard = player.getCardFromHand(index);
				player.removeCardFromHand(index);
				player.addCardToHand(deck.getCard());
			}
		}		
		return playerCard;
	}

	public boolean checkCard(Card card, Card topCard) {
		if(card.getMagic() != null) {
			if(card.getMagic().cardIsSequential() == false) {
				return true; //play card
			}			
		}		
		if(player.canPlayerPlay(topCard, playBelow)) {		
			return true; //play card		
		} 
		return false; //pickUp
	}
	
	public void playCard(int index) {
		cardPile.add(player.getCardFromHand(index));
		player.removeCardFromHand(index);
		if(player.getHandSize() < 3) {
			player.addCardToHand(deck.getCard());
			player.sortHand();
		}			
	}
	
	public void changeCard(int handIndex, int faceUpIndex) {
		Card handCard = player.getHand().get(handIndex);
		Card faceUpCard = player.getFaceUp().get(faceUpIndex);		
		player.getHand().set(handIndex, faceUpCard);
		player.getFaceUp().set(faceUpIndex, handCard);
	}
	
	public void drawCard() {
		cardPile.add(deck.getCard());
	}
	
	public Card getTopCard() {
		int index = cardPile.size();
		Card card = cardPile.get(index - 1);
		return card;
	}
	
	public void pickUp() {
		System.out.println("You cannot beat " + cardPile.get(cardPile.size()-1).printCard() + ", Player pick up");
		for(Card card : cardPile) {
			player.addCardToHand(card);
		}	
		cardPile.clear();
		cardPile.add(deck.getCard());
	}
	
	public boolean between(int i, int minValueInclusive, int maxValueInclusive) {
	    return (i >= minValueInclusive && i <= maxValueInclusive);
	}
}
