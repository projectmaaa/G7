package controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import boundaries.QuestionInComputerizeExam;
import client.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.Stage;
import resources.*;

/**
 * This class represents controller for Principal Window (Gui).
 * 
 * @author Group 7
 *
 */
public class PrincipalWindowController implements Initializable, IScreenController {

	/******************** Attributes ********************/

	private ScreensController screensController;

	private String firstName;

	private String lastName;

	private Client client;

	@FXML
	private AnchorPane mainAnchorPane;

	@FXML
	private Button logoutButton;

	@FXML
	private AnchorPane welconeAnchorPane;

	@FXML
	private Text welcomeText;

	@FXML
	private Text date;

	@FXML
	private Text name;

	@FXML
	private AnchorPane welcomeAnchorPane;

	@FXML
	private MenuBar menuBar;

	ArrayList<Integer> grades;

	HashMap<String, Integer> gradesWithExam;

	/***** Questions Pool Attributes *****/

	@FXML
	private MenuItem questionsPool;

	@FXML
	private AnchorPane questionsPoolAnchorPane;

	@FXML
	private Label subjectInQuestionsPoolLabel;

	@FXML
	private ComboBox<String> subjectComboBoxInQuestionsPool;

	@FXML
	private Button showQuestionsInQuestionsPool;

	@FXML
	private TableView<Question> tableViewInQuestionsPool;

	@FXML
	private TableColumn<Question, String> subjectIDColumnInQuestionsPool;

	@FXML
	private TableColumn<Question, String> questionNumColumnInQuestionsPool;

	@FXML
	private TableColumn<Question, String> authorColumnInQuestionsPool;

	@FXML
	private TableColumn<Question, String> questionTextColumnInQuestionsPool;

	@FXML
	private TableColumn<Question, String> possibleAnswersColumnInQuestionsPool;

	@FXML
	private TableColumn<Question, String> firstPossibleAnswerColumnInQuestionsPool;

	@FXML
	private TableColumn<Question, String> secondPossibleAnswerColumnInQuestionsPool;

	@FXML
	private TableColumn<Question, String> thirdPossibleAnswerColumnInQuestionsPool;

	@FXML
	private TableColumn<Question, String> fourthPossibleAnswerColumnInQuestionsPool;

	@FXML
	private TableColumn<Question, String> correctAnswerColumnInQuestionsPool;

	/***** Exams Pool Attributes *****/

	@FXML
	private AnchorPane examsPoolAnchorPane;

	@FXML
	private ComboBox<String> subjectComboBoxInExamPool;

	@FXML
	private ComboBox<String> courseComboBoxInExamPool;

	@FXML
	private Button showExamsInExamsPool;

	@FXML
	private Button showQuestionsInExamsPool;

	@FXML
	private TableView<Exam> tableViewInExamsPool;

	@FXML
	private TableColumn<Exam, String> subjectColInExamsPool;

	@FXML
	private TableColumn<Exam, String> courseColInExamsPool;

	@FXML
	private TableColumn<Exam, String> examNumColInExamsPool;

	@FXML
	private TableColumn<Exam, String> authorColInExamsPool;

	@FXML
	private TableColumn<Exam, String> durationColInExamsPool;

	@FXML
	private TableColumn<Exam, String> textExamineesColInExamsPool;

	@FXML
	private TableColumn<Exam, String> textTeachersColInExamsPool;

	@FXML
	private TableView<Question> questionsTableOfSpecificExam;

	@FXML
	private TableColumn<Question, String> subjectIDOfSpecificExam;

	@FXML
	private TableColumn<Question, String> questionNumberInSpecificExam;

	@FXML
	private TableColumn<Question, String> authorOfSpecificExam;

	@FXML
	private TableColumn<Question, String> questionTextInSpecificExam;

	@FXML
	private TableColumn<Question, String> possibleAnswersInSpecificExam;

	@FXML
	private TableColumn<Question, String> firstPossibleAnswerInSpecificExam;

	@FXML
	private TableColumn<Question, String> secondPossibleAnswerInSpecificExam;

	@FXML
	private TableColumn<Question, String> thirdPossibleAnswerInSpecificExam;

	@FXML
	private TableColumn<Question, String> fourthPossibleAnswerInSpecificExam;

	@FXML
	private TableColumn<Question, String> correctAnswerInSpecificExam;

	/***** Active Exams Attributes *****/

	@FXML
	private AnchorPane activeExamAnchorPane;

	@FXML
	private ComboBox<String> subjectComboBoxInActiveExams;

	@FXML
	private ComboBox<String> courseComboBoxInActiveExams;

	@FXML
	private Button showActiveExamButton;

	@FXML
	private TableView<ActiveExam> activeExamTableView;

	@FXML
	private TableColumn<ActiveExam, String> subjectColInActiveExams;

	@FXML
	private TableColumn<ActiveExam, String> courseColInActiveExams;

	@FXML
	private TableColumn<ActiveExam, String> examNumColInActiveExams;

	@FXML
	private TableColumn<ActiveExam, String> executionCodeColInActiveExams;

	@FXML
	private TableColumn<ActiveExam, String> activatorColInActiveExams;

	@FXML
	private TableColumn<ActiveExam, Integer> durationColInActiveExams;

	@FXML
	private TableColumn<ActiveExam, Integer> lockedColInActiveExams;

	@FXML
	private TableColumn<ActiveExam, String> typeColInActiveExams;

