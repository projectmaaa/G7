package resources;

import java.io.Serializable;

/**
 * This class holds the information like grade, comments and the checked exam
 * after the teacher approve the student's exam.
 * 
 * @author Group 7
 *
 */
public class ApprovedExamForStudent implements Serializable {

	/******************** Attributes ********************/

	private static final long serialVersionUID = 1L;

	private CheckedExam checkedExam;

	private int finalGrade;

	private String comments;

	/******************** Constructors ********************/

	/**
	 * 
	 * @param checkedExam
	 *            Checked Exam
	 * @param finalGrade
	 *            Final Grade
	 */
	public ApprovedExamForStudent(CheckedExam checkedExam, int finalGrade) {
		this.checkedExam = checkedExam;
		this.finalGrade = finalGrade;
	}

	/**
	 * 
	 * @param checkedExam
	 *            Checked Exam
	 * @param finalGrade
	 *            Final Grade
	 * @param comments
	 *            Comments
	 */
	public ApprovedExamForStudent(CheckedExam checkedExam, int finalGrade, String comments) {
		this.checkedExam = checkedExam;
		this.finalGrade = finalGrade;
		this.comments = comments;
	}

	/******************** Getters and Setters ********************/

	/**
	 * 
	 * @return The checked exam
	 */
	public CheckedExam getCheckedExam() {
		return checkedExam;
	}

	/**
	 * 
	 * @param checkedExam
	 *            Checked Exam
	 */
	public void setCheckedExam(CheckedExam checkedExam) {
		this.checkedExam = checkedExam;
	}

	/**
	 * 
	 * @return The new grade after changing or the grade that the system gave to the
	 *         student
	 */
	public int getFinalGrade() {
		return finalGrade;
	}

	/**
	 * 
	 * @param finalGrade
	 *            Final Grade
	 */
	public void setFinalGrade(int finalGrade) {
		this.finalGrade = finalGrade;
	}

	/**
	 * 
	 * @return Teacher's comments
	 */
	public String getComments() {
		return comments;
	}

	/**
	 * 
	 * @param comments
	 *            Teacher's comments
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}

	/**
	 * 
	 * @return The exam number
	 */
	public String getExamNum() {
		return getCheckedExam().getSubmittedExam().getStudentInActiveExam().getActiveExam().getExam().getExamNum();
	}

	/**
	 * 
	 * @param examNum
	 *            The exam number
	 */
	public void setExamNum(String examNum) {
		getCheckedExam().getSubmittedExam().getStudentInActiveExam().getActiveExam().getExam().setExamNum(examNum);
	}

	/**
	 * 
	 * @param executionCode
	 *            The execution code
	 */
	public void setExecutionCode(String executionCode) {
		getCheckedExam().getSubmittedExam().getStudentInActiveExam().getActiveExam().setExecutionCode(executionCode);
	}

	/**
	 * 
	 * @return The execution code
	 */
	public String getExecutionCode() {
		return getCheckedExam().getSubmittedExam().getStudentInActiveExam().getActiveExam().getExecutionCode();
	}

	/**
	 * 
	 * @return Teacher's comments. The combination of changing grade comments and and
	 *         side comments that the teacher wants to add for the student.
	 */
	public String getGeneralComments() {
		return getCheckedExam().getGeneralComments();
	}

	/**
	 * 
	 * @param generalComments
	 *            Teacher's comments. The combination of changing grade comments and
	 *            and side comments that the teacher wants to add for the student.
	 */
	public void setGeneralComments(String generalComments) {
		getCheckedExam().setGeneralComments(generalComments);
	}

} // end of class