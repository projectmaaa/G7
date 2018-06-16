package resources;

import java.io.Serializable;
import java.util.ArrayList;

public class ReportAboutStudent extends Report implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Student student;
	
	private ArrayList<Student> students;
	
	private String command;

	public ReportAboutStudent(String command, double average, Student student) {
		this.command=command;
		setAverage(average);
		this.student=student;
	}

	public ReportAboutStudent(ArrayList<Student> students, String command) {
		super();
		this.students = students;
		this.command = command;
	}



	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public ArrayList<Student> getStudents() {
		return students;
	}

	public void setStudents(ArrayList<Student> students) {
		this.students = students;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	
}
