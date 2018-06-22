package resources;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * The class that holds the exam\s & the command what to do with the
 * exam\s. This class is the message that sent between the client & the server.
 * 
 * @author Group 7
 *
 */
public class ExamHandle implements Serializable {

	/******************** Attributes ********************/
	
	private static final long serialVersionUID = 1L;

	private String command;

	private Exam exam;

	private ArrayList<Exam> exams;
	
	/******************** Constructors ********************/

	/**
	 * 
	 * @param command
	 * @param exam
	 */
	public ExamHandle(String command, Exam exam) {
		this.command = command;
		this.exam = exam;
	}

	/**
	 * 
	 * @param command
	 * @param exams
	 */
	public ExamHandle(String command, ArrayList<Exam> exams) {
		this.command = command;
		this.setExams(exams);
	}

	/******************** Getters & Setters ********************/
	
	/**
	 * 
	 * @return The message what to do with the exam\s
	 */
	public String getCommand() {
		return command;
	}

	/**
	 * 
	 * @param command
	 */
	public void setCommand(String command) {
		this.command = command;
	}

	/**
	 * 
	 * @return The exam
	 */
	public Exam getExam() {
		return exam;
	}

	/**
	 * 
	 * @param exam
	 */
	public void setExam(Exam exam) {
		this.exam = exam;
	}

	/**
	 * 
	 * @return The exams
	 */
	public ArrayList<Exam> getExams() {
		return exams;
	}

	/**
	 * 
	 * @param exams
	 */
	public void setExams(ArrayList<Exam> exams) {
		this.exams = exams;
	}

} // end of class