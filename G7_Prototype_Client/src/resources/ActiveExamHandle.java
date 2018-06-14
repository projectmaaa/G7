package resources;

import java.io.Serializable;
import java.util.ArrayList;

public class ActiveExamHandle implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String command;

	private ActiveExam activeExam;

	private String userID;
	
	private ArrayList<ActiveExam> activeExams;

	public ActiveExamHandle(String command, ActiveExam activeExam) {
		super();
		this.command = command;
		this.activeExam = activeExam;
	}

	public ActiveExamHandle(String command, ActiveExam activeExam, String userID) {
		super();
		this.command = command;
		this.activeExam = activeExam;
		this.userID = userID;
	}

	public ActiveExamHandle(String command, ArrayList<ActiveExam> activeExams) {
		this.command = command;
		this.activeExams = activeExams;
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

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public ArrayList<ActiveExam> getActiveExams() {
		return activeExams;
	}

	public void setActiveExams(ArrayList<ActiveExam> activeExams) {
		this.activeExams = activeExams;
	}

}
