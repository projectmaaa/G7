package resources;

public class StudentInActiveExam {

	private Student student;
	
	private ActiveExam activeExam;
	
	private int timeToSolve;

	public StudentInActiveExam(Student student, ActiveExam activeExam, int timeToSolve) {
		this.student = student;
		this.activeExam = activeExam;
		//this.timeToSolve = timeToSolve;
	}

	//setters, getters
	
	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public ActiveExam getActiveExam() {
		return activeExam;
	}

	public void setActiveExam(ActiveExam activeExam) {
		this.activeExam = activeExam;
	}

	public int getTimeToSolve() {
		return timeToSolve;
	}

	public void setTimeToSolve(int timeToSolve) {
		this.timeToSolve = timeToSolve;
	}
	
	
	
}
