package resources;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * The class that holds the request\s and the command what to do with the
 * request\s. This class is the message that sent between the client and the
 * server.
 * 
 * @author Group 7
 *
 */
public class WaitingActiveExamHandle implements Serializable {

	/******************** Attributes ********************/

	private static final long serialVersionUID = 1L;

	private String command;

	private WaitingActiveExam waitingActiveExam;

	private ArrayList<WaitingActiveExam> waitingActiveExams;

	/******************** Constructors ********************/

	/**
	 * 
	 * @param command
	 *            What To Do
	 * @param waitingActiveExam
	 *            The Requested Exam
	 */
	public WaitingActiveExamHandle(String command, WaitingActiveExam waitingActiveExam) {
		this.command = command;
		this.waitingActiveExam = waitingActiveExam;
	}

	/**
	 * 
	 * @param command
	 *            What To Do
	 * @param waitingActiveExams
	 *            The Requested Exams
	 */
	public WaitingActiveExamHandle(String command, ArrayList<WaitingActiveExam> waitingActiveExams) {
		this.command = command;
		this.waitingActiveExams = waitingActiveExams;
	}

	/******************** Getters and Setters ********************/

	/**
	 * 
	 * @return The message what to do with the request\s
	 */
	public String getCommand() {
		return command;
	}

	/**
	 * 
	 * @param command
	 *            The message what to do with the request\s
	 */
	public void setCommand(String command) {
		this.command = command;
	}

	/**
	 * 
	 * @return The exam
	 */
	public WaitingActiveExam getWaitingActiveExam() {
		return waitingActiveExam;
	}

	/**
	 * 
	 * @param waitingActiveExam
	 *            The exam
	 */
	public void setWaitingActiveExam(WaitingActiveExam waitingActiveExam) {
		this.waitingActiveExam = waitingActiveExam;
	}

	/**
	 * 
	 * @return The exams
	 */
	public ArrayList<WaitingActiveExam> getWaitingActiveExams() {
		return waitingActiveExams;
	}

	/**
	 * 
	 * @param waitingActiveExams
	 *            The exams
	 */
	public void setWaitingActiveExams(ArrayList<WaitingActiveExam> waitingActiveExams) {
		this.waitingActiveExams = waitingActiveExams;
	}

} // end of class