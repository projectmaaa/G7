package resources;

import java.io.Serializable;
import java.util.ArrayList;

public class SubmittedExam implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ArrayList<StudentAnswerInQuestion> answers;

	private int timeToSolve;

	private StudentInActiveExam studentInActiveExam;

	private int submitted;

	/**
	 * 
	 * @param answers
	 * @param timeToSolve
	 * @param studentInActiveExam
	 */
	public SubmittedExam(int timeToSolve, StudentInActiveExam studentInActiveExam) {
		this.answers = new ArrayList<StudentAnswerInQuestion>();
		this.timeToSolve = timeToSolve;
		this.studentInActiveExam = studentInActiveExam;
	}

	/**
	 * 
	 * @param studentInActiveExam
	 */
	public SubmittedExam(StudentInActiveExam studentInActiveExam) {
		this.answers = new ArrayList<StudentAnswerInQuestion>();
		this.studentInActiveExam = studentInActiveExam;
	}

	public ArrayList<StudentAnswerInQuestion> getAnswers() {
		return answers;
	}

	public void addAnswer(StudentAnswerInQuestion answer) {
		answers.add(answer);
	}

	public int getTimeToSolve() {
		return timeToSolve;
	}

	public void setTimeToSolve(int timeToSolve) {
		this.timeToSolve = timeToSolve;
	}

	public StudentInActiveExam getStudentInActiveExam() {
		return studentInActiveExam;
	}

	public void setStudentInActiveExam(StudentInActiveExam studentInActiveExam) {
		this.studentInActiveExam = studentInActiveExam;
	}

	public int getSubmitted() {
		return submitted;
	}

	public void setSubmitted(int submitted) {
		this.submitted = submitted;
	}

	@Override
	public String toString() {
		return "SubmittedExam \n" + answerString();
	}

	private String answerString() {
		String string = "";
		for (StudentAnswerInQuestion studentAnswerInQuestion : answers) {
			string += "QuestionNum : " + studentAnswerInQuestion.getQuestionNum() + ", QuestionOder : "
					+ studentAnswerInQuestion.getQuestionOrderInExam() + ", Answer : "
					+ studentAnswerInQuestion.getStudentAnswer() + "\n";
		}
		return string;
	}

}
