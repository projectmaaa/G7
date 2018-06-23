package resources;

import java.io.Serializable;

/**
 * The class that contains the data of exam that has been activated. This is the
 * class that represents the exam with it's own execution code.
 * 
 * @author Group 7
 *
 */
public class ActiveExam implements Serializable {

	/******************** Attributes ********************/

	private static final long serialVersionUID = 1L;

	private Exam exam;

	private int duration;

	private String executionCode;

	private int locked;

	private String type;

	private String activator;

	private String activatorsID;

	/******************** Constructors ********************/

	/**
	 * 
	 * @param exam
	 *            The exam
	 * @param executionCode
	 *            The execution code
	 */
	public ActiveExam(Exam exam, String executionCode) {
		this.exam = exam;
		this.executionCode = executionCode;
		this.duration = exam.getExamDuration();
		locked = 0;
	}

	/**
	 * 
	 * @param exam
	 *            The exam
	 * @param executionCode
	 *            The execution code
	 * @param type
	 *            The type
	 * @param activator
	 *            Activator's name
	 * @param activatorsID
	 *            Activator's ID
	 */
	public ActiveExam(Exam exam, String executionCode, String type, String activator, String activatorsID) {
		this.exam = exam;
		this.executionCode = executionCode;
		this.duration = exam.getExamDuration();
		this.type = type;
		this.activator = activator;
		this.activatorsID = activatorsID;
		locked = 0;
	}

	/**
	 * 
	 * @param exam
	 *            The exam
	 * @param executionCode
	 *            The execution code
	 * @param duration
	 *            The duration
	 */
	public ActiveExam(Exam exam, String executionCode, int duration) {
		this.exam = exam;
		this.executionCode = executionCode;
		this.duration = duration;
	}

	/**
	 * 
	 * @param exam
	 *            The exam
	 * @param executionCode
	 *            The execution code
	 * @param activator
	 *            Activator's name
	 * @param duration
	 *            The duration
	 * @param locked
	 *            If locked
	 * @param type
	 *            The type
	 */
	public ActiveExam(Exam exam, String executionCode, String activator, int duration, int locked, String type) {
		this.exam = exam;
		this.executionCode = executionCode;
		this.activator = activator;
		this.duration = duration;
		this.locked = locked;
		this.type = type;
	}

	/**
	 * 
	 * @param exam
	 *            The exam
	 * @param duration
	 *            The duration
	 * @param executionCode
	 *            The execution code
	 * @param type
	 *            The type
	 */
	public ActiveExam(Exam exam, int duration, String executionCode, String type) {
		this.exam = exam;
		this.duration = duration;
		this.executionCode = executionCode;
		this.type = type;
	}

	/**
	 * 
	 * @param executionCode
	 *            The execution code
	 */
	public ActiveExam(String executionCode) {
		this.executionCode = executionCode;
	}

	/******************** Getters and Setters ********************/

	/**
	 * 
	 * @return The exam itself
	 */
	public Exam getExam() {
		return exam;
	}

	/**
	 * 
	 * @param exam
	 *            Exam
	 */
	public void setExam(Exam exam) {
		this.exam = exam;
	}

	/**
	 * 
	 * @return The execution code of the exam
	 */
	public String getExecutionCode() {
		return executionCode;
	}

	/**
	 * 
	 * @param executionCode
	 *            Execution Code
	 */
	public void setExecutionCode(String executionCode) {
		this.executionCode = executionCode;
	}

	/**
	 * 
	 * @return The duration of the exam
	 */
	public int getDuration() {
		return duration;
	}

	/**
	 * 
	 * @param duration
	 *            Duration
	 */
	public void setDuration(int duration) {
		this.duration = duration;
	}

	/**
	 * 
	 * @return The value of the locked flag. 1=locked, 0=Not yet locked.
	 */
	public int getLocked() {
		return locked;
	}

	/**
	 * 
	 * @param locked
	 *            Locked Value
	 */
	public void setLocked(int locked) {
		this.locked = locked;
	}

	/**
	 * 
	 * @return Returns true if the exam is locked
	 */
	public boolean isLocked() {
		if (locked == 1) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @return Duration in string format
	 */
	public String getDurationInString() {
		Integer dur = duration;
		return dur.toString();
	}

	/**
	 * 
	 * @return The type of the exam: Computerized or Manual
	 */
	public String getType() {
		return type;
	}

	/**
	 * 
	 * @param type
	 *            Type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * 
	 * @return The teacher's name who activated this exam with this specific
	 *         execution code
	 */
	public String getActivator() {
		return activator;
	}

	/**
	 * 
	 * @param activator
	 *            Activator's Name
	 */
	public void setActivator(String activator) {
		this.activator = activator;
	}

	/**
	 * 
	 * @return The teacher's ID who activated this exam with this specific execution
	 *         code
	 */
	public String getActivatorsID() {
		return activatorsID;
	}

	/**
	 * 
	 * @param activatorsID
	 *            Activator's ID
	 */
	public void setActivatorsID(String activatorsID) {
		this.activatorsID = activatorsID;
	}

	/******************** Methods ********************/

	/**
	 * @return The exam details
	 */
	@Override
	public String toString() {
		return "[CourseID : " + exam.getCourseID() + ", ExamNum : " + exam.getExamNum() + ", TextForExaminees : "
				+ exam.getFreeTextForExaminees() + ", TextForTeacherOnly : " + exam.getFreeTextForTeacherOnly()
				+ ", SubjectID : " + exam.getSubjectID() + ", TeacherName : " + exam.getTeacherName() + "]\n[duration="
				+ duration + ", executionCode=" + executionCode + ", locked=" + locked + "]" + questionString();
	}

	/**
	 * 
	 * @return The details of the questions in the exam
	 */
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

} // end of class