package cheatcardgame;

import static org.junit.Assert.*;

import java.awt.event.ActionEvent;

import javax.swing.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class StartGamePanelTest {

	public static StartGamePanel sp;
	private ActionEvent startButton;
	
	@BeforeClass
	public void setUp() {
		sp = new StartGamePanel(null);
		StartGameControl sc = new StartGameControl(null, null);
		sc.actionPerformed(startButton);
	}

}
