package cheatcardgame;

import java.awt.*;
import javax.swing.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import ocsf.server.AbstractServer;
import ocsf.server.ConnectionToClient;

public class CheatServer extends AbstractServer
{
  // Data fields for this chat server.
  private JTextArea log;
  private JLabel status;
  private boolean running = false;
  //private Database database;

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
  
  /* Commented out until we get the database set up
  //Setter for the database
  public void setDatabase(Database database) 
  {
	  
	  this.database = database;
	  
  }
  */
  
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

  // When a client connects or disconnects, display a message in the log.
  public void clientConnected(ConnectionToClient client)
  {
    log.append("Client " + client.getId() + " connected\n");
  }

  // When a message is received from a client, handle it.
  public void handleMessageFromClient(Object arg0, ConnectionToClient arg1)
  {
    
	  //placeholder for a function to handle game moves and other requests from clients
	  
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