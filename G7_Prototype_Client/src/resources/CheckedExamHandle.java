package resources;

import java.io.Serializable;
import java.util.ArrayList;

public class CheckedExamHandle implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String command;
	
	private CheckedExam checkedExam;
	
	private ArrayList<CheckedExam> checkedExams;

	public CheckedExamHandle(String command, CheckedExam checkedExam) {
		this.command = command;
		this.checkedExam = checkedExam;
	}
	
	public CheckedExamHandle(String command, ArrayList<CheckedExam> checkedExams) {
		this.command = command;
		this.checkedExams = checkedExams;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public CheckedExam getCheckedExam() {
		return checkedExam;
	}

	public void setCheckedExam(CheckedExam checkedExam) {
		this.checkedExam = checkedExam;
	}

	public ArrayList<CheckedExam> getCheckedExams() {
		return checkedExams;
	}

	public void setCheckedExams(ArrayList<CheckedExam> checkedExams) {
		this.checkedExams = checkedExams;
	}

	

}
