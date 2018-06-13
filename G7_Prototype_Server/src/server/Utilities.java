package server;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import resources.ActiveExam;
import resources.MyFile;
import resources.QuestionInExam;

public class Utilities {

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

	public static MyFile getWordFile(String executionCode, String studentID) {
		MyFile myFile = new MyFile("./exams/" + executionCode + "_" + studentID + ".docx");
		File file = new File("./exams/" + executionCode + "_" + studentID + ".docx");
		try {
			byte[] byteArray = new byte[(int) file.length()];
			FileInputStream fileInputStream;

			fileInputStream = new FileInputStream(file);

			BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);

			myFile.initArray(byteArray.length);
			myFile.setSize(byteArray.length);

			bufferedInputStream.read(myFile.getMybytearray(), 0, byteArray.length);
			bufferedInputStream.close();
		} catch (IOException e) {
			System.out.println(e);
		}
		return myFile;
	}

}
