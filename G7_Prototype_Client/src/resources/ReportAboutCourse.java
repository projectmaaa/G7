package resources;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * This class contains the details for the principal report about specific
 * course. This class is the message that sent between the client and the
 * server.
 * 
 * @author Group 7
 *
 */
public class ReportAboutCourse extends Report implements Serializable {

	/******************** Attributes ********************/

	private static final long serialVersionUID = 1L;

	private String courseName;

	private Course course;

	private String command;

	private ArrayList<Course> courses;

	private ArrayList<Integer> grades;

	/******************** Constructors ********************/

	/**
	 * 
	 * @param command
	 *            What To Do
	 * @param courses
	 *            Courses
	 */
	public ReportAboutCourse(String command, ArrayList<Course> courses) {
		this.command = command;
		this.courses = courses;
	}

	/**
	 * 
	 * @param command
	 *            What To Do
	 * @param average
	 *            Average
	 * @param med
	 *            Median
	 * @param course
	 *            Course
	 * @param grades
	 *            Grades
	 */
	public ReportAboutCourse(String command, double average, int med, Course course, ArrayList<Integer> grades) {
		this.command = command;
		setAverage(average);
		setMedian(med);
		this.course = course;
		this.grades = grades;
	}

	/******************** Getters and Setters ********************/

	/**
	 * 
	 * @return The course name that this report is about
	 */
	public String getCourseName() {
		return courseName;
	}

	/**
	 * 
	 * @param courseName
	 *            The course name that this report is about
	 */
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	/**
	 * 
	 * @return The course that this report is about
	 */
	public Course getCourse() {
		return course;
	}

	/**
	 * 
	 * @param course
	 *            The course that this report is about
	 */
	public void setCourse(Course course) {
		this.course = course;
	}

	/**
	 * 
	 * @return The courses that this report is about
	 */
	public ArrayList<Course> getCourses() {
		return courses;
	}

	/**
	 * 
	 * @param courses
	 *            The courses that this report is about
	 */
	public void setCourses(ArrayList<Course> courses) {
		this.courses = courses;
	}

	/**
	 * 
	 * @return The message what to do with the report
	 */
	public String getCommand() {
		return command;
	}

	/**
	 * 
	 * @param command
	 *            The message what to do with the report
	 */
	public void setCommand(String command) {
		this.command = command;
	}

	/**
	 * 
	 * @return The grades that is going to be shown to the principal
	 */
	public ArrayList<Integer> getGrades() {
		return grades;
	}

	/**
	 * 
	 * @param grades
	 *            The grades that is going to be shown to the principal
	 */
	public void setGrades(ArrayList<Integer> grades) {
		this.grades = grades;
	}

} // end of class