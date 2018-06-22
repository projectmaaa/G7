package resources;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class contains the details for the principal report about specific
 * student. This class is the message that sent between the client & the server.
 * 
 * @author Group 7
 *
 */
public class ReportAboutStudent extends Report implements Serializable {

	/******************** Attributes ********************/

	private static final long serialVersionUID = 1L;

	private Student student;

	private ArrayList<Student> students;

	private HashMap<String, Integer> gradesWithExam;

	private String command;

	/******************** Constructors ********************/

	/**
	 * 
	 * @param command
	 * @param average
	 * @param median
	 * @param student
	 * @param grades
	 */
	public ReportAboutStudent(String command, double average, int median, Student student,
			HashMap<String, Integer> grades) {
		this.command = command;
		setAverage(average);
		setMedian(median);
		this.student = student;
		this.gradesWithExam = grades;
	}

	/**
	 * 
	 * @param students
	 * @param command
	 */
	public ReportAboutStudent(ArrayList<Student> students, String command) {
		this.students = students;
		this.command = command;
	}

	/******************** Getters & Setters ********************/

	/**
	 * 
	 * @return The student who's the report about
	 */
	public Student getStudent() {
		return student;
	}

	/**
	 * 
	 * @param student
	 */
	public void setStudent(Student student) {
		this.student = student;
	}

	/**
	 * 
	 * @return The students who's the report about
	 */
	public ArrayList<Student> getStudents() {
		return students;
	}

	/**
	 * 
	 * @param students
	 */
	public void setStudents(ArrayList<Student> students) {
		this.students = students;
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
	 * @return The grades of the student
	 */
	public HashMap<String, Integer> getGrades() {
		return gradesWithExam;
	}

	/**
	 * 
	 * @param grades
	 */
	public void setGrades(HashMap<String, Integer> grades) {
		this.gradesWithExam = grades;
	}

} // end of class