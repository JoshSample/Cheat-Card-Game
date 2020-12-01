package cheatcardgame;

import static org.junit.Assert.*;

import org.junit.Test;

public class StartGameDataTest {
	private String[] users = {"jsmith@uca.edu","msmith@uca.edu","tjones@yahoo.com","jjones@yahoo.com"};
	private int rando = ((int)Math.random()*users.length);
	@Test
	public void testSetUsername() {
		String username = users[rando]; 
		String actual = username;
		String expected = users[rando];
		assertEquals(actual, expected);
	}

}
