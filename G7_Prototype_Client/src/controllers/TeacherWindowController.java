package controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import boundaries.QuestionInComputerizeExam;
import client.Client;
import client.MainAppClient;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.converter.IntegerStringConverter;
import resources.*;

/**
 * This class represents controller for Teacher Window (Gui).
 * 
 * @author Group 7
 *
 */
public class TeacherWindowController implements Initializable, IScreenController {

	/******************** Attributes ********************/

	@FXML
	private AnchorPane mainAnchorPane;

	@FXML
	private AnchorPane welcomeAnchorPane;

	@FXML
	private AnchorPane backAnchorPane;

	@FXML
	private Text TextGroup7;

	@FXML
	private ImageView Group7Logo;

	@FXML
	private Button logoutButton;

	@FXML
	private Text date;

	@FXML
	private Text welcomeText;

	/***** Questions Attributes *****/

	@FXML
	private MenuItem editOrRemoveQuestion;

	@FXML
	private AnchorPane questionsTableAnchorPaneInEditOrRemove;

	@FXML
	private TableView<Question> tableViewInEditOrRemove;

	@FXML
	private TableColumn<Question, String> subjectIDColumnInEditOrRemove;

	@FXML
	private TableColumn<Question, String> questionNumColumnInEditOrRemove;

	@FXML
	private TableColumn<Question, String> authorColumnInEditOrRemove;

	@FXML
	private TableColumn<Question, String> questionTextColumnInEditOrRemove;

	@FXML
	private TableColumn<Question, String> firstPossibleAnswerColumnInEditOrRemove;

	@FXML
	private TableColumn<Question, String> secondPossibleAnswerColumnInEditOrRemove;

	@FXML
	private TableColumn<Question, String> thirdPossibleAnswerColumnInEditOrRemove;

	@FXML
	private TableColumn<Question, String> fourthPossibleAnswerColumnInEditOrRemove;

	@FXML
	private TableColumn<Question, String> correctAnswerColumnInEditOrRemove;

	@FXML
	private Button saveButton;

	@FXML
	private Button removeButton;

	@FXML
	private AnchorPane addQuestionAnchorPane;

	@FXML
	private Label subject;

	@FXML
	private ComboBox<String> subjectComboBoxInAddQuestion;

	@FXML
	private Label questionText;

	@FXML
	private TextField questionTextField;

	@FXML
	private Label firstAnswer;

	@FXML
	private TextField firstAnswerField;

	@FXML
	private Label secondAnswer;

	@FXML
	private TextField secondAnswerField;

	@FXML
	private Label thirdAnswer;

	@FXML
	private TextField thirdAnswerField;

	@FXML
	private Label fourthAnswer;

	@FXML
	private TextField fourthAnswerField;

	@FXML
	private Label correcthAnswer;

	@FXML
	private ComboBox<String> correctAnswerComboBox;

	@FXML
	private Button createQuestionButton;

	@FXML
	private Button clearButton;

	@FXML
	private ComboBox<String> subjectComboBoxInEditOrRemove;

	/***** Create Exam Attributes ****/

	@FXML
	private AnchorPane createExamAnchorPane;

	/***************************************************/

	@FXML
	private AnchorPane anchorPaneInCreateExamFirstWindow;

	@FXML
	private ComboBox<String> subjectInCreateExamComboBox;

	@FXML
	private ComboBox<String> courseInCreateExamComboBox;

	@FXML
	private TextField durationInCreateExamField;

	@FXML
	private Button createExamNextButtonFirstWindow;

	/***************************************************/

	@FXML
	private AnchorPane anchorPaneInCreateExamSecondWindow;

	@FXML
	private TableView<Question> tableViewInCreateExamAllQuestion;

	@FXML
	private TableColumn<Question, String> subjectIDColumnInCreateExamAllQuestions;

	@FXML
	private TableColumn<Question, String> questionNumColumnInCreateExamAllQuestions;

	@FXML
	private TableColumn<Question, String> authorColumnInCreateExamAllQuestions;

	@FXML
	private TableColumn<Question, String> questionTextColumnInCreateExamAllQuestions;

	@FXML
	private TableColumn<Question, String> possibleAnswersColumnInCreateExamAllQuestions;

	@FXML
	private TableColumn<Question, String> firstPossibleAnswerColumnInCreateExamAllQuestions;

	@FXML
	private TableColumn<Question, String> secondPossibleAnswerColumnInCreateExamAllQuestions;

	@FXML
	private TableColumn<Question, String> thirdPossibleAnswerColumnInCreateExamAllQuestions;

	@FXML
	private TableColumn<Question, String> fourthPossibleAnswerColumnInCreateExamAllQuestions;

	@FXML
	private TableColumn<Question, String> correctAnswerColumnInCreateExamAllQuestions;

	@FXML
	private TableView<QuestionInExam> tableViewInCreateExamQuestion;

	@FXML
	private TableColumn<Question, String> subjectIDColumnInCreateExam;

	@FXML
	private TableColumn<Question, String> questionNumberColumnInCreateExam;

	@FXML
	private TableColumn<Question, String> questionTextColumnInCreateExam;

	@FXML
	private TableColumn<QuestionInExam, Integer> pointsColumnInCreateExam;

	@FXML
	private Button createExamNextButtonSecondWindow;

	/***************************************************/

	@FXML
	private AnchorPane anchorPaneInCreateExamThirdWindow;

	@FXML
	private TextArea textAreaStudentsInCreateExam;

	@FXML
	private TextArea textAreaTeachersInCreateExam;

	@FXML
	private Button createExamButton;

	/***** Exam Management Attributes *****/

	@FXML
	private AnchorPane examManagementAnchorPane;

	@FXML
	private ComboBox<String> subjectExamManagement;

	@FXML
	private ComboBox<String> courseExamManagement;

	@FXML
	private Button examManagementUpdateButton;

	@FXML
	private Button activateButton;

	@FXML
	private Button deleteExamButton;

	@FXML
	private Button showQuestionsButton;

	@FXML
	private Button showExamsButtonInExamManagement;

	@FXML
	private TableColumn<Exam, String> subjectColInExamsManagement;

	@FXML
	private TableColumn<Exam, String> courseColInExamsManagement;

	@FXML
	private TableColumn<Exam, String> examNumColInExamsManagement;

	@FXML
	private TableColumn<Exam, String> authorColInExamsManagement;

	@FXML
	private TableColumn<Exam, String> durationColInExamsManagement;

	@FXML
	private TableColumn<Exam, String> textExamineesColInExamsManagement;

	@FXML
	private TableColumn<Exam, String> textTeachersColInExamsManagement;

	@FXML
	private TableView<Exam> tableViewInExamsManagement;

	@FXML
	private TextField executionCode;

	@FXML
	private TableView<Question> questionsOfSpecifcExamTable;

	@FXML
	private TableColumn<Question, String> subjectIDColumnInExamManagement;

	@FXML
	private TableColumn<Question, String> questionNumColumnInExamManagement;

	@FXML
	private TableColumn<Question, String> authorColumnInExamManagement;

	@FXML
	private TableColumn<Question, String> questionTextColumnInExamManagement;

	@FXML
	private TableColumn<Question, String> firstPossibleAnswerColumnInExamManagement;

	@FXML
	private TableColumn<Question, String> secondPossibleAnswerColumnInExamManagement;

	@FXML
	private TableColumn<Question, String> thirdPossibleAnswerColumnInExamManagement;

	@FXML
	private TableColumn<Question, String> fourthPossibleAnswerColumnInExamManagement;

	@FXML
	private TableColumn<Question, String> correctAnswerColumnInExamManagement;

	/***** Active Exam Management Attributes *****/

	@FXML
	private AnchorPane activeExamManagementAnchorPane;

	@FXML
	private TableView<ActiveExam> activeExamsTableView;

	@FXML
	private TableColumn<ActiveExam, String> subjectNumberOfActiveExam;

	@FXML
	private TableColumn<ActiveExam, String> courseNumberOfAvticeExam;

	@FXML
	private TableColumn<ActiveExam, String> examNumberOfAvticeExam;

	@FXML
	private TableColumn<ActiveExam, String> executionCodeOfAvticeExam;

	@FXML
	private TableColumn<ActiveExam, Integer> durationOfAvticeExam;

	@FXML
	private TableColumn<ActiveExam, String> typeOfAvticeExam;

	@FXML
	private Button showActiveExamButtonInExamsManagement;

	@FXML
	private ComboBox<String> subjectsActiveExamManagement;

	@FXML
	private ComboBox<String> coursesActiveExamManagement;

	@FXML
	private Button LockButton;

	@FXML
	private Button changeTimeButton;

	@FXML
	private ScrollPane copiersStudents;

	/***** confirm grades attributes *****/

	@FXML
	private AnchorPane confirmGradesAnchorPane;

	@FXML
	private TableView<CheckedExam> confirmGradeTableView;

	@FXML
	private TableColumn<CheckedExam, String> subjectColInConfirmGrades;

	@FXML
	private TableColumn<CheckedExam, String> courseColInConfirmGrades;

	@FXML
	private TableColumn<CheckedExam, String> examNumColInConfirmGrades;

	@FXML
	private TableColumn<CheckedExam, String> executionCodeColInConfirmGrades;

	@FXML
	private TableColumn<CheckedExam, String> studentIDColInConfirmGrades;

	@FXML
	private TableColumn<CheckedExam, Integer> gradeColInConfirmGrades;

	@FXML
	private Button approveButtonInConfirmGrade;

	@FXML
	private Button changeGradeButtonInConfirmGrades;

	@FXML
	private Button addCommentsButtonInConfirmGrades;

	/***************************************************/

	@FXML
	private AnchorPane anchorPaneShowExam;

	@FXML
	private ScrollPane scrollPaneShowExam;

	@FXML
	private VBox vBoxShowExam;

	@FXML
	private Button okButtonShowExam;

	private ArrayList<QuestionInComputerizeExam> questionInComputerizeExamArray;

	/***************************************************/

	/***** Exams Statistic Attributes *****/

	@FXML
	private AnchorPane examStatisticAnchorPane;

	@FXML
	private ComboBox<String> subjectComboBoxInExamStatistic;

