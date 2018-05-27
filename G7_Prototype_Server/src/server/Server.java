package server;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import ocsf.server.AbstractServer;
import ocsf.server.ConnectionToClient;
import resources.Message;
import resources.Question;

public class Server extends AbstractServer {

	// region Constants

	@SuppressWarnings("unused")
	private int DEFAULT_PORT = 5555;

	private Connection connection;

	private String userNameDBcon = "app";

	private String passWordDBcon = "project7";

	// end region -> Constants

	// region Constructors

	public Server(int port) {
		super(port);
	}

	// end region -> Constructors

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public String getUserNameDBcon() {
		return userNameDBcon;
	}

	public void setUserNameDBcon(String userNameDBcon) {
		this.userNameDBcon = userNameDBcon;
	}

	public String getPassWordDBcon() {
		return passWordDBcon;
	}

	public void setPassWordDBcon(String passWordDBcon) {
		this.passWordDBcon = passWordDBcon;
	}

	// region Protected Methods

	@SuppressWarnings("unchecked")
	@Override
	protected void handleMessageFromClient(Object msg, ConnectionToClient client) {
		if (msg == null) {
			// add error screen
			return;
		} else if (msg instanceof Question) {
			if (((Question) msg).getAuthor() == null) { // question to remove
				try {
					SqlUtilities.removeQuestion((Question) msg, connection);
					client.sendToClient(Message.SaveTable);
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else { // new question to add
				try {
					if (((Question) msg).getQuestionID().length() == 2) { // before the process to complete the
																			// questionID is finished
						int questionCount = SqlUtilities.getQuestionCount(((Question) msg).getQuestionID(), connection);
						client.sendToClient(questionCount);
					} else {
						SqlUtilities.insertNewQuestion((Question) msg, connection);
						client.sendToClient(Message.SaveTable);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} else if (msg instanceof ArrayList<?>) {

			// if it's from the questions table
			if (((ArrayList<?>) msg).get(0) instanceof Question) {
				try {
					SqlUtilities.editTable((ArrayList<Question>) msg, connection);
					client.sendToClient(Message.SaveTable);
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} else if (msg instanceof String) {
			String str = (String) msg;
			String[] strArray = str.split(" ");
			try {
				switch (strArray[0]) {
				case "#login":
					PreparedStatement login = connection.prepareStatement(SqlUtilities.Login_SELECT_UserID_From_Users);
					login.setString(1, strArray[1]);
					login.setString(2, strArray[2]);
					ResultSet rs;
					rs = login.executeQuery();
					rs.next();
					if (rs.getString(1).equals(strArray[1])) {
						login = connection.prepareStatement(SqlUtilities.Login_getlog_Status);
						login.setString(1, strArray[1]);
						login.setString(2, strArray[2]);
						rs = login.executeQuery();
						rs.next();
						if (rs.getInt(1) == 0) {
							client.sendToClient("#Teacher");
							login = connection.prepareStatement(SqlUtilities.Login_UpdateUser_logStatus_Connected);
							login.setString(1, strArray[1]);
							login.setString(2, strArray[2]);
							login.executeUpdate();
							client.setInfo("#login", strArray[1] + " " + strArray[2]);
							return;
						} else {
							client.sendToClient("#UserAlreadyConnected");
							System.out.println("UserAlreadyConnected");
							return;
						}
					} else {
						client.sendToClient("No such user");
						return;
					}
				case "#EditorRemovePressed":
					client.sendToClient(SqlUtilities.getQuestions(connection));
					break;
				case "#logout":
					int i = 1;
					PreparedStatement logout = connection
							.prepareStatement(SqlUtilities.Login_UpdateUser_logStatus_DisConnected);
					for (String string : client.getInfo("#login").toString().split(" ")) {
						logout.setString(i++, string);
					}
					logout.executeUpdate();
					return;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * This method overrides the one in the superclass. Called when the server
	 * starts listening for connections.
	 */
	@Override
	protected void serverStarted() {
		System.out.println("Server Started");
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

}