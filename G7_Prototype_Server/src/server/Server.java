package server;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ocsf.server.AbstractServer;
import ocsf.server.ConnectionToClient;
import resources.*;

/**
 * This class handles the client requests. Only this class works with the data
 * base directly.
 * 
 * @author Group 7
 *
 */
public class Server extends AbstractServer {

	/******************** Attributes ********************/

	@SuppressWarnings("unused")
	private int DEFAULT_PORT = 5555;

	private Connection connection;

	private String userNameDBcon = "app";

	private String passWordDBcon = "project7";

	/******************** Constructors ********************/

	/**
	 * 
	 * @param port
	 *            Port Number
	 */
	public Server(int port) {
		super(port);
	}

	/******************** Getters and Setters ********************/

	/**
	 * 
	 * @return The connection
	 */
	public Connection getConnection() {
		return connection;
	}

	/**
	 * 
	 * @param connection
	 *            Connection to the server
	 */
	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	/**
	 * 
	 * @return The user name to connect to the data base
	 */
	public String getUserNameDBcon() {
		return userNameDBcon;
	}

	/**
	 * 
	 * @param userNameDBcon
	 *            The user name to connect to the data base
	 */
	public void setUserNameDBcon(String userNameDBcon) {
		this.userNameDBcon = userNameDBcon;
	}

	/**
	 * 
	 * @return The password to connect to the data base
	 */
	public String getPassWordDBcon() {
		return passWordDBcon;
	}

	/**
	 * 
	 * @param passWordDBcon
	 *            The password to connect to the data base
	 */
	public void setPassWordDBcon(String passWordDBcon) {
		this.passWordDBcon = passWordDBcon;
	}

	/******************** Methods ********************/

