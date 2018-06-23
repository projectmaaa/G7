package resources;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * The class that holds the approved exam\s and the command what to do with the
 * exam\s. This class is the message that sent between the client and the
 * server.
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
	 *            What to do
	 * @param approvedExamForStudentsArray
	 *            Approved Exams
	 */
	public ApprovedExamForStudentHandle(String command,
			ArrayList<ApprovedExamForStudent> approvedExamForStudentsArray) {
		this.command = command;
		this.approvedExamForStudentsArray = approvedExamForStudentsArray;
	}

	/**
	 * 
	 * @param command
	 *            What to do
	 * @param approvedExamForStudent
	 *            Approved Exams
	 */
	public ApprovedExamForStudentHandle(String command, ApprovedExamForStudent approvedExamForStudent) {
		this.command = command;
		this.approvedExamForStudent = approvedExamForStudent;
	}

	/******************** Getters and Setters ********************/

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
	 *            What to do
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
	 *            The approved exam
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
	 *            The approved exams
	 */
	public void setApprovedExamForStudentsArray(ArrayList<ApprovedExamForStudent> approvedExamForStudentsArray) {
		this.approvedExamForStudentsArray = approvedExamForStudentsArray;
	}

} // end of class