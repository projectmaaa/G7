package resources;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * The class that holds the student\s and the command what to do with the
 * student\s. This class is the message that sent between the client and the
 * server.
 * 
 * @author Group 7
 *
 */
public class StudentHandle implements Serializable {

	/******************** Attributes ********************/

	private static final long serialVersionUID = 1L;

	private String command;

	private Student student;

	private ArrayList<Student> students;

	private HashMap<Student, ArrayList<Student>> copeied;

	/******************** Constructors ********************/

	/**
	 * 
	 * @param command
	 *            What To Do
	 * @param student
	 *            Student
	 */
	public StudentHandle(String command, Student student) {
		this.command = command;
		this.student = student;
	}

	/**
	 * 
	 * @param command
	 *            What To Do
	 * @param students
	 *            List of Students
	 */
	public StudentHandle(String command, ArrayList<Student> students) {
		this.command = command;
		this.students = students;
	}

	/**
	 * 
	 * @param command
	 *            What To Do
	 * @param copeied
	 *            List of Students who copied
	 */
	public StudentHandle(String command, HashMap<Student, ArrayList<Student>> copeied) {
		this.command = command;
		this.copeied = copeied;
	}

	/******************** Getters and Setters ********************/

	/**
	 * 
	 * @return The student
	 */
	public Student getStudent() {
		return student;
	}

	/**
	 * 
	 * @param student
	 *            The student
	 */
	public void setStudent(Student student) {
		this.student = student;
	}

	/**
	 * 
	 * @return The message what to do with the student\s information
	 */
	public String getCommand() {
		return command;
	}

	/**
	 * 
	 * @param command
	 *            The message what to do with the student\s information
	 */
	public void setCommand(String command) {
		this.command = command;
	}

	/**
	 * 
	 * @return The list of students who copied from specific student
	 */
	public HashMap<Student, ArrayList<Student>> getCopeied() {
		return copeied;
	}

	/**
	 * 
	 * @param copeied
	 *            The list of students who copied from specific student
	 */
	public void setCopeied(HashMap<Student, ArrayList<Student>> copeied) {
		this.copeied = copeied;
	}

	/**
	 * 
	 * @return The students
	 */
	public ArrayList<Student> getStudents() {
		return students;
	}

	/**
	 * 
	 * @param students
	 *            The students
	 */
	public void setStudents(ArrayList<Student> students) {
		this.students = students;
	}

} // end of class