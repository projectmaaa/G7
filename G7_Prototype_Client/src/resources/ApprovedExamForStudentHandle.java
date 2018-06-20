package resources;

import java.io.Serializable;
import java.util.ArrayList;

public class ApprovedExamForStudentHandle implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String command;

	private ApprovedExamForStudent approvedExamForStudent;

	private ArrayList<ApprovedExamForStudent> approvedExamForStudentsArray;

	public ApprovedExamForStudentHandle(String command,
			ArrayList<ApprovedExamForStudent> approvedExamForStudentsArray) {
		super();
		this.command = command;
		this.approvedExamForStudentsArray = approvedExamForStudentsArray;
	}

	public ApprovedExamForStudentHandle(String command, ApprovedExamForStudent approvedExamForStudent) {
		super();
		this.command = command;
		this.approvedExamForStudent = approvedExamForStudent;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public ApprovedExamForStudent getApprovedExamForStudent() {
		return approvedExamForStudent;
	}

	public void setApprovedExamForStudent(ApprovedExamForStudent approvedExamForStudent) {
		this.approvedExamForStudent = approvedExamForStudent;
	}

	public ArrayList<ApprovedExamForStudent> getApprovedExamForStudentsArray() {
		return approvedExamForStudentsArray;
	}

	public void setApprovedExamForStudentsArray(ArrayList<ApprovedExamForStudent> approvedExamForStudentsArray) {
		this.approvedExamForStudentsArray = approvedExamForStudentsArray;
	}

}
