package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import client.Client;
import client.MainAppClient;
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
import javafx.scene.chart.XYChart.Data;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.Stage;
import resources.Course;
import resources.Exam;
import resources.Message;
import resources.Question;
import resources.ReportHandle;
import resources.Student;
import resources.Teacher;
import resources.Utilities_Client;
import resources.WaitingActiveExam;
import resources.WaitingActiveExamHandle;

public class PrincipalWindowController implements Initializable, IScreenController {

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

	// questions pool

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

	// exams pool

	@FXML
	private AnchorPane examsPoolAnchorPane;

	@FXML
	private Label subjectInExamsPoolLabel;

	@FXML
	private ComboBox<String> subjectComboBoxInExamPool;

	@FXML
	private Button showExamsInExamsPool;

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
	private TableView<Exam> tableViewInExamsPool;

	// handling requests

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

	// student report

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

	// course report

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

	// teacher report

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

	/***********************************************************************************************************/

	/* --------------------- setters & getters ---------------------- */

	@Override
	public void setScreenParent(ScreensController screenParent) {
		screensController = screenParent;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setName() {
		name.setText(firstName + " " + lastName);
	}

	public TextField getAverageTextFieldInStudentReport() {
		return averageTextFieldInStudentReport;
	}

	public void setAverageTextFieldInStudentReport(TextField averageTextFieldInStudentReport) {
		this.averageTextFieldInStudentReport = averageTextFieldInStudentReport;
	}

	public void setNameAndLastName(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
		welcomeText.setText(welcomeText.getText() + " " + this.firstName + " " + this.lastName);
	}

	public TextField getAverageTextFieldInCourseReport() {
		return averageTextFieldInCourseReport;
	}

	public void setAverageTextFieldInCourseReport(TextField averageTextFieldInCourseReport) {
		this.averageTextFieldInCourseReport = averageTextFieldInCourseReport;
	}

	public TextField getAverageTextFieldInTeacherReport() {
		return averageTextFieldInTeacherReport;
	}

	public void setAverageTextFieldInTeacherReport(TextField averageTextFieldInTeacherReport) {
		this.averageTextFieldInTeacherReport = averageTextFieldInTeacherReport;
	}

	/*
	 * ---------------------------------- public methods
	 * --------------------------------- *
	 */

	// initialize method

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		date.setText(Utilities_Client.setDate());
		this.client = MainAppClient.getClient();
		client.setPrincipalWindowController(this);
		setColumnsInQuestionsPool();
		setColumnsInExamsPool();
		setColumnsInHandlingRequests();
		setColInStudentReport();
		setColInCourseReport();
		setColInTeacherReport();

	}

	/**
	 * logout handler
	 * 
	 * @param event
	 */
	public void logOutButtonHandler(ActionEvent event) {
		welcomeText.setText("Welcome");
		this.client.handleMessageFromClientUI(Message.logout);
		screensController.setScreen(MainAppClient.loginScreenID);
		setAnchorPanesFalse();
		welcomeAnchorPane.setVisible(true);
	}

	// question tab was pressed

	public void openQuestionPool(ActionEvent event) {
		setAnchorPanesFalse();
		questionsPoolAnchorPane.setVisible(true);
		setSubjectComboBox(subjectComboBoxInQuestionsPool);
	}

	// show question button was clicked

	public void showQuestionHandler(ActionEvent event) {
		setTableInQuestionPool();
	}

	// exam pool tab was pressed

	public void openExamPool(ActionEvent event) {
		setAnchorPanesFalse();
		examsPoolAnchorPane.setVisible(true);
		setSubjectComboBox(subjectComboBoxInExamPool);
	}

	// show exams button was clicked

	public void showExamsHandler(ActionEvent event) {
		setTableInExamsPool();
	}

	// handling requests tab was pressed

	public void openHandlingRequests(ActionEvent event) {
		setAnchorPanesFalse();
		handlingRequestsAnchorPane.setVisible(true);
		setTableInHandlingRequests();
	}

