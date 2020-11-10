
public class Card implements Comparable{
	
	String suit;
	int number;
	String type;
	
	public Card(String suit, int number) {
		this.suit = suit;
		this.number = number;
		switch(number) {
			case 1:
				type = "Ace";
				break;
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
		if(number > 1 && number < 11) {
			type = Integer.toString(number);
		}
	}
	
	public String getSuit() {
		return suit;
	}
	
	public int getNumber() {
		return number;
	}
	
	public String getType() {
		return type;
	}	
	
	public String printCard() {
		return type + " of " + suit;
	}
	
	@Override
	public int compareTo(Object compareCard) {
		int compareNum = ((Card)compareCard).getNumber();
        return this.number-compareNum;
	}

    @Override
    public String toString() {
        return "[ suit= " + suit + ", number=" + number + ", type=" + type + "]";
    }	

}
