package resources;

import java.io.Serializable;

public abstract class Report implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private double average;

	private int median;

	// histogram?
	
	public Report() {
		
	}

	public Report(int average, int median) {
		this.average = average;
		this.median = median;
	}

	public double getAverage() {
		return average;
	}

	public void setAverage(double average2) {
		this.average = average2;
	}

	public int getMedian() {
		return median;
	}

	public void setMedian(int median) {
		this.median = median;
	}

}
