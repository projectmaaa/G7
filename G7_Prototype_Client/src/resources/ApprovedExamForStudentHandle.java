package resources;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * The class that holds the approved exam\s & the command what to do with the
 * exam\s. This class is the message that sent between the client & the server.
 * 
 * @author Group 7
 *
 */
public class ApprovedExamForStudentHandle implements Serializable {

	/******************** Attributes ********************/

	private static final long serialVersionUID = 1L;

	private String command;

	private ApprovedExamForStudent approvedExamForStudent;

	private ArrayList<ApprovedExamForStudent> approvedExamForStudentsArray;

	/******************** Constructors ********************/

	/**
	 * 
	 * @param command
	 * @param approvedExamForStudentsArray
	 */
	public ApprovedExamForStudentHandle(String command,
			ArrayList<ApprovedExamForStudent> approvedExamForStudentsArray) {
		this.command = command;
		this.approvedExamForStudentsArray = approvedExamForStudentsArray;
	}

	/**
	 * 
	 * @param command
	 * @param approvedExamForStudent
	 */
	public ApprovedExamForStudentHandle(String command, ApprovedExamForStudent approvedExamForStudent) {
		this.command = command;
		this.approvedExamForStudent = approvedExamForStudent;
	}

	/******************** Getters & Setters ********************/

	/**
	 * 
	 * @return The message what to do with the approved exam\s
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
	 * @return The approved exam
	 */
	public ApprovedExamForStudent getApprovedExamForStudent() {
		return approvedExamForStudent;
	}

	/**
	 * 
	 * @param approvedExamForStudent
	 */
	public void setApprovedExamForStudent(ApprovedExamForStudent approvedExamForStudent) {
		this.approvedExamForStudent = approvedExamForStudent;
	}

	/**
	 * 
	 * @return The approved exams
	 */
	public ArrayList<ApprovedExamForStudent> getApprovedExamForStudentsArray() {
		return approvedExamForStudentsArray;
	}

	/**
	 * 
	 * @param approvedExamForStudentsArray
	 */
	public void setApprovedExamForStudentsArray(ArrayList<ApprovedExamForStudent> approvedExamForStudentsArray) {
		this.approvedExamForStudentsArray = approvedExamForStudentsArray;
	}

} // end of class