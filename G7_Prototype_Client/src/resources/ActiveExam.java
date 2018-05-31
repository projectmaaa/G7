package resources;


public class ActiveExam {

	private Exam exam;
	
	private String executionCode;
	
	public ActiveExam(Exam exam, String executionCode) {
		this.exam=exam;
		this.executionCode=executionCode;
	}
	
	//setters, getters

	public Exam getExam() {
		return exam;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}

	public String getExecutionCode() {
		return executionCode;
	}

	public void setExecutionCode(String executionCode) {
		this.executionCode = executionCode;
	}
	
}
