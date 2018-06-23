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
	 * @param timeToSolve
	 *            Time To Solve
	 * @param studentInActiveExam
	 *            Student In Active Exam
	 */
	public SubmittedExam(int timeToSolve, StudentInActiveExam studentInActiveExam) {
		this.answers = new ArrayList<StudentAnswerInQuestion>();
		this.timeToSolve = timeToSolve;
		this.studentInActiveExam = studentInActiveExam;
	}

	/**
	 * 
	 * @param studentInActiveExam
	 *            Student In Active Exam
	 */
	public SubmittedExam(StudentInActiveExam studentInActiveExam) {
		this.answers = new ArrayList<StudentAnswerInQuestion>();
		this.studentInActiveExam = studentInActiveExam;
	}

	/******************** Getters and Setters ********************/

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
	 *            Time To Solve in Minutes
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
	 *            Student In Active Exam
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
	 *            If submitted by himself\herself or forced by the system
	 */
	public void setSubmitted(int submitted) {
		this.submitted = submitted;
	}

	/******************** Methods ********************/

	/**
	 * 
	 * @param answer
	 *            Student's Answer In Question
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