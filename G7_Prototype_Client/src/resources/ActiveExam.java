package resources;

import java.io.Serializable;

public class ActiveExam implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Exam exam;

	private int duration;

	private String executionCode;

	private int locked;
<<<<<<< HEAD
	
=======

>>>>>>> branch 'master' of https://github.com/projectmaaa/G7
	public ActiveExam(Exam exam, String executionCode) {
		this.exam = exam;
		this.executionCode = executionCode;
		this.duration = exam.getExamDuration();
		locked = 0;
	}

	// setters, getters

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

<<<<<<< HEAD
	@Override
	public String toString() {
		return "ActiveExam [exam=" + exam + ", duration=" + duration + ", executionCode=" + executionCode + ", locked="
				+ locked + "]";
	}
	
	
=======
>>>>>>> branch 'master' of https://github.com/projectmaaa/G7
}
