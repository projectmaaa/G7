package resources;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * This class contains all the question details.
 * 
 * @author group 7
 *
 */
public class Question implements Serializable {

	/******************** Attributes ********************/

	private static final long serialVersionUID = 1L;

	private String subjectID;

	private String questionNum;

	private String author;

	private String questionText;

	private String firstPossibleAnswer;

	private String secondPossibleAnswer;

	private String thirdPossibleAnswer;

	private String fourthPossibleAnswer;

	private String correctAnswer;

	/******************** Constructors ********************/

	/**
	 * 
	 * @param subjectID
	 * @param author
	 * @param questionText
	 * @param firstPossibleAnswer
	 * @param secondPossibleAnswer
	 * @param thirdPossibleAnswer
	 * @param fourthPossibleAnswer
	 * @param correctAnswer
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
	 * 
	 * @param subjectID
	 * @param questionNum
	 * @param author
	 * @param questionText
	 * @param possibleAnswers
	 * @param correctAnswer
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

	/**
	 * 
	 * @param questionText
	 * @param firstPossibleAnswer
	 * @param secondPossibleAnswer
	 * @param thirdPossibleAnswer
	 * @param fourthPossibleAnswer
	 * @param correctAnswer
	 */
	public Question(String questionText, String firstPossibleAnswer, String secondPossibleAnswer,
			String thirdPossibleAnswer, String fourthPossibleAnswer, String correctAnswer, String questionNum) {
		super();
		this.questionText = questionText;
		this.firstPossibleAnswer = firstPossibleAnswer;
		this.secondPossibleAnswer = secondPossibleAnswer;
		this.thirdPossibleAnswer = thirdPossibleAnswer;
		this.fourthPossibleAnswer = fourthPossibleAnswer;
		this.correctAnswer = correctAnswer;
		this.questionNum = questionNum;
	}

	/******************** Getters & Setters ********************/

	/**
	 * 
	 * @return The question number
	 */
	public String getQuestionNum() {
		return questionNum;
	}

	/**
	 * 
	 * @param questionNum
	 */
	public void setQuestionNum(String questionNum) {
		this.questionNum = questionNum;
	}

	/**
	 * 
	 * @return The number of the subject that this question belongs to
	 */
	public String getSubjectID() {
		return subjectID;
	}

	/**
	 * 
	 * @param subjectID
	 */
	public void setSubjectID(String subjectID) {
		this.subjectID = subjectID;
	}

	/**
	 * 
	 * @return The teacher's name who wrote this question
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * 
	 * @param author
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * 
	 * @return The question itself
	 */
	public String getQuestionText() {
		return questionText;
	}

	/**
	 * 
	 * @param questionText
	 */
	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}

	/**
	 * 
	 * @return The first option to answer
	 */
	public String getFirstPossibleAnswer() {
		return firstPossibleAnswer;
	}

	/**
	 * 
	 * @param firstPossibleAnswer
	 */
	public void setFirstPossibleAnswer(String firstPossibleAnswer) {
		this.firstPossibleAnswer = firstPossibleAnswer;
	}

	/**
	 * 
	 * @return The second option to answer
	 */
	public String getSecondPossibleAnswer() {
		return secondPossibleAnswer;
	}

	/**
	 * 
	 * @param secondPossibleAnswer
	 */
	public void setSecondPossibleAnswer(String secondPossibleAnswer) {
		this.secondPossibleAnswer = secondPossibleAnswer;
	}

	/**
	 * 
	 * @return The third option to answer
	 */
	public String getThirdPossibleAnswer() {
		return thirdPossibleAnswer;
	}

	/**
	 * 
	 * @param thirdPossibleAnswer
	 */
	public void setThirdPossibleAnswer(String thirdPossibleAnswer) {
		this.thirdPossibleAnswer = thirdPossibleAnswer;
	}

	/**
	 * 
	 * @return The fourth option to answer
	 */
	public String getFourthPossibleAnswer() {
		return fourthPossibleAnswer;
	}

	/**
	 * 
	 * @param fourthPossibleAnswer
	 */
	public void setFourthPossibleAnswer(String fourthPossibleAnswer) {
		this.fourthPossibleAnswer = fourthPossibleAnswer;
	}

	/**
	 * 
	 * @return The correct answer
	 */
	public String getCorrectAnswer() {
		return correctAnswer;
	}

	/**
	 * 
	 * @param correctAnswer
	 */
	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}

} // end of class