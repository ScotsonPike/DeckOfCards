import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.IntStream;

/*
 * The Game class loops through he primary methods of the card game
 * through deal(), handSetUp() and play().
 * */

public class Game {
	
	private Deck deck;
	private static int handSize = 3; // this is being doubled somewhere, issue with deal()?
	private ArrayList<Card> cardPile;
	private Scanner scanner;
	private boolean playBelow = false;
	private boolean missAGo = false;
	private int lastIndex = 0;
	private Card topCard;
		
	public Game(Deck deck) {
		// Constructor for Game class
		this.deck = deck;
		cardPile = new ArrayList<>();
		scanner = new Scanner(System.in);
	}
	
	public void welcome() {
		System.out.println("-- NEW GAME STARTED -- WELCOME! --");
	}
	
	public void setUpDeck() {
		// set up deck
		deck.createDeck();
		deck.shuffle();
	}
	
	public void deal(Player player) {		
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
	
	public void handSetUp(Player player) {
		player.printName();
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
						changeCard(player, (handIndex - 1), (faceUpIndex - 1));	
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
	
	public void play(Player player1, Player player2){
		// Main game loop method
		while(player1.cardsRemaining() > 0 || player2.cardsRemaining() > 0) {
			turn(player1);
			turn(player2);
		}			
	}
	
	private void turn(Player player) {
		player.printName();
		topCard = cardPile.get(cardPile.size()-1);
		System.out.println("Current top card in play: " + topCard.printCard());
		Card playerCard = selectCard(player);	
		if(checkCard(player, playerCard, topCard)) {
			// Player's hand contains a playable card
			if(playerCard.getMagic() != null) {
				// Card is magic
				magicRules(player, playerCard, topCard, lastIndex);					
			}
			else if(playBelow == true) {
				if(playerCard.getNumber() <= topCard.getNumber()){
					// Card can be played in order.
					playCard(player, lastIndex);
				}
			}
			else if(playerCard.getNumber() >= topCard.getNumber() ) {
				// Card can be played in order.
				playCard(player, lastIndex);
			}
			else {
				// Card is too low to be played
				System.out.println("Too Low");
			}
		}
		else {
			// Player's hand does not contain a card that can be played, player must pick up
			pickUp(player);
		}
		checkMagic();
	}
	
	private Card selectCard(Player player) {
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
				lastIndex = index;
				//player.removeCardFromHand(index);
				//player.addCardToHand(deck.getCard());
			}
			else {
				System.out.println("Invalid input.");
			}
		}		
		return playerCard;
	}
	
	private void checkMagic() {
		if(topCard.getNumber() != 9) {
			playBelow = false;
		}
		if(topCard.getNumber() != 8) {
			missAGo = false;
		}
	}
	
	private void magicRules(Player player, Card playerCard, Card topCard, int index) {		
		if(playerCard.getMagic().cardIsSequential() == false) {
			// Magic card can be played out of order
			switch(playerCard.getMagic()) {
				case startAgain:
					// start cardPile from 2
					playCard(player, index);
					break;
				case burn:
					// Play a burn card (10), empty cardPile, draw a card and select a new card to play.
					playCard(player, index);
					cardPile.clear();
					cardPile.add(selectCard(player));
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
					playCard(player, index);
					break;
				case playBelow:
					// Play a play below card (9)
					playBelow = true;
					playCard(player, index);
					break;
			default:
				break;
			}
		}
	}

	public boolean checkCard(Player player, Card card, Card topCard) {
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
	
	public void playCard(Player player, int index) {
		cardPile.add(player.getCardFromHand(index));
		player.removeCardFromHand(index);
		if(player.getHandSize() < 3) {
			player.addCardToHand(deck.getCard());
			player.sortHand();
		}			
	}
	
	public void changeCard(Player player, int handIndex, int faceUpIndex) {
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
	
	public void pickUp(Player player) {
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
