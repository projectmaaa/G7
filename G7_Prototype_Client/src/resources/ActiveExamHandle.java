package resources;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * The class that holds the active exam\s and the command what to do with the
 * exam\s. This class is the message that sent between the client and the
 * server.
 * 
 * @author Group 7
 *
 */
public class ActiveExamHandle implements Serializable {

	/******************** Attributes ********************/

	private static final long serialVersionUID = 1L;

	private String command;

	private ActiveExam activeExam;

	private String userID;

	private ArrayList<ActiveExam> activeExams;

	/******************** Constructors ********************/

	/**
	 * 
	 * @param command
	 *            What to do
	 * @param activeExam
	 *            The exam
	 */
	public ActiveExamHandle(String command, ActiveExam activeExam) {
		this.command = command;
		this.activeExam = activeExam;
	}

	/**
	 * 
	 * @param command
	 *            What to do
	 * @param activeExam
	 *            The exam
	 * @param userID
	 *            User's ID
	 */
	public ActiveExamHandle(String command, ActiveExam activeExam, String userID) {
		this.command = command;
		this.activeExam = activeExam;
		this.userID = userID;
	}

	/**
	 * 
	 * @param command
	 *            What to do
	 * @param activeExams
	 *            The exam
	 */
	public ActiveExamHandle(String command, ArrayList<ActiveExam> activeExams) {
		this.command = command;
		this.activeExams = activeExams;
	}

	/******************** Getters and Setters ********************/

	/**
	 * 
	 * @return The message what to do with the active exam\s
	 */
	public String getCommand() {
		return command;
	}

	/**
	 * 
	 * @param command
	 *            What to do
	 */
	public void setCommand(String command) {
		this.command = command;
	}

	/**
	 * 
	 * @return The active exam
	 */
	public ActiveExam getActiveExam() {
		return activeExam;
	}

	/**
	 * 
	 * @param activeExam
	 *            The active exam
	 */
	public void setActiveExam(ActiveExam activeExam) {
		this.activeExam = activeExam;
	}

	/**
	 * 
	 * @return The activator's ID
	 */
	public String getUserID() {
		return userID;
	}

	/**
	 * 
	 * @param userID
	 *            The activator's ID
	 */
	public void setUserID(String userID) {
		this.userID = userID;
	}

	/**
	 * 
	 * @return The active exams
	 */
	public ArrayList<ActiveExam> getActiveExams() {
		return activeExams;
	}

	/**
	 * 
	 * @param activeExams
	 *            The active exams
	 */
	public void setActiveExams(ArrayList<ActiveExam> activeExams) {
		this.activeExams = activeExams;
	}

} // end of class