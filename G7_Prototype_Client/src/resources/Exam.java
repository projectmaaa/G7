package resources;

import java.io.Serializable;
import java.util.ArrayList;

import javafx.collections.ObservableList;

public class Exam implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String subjectID;

	private String courseID;

	private String examNum;

	private String teacherName;

	private int examDuration;

	private String freeTextForExaminees;

	private String freeTextForTeacherOnly;

	private ArrayList<QuestionInExam> questions;

	public Exam(String subjectID, String courseID, int examDuration, String teacherName) {
		this.subjectID = subjectID;
		this.courseID = courseID;
		this.examDuration = examDuration;
		this.teacherName = teacherName;
		questions = new ArrayList<>();
	}
	
	public Exam(String subjectID, String courseID, String examNum) {
		this.subjectID = subjectID;
		this.courseID = courseID;
		this.examNum=examNum;
		questions = new ArrayList<>();
	}

	public Exam(String subjectID, String courseID, String examNum, String teacherName, int examDuration,
			String freeTextForExaminees, String freeTextForTeacherOnly) {
		this.subjectID = subjectID;
		this.courseID = courseID;
		this.examNum = examNum;
		this.teacherName = teacherName;
		this.examDuration = examDuration;
		this.freeTextForExaminees = freeTextForExaminees;
		this.freeTextForTeacherOnly = freeTextForTeacherOnly;
		questions = new ArrayList<>();
	}

	public Exam(String subjectID, String courseID, String examNum, String teacherName, int examDuration,
			String freeTextForExaminees, String freeTextForTeacherOnly, ArrayList<QuestionInExam> questions) {
		this.subjectID = subjectID;
		this.courseID = courseID;
		this.examNum = examNum;
		this.teacherName = teacherName;
		this.examDuration = examDuration;
		this.freeTextForExaminees = freeTextForExaminees;
		this.freeTextForTeacherOnly = freeTextForTeacherOnly;
		this.questions = questions;
	}

	// setters, getters

	public String getSubjectID() {
		return subjectID;
	}

	public void setSubjectID(String subjectID) {
		this.subjectID = subjectID;
	}

	public String getCourseID() {
		return courseID;
	}

	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}

	public ArrayList<QuestionInExam> getQuestions() {
		return questions;
	}

	public void setQuestions(ObservableList<QuestionInExam> questions) {
		this.questions.addAll(questions);
	}

	public int getExamDuration() {
		return examDuration;
	}

	public void setExamDuration(int examDuration) {
		this.examDuration = examDuration;
	}

	public String getFreeTextForExaminees() {
		return freeTextForExaminees;
	}

	public void setFreeTextForExaminees(String freeTextForExaminees) {
		this.freeTextForExaminees = freeTextForExaminees;
	}

	public String getFreeTextForTeacherOnly() {
		return freeTextForTeacherOnly;
	}

	public void setFreeTextForTeacherOnly(String freeTextForTeacherOnly) {
		this.freeTextForTeacherOnly = freeTextForTeacherOnly;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public String getExamNum() {
		return examNum;
	}

	public void setExamNum(String examNum) {
		this.examNum = examNum;
	}
	
	public String getDurationInString() {
		//Integer dur = examDuration;
		return String.valueOf(examDuration);
	}

	// specific methods

	public void addQuestionToExam(QuestionInExam question) {
		questions.add(question);
	}

}