	@FXML
	private ComboBox<String> courseComboBoxInExamStatistic;

	@FXML
	private ComboBox<String> examNumComboBoxInExamStatistic;

	@FXML
	private Button createReportButton;

	@FXML
	private BarChart<?, ?> examStatisticBarChart;

	@FXML
	private CategoryAxis xAxis;

	@FXML
	private NumberAxis yAxis;

	@FXML
	private AnchorPane examReportAnchorPane;

	@FXML
	private TextField averageTextFieldInTeacherReport;

	@FXML
	private TextField medianTextFieldInTeacherReport;

	@FXML
	private TextField startedTextFieldInTeacherReport;

	@FXML
	private TextField finishedTextFieldInTeacherReport;

	@FXML
	private TextField forcedTextFieldInTeacherReport;

	private ArrayList<Integer> grades;

	/***** General Attributes *****/

	private ScreensController screensController;

	private String firstName;

	private String lastName;

	private Client client;

	private Exam exam;

	private StudentHandle studentHandle;

	private boolean rejectionFlag;

	private boolean acceptionFlag;

	private boolean hadCopied;

	/******************** Getters & Setters ********************/

	/**
	 * get Student Handle
	 * 
	 * @return StudentHandle
	 */
	public StudentHandle getStudentHandle() {
		return studentHandle;
	}

	/**
	 * set Student Handle
	 * 
	 * @param studentHandle
	 */
	public void setStudentHandle(StudentHandle studentHandle) {
		this.studentHandle = studentHandle;
	}

	/**
	 * set Screen Parent
	 * 
	 * @param screenParent
	 */
	public void setScreenParent(ScreensController screenParent) {
		screensController = screenParent;
	}

	/**
	 * get client First Name
	 * 
	 * @return String
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * set client First Name
	 * 
	 * @param firstName
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * get client last Name
	 * 
	 * @return String
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * set client last Name
	 * 
	 * @param lastName
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * checking rejection flag for principal rejection
	 * 
	 * @return boolean rejectionFlag
	 */
	public boolean isRejectionFlag() {
		return rejectionFlag;
	}

	/**
	 * setting rejection flag for principal rejection
	 * 
	 * @param rejectionFlag
	 */
	public void setRejectionFlag(boolean rejectionFlag) {
		this.rejectionFlag = rejectionFlag;
	}

	/**
	 * is Had Copied flag for students copies
	 * 
	 * @return boolean hadCopied
	 */
	public boolean isHadCopied() {
		return hadCopied;
	}

	/**
	 * set Had Copied flag for students copies
	 * 
	 * @param hadCopied
	 */
	public void setHadCopied(boolean hadCopied) {
		this.hadCopied = hadCopied;
	}

	/**
	 * checking acception flag in principal approve
	 * 
	 * @return boolean acceptionFlag
	 */
	public boolean isAcceptionFlag() {
		return acceptionFlag;
	}

	/**
	 * setting acception flag in principal approve
	 * 
	 * @param acceptionFlag
	 */
	public void setAcceptionFlag(boolean acceptionFlag) {
		this.acceptionFlag = acceptionFlag;
	}

	/**
	 * get grades array for histogram
	 * 
	 * @return ArrayList<Integer> grades
	 */
	public ArrayList<Integer> getGrades() {
		return grades;
	}

	/**
	 * set grades array for histogram
	 * 
	 * @param grades
	 */
	public void setGrades(ArrayList<Integer> grades) {
		this.grades = grades;
	}

	/**
	 * get Average Text Field In Teacher Report
	 * 
	 * @return TextField averageTextFieldInTeacherReport
	 */
	public TextField getAverageTextFieldInTeacherReport() {
		return averageTextFieldInTeacherReport;
	}

	/**
	 * set Average Text Field In Teacher Report
	 * 
	 * @param averageTextFieldInTeacherReport
	 */
	public void setAverageTextFieldInTeacherReport(TextField averageTextFieldInTeacherReport) {
		this.averageTextFieldInTeacherReport = averageTextFieldInTeacherReport;
	}

	/**
	 * get Median Text Field In Teacher Report
	 * 
	 * @return TextField medianTextFieldInTeacherReport
	 */
	public TextField getMedianTextFieldInTeacherReport() {
		return medianTextFieldInTeacherReport;
	}

	/**
	 * set Median Text Field In Teacher Report
	 * 
	 * @param medianTextFieldInTeacherReport
	 */
	public void setMedianTextFieldInTeacherReport(TextField medianTextFieldInTeacherReport) {
		this.medianTextFieldInTeacherReport = medianTextFieldInTeacherReport;
	}

	/**
	 * get Started Text Field In Teacher Report
	 * 
	 * @return TextField startedTextFieldInTeacherReport
	 */
	public TextField getStartedTextFieldInTeacherReport() {
		return startedTextFieldInTeacherReport;
	}

	/**
	 * set Started Text Field In Teacher Report
	 * 
	 * @param startedTextFieldInTeacherReport
	 */
	public void setStartedTextFieldInTeacherReport(TextField startedTextFieldInTeacherReport) {
		this.startedTextFieldInTeacherReport = startedTextFieldInTeacherReport;
	}

	/**
	 * get Finished Text Field In Teacher Report
	 * 
	 * @return TextField finishedTextFieldInTeacherReport
	 */
	public TextField getFinishedTextFieldInTeacherReport() {
		return finishedTextFieldInTeacherReport;
	}

	/**
	 * set Finished Text Field In Teacher Report
	 * 
	 * @param finishedTextFieldInTeacherReport
	 */
	public void setFinishedTextFieldInTeacherReport(TextField finishedTextFieldInTeacherReport) {
		this.finishedTextFieldInTeacherReport = finishedTextFieldInTeacherReport;
	}

	/**
	 * get Forced Text Field In Teacher Report
	 * 
	 * @return TextField forcedTextFieldInTeacherReport
	 */
	public TextField getForcedTextFieldInTeacherReport() {
		return forcedTextFieldInTeacherReport;
	}

	/**
	 * set Forced Text Field In Teacher Report
	 * 
	 * @param forcedTextFieldInTeacherReport
	 */
	public void setForcedTextFieldInTeacherReport(TextField forcedTextFieldInTeacherReport) {
		this.forcedTextFieldInTeacherReport = forcedTextFieldInTeacherReport;
	}

	/******************** Initialization ********************/

	/**
	 * initialize this screen
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		backAnchorPane.setVisible(false);
		date.setText(Utilities_Client.setDate());
		this.client = MainAppClient.getClient();
		questionInComputerizeExamArray = new ArrayList<QuestionInComputerizeExam>();
		setColumnsInEditOrRemove();
		setColumnInCreateExamAllQuestions();
		setColumnInCreateExamQuestions();
		setColumnsInExamsManagement();
		setColumnInConfirmGrades();
		setColumnsOfActiveExams();
		setColumnsInExamManagement();
		initAnchorPaneInCreateExamFirstWindow();
		tableViewInCreateExamQuestion.setEditable(true);
		tableViewInCreateExamQuestion.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		tableViewInEditOrRemove.setEditable(true);
		tableViewInEditOrRemove.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		tableViewInCreateExamAllQuestion.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		initAddQuestionOption();
		client.setTeacherWindowController(this);
	}

	/******************** Methods ********************/

	/**
	 * logOut Button Handler
	 * 
	 * @param event
	 * @throws Exception
	 */
	public void logOutButtonHandler(ActionEvent event) throws Exception {
		if (questionsTableAnchorPaneInEditOrRemove.isVisible()) {
			tableViewInEditOrRemove.getItems().clear();
			setQuestionsTableInfoInEditOrRemove();
			questionsTableAnchorPaneInEditOrRemove.setVisible(false);
		}
		if (addQuestionAnchorPane.isVisible()) {
			clearAddQuestionFields();
			addQuestionAnchorPane.setVisible(false);
		}
		if (createExamAnchorPane.isVisible()) {
			tableViewInCreateExamAllQuestion.getItems().clear();
			tableViewInCreateExamQuestion.getItems().clear();
			createExamAnchorPane.setVisible(false);
		}
		if (examManagementAnchorPane.isVisible())
			examManagementAnchorPane.setVisible(false);
		if (activeExamManagementAnchorPane.isVisible())
			activeExamManagementAnchorPane.setVisible(false);
		if (backAnchorPane.isVisible())
			backAnchorPane.setVisible(false);
		if (confirmGradesAnchorPane.isVisible())
			confirmGradesAnchorPane.setVisible(false);
		if (examStatisticAnchorPane.isVisible()) {
			examStatisticAnchorPane.setVisible(false);
		}
		if (!vBoxShowExam.getChildren().isEmpty()) {
			vBoxShowExam.getChildren().clear();
		}
		setRejectionFlag(false);
		setAcceptionFlag(false);
		welcomeText.setText("Welcome");
		welcomeAnchorPane.setVisible(true);
		this.client.handleMessageFromClientUI(Message.logout);
		screensController.setScreen(MainAppClient.loginScreenID);
	}

	/**
	 * set Name And Last Name of client
	 * 
	 * @param firstName
	 * @param lastName
	 */
	public void setNameAndLastName(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
		welcomeText.setText(welcomeText.getText() + " " + this.firstName + " " + this.lastName);
	}

