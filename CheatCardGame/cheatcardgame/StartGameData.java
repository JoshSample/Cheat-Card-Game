package cheatcardgame;

import java.io.Serializable;

public class StartGameData implements Serializable  {
	// private data field for username
	private String username;
	
	// gets username
	public String getUsername() { 
		return username;
	}
	
	// sets the username
	public void setUsername(String username) {
		this.username = username;
	}
	
	// constructor that sets username
	public StartGameData(String username) {
		setUsername(username);
	}
}
