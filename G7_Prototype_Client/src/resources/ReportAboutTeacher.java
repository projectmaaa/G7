package resources;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * This class contains the details for the principal report about specific
 * teacher. This class is the message that sent between the client & the server.
 * 
 * @author Group 7
 *
 */
public class ReportAboutTeacher extends Report implements Serializable {

	/******************** Attributes ********************/

	private static final long serialVersionUID = 1L;

	private Teacher teacher;

	private ArrayList<Teacher> teachers;

	private ArrayList<Integer> grades;

	private String command;

	/******************** Constructors ********************/

	/**
	 * 
	 * @param command
	 * @param average
	 * @param med
	 * @param teacher
	 * @param grades
	 */
	public ReportAboutTeacher(String command, double average, int med, Teacher teacher, ArrayList<Integer> grades) {
		setAverage(average);
		setMedian(med);
		this.command = command;
		this.teacher = teacher;
		this.grades = grades;
	}

	/**
	 * 
	 * @param teachers
	 * @param command
	 */
	public ReportAboutTeacher(ArrayList<Teacher> teachers, String command) {
		this.teachers = teachers;
		this.command = command;
	}

	/******************** Getters & Setters ********************/

	/**
	 * 
	 * @return The teachers who's the report about
	 */
	public ArrayList<Teacher> getTeachers() {
		return teachers;
	}

	/**
	 * 
	 * @param teachers
	 */
	public void setTeachers(ArrayList<Teacher> teachers) {
		this.teachers = teachers;
	}

	/**
	 * 
	 * @return The message what to do with the report
	 */
	public String getCommand() {
		return command;
	}

	/**
	 * 
	 * @param command
	 */
	public void setCommand(String command) {
		this.command = command;
	}

	/**
	 * 
	 * @return The teacher who's the report about
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
	 * @return The grades that is going to be shown in the report
	 */
	public ArrayList<Integer> getGrades() {
		return grades;
	}

	/**
	 * 
	 * @param grades
	 */
	public void setGrades(ArrayList<Integer> grades) {
		this.grades = grades;
	}

} // end of class