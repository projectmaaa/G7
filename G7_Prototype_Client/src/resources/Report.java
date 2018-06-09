package resources;

import java.io.Serializable;

public abstract class Report implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int average;

	private int median;

	// histogram?

	public Report() {

	}

	public int getAverage() {
		return average;
	}

	public void setAverage(int average) {
		this.average = average;
	}

	public int getMedian() {
		return median;
	}

	public void setMedian(int median) {
		this.median = median;
	}

}
