package cheatcardgame;

import java.io.Serializable;

public class PlayGameData implements Serializable {
	//private Player player;
	//private Player opponent;
	private int playerCount;
	private int opponentCount;
	private boolean turn;
	private String playedCard;
	
	public PlayGameData(Player player, Player opponent) {
		//this.player = player;
		//this.opponent = opponent;
	}
	
	public void setTurn(boolean val) {
		this.turn = val;
	}
	
	public boolean getTurn() {
		return turn;
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
	
	
}
