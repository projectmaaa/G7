package resources;

import java.io.Serializable;
import java.util.ArrayList;
import javafx.collections.ObservableList;

/**
 * The class that holds the information about specific exam.
 * 
 * @author Group 7
 *
 */
public class Exam implements Serializable {

	/******************** Attributes ********************/

	private static final long serialVersionUID = 1L;

	private String subjectID;

	private String courseID;

	private String examNum;

	private String teacherName;

	private int examDuration;

	private String freeTextForExaminees;

	private String freeTextForTeacherOnly;

	private ArrayList<QuestionInExam> questions;

	/******************** Constructors ********************/

	/**
	 * 
	 * @param subjectID
	 *            Subject ID
	 * @param courseID
	 *            Course ID
	 * @param examDuration
	 *            Duration
	 * @param teacherName
	 *            Teacher's name
	 */
	public Exam(String subjectID, String courseID, int examDuration, String teacherName) {
		this.subjectID = subjectID;
		this.courseID = courseID;
		this.examDuration = examDuration;
		this.teacherName = teacherName;
		questions = new ArrayList<>();
	}

	/**
	 * 
	 * @param subjectID
	 *            Subject ID
	 * @param courseID
	 *            Course ID
	 * @param examNum
	 *            Exam Number
	 */
	public Exam(String subjectID, String courseID, String examNum) {
		this.subjectID = subjectID;
		this.courseID = courseID;
		this.examNum = examNum;
		questions = new ArrayList<>();
	}

	/**
	 * 
	 * @param subjectID
	 *            Subject ID
	 * @param courseID
	 *            Course ID
	 * @param examNum
	 *            Exam Number
	 * @param teacherName
	 *            Teacher's name
	 * @param examDuration
	 *            Duration
	 * @param freeTextForExaminees
	 *            Text For Examines
	 * @param freeTextForTeacherOnly
	 *            Text For Teacher Only
	 */
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

	/**
	 * 
	 * @param subjectID
	 *            Subject ID
	 * @param courseID
	 *            Course ID
	 * @param examNum
	 *            Exam Number
	 * @param teacherName
	 *            Teacher's name
	 * @param examDuration
	 *            Duration
	 * @param freeTextForExaminees
	 *            Text For Examines
	 * @param freeTextForTeacherOnly
	 *            Text For Teacher Only
	 * @param questions
	 *            Questions
	 */
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

	/**
	 * 
	 * @param examNum
	 *            Exam Number
	 */
	public Exam(String examNum) {
		this.examNum = examNum;
	}

	/******************** Getters and Setters ********************/

	/**
	 * 
	 * @return The number of the subject that this exam belongs to
	 */
	public String getSubjectID() {
		return subjectID;
	}

	/**
	 * 
	 * @param subjectID
	 *            The number of the subject that this exam belongs to
	 */
	public void setSubjectID(String subjectID) {
		this.subjectID = subjectID;
	}

	/**
	 * 
	 * @return The number of the course that this exam belongs to
	 */
	public String getCourseID() {
		return courseID;
	}

	/**
	 * 
	 * @param courseID
	 *            The number of the course that this exam belongs to
	 */
	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}

	/**
	 * 
	 * @return The questions in this exam
	 */
	public ArrayList<QuestionInExam> getQuestions() {
		return questions;
	}

	/**
	 * 
	 * @param questions
	 *            The questions in this exam
	 */
	public void setQuestions(ObservableList<QuestionInExam> questions) {
		this.questions.addAll(questions);
	}

	/**
	 * 
	 * @return The exam duration in minutes
	 */
	public int getExamDuration() {
		return examDuration;
	}

	/**
	 * 
	 * @param examDuration
	 *            The exam duration in minutes
	 */
	public void setExamDuration(int examDuration) {
		this.examDuration = examDuration;
	}

	/**
	 * 
	 * @return The text for examines that the teacher entered when created the exam
	 */
	public String getFreeTextForExaminees() {
		return freeTextForExaminees;
	}

	/**
	 * 
	 * @param freeTextForExaminees
	 *            The text for examines that the teacher entered when created the
	 *            exam
	 */
	public void setFreeTextForExaminees(String freeTextForExaminees) {
		this.freeTextForExaminees = freeTextForExaminees;
	}

	/**
	 * 
	 * @return The text for the activating teacher that the creating teacher entered
	 *         when created the exam
	 */
	public String getFreeTextForTeacherOnly() {
		return freeTextForTeacherOnly;
	}

	/**
	 * 
	 * @param freeTextForTeacherOnly
	 *            The text for the activating teacher that the creating teacher
	 *            entered when created the exam
	 */
	public void setFreeTextForTeacherOnly(String freeTextForTeacherOnly) {
		this.freeTextForTeacherOnly = freeTextForTeacherOnly;
	}

	/**
	 * 
	 * @return The creator's name
	 */
	public String getTeacherName() {
		return teacherName;
	}

	/**
	 * 
	 * @param teacherName
	 *            The creator's name
	 */
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	/**
	 * 
	 * @return The exam number
	 */
	public String getExamNum() {
		return examNum;
	}

	/**
	 * 
	 * @param examNum
	 *            The exam number
	 */
	public void setExamNum(String examNum) {
		this.examNum = examNum;
	}

	/******************** Methods ********************/

	/**
	 * 
	 * @return The duration as string
	 */
	public String getDurationInString() {
		return String.valueOf(examDuration);
	}

	/**
	 * Adds a question to this exam
	 * 
	 * @param question
	 *            Question
	 */
	public void addQuestionToExam(QuestionInExam question) {
		questions.add(question);
	}

} // end of class