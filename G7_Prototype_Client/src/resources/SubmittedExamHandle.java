package resources;

import java.io.Serializable;

public class SubmittedExamHandle implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String command;

	private SubmittedExam submittedExam;

	public SubmittedExamHandle(String command, SubmittedExam submittedExam) {
		super();
		this.command = command;
		this.submittedExam = submittedExam;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public SubmittedExam getSubmittedExam() {
		return submittedExam;
	}

	public void setSubmittedExam(SubmittedExam submittedExam) {
		this.submittedExam = submittedExam;
	}

}
