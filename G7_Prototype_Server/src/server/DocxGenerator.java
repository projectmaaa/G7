package server;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

/**
 * This class generating all the data into word document.
 * 
 * @author Group 7
 *
 */
public class DocxGenerator {

	/******************** Attributes ********************/

	private XWPFDocument document;

	private File docxFile;

	private FileOutputStream fileOutputStream;

	private int index;

	/******************** Constructors ********************/

	/**
	 * 
	 * @param executionCode
	 * @param studentID
	 * @throws FileNotFoundException
	 */
	public DocxGenerator(String executionCode, String studentID) throws FileNotFoundException {
		document = new XWPFDocument();
		docxFile = new File("./exams/" + executionCode + "_" + studentID + ".docx");
	}

	/******************** Getters & Setters ********************/

	/**
	 * 
	 * @return The word document
	 */
	public XWPFDocument getDocument() {
		return document;
	}

	/**
	 * 
	 * @param document
	 */
	public void setDocument(XWPFDocument document) {
		this.document = document;
	}

	/**
	 * 
	 * @return The word file
	 */
	public File getDocxFile() {
		return docxFile;
	}

	/**
	 * 
	 * @param docxFile
	 */
	public void setDocxFile(File docxFile) {
		this.docxFile = docxFile;
	}

	/******************** Methods ********************/

	/**
	 * Adding the question and the answers into the word file
	 * 
	 * @param questionText
	 * @param firstPossibleAnswer
	 * @param secondPossibleAnswer
	 * @param thirdPossibleAnswer
	 * @param fourthPossibleAnswer
	 * @throws IOException
	 */
	public void addQuestionToWord(String questionText, String firstPossibleAnswer, String secondPossibleAnswer,
			String thirdPossibleAnswer, String fourthPossibleAnswer) throws IOException {
		addQuestion(questionText);
		addQuestionAnswers(firstPossibleAnswer, secondPossibleAnswer, thirdPossibleAnswer, fourthPossibleAnswer);
	}

	/**
	 * Adding the question
	 * 
	 * @param questionText
	 * @throws IOException
	 */
	private void addQuestion(String questionText) throws IOException {
		fileOutputStream = new FileOutputStream(docxFile);
		XWPFParagraph paragraph = document.createParagraph();
		paragraph.setAlignment(ParagraphAlignment.LEFT);
		XWPFRun run = paragraph.createRun();
		run.setBold(true);
		run.setText("" + (++index) + ". " + questionText);
		run.addBreak();
		document.write(fileOutputStream);
		fileOutputStream.close();
	}

	/**
	 * Adding the answers
	 * 
	 * @param firstPossibleAnswer
	 * @param secondPossibleAnswer
	 * @param thirdPossibleAnswer
	 * @param fourthPossibleAnswer
	 * @throws IOException
	 */
	private void addQuestionAnswers(String firstPossibleAnswer, String secondPossibleAnswer, String thirdPossibleAnswer,
			String fourthPossibleAnswer) throws IOException {
		int index = 0;
		fileOutputStream = new FileOutputStream(docxFile);
		XWPFParagraph paragraph = document.createParagraph();
		paragraph.setAlignment(ParagraphAlignment.LEFT);
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

	/**
	 * Adding the instructions
	 * 
	 * @param instruction
	 * @throws IOException
	 */
	public void addInstruction(String instruction) throws IOException {
		fileOutputStream = new FileOutputStream(docxFile);
		XWPFParagraph paragraph = document.createParagraph();
		paragraph.setAlignment(ParagraphAlignment.LEFT);
		XWPFRun run = paragraph.createRun();
		run.setBold(true);
		run.setText(instruction);
		run.addBreak();
		run.addBreak();
		run.addBreak();
		document.write(fileOutputStream);
		fileOutputStream.close();
	}

	/**
	 * Add tab & break
	 * 
	 * @param run
	 */
	private void tabAndBreak(XWPFRun run) {
		run.addBreak();
		run.addTab();
	}

} // end of class