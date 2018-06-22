package resources;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * The class that holds the answers of the student & the command what to do with
 * the answers. This class is the message that sent between the client & the
 * server.
 * 
 * @author Group 7
 *
 */
public class StudentAnswerInQuestionHandle implements Serializable {

	/******************** Attributes ********************/

	private static final long serialVersionUID = 1L;

	private String command;

	private ArrayList<StudentAnswerInQuestion> studentAnswers;

	/******************** Constructors ********************/

	/**
	 * 
	 * @param command
	 * @param studentAnswers
	 */
	public StudentAnswerInQuestionHandle(String command, ArrayList<StudentAnswerInQuestion> studentAnswers) {
		this.command = command;
		this.studentAnswers = studentAnswers;
	}

	/******************** Getters & Setters ********************/

	/**
	 * 
	 * @return The message what to do with the answers
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
	 * @return Student's answers
	 */
	public ArrayList<StudentAnswerInQuestion> getStudentAnswers() {
		return studentAnswers;
	}

	/**
	 * 
	 * @param studentAnswers
	 */
	public void setStudentAnswers(ArrayList<StudentAnswerInQuestion> studentAnswers) {
		this.studentAnswers = studentAnswers;
	}

} // end of class