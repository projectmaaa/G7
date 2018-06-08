package resources;

public class StudentInActiveExam {

	private String date;

	private String startedTime;

	private Student student;

	private ActiveExam activeExam;

	private boolean sumbited;

	public StudentInActiveExam(Student student, ActiveExam activeExam) {
		super();
		this.date = Utilities.setDate();
		this.startedTime = Utilities.getTime();
		this.student = student;
		this.activeExam = activeExam;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getStartedTime() {
		return startedTime;
	}

	public void setStartedTime(String startedTime) {
		this.startedTime = startedTime;
	}

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

	public boolean isSumbited() {
		return sumbited;
	}

	public void setSumbited(boolean sumbited) {
		this.sumbited = sumbited;
	}

}
