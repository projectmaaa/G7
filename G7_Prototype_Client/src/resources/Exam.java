package resources;

import java.io.Serializable;
import java.util.ArrayList;

import javafx.collections.ObservableList;

public class Exam implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String subjectID;

	private String courseID;

	private static int examCounter;

	private ArrayList<QuestionInExam> questions;

	private int examDuration;

	private String freeTextForExaminees;

	private String freeTextForTeacherOnly;

	private String teacherName;

	public Exam(String subjectID, String courseID, int examDuration, String teacherName) {

		this.subjectID = subjectID;
		this.courseID = courseID;
		this.examDuration = examDuration;
		this.teacherName = teacherName;
		questions = new ArrayList<>();
		examCounter++;
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

	public static int getExamCounter() {
		return examCounter;
	}

	public static void setExamCounter(int examCounter) {
		Exam.examCounter = examCounter;
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

	// specific methods

	public void addQuestionToExam(QuestionInExam question) {
		questions.add(question);
	}
}
