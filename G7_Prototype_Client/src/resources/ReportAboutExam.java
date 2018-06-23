package resources;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * This class contains the details for the principal report about specific exam.
 * This class is the message that sent between the client and the server.
 * 
 * @author Group 7
 *
 */
public class ReportAboutExam extends Report implements Serializable {

	/******************** Attributes ********************/

	private static final long serialVersionUID = 1L;

	private int studentStarted;

	private int studentFinished;

	private int studentForced;

	private ArrayList<Integer> grades;

	/******************** Constructors ********************/

	/**
	 * 
	 * @param avg
	 *            Average
	 * @param median
	 *            Median
	 * @param studentStarted
	 *            Amount of Students who Started
	 * @param studentFinished
	 *            Amount of Students who finished the exam by their own submission
	 * @param studentForced
	 *            finished the exam by the system automatic submission
	 * @param grades
	 *            Grades
	 */
	public ReportAboutExam(double avg, int median, int studentStarted, int studentFinished, int studentForced,
			ArrayList<Integer> grades) {
		setAverage(avg);
		setMedian(median);
		this.studentStarted = studentStarted;
		this.studentFinished = studentFinished;
		this.studentForced = studentForced;
		this.grades = grades;
	}

	/******************** Getters and Setters ********************/

	/**
	 * 
	 * @return The amount of students who started the exam
	 */
	public int getStudentStarted() {
		return studentStarted;
	}

	/**
	 * 
	 * @param studentStarted
	 *            The amount of students who started the exam
	 */
	public void setStudentStarted(int studentStarted) {
		this.studentStarted = studentStarted;
	}

	/**
	 * 
	 * @return The amount of students who finished the exam by their own submission
	 */
	public int getStudentFinished() {
		return studentFinished;
	}

	/**
	 * 
	 * @param studentFinished
	 *            The amount of students who finished the exam by their own
	 *            submission
	 */
	public void setStudentFinished(int studentFinished) {
		this.studentFinished = studentFinished;
	}

	/**
	 * 
	 * @return The amount of students who finished the exam by the system automatic
	 *         submission
	 */
	public int getStudentForced() {
		return studentForced;
	}

	/**
	 * 
	 * @param studentForced
	 *            The amount of students who finished the exam by the system
	 *            automatic submission
	 */
	public void setStudentForced(int studentForced) {
		this.studentForced = studentForced;
	}

	/**
	 * 
	 * @return The grades of the students who did this exam
	 */
	public ArrayList<Integer> getGrades() {
		return grades;
	}

	/**
	 * 
	 * @param grades
	 *            The grades of the students who did this exam
	 */
	public void setGrades(ArrayList<Integer> grades) {
		this.grades = grades;
	}

} // end of class