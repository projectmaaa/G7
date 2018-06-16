package resources;

import java.io.Serializable;
import java.util.ArrayList;

public class ReportAboutTeacher extends Report implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String teacherName;
	
	private ArrayList<Teacher> teachers;
	
	private String command;

	public ReportAboutTeacher(int average, int median, String teacherName) {
		super(average, median);
		this.teacherName = teacherName;
	}
	

	public ReportAboutTeacher(ArrayList<Teacher> teachers, String command) {
		super();
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



	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

}
