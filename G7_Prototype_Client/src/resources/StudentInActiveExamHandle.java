package resources;

import java.io.Serializable;

/**
 * The class that holds the student in active exam & the command what to do with
 * the student. This class is the message that sent between the client & the
 * server.
 * 
 * @author Group 7
 *
 */
public class StudentInActiveExamHandle implements Serializable {

	/******************** Attributes ********************/

	private static final long serialVersionUID = 1L;

	private String command;

	private StudentInActiveExam studentInActiveExam;

	/******************** Constructors ********************/

	/**
	 * 
	 * @param command
	 * @param studentInActiveExam
	 */
	public StudentInActiveExamHandle(String command, StudentInActiveExam studentInActiveExam) {
		this.command = command;
		this.studentInActiveExam = studentInActiveExam;
	}

	/******************** Getters & Setters ********************/

	/**
	 * 
	 * @return The message what to do with the student
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
	 * @return The student in active exam
	 */
	public StudentInActiveExam getStudentInActiveExam() {
		return studentInActiveExam;
	}

	/**
	 * 
	 * @param studentInActiveExam
	 */
	public void setStudentInActiveExam(StudentInActiveExam studentInActiveExam) {
		this.studentInActiveExam = studentInActiveExam;
	}

} // end of class