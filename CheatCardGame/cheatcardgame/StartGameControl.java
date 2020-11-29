package cheatcardgame;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

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
		// Get the name of the button clicked.
		String command = ae.getActionCommand();

		// The quit button will exit the user back to the initial panel
		if (command.equals("Quit")) {
			CardLayout cardLayout = (CardLayout)container.getLayout();
			cardLayout.show(container, "1");
		}
		// Start Game button sends the user into the Play Game panel
		else if (command.equals("Start Game")) {
			CardLayout cardLayout = (CardLayout)container.getLayout();
			cardLayout.show(container, "5");
		}
	}

}
