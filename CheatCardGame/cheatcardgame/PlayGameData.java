package cheatcardgame;

import java.io.Serializable;
import java.util.*;

public class PlayGameData implements Serializable {
	//private Player player;
	//private Player opponent;
	private int playerCount;
	private int opponentCount;
	private boolean turn;
	private boolean cheat;
	private ArrayList<String> cards = new ArrayList<String>();
	private String playedCard;
	
	public PlayGameData(boolean turn, boolean cheat, String playedCard, int playerCount) {
		this.turn = turn;
		this.playedCard = playedCard;
		this.cheat = cheat;
		this.playerCount = playerCount;
	}
	
	
	public void setTurn(boolean val) {
		this.turn = val;
	}
	
	public boolean getTurn() {
		return turn;
	}
	public void setCheat(boolean val) {
		this.cheat = val;
	}
	
	public boolean getCheat() {
		return cheat;
	}
	
	public void setPlayerCount(int count) {
		this.playerCount = count;
	}
	
	public void setOpponentCount(int count) {
		this.opponentCount = count;
	}
	
	public void setPlayedCard(String playedCard) {
		this.playedCard = playedCard;
	}
	public String getPlayedCard() {
		return playedCard;
	}
	public void setCards(ArrayList<String> cards) {
		this.cards = cards;
	}
	public ArrayList<String> getCards(){
		return cards;
	}
	
	
}
