package resources;

import java.io.Serializable;

/**
 * This class holds the information about the student, about the exam and
 * general information about the submission.
 * 
 * @author Group 7
 *
 */
public class StudentInActiveExam implements Serializable {

	/******************** Attributes ********************/

	private static final long serialVersionUID = 1L;

	private String date;

	private String startedTime;

	private Student student;

	private ActiveExam activeExam;

	private boolean sumbited;

	/******************** Constructors ********************/

	/**
	 * 
	 * @param student
	 *            Student
	 * @param activeExam
	 *            Active Exam
	 */
	public StudentInActiveExam(Student student, ActiveExam activeExam) {
		this.date = Utilities_Client.setDate();
		this.startedTime = Utilities_Client.getTime();
		this.student = student;
		this.activeExam = activeExam;
	}

	/******************** Getters and Setters ********************/

	/**
	 * 
	 * @param activeExam
	 *            Active Exam
	 */
	public StudentInActiveExam(ActiveExam activeExam) {
		this.activeExam = activeExam;
	}

	/**
	 * 
	 * @return The date of the exam session
	 */
	public String getDate() {
		return date;
	}

	/**
	 * 
	 * @param date
	 *            The date of the exam session
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * 
	 * @return The time of the exam session
	 */
	public String getStartedTime() {
		return startedTime;
	}

	/**
	 * 
	 * @param startedTime
	 *            The time of the exam session
	 */
	public void setStartedTime(String startedTime) {
		this.startedTime = startedTime;
	}

	/**
	 * 
	 * @return The student
	 */
	public Student getStudent() {
		return student;
	}

	/**
	 * 
	 * @param student
	 *            The student
	 */
	public void setStudent(Student student) {
		this.student = student;
	}

	/**
	 * 
	 * @return The active exam
	 */
	public ActiveExam getActiveExam() {
		return activeExam;
	}

	/**
	 * 
	 * @param activeExam
	 *            The active exam
	 */
	public void setActiveExam(ActiveExam activeExam) {
		this.activeExam = activeExam;
	}

	/**
	 * 
	 * @return True if the student submitted the exam by himself\herself
	 */
	public boolean isSumbited() {
		return sumbited;
	}

	/**
	 * 
	 * @param sumbited
	 *            True if the student submitted the exam by himself\herself
	 */
	public void setSumbited(boolean sumbited) {
		this.sumbited = sumbited;
	}

} // end of class