	/**
	 * Edit\Remove Questions was pressed
	 * 
	 * @param event
	 */
	public void openEditorRemove(ActionEvent event) {
		try {
			setSubjectComboBox(subjectComboBoxInEditOrRemove);
			tableViewInEditOrRemove.getItems().clear();
			questionsTableAnchorPaneInEditOrRemove.setVisible(true);
			backAnchorPane.setVisible(true);
			addQuestionAnchorPane.setVisible(false);
			createExamAnchorPane.setVisible(false);
			examManagementAnchorPane.setVisible(false);
			clearAddQuestionFields();
			welcomeAnchorPane.setVisible(false);
			confirmGradesAnchorPane.setVisible(false);
			activeExamManagementAnchorPane.setVisible(false);
			examStatisticAnchorPane.setVisible(false);
			examReportAnchorPane.setVisible(false);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	/**
	 * sends message to the server to update the data base
	 * 
	 * @param event
	 */
	public void saveButtonHandler(ActionEvent event) {
		ObservableList<Question> newQuestions = FXCollections.observableArrayList();
		ArrayList<Question> updateDB = new ArrayList<Question>();
		newQuestions = tableViewInEditOrRemove.getItems();
		for (Question question : newQuestions) {
			String answerNumber = question.getCorrectAnswer();
			if (!answerNumber.equals("1") && !answerNumber.equals("2") && !answerNumber.equals("3")
					&& !answerNumber.equals("4")) {
				Utilities_Client.popUpMethod("Please insert only numbers from 1 to 4 in the correct answer column");
				tableViewInEditOrRemove.getItems().clear();
				setQuestionsTableInfoInEditOrRemove();
				System.out.println("answerNumber 1<-->4");
				return;
			}
			updateDB.add(question);
		}
		client.handleMessageFromClientUI(new QuestionHandle("All", updateDB));
		Utilities_Client.popUpMethod("Question updated successfully");
	}

	/**
	 * Remove button was pressed
	 * 
	 * @param event
	 */
	public void removeButtonHandler(ActionEvent event) {
		Label text;
		Stage primaryStage = new Stage();
		primaryStage.setTitle("AES7");
		primaryStage.getIcons().add(new Image("boundaries/Images/AES2.png"));
		primaryStage.setHeight(100);
		primaryStage.setWidth(250);
		primaryStage.setResizable(false);
		Popup popup = new Popup();
		popup.setX(700);
		popup.setY(400);
		GridPane layout = new GridPane();
		layout.setHgap(3);
		layout.setVgap(3);
		layout.setPadding(new Insets(0, 10, 0, 10));
		text = new Label("Are you sure?");
		popup.getContent().addAll(text);
		Button yesButton = new Button("Yes");
		yesButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				primaryStage.hide();
				ObservableList<Question> selectedQuestions = tableViewInEditOrRemove.getSelectionModel()
						.getSelectedItems();
				ArrayList<Question> questions = new ArrayList<Question>();
				questions.addAll(selectedQuestions);
				client.handleMessageFromClientUI(new QuestionHandle("Delete", questions));
				tableViewInEditOrRemove.getItems().clear();
				setQuestionsTableInfoInEditOrRemove();
			}
		});
		Button noButton = new Button("No");
		noButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				primaryStage.hide();
			}
		});
		layout.add(text, 8, 0);
		layout.add(yesButton, 9, 1);
		layout.add(noButton, 10, 1);
		layout.setStyle("-fx-background-color: cornsilk; -fx-padding: 10;");
		primaryStage.setScene(new Scene(layout));
		primaryStage.show();
	}

	/**
	 * red Arrow Up in create exam by connecting question to exam
	 * 
	 * @param event
	 */
	@FXML
	void redArrowUp(MouseEvent event) {
		ObservableList<QuestionInExam> regretQuestions = tableViewInCreateExamQuestion.getSelectionModel()
				.getSelectedItems();
		if (!regretQuestions.isEmpty()) {
			ArrayList<QuestionInExam> questionsToDelete = new ArrayList<>();
			questionsToDelete.addAll(regretQuestions);
			for (QuestionInExam question : regretQuestions)
				if (question.getPoints() != 0)
					question.setPoints(0);
			tableViewInCreateExamQuestion.getItems().removeAll(questionsToDelete);
			for (QuestionInExam question : questionsToDelete)
				tableViewInCreateExamAllQuestion.getItems().add(question.getQuestion());
			initTablesInCreateExam(true, true);
		} else // if the teacher didn't choose anything
			Utilities_Client.popUpMethod("Please Select Questions");
	}

	/**
	 * green Arrow Up in create exam by connecting question to exam
	 * 
	 * @param event
	 */
	@FXML
	void greenArrowDown(MouseEvent event) {
		ObservableList<Question> questionsFromAllTable = tableViewInCreateExamAllQuestion.getSelectionModel()
				.getSelectedItems();
		if (!questionsFromAllTable.isEmpty()) {
			ArrayList<Question> questionsToAdd = new ArrayList<>();
			questionsToAdd.addAll(questionsFromAllTable);
			tableViewInCreateExamAllQuestion.getItems().removeAll(questionsToAdd);
			for (Question question : questionsToAdd)
				tableViewInCreateExamQuestion.getItems().add(new QuestionInExam(exam, question));
			initTablesInCreateExam(true, true);
		} else // if the teacher didn't choose anything
			Utilities_Client.popUpMethod("Please Select Questions");
	}

	/**
	 * check Total Points In Create Exam
	 * 
	 * @param event
	 */
	public void checkTotalPointsInCreateExam(MouseEvent event) {
		int totalPoints = 0;
		for (QuestionInExam question : tableViewInCreateExamQuestion.getItems()) {
			int points = question.getPoints();
			if ((points < 1) || (points > 100)) {
				Utilities_Client.popUpMethod(
						"Wrong amount of points in question number" + " " + question.getQuestion().getQuestionNum());
				return;
			}
			totalPoints += points;
		}
		if (totalPoints != 100) {
			Utilities_Client.popUpMethod("The total amount of points isn't 100");
		} else {
			exam.setQuestions(tableViewInCreateExamQuestion.getItems());
			System.out.println(exam.getQuestions());
			anchorPaneInCreateExamSecondWindow.setVisible(false);
			anchorPaneInCreateExamThirdWindow.setVisible(true);
			clearFieldsInAddExamWindows("second");
		}
	}

	/**
	 * create Final Exam
	 * 
	 * @param event
	 */
	public void createFinalExam(MouseEvent event) {
		if (!textAreaStudentsInCreateExam.getText().isEmpty()) {
			exam.setFreeTextForExaminees(textAreaStudentsInCreateExam.getText());
		}
		if (!textAreaTeachersInCreateExam.getText().isEmpty()) {
			exam.setFreeTextForTeacherOnly(textAreaTeachersInCreateExam.getText());
		}
		client.handleMessageFromClientUI(new ExamHandle(Message.exam, exam));
		Utilities_Client.popUpMethod("Exam created successfully");
		anchorPaneInCreateExamThirdWindow.setVisible(false);
		clearFieldsInAddExamWindows("third");
		anchorPaneInCreateExamFirstWindow.setVisible(true);
	}

	/**
	 * initialize Tables In Create Exam
	 * 
	 * @param allquestions
	 * @param selected
	 */
	private void initTablesInCreateExam(boolean allquestions, boolean selected) {
		if (allquestions) {
			tableViewInCreateExamAllQuestion.refresh();
		}
		if (selected) {
			tableViewInCreateExamQuestion.refresh();
		}
	}

	/**
	 * 'Add Question' was pressed
	 * 
	 * @param event
	 */
	public void openAddQuestion(ActionEvent event) {
		try {
			setSubjectComboBox(subjectComboBoxInAddQuestion);
			addQuestionAnchorPane.setVisible(true);
			backAnchorPane.setVisible(true);
			questionsTableAnchorPaneInEditOrRemove.setVisible(false);
			welcomeAnchorPane.setVisible(false);
			createExamAnchorPane.setVisible(false);
			examManagementAnchorPane.setVisible(false);
			confirmGradesAnchorPane.setVisible(false);
			activeExamManagementAnchorPane.setVisible(false);
			examStatisticAnchorPane.setVisible(false);
			examReportAnchorPane.setVisible(false);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	/**
	 * reload Tables In Create Exam
	 * 
	 * @param event
	 */
	public void realodTablesInCreateExam(MouseEvent event) {
		initTablesInCreateExam(true, true);
	}

	/**
	 * 'Create question' button was pressed - add Question to DB
	 * 
	 * @param event
	 */
	public void addNewQuestion(ActionEvent event) {
		// if the user didn't select a subject
		if (subjectComboBoxInAddQuestion.getValue() == null) {
			Utilities_Client.popUpMethod("Please Select Subject");
			return;
		}
		// if the user didn't filled all the fields
		if (questionTextField.getText().isEmpty() || firstAnswerField.getText().isEmpty()
				|| secondAnswerField.getText().isEmpty() || thirdAnswerField.getText().isEmpty()
				|| fourthAnswerField.getText().isEmpty()) {
			Utilities_Client.popUpMethod("Please Enter Text");
			return;
		}
		// if the user didn't select the correct answer
		if (correctAnswerComboBox.getValue() == null) {
			Utilities_Client.popUpMethod("Please Select Answer");
			return;
		}
		Question question = new Question(subjectComboBoxInAddQuestion.getValue(), firstName + " " + lastName,
				questionTextField.getText(), firstAnswerField.getText(), secondAnswerField.getText(),
				thirdAnswerField.getText(), fourthAnswerField.getText(), correctAnswerComboBox.getValue());
		client.setQuestion(question);
		client.handleMessageFromClientUI(new QuestionHandle("Add", question));
		Utilities_Client.popUpMethod("Question Added successfully");
		clearAddQuestionFields();
	}

	/**
	 * open Create Exam anchor pane
	 * 
	 * @param event
	 */
	public void openCreateExam(ActionEvent event) {
		try {
			tableViewInCreateExamAllQuestion.getItems().clear();
			createExamAnchorPane.setVisible(true);
			anchorPaneInCreateExamFirstWindow.setVisible(true);
			anchorPaneInCreateExamSecondWindow.setVisible(false);
			anchorPaneInCreateExamThirdWindow.setVisible(false);
			backAnchorPane.setVisible(true);
			addQuestionAnchorPane.setVisible(false);
			questionsTableAnchorPaneInEditOrRemove.setVisible(false);
			welcomeAnchorPane.setVisible(false);
			examManagementAnchorPane.setVisible(false);
			confirmGradesAnchorPane.setVisible(false);
			activeExamManagementAnchorPane.setVisible(false);
			examStatisticAnchorPane.setVisible(false);
			examReportAnchorPane.setVisible(false);
			clearAddQuestionFields();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create Exam initialize
	 * 
	 * @param event
	 */
	public void createExamInit(ActionEvent event) {
		String subject = subjectInCreateExamComboBox.getValue();
		if (subject == null) {
			Utilities_Client.popUpMethod("You must select the subject first");
			return;
		}
		String course = courseInCreateExamComboBox.getValue();
		if (course == null) {
			Utilities_Client.popUpMethod("Please select for which course you creating the exam");
			return;
		}
		try {
			int duration = Integer.parseInt(durationInCreateExamField.getText());
			if (duration <= 0) {
				Utilities_Client.popUpMethod("You must enter valid duration before you proceed");
				return;
			}
			exam = new Exam(subject, course, duration, firstName + " " + lastName);
			anchorPaneInCreateExamFirstWindow.setVisible(false);
			client.getQuestionsFromDB().clear();
			anchorPaneInCreateExamSecondWindow.setVisible(true);
			setTableInCreateExamAllQuestions();
			clearFieldsInAddExamWindows("first");
		} catch (NumberFormatException e) {
			Utilities_Client.popUpMethod("You must enter valid duration before you proceed");
		}
	}

	/**
	 * Prevents from the user to select course if he\she didn't select a subject
	 * 
	 * @param event
	 */
	public void selectCourseComboBoxHandler(MouseEvent event) {
		String selectedSubject = null;
		if (createExamAnchorPane.isVisible())
			selectedSubject = subjectInCreateExamComboBox.getValue();
		else if (examStatisticAnchorPane.isVisible())
			selectedSubject = subjectComboBoxInExamStatistic.getValue();
		else if (examManagementAnchorPane.isVisible())
			selectedSubject = subjectExamManagement.getValue();
		else if (activeExamManagementAnchorPane.isVisible())
			selectedSubject = subjectsActiveExamManagement.getValue();
		if (selectedSubject == null)
			Utilities_Client.popUpMethod("You must select the subject first");
		else if (createExamAnchorPane.isVisible())
			selectCourseComboBox(courseInCreateExamComboBox, selectedSubject);
		else if (examStatisticAnchorPane.isVisible())
			selectCourseComboBox(courseComboBoxInExamStatistic, selectedSubject);
		else if (examManagementAnchorPane.isVisible())
			selectCourseComboBox(courseExamManagement, selectedSubject);
		else if (activeExamManagementAnchorPane.isVisible())
			selectCourseComboBox(coursesActiveExamManagement, selectedSubject);
	}

	/**
	 * order Student Exam was pressed
	 * 
	 * @param mouseEvent
	 */
	public void orderStudentExam(MouseEvent mouseEvent) {
		if (confirmGradeTableView.getSelectionModel().getSelectedItem() != null) {
			CheckedExam selectedStudnet = confirmGradeTableView.getSelectionModel().getSelectedItem();
			client.handleMessageFromClientUI(Message.getAnswers + " "
					+ selectedStudnet.getSubmittedExam().getStudentInActiveExam().getStudent().getId() + " "
					+ selectedStudnet.getSubmittedExam().getStudentInActiveExam().getActiveExam().getExam()
							.getSubjectID()
					+ " "
					+ selectedStudnet.getSubmittedExam().getStudentInActiveExam().getActiveExam().getExam()
							.getCourseID()
					+ " " + selectedStudnet.getExamNum() + " " + selectedStudnet.getExecutionCode() + " " + "false");
			client.handleMessageFromClientUI(Message.getQuestionInExam + " " + selectedStudnet.getExecutionCode());
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			showExam();
		} else {
			Utilities_Client.popUpMethod("Please select student");
		}
	}

	/**
	 * show Exams was pressed
	 * 
	 * @param event
	 */
	public void showExamsHandler(ActionEvent event) {
		if (examManagementAnchorPane.isVisible() && courseExamManagement.getValue() != null)
			setTableInExamsManagement();
		else if (activeExamManagementAnchorPane.isVisible() && coursesActiveExamManagement.getValue() != null)
			setTableInActiveExamManagement();
		else
			Utilities_Client.popUpMethod("Please Select the Course");
	}

	/**
	 * return To Exam Table
	 * 
	 * @param mouseEvent
	 */
	public void returnToExamTable(MouseEvent mouseEvent) {
		vBoxShowExam.getChildren().clear();
		vBoxShowExam.setVisible(false);
		anchorPaneShowExam.setVisible(false);
	}

	/**
	 * update Table In Edit Or Remove button was pressed
	 * 
	 * @param event
	 */
	public void updateTableInEditOrRemove(ActionEvent event) {
		setQuestionsTableInfoInEditOrRemove();
	}

	/**
	 * exam management tab was pressed
	 * 
	 * @param event
	 */
	public void openExamManagement(ActionEvent event) {
		try {
			tableViewInExamsManagement.getItems().clear();
			examManagementAnchorPane.setVisible(true);
			backAnchorPane.setVisible(true);
			createExamAnchorPane.setVisible(false);
			addQuestionAnchorPane.setVisible(false);
			questionsTableAnchorPaneInEditOrRemove.setVisible(false);
			welcomeAnchorPane.setVisible(false);
			confirmGradesAnchorPane.setVisible(false);
			clearAddQuestionFields();
			setSubjectComboBox(subjectExamManagement);
			setCourseComboBox(courseExamManagement);
			activeExamManagementAnchorPane.setVisible(false);
			examStatisticAnchorPane.setVisible(false);
			examReportAnchorPane.setVisible(false);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	/**
	 * activate Button was pressed
	 * 
	 * @param event
	 */
	public void activateButtonHandler(ActionEvent event) {
		Exam selectedExam = tableViewInExamsManagement.getSelectionModel().getSelectedItem();
		if (selectedExam == null)
			Utilities_Client.popUpMethod("Please Select Exam");
		else {
			Label text = new Label("Please enter an execution code:");
			Stage primaryStage = new Stage();
			primaryStage.setTitle("AES7");
			primaryStage.getIcons().add(new Image("boundaries/Images/AES2.png"));
			Popup popup = new Popup();
			popup.setX(700);
			popup.setY(400);
			HBox layout = new HBox(10);
			popup.getContent().addAll(text);
			executionCode = new TextField();
			executionCode.setPrefWidth(55);
			executionCode.setEditable(true);
			ComboBox<String> type = new ComboBox<String>();
			type.getSelectionModel().clearSelection();
			type.setPromptText("Select Type");
			type.getItems().addAll("Computerized", "Manual");
			Button okButton = new Button("OK");
			okButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					/* check execution code */
					String typedExecutionCode = executionCode.getText();
					if ((typedExecutionCode.length() != 4) || !typedExecutionCode.matches("[a-zA-Z0-9]*"))
						Utilities_Client.popUpMethod("Illegal execution code. please try again!");
					else if (!typedExecutionCode.matches(".*\\d+.*"))
						Utilities_Client.popUpMethod("The execution code must contain at least 1 numeric character");
					else if (!typedExecutionCode.matches(".*[a-zA-Z]+.*"))
						Utilities_Client.popUpMethod("The execution code must contain at least 1 alphabetic character");
					else if (type.getValue() == null)
						Utilities_Client.popUpMethod("Type was not selected. please try again!");
					else if (!executionCodeExist(typedExecutionCode)) {
						ActiveExam activeExam = new ActiveExam(selectedExam, typedExecutionCode, type.getValue(),
								firstName + " " + lastName, client.getId());
						client.handleMessageFromClientUI(new ActiveExamHandle("Activate", activeExam));
						Utilities_Client.popUpMethod("Exam activated successfully!");
					} else
						Utilities_Client.popUpMethod("Exam with this execution Code already exist!");
					primaryStage.hide();
				}
			});
			Button cancelButton = new Button("Cancel");
			cancelButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					primaryStage.hide();
				}
			});
			layout.setStyle("-fx-background-color: cornsilk; -fx-padding: 10;");
			layout.getChildren().addAll(text, executionCode, type, okButton, cancelButton);
			primaryStage.setScene(new Scene(layout));
			primaryStage.show();
		}
	}

	/**
	 * show Questions Button pressed
	 * 
	 * @param event
	 */
	public void showQuestionsButtonHandler(MouseEvent event) {
		Exam selectedExam = tableViewInExamsManagement.getSelectionModel().getSelectedItem();
		if (selectedExam == null)
			Utilities_Client.popUpMethod("Please Select Exam");
		else {
			client.handleMessageFromClientUI(Message.getQuestionsFromSpecificExam + " " + selectedExam.getSubjectID()
					+ " " + selectedExam.getCourseID() + " " + selectedExam.getExamNum());
			Stage primaryStage = new Stage();
			primaryStage.setTitle("AES7");
			primaryStage.getIcons().add(new Image("boundaries/Images/AES2.png"));
			Popup popup = new Popup();
			popup.setX(700);
			popup.setY(400);
			HBox layout = new HBox(10);
			primaryStage.setResizable(false);
			questionsOfSpecifcExamTable.setItems(client.getQuestionsFromDB());
			layout.setStyle("-fx-background-color: cornsilk; -fx-padding: 10;");
			layout.getChildren().addAll(questionsOfSpecifcExamTable);
			questionsOfSpecifcExamTable.setVisible(true);
			primaryStage.setScene(new Scene(layout));
			primaryStage.show();
		}
	}

	/**
	 * When teacher press 'Delete' in the exam management window
	 * 
	 * @param event
	 */
	public void deleteExamButtonHandler(MouseEvent event) {
		Exam selectedExam = tableViewInExamsManagement.getSelectionModel().getSelectedItem();
		if (selectedExam == null)
			Utilities_Client.popUpMethod("Please Select Exam");
		else if (!selectedExam.getTeacherName().equals(firstName + " " + lastName))
			Utilities_Client.popUpMethod("You Can Delete Only Your Own Exams");
		else {
			Label text = new Label("Are You Sure?");
			Stage primaryStage = new Stage();
			primaryStage.setTitle("AES7");
			primaryStage.getIcons().add(new Image("boundaries/Images/AES2.png"));
			primaryStage.setHeight(100);
			primaryStage.setWidth(250);
			primaryStage.setResizable(false);
			Popup popup = new Popup();
			popup.setX(700);
			popup.setY(400);
			GridPane layout = new GridPane();
			layout.setHgap(3);
			layout.setVgap(3);
			layout.setPadding(new Insets(0, 10, 0, 10));
			popup.getContent().addAll(text);
			Button yesButton = new Button("Yes");
			yesButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					client.handleMessageFromClientUI(new ExamHandle(Message.deleteExam, selectedExam));
					setTableInExamsManagement();
					Utilities_Client.popUpMethod("Exam deleted successfully!");
					primaryStage.hide();

				}
			});
			Button noButton = new Button("No");
			noButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					primaryStage.hide();
				}
			});
			layout.add(text, 8, 0);
			layout.add(yesButton, 9, 1);
			layout.add(noButton, 10, 1);
			layout.setStyle("-fx-background-color: cornsilk; -fx-padding: 10;");
			primaryStage.setScene(new Scene(layout));
			primaryStage.show();
		}
	}

	/**
	 * change Time Button was pressed
	 * 
	 * @param event
	 */
	public void changeTimeButtonHandler(ActionEvent event) {
		ActiveExam selectedExam = activeExamsTableView.getSelectionModel().getSelectedItem();
		if (selectedExam == null)
			Utilities_Client.popUpMethod("Please Select Exam");
		else {
			Label text = new Label("Please enter New Time and Reasons:");
			Stage primaryStage = new Stage();
			primaryStage.setTitle("AES7");
			primaryStage.getIcons().add(new Image("boundaries/Images/AES2.png"));
			Popup popup = new Popup();
			popup.setX(700);
			popup.setY(400);
			HBox layout = new HBox(10);
			TextField newDuration = new TextField();
			newDuration.setPromptText("Enter new time here");
			TextField reason = new TextField();
			reason.setPromptText("Enter reason here");
			popup.getContent().addAll(text, reason);
			newDuration.setEditable(true);
			reason.setEditable(true);
			Button okButton = new Button("Send");
			okButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					String newTypedDuration = newDuration.getText();
					if (newTypedDuration.isEmpty() || !newTypedDuration.matches("[0-9]*")
							|| newTypedDuration.charAt(0) == '0')
						Utilities_Client.popUpMethod("Please enter new time in minutes without leading zeros");
					else if (reason.getText().isEmpty())
						Utilities_Client.popUpMethod("Please enter your reason");
					else {
						WaitingActiveExam waitingActiveExam = new WaitingActiveExam(selectedExam,
								Integer.parseInt(newTypedDuration), reason.getText());
						client.handleMessageFromClientUI(new WaitingActiveExamHandle("ChangeTime", waitingActiveExam));
						Utilities_Client.popUpMethod("Request sent to Principal!");
						primaryStage.hide();
					}
				}
			});
			Button cancelButton = new Button("Cancel");
			cancelButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					primaryStage.hide();
				}
			});
			layout.setStyle("-fx-background-color: cornsilk; -fx-padding: 10;");
			layout.getChildren().addAll(text, newDuration, reason, okButton, cancelButton);
			primaryStage.setScene(new Scene(layout));
			primaryStage.show();
		}

	}

	/**
	 * lock Button was pressed
	 * 
	 * @param event
	 */
	public void lockButtonHandler(ActionEvent event) {
		ActiveExam selectedActiveExam = activeExamsTableView.getSelectionModel().getSelectedItem();
		if (selectedActiveExam == null)
			Utilities_Client.popUpMethod("Please Select Exam");
		else {
			Label text = new Label("Are You Sure?");
			Stage primaryStage = new Stage();
			primaryStage.setTitle("AES7");
			primaryStage.getIcons().add(new Image("boundaries/Images/AES2.png"));
			primaryStage.setHeight(100);
			primaryStage.setWidth(250);
			primaryStage.setResizable(false);
			Popup popup = new Popup();
			popup.setX(700);
			popup.setY(400);
			GridPane layout = new GridPane();
			layout.setHgap(3);
			layout.setVgap(3);
			layout.setPadding(new Insets(0, 10, 0, 10));
			popup.getContent().addAll(text);
			Button yesButton = new Button("Yes");
			yesButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					client.handleMessageFromClientUI(new ActiveExamHandle("Lock", selectedActiveExam));
					setTableInActiveExamManagement();
					Utilities_Client.popUpMethod("Exam locked successfully!");
					primaryStage.hide();

				}
			});
			Button noButton = new Button("No");
			noButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					primaryStage.hide();
				}
			});
			layout.add(text, 8, 0);
			layout.add(yesButton, 9, 1);
			layout.add(noButton, 10, 1);
			layout.setStyle("-fx-background-color: cornsilk; -fx-padding: 10;");
			primaryStage.setScene(new Scene(layout));
			primaryStage.show();
		}
	}

	/**
	 * clear Button was pressed
	 * 
	 * @param event
	 */
	public void clearButtonPressed(ActionEvent event) {
		clearAddQuestionFields();
	}

	/**
	 * Confirm Grades tab was pressed
	 * 
	 * @param event
	 */
	public void openConfirmGrades(ActionEvent event) {
		confirmGradesAnchorPane.setVisible(true);
		welcomeAnchorPane.setVisible(false);
		backAnchorPane.setVisible(false);
		addQuestionAnchorPane.setVisible(false);
		questionsTableAnchorPaneInEditOrRemove.setVisible(false);
		createExamAnchorPane.setVisible(false);
		examManagementAnchorPane.setVisible(false);
		activeExamManagementAnchorPane.setVisible(false);
		examStatisticAnchorPane.setVisible(false);
		examReportAnchorPane.setVisible(false);
		setTableInConfirmGrades();
	}

	/**
	 * approve Button was pressed
	 * 
	 * @param event
	 */
	public void approveButtonHandler(ActionEvent event) {
		CheckedExam selectedExam = confirmGradeTableView.getSelectionModel().getSelectedItem();
		if (selectedExam == null)
			Utilities_Client.popUpMethod("Please Select Exam");
		else {
			Label text = new Label("Are you sure?");
			Stage primaryStage = new Stage();
			primaryStage.setTitle("AES7");
			primaryStage.getIcons().add(new Image("boundaries/Images/AES2.png"));
			Popup popup = new Popup();
			popup.setX(700);
			popup.setY(400);
			HBox layout = new HBox(10);
			popup.getContent().addAll(text);
			Button yesButton = new Button("Yes");
			yesButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					selectedExam.setIdApprover(client.getId());
					client.handleMessageFromClientUI(new CheckedExamHandle("Approve", selectedExam));
					client.handleMessageFromClientUI(new CheckedExamHandle("Remove", selectedExam));
					setTableInConfirmGrades();
					Utilities_Client.popUpMethod("Exam sent to Student!");
					primaryStage.hide();
				}
			});
			Button noButton = new Button("No");
			noButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					primaryStage.hide();
				}
			});
			layout.setStyle("-fx-background-color: cornsilk; -fx-padding: 10;");
			layout.getChildren().addAll(text, yesButton, noButton);
			primaryStage.setScene(new Scene(layout));
			primaryStage.show();
		}
	}

	/**
	 * change Grade Button was pressed
	 * 
	 * @param event
	 */
	public void changeGradeButtonHandler(ActionEvent event) {
		CheckedExam selectedExam = confirmGradeTableView.getSelectionModel().getSelectedItem();
		if (selectedExam == null)
			Utilities_Client.popUpMethod("Please Select Exam");
		else {
			Label text = new Label("Please enter new grade:");
			Stage primaryStage = new Stage();
			primaryStage.setTitle("AES7");
			primaryStage.getIcons().add(new Image("boundaries/Images/AES2.png"));
			Popup popup = new Popup();
			popup.setX(700);
			popup.setY(400);
			HBox layout = new HBox(10);
			popup.getContent().addAll(text);
			TextField newGrade = new TextField();
			TextField reasons = new TextField();
			newGrade.setPrefWidth(50);
			newGrade.setEditable(true);
			reasons.setPromptText("Enter reason here");
			reasons.setEditable(true);
			Button saveButton = new Button("Save");
			saveButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					String newTypedGrade = newGrade.getText();
					if (reasons.getText().isEmpty() || newTypedGrade.isEmpty()) {
						Utilities_Client.popUpMethod("Some fields are missing. Try again!");
					} else if (!newTypedGrade.matches("[0-9]*") || newTypedGrade.charAt(0) == '0')
						Utilities_Client.popUpMethod("Please enter new grade without leading zeros");
					else if (Integer.parseInt(newTypedGrade) > 100)
						Utilities_Client.popUpMethod("You can't give more than 100 points");
					else {
						selectedExam.setGrade(Integer.parseInt(newTypedGrade));
						selectedExam.setCommentsOfChangeGrade(reasons.getText());
						client.handleMessageFromClientUI(new CheckedExamHandle("ChangeGrade", selectedExam));
						setTableInConfirmGrades();
						Utilities_Client.popUpMethod("Grade changed successfully!");
					}
					primaryStage.hide();
				}
			});
			Button cancelButton = new Button("Cancel");
			cancelButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					primaryStage.hide();
				}
			});
			layout.setStyle("-fx-background-color: cornsilk; -fx-padding: 10;");
			layout.getChildren().addAll(text, newGrade, reasons, saveButton, cancelButton);
			primaryStage.setScene(new Scene(layout));
			primaryStage.show();
		}
	}

	/**
	 * add comments Button was pressed
	 * 
	 * @param event
	 */
	public void addcommentsButtonHandler(ActionEvent event) {
		CheckedExam selectedExam = confirmGradeTableView.getSelectionModel().getSelectedItem();
		if (selectedExam == null)
			Utilities_Client.popUpMethod("Please Select Exam");
		else {
			Stage primaryStage = new Stage();
			primaryStage.setTitle("AES7");
			primaryStage.getIcons().add(new Image("boundaries/Images/AES2.png"));
			Popup popup = new Popup();
			popup.setX(700);
			popup.setY(400);
			HBox layout = new HBox(10);
			TextField comments = new TextField();
			comments.setPromptText("Enter comment here");
			comments.setEditable(true);
			Button saveButton = new Button("Save");
			saveButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					if (comments.getText().equals("")) {
						Utilities_Client.popUpMethod("Some fields are missing. Try again!");
						primaryStage.hide();
					} else {
						selectedExam.setGeneralComments(comments.getText());
						client.handleMessageFromClientUI(new CheckedExamHandle("AddComments", selectedExam));
						Utilities_Client.popUpMethod("Comment added successfully!");
						primaryStage.hide();
					}
				}
			});
			Button cancelButton = new Button("Cancel");
			cancelButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					primaryStage.hide();
				}
			});
			layout.setStyle("-fx-background-color: cornsilk; -fx-padding: 10;");
			layout.getChildren().addAll(comments, saveButton, cancelButton);
			primaryStage.setScene(new Scene(layout));
			primaryStage.show();
		}
	}

	/**
	 * Exam Statistic tab was pressed
	 * 
	 * @param event
	 */
	public void openExamStatistic(ActionEvent event) {
		examStatisticAnchorPane.setVisible(true);
		confirmGradesAnchorPane.setVisible(false);
		welcomeAnchorPane.setVisible(false);
		backAnchorPane.setVisible(false);
		addQuestionAnchorPane.setVisible(false);
		questionsTableAnchorPaneInEditOrRemove.setVisible(false);
		createExamAnchorPane.setVisible(false);
		examManagementAnchorPane.setVisible(false);
		activeExamManagementAnchorPane.setVisible(false);
		examReportAnchorPane.setVisible(false);
		setSubjectComboBox(subjectComboBoxInExamStatistic);
		setCourseComboBox(courseComboBoxInExamStatistic);
		setExamNumberComboBox(examNumComboBoxInExamStatistic);
	}

	/**
	 * create Report button was pressed
	 * 
	 * @param event
	 */
	public void createReportHandler(ActionEvent event) {
		if (examNumComboBoxInExamStatistic.getValue() == null)
			Utilities_Client.popUpMethod("Please select exam number");
		else {
			examReportAnchorPane.setVisible(true);
			createExamHistogram(subjectComboBoxInExamStatistic.getValue(), courseComboBoxInExamStatistic.getValue(),
					examNumComboBoxInExamStatistic.getValue());
			averageTextFieldInTeacherReport.setEditable(false);
			medianTextFieldInTeacherReport.setEditable(false);
			startedTextFieldInTeacherReport.setEditable(false);
			finishedTextFieldInTeacherReport.setEditable(false);
			forcedTextFieldInTeacherReport.setEditable(false);
		}
	}

	/**
	 * create exam button was pressed
	 * 
	 * @param subject
	 * @param course
	 * @param examNum
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void createExamHistogram(String subject, String course, String examNum) {
		examStatisticBarChart.getData().clear();
		examStatisticBarChart.setCategoryGap(2);
		examStatisticBarChart.setBarGap(0);
		examStatisticBarChart.setAnimated(false);
		int group[] = new int[10];
		client.handleMessageFromClientUI(new ExamReportHandle(subject, course, examNum, "ExamStatistic"));
		xAxis.setLabel(" ");
		yAxis.setLabel("Student Amount");

		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < grades.size(); i++) {
			int grade = grades.get(i);
			if (grade <= 10) {
				group[0]++;
			} else if (grade <= 20) {
				group[1]++;
			} else if (grade <= 30) {
				group[2]++;
			} else if (grade <= 40) {
				group[3]++;
			} else if (grade <= 50) {
				group[4]++;
			} else if (grade <= 60) {
				group[5]++;
			} else if (grade <= 70) {
				group[6]++;
			} else if (grade <= 80) {
				group[7]++;
			} else if (grade <= 90) {
				group[8]++;
			} else if (grade <= 100) {
				group[9]++;
			}
		}
		XYChart.Series series1 = new XYChart.Series();
		series1.setName("AES7-Histogram");
		series1.getData().add(new XYChart.Data("0-10", group[0]));
		series1.getData().add(new XYChart.Data("11-20", group[1]));
		series1.getData().add(new XYChart.Data("21-30", group[2]));
		series1.getData().add(new XYChart.Data("31-40", group[3]));
		series1.getData().add(new XYChart.Data("41-50", group[4]));
		series1.getData().add(new XYChart.Data("51-60", group[5]));
		series1.getData().add(new XYChart.Data("61-70", group[6]));
		series1.getData().add(new XYChart.Data("71-80", group[7]));
		series1.getData().add(new XYChart.Data("81-90", group[8]));
		series1.getData().add(new XYChart.Data("91-100", group[9]));
		examStatisticBarChart.getData().addAll(series1);

	}

	/**
	 * With this method you can jump between the fields in add question screen
	 * 
	 * @param event
	 */
	public void keyHandlerInAddQuestionScreen(KeyEvent event) {
		KeyCode code = event.getCode();
		if (code == KeyCode.ENTER) {
			if (questionTextField.isFocused())
				firstAnswerField.requestFocus();
			else if (firstAnswerField.isFocused())
				secondAnswerField.requestFocus();
			else if (secondAnswerField.isFocused())
				thirdAnswerField.requestFocus();
			else if (thirdAnswerField.isFocused())
				fourthAnswerField.requestFocus();
			else if (fourthAnswerField.isFocused())
				questionTextField.requestFocus();
		}
	}

	/**
	 * When the whit back arrow is pressed
	 * 
	 * @param event
	 */
	public void backToMainScreen(MouseEvent event) {
		welcomeAnchorPane.setVisible(true);
		backAnchorPane.setVisible(false);
		addQuestionAnchorPane.setVisible(false);
		questionsTableAnchorPaneInEditOrRemove.setVisible(false);
		createExamAnchorPane.setVisible(false);
		examManagementAnchorPane.setVisible(false);
		confirmGradesAnchorPane.setVisible(false);
		activeExamManagementAnchorPane.setVisible(false);
	}

	/**
	 * checks if the execution code already exists in the DB
	 * 
	 * @param code
	 * @return True if execution code exists already in other exam
	 */
	public boolean executionCodeExist(String code) {
		client.handleMessageFromClientUI(new ExecutionCodeHandle("Check", code));
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return client.getExecutionCodeExistFlag();
	}

	/**
	 * When Active Exam management is pressed
	 * 
	 * @param event
	 */
	public void openActiveExamManagement(ActionEvent event) {
		welcomeAnchorPane.setVisible(false);
		addQuestionAnchorPane.setVisible(false);
		clearAddQuestionFields();
		questionsTableAnchorPaneInEditOrRemove.setVisible(false);
		createExamAnchorPane.setVisible(false);
		examManagementAnchorPane.setVisible(false);
		confirmGradesAnchorPane.setVisible(false);
		setSubjectComboBox(subjectsActiveExamManagement);
		setCourseComboBox(coursesActiveExamManagement);
		activeExamsTableView.getItems().clear();
		backAnchorPane.setVisible(true);
		activeExamManagementAnchorPane.setVisible(true);
		examStatisticAnchorPane.setVisible(false);
	}

	/**
	 * Showing the pop up for the teacher if his\hers request is denied\accepted
	 * 
	 * @param event
	 */
	public void checkRequest(MouseEvent event) {
		if (rejectionFlag) {
			Utilities_Client.popUpMethod("The principal rejected your request");
			setRejectionFlag(false);
		}
		if (acceptionFlag) {
			setTableInActiveExamManagement();
			Utilities_Client.popUpMethod("The principal approved your request");
			setAcceptionFlag(false);
		}
		if (hadCopied) {
			handleCopiers();
			setHadCopied(false);
		}
	}

	/**
	 * Shows pop-up with students that copied.
	 * 
	 * @param studentHandle
	 */
	public void handleCopiers() {
		HashMap<Student, ArrayList<Student>> copeied = studentHandle.getCopeied();
		String str = "";
		for (Student studend : copeied.keySet()) {
			str += studend.getFirstName() + " " + studend.getLastName() + " copied with:\n";
			for (Student copier : copeied.get(studend)) {
				str += copier.getFirstName() + " " + copier.getLastName() + "\n";
			}
			str += "####################\n";
		}
		VBox vBox = new VBox();
		Label text = null;
		Stage primaryStage = new Stage();
		primaryStage.setTitle("AES7");
		primaryStage.setResizable(false);
		primaryStage.setHeight(600);
		primaryStage.setWidth(400);
		primaryStage.initStyle(StageStyle.UNDECORATED);
		vBox.setPrefHeight(300);
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setLayoutX(25);
		scrollPane.setContent(vBox);
		Popup popup = new Popup();
		popup.setX(700);
		popup.setY(400);
		popup.getContent().add(scrollPane);
		BorderPane border = new BorderPane();
		Button okButton = new Button("OK");
		primaryStage.getIcons().add(new Image("boundaries/Images/AES2.png"));
		okButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				primaryStage.hide();
			}
		});
		border.setStyle(
				"-fx-background-color: cornsilk; -fx-padding: 10; -fx-border-color: black;-fx-border-width: 1;");
		try {
			text = new Label(str);
		} catch (NullPointerException e) {
			text = new Label("No Message Sent");
		}
		border.setCenter(scrollPane);
		vBox.getChildren().add(text);
		popup.getContent().addAll(vBox);
		border.setBottom(okButton);
		primaryStage.setScene(new Scene(border));
		primaryStage.show();
	}

	/**
	 * The handler when the teacher want to see his written exams
	 * 
	 * @param event
	 */
	public void selectExamNumberHandler(MouseEvent event) {
		String selectedCourse = null;
		if (examStatisticAnchorPane.isVisible())
			selectedCourse = courseComboBoxInExamStatistic.getValue();
		if (selectedCourse == null)
			Utilities_Client.popUpMethod("You must select the course first");
		else if (examStatisticAnchorPane.isVisible())
			selectExamNumber(examNumComboBoxInExamStatistic, selectedCourse);
	}

	/**
	 * select Course ComboBox handler
	 * 
	 * @param combobox
	 * @param selectedSubject
	 */
	private void selectCourseComboBox(ComboBox<String> combobox, String selectedSubject) {
		client.handleMessageFromClientUI(Message.getCourses + " " + selectedSubject);
		combobox.getItems().clear();
		combobox.setItems(client.getCoursesFromDB()); // sets the courses that is under specific
														// subject
		combobox.setPromptText("Select Course");
		combobox.setButtonCell(new ListCell<String>() {
			@Override
			protected void updateItem(String item, boolean empty) {
				super.updateItem(item, empty);
				if (empty || item == null) {
					setText("Select Course");
				} else {
					setText(item);
				}
			}
		});
	}

	/**
	 * setting table in exam management
	 */
	private void setTableInExamsManagement() {
		client.getExamsFromDB().clear();
		client.handleMessageFromClientUI(Message.getExamByCourse + " " + courseExamManagement.getValue());
		tableViewInExamsManagement.setItems(client.getExamsFromDB());
	}

	/**
	 * setting table in active exam management
	 */
	private void setTableInActiveExamManagement() {
		client.getActivatedUnlockedExams().clear();
		client.handleMessageFromClientUI(Message.getActiveExamsByActivator + " " + client.getId() + " "
				+ coursesActiveExamManagement.getValue());
		activeExamsTableView.setItems(client.getActivatedUnlockedExams());
	}

	/**
	 * set Columns In Exams Management table view
	 */
	private void setColumnsInExamsManagement() {
		subjectColInExamsManagement.setCellValueFactory(new PropertyValueFactory<>("subjectID"));
		courseColInExamsManagement.setCellValueFactory(new PropertyValueFactory<>("courseID"));
		examNumColInExamsManagement.setCellValueFactory(new PropertyValueFactory<>("examNum"));
		authorColInExamsManagement.setCellValueFactory(new PropertyValueFactory<>("teacherName"));
		durationColInExamsManagement.setCellValueFactory(new PropertyValueFactory<>("examDuration"));
		textExamineesColInExamsManagement.setCellValueFactory(new PropertyValueFactory<>("freeTextForExaminees"));
		textTeachersColInExamsManagement.setCellValueFactory(new PropertyValueFactory<>("freeTextForTeacherOnly"));
	}

	/**
	 * Initialize for comboBox in CreateExam
	 */
	private void initAnchorPaneInCreateExamFirstWindow() {
		setSubjectComboBox(subjectInCreateExamComboBox);
		setCourseComboBox(courseInCreateExamComboBox);
		durationInCreateExamField.setText(null);
	}

	/**
	 * Update the table by subject for create exam
	 * 
	 * @param event
	 */
	private void setTableInCreateExamAllQuestions() {
		client.getQuestionsFromDB().clear();
		client.handleMessageFromClientUI(Message.getQuestionBySubject + " " + subjectInCreateExamComboBox.getValue());
		initTablesInCreateExam(true, false);
		tableViewInCreateExamAllQuestion.setItems(client.getQuestionsFromDB());
	}

	/**
	 * show exam handler
	 */
	private void showExam() {
		int index = 0;
		QuestionInComputerizeExam questionInComputerizeExam;
		vBoxShowExam.getChildren().add(new Text("\n"));
		for (Question questionsFromDB : client.getQuestionsFromDB()) {
			for (StudentAnswerInQuestion studnetAnswerInQuestionDB : client.getStudnetAnswerInQuestionDB()) {
				if (questionsFromDB.getQuestionNum().equals(studnetAnswerInQuestionDB.getQuestionNum())) {
					questionInComputerizeExam = new QuestionInComputerizeExam(
							Integer.toString(++index) + ". " + questionsFromDB.getQuestionText(),
							"\t" + questionsFromDB.getFirstPossibleAnswer() + "\t",
							"\t" + questionsFromDB.getSecondPossibleAnswer() + "\t",
							"\t" + questionsFromDB.getThirdPossibleAnswer() + "\t",
							"\t" + questionsFromDB.getFourthPossibleAnswer() + "\t");
					questionInComputerizeExam.setTextOnGreen(questionsFromDB.getCorrectAnswer());
					if (!studnetAnswerInQuestionDB.getStudentAnswer().equals(questionsFromDB.getCorrectAnswer())) {
						questionInComputerizeExam.setTextOnRed(studnetAnswerInQuestionDB.getStudentAnswer());
					}
					questionInComputerizeExamArray.add(questionInComputerizeExam);
					vBoxShowExam.getChildren().addAll(questionInComputerizeExam.getList());
					vBoxShowExam.getChildren().add(new Text(""));
				}
			}
		}
		anchorPaneShowExam.setVisible(true);
		vBoxShowExam.setVisible(true);
	}

	/**
	 * Sets the exam number combo box that is filtered by author
	 * 
	 * @param combobox
	 * @param course
	 */
	private void selectExamNumber(ComboBox<String> combobox, String course) {
		client.handleMessageFromClientUI(Message.getExamsByAuthor + " " + course + " " + firstName + " " + lastName);
		combobox.getItems().clear();
		combobox.setItems(client.getExamsByAuthorFromDB()); // sets the exams that is under specific course & written by
															// specific teacher
		combobox.setPromptText("Select Exam Number");
		combobox.setButtonCell(new ListCell<String>() {
			@Override
			protected void updateItem(String item, boolean empty) {
				super.updateItem(item, empty);
				if (empty || item == null) {
					setText("Select Exam Number");
				} else {
					setText(item);
				}
			}
		});
	}

	/**
	 * Define the columns in Confirm Grades screen
	 */
	private void setTableInConfirmGrades() {
		client.getCheckedExamsFromDB().clear();
		client.handleMessageFromClientUI(Message.getCheckedExams + " " + client.getId());
		confirmGradeTableView.setItems(client.getCheckedExamsFromDB());
	}

	/**
	 * Define the columns in Edit\Remove screen
	 */
	private void setColumnsInEditOrRemove() {
		subjectIDColumnInEditOrRemove.setCellValueFactory(new PropertyValueFactory<>("subjectID"));
		questionNumColumnInEditOrRemove.setCellValueFactory(new PropertyValueFactory<>("questionNum"));
		authorColumnInEditOrRemove.setCellValueFactory(new PropertyValueFactory<>("author"));
		questionTextColumnInEditOrRemove.setCellValueFactory(new PropertyValueFactory<>("questionText"));
		firstPossibleAnswerColumnInEditOrRemove.setCellValueFactory(new PropertyValueFactory<>("firstPossibleAnswer"));
		secondPossibleAnswerColumnInEditOrRemove
				.setCellValueFactory(new PropertyValueFactory<>("secondPossibleAnswer"));
		thirdPossibleAnswerColumnInEditOrRemove.setCellValueFactory(new PropertyValueFactory<>("thirdPossibleAnswer"));
		fourthPossibleAnswerColumnInEditOrRemove
				.setCellValueFactory(new PropertyValueFactory<>("fourthPossibleAnswer"));
		correctAnswerColumnInEditOrRemove.setCellValueFactory(new PropertyValueFactory<>("correctAnswer"));

		/* define the columns editable */
		questionTextColumnInEditOrRemove.setCellFactory(TextFieldTableCell.forTableColumn());
		questionTextColumnInEditOrRemove.setOnEditCommit(new EventHandler<CellEditEvent<Question, String>>() {
			@Override
			public void handle(CellEditEvent<Question, String> t) {
				((Question) t.getTableView().getItems().get(t.getTablePosition().getRow()))
						.setQuestionText(t.getNewValue());
			}
		});
		firstPossibleAnswerColumnInEditOrRemove.setCellFactory(TextFieldTableCell.forTableColumn());
		firstPossibleAnswerColumnInEditOrRemove.setOnEditCommit(new EventHandler<CellEditEvent<Question, String>>() {
			@Override
			public void handle(CellEditEvent<Question, String> t) {
				((Question) t.getTableView().getItems().get(t.getTablePosition().getRow()))
						.setFirstPossibleAnswer(t.getNewValue());
			}
		});
		secondPossibleAnswerColumnInEditOrRemove.setCellFactory(TextFieldTableCell.forTableColumn());
		secondPossibleAnswerColumnInEditOrRemove.setOnEditCommit(new EventHandler<CellEditEvent<Question, String>>() {
			@Override
			public void handle(CellEditEvent<Question, String> t) {
				((Question) t.getTableView().getItems().get(t.getTablePosition().getRow()))
						.setSecondPossibleAnswer(t.getNewValue());
			}
		});
		thirdPossibleAnswerColumnInEditOrRemove.setCellFactory(TextFieldTableCell.forTableColumn());
		thirdPossibleAnswerColumnInEditOrRemove.setOnEditCommit(new EventHandler<CellEditEvent<Question, String>>() {
			@Override
			public void handle(CellEditEvent<Question, String> t) {
				((Question) t.getTableView().getItems().get(t.getTablePosition().getRow()))
						.setThirdPossibleAnswer(t.getNewValue());
			}
		});
		fourthPossibleAnswerColumnInEditOrRemove.setCellFactory(TextFieldTableCell.forTableColumn());
		fourthPossibleAnswerColumnInEditOrRemove.setOnEditCommit(new EventHandler<CellEditEvent<Question, String>>() {
			@Override
			public void handle(CellEditEvent<Question, String> t) {
				((Question) t.getTableView().getItems().get(t.getTablePosition().getRow()))
						.setFourthPossibleAnswer(t.getNewValue());
			}
		});
		correctAnswerColumnInEditOrRemove.setCellFactory(TextFieldTableCell.forTableColumn());
		correctAnswerColumnInEditOrRemove.setOnEditCommit(new EventHandler<CellEditEvent<Question, String>>() {
			@Override
			public void handle(CellEditEvent<Question, String> t) {
				((Question) t.getTableView().getItems().get(t.getTablePosition().getRow()))
						.setCorrectAnswer(t.getNewValue());
			}
		});
	}

	/**
	 * Define the columns for the table of the 'Show Questions' pop up in the exam
	 * management screen
	 */
	private void setColumnsInExamManagement() {
		subjectIDColumnInExamManagement.setCellValueFactory(new PropertyValueFactory<>("subjectID"));
		questionNumColumnInExamManagement.setCellValueFactory(new PropertyValueFactory<>("questionNum"));
		authorColumnInExamManagement.setCellValueFactory(new PropertyValueFactory<>("author"));
		questionTextColumnInExamManagement.setCellValueFactory(new PropertyValueFactory<>("questionText"));
		firstPossibleAnswerColumnInExamManagement
				.setCellValueFactory(new PropertyValueFactory<>("firstPossibleAnswer"));
		secondPossibleAnswerColumnInExamManagement
				.setCellValueFactory(new PropertyValueFactory<>("secondPossibleAnswer"));
		thirdPossibleAnswerColumnInExamManagement
				.setCellValueFactory(new PropertyValueFactory<>("thirdPossibleAnswer"));
		fourthPossibleAnswerColumnInExamManagement
				.setCellValueFactory(new PropertyValueFactory<>("fourthPossibleAnswer"));
		correctAnswerColumnInExamManagement.setCellValueFactory(new PropertyValueFactory<>("correctAnswer"));
	}

	/**
	 * Define the columns by subject
	 */
	private void setColumnInCreateExamAllQuestions() {
		subjectIDColumnInCreateExamAllQuestions.setCellValueFactory(new PropertyValueFactory<>("subjectID"));
		questionNumColumnInCreateExamAllQuestions.setCellValueFactory(new PropertyValueFactory<>("questionNum"));
		authorColumnInCreateExamAllQuestions.setCellValueFactory(new PropertyValueFactory<>("author"));
		questionTextColumnInCreateExamAllQuestions.setCellValueFactory(new PropertyValueFactory<>("questionText"));
		firstPossibleAnswerColumnInCreateExamAllQuestions
				.setCellValueFactory(new PropertyValueFactory<>("firstPossibleAnswer"));
		secondPossibleAnswerColumnInCreateExamAllQuestions
				.setCellValueFactory(new PropertyValueFactory<>("secondPossibleAnswer"));
		thirdPossibleAnswerColumnInCreateExamAllQuestions
				.setCellValueFactory(new PropertyValueFactory<>("thirdPossibleAnswer"));
		fourthPossibleAnswerColumnInCreateExamAllQuestions
				.setCellValueFactory(new PropertyValueFactory<>("fourthPossibleAnswer"));
		correctAnswerColumnInCreateExamAllQuestions.setCellValueFactory(new PropertyValueFactory<>("correctAnswer"));
	}

	/**
	 * define the columns In Create Exam Questions
	 */
	private void setColumnInCreateExamQuestions() {
		subjectIDColumnInCreateExam.setCellValueFactory(new PropertyValueFactory<>("subjectID"));
		questionNumberColumnInCreateExam.setCellValueFactory(new PropertyValueFactory<>("questionNum"));
		questionTextColumnInCreateExam.setCellValueFactory(new PropertyValueFactory<>("questionText"));
		pointsColumnInCreateExam.setCellValueFactory(new PropertyValueFactory<>("points"));

		// define the columns editable

		pointsColumnInCreateExam.setCellFactory(
				TextFieldTableCell.<QuestionInExam, Integer>forTableColumn(new IntegerStringConverter()));
		pointsColumnInCreateExam.setOnEditCommit(new EventHandler<CellEditEvent<QuestionInExam, Integer>>() {
			@Override
			public void handle(CellEditEvent<QuestionInExam, Integer> t) {
				((QuestionInExam) t.getTableView().getItems().get(t.getTablePosition().getRow()))
						.setPoints(t.getNewValue());
			}
		});
	}

	/**
	 * set Column In Confirm Grades
	 */
	private void setColumnInConfirmGrades() {
		subjectColInConfirmGrades.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()
				.getSubmittedExam().getStudentInActiveExam().getActiveExam().getExam().getSubjectID()));
		courseColInConfirmGrades.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()
				.getSubmittedExam().getStudentInActiveExam().getActiveExam().getExam().getCourseID()));
		examNumColInConfirmGrades.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()
				.getSubmittedExam().getStudentInActiveExam().getActiveExam().getExam().getExamNum()));
		executionCodeColInConfirmGrades.setCellValueFactory(cellData -> new SimpleStringProperty(
				cellData.getValue().getSubmittedExam().getStudentInActiveExam().getActiveExam().getExecutionCode()));
		studentIDColInConfirmGrades.setCellValueFactory(cellData -> new SimpleStringProperty(
				cellData.getValue().getSubmittedExam().getStudentInActiveExam().getStudent().getId()));
		gradeColInConfirmGrades.setCellValueFactory(new PropertyValueFactory<>("grade"));

	}

	/**
	 * Sets the columns of the activated exams table view
	 */
	private void setColumnsOfActiveExams() {
		subjectNumberOfActiveExam.setCellValueFactory(
				cellData -> new SimpleStringProperty(cellData.getValue().getExam().getSubjectID()));
		courseNumberOfAvticeExam
				.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getExam().getCourseID()));
		examNumberOfAvticeExam
				.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getExam().getExamNum()));
		executionCodeOfAvticeExam.setCellValueFactory(new PropertyValueFactory<>("executionCode"));
		durationOfAvticeExam.setCellValueFactory(new PropertyValueFactory<>("duration"));
		typeOfAvticeExam.setCellValueFactory(new PropertyValueFactory<>("type"));
	}

	/**
	 * Updates the GUI questions table from the data base
	 */
	private void setQuestionsTableInfoInEditOrRemove() {
		String subject = subjectComboBoxInEditOrRemove.getValue();
		if (subject != null) {
			client.handleMessageFromClientUI(Message.editOrRemove + " " + firstName + " " + lastName + " " + subject);
		} else {
			Utilities_Client.popUpMethod("Please select the subject");
		}
		tableViewInEditOrRemove.setItems(client.getQuestionsFromDB());
	}

	/**
	 * init's the fields of the 'Add Question' option
	 */
	private void initAddQuestionOption() {
		setSubjectComboBox(subjectComboBoxInAddQuestion);
		setSelectComboBox(correctAnswerComboBox);
	}

	/**
	 * clear the fields of the last question that was added or when clear button was
	 * pressed
	 */
	private void clearAddQuestionFields() {
		if (subjectComboBoxInAddQuestion.getValue() != null) {
			subjectComboBoxInAddQuestion.getSelectionModel().clearSelection();
			subjectComboBoxInAddQuestion.setPromptText("Select Subject");
			subjectComboBoxInAddQuestion.setButtonCell(new ListCell<String>() {
				@Override
				protected void updateItem(String item, boolean empty) {
					super.updateItem(item, empty);
					if (empty || item == null) {
						setText("Select Subject");
					} else {
						setText(item);
					}
				}
			});
		}
		if (correctAnswerComboBox.getValue() != null) {
			correctAnswerComboBox.getSelectionModel().clearSelection();
			correctAnswerComboBox.setPromptText("Select");
			correctAnswerComboBox.setButtonCell(new ListCell<String>() {

				@Override
				protected void updateItem(String item, boolean empty) {
					super.updateItem(item, empty);
					if (empty || item == null) {
						setText("Select");
					} else {
						setText(item);
					}
				}

			});
		}
		if (!questionTextField.getText().isEmpty())

		{
			questionTextField.clear();
		}
		if (!firstAnswerField.getText().isEmpty()) {
			firstAnswerField.clear();
		}
		if (!secondAnswerField.getText().isEmpty()) {
			secondAnswerField.clear();
		}
		if (!thirdAnswerField.getText().isEmpty()) {
			thirdAnswerField.clear();
		}
		if (!fourthAnswerField.getText().isEmpty()) {
			fourthAnswerField.clear();
		}
	}

	/**
	 * Clears the add exam screen according to the requested window
	 * 
	 * @param window
	 */
	private void clearFieldsInAddExamWindows(String window) {
		// clear the first window fields
		if (window.equals("first")) {
			subjectInCreateExamComboBox.getSelectionModel().clearSelection();
			subjectInCreateExamComboBox.setPromptText("Select Subject");
			subjectInCreateExamComboBox.setButtonCell(new ListCell<String>() {
				@Override
				protected void updateItem(String item, boolean empty) {
					super.updateItem(item, empty);
					if (empty || item == null) {
						setText("Select Subject");
					} else {
						setText(item);
					}
				}
			});
			courseInCreateExamComboBox.getSelectionModel().clearSelection();
			courseInCreateExamComboBox.setPromptText("Select Course");
			courseInCreateExamComboBox.setButtonCell(new ListCell<String>() {
				@Override
				protected void updateItem(String item, boolean empty) {
					super.updateItem(item, empty);
					if (empty || item == null) {
						setText("Select Course");
					} else {
						setText(item);
					}
				}
			});
			durationInCreateExamField.clear();
		} else if (window.equals("second")) // clears the table in the second window
			tableViewInCreateExamQuestion.getItems().clear();
		else if (window.equals("third")) { // clears the the text fields in the third window
			textAreaStudentsInCreateExam.clear();
			textAreaTeachersInCreateExam.clear();
		}
	}

	/**
	 * Sets the subject combo box by teacher
	 * 
	 * @param comboBox
	 */
	private void setSubjectComboBox(ComboBox<String> comboBox) {
		comboBox.getSelectionModel().clearSelection();
		comboBox.setPromptText("Select Subject");
		comboBox.setButtonCell(new ListCell<String>() {
			@Override
			protected void updateItem(String item, boolean empty) {
				super.updateItem(item, empty);
				if (empty || item == null) {
					setText("Select Subject");
				} else {
					setText(item);
				}
			}
		});
		String ID = client.getId();
		if (ID.isEmpty())
			client.handleMessageFromClientUI(Message.getSubjects);
		else
			client.handleMessageFromClientUI(Message.getSubjectsByTeacherID + " " + ID);
		comboBox.setItems(client.getSubjectsFromDB());
	}

	/**
	 * Sets the course combo box by subject
	 * 
	 * @param comboBox
	 */
	private void setCourseComboBox(ComboBox<String> comboBox) {
		comboBox.getSelectionModel().clearSelection();
		comboBox.setPromptText("Select Course");
	}

	/**
	 * Set the answers combo box
	 * 
	 * @param comboBox
	 */
	private void setSelectComboBox(ComboBox<String> comboBox) {
		comboBox.getSelectionModel().clearSelection();
		comboBox.setPromptText("Select");
		comboBox.getItems().addAll("1", "2", "3", "4");
	}

	/**
	 * Sets the exam number combo box to default
	 * 
	 * @param comboBox
	 */
	private void setExamNumberComboBox(ComboBox<String> comboBox) {
		comboBox.getSelectionModel().clearSelection();
		comboBox.setPromptText("Select Exam Number");
	}

} // end of class