	/***** Solved Exams Attributes *****/

	@FXML
	private AnchorPane solvedExamsAnchorPane;

	@FXML
	private ComboBox<String> studentComboBoxInSolvedExams;

	@FXML
	private TableView<ApprovedExamForStudent> solvedExamsTableView;

	@FXML
	private TableColumn<ApprovedExamForStudent, String> subjectColInSolvedExams;

	@FXML
	private TableColumn<ApprovedExamForStudent, String> courseColInSolvedExams;

	@FXML
	private TableColumn<ApprovedExamForStudent, String> examNumColInSolvedExams;

	@FXML
	private TableColumn<ApprovedExamForStudent, String> executionCodeColInSolvedExams;

	@FXML
	private TableColumn<ApprovedExamForStudent, Integer> gradeColInSolvedExams;

	@FXML
	private TableColumn<ApprovedExamForStudent, String> commentsColInSolvedExams;

	@FXML
	private Button showSolvedExamButton;

	@FXML
	private Button showButtonInSolvedExams;

	/***** Show Solved Exam *****/

	@FXML
	private AnchorPane anchorPaneShowExam;

	@FXML
	private ScrollPane scrollPaneShowExam;

	@FXML
	private VBox vBoxShowExam;

	@FXML
	private Button okButtonShowExam;

	private ArrayList<QuestionInComputerizeExam> questionInComputerizeExamArray;

	/***** Handling Requests Attributes *****/

	@FXML
	private AnchorPane handlingRequestsAnchorPane;

	@FXML
	private TableView<WaitingActiveExam> handlingRequestsTableView;

	@FXML
	private TableColumn<WaitingActiveExam, String> subjectColInHandlingRequests;

	@FXML
	private TableColumn<WaitingActiveExam, String> courseColInHandlingRequests;

	@FXML
	private TableColumn<WaitingActiveExam, String> examNumColInHandlingRequests;

	@FXML
	private TableColumn<WaitingActiveExam, String> executionCodeColInHandlingRequests;

	@FXML
	private TableColumn<WaitingActiveExam, String> durationColInHandlingRequests;

	@FXML
	private TableColumn<WaitingActiveExam, String> newDurationColInHandlingRequests;

	@FXML
	private TableColumn<WaitingActiveExam, String> reasonColInHandlingRequests;

	@FXML
	private Button approveButtonInHandlingRequests;

	@FXML
	private Button rejectButtonInHandlingRequests;

	@FXML
	private ImageView refreshButtonInHandlingRequests;

	/***** Student Report Attributes *****/

	@FXML
	private AnchorPane studentReportAnchorPane;

	@FXML
	private TableView<Student> studentsTableView;

	@FXML
	private TableColumn<Student, String> studentIDColInStudentReport;

	@FXML
	private TableColumn<Student, String> firstNameColInStudentReport;

	@FXML
	private TableColumn<Student, String> lastNameColInStudentReport;

	@FXML
	private Button createReportButton;

	@FXML
	private AnchorPane report3AnchorPane;

	@FXML
	private TextField averageTextFieldInStudentReport;

	@FXML
	private Label studentNameLabel;

	@FXML
	private TextField medianTextFieldInStudentReport;

	@FXML
	private BarChart<String, Number> studentBarChart;

	@FXML
	private CategoryAxis xInStudentReportBarChart;

	@FXML
	private NumberAxis yInStudentReportBarChart;

	/***** Course Report Attributes *****/

	@FXML
	private AnchorPane courseReportAnchorPane;

	@FXML
	private Button createReportButtonInCourseReport;

	@FXML
	private TableView<Course> courseTableView;

	@FXML
	private TableColumn<Course, String> subjectColInStudentReport;

	@FXML
	private TableColumn<Course, String> courseIDColInStudentReport;

	@FXML
	private TableColumn<Course, String> courseNameColInStudentReport;

	@FXML
	private AnchorPane report2AnchorPane;

	@FXML
	private TextField averageTextFieldInCourseReport;

	@FXML
	private Label CourseNameLabel;

	@FXML
	private TextField medianTextFieldInCourseReport;

	@FXML
	private BarChart<?, ?> courseReportBarChart;

	@FXML
	private CategoryAxis xAxisInCourseReport;

	@FXML
	private NumberAxis yAxisInCourseReport;

	/***** Teacher Report Attributes *****/

	@FXML
	private AnchorPane teacherReportAnchorPane;

	@FXML
	private Button createReportButtonInTeacherReport;

	@FXML
	private TableView<Teacher> teacherTableView;

	@FXML
	private TableColumn<Teacher, String> teacherIDColInSTeacherReport;

	@FXML
	private TableColumn<Teacher, String> firstNameColInTeacherReport;

	@FXML
	private TableColumn<Teacher, String> lastNameColInTeacherReport;

	@FXML
	private AnchorPane report1AnchorPane;

	@FXML
	private TextField averageTextFieldInTeacherReport;

	@FXML
	private Label teacherNameLabel;

	@FXML
	private TextField medianTextFieldInTeacherReport;

	@FXML
	private BarChart<?, ?> teacherBarChart;

	@FXML
	private CategoryAxis xAxisInTeacherReport;

	@FXML
	private NumberAxis yAxisInTeacherReport;

	/******************** Getters and Setters ********************/

	/**
	 * Set the parent screen
	 */
	@Override
	public void setScreenParent(ScreensController screenParent) {
		screensController = screenParent;
	}

