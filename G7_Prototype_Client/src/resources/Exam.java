package resources;

import java.util.ArrayList;

public class Exam {

	private String subjectID;
	
	private String courseID;
	
	private static int examCounter;
	
	private ArrayList<QuestionInExam> questions;
	
	private int examDuration;
	
	private String freeTextForExaminees;
	
	private String freeTextForTeacherOnly;
	
	private String teacherName;
	
	public Exam (String subjectID, String courseID, int examDuration, String freeTextForExaminees,
			String freeTextForTeacherOnly, String teacherName) {
		
		this.subjectID=subjectID;
		this.courseID=courseID;
		this.examDuration=examDuration;
		this.freeTextForExaminees=freeTextForExaminees;
		this.freeTextForTeacherOnly=freeTextForTeacherOnly;
		this.teacherName=teacherName;
		this.questions=new ArrayList<QuestionInExam>();
	}
	
	//setters, getters
	
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

	public void setQuestions(ArrayList<QuestionInExam> questions) {
		this.questions = questions;
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

	//specific methods
	
	public void addQuestionToExam(Question question, int points) {
		questions.add(new QuestionInExam(this,question,points));
	}
}
