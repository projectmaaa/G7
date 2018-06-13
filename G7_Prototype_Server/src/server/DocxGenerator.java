package server;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

public class DocxGenerator {

	private XWPFDocument document;

	private File docxFile;

	private FileOutputStream fileOutputStream;

	private int index;

	public DocxGenerator(String executionCode, String studentID) throws FileNotFoundException {
		document = new XWPFDocument();
		docxFile = new File("./exams/" + executionCode + "_" + studentID + ".docx");
	}

	public XWPFDocument getDocument() {
		return document;
	}

	public void setDocument(XWPFDocument document) {
		this.document = document;
	}

	public File getDocxFile() {
		return docxFile;
	}

	public void setDocxFile(File docxFile) {
		this.docxFile = docxFile;
	}

	public void addQuestionToWord(String questionText, String firstPossibleAnswer, String secondPossibleAnswer,
			String thirdPossibleAnswer, String fourthPossibleAnswer) throws IOException {
		addQuestion(questionText);
		addQuestionAnswers(firstPossibleAnswer, secondPossibleAnswer, thirdPossibleAnswer, fourthPossibleAnswer);
	}

	private void addQuestion(String questionText) throws IOException {
		fileOutputStream = new FileOutputStream(docxFile);
		XWPFParagraph paragraph = document.createParagraph();
		XWPFRun run = paragraph.createRun();
		run.setBold(true);
		run.setText("" + (++index) + ". " + questionText);
		run.addBreak();
		document.write(fileOutputStream);
		fileOutputStream.close();
	}

	private void addQuestionAnswers(String firstPossibleAnswer, String secondPossibleAnswer, String thirdPossibleAnswer,
			String fourthPossibleAnswer) throws IOException {
		int index = 0;
		fileOutputStream = new FileOutputStream(docxFile);
		XWPFParagraph paragraph = document.createParagraph();
		XWPFRun run = paragraph.createRun();
		run.addTab();
		run.setText("" + (++index) + ". " + firstPossibleAnswer);
		tabAndBreak(run);
		run.setText("" + (++index) + ". " + secondPossibleAnswer);
		tabAndBreak(run);
		run.setText("" + (++index) + ". " + thirdPossibleAnswer);
		tabAndBreak(run);
		run.setText("" + (++index) + ". " + fourthPossibleAnswer);
		tabAndBreak(run);
		document.write(fileOutputStream);
		fileOutputStream.close();
	}

	private void tabAndBreak(XWPFRun run) {
		run.addBreak();
		run.addTab();
	}

}
