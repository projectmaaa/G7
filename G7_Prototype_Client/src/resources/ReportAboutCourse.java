package resources;

import java.io.Serializable;

public class ReportAboutCourse extends Report implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String courseName;

	private Course course;

	public ReportAboutCourse(double average, Course course) {
		setAverage(average);
		this.course=course;;
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
	
	
}
