package resources;

import java.io.Serializable;
import java.util.ArrayList;

public class Question implements Serializable {

	// region Constants

	private static final long serialVersionUID = 1L;

	private static int count;

	// end region -> Constants

	// region Fields

	private String questionID;

	private String author;

	private String questionText;

	private String firstPossibleAnswer;

	private String secondPossibleAnswer;

	private String thirdPossibleAnswer;

	private String fourthPossibleAnswer;

	private String correctAnswer;

	// end region -> Fields

	// region Constructors

	/*
	 * this constructor is for creating new question
	 */
	public Question(String subject, String author, String questionText, String firstPossibleAnswer,
			String secondPossibleAnswer, String thirdPossibleAnswer, String fourthPossibleAnswer,
			String correctAnswer) {
		setNewQuestionID(subject);
		this.author = author;
		this.questionText = questionText;
		this.firstPossibleAnswer = firstPossibleAnswer;
		this.secondPossibleAnswer = secondPossibleAnswer;
		this.thirdPossibleAnswer = thirdPossibleAnswer;
		this.fourthPossibleAnswer = fourthPossibleAnswer;
		this.correctAnswer = correctAnswer;
	}

	/*
	 * this constructor is when getting the info from the data base to show in the
	 * table view
	 */
	public Question(String questionID, String author, String questionText, ArrayList<String> possibleAnswers,
			String correctAnswer) {
		this.questionID = questionID;
		this.author = author;
		this.questionText = questionText;
		this.firstPossibleAnswer = possibleAnswers.get(0);
		this.secondPossibleAnswer = possibleAnswers.get(1);
		this.thirdPossibleAnswer = possibleAnswers.get(2);
		this.fourthPossibleAnswer = possibleAnswers.get(3);
		this.correctAnswer = correctAnswer;
	}

	// end region -> Constructors

	// region Getters & Setters

	public String getQuestionID() {
		return questionID;
	}

	public void setQuestionID(String questionID) {
		this.questionID = questionID;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getQuestionText() {
		return questionText;
	}

	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}

	public String getFirstPossibleAnswer() {
		return firstPossibleAnswer;
	}

	public void setFirstPossibleAnswer(String firstPossibleAnswer) {
		this.firstPossibleAnswer = firstPossibleAnswer;
	}

	public String getSecondPossibleAnswer() {
		return secondPossibleAnswer;
	}

	public void setSecondPossibleAnswer(String secondPossibleAnswer) {
		this.secondPossibleAnswer = secondPossibleAnswer;
	}

	public String getThirdPossibleAnswer() {
		return thirdPossibleAnswer;
	}

	public void setThirdPossibleAnswer(String thirdPossibleAnswer) {
		this.thirdPossibleAnswer = thirdPossibleAnswer;
	}

	public String getFourthPossibleAnswer() {
		return fourthPossibleAnswer;
	}

	public void setFourthPossibleAnswer(String fourthPossibleAnswer) {
		this.fourthPossibleAnswer = fourthPossibleAnswer;
	}

	public String getCorrectAnswer() {
		return correctAnswer;
	}

	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}

	// end region -> Getters & Setters

	/*
	 * sets the question ID for new question
	 */
	private void setNewQuestionID(String subject) {
		count++;
		switch (subject) {
		case "Software":
			questionID += "01";
		case "Math":
			questionID += "02";
		}
		if (count < 10)
			questionID += "00";
		else if (count < 100)
			questionID += "0";
		questionID += count;
	}

}// end of class Question