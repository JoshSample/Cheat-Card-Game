package cheatcardgame;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;

public class StartGameControl implements ActionListener {
	// private data fields
	private JPanel container;
	private CheatClient cheatClient;
	
	// constructor, sets container and client
	public StartGameControl(JPanel container, CheatClient cheatClient) {
		this.container = container;
		this.cheatClient = cheatClient;
	}
	
	public void actionPerformed(ActionEvent ae) {
		
		
	}

}