	/**
	 * get first name of client
	 * 
	 * @return The first name
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * set first name of client
	 * 
	 * @param firstName
	 *            The first name
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * get last name of client
	 * 
	 * @return The last name
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * set last name of client
	 * 
	 * @param lastName
	 *            The last name
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * set full name of client
	 */
	public void setName() {
		name.setText(firstName + " " + lastName);
	}

	/**
	 * 
	 * @return The average text field
	 */
	public TextField getAverageTextFieldInStudentReport() {
		return averageTextFieldInStudentReport;
	}

	/**
	 * 
	 * @param averageTextFieldInStudentReport
	 *            The average text field
	 */
	public void setAverageTextFieldInStudentReport(TextField averageTextFieldInStudentReport) {
		this.averageTextFieldInStudentReport = averageTextFieldInStudentReport;
	}

	/**
	 * set first and last name of client
	 * 
	 * @param firstName
	 *            The first name
	 * @param lastName
	 *            The last name
	 */
	public void setNameAndLastName(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
		welcomeText.setText(welcomeText.getText() + " " + this.firstName + " " + this.lastName);
	}

	/**
	 * get Average Text Field In Course Report
	 * 
	 * @return TextField averageTextFieldInCourseReport
	 */
	public TextField getAverageTextFieldInCourseReport() {
		return averageTextFieldInCourseReport;
	}

