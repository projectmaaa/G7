package resources;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * This class handles active exam that waiting for approvement or rejection of
 * principal of changing time
 * 
 * @author Group7
 *
 */

public class WaitingActiveExamHandle implements Serializable {

	/**
	 * Class attributes
	 */
	private static final long serialVersionUID = 1L;

	private String command;

	private WaitingActiveExam waitingActiveExam;

	private ArrayList<WaitingActiveExam> waitingActiveExams;

	public WaitingActiveExamHandle(String command, WaitingActiveExam waitingActiveExam) {
		super();
		this.command = command;
		this.waitingActiveExam = waitingActiveExam;
	}

	public WaitingActiveExamHandle(String command, ArrayList<WaitingActiveExam> waitingActiveExams) {
		super();
		this.command = command;
		this.waitingActiveExams = waitingActiveExams;
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

	public ArrayList<WaitingActiveExam> getWaitingActiveExams() {
		return waitingActiveExams;
	}

	public void setWaitingActiveExams(ArrayList<WaitingActiveExam> waitingActiveExams) {
		this.waitingActiveExams = waitingActiveExams;
	}

}