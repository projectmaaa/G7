package resources;

import java.util.ArrayList;

public class ActiveExam {

	private Exam exam;
	
	private String executionCode;
	
	public ActiveExam(Exam exam, String executionCode) {
		this.exam=exam;
		this.executionCode=executionCode;
	}
	
}