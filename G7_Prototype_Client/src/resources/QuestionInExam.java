package resources;

import java.io.Serializable;

public class QuestionInExam implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Exam exam;

	private Question question;

	private int points;

	public QuestionInExam(Exam exam, Question question) {
		this.exam = exam;
		this.question = question;
	}

	public QuestionInExam(Exam exam, Question question, int points) {
		this.exam = exam;
		this.question = question;
		this.points = points;
	}

	// setters, getters

	public Exam getExam() {
		return exam;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}

	public void setSubjectID(String subjectID) {
		this.question.setSubjectID(subjectID);
	}

	public String getSubjectID() {
		return question.getSubjectID();
	}

	public void setQuestionNum(String questionNum) {
		this.question.setQuestionNum(questionNum);
	}

	public String getQuestionNum() {
		return this.question.getQuestionNum();
	}

	public void setQuestionText(String questionText) {
		this.question.setQuestionText(questionText);
	}

	public String getQuestionText() {
		return this.question.getQuestionText();
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	@Override
	public String toString() {
		return "QuestionInExam [exam=" + exam + ", question=" + question + ", points=" + points + "]";
	}

}
