package resources;

import java.io.Serializable;


public class ReportHandle implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String command;
	
	private Student student;
	
	private Course course;
	
	private Teacher teacher;

	public ReportHandle(String command, Student student) {
		this.command = command;
		this.student = student;
	}
	
	public ReportHandle(String command, Course course) {
		this.command = command;
		this.course = course;
	}

	public ReportHandle(String command, Teacher teacher) {
		this.command = command;
		this.teacher = teacher;
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


	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	
}
