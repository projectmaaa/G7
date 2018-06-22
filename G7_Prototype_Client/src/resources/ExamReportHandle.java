package resources;

import java.io.Serializable;

/**
 * The class that helps to build teacher's histogram.
 * 
 * @author Group 7
 *
 */
public class ExamReportHandle implements Serializable {

	/******************** Attributes ********************/

	private static final long serialVersionUID = 1L;

	private String subject;

	private String course;

	private String examNum;

	private String command;

	/******************** Constructors ********************/

	/**
	 * 
	 * @param subject
	 * @param course
	 * @param examNum
	 * @param command
	 */
	public ExamReportHandle(String subject, String course, String examNum, String command) {
		this.subject = subject;
		this.course = course;
		this.examNum = examNum;
		this.command = command;
	}

	/******************** Getters & Setters ********************/

	/**
	 * 
	 * @return The subject that the exam belongs to
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * 
	 * @param subject
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * 
	 * @return The course that the exam belongs to
	 */
	public String getCourse() {
		return course;
	}

	/**
	 * 
	 * @param course
	 */
	public void setCourse(String course) {
		this.course = course;
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
	 */
	public void setExamNum(String examNum) {
		this.examNum = examNum;
	}

	/**
	 * 
	 * @return The message what to do
	 */
	public String getCommand() {
		return command;
	}

	/**
	 * 
	 * @param command
	 */
	public void setCommand(String command) {
		this.command = command;
	}

} // end of class