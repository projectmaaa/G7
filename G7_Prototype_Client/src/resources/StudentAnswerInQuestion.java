package resources;

import java.io.Serializable;

/**
 * This class contains the selected answer by student in specific question &
 * details about the student & the question.
 * 
 * @author Group 7
 *
 */
public class StudentAnswerInQuestion implements Serializable {

	/******************** Attributes ********************/

	private static final long serialVersionUID = 1L;

	private String subjectID;

	private String questionNum;

	private String questionOrderInExam;

	private String studentAnswer;

	private Student student;

	/******************** Constructors ********************/

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

	/**
	 * 
	 * @param subjectID
	 * @param questionNum
	 * @param questionOrderInExam
	 * @param studentAnswer
	 */
	public StudentAnswerInQuestion(String subjectID, String questionNum, String questionOrderInExam,
			String studentAnswer) {
		this.subjectID = subjectID;
		this.questionNum = questionNum;
		this.questionOrderInExam = questionOrderInExam;
		this.studentAnswer = studentAnswer;
	}

	/******************** Getters & Setters ********************/

	/**
	 * 
	 * @return The number of the subject that this question belongs to
	 */
	public String getSubjectID() {
		return subjectID;
	}

	/**
	 * 
	 * @param subjectID
	 */
	public void setSubjectID(String subjectID) {
		this.subjectID = subjectID;
	}

	/**
	 * 
	 * @return The question number
	 */
	public String getQuestionNum() {
		return questionNum;
	}

	/**
	 * 
	 * @param questionNum
	 */
	public void setQuestionNum(String questionNum) {
		this.questionNum = questionNum;
	}

	/**
	 * 
	 * @return The answer that the student selected
	 */
	public String getStudentAnswer() {
		return studentAnswer;
	}

	/**
	 * 
	 * @param studentAnswer
	 */
	public void setStudentAnswer(String studentAnswer) {
		this.studentAnswer = studentAnswer;
	}

	/**
	 * 
	 * @return The student who took the exam
	 */
	public Student getStudent() {
		return student;
	}

	/**
	 * 
	 * @param student
	 */
	public void setStudent(Student student) {
		this.student = student;
	}

	/**
	 * 
	 * @return The question order in the specific exam
	 */
	public String getQuestionOrderInExam() {
		return questionOrderInExam;
	}

	/**
	 * 
	 * @param questionOrderInExam
	 */
	public void setQuestionOrderInExam(String questionOrderInExam) {
		this.questionOrderInExam = questionOrderInExam;
	}

} // end of class