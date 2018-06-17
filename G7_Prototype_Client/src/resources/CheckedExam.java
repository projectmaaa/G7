package resources;

import java.io.Serializable;

public class CheckedExam implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private SubmittedExam submittedExam;
	
	private int grade;
	
	private String comments;
	
	private String commentsOfChangeGrade;
	
	private String idApprover;

	public CheckedExam(SubmittedExam submittedExam, int grade, String comments,
			String commentsOfChangeGrade) {
		this.submittedExam = submittedExam;
		this.grade = grade;
		this.comments = comments;
		this.commentsOfChangeGrade = commentsOfChangeGrade;
	}

	public CheckedExam(SubmittedExam submittedExam, int grade) {
		this.submittedExam = submittedExam;
		this.grade = grade;
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


	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
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
