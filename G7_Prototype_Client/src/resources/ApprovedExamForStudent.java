package resources;

import java.io.Serializable;

public class ApprovedExamForStudent implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private CheckedExam checkedExam;

	private int finalGrade;

	public ApprovedExamForStudent(CheckedExam checkedExam, int finalGrade) {
		super();
		this.checkedExam = checkedExam;
		this.finalGrade = finalGrade;
	}

	public CheckedExam getCheckedExam() {
		return checkedExam;
	}

	public void setCheckedExam(CheckedExam checkedExam) {
		this.checkedExam = checkedExam;
	}

	public int getFinalGrade() {
		return finalGrade;
	}

	public void setFinalGrade(int finalGrade) {
		this.finalGrade = finalGrade;
	}

	public String getExamNum() {
		return getCheckedExam().getSubmittedExam().getStudentInActiveExam().getActiveExam().getExam().getExamNum();
	}

	public void setExamNum(String examNum) {
		getCheckedExam().getSubmittedExam().getStudentInActiveExam().getActiveExam().getExam().setExamNum(examNum);
	}

	public void setExecutionCode(String executionCode) {
		getCheckedExam().getSubmittedExam().getStudentInActiveExam().getActiveExam().setExecutionCode(executionCode);
	}

	public String getExecutionCode() {
		return getCheckedExam().getSubmittedExam().getStudentInActiveExam().getActiveExam().getExecutionCode();
	}

	public String getGeneralComments() {
		return getCheckedExam().getGeneralComments();
	}

	public void setGeneralComments(String generalComments) {
		getCheckedExam().setGeneralComments(generalComments);
	}

}
