package resources;

import java.io.Serializable;
import java.util.ArrayList;

public class ExamHandle implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String command;

	private Exam exam;

	private ArrayList<Exam> exams;

	private ActiveExam activeExam;

	public ExamHandle(String command, Exam exam) {
		this.command = command;
		this.exam = exam;
	}

	public ExamHandle(String command, ArrayList<Exam> exams) {
		this.command = command;
		this.setExams(exams);
	}

	public ExamHandle(String command, ActiveExam activeExam) {
		this.command = command;
		this.activeExam = activeExam;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public Exam getExam() {
		return exam;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}

	public ArrayList<Exam> getExams() {
		return exams;
	}

	public void setExams(ArrayList<Exam> exams) {
		this.exams = exams;
	}

	public ActiveExam getActiveExam() {
		return activeExam;
	}

	public void setActiveExam(ActiveExam activeExam) {
		this.activeExam = activeExam;
	}

}
