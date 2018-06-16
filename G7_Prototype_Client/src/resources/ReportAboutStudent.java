package resources;

import java.io.Serializable;

public class ReportAboutStudent extends Report implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Student student;

	public ReportAboutStudent(double average, Student student) {
		setAverage(average);
		this.student=student;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	
}
