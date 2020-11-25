package cheatcardgame;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class StartGamePanel extends JPanel {
	public StartGamePanel(StartGameControl sgc) {
		// set the grid layout
		setLayout(new GridLayout(2, 1, 100, 100));
		
		// title of game
		JLabel lblCheatCardGame = new JLabel("Cheat Card Game", JLabel.CENTER);
		lblCheatCardGame.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 38));
		add(lblCheatCardGame);
		
		// create a button panel, add the buttons
		JPanel buttonPanel = new JPanel();
		add(buttonPanel);
		JButton startButton = new JButton("Start Game");
		buttonPanel.add(startButton);
		startButton.addActionListener(sgc);
		JButton quitButton = new JButton("Quit");
		buttonPanel.add(quitButton);
		quitButton.addActionListener(sgc);
	}
}
