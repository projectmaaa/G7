package resources;

import java.io.Serializable;

public class WaitingActiveExamHandle implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String command;

	private WaitingActiveExam waitingActiveExam;

	public WaitingActiveExamHandle(String command, WaitingActiveExam waitingActiveExam) {
		super();
		this.command = command;
		this.waitingActiveExam = waitingActiveExam;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public WaitingActiveExam getWaitingActiveExam() {
		return waitingActiveExam;
	}

	public void setWaitingActiveExam(WaitingActiveExam waitingActiveExam) {
		this.waitingActiveExam = waitingActiveExam;
	}

}