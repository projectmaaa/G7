package resources;

public class QuestionInExam {
	
	private Exam exam;
	
	private Question question;
	
	private int points;
	
	public QuestionInExam(Exam exam, Question question, int points) {
		this.exam=exam;
		this.question=question;
		this.points=points;
	}
}
