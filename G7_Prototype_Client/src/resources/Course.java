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
	 *            Subject ID
	 * @param courseID
	 *            Course ID
	 * @param courseName
	 *            Course Name
	 */
	public Course(String subjectID, String courseID, String courseName) {
		this.courseID = courseID;
		this.subjectID = subjectID;
		this.courseName = courseName;
	}

	/******************** Getters and Setters ********************/

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
	 *            The course number
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
	 *            The number of the subject that this course belongs to
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
	 *            The name of the course
	 */
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

} // end of class