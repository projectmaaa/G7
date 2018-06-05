package resources;

import java.io.Serializable;

public class ActiveExam implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Exam exam;
	
	private int duration;
	
	private String executionCode;
	
	public ActiveExam(Exam exam, String executionCode) {
		this.exam=exam;
		this.executionCode=executionCode;
		this.duration = exam.getExamDuration();
		locked=0;
		
	}
	
	//setters, getters

	public Exam getExam() {
		return exam;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}

	public String getExecutionCode() {
		return executionCode;
	}

	public void setExecutionCode(String executionCode) {
		this.executionCode = executionCode;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}
	
	public int getLocked() {
		return locked;
	}

	public void setLocked(int locked) {
		this.locked = locked;
	}
	
	
}
