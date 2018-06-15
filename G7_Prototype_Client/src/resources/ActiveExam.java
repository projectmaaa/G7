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

	private String type;

	private String activator;

	public ActiveExam(Exam exam, String executionCode) {
		this.exam = exam;
		this.executionCode = executionCode;
		this.duration = exam.getExamDuration();
		locked = 0;
	}

	public ActiveExam(Exam exam, String executionCode, String type, String activator) {
		this.exam = exam;
		this.executionCode = executionCode;
		this.duration = exam.getExamDuration();
		this.type = type;
		this.activator = activator;
		locked = 0;
	}

	public ActiveExam(Exam exam, String executionCode, int duration) {
		this.exam = exam;
		this.executionCode = executionCode;
		this.duration = duration;
	}

	public ActiveExam(Exam exam, int duration, String executionCode, String type) {
		this.exam = exam;
		this.duration = duration;
		this.executionCode = executionCode;
		this.type = type;
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

	public boolean isLocked() {
		if (locked == 1) {
			return true;
		}
		return false;
	}

	public String getDurationInString() {
		Integer dur = duration;
		return dur.toString();
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getActivator() {
		return activator;
	}

	public void setActivator(String activator) {
		this.activator = activator;
	}

	@Override
	public String toString() {
		return "[CourseID : " + exam.getCourseID() + ", ExamNum : " + exam.getExamNum() + ", TextForExaminees : "
				+ exam.getFreeTextForExaminees() + ", TextForTeacherOnly : " + exam.getFreeTextForTeacherOnly()
				+ ", SubjectID : " + exam.getSubjectID() + ", TeacherName : " + exam.getTeacherName() + "]\n[duration="
				+ duration + ", executionCode=" + executionCode + ", locked=" + locked + "]" + questionString();
	}

	private String questionString() {
		String string = "";
		for (QuestionInExam questionInExam : exam.getQuestions()) {
			string += "\n";
			string += "Question :  " + questionInExam.getQuestion().getQuestionText() + "\n";
			string += "FirstPossibleAnswer : " + questionInExam.getQuestion().getFirstPossibleAnswer() + "\n";
			string += "SecondPossibleAnswer : " + questionInExam.getQuestion().getSecondPossibleAnswer() + "\n";
			string += "ThirdPossibleAnswer : " + questionInExam.getQuestion().getThirdPossibleAnswer() + "\n";
			string += "FourthPossibleAnswer : " + questionInExam.getQuestion().getFourthPossibleAnswer();
		}
		return string;
	}
}
