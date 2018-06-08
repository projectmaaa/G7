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

	public SubmittedExam(ArrayList<StudentAnswerInQuestion> answers, int timeToSolve,
			StudentInActiveExam studentInActiveExam) {
		this.answers = new ArrayList<StudentAnswerInQuestion>();
		this.timeToSolve = timeToSolve;
		this.studentInActiveExam = studentInActiveExam;
	}

	public ArrayList<StudentAnswerInQuestion> getAnswers() {
		return answers;
	}

	public void setAnswer(StudentAnswerInQuestion answer) {
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


}
