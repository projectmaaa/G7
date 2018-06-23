package resources;

import java.io.Serializable;

/**
 * This class contains the information about exam and the changing time request.
 * 
 * @author Group 7
 *
 */
public class WaitingActiveExam implements Serializable {

	/******************** Attributes ********************/

	private static final long serialVersionUID = 1L;

	private ActiveExam activeExam;

	private int newDuration;

	private String reason;

	/******************** Constructors ********************/

	/**
	 * 
	 * @param activeExam
	 *            Active Exam
	 * @param newDuration
	 *            New Requested Duration
	 * @param reason
	 *            Reason for changing
	 */
	public WaitingActiveExam(ActiveExam activeExam, int newDuration, String reason) {
		this.activeExam = activeExam;
		this.newDuration = newDuration;
		this.reason = reason;
	}

	/******************** Getters and Setters ********************/

	/**
	 * 
	 * @return The active exam
	 */
	public ActiveExam getActiveExam() {
		return activeExam;
	}

	/**
	 * 
	 * @param activeExam
	 *            The active exam
	 */
	public void setActiveExam(ActiveExam activeExam) {
		this.activeExam = activeExam;
	}

	/**
	 * 
	 * @return The new requested duration
	 */
	public int getNewDuration() {
		return newDuration;
	}

	/**
	 * 
	 * @param newDuration
	 *            The new requested duration
	 */
	public void setNewDuration(int newDuration) {
		this.newDuration = newDuration;
	}

	/**
	 * 
	 * @return The reason for changing the time
	 */
	public String getReason() {
		return reason;
	}

	/**
	 * 
	 * @param reason
	 *            The reason for changing the time
	 */
	public void setReason(String reason) {
		this.reason = reason;
	}

} // end of class