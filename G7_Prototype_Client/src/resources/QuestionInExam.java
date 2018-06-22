package resources;

import java.io.Serializable;

/**
 * This class contains the question & the exam it belongs to.
 * 
 * @author Group 7
 *
 */
public class QuestionInExam implements Serializable {

	/******************** Attributes ********************/

	private static final long serialVersionUID = 1L;

	private Exam exam;

	private Question question;

	private int points;

	/******************** Constructors ********************/

	/**
	 * 
	 * @param exam
	 * @param question
	 */
	public QuestionInExam(Exam exam, Question question) {
		this.exam = exam;
		this.question = question;
	}

	/**
	 * 
	 * @param exam
	 * @param question
	 * @param points
	 */
	public QuestionInExam(Exam exam, Question question, int points) {
		this.exam = exam;
		this.question = question;
		this.points = points;
	}

	/******************** Getters & Setters ********************/

	/**
	 * 
	 * @return The exam that contains this question
	 */
	public Exam getExam() {
		return exam;
	}

	/**
	 * 
	 * @param exam
	 */
	public void setExam(Exam exam) {
		this.exam = exam;
	}

	/**
	 * 
	 * @param subjectID
	 */
	public void setSubjectID(String subjectID) {
		this.question.setSubjectID(subjectID);
	}

	/**
	 * 
	 * @return The number of the subject that this question belongs to
	 */
	public String getSubjectID() {
		return question.getSubjectID();
	}

	/**
	 * 
	 * @param questionNum
	 */
	public void setQuestionNum(String questionNum) {
		this.question.setQuestionNum(questionNum);
	}

	/**
	 * 
	 * @return The question number
	 */
	public String getQuestionNum() {
		return this.question.getQuestionNum();
	}

	/**
	 * 
	 * @param questionText
	 */
	public void setQuestionText(String questionText) {
		this.question.setQuestionText(questionText);
	}

	/**
	 * 
	 * @return The question text
	 */
	public String getQuestionText() {
		return this.question.getQuestionText();
	}

	/**
	 * 
	 * @return The question that contains all the general information
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

	/**
	 * 
	 * @return The points for this question
	 */
	public int getPoints() {
		return points;
	}

	/**
	 * 
	 * @param points
	 */
	public void setPoints(int points) {
		this.points = points;
	}

} // end of class