	/**
	 * When this single slot method called by the framework, it provides as
	 * arguments the message received as well as the instance of ConnectionToClient
	 * corresponding to the client that sent the message.
	 */
	@Override
	protected void handleMessageFromClient(Object msg, ConnectionToClient client) {
		if (msg == null) {
			System.out.println("error window");
			return;
		} else if (msg instanceof QuestionHandle) {
			/**
			 * The client sends questions for the server in order to insert them to the
			 * database
			 */
			QuestionHandle questionsHandle = (QuestionHandle) msg;
			if (questionsHandle.getCommand().equals("Delete")) { /** question to remove */
				try {
					SqlUtilities.removeQuestions(questionsHandle.getQuestionArray(), connection);
					client.sendToClient(Message.tableSaved);
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else if (questionsHandle.getCommand().equals("Add")) { /** new question to add */
				try {
					SqlUtilities.insertNewQuestion(questionsHandle.getQuestion(), connection);
					client.sendToClient(Message.tableSaved);
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else if (questionsHandle.getCommand().equals("All")) { /** if it's from the questions table */
				try {
					SqlUtilities.editQuestionsTable(questionsHandle.getQuestionArray(), connection);
					client.sendToClient(Message.tableSaved);
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} else if (msg instanceof SubmittedExamHandle) {
			/**
			 * The client sends submitted exam for the server in order to insert them to the
			 * database
			 */
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
			/**
			 * The client sends exams for the server in order to insert them to the database
			 */
			ExamHandle examHandle = (ExamHandle) msg;
			if (examHandle.getCommand().equals(Message.exam))
				try {
					SqlUtilities.insertNewExam(examHandle.getExam(), connection);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			else if (examHandle.getCommand().equals(Message.deleteExam))
				try {
					SqlUtilities.deleteExam(examHandle.getExam(), connection);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			try {
				client.sendToClient(Message.tableSaved);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (msg instanceof ActiveExamHandle) {
			/**
			 * The client sends active exam for the server in order to insert her to the
			 * database
			 */
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
					/**
					 * send to the teacher all the students that copied in the exam.
					 */
					StudentHandle studentHandele = SqlUtilities.findExamHaveExamineesThatCopy(activeExamHandle,
							connection);
					if (!studentHandele.getCopeied().keySet().isEmpty()) {
						client.sendToClient(studentHandele);
					}
				} catch (SQLException | IOException e) {
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
			/**
			 * The client sends .docx file that is a manual exam for the server in order to
			 * insert exams folder
			 */
			MyFileHandle myFileHandle = (MyFileHandle) msg;
			if (myFileHandle.getCommand().equals("UploadExam")) {
				String[] strArray = myFileHandle.getFile().getFileName().split("/");
				myFileHandle.getFile().setFileName(strArray[0] + "/uploadedExams/" + strArray[2]);
				Utilities_Client.writeWordFile(myFileHandle.getFile(), false);
			}
		} else if (msg instanceof ExecutionCodeHandle) {
			/**
			 * The client sends an execution code for the server in order to check if exists
			 */
			ExecutionCodeHandle executionCodeHandle = (ExecutionCodeHandle) msg;
			try {
				client.sendToClient(SqlUtilities.checkCode(executionCodeHandle, connection));
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (msg instanceof StudentInActiveExamHandle) {
			/**
			 * The client sends an student that started an exam for the server in order to
			 * insert them to the database
			 */
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
			/**
			 * The client sends an exam for the server in order to insert them to the
			 * database by the following command
			 */
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
			} else if (waitingActiveExamHandle.getCommand().equals(Message.requestRejected)) {
				/** Show the pop up for the teacher */
				try {
					String activatorsID = SqlUtilities.getActivatorsID(
							waitingActiveExamHandle.getWaitingActiveExam().getActiveExam().getExam().getSubjectID(),
							waitingActiveExamHandle.getWaitingActiveExam().getActiveExam().getExam().getCourseID(),
							waitingActiveExamHandle.getWaitingActiveExam().getActiveExam().getExam().getExamNum(),
							connection);
					sendToAllClients(Message.requestRejected + " " + activatorsID);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} else if (waitingActiveExamHandle.getCommand().equals(Message.requestApproved)) {
				try {
					String activatorsID = SqlUtilities.getActivatorsID(
							waitingActiveExamHandle.getWaitingActiveExam().getActiveExam().getExam().getSubjectID(),
							waitingActiveExamHandle.getWaitingActiveExam().getActiveExam().getExam().getCourseID(),
							waitingActiveExamHandle.getWaitingActiveExam().getActiveExam().getExam().getExamNum(),
							connection);
					sendToAllClients(Message.requestApproved + " " + activatorsID);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} else if (msg instanceof CheckedExamHandle) {
			/**
			 * The client sends an checked exam for the server in order to insert them to
			 * the database by the following command
			 */
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
		} else if (msg instanceof StudentHandle) {
			/**
			 * The client sends an students for the server in order to insert them to the
			 * database by the following command
			 */
			StudentHandle studentHandle = (StudentHandle) msg;
			if (studentHandle.getCommand().equals("SolvedExamsOfStudent")) {
				try {
					client.sendToClient(SqlUtilities.getSolvedExamsByStudent(studentHandle, connection));
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		} else if (msg instanceof ReportHandle) {
			/**
			 * The client sends a report for the server in order to insert them to the
			 * database by the following command
			 */
			ReportHandle reportHandle = (ReportHandle) msg;
			if (reportHandle.getCommand().equals("StudentStatistic")) {
				try {
					client.sendToClient(SqlUtilities.calculateStudentStatistic(reportHandle, connection));
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else if (reportHandle.getCommand().equals("CourseStatistic")) {
				try {
					client.sendToClient(SqlUtilities.calculateCourseStatistic(reportHandle, connection));
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else if (reportHandle.getCommand().equals("TeacherStatistic")) {
				try {
					client.sendToClient(SqlUtilities.calculateTeacherStatistic(reportHandle, connection));
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		} else if (msg instanceof ExamReportHandle) {
			/**
			 * The client sends an exam report for the server in order to insert them to the
			 * database by the following command
			 */
			ExamReportHandle examReportHandle = (ExamReportHandle) msg;
			if (examReportHandle.getCommand().equals("ExamStatistic")) {
				try {
					client.sendToClient(SqlUtilities.calculateExamStatistic(examReportHandle, connection));
				} catch (IOException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} else if (msg instanceof String) {
			String str = (String) msg;
			String[] strArray = str.split(" ");
			try {
				switch (strArray[0]) {
				/**
				 * The client sends the user's name and password for the server in order to
				 * authenticate them with the database
				 */
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
				case Message.getExamByCourse:
					client.sendToClient(SqlUtilities.getExamsByCourse(connection, strArray[1]));
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
				case Message.getSubjectsByTeacherID:
					client.sendToClient(SqlUtilities.getTypeFromDB(SqlUtilities.SELECT_Subjects_By_Teacher_ID,
							strArray[1], "Subjects", connection));
					break;
				case Message.getExamsByAuthor:
					client.sendToClient(SqlUtilities.getTypeFromDB(SqlUtilities.SELECT_Exams_By_Author_and_Course,
							strArray[1] + " " + strArray[2] + " " + strArray[3], "ExamNumbers", connection));
					break;
				case Message.getAllStudents:
					client.sendToClient(
							SqlUtilities.getTypeFromDB(SqlUtilities.SELECT_All_Students, null, "Students", connection));
				case Message.getWaitingActiveExams:
					client.sendToClient(SqlUtilities.getWaitingActiveExam(connection));
					break;
				case Message.getCheckedExams:
					client.sendToClient(SqlUtilities.getCheckedExam(strArray[1], connection));
					break;
				case Message.getApprovedExamForStudent:
					client.sendToClient(
							SqlUtilities.getApprovedExamForStudent(strArray[1], strArray[2], strArray[3], connection));
					break;
				case Message.getActiveExamsByActivator:
					client.sendToClient(
							SqlUtilities.getActiveExamsByActivatorsID(strArray[1], strArray[2], connection));
					break;
				case Message.getStudents:
					client.sendToClient(SqlUtilities.getAllStudents(connection));
					break;
				case Message.getAllCourses:
					client.sendToClient(SqlUtilities.getAllCourses(connection));
					break;
				case Message.getAllTeachers:
					client.sendToClient(SqlUtilities.getAllTeachers(connection));
					break;
				case Message.getAnswers:
					client.sendToClient(SqlUtilities.getAnswers(strArray[1], strArray[2], strArray[3], strArray[4],
							strArray[5], strArray[6], connection));
					break;
				case Message.getQuestionInExam:
					client.sendToClient(SqlUtilities.getQuestionInExam(strArray[1], connection));
					break;
				case Message.getActiveExamBySubject:
					client.sendToClient(SqlUtilities.getActiveExamsBySubject(strArray[1], connection));
					break;
				case Message.getQuestionsFromSpecificExam:
					client.sendToClient(
							SqlUtilities.getQuestionsInGeneralExam(strArray[1], strArray[2], strArray[3], connection));
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

} // end of class