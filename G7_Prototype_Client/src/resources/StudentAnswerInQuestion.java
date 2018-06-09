package resources;

import java.io.Serializable;

public class StudentAnswerInQuestion implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String subjectID;

	private String questionNum;

	private String questionOrderInExam;

	private String studentAnswer;

	private Student student;

	/**
	 * 
	 * @param subjectID
	 * @param questionNum
	 * @param questionNum
	 * @param studentAnswer
	 * @param student
	 */
	public StudentAnswerInQuestion(String subjectID, String questionNum, String questionOrderInExam,
			String studentAnswer, Student student) {
		this.subjectID = subjectID;
		this.questionNum = questionNum;
		this.questionOrderInExam = questionOrderInExam;
		this.studentAnswer = studentAnswer;
		this.student = student;
	}

	public String getSubjectID() {
		return subjectID;
	}

	public void setSubjectID(String subjectID) {
		this.subjectID = subjectID;
	}

	public String getQuestionNum() {
		return questionNum;
	}

	public void setQuestionNum(String questionNum) {
		this.questionNum = questionNum;
	}

	public String getStudentAnswer() {
		return studentAnswer;
	}

	public void setStudentAnswer(String studentAnswer) {
		this.studentAnswer = studentAnswer;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public String getQuestionOrderInExam() {
		return questionOrderInExam;
	}

	public void setQuestionOrderInExam(String questionOrderInExam) {
		this.questionOrderInExam = questionOrderInExam;
	}

}
