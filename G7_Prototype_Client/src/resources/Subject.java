package resources;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * This class contains the subject details & the courses that belongs to this
 * subject.
 * 
 * @author Group 7
 *
 */
public class Subject implements Serializable {

	/******************** Attributes ********************/

	private static final long serialVersionUID = 1L;

	private String subjectID;

	private String subjectName;

	private ArrayList<Course> courses;

	/******************** Constructors ********************/

	/**
	 * 
	 * @param subjectID
	 * @param subjectName
	 */
	public Subject(String subjectID, String subjectName) {
		this.subjectID = subjectID;
		this.subjectName = subjectName;
		this.courses = new ArrayList<Course>();
	}

	/******************** Getters & Setters ********************/

	/**
	 * 
	 * @return The number of the subject
	 */
	public String getSubjectID() {
		return subjectID;
	}

	/**
	 * 
	 * @param subjectID
	 */
	public void setSubjectID(String subjectID) {
		this.subjectID = subjectID;
	}

	/**
	 * 
	 * @return The name of the subject
	 */
	public String getSubjectName() {
		return subjectName;
	}

	/**
	 * 
	 * @param subjectName
	 */
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	/**
	 * 
	 * @return The courses that belongs to this subject
	 */
	public ArrayList<Course> getCourses() {
		return courses;
	}

	/******************** Methods ********************/

	/**
	 * Adds new course to this subject
	 * 
	 * @param course
	 */
	public void addCourseToSubject(Course course) {
		courses.add(course);
	}

} // end of class