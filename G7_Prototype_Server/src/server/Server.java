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
import resources.QuestionsHandle;

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
			System.out.println("error window");
			return;
		} else if (msg instanceof QuestionsHandle) {
			QuestionsHandle questionsHandle = (QuestionsHandle) msg;
			if (questionsHandle.getCommand().equals("Delete")) { // question to remove
				try {
					SqlUtilities.removeQuestion(questionsHandle.getQuestion(), connection);
					client.sendToClient(Message.tableSaved);
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
						client.sendToClient(Message.tableSaved);
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
					client.sendToClient(Message.tableSaved);
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
				case Message.login:
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
							PreparedStatement getName = connection
									.prepareStatement(SqlUtilities.GetUserNameAndLastName);
							getName.setString(1, strArray[1]);
							rs = getName.executeQuery();
							rs.next();
							// System.out.println(rs.getString(1) + " " + rs.getString(2));
							client.sendToClient(Message.teacher + " " + rs.getString(1) + " " + rs.getString(2));
							login = connection.prepareStatement(SqlUtilities.Login_UpdateUser_logStatus_Connected);
							login.setString(1, strArray[1]);
							login.setString(2, strArray[2]);
							login.executeUpdate();
							client.setInfo(Message.login, strArray[1] + " " + strArray[2]);
							getName.close();
							login.close();
							rs.close();
							return;
						} else {
							client.sendToClient(Message.userAlreadyConnected);
							System.out.println("User Already Connected");
							login.close();
							rs.close();
							return;
						}
					} else {
						client.sendToClient(Message.noSuchUser);
						return;
					}
				case Message.editOrRemove:
					client.sendToClient(SqlUtilities.getQuestions(connection));
					break;
				case Message.logout:
					int i = 1;
					PreparedStatement logout = connection
							.prepareStatement(SqlUtilities.Login_UpdateUser_logStatus_DisConnected);
					for (String string : client.getInfo(Message.login).toString().split(" ")) {
						logout.setString(i++, string);
					}
					logout.executeUpdate();
					logout.close();
					break;
				case Message.getQuestionBySubject:
					client.sendToClient(SqlUtilities.getQuestionsBySubject(connection, strArray[1]));
					break;
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