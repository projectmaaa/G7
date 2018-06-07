package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import client.Client;
import client.MainAppClient;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import resources.Exam;
import resources.Message;
import resources.Question;
import resources.Utilities;
import resources.WaitingActiveExam;

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
	
	//handling requests
	
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

	/* --------------------- setters & getters ---------------------- */

	public void setName() {
		name.setText(firstName + " " + lastName);
	}

	/* --------------------- public methods ---------------------- */

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		date.setText(Utilities.setDate());
		this.client = MainAppClient.getClient();
		client.setPrincipalWindowController(this);
		setColumnsInQuestionsPool();
		setColumnsInExamsPool();
		setColumnsInHandlingRequests();
	}

	/**
	 * 
	 */
	public void setNameAndLastName(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
		welcomeText.setText(welcomeText.getText() + " " + this.firstName + " " + this.lastName);
	}

	/**
	 * 
	 * @param event
	 */

	public void logOutButtonHandler(ActionEvent event) {
		welcomeText.setText("Welcome");
		welcomeAnchorPane.setVisible(true);
		this.client.handleMessageFromClientUI(Message.logout);
		screensController.setScreen(MainAppClient.loginScreenID);
	}

	// question tab was pressed

	public void openQuestionPool(ActionEvent event) {
		questionsPoolAnchorPane.setVisible(true);
		welcomeAnchorPane.setVisible(false);
		examsPoolAnchorPane.setVisible(false);
		handlingRequestsAnchorPane.setVisible(false);
		setSubjectComboBox(subjectComboBoxInQuestionsPool);
	}

	// show question button was clicked

	public void showQuestionHandler(ActionEvent event) {
		setTableInQuestionPool();
	}
	
	// exam pool tab was pressed

	public void openExamPool(ActionEvent event) {
		examsPoolAnchorPane.setVisible(true);
		questionsPoolAnchorPane.setVisible(false);
		welcomeAnchorPane.setVisible(false);
		handlingRequestsAnchorPane.setVisible(false);
		setSubjectComboBox(subjectComboBoxInExamPool);
	}
	
	// show exams button was clicked

	public void showExamsHandler(ActionEvent event) {
		setTableInExamsPool();
		}
	
	// handling requests tab was pressed
	
	public void openHandlingRequests(ActionEvent event) {
		handlingRequestsAnchorPane.setVisible(true);
		examsPoolAnchorPane.setVisible(false);
		questionsPoolAnchorPane.setVisible(false);
		welcomeAnchorPane.setVisible(false);
		setTableInHandlingRequests();
	}

	/* --------------------- private methods ---------------------- */

	private void setSubjectComboBox(ComboBox<String> comboBox) {
		comboBox.getSelectionModel().clearSelection();
		comboBox.setPromptText("Select Subject");
		comboBox.getItems().addAll("Software", "Math", "Physics");
	}

	private void setTableInQuestionPool() {
		client.getQuestionsFromDB().clear();
		client.handleMessageFromClientUI(
				Message.getQuestionBySubject + " " + subjectComboBoxInQuestionsPool.getValue());
		tableViewInQuestionsPool.setItems(client.getQuestionsFromDB());
	}
	
	private void setTableInExamsPool() {
		client.getExamsFromDB().clear();
		client.handleMessageFromClientUI(
				Message.getExamBySubject + " " + subjectComboBoxInExamPool.getValue());
		tableViewInExamsPool.setItems(client.getExamsFromDB());
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
		textExamineesColInExamsPool
				.setCellValueFactory(new PropertyValueFactory<>("freeTextForExaminees"));
		textTeachersColInExamsPool.setCellValueFactory(new PropertyValueFactory<>("freeTextForTeacherOnly"));

	}
	
	private void setColumnsInHandlingRequests() {
		
		subjectColInHandlingRequests.setCellValueFactory(cellData -> 
        new SimpleStringProperty(cellData.getValue().getActiveExam().getExam().getSubjectID()));
		courseColInHandlingRequests.setCellValueFactory(cellData -> 
        new SimpleStringProperty(cellData.getValue().getActiveExam().getExam().getCourseID()));
		examNumColInHandlingRequests.setCellValueFactory(cellData -> 
        new SimpleStringProperty(cellData.getValue().getActiveExam().getExam().getExamNum()));
		executionCodeColInHandlingRequests.setCellValueFactory(cellData -> 
        new SimpleStringProperty(cellData.getValue().getActiveExam().getExecutionCode()));
		durationColInExamsPool.setCellValueFactory(new PropertyValueFactory<>("originalDuration"));
		newDurationColInHandlingRequests
				.setCellValueFactory(new PropertyValueFactory<>("newDuration"));
		reasonColInHandlingRequests.setCellValueFactory(new PropertyValueFactory<>("reason"));

	}
}
