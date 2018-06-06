package resources;

import java.io.Serializable;
import java.util.ArrayList;

public class Question implements Serializable {

	// region Constants

	private static final long serialVersionUID = 1L;

	// end region -> Constants

	// region Fields

	private String subjectID;

	private String questionNum;

	private String author;

	private String questionText;

	private String firstPossibleAnswer;

	private String secondPossibleAnswer;

	private String thirdPossibleAnswer;

	private String fourthPossibleAnswer;

	private String correctAnswer;

	// end region -> Fields

	// region Constructors

	/**
	 * this constructor is for creating new question
	 */
	public Question(String subjectID, String author, String questionText, String firstPossibleAnswer,
			String secondPossibleAnswer, String thirdPossibleAnswer, String fourthPossibleAnswer,
			String correctAnswer) {
		this.subjectID = subjectID;
		this.author = author;
		this.questionText = questionText;
		this.firstPossibleAnswer = firstPossibleAnswer;
		this.secondPossibleAnswer = secondPossibleAnswer;
		this.thirdPossibleAnswer = thirdPossibleAnswer;
		this.fourthPossibleAnswer = fourthPossibleAnswer;
		this.correctAnswer = correctAnswer;
	}

	/**
	 * this constructor is when getting the info from the data base to show in the
	 * table view of the whole questions
	 */
	public Question(String subjectID, String questionNum, String author, String questionText,
			ArrayList<String> possibleAnswers, String correctAnswer) {
		this.subjectID = subjectID;
		this.questionNum = questionNum;
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

	public String getQuestionNum() {
		return questionNum;
	}

	public void setQuestionNum(String questionNum) {
		this.questionNum = questionNum;
	}

	public String getSubjectID() {
		return subjectID;
	}

	public void setSubjectID(String subjectID) {
		this.subjectID = subjectID;
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

	@Override
	public String toString() {
		return "Question [subjectID=" + subjectID + ", questionNum=" + questionNum + ", author=" + author
				+ ", questionText=" + questionText + ", firstPossibleAnswer=" + firstPossibleAnswer
				+ ", secondPossibleAnswer=" + secondPossibleAnswer + ", thirdPossibleAnswer=" + thirdPossibleAnswer
				+ ", fourthPossibleAnswer=" + fourthPossibleAnswer + ", correctAnswer=" + correctAnswer + "]";
	}

}// end of class Question