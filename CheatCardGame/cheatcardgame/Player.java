package cheatcardgame;

import java.io.Serializable;

public class Player implements Serializable {
	// Private data fields for the username and password.
	private String username;
	private String password;
	
	// constructor for player class
	public Player(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	// setter for username
	public void setUsername(String username) {
		this.username = username;
	}
	
	// getter for username
	public String getUsername() {
		return username;
	}
	
	// setter for password
	public void setPassword(String password) {
		this.password = password;
	}
	
	// getter for password
	public String getPassword() {
		return password;
	}
}
