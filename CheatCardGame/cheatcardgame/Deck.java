package cheatcardgame;

import java.util.List;
import java.util.Collections;

public class Deck {

	private List<Card> cardList;
	private int index;
	
	public Deck(Card card) {
		
		cardList.add(card);
		
	}
	
	public void setIndex(int index) {
		
		this.index = index;
	}
	
	public void removeCard(String cardName, String suitName) {
		
		for (int i = 0; i < cardList.size(); i++) {
			Card tempCard = cardList.get(i);
			if (tempCard.getName() == cardName && tempCard.getSuit() == suitName) {
				cardList.remove(i);
				break;
			}
			
		}
		
	}
	
	public void addCard(String cardName, String suitName) {
		
		Card tempCard = new Card(cardName, suitName);
		cardList.add(tempCard);
	}
	
	public void shuffleDeck() {
		
		Collections.shuffle(cardList);
	}
	
	public Card getCard(int index) {
		
		return cardList.get(index);
	}
	
	public String toString() {
		
		
		
		String deck = new String();
		for (int i = 0; i < cardList.size(); i++) {
			Card tempCard = cardList.get(i);
			deck.concat(", " + tempCard.toString());
		}
		
		return deck;
	}
}
