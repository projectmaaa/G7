package resources;

import java.io.Serializable;

public class CheckedExam implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private SubmittedExam submittedExam;
	
	private int grade;
	
	private boolean approved;
	
	private String comments;
	
	private String commentsOfChangeGrade;
	
	

	public CheckedExam(SubmittedExam submittedExam, int grade, boolean approved, String comments,
			String commentsOfChangeGrade) {
		super();
		this.submittedExam = submittedExam;
		this.grade = grade;
		this.approved = approved;
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

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
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

}