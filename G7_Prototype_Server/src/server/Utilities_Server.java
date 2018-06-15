package server;

import java.io.IOException;

import resources.ActiveExam;
import resources.QuestionInExam;

public class Utilities_Server {

	public static void getManualExam(ActiveExam activeExam, String executionCode, String userID) throws IOException {
		DocxGenerator docxGenerator = new DocxGenerator(executionCode, userID);
		for (QuestionInExam questionInExam : activeExam.getExam().getQuestions()) {
			docxGenerator.addQuestionToWord(questionInExam.getQuestionText(),
					questionInExam.getQuestion().getFirstPossibleAnswer(),
					questionInExam.getQuestion().getSecondPossibleAnswer(),
					questionInExam.getQuestion().getThirdPossibleAnswer(),
					questionInExam.getQuestion().getFourthPossibleAnswer());
		}
		docxGenerator.getDocument().close();
	}

}
