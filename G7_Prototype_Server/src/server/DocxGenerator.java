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

	public DocxGenerator(String studentID) throws FileNotFoundException {
		document = new XWPFDocument();
		// docxFile = new File("/exams/" + studentID + ".docx");
		docxFile = new File("\\exams\\123.docx");
		// fileOutputStream = new FileOutputStream(docxFile);
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
		run.setText(questionText);
		run.addBreak();
		document.write(fileOutputStream);
		fileOutputStream.close();
	}

	private void addQuestionAnswers(String firstPossibleAnswer, String secondPossibleAnswer, String thirdPossibleAnswer,
			String fourthPossibleAnswer) throws IOException {
		System.out.println("secondPart");
		fileOutputStream = new FileOutputStream(docxFile);
		XWPFParagraph paragraph = document.createParagraph();
		XWPFRun run = paragraph.createRun();
		run.setText(firstPossibleAnswer);
		run.addBreak();
		run.setText(secondPossibleAnswer);
		run.addBreak();
		run.setText(thirdPossibleAnswer);
		run.addBreak();
		run.setText(fourthPossibleAnswer);
		run.addBreak();
		document.write(fileOutputStream);
		fileOutputStream.close();
	}

}
