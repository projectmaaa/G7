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
		} else if (msg instanceof QuestionHandle) {
			QuestionHandle questionsHandle = (QuestionHandle) msg;
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
					SqlUtilities.insertNewQuestion(questionsHandle.getQuestion(), connection);
					client.sendToClient(Message.tableSaved);
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else if (questionsHandle.getCommand().equals("All")) { // if it's from the questions table
				try {
					SqlUtilities.editTable(questionsHandle.getQuestionArray(), connection);
					client.sendToClient(Message.tableSaved);
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} else if (msg instanceof SubmittedExamHandle) {
			SubmittedExamHandle submittedExamHandle = (SubmittedExamHandle) msg;
			if (submittedExamHandle.getCommand().equals(Message.submittedExam)) {
				try {
					SqlUtilities.insertSubmittedExam(submittedExamHandle.getSubmittedExam(), connection);
					SqlUtilities.insertCheckedExam(submittedExamHandle.getSubmittedExam(), connection);
					SqlUtilities.insert_StudentAnswerInQuestion(submittedExamHandle.getSubmittedExam(), connection);
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (NullPointerException e) {
					e.printStackTrace();
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
		} else if (msg instanceof ActiveExamHandle) {
			ActiveExamHandle activeExamHandle = (ActiveExamHandle) msg;
			if (activeExamHandle.getCommand().equals("Activate")) {
				try {
					SqlUtilities.insertActiveExam(activeExamHandle.getActiveExam(), connection);
					client.sendToClient(Message.tableSaved);
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else if (activeExamHandle.getCommand().equals("Lock")) {
				try {
					sendToAllClients("#LockExam" + " "
							+ SqlUtilities.lockActiveExam(activeExamHandle.getActiveExam(), connection));
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} else if (activeExamHandle.getCommand().equals("#ManualExam")) {
				try {
					Utilities_Server.getManualExam(activeExamHandle.getActiveExam(),
							activeExamHandle.getActiveExam().getExecutionCode(), activeExamHandle.getUserID());
					client.sendToClient(new MyFileHandle("StudnetExam", Utilities_Client.getWordFile(
							activeExamHandle.getActiveExam().getExecutionCode(), activeExamHandle.getUserID())));
				} catch (IOException e) {
					System.out.println(e);
				}
			}
		} else if (msg instanceof MyFileHandle) {
			MyFileHandle myFileHandle = (MyFileHandle) msg;
			if (myFileHandle.getCommand().equals("UploadExam")) {
				String[] strArray = myFileHandle.getFile().getFileName().split("/");
				myFileHandle.getFile().setFileName(strArray[0] + "/uploadedExams/" + strArray[2]);
				Utilities_Client.writeWordFile(myFileHandle.getFile(), false);
			}
		} else if (msg instanceof ExecutionCodeHandle) {
			ExecutionCodeHandle executionCodeHandle = (ExecutionCodeHandle) msg;
			try {
				client.sendToClient(SqlUtilities.checkCode(executionCodeHandle, connection));
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (msg instanceof StudentInActiveExamHandle) {
			StudentInActiveExamHandle studentInActiveExamHandle = (StudentInActiveExamHandle) msg;
			if (studentInActiveExamHandle.getCommand().equals(Message.studentInActiveExam)) {
				try {
					SqlUtilities.insert_StudentInActiveExam(studentInActiveExamHandle.getStudentInActiveExam(),
							connection);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} else if (msg instanceof WaitingActiveExamHandle) {
			WaitingActiveExamHandle waitingActiveExamHandle = (WaitingActiveExamHandle) msg;
			if (waitingActiveExamHandle.getCommand().equals("ChangeTime")) {
				try {
					SqlUtilities.insertWaitingActiveExam(waitingActiveExamHandle.getWaitingActiveExam(), connection);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} else if (waitingActiveExamHandle.getCommand().equals("Approve")) {
				try {
					sendToAllClients("#ChangeTime" + " " + SqlUtilities
							.changeTimeActiveExam(waitingActiveExamHandle.getWaitingActiveExam(), connection));
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} else if (waitingActiveExamHandle.getCommand().equals("Remove")) {
				try {
					SqlUtilities.removeWaitingActiveExam(waitingActiveExamHandle.getWaitingActiveExam(), connection);
					client.sendToClient(Message.tableSaved);
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} else if (msg instanceof CheckedExamHandle) {
			CheckedExamHandle checkedExam = (CheckedExamHandle) msg;
			if (checkedExam.getCommand().equals("Approve")) {
				try {
					SqlUtilities.approveCheckedExam(checkedExam.getCheckedExam(), connection);
					client.sendToClient(Message.tableSaved);
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else if (checkedExam.getCommand().equals("Remove")) {
				try {
					SqlUtilities.removeCheckedExam(checkedExam.getCheckedExam(), connection);
					client.sendToClient(Message.tableSaved);
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else if (checkedExam.getCommand().equals("ChangeGrade")) {
				try {
					SqlUtilities.changeGradeByTeacher(checkedExam.getCheckedExam(), connection);
					client.sendToClient(Message.tableSaved);
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else if (checkedExam.getCommand().equals("AddComments")) {
				try {
					SqlUtilities.addCommentsByTeacher(checkedExam.getCheckedExam(), connection);
					client.sendToClient(Message.tableSaved);
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} else if (msg instanceof ReportHandle) {
			ReportHandle reportHandle = (ReportHandle) msg;
			if (reportHandle.getCommand().equals("StudentAverage")) {
				try {
					client.sendToClient(SqlUtilities.calculateStudentAverage(reportHandle, connection));
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else if (reportHandle.getCommand().equals("CourseAverage")) {
				try {
					client.sendToClient(SqlUtilities.calculateCourseAverage(reportHandle, connection));
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		else if (msg instanceof String) {
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
							String id = rs.getString(1);
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
											Message.studnet + " " + rs.getString(2) + " " + rs.getString(3) + " " + id);
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
								login.close();
								rs.close();
								return;
							}
						}
					} else {
						client.sendToClient(Message.noSuchUser);
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
				case Message.getExamBySubject:
					client.sendToClient(SqlUtilities.getExamsBySubject(connection, strArray[1]));
					break;
				case Message.getExecutionCode:
					client.sendToClient(SqlUtilities.getActiveExam(strArray[1], connection));
					break;
				case Message.getSubjects:
					client.sendToClient(
							SqlUtilities.getTypeFromDB(SqlUtilities.SELECT_Subjects, null, "Subjects", connection));
					break;
				case Message.getCourses:
					client.sendToClient(SqlUtilities.getTypeFromDB(SqlUtilities.SELECT_Courses_BY_SubjectID,
							strArray[1], "Courses", connection));
					break;
				case Message.getWaitingActiveExams:
					client.sendToClient(SqlUtilities.getWaitingActiveExam(connection));
					break;
				case Message.getCheckedExams:
					client.sendToClient(SqlUtilities.getCheckedExam(connection));
					break;
				case Message.getActiveExamsByActivator:
					client.sendToClient(SqlUtilities.getActiveExamsByActivator(strArray[1] + " " + strArray[2],
							strArray[3], connection));
					break;
				case Message.getStudents:
					client.sendToClient(SqlUtilities.getAllStudents(connection));
					break;
				case Message.getAllCourses:
					client.sendToClient(SqlUtilities.getAllCourses(connection));
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