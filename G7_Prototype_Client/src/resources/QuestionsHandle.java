package resources;

import java.io.Serializable;
import java.util.ArrayList;

public class QuestionsHandle implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String command;

	private ArrayList<Question> questionArray;
	// = new ArrayList<Question>();

	private Question question;

	public QuestionsHandle(String command, Question question) {
		this.command = command;
		this.question = question;
	}

	public QuestionsHandle(String command, ArrayList<Question> questionArray) {
		this.command = command;
		this.questionArray = questionArray;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public ArrayList<Question> getQuestionArray() {
		return questionArray;
	}

	public void setQuestionArray(ArrayList<Question> questionArray) {
		this.questionArray = questionArray;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

}