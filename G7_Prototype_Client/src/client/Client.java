package client;

import java.io.IOException;
import java.util.ArrayList;

import controllers.IScreenController;
import controllers.ScreensController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ocsf.client.AbstractClient;
import resources.Question;

public class Client extends AbstractClient implements IScreenController {

	// Class variables *************************************************

	// region Constants

	/**
	 * The default port to connect on.
	 */
	final public static int DEFAULT_PORT = 5555;

	// end region -> Constants

	// region Fields

	private ObservableList<Question> questionsFromDB = FXCollections.observableArrayList();

	private ScreensController myController;

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
	public Client(String host, int port, ScreensController screenParent) {
		super(host, port); // Call the superclass constructor
		setScreenParent(screenParent);
		try {
			openConnection();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	// region Setters

	public void setScreenParent(ScreensController screenParent) {
		myController = screenParent;
	}

	public ObservableList<Question> getQuestionsFromDB() {
		return questionsFromDB;
	}

	/*
	 * set the questions observable list from the returned array list of
	 * SqlUtilities.getQuestions
	 */
	public void setQuestionsFromDB(ArrayList<Question> questions) {
		for (Question question : questions)
			questionsFromDB.add(question);
	}

	// end region -> Setters

	// region Public Methods

	/**
	 * This method handles all data that comes in from the server.
	 *
	 * @param msg
	 *            The message from the server.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void handleMessageFromServer(Object msg) {
		if (msg == null) {
			// add something
			return;
		}
		if (msg instanceof String) {
			String str = (String) msg;
			switch (str) {
			case "#Teacher":
				try {
					myController.setScreen(MainApp.teacherScreenID);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case "#No":
				System.out.println("Wrong login details");
				break;
			case "#TableSaved":
				System.out.println("Data Base Updated successfully");
				break;
			case "#UserAlreadyConnected":
				// myController.getScreen(MainApp.loginScreenID).getStyleClass()
			}
		}
		if (msg instanceof ArrayList<?>) {
			if (((ArrayList<?>) msg).get(0) instanceof Question) /* if it's from the questions table */
				setQuestionsFromDB((ArrayList<Question>) msg);
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