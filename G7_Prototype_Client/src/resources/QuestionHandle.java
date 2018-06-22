package resources;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * The class that holds the question\s & the command what to do with the
 * question\s. This class is the message that sent between the client & the
 * server.
 * 
 * @author Group 7
 *
 */
public class QuestionHandle implements Serializable {

	/******************** Attributes ********************/

	private static final long serialVersionUID = 1L;

	private String command;

	private ArrayList<Question> questionArray;

	private Question question;

	/******************** Constructors ********************/

	/**
	 * 
	 * @param command
	 * @param question
	 */
	public QuestionHandle(String command, Question question) {
		this.command = command;
		this.question = question;
	}

	/**
	 * 
	 * @param command
	 * @param questionArray
	 */
	public QuestionHandle(String command, ArrayList<Question> questionArray) {
		this.command = command;
		this.questionArray = questionArray;
	}

	/******************** Getters & Setters ********************/

	/**
	 * 
	 * @return The message what to do with the question\s
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
	 * @return The questions
	 */
	public ArrayList<Question> getQuestionArray() {
		return questionArray;
	}

	/**
	 * 
	 * @param questionArray
	 */
	public void setQuestionArray(ArrayList<Question> questionArray) {
		this.questionArray = questionArray;
	}

	/**
	 * 
	 * @return The question
	 */
	public Question getQuestion() {
		return question;
	}

	/**
	 * 
	 * @param question
	 */
	public void setQuestion(Question question) {
		this.question = question;
	}

} // end of class