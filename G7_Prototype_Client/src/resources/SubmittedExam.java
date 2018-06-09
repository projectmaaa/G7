package resources;

import java.io.Serializable;
import java.util.ArrayList;

public class SubmittedExam implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ArrayList<StudentAnswerInQuestion> answers;

	private int grade;

	private int timeToSolve;

	private StudentInActiveExam studentInActiveExam;

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

	public ArrayList<StudentAnswerInQuestion> getAnswers() {
		return answers;
	}

	public void addAnswer(StudentAnswerInQuestion answer) {
		answers.add(answer);
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
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

	@Override
	public String toString() {
		return "SubmittedExam \n" + answerString();
	}

	private String answerString() {
		String string = "";
		for (StudentAnswerInQuestion studentAnswerInQuestion : answers) {
			string += "Question : " + studentAnswerInQuestion.getQuestionNum() + ", Answer : "
					+ studentAnswerInQuestion.getStudentAnswer() + "\n";
		}
		return string;
	}

}
