package cheatcardgame;

import static org.junit.Assert.*;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class DatabasesTest {
	private String[] users = {"jsmith@uca.edu","msmith@uca.edu","tjones@yahoo.com","jjones@yahoo.com"};
	private String[] passwords = {"hello123","pass123","123456","hello1234"};
	private Databases db = new Databases();
	private int rando = ((int)Math.random()*users.length);

	@Test
	public void testSetConnection() throws IOException {
		db.setConnection("cheatcardgame/db.properties");
		Connection conn = db.getConnection();
		assertNotNull("Check setConnection", conn);
	}
	
	@Test
	public void testQuery() throws IOException {
		db.setConnection("cheatcardgame/db.properties");
		Connection conn = db.getConnection();
		String username = users[rando]; 
		String expected = passwords[rando];
		ArrayList<String> actual = db.query("select username, aes_decrypt(password,'key') from users where username = '" + username + "';");
		String[] actual2 = actual.get(0).split(",");
		assertEquals(actual2[1], expected);
	}
	
	@Test
	public void testExecuteDML() throws IOException {
		db.setConnection("cheatcardgame/db.properties");
		Connection conn = db.getConnection();
		try {
			db.executeDML("insert into users values('jsample3@uca.edu, aes_encrypt('hello', 'key'))");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
