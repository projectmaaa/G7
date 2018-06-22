package resources;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * This class contains details about the submitted exam.
 * 
 * @author Group 7
 *
 */
public class SubmittedExam implements Serializable {

	/******************** Attributes ********************/

	private static final long serialVersionUID = 1L;

	private ArrayList<StudentAnswerInQuestion> answers;

	private int timeToSolve;

	private StudentInActiveExam studentInActiveExam;

	private int submitted;

	/******************** Constructors ********************/

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

	/******************** Getters & Setters ********************/

	/**
	 * 
	 * @return The student's answers in this exam
	 */
	public ArrayList<StudentAnswerInQuestion> getAnswers() {
		return answers;
	}

	/**
	 * 
	 * @return The amount of minutes that took the student to solve the exam
	 */
	public int getTimeToSolve() {
		return timeToSolve;
	}

	/**
	 * 
	 * @param timeToSolve
	 */
	public void setTimeToSolve(int timeToSolve) {
		this.timeToSolve = timeToSolve;
	}

	/**
	 * 
	 * @return The student who did the exam
	 */
	public StudentInActiveExam getStudentInActiveExam() {
		return studentInActiveExam;
	}

	/**
	 * 
	 * @param studentInActiveExam
	 */
	public void setStudentInActiveExam(StudentInActiveExam studentInActiveExam) {
		this.studentInActiveExam = studentInActiveExam;
	}

	/**
	 * 
	 * @return If submitted by himself\herself or forced by the system
	 */
	public int getSubmitted() {
		return submitted;
	}

	/**
	 * 
	 * @param submitted
	 */
	public void setSubmitted(int submitted) {
		this.submitted = submitted;
	}

	/******************** Methods ********************/

	/**
	 * 
	 * @param answer
	 */
	public void addAnswer(StudentAnswerInQuestion answer) {
		answers.add(answer);
	}

	/**
	 * 
	 */
	@Override
	public String toString() {
		return "SubmittedExam \n" + answerString();
	}

	/**
	 * 
	 * @return The answers the student chose
	 */
	private String answerString() {
		String string = "";
		for (StudentAnswerInQuestion studentAnswerInQuestion : answers) {
			string += "QuestionNum : " + studentAnswerInQuestion.getQuestionNum() + ", QuestionOder : "
					+ studentAnswerInQuestion.getQuestionOrderInExam() + ", Answer : "
					+ studentAnswerInQuestion.getStudentAnswer() + "\n";
		}
		return string;
	}

} // end of class