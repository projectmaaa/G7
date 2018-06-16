package resources;

import java.io.Serializable;
import java.util.ArrayList;

public class StudentHandle implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String command;
	
	private Student student;
	
	private ArrayList<Student> students;

	public StudentHandle(String command, Student student) {
		this.command = command;
		this.student = student;
	}

	public StudentHandle(String command, ArrayList<Student> students) {
		this.command = command;
		this.students = students;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public ArrayList<Student> getStudents() {
		return students;
	}

	public void setStudents(ArrayList<Student> students) {
		this.students = students;
	}
	
}