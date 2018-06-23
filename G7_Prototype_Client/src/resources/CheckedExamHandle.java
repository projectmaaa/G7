package resources;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * The class that holds the checked exam\s and the command what to do with the
 * exam\s. This class is the message that sent between the client and the
 * server.
 * 
 * @author Group 7
 *
 */
public class CheckedExamHandle implements Serializable {

	/******************** Attributes ********************/

	private static final long serialVersionUID = 1L;

	private String command;

	private CheckedExam checkedExam;

	private ArrayList<CheckedExam> checkedExams;

	/******************** Constructors ********************/

	/**
	 * 
	 * @param command
	 *            What To Do
	 * @param checkedExam
	 *            Checked Exam
	 */
	public CheckedExamHandle(String command, CheckedExam checkedExam) {
		this.command = command;
		this.checkedExam = checkedExam;
	}

	/**
	 * 
	 * @param command
	 *            What To Do
	 * @param checkedExams
	 *            Checked Exams
	 */
	public CheckedExamHandle(String command, ArrayList<CheckedExam> checkedExams) {
		this.command = command;
		this.checkedExams = checkedExams;
	}

	/******************** Getters and Setters ********************/

	/**
	 * 
	 * @return The message what to do with the checked exam\s
	 */
	public String getCommand() {
		return command;
	}

	/**
	 * 
	 * @param command
	 *            The message what to do with the checked exam\s
	 */
	public void setCommand(String command) {
		this.command = command;
	}

	/**
	 * 
	 * @return The checked exam
	 */
	public CheckedExam getCheckedExam() {
		return checkedExam;
	}

	/**
	 * 
	 * @param checkedExam
	 *            The checked exam
	 */
	public void setCheckedExam(CheckedExam checkedExam) {
		this.checkedExam = checkedExam;
	}

	/**
	 * 
	 * @return The checked exams
	 */
	public ArrayList<CheckedExam> getCheckedExams() {
		return checkedExams;
	}

	/**
	 * 
	 * @param checkedExams
	 *            The checked exams
	 */
	public void setCheckedExams(ArrayList<CheckedExam> checkedExams) {
		this.checkedExams = checkedExams;
	}

} // end of class