package resources;

import java.io.Serializable;

public class WaitingActiveExam implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ActiveExam activeExam;
	
	private int newDuration;
	
	private String reason;
	
	public WaitingActiveExam(ActiveExam activeExam, int newDuration, String reason) {
		this.activeExam = activeExam;
		this.newDuration = newDuration;
		this.reason = reason;
	}
	

	public ActiveExam getActiveExam() {
		return activeExam;
	}

	public void setActiveExam(ActiveExam activeExam) {
		this.activeExam = activeExam;
	}

	public int getNewDuration() {
		return newDuration;
	}

	public void setNewDuration(int newDuration) {
		this.newDuration = newDuration;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}	
	
}
