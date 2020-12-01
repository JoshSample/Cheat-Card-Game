package cheatcardgame;

import java.awt.*;
import javax.swing.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

import ocsf.server.AbstractServer;
import ocsf.server.ConnectionToClient;

public class CheatServer extends AbstractServer
{
  // Data fields for this chat server.
  private JTextArea log;
  private JLabel status;
  private boolean running = false;
  private Database database;
  private ConnectionToClient conn1;
  private ConnectionToClient conn2;
  private Player player1;
  private Player player2;
  private ArrayList<String> discardPile = new ArrayList<String>();
  private ArrayList<String> gameDeck = new ArrayList<String>();
  private ArrayList<String> player1Hand = new ArrayList<String>();
  private ArrayList<String> player2Hand = new ArrayList<String>();
  private String prevCard;
  private String[] cardOrder = {"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
  private int iterator = 0;

	// Constructor for initializing the server with default settings.
	public CheatServer()
	{
		super(12345);
		this.setTimeout(500);
	}

	// Getter that returns whether the server is currently running.
	public boolean isRunning()
	{
		return running;
	}


	//Setter for the database
	public void setDatabase(Database database) 
	{
		this.database = database;

	}

  // When a client connects or disconnects, display a message in the log.
  public void clientConnected(ConnectionToClient client)
  {
    log.append("Client " + client.getId() + " connected\n");
    if(conn1 == null) {
    	conn1 = client;
    }
    else if (conn2 == null){
    	conn2 = client;
    }
  }
  
  
  //Method to create the full deck of cards
  public void createFullDeck() {
	  //Suit of Hearts
	  for (int i = 0; i < cardOrder.length; i++) {
		  gameDeck.add(cardOrder[i] + "H");
	  }
	  
	  //Suit of Diamonds
	  for (int i = 0; i < cardOrder.length; i++) {
		  gameDeck.add(cardOrder[i] + "D");
	  }
	  
	  //Suit of Clubs
	  for (int i = 0; i < cardOrder.length; i++) {
		  gameDeck.add(cardOrder[i] + "C");
	  }
	  
	  //Suit of Spades
	  for (int i = 0; i < cardOrder.length; i++) {
		  gameDeck.add(cardOrder[i] + "S");
	  }
	  
	  //Shuffle the deck after it is created
	  Collections.shuffle(gameDeck);
  }
  
  //Splits the full deck in two and assigns it to each player
  public void assignPlayerHands() throws IOException {
	  
	  //Assign a hand to player 1
	  for (int i = 0; i < 26; i++) {
		  String card = gameDeck.get(0);
		  player1Hand.add(card);
		  gameDeck.remove(card);
	  }
	  conn1.sendToClient(player1Hand);
	  
	  //Assign a hand to player 2
	  for (int i = 0; i < 26; i++) {
		  String card = gameDeck.get(0);
		  player2Hand.add(card);
		  gameDeck.remove(card);
	  }
	  conn2.sendToClient(player2Hand);
	  
  }
		

	// Setters for the data fields corresponding to the GUI elements.
	public void setLog(JTextArea log)
	{
		this.log = log;
	}
	public void setStatus(JLabel status)
	{
		this.status = status;
	}

	// When the server starts, update the GUI.
	public void serverStarted()
	{
		running = true;
		status.setText("Listening");
		status.setForeground(Color.GREEN);
		log.append("Server started\n");
	}

	// When the server stops listening, update the GUI.
	public void serverStopped()
	{
		status.setText("Stopped");
		status.setForeground(Color.RED);
		log.append("Server stopped accepting new clients - press Listen to start accepting new clients\n");
	}

	// When the server closes completely, update the GUI.
	public void serverClosed()
	{
		running = false;
		status.setText("Close");
		status.setForeground(Color.RED);
		log.append("Server and all current clients are closed - press Listen to restart\n");
	}

	// When a message is received from a client, handle it.
	public void handleMessageFromClient(Object arg0, ConnectionToClient arg1)
	{

		//If we received LoginData, verify the account information
		if (arg0 instanceof LoginData)
		{

			// Check the username and password with the database.
			LoginData data = (LoginData)arg0;
			Object result;

			if (database.checkLogin(data.getUsername(), data.getPassword()))
			{
				result = "LoginSuccessful";
				log.append("Client " + arg1.getId() + " successfully logged in as " + data.getUsername() + "\n");
			}
			else
			{
				result = new Error("The username and password are incorrect.", "Login");
				log.append("Client " + arg1.getId() + " failed to log in\n");
				return;
			}

			try {
				arg1.sendToClient(result);
			}
			catch (IOException e) {
				e.printStackTrace();
			} 
			
			if(player1 == null) {
				player1 = new Player(data.getUsername(), data.getPassword());
			}
			else if (player2 == null) {
				player2 = new Player(data.getUsername(), data.getPassword());
			}
			
			if (player1 != null && player2 != null) {
		    	createFullDeck();
		    	try {
		    	assignPlayerHands();
		    	} catch (IOException e) {
		    		e.printStackTrace();
		    	}
		    }
			
			return;
		}

		//If we receive Create Account data, verify that the account does not exist
		else if (arg0 instanceof CreateAccountData) {

			//Try to create the account.
			CreateAccountData data = (CreateAccountData)arg0;
			Object result;

			if (database.createNewAccount(data.getUsername(), data.getPassword()))
			{
				result = "CreateAccountSuccessful";
				log.append("Client " + arg1.getId() + " created a new account called " + data.getUsername() + "\n");
			}
			else
			{
				result = new Error("The username is already in use.", "CreateAccount");
				log.append("Client " + arg1.getId() + " failed to create a new account\n");
			}

			try {
				arg1.sendToClient(result);
			}
			catch (IOException e) {
				return;
			}

		}


		else if (arg0 instanceof PlayGameData) {
			if (((PlayGameData) arg0).getTurn() == false && ((PlayGameData)arg0).getCheat() == false) {
				try {
					arg1.sendToClient("invalid");
				}
				catch (IOException e) {
					e.printStackTrace();
				}		   }
			else if (((PlayGameData) arg0).getTurn() == false && ((PlayGameData)arg0).getCheat() == true) {
				((PlayGameData) arg0).setCards(discardPile);
				if (prevCard.contains(cardOrder[iterator])) { // if previous card plays matches the correct card order
					try {
						arg1.sendToClient("Cheat False"); //cheat accusation was false
					}
					catch (IOException e) {
						e.printStackTrace();
					}
					if(arg1.equals(conn1)) {
						try {
							conn2.sendToClient(arg0);
						}
						catch (IOException e) {
							e.printStackTrace();
						}
					}
					else {
						try {
							conn1.sendToClient(arg0);
						}
						catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
				else {
					try {
						arg1.sendToClient("Cheat True");
					}
					catch (IOException e) {
						e.printStackTrace();
					}
					if(arg1.equals(conn1)) {
						try {
							conn1.sendToClient(arg0);
						}
						catch (IOException e) {
							e.printStackTrace();
						}
					}
					else {
						try {
							conn2.sendToClient(arg0);
						}
						catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}
			else {
				prevCard = ((PlayGameData) arg0).getPlayedCard();
				try {
					conn1.sendToClient(arg0);
					conn2.sendToClient(arg0);
				}
				catch (IOException e) {
					e.printStackTrace();
				}
				discardPile.add(((PlayGameData) arg0).getPlayedCard());
				iterator++;
			}
		}

	}

	// Method that handles listening exceptions by displaying exception information.
	public void listeningException(Throwable exception) 
	{
		running = false;
		status.setText("Exception occurred while listening");
		status.setForeground(Color.RED);
		log.append("Listening exception: " + exception.getMessage() + "\n");
		log.append("Press Listen to restart server\n");
	}


}