package server;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import ocsf.server.AbstractServer;
import ocsf.server.ConnectionToClient;
import resources.*;

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

	@Override
	protected void handleMessageFromClient(Object msg, ConnectionToClient client) {
		if (msg == null) {
			System.out.println("error window");
			return;
		} else if (msg instanceof QuestionsHandle) {
			QuestionsHandle questionsHandle = (QuestionsHandle) msg;
			if (questionsHandle.getCommand().equals("Delete")) { // question to remove
				try {
					SqlUtilities.removeQuestions(questionsHandle.getQuestionArray(), connection);
					client.sendToClient(Message.tableSaved);
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else if (questionsHandle.getCommand().equals("Add")) { // new question to add
				try {
					if (questionsHandle.getQuestion().getQuestionNum() == null) { // before the process to complete the
																					// questionID is finished
						int questionCount = SqlUtilities.getQuestionCount(questionsHandle.getQuestion().getSubjectID(),
								connection);
						client.sendToClient(questionCount);
					} else {
						SqlUtilities.insertNewQuestion(questionsHandle.getQuestion(), connection);
						client.sendToClient(Message.tableSaved);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else if (questionsHandle.getCommand().equals("All")) {
				// if it's from the questions table
				if (questionsHandle.getQuestionArray().get(0) instanceof Question) {
					try {
						SqlUtilities.editTable(questionsHandle.getQuestionArray(), connection);
						client.sendToClient(Message.tableSaved);
					} catch (SQLException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		} else if (msg instanceof ExamHandle) {
			ExamHandle examHandle = (ExamHandle) msg;
			if (examHandle.getCommand().equals(Message.exam)) {
				try {
					SqlUtilities.insertNewExam(examHandle.getExam(), connection);
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
					if (rs.next()) {
						if (rs.getString(1).equals(strArray[1])) {
							login = connection.prepareStatement(SqlUtilities.Login_getlog_Status);
							login.setString(1, strArray[1]);
							login.setString(2, strArray[2]);
							rs = login.executeQuery();
							rs.next();
							if (rs.getInt(1) == 0) {
								PreparedStatement getName = connection
										.prepareStatement(SqlUtilities.GetTypeAndUserNameAndLastName);
								getName.setString(1, strArray[1]);
								rs = getName.executeQuery();
								rs.next();
								switch (rs.getString(1)) {
								case "Teacher":
									client.sendToClient(
											Message.teacher + " " + rs.getString(2) + " " + rs.getString(3));
									break;
								case "Student":
									client.sendToClient(
											Message.studnet + " " + rs.getString(2) + " " + rs.getString(3));
									break;
								case "Principal":
									client.sendToClient(
											Message.principal + " " + rs.getString(2) + " " + rs.getString(3));
									break;
								default:
									client.sendToClient("#No" + " " + rs.getString(2) + " " + rs.getString(3));
									break;
								}
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
						}
					} else {
						client.sendToClient(Message.noSuchUser);
						System.out.println("Wrong Username or Pasword");
						return;
					}
				case Message.editOrRemove:
					client.sendToClient(
							SqlUtilities.getQuestions(strArray[1] + " " + strArray[2], strArray[3], connection));
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