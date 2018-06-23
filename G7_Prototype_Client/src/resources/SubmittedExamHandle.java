package resources;

import java.io.Serializable;

/**
 * The class that holds the submitted exam and the command what to do with the
 * exam. This class is the message that sent between the client and the server.
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
	 *            What To Do
	 * @param submittedExam
	 *            Submitted Exam
	 */
	public SubmittedExamHandle(String command, SubmittedExam submittedExam) {
		this.command = command;
		this.submittedExam = submittedExam;
	}

	/******************** Getters and Setters ********************/

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
	 *            The message what to do with the submitted exam
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
	 *            The exam
	 */
	public void setSubmittedExam(SubmittedExam submittedExam) {
		this.submittedExam = submittedExam;
	}

} // end of class