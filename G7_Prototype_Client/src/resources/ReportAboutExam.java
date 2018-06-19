package resources;

import java.io.Serializable;
import java.util.ArrayList;

public class ReportAboutExam extends Report implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int studentStarted;

	private int studentFinished;

	private int studentForced;

	private ArrayList<Integer> grades;

	public ReportAboutExam(double avg, int median, int studentStarted, int studentFinished, int studentForced,
			ArrayList<Integer> grades) {
		setAverage(avg);
		setMedian(median);
		this.studentStarted = studentStarted;
		this.studentFinished = studentFinished;
		this.studentForced = studentForced;
		this.grades = grades;
	}

	public int getStudentStarted() {
		return studentStarted;
	}

	public void setStudentStarted(int studentStarted) {
		this.studentStarted = studentStarted;
	}

	public int getStudentFinished() {
		return studentFinished;
	}

	public void setStudentFinished(int studentFinished) {
		this.studentFinished = studentFinished;
	}

	public int getStudentForced() {
		return studentForced;
	}

	public void setStudentForced(int studentForced) {
		this.studentForced = studentForced;
	}

	public ArrayList<Integer> getGrades() {
		return grades;
	}

	public void setGrades(ArrayList<Integer> grades) {
		this.grades = grades;
	}

}
