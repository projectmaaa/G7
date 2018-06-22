package resources;

import java.io.Serializable;

/**
 * This class holds the information like grade, comments & the submitted exam
 * before the teacher approve the student's exam.
 * 
 * @author Group 7
 *
 */
public class CheckedExam implements Serializable {

	/******************** Attributes ********************/

	private static final long serialVersionUID = 1L;

	private SubmittedExam submittedExam;

	private int grade;

	private String generalComments;

	private String commentsOfChangeGrade;

	private String idApprover;

	/******************** Constructors ********************/

	/**
	 * 
	 * @param submittedExam
	 * @param grade
	 * @param generalComments
	 * @param commentsOfChangeGrade
	 */
	public CheckedExam(SubmittedExam submittedExam, int grade, String generalComments, String commentsOfChangeGrade) {
		this.submittedExam = submittedExam;
		this.grade = grade;
		this.generalComments = generalComments;
		this.commentsOfChangeGrade = commentsOfChangeGrade;
	}

	/**
	 * 
	 * @param submittedExam
	 * @param grade
	 */
	public CheckedExam(SubmittedExam submittedExam, int grade) {
		this.submittedExam = submittedExam;
		this.grade = grade;
	}

	/**
	 * 
	 * @param submittedExam
	 */
	public CheckedExam(SubmittedExam submittedExam) {
		this.submittedExam = submittedExam;
	}

	/**
	 * 
	 * @param submittedExam
	 * @param grade
	 * @param generalComments
	 */
	public CheckedExam(SubmittedExam submittedExam, int grade, String generalComments) {
		this.submittedExam = submittedExam;
		this.grade = grade;
		this.generalComments = generalComments;
	}

	/**
	 * 
	 * @param submittedExam
	 * @param generalComments
	 */
	public CheckedExam(SubmittedExam submittedExam, String generalComments) {
		this.submittedExam = submittedExam;
		this.generalComments = generalComments;
	}

	/******************** Getters & Setters ********************/

	/**
	 * 
	 * @return Student's submitted exam
	 */
	public SubmittedExam getSubmittedExam() {
		return submittedExam;
	}

	/**
	 * 
	 * @param submittedExam
	 */
	public void setSubmittedExam(SubmittedExam submittedExam) {
		this.submittedExam = submittedExam;
	}

	/**
	 * 
	 * @return Student's grade which the system gave him\her
	 */
	public int getGrade() {
		return grade;
	}

	/**
	 * 
	 * @param grade
	 */
	public void setGrade(int grade) {
		this.grade = grade;
	}

	/**
	 * 
	 * @return The exam number
	 */
	public String getExamNum() {
		return this.submittedExam.getStudentInActiveExam().getActiveExam().getExam().getExamNum();
	}

	/**
	 * 
	 * @param examNum
	 */
	public void setExamNum(String examNum) {
		this.submittedExam.getStudentInActiveExam().getActiveExam().getExam().setExamNum(examNum);
	}

	/**
	 * 
	 * @param executionCode
	 */
	public void setExecutionCode(String executionCode) {
		this.submittedExam.getStudentInActiveExam().getActiveExam().setExecutionCode(executionCode);
	}

	/**
	 * 
	 * @return The execution code
	 */
	public String getExecutionCode() {
		return this.submittedExam.getStudentInActiveExam().getActiveExam().getExecutionCode();
	}

	/**
	 * 
	 * @return The general comments
	 */
	public String getGeneralComments() {
		return generalComments;
	}

	/**
	 * 
	 * @param generalComments
	 */
	public void setGeneralComments(String generalComments) {
		this.generalComments = generalComments;
	}

	/**
	 * 
	 * @return The comments that the teacher added when changed studet's grade
	 */
	public String getCommentsOfChangeGrade() {
		return commentsOfChangeGrade;
	}

	/**
	 * 
	 * @param commentsOfChangeGrade
	 */
	public void setCommentsOfChangeGrade(String commentsOfChangeGrade) {
		this.commentsOfChangeGrade = commentsOfChangeGrade;
	}

	/**
	 * 
	 * @return The ID of the teacher who need to approve this exam
	 */
	public String getIdApprover() {
		return idApprover;
	}

	/**
	 * 
	 * @param idApprover
	 */
	public void setIdApprover(String idApprover) {
		this.idApprover = idApprover;
	}

	/******************** Methods ********************/

	/**
	 * 
	 * @return The grade converted to string
	 */
	public String getGradeString() {
		Integer gr = this.grade;
		return gr.toString();
	}

	/**
	 * 
	 * @return The combination of the general comments & the comments of the changed
	 *         grade
	 */
	public String getAllComments() {
		return this.generalComments + this.commentsOfChangeGrade;
	}

} // end of class