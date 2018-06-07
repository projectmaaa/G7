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

	public ActiveExam(Exam exam, String executionCode) {
		this.exam = exam;
		this.executionCode = executionCode;
		this.duration = exam.getExamDuration();
		locked = 0;
	}
	
	public ActiveExam(Exam exam, String executionCode, int duration) {
		this.exam = exam;
		this.executionCode = executionCode;
		this.duration = duration;
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
	
	public String getDurationInString() {
		Integer dur = duration;
		return dur.toString();
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
