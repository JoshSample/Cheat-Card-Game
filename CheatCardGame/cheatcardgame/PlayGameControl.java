package cheatcardgame;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JPanel;


public class PlayGameControl implements ActionListener {
	private CheatClient cheatClient;
	private JPanel container;
	private Player player;
	private Player opponent;
	private Play play;
	
	public PlayGameControl(JPanel container, CheatClient cheatClient) {
		this.cheatClient = cheatClient;
		this.container = container;
	}
	
	public void setPlayer(Player player) {
		this.player = player;
	}
	
	public void setOpponent(Player opponent) {
		this.opponent = opponent;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String command = e.getActionCommand();
		
		//Back button iterates backwards through held cards
	    if (command == "Back")
	    {

	    }

	    //Select Card to be played
	    else if (command == "Select")
	    {

	    }
	    
	    //Iterate forward through held cards
	    else if (command == "Forward")
	    {
	    	
	    }
	    
	    //deselect all cards held in hand
	    else if (command == "Deselect")
	    {
	    	
	    }
	    
	    //call a player's bluff and confirm if they are making a legal play.
	    else if (command == "Cheat")
	    {
	    	
	    }
	    
	    //Place your selected cards into the discard pile.
	    else if (command == "play")
	    {
	    	
	    }
	}	
}
