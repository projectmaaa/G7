package resources;

import java.io.Serializable;

/**
 * General handler for reports.
 * 
 * @author Group 7
 *
 */
public class ReportHandle implements Serializable {

	/******************** Attributes ********************/

	private static final long serialVersionUID = 1L;

	private String command;

	private Student student;

	private Course course;

	private Teacher teacher;

	/******************** Constructors ********************/

	/**
	 * 
	 * @param command
	 *            What To Do
	 * @param student
	 *            Student
	 */
	public ReportHandle(String command, Student student) {
		this.command = command;
		this.student = student;
	}

	/**
	 * 
	 * @param command
	 *            What To Do
	 * @param course
	 *            Course
	 */
	public ReportHandle(String command, Course course) {
		this.command = command;
		this.course = course;
	}

	/**
	 * 
	 * @param command
	 *            What To Do
	 * @param teacher
	 *            Teacher
	 */
	public ReportHandle(String command, Teacher teacher) {
		this.command = command;
		this.teacher = teacher;
	}

	/******************** Getters and Setters ********************/

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
	 *            The message what to do
	 */
	public void setCommand(String command) {
		this.command = command;
	}

	/**
	 * 
	 * @return The student
	 */
	public Student getStudent() {
		return student;
	}

	/**
	 * 
	 * @param student
	 *            The student
	 */
	public void setStudent(Student student) {
		this.student = student;
	}

	/**
	 * 
	 * @return The course
	 */
	public Course getCourse() {
		return course;
	}

	/**
	 * 
	 * @param course
	 *            The course
	 */
	public void setCourse(Course course) {
		this.course = course;
	}

	/**
	 * 
	 * @return The teacher
	 */
	public Teacher getTeacher() {
		return teacher;
	}

	/**
	 * 
	 * @param teacher
	 *            The teacher
	 */
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

} // end of class