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
	 *            Subject ID
	 * @param author
	 *            Teacher's Name
	 * @param questionText
	 *            Question Text
	 * @param firstPossibleAnswer
	 *            First Possible Answer
	 * @param secondPossibleAnswer
	 *            Second Possible Answer
	 * @param thirdPossibleAnswer
	 *            Third Possible Answer
	 * @param fourthPossibleAnswer
	 *            Fourth Possible Answer
	 * @param correctAnswer
	 *            Correct Answer
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
	 *            Subject ID
	 * @param questionNum
	 *            Question Number
	 * @param author
	 *            Teacher's Name
	 * @param questionText
	 *            Question Text
	 * @param possibleAnswers
	 *            Possible Answers
	 * @param correctAnswer
	 *            Correct Answer
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
	 *            Question Text
	 * @param firstPossibleAnswer
	 *            First Possible Answer
	 * @param secondPossibleAnswer
	 *            Second Possible Answer
	 * @param thirdPossibleAnswer
	 *            Third Possible Answer
	 * @param fourthPossibleAnswer
	 *            Fourth Possible Answer
	 * @param correctAnswer
	 *            Correct Answer
	 * @param questionNum
	 *            Question Number
	 */
	public Question(String questionText, String firstPossibleAnswer, String secondPossibleAnswer,
			String thirdPossibleAnswer, String fourthPossibleAnswer, String correctAnswer, String questionNum) {
		this.questionText = questionText;
		this.firstPossibleAnswer = firstPossibleAnswer;
		this.secondPossibleAnswer = secondPossibleAnswer;
		this.thirdPossibleAnswer = thirdPossibleAnswer;
		this.fourthPossibleAnswer = fourthPossibleAnswer;
		this.correctAnswer = correctAnswer;
		this.questionNum = questionNum;
	}

	/******************** Getters and Setters ********************/

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
	 *            The question number
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
	 *            The number of the subject that this question belongs to
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
	 *            The teacher's name who wrote this question
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
	 *            The question itself
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
	 *            The first option to answer
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
	 *            The second option to answer
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
	 *            The third option to answer
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
	 *            The fourth option to answer
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
	 *            The correct answer
	 */
	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}

} // end of class