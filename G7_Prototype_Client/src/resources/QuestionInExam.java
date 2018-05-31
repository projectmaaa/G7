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
	
	//setters, getters

	public Exam getExam() {
		return exam;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}
	
}
