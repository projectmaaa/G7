package resources;

import java.io.Serializable;
import java.util.ArrayList;

public class Subject implements Serializable {

	private String subjectID;
	
	private String subjectName;
	
	private ArrayList<Course> courses;

	public Subject(String subjectID, String subjectName) {
		this.subjectID = subjectID;
		this.subjectName = subjectName;
		this.courses = new ArrayList<Course>();
	}

	//setters, getters
	
	public String getSubjectID() {
		return subjectID;
	}

	public void setSubjectID(String subjectID) {
		this.subjectID = subjectID;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public ArrayList<Course> getCourses() {
		return courses;
	}

	//specific methods
	
	public void addCourseToSubject(Course course) {
		courses.add(course);
	}
	
}
