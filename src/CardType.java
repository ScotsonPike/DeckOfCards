public class CardType {
	/*
	 * Class to decouple the type and number fields in the card class
	 */

	int number;
	String type;
	Magic magic;

	public CardType(int number) {
		this.number = number;
		switch (number) {
		// place in CardType
		case 1:
			type = "Ace";
			setNumber(14);
			break;
		case 2:
			magic = Magic.startAgain;
			break;
		case 7:
			magic = Magic.seeThrough;
			break;
		case 8:
			magic = Magic.missAGo;
			break;
		case 9:
			magic = Magic.playBelow;
			break;
		case 10:
			magic = Magic.burn;
		case 11:
			type = "Jack";
			break;
		case 12:
			type = "Queen";
			break;
		case 13:
			type = "King";
			break;
		}
		if (number > 1 && number < 11) {
			type = Integer.toString(number);
		}
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Magic getMagic() {
		return magic;
	}

	public void setMagic(Magic magic) {
		this.magic = magic;
	}

}
