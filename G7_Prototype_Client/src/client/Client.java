package client;

import java.io.IOException;
import java.util.ArrayList;
import controllers.IScreenController;
import controllers.LoginWindowController;
import controllers.PrincipalWindowController;
import controllers.ScreensController;
import controllers.StudentWindowController;
import controllers.TeacherWindowController;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ocsf.client.AbstractClient;
import resources.*;

public class Client extends AbstractClient implements IScreenController {

	// Class variables *************************************************

	// region Constants

	// end region -> Constants

	// region Fields

	private ObservableList<Question> questionsFromDB = FXCollections.observableArrayList();

	private ObservableList<Exam> examsFromDB = FXCollections.observableArrayList();

	private ObservableList<String> subjectsFromDB = FXCollections.observableArrayList();

	private ObservableList<String> coursesFromDB = FXCollections.observableArrayList();

	private ObservableList<WaitingActiveExam> WaitingActiveExamsFromDB = FXCollections.observableArrayList();

	private ScreensController controller;

	private LoginWindowController loginWindowController;

	private TeacherWindowController teacherWindowController;

	private StudentWindowController studentWindowController;

	private PrincipalWindowController principalWindowController;

	private Question question;

	private String firstName = "";

	private String lastName = "";

	private String id = "";
	
	private boolean executionCodeExistFlag;

	// end region -> Fields

