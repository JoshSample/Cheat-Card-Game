package cheatcardgame;

public class Card {

	private String cardName;
	private String suitName; 
	
	 public Card(String cardName, String suitName) {
		 
		 this.cardName = cardName;
		 this.suitName = suitName;
		 
	 }
	
	public String getName() {
		return cardName;
	}
	
	public String getSuit() {
		return suitName;
	}
	
	public String toString() {
		return (cardName + "(" + suitName + ")");
	}
	
}