	/**
	 * set Average Text Field In Course Report
	 * 
	 * @param averageTextFieldInCourseReport
	 *            The average text field in course report
	 */
	public void setAverageTextFieldInCourseReport(TextField averageTextFieldInCourseReport) {
		this.averageTextFieldInCourseReport = averageTextFieldInCourseReport;
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
	 *            The average text field in teacher's report
	 */
	public void setAverageTextFieldInTeacherReport(TextField averageTextFieldInTeacherReport) {
		this.averageTextFieldInTeacherReport = averageTextFieldInTeacherReport;
	}

	/**
	 * get grades array for histogram
	 * 
	 * @return Array List of grades
	 */
	public ArrayList<Integer> getGrades() {
		return grades;
	}

	/**
	 * 
	 * @param grades
	 *            The list of grades
	 */
	public void setGrades(ArrayList<Integer> grades) {
		this.grades = grades;
	}

	/**
	 * get Median Text Field In Student Report
	 * 
	 * @return TextField medianTextFieldInStudentReport
	 */
	public TextField getMedianTextFieldInStudentReport() {
		return medianTextFieldInStudentReport;
	}

	/**
	 * set Median Text Field In Student Report
	 * 
	 * @param medianTextFieldInStudentReport
	 *            The median text field in the report about student
	 */
	public void setMedianTextFieldInStudentReport(TextField medianTextFieldInStudentReport) {
		this.medianTextFieldInStudentReport = medianTextFieldInStudentReport;
	}

	/**
	 * get Median Text Field In Course Report
	 * 
	 * @return TextField medianTextFieldInCourseReport
	 */
	public TextField getMedianTextFieldInCourseReport() {
		return medianTextFieldInCourseReport;
	}

	/**
	 * set Median Text Field In Course Report
	 * 
	 * @param medianTextFieldInCourseReport
	 *            The median text field in the report about course
	 */
	public void setMedianTextFieldInCourseReport(TextField medianTextFieldInCourseReport) {
		this.medianTextFieldInCourseReport = medianTextFieldInCourseReport;
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
	 *            The median text field in the report about teacher
	 */
	public void setMedianTextFieldInTeacherReport(TextField medianTextFieldInTeacherReport) {
		this.medianTextFieldInTeacherReport = medianTextFieldInTeacherReport;
	}

	/**
	 * get exam and grade hash map for student report
	 * 
	 * @return Grades With Exam
	 */
	public HashMap<String, Integer> getGradesWithExam() {
		return gradesWithExam;
	}

	/**
	 * set exam and grade hash map for student report
	 * 
	 * @param gradesWithExam
	 *            Grades With Exam
	 */
	public void setGradesWithExam(HashMap<String, Integer> gradesWithExam) {
		this.gradesWithExam = gradesWithExam;
	}

	/******************** Initialization ********************/

	/**
	 * initialize screen method
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		date.setText(Utilities_Client.setDate());
		this.client = MainAppClient.getClient();
		client.setPrincipalWindowController(this);
		questionInComputerizeExamArray = new ArrayList<QuestionInComputerizeExam>();
		/* set the table at the questions pool */
		setColumnsOfQuestionsTable(subjectIDColumnInQuestionsPool, questionNumColumnInQuestionsPool,
				authorColumnInQuestionsPool, questionTextColumnInQuestionsPool,
				firstPossibleAnswerColumnInQuestionsPool, secondPossibleAnswerColumnInQuestionsPool,
				thirdPossibleAnswerColumnInQuestionsPool, fourthPossibleAnswerColumnInQuestionsPool,
				correctAnswerColumnInQuestionsPool);
		/* set the table for the questions pop up of specific exam */
		setColumnsOfQuestionsTable(subjectIDOfSpecificExam, questionNumberInSpecificExam, authorOfSpecificExam,
				questionTextInSpecificExam, firstPossibleAnswerInSpecificExam, secondPossibleAnswerInSpecificExam,
				thirdPossibleAnswerInSpecificExam, fourthPossibleAnswerInSpecificExam, correctAnswerInSpecificExam);
		setColumnsInExamsPool();
		setColumnsInHandlingRequests();
		setColInStudentReport();
		setColInCourseReport();
		setColInTeacherReport();
		setColumnsInActiveExams();
		setColumnsInSolvedExams();
	}

	/******************** Methods ********************/

	/**
	 * logout button handler
	 * 
	 * @param event
	 *            The button is clicked
	 */
	public void logOutButtonHandler(ActionEvent event) {
		welcomeText.setText("Welcome");
		this.client.handleMessageFromClientUI(Message.logout);
		screensController.setScreen(MainAppClient.loginScreenID);
		setAnchorPanesFalse();
		welcomeAnchorPane.setVisible(true);
		clearSolvedExam();
	}

	/**
	 * Question tab was pressed
	 * 
	 * @param event
	 *            The menu item is clicked
	 */
	public void openQuestionPool(ActionEvent event) {
		tableViewInQuestionsPool.getItems().clear();
		setAnchorPanesFalse();
		questionsPoolAnchorPane.setVisible(true);
		setSubjectComboBox(subjectComboBoxInQuestionsPool);
	}

	/**
	 * show question button was clicked
	 * 
	 * @param event
	 *            The button is clicked
	 */
	public void showQuestionHandler(ActionEvent event) {
		setTableInQuestionPool();
	}

	/**
	 * exam pool tab was pressed
	 * 
	 * @param event
	 *            The menu item is clicked
	 */
	public void openExamPool(ActionEvent event) {
		tableViewInExamsPool.getItems().clear();
		setAnchorPanesFalse();
		examsPoolAnchorPane.setVisible(true);
		setSubjectComboBox(subjectComboBoxInExamPool);
		setCourseComboBox(courseComboBoxInExamPool);
	}

	/**
	 * The action to be performed when the course combo box is clicked
	 * 
	 * @param event
	 *            The combo box is clicked
	 */
	public void courseComboBoxHandler(MouseEvent event) {
		if (examsPoolAnchorPane.isVisible()) {
			if (subjectComboBoxInExamPool.getValue() != null)
				courseComboBox(courseComboBoxInExamPool, subjectComboBoxInExamPool.getValue());
			else
				Utilities_Client.popUpMethod("Please Select Subject");
		} else if (activeExamAnchorPane.isVisible()) {
			if (subjectComboBoxInActiveExams.getValue() != null)
				courseComboBox(courseComboBoxInActiveExams, subjectComboBoxInActiveExams.getValue());
			else
				Utilities_Client.popUpMethod("Please Select Subject");
		}
	}

	/**
	 * Sets the courses from the data base in the combo box in the relevant screen
	 * 
	 * @param combobox
	 *            The course combo box in the relevant screen
	 * @param selectedSubject
	 *            The selected subject from the subject combo box in the relevant
	 *            screen
	 */
	private void courseComboBox(ComboBox<String> combobox, String selectedSubject) {
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
	 * show exams button was clicked
	 * 
	 * @param event
	 *            The button is clicked
	 */
	public void showExamsHandler(ActionEvent event) {
		setTableInExamsPool();
	}

	/**
	 * This method shows to the principal the questions of specific exam
	 * 
	 * @param event
	 *            The button is clicked
	 */
	public void showQuestionsOfSpecificExam(MouseEvent event) {
		Exam selectedExam = tableViewInExamsPool.getSelectionModel().getSelectedItem();
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
			questionsTableOfSpecificExam.setItems(client.getQuestionsFromDB());
			layout.setStyle("-fx-background-color: cornsilk; -fx-padding: 10;");
			layout.getChildren().addAll(questionsTableOfSpecificExam);
			questionsTableOfSpecificExam.setVisible(true);
			primaryStage.setScene(new Scene(layout));
			primaryStage.show();
		}
	}

	/**
	 * active exams was pressed
	 * 
	 * @param event
	 *            The menu item is clicked
	 */
	public void openActiveExams(ActionEvent event) {
		activeExamTableView.getItems().clear();
		setAnchorPanesFalse();
		activeExamAnchorPane.setVisible(true);
		setSubjectComboBox(subjectComboBoxInActiveExams);
	}

	/**
	 * show active exam button pressed
	 * 
	 * @param event
	 *            The button is clicked
	 */
	public void showActiveExamButtonHandler(ActionEvent event) {
		setTableInActiveExams();
	}

	/**
	 * solved exams tab pressed
	 * 
	 * @param event
	 *            The menu item is clicked
	 */
	public void openSolvedExams(ActionEvent event) {
		solvedExamsTableView.getItems().clear();
		setAnchorPanesFalse();
		solvedExamsAnchorPane.setVisible(true);
		setStudentComboBox(studentComboBoxInSolvedExams);
	}

	/**
	 * show button was pressed
	 * 
	 * @param event
	 *            The button is clicked
	 */
	public void showButtonHandler(ActionEvent event) {
		setTableInSolvedExams();
	}

	/**
	 * handling requests tab was pressed
	 * 
	 * @param event
	 *            The menu item is clicked
	 */
	public void openHandlingRequests(ActionEvent event) {
		setAnchorPanesFalse();
		handlingRequestsAnchorPane.setVisible(true);
		setTableInHandlingRequests();
	}

	/**
	 * Approve button was pressed
	 * 
	 * @param event
	 *            The button is clicked
	 */
	public void approveButtonHandle(ActionEvent event) {
		WaitingActiveExam waitingActiveExam = handlingRequestsTableView.getSelectionModel().getSelectedItem();
		if (waitingActiveExam == null)
			Utilities_Client.popUpMethod("Please choose the request");
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
					client.handleMessageFromClientUI(
							new WaitingActiveExamHandle(Message.requestApproved, waitingActiveExam));// Approvement pop
																										// up in
																										// teacher's
																										// window
					client.handleMessageFromClientUI(new WaitingActiveExamHandle("Approve", waitingActiveExam));
					client.handleMessageFromClientUI(new WaitingActiveExamHandle("Remove", waitingActiveExam));
					Utilities_Client.popUpMethod("Exam duration changed successfully!");
					setTableInHandlingRequests();
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
			layout.getChildren().addAll(text, yesButton, noButton);
			layout.setStyle("-fx-background-color: cornsilk; -fx-padding: 10;");
			primaryStage.setScene(new Scene(layout));
			primaryStage.show();
		}
	}

