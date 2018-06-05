package resources;

import java.io.Serializable;

public class ActiveExamHandle implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String command;

	private ActiveExam activeExam;

	public ActiveExamHandle(String command, ActiveExam activeExam) {
		super();
		this.command = command;
		this.activeExam = activeExam;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public ActiveExam getActiveExam() {
		return activeExam;
	}

	public void setActiveExam(ActiveExam activeExam) {
		this.activeExam = activeExam;
	}

}
