package server;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import resources.ActiveExam;
import resources.QuestionInExam;
import resources.StudentAnswerInQuestion;
import resources.SubmittedExam;

public class Utilities_Server {

	public static void getManualExam(ActiveExam activeExam, String executionCode, String userID) throws IOException {
		DocxGenerator docxGenerator = new DocxGenerator(executionCode, userID);
		docxGenerator.addInstruction(activeExam.getExam().getFreeTextForExaminees());
		for (QuestionInExam questionInExam : activeExam.getExam().getQuestions()) {
			docxGenerator.addQuestionToWord(questionInExam.getQuestionText(),
					questionInExam.getQuestion().getFirstPossibleAnswer(),
					questionInExam.getQuestion().getSecondPossibleAnswer(),
					questionInExam.getQuestion().getThirdPossibleAnswer(),
					questionInExam.getQuestion().getFourthPossibleAnswer());
		}
		docxGenerator.getDocument().close();
	}

	/**
	 * Returns a calculated grade of exam
	 * 
	 * @param exam
	 * @param connection
	 * @return the grade
	 * @throws SQLException
	 */
	public static int getCalculateExamGrade(SubmittedExam exam, Connection connection) throws SQLException {
		int grade = 0;
		for (StudentAnswerInQuestion question : exam.getAnswers()) {
			if (SqlUtilities.getCorrectAnswer(question.getSubjectID(), question.getQuestionNum(), connection)
					.equals(question.getStudentAnswer()))
				grade += SqlUtilities.getPointsOfQuestion(question.getSubjectID(), question.getQuestionNum(),
						exam.getStudentInActiveExam().getActiveExam().getExam().getCourseID(),
						exam.getStudentInActiveExam().getActiveExam().getExam().getExamNum(), connection);
		}
		return grade;
	}

}
