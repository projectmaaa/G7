package resources;

import java.io.Serializable;
import java.util.ArrayList;

import javafx.scene.control.CheckBox;

public class Question implements Serializable {

	// region Constants

	private static final long serialVersionUID = 1L;

	// end region -> Constants

	// region Fields

	private String questionSubject;

	private String questionNum;

	private String author;

	private String questionText;

	private String firstPossibleAnswer;

	private String secondPossibleAnswer;

	private String thirdPossibleAnswer;

	private String fourthPossibleAnswer;

	private String correctAnswer;

//	private CheckBox select;

	private int points;

	// end region -> Fields

	// region Constructors

	/**
	 * this constructor is for creating new question
	 */
	public Question(String questionSubject, String author, String questionText, String firstPossibleAnswer,
			String secondPossibleAnswer, String thirdPossibleAnswer, String fourthPossibleAnswer,
			String correctAnswer) {
		setSubjectNumber(questionSubject);
		this.author = author;
		this.questionText = questionText;
		this.firstPossibleAnswer = firstPossibleAnswer;
		this.secondPossibleAnswer = secondPossibleAnswer;
		this.thirdPossibleAnswer = thirdPossibleAnswer;
		this.fourthPossibleAnswer = fourthPossibleAnswer;
		this.correctAnswer = correctAnswer;
//		this.select = new CheckBox();
	}

	// /**
	// *
	// * @param questionSubject
	// * @param questionNum
	// * @param questionText
	// * @param firstPossibleAnswer
	// * @param secondPossibleAnswer
	// * @param thirdPossibleAnswer
	// * @param fourthPossibleAnswer
	// * @param correctAnswer
	// * @param points
	// * this constructor is for the table of create exam
	// */
	// public Question(String questionSubject, String questionNum, String
	// questionText, String firstPossibleAnswer,
	// String secondPossibleAnswer, String thirdPossibleAnswer, String
	// fourthPossibleAnswer, String correctAnswer,
	// int points) {
	// setSubjectNumber(questionSubject);
	// this.questionNum = questionNum;
	// this.questionText = questionText;
	// this.firstPossibleAnswer = firstPossibleAnswer;
	// this.secondPossibleAnswer = secondPossibleAnswer;
	// this.thirdPossibleAnswer = thirdPossibleAnswer;
	// this.fourthPossibleAnswer = fourthPossibleAnswer;
	// this.correctAnswer = correctAnswer;
	// this.points = points;
	// }

	/**
	 * this constructor is when getting the info from the data base to show in the
	 * table view of the whole questions
	 */
	public Question(String questionSubject, String questionNum, String author, String questionText,
			ArrayList<String> possibleAnswers, String correctAnswer) {
		this.questionSubject = questionSubject;
		this.questionNum = questionNum;
		this.author = author;
		this.questionText = questionText;
		this.firstPossibleAnswer = possibleAnswers.get(0);
		this.secondPossibleAnswer = possibleAnswers.get(1);
		this.thirdPossibleAnswer = possibleAnswers.get(2);
		this.fourthPossibleAnswer = possibleAnswers.get(3);
		this.correctAnswer = correctAnswer;
//		this.select = new CheckBox();
	}

	/**
	 * this constructor is when getting the info from the data base to show in the
	 * table view of creating exam
	 */
	public Question(String questionSubject, String questionNum, String questionText, ArrayList<String> possibleAnswers,
			String correctAnswer, int points) {
		this.questionSubject = questionSubject;
		this.questionNum = questionNum;
		this.questionText = questionText;
		this.firstPossibleAnswer = possibleAnswers.get(0);
		this.secondPossibleAnswer = possibleAnswers.get(1);
		this.thirdPossibleAnswer = possibleAnswers.get(2);
		this.fourthPossibleAnswer = possibleAnswers.get(3);
		this.correctAnswer = correctAnswer;
//		this.select = new CheckBox();
		this.points = points;
	}

	// end region -> Constructors

	// region Getters & Setters

	public String getQuestionNum() {
		return questionNum;
	}

	public void setQuestionNum(String questionNum) {
		this.questionNum = questionNum;
	}

	public String getQuestionSubject() {
		return questionSubject;
	}

	public void setQuestionSubject(String questionSubject) {
		this.questionSubject = questionSubject;
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

	public String getPoints() {
		return Integer.toString(points);
	}

	public void setPoints(String points) {
		this.points = Integer.parseInt(points);
	}

	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}

	// public CheckBox getSelect() {
	// return select;
	// }
	//
	// public void setSelect(CheckBox select) {
	// this.select = select;
	// }

	public void setSubjectNumber(String subject) {
		switch (subject) {
		case "Software":
			this.questionSubject = "01";
			break;
		case "Math":
			this.questionSubject = "02";
			break;
		case "Physics":
			this.questionSubject = "03";
			break;
		}
	}

	// end region -> Getters & Setters

	/**
	 * this string concatenates the question counter to the questioID
	 */
	public void concatenateQuestionCount(int count) {
		if (count < 10)
			questionNum = "00";
		else if (count < 100)
			questionNum = "0";
		questionNum += count;
	}

}// end of class Question