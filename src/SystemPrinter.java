
public class SystemPrinter {
	// Methods to print output text so it can be easily edited.

	public void welcome() {
		System.out.println("-------------------------------------");
		System.out.println("--- NEW GAME STARTED --- WELCOME! ---");
		System.out.println("-------------------------------------");
		System.out.println();
	}

	public void arrangeHandInstructions() {
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("~~~~ PHASE 1: ARRANGE YOUR HAND - INSTRUCTIONS:  ~~~~~");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("~~~ Swap a card in your hand with a face up card.  ~~~");
		System.out.println("~~~ Do  this  in the form 'number  space  number'. ~~~");
		System.out.println("~~~ For example,  to swap  the first card in your  ~~~");
		System.out.println("~~~ hand with the second face up card, type '1 2'. ~~~");
		System.out.println("~~~ When  you  are  done  swapping,  type 'exit'.  ~~~");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println();
	}
	
	public void generalInstructions() {
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("~~~~~~~~~~~~ GENERAL GAME INSTRUCTIONS:  ~~~~~~~~~~~~~");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("~~~ Look at the cards in your hand and try to play ~~~");
		System.out.println("~~~ above the current card in play. Some cards are ~~~");
		System.out.println("~~~ magic and have special rules. To end the game  ~~~");
		System.out.println("~~~ type 'endgame' (all one word).                 ~~~");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println();
	}

	public void prompt() {
		System.out.println("Please type here: ");
	}

	public void yourTurn() {
		System.out.println("It's your turn! ");
		System.out.println();
	}
}
