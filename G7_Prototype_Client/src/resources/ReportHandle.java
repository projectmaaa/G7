package resources;

import java.io.Serializable;

public class ReportHandle implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String command;
	
	private Student student;
	
	private String courseID;
	
	private Teacher teacher;

	public ReportHandle(String command, Student student) {
		this.command = command;
		this.student = student;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public String getCourseID() {
		return courseID;
	}

	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	
}