	// region Constructors

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
	public Client(String host, int port) {
		super(host, port); // Call the superclass constructor
		try {
			openConnection();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	// region Setters

	public void setScreenParent(ScreensController screenParent) {
		controller = screenParent;
	}

	public LoginWindowController getLoginWindowController() {
		return loginWindowController;
	}

	public void setLoginWindowController(LoginWindowController loginWindowController) {
		this.loginWindowController = loginWindowController;
	}

	public TeacherWindowController getTeacherWindowController() {
		return teacherWindowController;
	}

	public void setTeacherWindowController(TeacherWindowController teacherWindowController) {
		this.teacherWindowController = teacherWindowController;
	}

	public StudentWindowController getStudentWindowController() {
		return studentWindowController;
	}

	public void setStudentWindowController(StudentWindowController studentWindowController) {
		this.studentWindowController = studentWindowController;
	}

	public PrincipalWindowController getPrincipalWindowController() {
		return principalWindowController;
	}

	public void setPrincipalWindowController(PrincipalWindowController principalWindowController) {
		this.principalWindowController = principalWindowController;
	}

	public ObservableList<Question> getQuestionsFromDB() {
		return questionsFromDB;
	}

	public void setQuestionsFromDB(ArrayList<Question> questions) {
		questionsFromDB.setAll(questions);
	}

	public Question getQuestion() {
		return question;
	}

	public ObservableList<Exam> getExamsFromDB() {
		return examsFromDB;
	}

	public void setExamsFromDB(ArrayList<Exam> exams) {
		examsFromDB.setAll(exams);
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public ObservableList<String> getSubjectsFromDB() {
		return subjectsFromDB;
	}

	public void setSubjectsFromDB(ArrayList<String> subjects) {
		Platform.runLater(() -> {
			subjectsFromDB.setAll(subjects);
		});
	}

	public ObservableList<String> getCoursesFromDB() {
		return coursesFromDB;
	}

	public void setCoursesFromDB(ArrayList<String> courses) {
		Platform.runLater(() -> {
			coursesFromDB.setAll(courses);
		});
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ObservableList<WaitingActiveExam> getWaitingActiveExamsFromDB() {
		return WaitingActiveExamsFromDB;
	}

	public void setWaitingActiveExamsFromDB(ArrayList<WaitingActiveExam> waitingActiveExamsFromDB) {
		Platform.runLater(() -> {
			WaitingActiveExamsFromDB.setAll(waitingActiveExamsFromDB);
		});
	}
	
	public boolean getExecutionCodeExistFlag() {
		return executionCodeExistFlag;
	}

	public void setExecutionCodeExistFlag(boolean executionCodeExistFlag) {
		this.executionCodeExistFlag = executionCodeExistFlag;
	}


	// end region -> Setters

	// region Public Methods

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
		} else if (msg instanceof String) {
			String str = (String) msg;
			String[] strArray = str.split(" ");
			switch (strArray[0]) {
			case Message.principal:
				firstName = strArray[1];
				lastName = strArray[2];
				controller.setScreen(MainAppClient.principalScreenID);
				principalWindowController.setNameAndLastName(firstName, lastName);
				break;
			case Message.teacher:
				firstName = strArray[1];
				lastName = strArray[2];
				controller.setScreen(MainAppClient.teacherScreenID);
				teacherWindowController.setNameAndLastName(firstName, lastName);
				break;
			case Message.studnet:
				firstName = strArray[1];
				lastName = strArray[2];
				id = strArray[3];
				controller.setScreen(MainAppClient.studentScreenID);
				studentWindowController.setFirstName(firstName);
				studentWindowController.setLastName(lastName);
				studentWindowController.setName();
				break;
			case "#ChangeTime":
				if ((studentWindowController != null)
						&& (studentWindowController.getActiveExam().getExecutionCode().equals(strArray[1]))) {
					System.out.println(this.firstName + " " + this.lastName);
					studentWindowController.getActiveExam().setDuration(Integer.parseInt(strArray[2]));
					studentWindowController.setSecondTimer();
				}
				break;
			case "#TableSaved":
				System.out.println("Data Base Updated successfully");
				break;
			case Message.userAlreadyConnected:
				loginWindowController.setLoginStatus("User Already Connected");
				break;
			case Message.noSuchUser:
				loginWindowController.setLoginStatus("Wrong Username or Pasword");
				break;
			case Message.getQuestionBySubject:
				break;
			default:
				break;
			}
		} else if (msg instanceof QuestionHandle) {
			QuestionHandle questionsHandle = (QuestionHandle) msg;
			if (questionsHandle.getQuestionArray().isEmpty()) // if the table in the DB is empty
				return;
			else if (questionsHandle.getCommand().equals("All")) /* if it's from the questions table */
				setQuestionsFromDB(questionsHandle.getQuestionArray());
			else if (questionsHandle.getCommand().equals("Subject")) {
				setQuestionsFromDB(questionsHandle.getQuestionArray());
			}
		} else if (msg instanceof ActiveExamHandle) {
			ActiveExamHandle activeExamsHandle = (ActiveExamHandle) msg;
			studentWindowController.setActiveExam(activeExamsHandle.getActiveExam());
		} else if (msg instanceof ExamHandle) {
			ExamHandle examsHandle = (ExamHandle) msg;
			if (examsHandle.getCommand().equals("Subject")) {
				setExamsFromDB(examsHandle.getExams());
			}
		} else if (msg instanceof TypeHandle) {
			TypeHandle typeHandle = (TypeHandle) msg;
			if (typeHandle.getCommand().equals("Subjects"))
				setSubjectsFromDB(typeHandle.getTypes());
			else if (typeHandle.getCommand().equals("Courses")) {
				setCoursesFromDB(typeHandle.getTypes());
			}
		} else if (msg instanceof WaitingActiveExamHandle) {
			WaitingActiveExamHandle waitingActiveExamHandle = (WaitingActiveExamHandle) msg;
			if (waitingActiveExamHandle.getCommand().equals("AllWaiting"))
				setWaitingActiveExamsFromDB(waitingActiveExamHandle.getWaitingActiveExams());
		} else if (msg instanceof Boolean) {
			boolean codeExist = (boolean) msg;
			setExecutionCodeExistFlag(codeExist);
		}
	}

	/**
	 * This method handles all data coming from the UI
	 *
	 * @param message
	 *            The message from the UI.
	 */
	public void handleMessageFromClientUI(Object message) {
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

	// end region -> Public Methods

}