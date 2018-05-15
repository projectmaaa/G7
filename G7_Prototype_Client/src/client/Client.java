package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import controllers.LoginWindowController;
import javafx.stage.Stage;
import ocsf.client.AbstractClient;

public class Client extends AbstractClient {

	// Class variables *************************************************

	/**
	 * The default port to connect on.
	 */
	final public static int DEFAULT_PORT = 5555;

	// Constructors ****************************************************

	/**
	 * Constructs an instance of the chat client.
	 *
	 * @param host
	 *            The server to connect to.
	 * @param port
	 *            The port number to connect on.
	 * @param clientUI
	 *            The interface type variable.
	 */
	public Client(String host, int port) throws IOException {
		super(host, port); // Call the superclass constructor
		openConnection();
	}

	/**
	 * This method handles all data that comes in from the server.
	 *
	 * @param msg
	 *            The message from the server.
	 */
	@Override
	public void handleMessageFromServer(Object obj) {
		if (obj == null) {
			// add something
			return;
		}

	}
	
	  /**
	   * This method waits for input from the console.  Once it is 
	   * received, it sends it to the client's message handler.
	   */
	  public void accept() 
	  {
	    try
	    {
	      BufferedReader fromConsole = 
	        new BufferedReader(new InputStreamReader(System.in));
	      String message;

	      while (true) 
	      {
	        message = fromConsole.readLine();
	        client.handleMessageFromClientUI(message);
	      }
	    } 
	    catch (Exception ex) 
	    {
	      System.out.println
	        ("Unexpected error while reading from console!");
	    }
	  }

	public static void main(String[] args) {
		LoginWindowController.go();
	}

}
