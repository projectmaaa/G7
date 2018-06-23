package resources;

import java.io.Serializable;

/**
 * The class that holds the student in active exam and the command what to do
 * with the student. This class is the message that sent between the client and
 * the server.
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
	 *            What To Do
	 * @param studentInActiveExam
	 *            Student In Active Exam
	 */
	public StudentInActiveExamHandle(String command, StudentInActiveExam studentInActiveExam) {
		this.command = command;
		this.studentInActiveExam = studentInActiveExam;
	}

	/******************** Getters and Setters ********************/

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
	 *            The message what to do with the student
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
	 *            The student in active exam
	 */
	public void setStudentInActiveExam(StudentInActiveExam studentInActiveExam) {
		this.studentInActiveExam = studentInActiveExam;
	}

} // end of class