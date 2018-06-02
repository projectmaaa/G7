package resources;

import java.io.Serializable;

public class Course implements Serializable {
	
	private String courseID;
	
	private String subjectID;
	
	private String courseName;

	public Course(String courseID, String subjectID, String courseName) {
		this.courseID = courseID;
		this.subjectID = subjectID;
		this.courseName = courseName;
	}
	
	//setters, getters

	public String getCourseID() {
		return courseID;
	}

	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}

	public String getSubjectID() {
		return subjectID;
	}

	public void setSubjectID(String subjectID) {
		this.subjectID = subjectID;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
}
