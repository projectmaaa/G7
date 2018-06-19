package resources;

import java.io.Serializable;

public class ExamReportHandle implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String subject;

	private String course;

	private String examNum;

	private String command;

	public ExamReportHandle(String subject, String course, String examNum, String command) {
		this.subject = subject;
		this.course = course;
		this.examNum = examNum;
		this.command = command;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public String getExamNum() {
		return examNum;
	}

	public void setExamNum(String examNum) {
		this.examNum = examNum;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

}
