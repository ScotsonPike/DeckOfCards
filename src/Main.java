public class Main {

	public static void main(String[] args) {
		Deck deck = new Deck();
		Player player1 = new Player("Player 1");
		Player player2 = new Player("Player 2");
		Game game = new Game(deck);
		SystemPrinter printer = new SystemPrinter();
		printer.welcome();
		game.setUpDeck();
		game.deal(player1);
		game.deal(player2);
		game.handSetUp(player1, printer);
		game.handSetUp(player2, printer);
		game.play(player1, player2, printer);
	}
}
