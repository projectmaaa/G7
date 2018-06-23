package resources;

/**
 * This class connects between the teacher and his\hers subjects.
 * 
 * @author Group 7
 *
 */
public class TeacherSubjects {

	/******************** Attributes ********************/

	private Teacher teacher;

	private Subject subject;

	/******************** Constructors ********************/

	/**
	 * 
	 * @param teacher
	 *            Teacher
	 * @param subject
	 *            Subject
	 */
	public TeacherSubjects(Teacher teacher, Subject subject) {
		this.teacher = teacher;
		this.subject = subject;
	}

	/******************** Getters and Setters ********************/

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

	/**
	 * 
	 * @return One of the teacher's subjects
	 */
	public Subject getSubject() {
		return subject;
	}

	/**
	 * 
	 * @param subject
	 *            One of the teacher's subjects
	 */
	public void setSubject(Subject subject) {
		this.subject = subject;
	}

} // end of class