	/**
	 * Reject button was pressed
	 * 
	 * @param event
	 *            The button is clicked
	 */
	public void rejectButtonHandle(ActionEvent event) {
		WaitingActiveExam waitingActiveExam = handlingRequestsTableView.getSelectionModel().getSelectedItem();
		if (waitingActiveExam == null)
			Utilities_Client.popUpMethod("Please choose the request");
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
					client.handleMessageFromClientUI(
							new WaitingActiveExamHandle(Message.requestRejected, waitingActiveExam)); // Rejection pop
																										// up in
																										// teacher's
																										// window
					client.handleMessageFromClientUI(new WaitingActiveExamHandle("Remove", waitingActiveExam));
					Utilities_Client.popUpMethod("Request rejected!");
					setTableInHandlingRequests();
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
			layout.getChildren().addAll(text, yesButton, noButton);
			layout.setStyle("-fx-background-color: cornsilk; -fx-padding: 10;");
			primaryStage.setScene(new Scene(layout));
			primaryStage.show();
		}
	}

	/**
	 * refresh button was pressed
	 * 
	 * @param event
	 *            The button is clicked
	 */
	public void refreshButtonHandle(MouseEvent event) {
		setTableInHandlingRequests();
	}

	/**
	 * student report tab was pressed
	 * 
	 * @param event
	 *            The menu item is clicked
	 */
	public void openStudentReport(ActionEvent event) {
		setAnchorPanesFalse();
		studentReportAnchorPane.setVisible(true);
		setTableInStudentReport();
	}

	/**
	 * create report button was pressed
	 * 
	 * @param event
	 *            The button is clicked
	 * @throws InterruptedException
	 */
	public void createReportInStudentReportHandler(ActionEvent event) throws InterruptedException {
		averageTextFieldInStudentReport.setEditable(false);
		medianTextFieldInStudentReport.setEditable(false);
		Student student = studentsTableView.getSelectionModel().getSelectedItem();
		if (student == null) {
			Utilities_Client.popUpMethod("Please choose student first!");
		} else {
			String fullName = student.getFirstName() + " " + student.getLastName();
			studentNameLabel.setText(fullName);
			client.handleMessageFromClientUI(new ReportHandle("StudentStatistic", student));
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			createStudentHistogram(gradesWithExam);
			// TimeUnit.SECONDS.sleep(6);
			report3AnchorPane.setVisible(true);
		}
	}

	/**
	 * course report tab was pressed
	 * 
	 * @param event
	 *            The menu item is clicked
	 */
	public void openCourseReport(ActionEvent event) {
		setAnchorPanesFalse();
		courseReportAnchorPane.setVisible(true);
		setTableInCourseReport();
	}

	/**
	 * create report button was pressed
	 * 
	 * @param event
	 *            The button is clicked
	 */
	public void createReportInCourseReportHandler(ActionEvent event) {
		Course course = courseTableView.getSelectionModel().getSelectedItem();
		if (course == null) {
			Utilities_Client.popUpMethod("Please choose course first!");
		} else {
			String fullName = course.getCourseName();
			CourseNameLabel.setText(fullName);
			client.handleMessageFromClientUI(new ReportHandle("CourseStatistic", course));
			try {
				TimeUnit.SECONDS.sleep(3);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(grades);
			averageTextFieldInCourseReport.setEditable(false);
			createHistogram(courseReportBarChart, grades);
			report2AnchorPane.setVisible(true);
		}
	}

	/**
	 * create Student Histogram with hash map
	 * 
	 * @param gradesWithExams
	 *            The grades of specific exam
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void createStudentHistogram(HashMap<String, Integer> gradesWithExams) {
		studentBarChart.getData().clear();
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		XYChart.Series<String, Number> series1 = new XYChart.Series<String, Number>();
		series1.setName("AES7-Histogram");
		studentBarChart.setCategoryGap(3);
		studentBarChart.setBarGap(2);
		studentBarChart.setAnimated(false);
		ArrayList<String> exams = new ArrayList<String>();
		exams.addAll(gradesWithExams.keySet());
		ArrayList<Integer> grade = new ArrayList<Integer>();
		grade.addAll(gradesWithExams.values());
		for (int i = 0; i < grade.size(); i++) {
			series1.getData().add(new XYChart.Data(exams.get(i), grade.get(i)));
		}
		studentBarChart.getData().addAll(series1);
	}

	/**
	 * teacher report was pressed
	 * 
	 * @param event
	 *            The menu item is clicked
	 */
	public void openTeacherReport(ActionEvent event) {
		setAnchorPanesFalse();
		teacherReportAnchorPane.setVisible(true);
		setTableInTeacherReport();
	}

	/**
	 * create report button was pressed
	 * 
	 * @param event
	 *            The button is clicked
	 */
	public void createReportInTeacherReportHandler(ActionEvent event) {
		Teacher teacher = teacherTableView.getSelectionModel().getSelectedItem();
		if (teacher == null) {
			Utilities_Client.popUpMethod("Please choose teacher first!");
		} else {
			String fullName = teacher.getFirstName() + " " + teacher.getLastName();
			teacherNameLabel.setText(fullName);
			client.handleMessageFromClientUI(new ReportHandle("TeacherStatistic", teacher));
			try {
				TimeUnit.SECONDS.sleep(3);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			averageTextFieldInTeacherReport.setEditable(false);
			medianTextFieldInTeacherReport.setEditable(false);
			createHistogram(teacherBarChart, grades);
			report1AnchorPane.setVisible(true);
		}
	}

	/**
	 * general create Histogram (Course and Teacher)
	 * 
	 * @param barchart
	 *            The histogram to present
	 * @param grades
	 *            The grades
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void createHistogram(BarChart barchart, ArrayList<Integer> grades) {
		barchart.getData().clear();
		int group[] = new int[10];
		XYChart.Series<String, Number> series1 = new XYChart.Series<String, Number>();
		series1.setName("AES7-Histogram");
		barchart.setCategoryGap(3);
		barchart.setBarGap(2);
		barchart.setAnimated(false);
		if (grades.size() != 0) {
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
			barchart.getData().addAll(series1);
		} else
			Utilities_Client.popUpMethod("Theres no grades yet in this course!");
	}

	/**
	 * order Student Exam was pressed
	 * 
	 * @param mouseEvent
	 *            The button is clicked
	 */
	public void orderStudentExam(MouseEvent mouseEvent) {
		if (studentComboBoxInSolvedExams.getValue() != null) {
			if (solvedExamsTableView.getSelectionModel().getSelectedItem() != null) {
				ApprovedExamForStudent selectedExam = solvedExamsTableView.getSelectionModel().getSelectedItem();
				String[] studentID = studentComboBoxInSolvedExams.getValue().split(" ");
				client.handleMessageFromClientUI(Message.getAnswers + " " + studentID[0] + " "
						+ selectedExam.getCheckedExam().getSubmittedExam().getStudentInActiveExam().getActiveExam()
								.getExam().getSubjectID()
						+ " "
						+ selectedExam.getCheckedExam().getSubmittedExam().getStudentInActiveExam().getActiveExam()
								.getExam().getCourseID()
						+ " " + selectedExam.getExamNum() + " " + selectedExam.getExecutionCode() + " " + "false");
				client.handleMessageFromClientUI(Message.getQuestionInExam + " " + selectedExam.getExecutionCode());
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				showExam();
			} else {
				Utilities_Client.popUpMethod("Please select exam");
			}
		} else {
			Utilities_Client.popUpMethod("Please select student");
		}
	}

	/**
	 * return To Exam Table
	 * 
	 * @param mouseEvent
	 *            The button is clicked
	 */
	public void returnToExamTable(MouseEvent mouseEvent) {
		clearSolvedExam();
	}

	/*-----------------------------------private-------------------------*/

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
	 * method to clear the tables and etc at solved exam
	 */
	private void clearSolvedExam() {
		vBoxShowExam.getChildren().clear();
		vBoxShowExam.setVisible(false);
		anchorPaneShowExam.setVisible(false);
	}

	/**
	 * Set subject combo box method
	 * 
	 * @param comboBox
	 *            The subject combo box of the relevant screen
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
		client.handleMessageFromClientUI(Message.getSubjects);
		comboBox.setItems(client.getSubjectsFromDB());
	}

	/**
	 * Set course combo box method
	 * 
	 * @param comboBox
	 *            The course combo box of the relevant screen
	 */
	private void setCourseComboBox(ComboBox<String> comboBox) {
		comboBox.getSelectionModel().clearSelection();
		comboBox.setPromptText("Select Course");
	}

	/**
	 * Set student combo box method
	 * 
	 * @param comboBox
	 *            The student combo box of the relevant screen
	 */
	private void setStudentComboBox(ComboBox<String> comboBox) {
		comboBox.getSelectionModel().clearSelection();
		comboBox.setPromptText("Select Student");
		comboBox.setButtonCell(new ListCell<String>() {
			@Override
			protected void updateItem(String item, boolean empty) {
				super.updateItem(item, empty);
				if (empty || item == null) {
					setText("Select Student");
				} else {
					setText(item);
				}
			}
		});
		client.handleMessageFromClientUI(Message.getAllStudents);
		comboBox.setItems(client.getAllStudentsFromDB());
	}

	/**
	 * set table in questions pool anchor pane
	 */
	private void setTableInQuestionPool() {
		client.getQuestionsFromDB().clear();
		if (subjectComboBoxInQuestionsPool.getValue() != null) {
			client.handleMessageFromClientUI(
					Message.getQuestionBySubject + " " + subjectComboBoxInQuestionsPool.getValue());
			tableViewInQuestionsPool.setItems(client.getQuestionsFromDB());
		} else {
			Utilities_Client.popUpMethod("Please Select Subject");
		}
	}

	/**
	 * set table in exams pool anchor pane
	 */
	private void setTableInExamsPool() {
		client.getExamsFromDB().clear();
		if (courseComboBoxInExamPool.getValue() != null) {
			client.handleMessageFromClientUI(Message.getExamByCourse + " " + courseComboBoxInExamPool.getValue());
			tableViewInExamsPool.setItems(client.getExamsFromDB());
		} else {
			Utilities_Client.popUpMethod("Please Select Course");
		}
	}

	/**
	 * set table in active exams anchor pane
	 */
	private void setTableInActiveExams() {
		client.getActiveExamsBySubject().clear();
		if (courseComboBoxInActiveExams.getValue() != null) {
			client.handleMessageFromClientUI(
					Message.getActiveExamBySubject + " " + courseComboBoxInActiveExams.getValue());
			activeExamTableView.setItems(client.getActiveExamsBySubject());
		} else {
			Utilities_Client.popUpMethod("Please Select Course");
		}
	}

	/**
	 * set table in solved exams anchor pane
	 */
	private void setTableInSolvedExams() {
		if (studentComboBoxInSolvedExams.getValue() != null) {
			client.getSolvedExamOfStudentsDB().clear();
			String[] strStudent = studentComboBoxInSolvedExams.getValue().split(" ");
			Student student = new Student(strStudent[0], strStudent[1], strStudent[2]);
			client.handleMessageFromClientUI(new StudentHandle("SolvedExamsOfStudent", student));
			solvedExamsTableView.setItems(client.getSolvedExamOfStudentsDB());
		} else {
			Utilities_Client.popUpMethod("Please Select Student");
		}
	}

	/**
	 * set table in handling requests anchor pane
	 */
	private void setTableInHandlingRequests() {
		client.getWaitingActiveExamsFromDB().clear();
		client.handleMessageFromClientUI(Message.getWaitingActiveExams);
		handlingRequestsTableView.setItems(client.getWaitingActiveExamsFromDB());
	}

	/**
	 * Set Columns in Questions Table
	 * 
	 * @param subjectID
	 *            Subject ID Column
	 * @param questionNumber
	 *            Question Number Column
	 * @param author
	 *            Author Column
	 * @param questionText
	 *            Question Text Column
	 * @param firstPossibleAnswer
	 *            First Possible Answer Column
	 * @param secondPossibleAnswer
	 *            Second Possible Answer Column
	 * @param thirdPossibleAnswer
	 *            Third Possible Answer Column
	 * @param fourthPossibleAnswer
	 *            Fourth Possible Answer Column
	 * @param correctAnswer
	 *            Correct Answer Column
	 */
	private void setColumnsOfQuestionsTable(TableColumn<Question, String> subjectID,
			TableColumn<Question, String> questionNumber, TableColumn<Question, String> author,
			TableColumn<Question, String> questionText, TableColumn<Question, String> firstPossibleAnswer,
			TableColumn<Question, String> secondPossibleAnswer, TableColumn<Question, String> thirdPossibleAnswer,
			TableColumn<Question, String> fourthPossibleAnswer, TableColumn<Question, String> correctAnswer) {
		subjectID.setCellValueFactory(new PropertyValueFactory<>("subjectID"));
		questionNumber.setCellValueFactory(new PropertyValueFactory<>("questionNum"));
		author.setCellValueFactory(new PropertyValueFactory<>("author"));
		questionText.setCellValueFactory(new PropertyValueFactory<>("questionText"));
		firstPossibleAnswer.setCellValueFactory(new PropertyValueFactory<>("firstPossibleAnswer"));
		secondPossibleAnswer.setCellValueFactory(new PropertyValueFactory<>("secondPossibleAnswer"));
		thirdPossibleAnswer.setCellValueFactory(new PropertyValueFactory<>("thirdPossibleAnswer"));
		fourthPossibleAnswer.setCellValueFactory(new PropertyValueFactory<>("fourthPossibleAnswer"));
		correctAnswer.setCellValueFactory(new PropertyValueFactory<>("correctAnswer"));
	}

	/**
	 * set columns in exams pool
	 */
	private void setColumnsInExamsPool() {
		subjectColInExamsPool.setCellValueFactory(new PropertyValueFactory<>("subjectID"));
		courseColInExamsPool.setCellValueFactory(new PropertyValueFactory<>("courseID"));
		examNumColInExamsPool.setCellValueFactory(new PropertyValueFactory<>("examNum"));
		authorColInExamsPool.setCellValueFactory(new PropertyValueFactory<>("teacherName"));
		durationColInExamsPool.setCellValueFactory(new PropertyValueFactory<>("examDuration"));
		textExamineesColInExamsPool.setCellValueFactory(new PropertyValueFactory<>("freeTextForExaminees"));
		textTeachersColInExamsPool.setCellValueFactory(new PropertyValueFactory<>("freeTextForTeacherOnly"));
	}

	/**
	 * set columns in active exams
	 */
	private void setColumnsInActiveExams() {
		subjectColInActiveExams.setCellValueFactory(
				cellData -> new SimpleStringProperty(cellData.getValue().getExam().getSubjectID()));
		courseColInActiveExams
				.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getExam().getCourseID()));
		examNumColInActiveExams
				.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getExam().getExamNum()));
		executionCodeColInActiveExams.setCellValueFactory(new PropertyValueFactory<>("executionCode"));
		activatorColInActiveExams.setCellValueFactory(new PropertyValueFactory<>("activator"));
		durationColInActiveExams.setCellValueFactory(new PropertyValueFactory<>("duration"));
		lockedColInActiveExams.setCellValueFactory(new PropertyValueFactory<>("locked"));
		typeColInActiveExams.setCellValueFactory(new PropertyValueFactory<>("type"));
	}

	/**
	 * set columns in solved exams
	 */
	private void setColumnsInSolvedExams() {
		subjectColInSolvedExams
				.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCheckedExam()
						.getSubmittedExam().getStudentInActiveExam().getActiveExam().getExam().getSubjectID()));
		courseColInSolvedExams.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()
				.getCheckedExam().getSubmittedExam().getStudentInActiveExam().getActiveExam().getExam().getCourseID()));
		examNumColInSolvedExams.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()
				.getCheckedExam().getSubmittedExam().getStudentInActiveExam().getActiveExam().getExam().getExamNum()));
		executionCodeColInSolvedExams.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()
				.getCheckedExam().getSubmittedExam().getStudentInActiveExam().getActiveExam().getExecutionCode()));
		gradeColInSolvedExams.setCellValueFactory(new PropertyValueFactory<>("finalGrade"));
		commentsColInSolvedExams.setCellValueFactory(new PropertyValueFactory<>("comments"));

	}

	/**
	 * set columns in handling requests
	 */
	private void setColumnsInHandlingRequests() {
		subjectColInHandlingRequests.setCellValueFactory(
				cellData -> new SimpleStringProperty(cellData.getValue().getActiveExam().getExam().getSubjectID()));
		courseColInHandlingRequests.setCellValueFactory(
				cellData -> new SimpleStringProperty(cellData.getValue().getActiveExam().getExam().getCourseID()));
		examNumColInHandlingRequests.setCellValueFactory(
				cellData -> new SimpleStringProperty(cellData.getValue().getActiveExam().getExam().getExamNum()));
		executionCodeColInHandlingRequests.setCellValueFactory(
				cellData -> new SimpleStringProperty(cellData.getValue().getActiveExam().getExecutionCode()));
		durationColInHandlingRequests.setCellValueFactory(
				cellData -> new SimpleStringProperty(cellData.getValue().getActiveExam().getDurationInString()));
		newDurationColInHandlingRequests.setCellValueFactory(new PropertyValueFactory<>("newDuration"));
		reasonColInHandlingRequests.setCellValueFactory(new PropertyValueFactory<>("reason"));
	}

	/**
	 * set columns in student report
	 */
	private void setColInStudentReport() {
		studentIDColInStudentReport.setCellValueFactory(new PropertyValueFactory<>("id"));
		firstNameColInStudentReport.setCellValueFactory(new PropertyValueFactory<>("firstName"));
		lastNameColInStudentReport.setCellValueFactory(new PropertyValueFactory<>("lastName"));
	}

	/**
	 * set columns in course report
	 */
	private void setColInCourseReport() {
		subjectColInStudentReport.setCellValueFactory(new PropertyValueFactory<>("subjectID"));
		courseIDColInStudentReport.setCellValueFactory(new PropertyValueFactory<>("courseID"));
		courseNameColInStudentReport.setCellValueFactory(new PropertyValueFactory<>("courseName"));
	}

	/**
	 * set columns in teacher report
	 */
	private void setColInTeacherReport() {
		teacherIDColInSTeacherReport.setCellValueFactory(new PropertyValueFactory<>("id"));
		firstNameColInTeacherReport.setCellValueFactory(new PropertyValueFactory<>("firstName"));
		lastNameColInTeacherReport.setCellValueFactory(new PropertyValueFactory<>("lastName"));
	}

	/**
	 * set table in student report
	 */
	private void setTableInStudentReport() {
		client.getStudentsFromDB().clear();
		client.handleMessageFromClientUI(Message.getStudents);
		studentsTableView.setItems(client.getStudentsFromDB());
	}

	/**
	 * set table in course report
	 */
	private void setTableInCourseReport() {
		client.getAllCoursesFromDB().clear();
		client.handleMessageFromClientUI(Message.getAllCourses);
		courseTableView.setItems(client.getAllCoursesFromDB());
	}

	/**
	 * set table in teacher report
	 */
	private void setTableInTeacherReport() {
		client.getAllTeachersFromDB().clear();
		client.handleMessageFromClientUI(Message.getAllTeachers);
		teacherTableView.setItems(client.getAllTeachersFromDB());
	}

	/**
	 * initialize grades
	 */
	public void initializeGradesArray() {
		grades = new ArrayList<Integer>();
		grades.add(0);
		grades.add(0);
	}

	/**
	 * set all anchor panes to false
	 */
	private void setAnchorPanesFalse() {
		handlingRequestsAnchorPane.setVisible(false);
		examsPoolAnchorPane.setVisible(false);
		questionsPoolAnchorPane.setVisible(false);
		welcomeAnchorPane.setVisible(false);
		studentReportAnchorPane.setVisible(false);
		report3AnchorPane.setVisible(false);
		courseReportAnchorPane.setVisible(false);
		report2AnchorPane.setVisible(false);
		report1AnchorPane.setVisible(false);
		teacherReportAnchorPane.setVisible(false);
		activeExamAnchorPane.setVisible(false);
		solvedExamsAnchorPane.setVisible(false);
	}

} // end of class