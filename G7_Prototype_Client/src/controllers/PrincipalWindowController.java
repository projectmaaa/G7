package controllers;

import java.net.URL;
import java.util.ArrayList;
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
import resources.ActiveExam;
import resources.ApprovedExamForStudent;
import resources.Course;
import resources.Exam;
import resources.Message;
import resources.Question;
import resources.ReportHandle;
import resources.Student;
import resources.StudentHandle;
import resources.Teacher;
import resources.Utilities_Client;
import resources.WaitingActiveExam;
import resources.WaitingActiveExamHandle;

public class PrincipalWindowController implements Initializable, IScreenController {

	/*
	 * general attributes
	 */

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

	/**
	 * questions pool attributes
	 */

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

	/**
	 * exams pool attributes
	 */

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

	/**
	 * active exams attributes
	 */

	@FXML
	private AnchorPane activeExamAnchorPane;

	@FXML
	private ComboBox<String> subjectComboBoxInActiveExams;

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

	/**
	 * solved exams attributes
	 */

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

	/**
	 * handling requests attributes
	 */

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

	/**
	 * student report attributes
	 */

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

	/**
	 * course report attributes
	 */

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

	/**
	 * teacher report attributes
	 */

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

	public ArrayList<Integer> getGrades() {
		return grades;
	}

	public void setGrades(ArrayList<Integer> grades) {
		this.grades = grades;
	}

	public TextField getMedianTextFieldInStudentReport() {
		return medianTextFieldInStudentReport;
	}

	public void setMedianTextFieldInStudentReport(TextField medianTextFieldInStudentReport) {
		this.medianTextFieldInStudentReport = medianTextFieldInStudentReport;
	}

	public TextField getMedianTextFieldInCourseReport() {
		return medianTextFieldInCourseReport;
	}

	public void setMedianTextFieldInCourseReport(TextField medianTextFieldInCourseReport) {
		this.medianTextFieldInCourseReport = medianTextFieldInCourseReport;
	}

	public TextField getMedianTextFieldInTeacherReport() {
		return medianTextFieldInTeacherReport;
	}

	public void setMedianTextFieldInTeacherReport(TextField medianTextFieldInTeacherReport) {
		this.medianTextFieldInTeacherReport = medianTextFieldInTeacherReport;
	}

	/*
	 * ---------------------------------- public methods
	 * --------------------------------- *
	 */

	/**
	 * initialize method
	 */

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
		setColumnsInActiveExams();
		setColumnsInSolvedExams();
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
		tableViewInQuestionsPool.getItems().clear();
		setAnchorPanesFalse();
		questionsPoolAnchorPane.setVisible(true);
		setSubjectComboBox(subjectComboBoxInQuestionsPool);
	}

	/**
	 * show question button was clicked
	 * 
	 * @param event
	 */

	public void showQuestionHandler(ActionEvent event) {
		setTableInQuestionPool();
	}

	/**
	 * exam pool tab was pressed
	 * 
	 * @param event
	 */

	public void openExamPool(ActionEvent event) {
		tableViewInExamsPool.getItems().clear();
		setAnchorPanesFalse();
		examsPoolAnchorPane.setVisible(true);
		setSubjectComboBox(subjectComboBoxInExamPool);
	}

	/**
	 * show exams button was clicked
	 * 
	 * @param event
	 */

	public void showExamsHandler(ActionEvent event) {
		setTableInExamsPool();
	}

	/**
	 * active exams was pressed
	 * 
	 * @param event
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
	 */

	public void showActiveExamButtonHandler(ActionEvent event) {
		setTableInActiveExams();
	}

	/**
	 * solved exams tab pressed
	 * 
	 * @param event
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
	 */

	public void showButtonHandler(ActionEvent event) {
		setTableInSolvedExams();
	}

	/**
	 * show solved exam button was pressed
	 * 
	 * @param event
	 */

	public void showSolvedExamButtonHandler(ActionEvent event) {
		// alex
	}

	/**
	 * handling requests tab was pressed
	 * 
	 * @param event
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
	 */

	public void refreshButtonHandle(MouseEvent event) {
		setTableInHandlingRequests();
	}

	/**
	 * student report tab was pressed
	 * 
	 * @param event
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
			createStudentHistogram();
			// TimeUnit.SECONDS.sleep(6);
			report3AnchorPane.setVisible(true);
		}
	}

	/**
	 * course report tab was pressed
	 * 
	 * @param event
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
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(grades);
			averageTextFieldInCourseReport.setEditable(false);
			createHistogram(courseReportBarChart, grades);
			report2AnchorPane.setVisible(true);
		}
	}

	public void createStudentHistogram() {
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
		series1.getData().add(new XYChart.Data("010101", 90));
		series1.getData().add(new XYChart.Data("010102", 20));
		studentBarChart.getData().addAll(series1);
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
			client.handleMessageFromClientUI(new ReportHandle("TeacherStatistic", teacher));
			averageTextFieldInTeacherReport.setEditable(false);
			createHistogram(teacherBarChart, grades);
			report1AnchorPane.setVisible(true);
		}
	}

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
//			try {
//				TimeUnit.SECONDS.sleep(3);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
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


	/*-----------------------------------private-------------------------*/
	/**
	 * set subject combobox method
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
		client.handleMessageFromClientUI(Message.getSubjects);
		comboBox.setItems(client.getSubjectsFromDB());
	}

	/**
	 * set student combobox method
	 * 
	 * @param comboBox
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
			Utilities_Client.popUpMethod("Select Subject");
		}
	}

	/**
	 * set table in exams pool anchor pane
	 */

	private void setTableInExamsPool() {
		client.getExamsFromDB().clear();
		if (subjectComboBoxInExamPool.getValue() != null) {
			client.handleMessageFromClientUI(Message.getExamByCourse + " " + subjectComboBoxInExamPool.getValue());
			tableViewInExamsPool.setItems(client.getExamsFromDB());
		} else {
			Utilities_Client.popUpMethod("Select Subject");
		}
	}

	/**
	 * set table in active exams anchor pane
	 */

	private void setTableInActiveExams() {
		client.getActiveExamsBySubject().clear();
		if (subjectComboBoxInActiveExams.getValue() != null) {
			client.handleMessageFromClientUI(
					Message.getActiveExamBySubject + " " + subjectComboBoxInActiveExams.getValue());
			activeExamTableView.setItems(client.getActiveExamsBySubject());
		} else {
			Utilities_Client.popUpMethod("Select Subject");
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
			Utilities_Client.popUpMethod("Select Student");
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
	 * set columns in questions pool
	 */

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
	}
}
