package resources;

import java.io.Serializable;
import java.util.ArrayList;

public class ReportAboutTeacher extends Report implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Teacher teacher;
	
	private ArrayList<Teacher> teachers;
	
	private String command;

	public ReportAboutTeacher(String command, double average, Teacher teacher ) {
		setAverage(average);
		this.command=command;
		this.teacher=teacher;
	}
	

	public ReportAboutTeacher(ArrayList<Teacher> teachers, String command) {
		this.teachers = teachers;
		this.command = command;
	}



	public ArrayList<Teacher> getTeachers() {
		return teachers;
	}



	public void setTeachers(ArrayList<Teacher> teachers) {
		this.teachers = teachers;
	}



	public String getCommand() {
		return command;
	}



	public void setCommand(String command) {
		this.command = command;
	}


	public Teacher getTeacher() {
		return teacher;
	}


	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}



}
