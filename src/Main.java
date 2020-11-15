public class Main {
	
	public static void main(String[] args) {		
		Deck deck = new Deck();	
		Player player = new Player();
		Game game = new Game(deck, player);	
		game.deal();
		game.handSetUp();
		game.play();
	}	
}
