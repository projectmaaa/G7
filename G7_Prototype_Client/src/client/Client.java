package client;

import java.io.IOException;
import controllers.IScreenController;
import controllers.ScreensController;
import ocsf.client.AbstractClient;

public class Client extends AbstractClient implements IScreenController {

	// Class variables *************************************************

	/**
	 * The default port to connect on.
	 */
	final public static int DEFAULT_PORT = 5555;

	private ScreensController myController;

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
	public Client(String host, int port, ScreensController screenParent) throws IOException {
		super(host, port); // Call the superclass constructor
		setScreenParent(screenParent);
		openConnection();
	}

	/**
	 * This method handles all data that comes in from the server.
	 *
	 * @param msg
	 *            The message from the server.
	 */
	@Override
	public void handleMessageFromServer(Object msg) {
		if (msg == null) {
			// add something
			return;
		}
		if (!(msg instanceof String)) {
			// this.sendToAllClients(msg);
			return;
		}
		String str = (String) msg;
		switch (str) {
		case "Teacher":
			try {
				myController.setScreen(MainApp.screen2ID);
			} catch (Exception e) {
				e.printStackTrace();
			}

			break;
		case "No":
			// try {
			// this.closeConnection();
			// } catch (IOException e) {
			// // TODO Auto-generated catch block
			// e.printStackTrace();
			// }
			System.out.println("Close connection");
			break;
		}

	}

	/**
	 * This method handles all data coming from the UI
	 *
	 * @param message
	 *            The message from the UI.
	 */
	public void handleMessageFromClientUI(String message) {
		try {
			sendToServer(message);
		} catch (IOException e) {
			// clientUI.display("Could not send message to server. Terminating client.");
			quit();
		}
	}

	/**
	 * This method terminates the client.
	 */
	public void quit() {
		try {
			closeConnection();
		} catch (IOException e) {
		}
		System.exit(0);
	}

	/**
	 * This method waits for input from the console. Once it is received, it sends
	 * it to the client's message handler.
	 */
	public void accept() {
		// try {
		// BufferedReader fromConsole = new BufferedReader(new
		// InputStreamReader(System.in));
		// String message;
		//
		// while (true) {
		// message = fromConsole.readLine();
		// client.handleMessageFromClientUI(message);
		// }
		// } catch (Exception ex) {
		// System.out.println("Unexpected error while reading from console!");
		// }
	}

	public void setScreenParent(ScreensController screenParent) {
		myController = screenParent;
	}

}