	/**
	 * Approve button was pressed
	 * 
	 * @param event
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
	 */
	public void rejectButtonHandle(ActionEvent event) {
		WaitingActiveExam waitingActiveExam = handlingRequestsTableView.getSelectionModel().getSelectedItem();
		if (waitingActiveExam == null)
			Utilities_Client.popUpMethod("Please choose the request");
		else {
			Label text = new Label("Are you sure?");
			;
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

	// refresh button was pressed

	public void refreshButtonHandle(MouseEvent event) {
		setTableInHandlingRequests();
	}

	// student report tab was pressed

	public void openStudentReport(ActionEvent event) {
		setAnchorPanesFalse();
		studentReportAnchorPane.setVisible(true);
		setTableInStudentReport();
	}

	// create report button was pressed

	public void createReportInStudentReportHandler(ActionEvent event) throws InterruptedException {
		Student student = studentsTableView.getSelectionModel().getSelectedItem();
		if (student == null) {
			Utilities_Client.popUpMethod("Please choose student first!");
		} else {
			String fullName = student.getFirstName() + " " + student.getLastName();
			studentNameLabel.setText(fullName);
			client.handleMessageFromClientUI(new ReportHandle("StudentAverage", student));
			averageTextFieldInStudentReport.setEditable(false);
			createStudentHistogram();
			TimeUnit.SECONDS.sleep(2);
			report3AnchorPane.setVisible(true);
		}
	}

	// course report tab was pressed

	public void openCourseReport(ActionEvent event) {
		setAnchorPanesFalse();
		courseReportAnchorPane.setVisible(true);
		setTableInCourseReport();
	}

	public void createReportInCourseReportHandler(ActionEvent event) {
		Course course = courseTableView.getSelectionModel().getSelectedItem();
		if (course == null) {
			Utilities_Client.popUpMethod("Please choose course first!");
		} else {
			String fullName = course.getCourseName();
			CourseNameLabel.setText(fullName);
			client.handleMessageFromClientUI(new ReportHandle("CourseAverage", course));
			averageTextFieldInCourseReport.setEditable(false);
			report2AnchorPane.setVisible(true);
		}
	}

	public void createStudentHistogram() {
		XYChart.Series<String, Number> series1 = new XYChart.Series<String, Number>();
		series1.setName("AES7-Histogram");
		studentBarChart.setCategoryGap(3);
		studentBarChart.setBarGap(2);
		series1.getData().add(new Data<String, Number>("010101", 90));
		series1.getData().add(new Data<String, Number>("010102", 20));
		studentBarChart.getData().add(series1);
		// studentBarChart.getData().addAll(series1);
	}

	public void openTeacherReport(ActionEvent event) {
		setAnchorPanesFalse();
		teacherReportAnchorPane.setVisible(true);
		setTableInTeacherReport();
	}

	public void createReportInTeacherReportHandler(ActionEvent event) {
		Teacher teacher = teacherTableView.getSelectionModel().getSelectedItem();
		if (teacher == null) {
			Utilities_Client.popUpMethod("Please choose teacher first!");
		} else {
			String fullName = teacher.getFirstName() + " " + teacher.getLastName();
			teacherNameLabel.setText(fullName);
			client.handleMessageFromClientUI(new ReportHandle("TeacherAverage", teacher));
			averageTextFieldInTeacherReport.setEditable(false);
			report1AnchorPane.setVisible(true);
		}
	}

	/*
	 * --------------------------------* private methods * *
	 * -----------------------------------
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

	private void setTableInQuestionPool() {
		client.getQuestionsFromDB().clear();
		if (subjectComboBoxInQuestionsPool.getValue() != null) {
			client.handleMessageFromClientUI(
					Message.getQuestionBySubject + " " + subjectComboBoxInQuestionsPool.getValue());
			tableViewInQuestionsPool.setItems(client.getQuestionsFromDB());
		} else {
			Utilities_Client.popUpMethod("Select Subject");
		}
	}

	private void setTableInExamsPool() {
		client.getExamsFromDB().clear();
		if (subjectComboBoxInExamPool.getValue() != null) {
			client.handleMessageFromClientUI(Message.getExamBySubject + " " + subjectComboBoxInExamPool.getValue());
			tableViewInExamsPool.setItems(client.getExamsFromDB());
		} else {
			Utilities_Client.popUpMethod("Select Subject");
		}
	}

	private void setTableInHandlingRequests() {
		client.getWaitingActiveExamsFromDB().clear();
		client.handleMessageFromClientUI(Message.getWaitingActiveExams);
		handlingRequestsTableView.setItems(client.getWaitingActiveExamsFromDB());
	}

	private void setColumnsInQuestionsPool() {
		subjectIDColumnInQuestionsPool.setCellValueFactory(new PropertyValueFactory<>("subjectID"));
		questionNumColumnInQuestionsPool.setCellValueFactory(new PropertyValueFactory<>("questionNum"));
		authorColumnInQuestionsPool.setCellValueFactory(new PropertyValueFactory<>("author"));
		questionTextColumnInQuestionsPool.setCellValueFactory(new PropertyValueFactory<>("questionText"));
		firstPossibleAnswerColumnInQuestionsPool.setCellValueFactory(new PropertyValueFactory<>("firstPossibleAnswer"));
		secondPossibleAnswerColumnInQuestionsPool
				.setCellValueFactory(new PropertyValueFactory<>("secondPossibleAnswer"));
		thirdPossibleAnswerColumnInQuestionsPool.setCellValueFactory(new PropertyValueFactory<>("thirdPossibleAnswer"));
		fourthPossibleAnswerColumnInQuestionsPool
				.setCellValueFactory(new PropertyValueFactory<>("fourthPossibleAnswer"));
		correctAnswerColumnInQuestionsPool.setCellValueFactory(new PropertyValueFactory<>("correctAnswer"));
	}

	private void setColumnsInExamsPool() {
		subjectColInExamsPool.setCellValueFactory(new PropertyValueFactory<>("subjectID"));
		courseColInExamsPool.setCellValueFactory(new PropertyValueFactory<>("courseID"));
		examNumColInExamsPool.setCellValueFactory(new PropertyValueFactory<>("examNum"));
		authorColInExamsPool.setCellValueFactory(new PropertyValueFactory<>("teacherName"));
		durationColInExamsPool.setCellValueFactory(new PropertyValueFactory<>("examDuration"));
		textExamineesColInExamsPool.setCellValueFactory(new PropertyValueFactory<>("freeTextForExaminees"));
		textTeachersColInExamsPool.setCellValueFactory(new PropertyValueFactory<>("freeTextForTeacherOnly"));
	}

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

	private void setColInStudentReport() {
		studentIDColInStudentReport.setCellValueFactory(new PropertyValueFactory<>("id"));
		firstNameColInStudentReport.setCellValueFactory(new PropertyValueFactory<>("firstName"));
		lastNameColInStudentReport.setCellValueFactory(new PropertyValueFactory<>("lastName"));
	}

	private void setColInCourseReport() {
		subjectColInStudentReport.setCellValueFactory(new PropertyValueFactory<>("subjectID"));
		courseIDColInStudentReport.setCellValueFactory(new PropertyValueFactory<>("courseID"));
		courseNameColInStudentReport.setCellValueFactory(new PropertyValueFactory<>("courseName"));
	}

	private void setColInTeacherReport() {
		teacherIDColInSTeacherReport.setCellValueFactory(new PropertyValueFactory<>("id"));
		firstNameColInTeacherReport.setCellValueFactory(new PropertyValueFactory<>("firstName"));
		lastNameColInTeacherReport.setCellValueFactory(new PropertyValueFactory<>("lastName"));
	}

	private void setTableInStudentReport() {
		client.getStudentsFromDB().clear();
		client.handleMessageFromClientUI(Message.getStudents);
		studentsTableView.setItems(client.getStudentsFromDB());
	}

	private void setTableInCourseReport() {
		client.getAllCoursesFromDB().clear();
		client.handleMessageFromClientUI(Message.getAllCourses);
		courseTableView.setItems(client.getAllCoursesFromDB());
	}

	private void setTableInTeacherReport() {
		client.getAllTeachersFromDB().clear();
		client.handleMessageFromClientUI(Message.getAllTeachers);
		teacherTableView.setItems(client.getAllTeachersFromDB());
	}

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
	}
}
