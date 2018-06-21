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
	
	private ArrayList<Integer> grades;
	
	private String command;

	public ReportAboutTeacher(String command, double average, int med, Teacher teacher, ArrayList<Integer> grades ) {
		setAverage(average);
		setMedian(med);
		this.command=command;
		this.teacher=teacher;
		this.grades=grades;
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


	public ArrayList<Integer> getGrades() {
		return grades;
	}


	public void setGrades(ArrayList<Integer> grades) {
		this.grades = grades;
	}


}
