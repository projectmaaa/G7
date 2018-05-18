package server;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import ocsf.server.AbstractServer;
import ocsf.server.ConnectionToClient;

public class Server extends AbstractServer {

	// region Constants

	final public static int DEFAULT_PORT = 5555;

	private final Connection connection;

	// end region -> Constants

	// region Constructors

	public Server(int port) {
		super(port);
		connection = SqlUtilities.connection();
	}

	// end region -> Constructors

	// region Protected Methods

	@Override
	protected void handleMessageFromClient(Object msg, ConnectionToClient client) {
		if (msg == null) {
			// add error screen
			return;
		}
		if (!(msg instanceof String)) {
			// add error screen
			return;
		}
		ResultSet rs;
		String str = (String) msg;
		String[] strArray = str.split(" ");
		try {
			switch (strArray[0]) {
			case "#login":
				PreparedStatement login = connection.prepareStatement(SqlUtilities.Login_SELECT_User_From_Users);
				login.setString(1, strArray[1]);
				login.setString(2, strArray[2]);
				rs = login.executeQuery();
				if (rs.next()) {
					// System.out.println("Teacher");
					client.sendToClient("Teacher");
					return;
				} else {
					client.sendToClient("No");
					return;
				}
			case "#EditorRemovePressed":
				client.sendToClient(SqlUtilities.getQuestions());
				break;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * This method overrides the one in the superclass. Called when the server
	 * starts listening for connections.
	 */
	@Override
	protected void serverStarted() {
		System.out.println("Server listening for connections on port " + getPort());
	}

	/**
	 * This method overrides the one in the superclass. Called when the server stops
	 * listening for connections.
	 */
	@Override
	protected void serverStopped() {
		System.out.println("Server has stopped listening for connections.");
	}

	// end region -> Protected Methods

	public static void main(String[] args) {
		int port = 0; // Port to listen on
		try {
			port = Integer.parseInt(args[0]); // Get port from command line
		} catch (Throwable t) {
			port = DEFAULT_PORT; // Set port to 5555
		}
		Server server = new Server(port);
		try {
			server.listen(); // Start listening for connections
		} catch (Exception ex) {
			System.out.println("ERROR - Could not listen for clients!");
		}
	}
}