package resources;

import java.io.Serializable;

/**
 * The class that holds the information about specific course.
 * 
 * @author Group 7
 *
 */
public class Course implements Serializable {

	/******************** Attributes ********************/

	private static final long serialVersionUID = 1L;

	private String courseID;

	private String subjectID;

	private String courseName;

	/******************** Constructors ********************/

	/**
	 * 
	 * @param subjectID
	 * @param courseID
	 * @param courseName
	 */
	public Course(String subjectID, String courseID, String courseName) {
		this.courseID = courseID;
		this.subjectID = subjectID;
		this.courseName = courseName;
	}

	/******************** Getters & Setters ********************/

	/**
	 * 
	 * @return The course number
	 */
	public String getCourseID() {
		return courseID;
	}

	/**
	 * 
	 * @param courseID
	 */
	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}

	/**
	 * 
	 * @return The number of the subject that this course belongs to
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
	 * @return The name of the course
	 */
	public String getCourseName() {
		return courseName;
	}

	/**
	 * 
	 * @param courseName
	 */
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

} // end of class