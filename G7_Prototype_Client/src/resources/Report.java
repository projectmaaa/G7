package resources;

public abstract class Report {

	private int average;
	
	private int median;
	
	//histogram?
	
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
