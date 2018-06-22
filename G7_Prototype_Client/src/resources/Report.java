package resources;

import java.io.Serializable;

/**
 * This class contains the average & the median that each report must contain.
 * 
 * @author Group 7
 *
 */
public abstract class Report implements Serializable {

	/******************** Attributes ********************/

	private static final long serialVersionUID = 1L;

	private double average;

	private int median;

	/******************** Constructors ********************/

	/**
	 * 
	 */
	public Report() {
	};

	/**
	 * 
	 * @param average
	 * @param median
	 */
	public Report(int average, int median) {
		this.average = average;
		this.median = median;
	}

	/******************** Getters & Setters ********************/

	/**
	 * 
	 * @return The average
	 */
	public double getAverage() {
		return average;
	}

	/**
	 * 
	 * @param average
	 */
	public void setAverage(double average) {
		this.average = average;
	}

	/**
	 * 
	 * @return The median
	 */
	public int getMedian() {
		return median;
	}

	/**
	 * 
	 * @param median
	 */
	public void setMedian(int median) {
		this.median = median;
	}

} // end of class