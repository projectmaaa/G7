package resources;

import java.io.Serializable;

/**
 * This class contains the question and the exam it belongs to.
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
	 *            Exam
	 * @param question
	 *            Question
	 */
	public QuestionInExam(Exam exam, Question question) {
		this.exam = exam;
		this.question = question;
	}

	/**
	 * 
	 * @param exam
	 *            Exam
	 * @param question
	 *            Question
	 * @param points
	 *            Points
	 */
	public QuestionInExam(Exam exam, Question question, int points) {
		this.exam = exam;
		this.question = question;
		this.points = points;
	}

	/******************** Getters and Setters ********************/

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
	 *            The exam that contains this question
	 */
	public void setExam(Exam exam) {
		this.exam = exam;
	}

	/**
	 * 
	 * @param subjectID
	 *            The number of the subject that this question belongs to
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
	 *            The question number
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
	 *            The question text
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
	 *            The question that contains all the general information
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
	 *            The points for this question
	 */
	public void setPoints(int points) {
		this.points = points;
	}

} // end of class