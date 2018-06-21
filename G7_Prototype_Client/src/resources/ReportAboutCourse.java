package resources;

import java.io.Serializable;
import java.util.ArrayList;

public class ReportAboutCourse extends Report implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String courseName;

	private Course course;
	
	private String command;
	
	private ArrayList<Course> courses;
	
	private ArrayList<Integer> grades;

	public ReportAboutCourse(String command, ArrayList<Course> courses) {
		this.command=command;
		this.courses = courses;
	}

	public ReportAboutCourse(String command, double average, int med, Course course, ArrayList<Integer> grades) {
		this.command=command;
		setAverage(average);
		setMedian(med);
		this.course=course;
		this.grades=grades;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public ArrayList<Course> getCourses() {
		return courses;
	}

	public void setCourses(ArrayList<Course> courses) {
		this.courses = courses;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public ArrayList<Integer> getGrades() {
		return grades;
	}

	public void setGrades(ArrayList<Integer> grades) {
		this.grades = grades;
	}
	
}
