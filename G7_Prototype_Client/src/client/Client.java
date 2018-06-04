package client;

import java.io.IOException;
import java.util.ArrayList;

import controllers.IScreenController;
import controllers.LoginWindowController;
import controllers.PrincipalWindowController;
import controllers.ScreensController;
import controllers.StudentWindowController;
import controllers.TeacherWindowController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ocsf.client.AbstractClient;
import resources.Message;
import resources.Question;
import resources.QuestionsHandle;
import resources.Utilities;

public class Client extends AbstractClient implements IScreenController {

	// Class variables *************************************************

	// region Constants

	// end region -> Constants

	// region Fields

	private ObservableList<Question> questionsFromDB = FXCollections.observableArrayList();

	private ScreensController controller;

	private LoginWindowController loginWindowController;

	private TeacherWindowController teacherWindowController;

	private StudentWindowController studentWindowController;

	private PrincipalWindowController principalWindowController;

	private Question question;

	private String firstName = "";

	private String lastName = "";

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

	public ObservableList<Question> getQuestionsFromDB() {
		return questionsFromDB;
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

	/*
	 * set the questions observable list from the returned array list of
	 * SqlUtilities.getQuestions
	 */
	public void setQuestionsFromDB(ArrayList<Question> questions) {
		questionsFromDB.setAll(questions);
	}

	public Question getQuestion() {
		return question;
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
				controller.setScreen(MainAppClient.studentScreenID);
				studentWindowController.setFirstName(firstName);
				studentWindowController.setLastName(lastName);
				studentWindowController.setName();
				break;
			case "#No":
				System.out.println("Wrong Type");
				try {
					sendToServer(Message.logout);
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			case Message.tableSaved:
				System.out.println("Data Base Updated successfully");
				Utilities.popUpMethod("exam");
				break;
			case Message.userAlreadyConnected:
				loginWindowController.setLoginStatus("User Already Connected");
				break;
			case Message.noSuchUser:
				loginWindowController.setLoginStatus("Wrong Username or Pasword");
				break;
			case Message.getQuestionBySubject:
				break;
			}
		} else if (msg instanceof QuestionsHandle) {
			QuestionsHandle questionsHandle = (QuestionsHandle) msg;
			if (questionsHandle.getQuestionArray().isEmpty()) // if the table in the DB is empty
				return;
			else if (questionsHandle.getCommand().equals("All")) /* if it's from the questions table */
				setQuestionsFromDB(questionsHandle.getQuestionArray());
			else if (questionsHandle.getCommand().equals("Subject")) {
				setQuestionsFromDB(questionsHandle.getQuestionArray());
			}
		} else if (msg instanceof Integer) {
			question.concatenateQuestionCount((int) msg);
			try {
				sendToServer(new QuestionsHandle("Add", question));
			} catch (IOException e) {
				e.printStackTrace();
			}
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