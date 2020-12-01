package cheatcardgame;

import ocsf.client.AbstractClient;
import java.util.*;

public class CheatClient extends AbstractClient
{
  // Private data fields for storing the GUI controllers.
  private LoginControl loginControl;
  private CreateAccountControl createAccountControl;
  private PlayGameControl playGameControl;

  // Setters for the GUI controllers.
  public void setLoginControl(LoginControl loginControl)
  {
    this.loginControl = loginControl;
  }
  public void setCreateAccountControl(CreateAccountControl createAccountControl)
  {
    this.createAccountControl = createAccountControl;
  }
  public void setPlayGameControl(PlayGameControl playGameControl)
  {
	  this.playGameControl = playGameControl;
  }


  // Constructor for initializing the client with default settings.
  public CheatClient()
  {
    super("localhost", 8300);
  }
  
  // Method that handles messages from the server.
  public void handleMessageFromServer(Object arg0)
  {
    // If we received a String, figure out what this event is.
    if (arg0 instanceof String)
    {
      // Get the text of the message.
      String message = (String)arg0;
      
      // If we successfully logged in, tell the login controller.
      if (message.equals("LoginSuccessful"))
      {
        loginControl.loginSuccess();
      }
      
      // If we successfully created an account, tell the create account controller.
      else if (message.equals("CreateAccountSuccessful"))
      {
        createAccountControl.createAccountSuccess();
      }
      else if (message.equals("Cheat True")) {
    	  playGameControl.setInstructions("Cheater! Take all of the cards!");
      }
      else if (message.equals("Cheat False")) {
    	  playGameControl.setInstructions("You were wrong! Take all of the cards!");
      }
    }
    if (arg0 instanceof ArrayList<?>) {
    	ArrayList<String> deck = (ArrayList<String>)arg0;
    	playGameControl.setDeck(deck);
    	playGameControl.initialize();
    }
    
    // If we received an Error, figure out where to display it.
    else if (arg0 instanceof Error)
    {
      // Get the Error object.
      Error error = (Error)arg0;
      
      // Display login errors using the login controller.
      if (error.getType().equals("Login"))
      {
        loginControl.displayError(error.getMessage());
      }
      
      // Display account creation errors using the create account controller.
      else if (error.getType().equals("CreateAccount"))
      {
        createAccountControl.displayError(error.getMessage());
      }
    }
    else if(arg0 instanceof PlayGameData) {
    	//check if discard pile list is not null
    		// add cards to player hand
    		ArrayList<String> deck = playGameControl.getDeck();
    		ArrayList<String> cards = ((PlayGameData) arg0).getCards();
    		deck.addAll(cards);
    		//iterate turn and place card
    		String placedCard = ((PlayGameData) arg0).getPlayedCard();
    		if(((PlayGameData) arg0).getTurn() == true && playGameControl.getTurn() == true)  {
    			playGameControl.removeCard(placedCard);
    			playGameControl.setTurn(false);
    		}
    		if(((PlayGameData) arg0).getTurn() == true && playGameControl.getTurn() == false)  {
    			playGameControl.setTurn(true);
    		}
    }
  }  
}