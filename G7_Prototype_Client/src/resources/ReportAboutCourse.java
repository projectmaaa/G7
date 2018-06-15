package resources;

import java.io.Serializable;

public class ReportAboutCourse extends Report implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String courseName;

	public ReportAboutCourse(int average, int median, String course) {
		super(average,median);
		this.courseName=course;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	
	
}
