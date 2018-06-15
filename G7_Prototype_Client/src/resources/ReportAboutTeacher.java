package resources;

import java.io.Serializable;

public class ReportAboutTeacher extends Report implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String teacherName;
	
	public ReportAboutTeacher(int average, int median, String teacherName) {
		super(average, median);
		this.teacherName = teacherName;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	
	
}
