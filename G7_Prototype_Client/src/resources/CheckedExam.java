package resources;

import java.io.Serializable;

public class CheckedExam implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private SubmittedExam submittedExam;

	private int grade;

	private String generalComments;

	private String commentsOfChangeGrade;

	private String idApprover;

	public CheckedExam(SubmittedExam submittedExam, int grade, String generalComments, String commentsOfChangeGrade) {
		this.submittedExam = submittedExam;
		this.grade = grade;
		this.generalComments = generalComments;
		this.commentsOfChangeGrade = commentsOfChangeGrade;
	}

	public CheckedExam(SubmittedExam submittedExam, int grade) {
		this.submittedExam = submittedExam;
		this.grade = grade;
	}

	public CheckedExam(SubmittedExam submittedExam, int grade, String generalComments) {
		this.submittedExam = submittedExam;
		this.grade = grade;
		this.generalComments = generalComments;
	}

	public SubmittedExam getSubmittedExam() {
		return submittedExam;
	}

	public void setSubmittedExam(SubmittedExam submittedExam) {
		this.submittedExam = submittedExam;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public String getExamNum() {
		return this.submittedExam.getStudentInActiveExam().getActiveExam().getExam().getExamNum();
	}

	public void setExamNum(String examNum) {
		this.submittedExam.getStudentInActiveExam().getActiveExam().getExam().setExamNum(examNum);
	}

	public void setExecutionCode(String executionCode) {
		this.submittedExam.getStudentInActiveExam().getActiveExam().setExecutionCode(executionCode);
	}

	public String getExecutionCode() {
		return this.submittedExam.getStudentInActiveExam().getActiveExam().getExecutionCode();
	}

	public String getGeneralComments() {
		return generalComments;
	}

	public void setGeneralComments(String generalComments) {
		this.generalComments = generalComments;
	}

	public String getCommentsOfChangeGrade() {
		return commentsOfChangeGrade;
	}

	public void setCommentsOfChangeGrade(String commentsOfChangeGrade) {
		this.commentsOfChangeGrade = commentsOfChangeGrade;
	}

	public String getIdApprover() {
		return idApprover;
	}

	public void setIdApprover(String idApprover) {
		this.idApprover = idApprover;
	}

}
