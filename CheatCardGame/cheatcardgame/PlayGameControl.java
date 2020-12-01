package cheatcardgame;

import java.awt.CardLayout;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JPanel;
import java.awt.Component;


public class PlayGameControl implements ActionListener {
	private CheatClient cheatClient;
	private JPanel container;
	private boolean turn = false;
	private ArrayList<String> deck = new ArrayList<String>();
	private int currentCard = 0;
	private int playerCount = 26;
	private int opponentCount = 26;

	
	public PlayGameControl(JPanel container, CheatClient cheatClient) {
		this.cheatClient = cheatClient;
		this.container = container;
	}
	
	
	public ArrayList<String> getDeck() {
		return deck;
	}
	
	public void setDeck(ArrayList<String> deck) {
		this.deck = deck;
	}
	
	public void setTurn(boolean turn) {
		this.turn = turn;
	}
	
	public boolean getTurn() {
		return turn;
	}
	
	public void setInstructions(String instructions) {
		PlayGamePanel playGamePanel = (PlayGamePanel)container.getComponent(4);
		playGamePanel.setInstructions(instructions);
	}
	public void setCard(String card) {
		PlayGamePanel playGamePanel = (PlayGamePanel)container.getComponent(4);
		playGamePanel.setCurrentCard(card);
	}
	public void initialize() {
		PlayGamePanel playGamePanel = (PlayGamePanel)container.getComponent(4);
		playGamePanel.setCurrentCard(deck.get(0));
		if (deck.contains("AS")) {
			turn = true;
			setInstructions("You have the Ace of Spades! Your turn.");
		}
		else {
			turn = false;
			setInstructions("Your opponent has the Ace of Spades! Their turn.");
		}
		playGamePanel.setPlayerCount(playerCount);
		playGamePanel.setOpponentCount(opponentCount);
	
	}
	public void removeCard(String card) {
		deck.remove(card);
	}
	public void setCurrentCard(int currentCard) {
		this.currentCard = currentCard;
	}
	
	public void setPlayerCount(int playerCount) {
		this.playerCount = playerCount;
		PlayGamePanel playGamePanel = (PlayGamePanel)container.getComponent(4);
		playGamePanel.setPlayerCount(playerCount);
	}
	public void setOpponentCount(int opponentCount) {
		this.opponentCount = opponentCount;
		PlayGamePanel playGamePanel = (PlayGamePanel)container.getComponent(4);
		playGamePanel.setOpponentCount(opponentCount);
	}
	public int getPlayerCount() {
		return playerCount;
	}
	public int getOpponentCount() {
		return opponentCount;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String command = e.getActionCommand();
		
		//Back button iterates backwards through held cards
	    if (command == "Back")
	    {
	    	currentCard -= 1;
	    	if (currentCard < 0) {
	    		currentCard = deck.size();
	    	}
	    	PlayGamePanel playGamePanel = (PlayGamePanel)container.getComponent(4);
	        playGamePanel.setCurrentCard(deck.get(currentCard%deck.size()).toString());
	    	
	    }

	    //Select Card to be played
	    else if (command == "Select")
	    {
	    	PlayGameData data = new PlayGameData(turn, false, deck.get(currentCard%deck.size()), playerCount);
	    	try {
		    	cheatClient.sendToServer(data);
		    	} catch(IOException e1) {
		    		e1.printStackTrace();
		    	}
	    }
	    
	    //Iterate forward through held cards
	    else if (command == "Forward")
	    {
	    	currentCard += 1;
	    	PlayGamePanel playGamePanel = (PlayGamePanel)container.getComponent(4); 
	        playGamePanel.setCurrentCard(deck.get(currentCard%deck.size()).toString());
	    }
	    
	    //call a player's bluff and confirm if they are making a legal play.
	    else if (command == "Cheat")
	    {
	    	PlayGameData data = new PlayGameData(turn, true, "", playerCount);
	    	try {
	    	cheatClient.sendToServer(data);
	    	} catch(IOException e1) {
	    		e1.printStackTrace();
	    	}
	    }
	}
}
