package resources;

import java.io.Serializable;

/**
 * The class that holds the submitted exam & the command what to do with the
 * exam. This class is the message that sent between the client & the server.
 * 
 * @author Group 7
 *
 */
public class SubmittedExamHandle implements Serializable {

	/******************** Attributes ********************/

	private static final long serialVersionUID = 1L;

	private String command;

	private SubmittedExam submittedExam;

	/******************** Constructors ********************/

	/**
	 * 
	 * @param command
	 * @param submittedExam
	 */
	public SubmittedExamHandle(String command, SubmittedExam submittedExam) {
		this.command = command;
		this.submittedExam = submittedExam;
	}

	/******************** Getters & Setters ********************/

	/**
	 * 
	 * @return The message what to do with the submitted exam
	 */
	public String getCommand() {
		return command;
	}

	/**
	 * 
	 * @param command
	 */
	public void setCommand(String command) {
		this.command = command;
	}

	/**
	 * 
	 * @return The exam
	 */
	public SubmittedExam getSubmittedExam() {
		return submittedExam;
	}

	/**
	 * 
	 * @param submittedExam
	 */
	public void setSubmittedExam(SubmittedExam submittedExam) {
		this.submittedExam = submittedExam;
	}

} // end of class