package resources;

import java.io.Serializable;

/**
 * This class contains the information about exam & the changing time request.
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
	 * @param newDuration
	 * @param reason
	 */
	public WaitingActiveExam(ActiveExam activeExam, int newDuration, String reason) {
		this.activeExam = activeExam;
		this.newDuration = newDuration;
		this.reason = reason;
	}

	/******************** Getters & Setters ********************/

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
	 */
	public void setReason(String reason) {
		this.reason = reason;
	}

} // end of class