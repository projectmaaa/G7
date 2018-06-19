package resources;

import java.io.Serializable;
import java.util.ArrayList;

public class StudentAnswerInQuestionHandle implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String command;

	private ArrayList<StudentAnswerInQuestion> studentAnswers;

	public StudentAnswerInQuestionHandle(String command, ArrayList<StudentAnswerInQuestion> studentAnswers) {
		super();
		this.command = command;
		this.studentAnswers = studentAnswers;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public ArrayList<StudentAnswerInQuestion> getStudentAnswers() {
		return studentAnswers;
	}

	public void setStudentAnswers(ArrayList<StudentAnswerInQuestion> studentAnswers) {
		this.studentAnswers = studentAnswers;
	}

}
