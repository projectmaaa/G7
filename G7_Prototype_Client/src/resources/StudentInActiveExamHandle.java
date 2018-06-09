package resources;

import java.io.Serializable;

public class StudentInActiveExamHandle implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String command;

	private StudentInActiveExam studentInActiveExam;

	public StudentInActiveExamHandle(String command, StudentInActiveExam studentInActiveExam) {
		super();
		this.command = command;
		this.studentInActiveExam = studentInActiveExam;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public StudentInActiveExam getStudentInActiveExam() {
		return studentInActiveExam;
	}

	public void setStudentInActiveExam(StudentInActiveExam studentInActiveExam) {
		this.studentInActiveExam = studentInActiveExam;
	}

}
