package resources;

/**
 * This class connects between the teacher & his\hers subjects.
 * 
 * @author Group 7
 *
 */
public class TeacherSubjects {

	/******************** Attributes ********************/

	private Teacher teacher;

	private Subject subject;

	/******************** Constructors ********************/

	public TeacherSubjects(Teacher teacher, Subject subject) {
		this.teacher = teacher;
		this.subject = subject;
	}

	/******************** Getters & Setters ********************/

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
	 */
	public void setSubject(Subject subject) {
		this.subject = subject;
	}

} // end of class