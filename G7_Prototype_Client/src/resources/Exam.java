package resources;

import java.util.ArrayList;

@SuppressWarnings("unused")
public class Exam {

	private String subjectID;
	
	private String courseID;
	
	private static int examCounter;
	
	private int examDuration;
	
	private String freeTextForExaminees;
	
	private String freeTextForTeacherOnly;
	
	private String teacherName;
	
	public Exam (String subjectID, String courseID, int examDuration, String freeTextForExaminees,
			String freeTextForTeacherOnly, String teacherName) {
		
		this.subjectID=subjectID;
		this.courseID=courseID;
		this.examDuration=examDuration;
		this.freeTextForExaminees=freeTextForExaminees;
		this.freeTextForTeacherOnly=freeTextForTeacherOnly;
		this.teacherName=teacherName;
	}
	
	
}
