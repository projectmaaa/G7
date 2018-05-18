package server;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import ocsf.server.AbstractServer;
import ocsf.server.ConnectionToClient;

public class Server extends AbstractServer {

	final public static int DEFAULT_PORT = 5555;
	private final Connection connection;

	public Server(int port) {
		super(port);
		connection = SqlUtilities.connection();
	}

	@Override
	protected void handleMessageFromClient(Object msg, ConnectionToClient client) {
		if (msg == null) {

			return;
		}
		if (!(msg instanceof String)) {
			// this.sendToAllClients(msg);
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
					System.out.println("Teacher");
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
	 * returns the selected question details (if exists)
	 */
	public String requestedQuestion(String questionID) throws SQLException {
		Statement statement = connection.createStatement();
		String question = "There's no such question with the requested ID";
		ResultSet rs = statement.executeQuery(SqlUtilities.SELECT_All_FROM_Questions);
		while (rs.next()) {
			/* if the question exists */
			if (rs.getString(1).equals(questionID)) {
				question = "Your Question Details:\nID: " + rs.getString(1) + "\nAuthor: " + rs.getString(2)
						+ "\nText: " + rs.getString(3) + "\nPossible Answers: " + rs.getString(4) + "\nCorrect Answer: "
						+ rs.getString(5);
				break;
			}
		}
		rs.close();
		return question;
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