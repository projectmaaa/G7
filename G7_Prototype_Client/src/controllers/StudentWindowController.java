package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import boundaries.QuestionInComputerizeExam;
import client.Client;
import client.MainAppClient;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Duration;
import resources.*;

/**
 * This class represents controller for Student Window (Gui).
 * 
 * @author Group 7
 *
 */
public class StudentWindowController implements Initializable, IScreenController {

	/******************** Attributes ********************/

	private ScreensController screensController;

	private String firstName;

	private String lastName;

	private Client client;

	private ActiveExam activeExam;

	@FXML
	private AnchorPane mainAnchorPane;

	@FXML
	private Button logoutButton;

	@FXML
	private AnchorPane welcomeAnchorPane;

	@FXML
	private Text welcomeText;

	@FXML
	private Text date;

	@FXML
	private Text name;

	@FXML
	private AnchorPane aesAnchorPane;

	@FXML
	private MenuBar menuBar;

	/*********************************************************/

	@FXML
	private AnchorPane examAnchorPane;

	@FXML
	private TextField executionCodeTextField;

	/*********************************************************/

	@FXML
	private AnchorPane computerizeExamPane;

	@FXML
	private TextField examIDTextField;

	@FXML
	private Button enterIDComputerizeExamButton;

	@FXML
	private VBox examSheetVBox;

	@FXML
	private Button submitExamButton;

	private ArrayList<QuestionInComputerizeExam> questionInComputerizeExamArray;

	@FXML
	private ScrollPane computrizedScrollPane;

	@FXML
	private Button uploadManualExam;

	@FXML
	private TextArea commentsTextArea;

	/*********************************************************/

	private StudentInActiveExam studentInActiveExam;

	private SubmittedExam submittedExam;

	/*********************************************************/

	@FXML
	private AnchorPane timerPane;

	@FXML
	private Text timerDisplay;

	private int secondTimer;

	private Timeline stopWatchTimeline;

	/*********************************************************/

	@FXML
	private AnchorPane checkedExamAnchorPane;

	@FXML
	private TableView<ApprovedExamForStudent> tableViewCheckedExam;

	@FXML
	private TableColumn<ApprovedExamForStudent, String> examNumberColInCheckedExam;

	@FXML
	private TableColumn<ApprovedExamForStudent, String> executionCodeColInCheckedExam;

	@FXML
	private TableColumn<ApprovedExamForStudent, Integer> gradeColInCheckedExam;

	@FXML
	private TableColumn<ApprovedExamForStudent, String> generalCommentsColInCheckedExam;

	@FXML
	private ComboBox<String> subjectComboBoxCheckedExam;

	@FXML
	private ComboBox<String> courseComboBoxCheckedBox;

	/*********************************************************/

	@FXML
	private AnchorPane anchorPaneShowExam;

	@FXML
	private ScrollPane scrollPaneShowExam;

	@FXML
	private VBox vBoxShowExam;

	@FXML
	private TextField gradeTextField;

	@FXML
	private Button okButtonShowExam;

	/*********************************************************/

	/******************** Initialization ********************/

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		date.setText(Utilities_Client.setDate());
		this.client = MainAppClient.getClient();
		client.setStudentWindowController(this);
		questionInComputerizeExamArray = new ArrayList<QuestionInComputerizeExam>();
		setSubjectComboBox(subjectComboBoxCheckedExam);
		setColumnsInCheckedExams();
		submitExamButton.setDisable(true);
		uploadManualExam.setDisable(true);
	}

	/******************** Getters and Setters ********************/

	@Override
	public void setScreenParent(ScreensController screenParent) {
		screensController = screenParent;
	}

	/**
	 * 
	 * @return String firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * 
	 * @param firstName
	 *            The first name
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * 
	 * @return String lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * 
	 * @param lastName
	 *            The last name
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * 
	 * @return int secondTimer
	 */
	public int getSecondTimer() {
		return secondTimer;
	}

	/**
	 * Changes the time of exam.
	 * 
	 * @param secondTimer
	 *            The timer
	 */
	public void setSecondTimer(int secondTimer) {
		this.secondTimer = secondTimer;
	}

	/**
	 * 
	 * @return ActiveExam activeExam
	 */
	public ActiveExam getActiveExam() {
		return activeExam;
	}

	/**
	 * 
	 * @param activeExam
	 *            The active exam
	 */
	public void setActiveExam(ActiveExam activeExam) {
		this.activeExam = activeExam;
	}

	/**
	 * Sets the name of the student in this controller
	 */
	public void setName() {
		name.setText(firstName + " " + lastName);
	}

	/******************** Methods ********************/

	/**
	 * Changing the timer based on the value at secondTimer
	 */
	public void changeSecondTimer() {
		int time = activeExam.getDuration() * 60;
		if (time - activeExam.getExam().getExamDuration() * 60 > 0) {
			this.secondTimer += (time - activeExam.getExam().getExamDuration() * 60);
		} else {
			int subTime = activeExam.getExam().getExamDuration() * 60 - time;
			if (secondTimer / 60 < activeExam.getExam().getExamDuration() - activeExam.getDuration()) {
				secondTimer = 0;
				setTimerDisplay();
			} else {
				secondTimer -= subTime;
			}
		}
		System.out.println(secondTimer);
	}

	/**
	 * Defines the steps at login out
	 * 
	 * @param event
	 *            The button is clicked
	 */
	public void logOutButtonHandler(MouseEvent event) {
		if (!submitExamButton.isDisabled() || !uploadManualExam.isDisabled())
			checkRunningExam();
		else { // if the student is in the middle of the exam and pressed 'No' in the submission
				// pop up than he\she wont exit the application
			if (stopWatchTimeline != null)
				stopWatchTimeline.stop();
			if (this.examIDTextField.isVisible()) {
				examIDTextField.clear();
				examIDTextField.setDisable(false);
				enterIDComputerizeExamButton.setDisable(false);
			}
			if (submitExamButton.isVisible())
				submitExamButton.setVisible(false);
			if (uploadManualExam.isVisible())
				uploadManualExam.setVisible(false);
			if (!examSheetVBox.getChildren().isEmpty()) {
				System.out.println("not empty");
				examSheetVBox.getChildren().clear();
			}
			turnOffAllPane();
			aesAnchorPane.setVisible(true);
			welcomeAnchorPane.setVisible(true);
			this.client.handleMessageFromClientUI(Message.logout);
			screensController.setScreen(MainAppClient.loginScreenID);
		}
	}

	/**
	 * Open exam window
	 * 
	 * @param event
	 *            The menu item is clicked
	 */
	public void openExamHandler(ActionEvent event) {
		if (!submitExamButton.isDisabled() || !uploadManualExam.isDisabled())
			checkRunningExam();
		else {
			clearOrderExam();
			turnOffAllPane();
			examAnchorPane.setVisible(true);
			welcomeAnchorPane.setVisible(true);
		}
	}

	/**
	 * Checking if the execution code is null
	 */
	public void checkExecutionCodeForNull() {
		if (activeExam == null) {
			Utilities_Client.popUpMethod("Wrong Code");
		} else {
			if (!activeExam.isLocked()) {
				setTimer();
				checkExamType();
			} else {
				Utilities_Client.popUpMethod("Exam Locked");
			}
		}
	}

	/**
	 * Ordering exam based on the selected information at the gui
	 * 
	 * @param mouseEvent
	 *            The button is clicked
	 */
	public void orderExam(MouseEvent mouseEvent) {
		if (tableViewCheckedExam.getSelectionModel().getSelectedItem() != null) {
			ApprovedExamForStudent selectedExam = tableViewCheckedExam.getSelectionModel().getSelectedItem();
			client.handleMessageFromClientUI(Message.getAnswers + " " + client.getId() + " "
					+ subjectComboBoxCheckedExam.getValue() + " " + courseComboBoxCheckedBox.getValue() + " "
					+ selectedExam.getExamNum() + " " + selectedExam.getExecutionCode() + " " + "true");
			client.handleMessageFromClientUI(Message.getQuestionInExam + " " + selectedExam.getExecutionCode());
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			gradeTextField.setEditable(false);
			gradeTextField.setText(Integer.toString(selectedExam.getFinalGrade()));
			showExam();
		} else {
			Utilities_Client.popUpMethod("Please select exam");
		}
	}

	/**
	 * when the user clicked on 'enter' after entering his\hers ID
	 * 
	 * @param e
	 *            The button is clicked
	 */
	public void enterIsClickedInEnterID(MouseEvent e) {
		checkStudentID();
	}

	/**
	 * Returns from the exam window to the view of checked exams
	 * 
	 * @param mouseEvent
	 *            The button is clicked
	 */
	public void returnToTableView(MouseEvent mouseEvent) {
		vBoxShowExam.getChildren().clear();
		gradeTextField.clear();
		vBoxShowExam.setVisible(false);
		anchorPaneShowExam.setVisible(false);
		checkedExamAnchorPane.setVisible(true);
	}

	/**
	 * When the user pressed enter key after entering his\hers ID
	 * 
	 * @param e
	 *            Enter is pressed
	 */
	public void enterIDKeyHandler(KeyEvent e) {
		KeyCode code = e.getCode();
		if (code == KeyCode.ENTER)
			checkStudentID();
	}

	/**
	 * Doesn't let the student to do an exam with wrong execution code
	 */
	public void checkExecutionCode() {
		String code = executionCodeTextField.getText();
		if (!code.isEmpty()) {
			client.handleMessageFromClientUI(Message.getExecutionCode + " " + code);
			try {
				TimeUnit.SECONDS.sleep(3);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			checkExecutionCodeForNull();
		} else {
			Utilities_Client.popUpMethod("Please Enter Execution Code");
		}
		executionCodeTextField.clear();
	}

	/**
	 * Showing solved exam for the student
	 */
	private void showExam() {
		int index = 0;
		turnOffAllPane();
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
	 * Checks if the inserted student ID is the same as the connected user
	 */
	private void checkStudentID() {
		if (!examIDTextField.getText().isEmpty()) {
			if (examIDTextField.getText().equals(client.getId())) {
				startTimer();
				studentInActiveExam = new StudentInActiveExam(new Student(client.getId(), firstName, lastName),
						activeExam);
				client.handleMessageFromClientUI(
						new StudentInActiveExamHandle(Message.studentInActiveExam, studentInActiveExam));
				enterIDComputerizeExamButton.setDisable(true);
				examIDTextField.setDisable(true);
				examSheetVBox.getChildren().clear();
				computerizeExam();
			} else {
				Utilities_Client.popUpMethod("Wrong ID");
				examIDTextField.clear();
			}
		} else {
			Utilities_Client.popUpMethod("Please Enter ID");
		}
	}

	/**
	 * Checking the type of the exam and on that action
	 */
	private void checkExamType() {
		setComputerizeExam();
		if (activeExam.getType().equals("c")) {
			if (!examIDTextField.getText().isEmpty())
				examIDTextField.clear();
			examIDTextField.setVisible(true);
			examIDTextField.setDisable(false);
			enterIDComputerizeExamButton.setVisible(true);
			enterIDComputerizeExamButton.setDisable(false);
			System.out.println("c");
		} else {
			System.out.println("m");
			studentInActiveExam = new StudentInActiveExam(new Student(client.getId(), firstName, lastName), activeExam);
			client.handleMessageFromClientUI(
					new StudentInActiveExamHandle(Message.studentInActiveExam, studentInActiveExam));
			manualExam();
		}
	}

	/**
	 * Submitting exam, stopping the timer
	 * 
	 * @param mouseEvent
	 *            The button is clicked
	 */
	public void sumbitExam(MouseEvent mouseEvent) {
		submittedExam = new SubmittedExam(activeExam.getDuration() - secondTimer / 60, studentInActiveExam);
		int num = 0;
		for (QuestionInComputerizeExam questionInComputerizeExam : questionInComputerizeExamArray) {
			if (questionInComputerizeExam.getToggleGroup().getSelectedToggle() != null) {
				submittedExam.addAnswer(new StudentAnswerInQuestion(activeExam.getExam().getSubjectID(),
						questionInComputerizeExam.getQuestionInExam().getQuestionNum(), Integer.toString(++num),
						questionInComputerizeExam.getToggleGroup().getSelectedToggle().getUserData().toString(),
						studentInActiveExam.getStudent()));
			} else {
				Utilities_Client.popUpMethod("Question : " + Integer.toString(++num) + " No Answer.");
				submittedExam.getAnswers().clear();
				return;
			}
		}
		stopWatchTimeline.stop();
		Utilities_Client.popUpMethod("Exam was submitted succesfully");
		submitExamButton.setDisable(true);
		submittedExam.setSubmitted(1);
		commentsTextArea.clear();
		commentsTextArea.setVisible(false);
		client.handleMessageFromClientUI(new SubmittedExamHandle(Message.submittedExam, submittedExam));
		if (!submittedExam.getAnswers().isEmpty()) {
			System.out.println(submittedExam);
		}
	}

	/**
	 * Updates the table view based on the selected combo boxes
	 * 
	 * @param mouseEvent
	 *            The button is clicked
	 */
	public void updateCheckExams(MouseEvent mouseEvent) {
		String selectedSubject = subjectComboBoxCheckedExam.getValue();
		if (selectedSubject == null) {
			Utilities_Client.popUpMethod("You must select the subjec");
			return;
		}
		String selectedCourse = courseComboBoxCheckedBox.getValue();
		if (selectedCourse == null) {
			Utilities_Client.popUpMethod("You must select the course");
			return;
		}
		if (!client.getCheckedExamsFromDB().isEmpty()) {
			client.getCheckedExamsFromDB().clear();
		}
		client.handleMessageFromClientUI(Message.getApprovedExamForStudent + " " + client.getId() + " "
				+ selectedSubject + " " + selectedCourse);
		tableViewCheckedExam.setItems(client.getApprovedExamForStudentsDB());
	}

	/**
	 * Checking if the student is still taking the exam
	 * 
	 * @param actionEvent
	 *            The menu item is clicked
	 */
	public void turnCheckExamAnchorPane(ActionEvent actionEvent) {
		if (!submitExamButton.isDisabled() || !uploadManualExam.isDisabled())
			checkRunningExam();
		else {
			turnOffAllPane();
			welcomeAnchorPane.setVisible(true);
			checkedExamAnchorPane.setVisible(true);
		}
	}

	/**
	 * Uploads manual exam
	 * 
	 * @param mouseEvent
	 *            The button is clicked
	 */
	public void uploadManualExam(MouseEvent mouseEvent) {
		stopWatchTimeline.stop();
		submittedExam = new SubmittedExam(activeExam.getDuration() - secondTimer / 60, studentInActiveExam);
		client.handleMessageFromClientUI(new MyFileHandle("UploadExam",
				Utilities_Client.getWordFile(activeExam.getExecutionCode(), studentInActiveExam.getStudent().getId())));
		uploadManualExam.setDisable(true);
		Utilities_Client.popUpMethod("Exam Uploaded Successfully");
		submittedExam.setSubmitted(1);
		client.handleMessageFromClientUI(new SubmittedExamHandle(Message.submittedExam, submittedExam));
	}

	/**
	 * Open computerized exam window
	 * 
	 * @param event
	 *            The button is clicked
	 */
	public void openComputerizedExamHandler(ActionEvent event) {
		turnOffAllPane();
		examAnchorPane.setVisible(true);
	}

	/**
	 * Sets the time at the timer
	 */
	public void setTimer() {
		secondTimer = activeExam.getDuration() * 60;
		timerDisplay.setText(
				String.format("%02d:%02d:%02d", secondTimer / 3600, (secondTimer % 3600) / 60, secondTimer % 60));
	}

	/**
	 * when the user clicked on 'enter' after entering the execution code
	 * 
	 * @param e
	 *            The button is clicked
	 */
	public void enterIsClickedInExecuteExam(MouseEvent e) {
		checkExecutionCode();
	}

	/**
	 * When the user pressed enter key after entering the execution code
	 * 
	 * @param e
	 *            Enter is pressed
	 */
	public void executionCodeKeyHandler(KeyEvent e) {
		KeyCode code = e.getCode();
		if (code == KeyCode.ENTER)
			checkExecutionCode();
	}

	/**
	 * Sets the window at computerize exam
	 */
	public void setComputerizeExam() {
		turnOffAllPane();
		computerizeExamPane.setVisible(true);
		welcomeAnchorPane.setVisible(true);
		timerPane.setVisible(true);
	}

	/**
	 * Prevents from the user to select course if he\she didn't select a subject
	 * 
	 * @param event
	 *            The combo box is clicked
	 */
	public void selectCourseComboBoxHandler(MouseEvent event) {
		String selectedSubject = subjectComboBoxCheckedExam.getValue();
		if (selectedSubject == null) {
			Utilities_Client.popUpMethod("You must select the subject first");
			return;
		} else {
			client.handleMessageFromClientUI(Message.getCourses + " " + selectedSubject);
			courseComboBoxCheckedBox.getItems().clear();
			courseComboBoxCheckedBox.setItems(client.getCoursesFromDB()); // sets the courses that is under specific //
																			// subject
			courseComboBoxCheckedBox.setPromptText("Select Course");
			courseComboBoxCheckedBox.setButtonCell(new ListCell<String>() {
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
	}

	/**
	 * Checking if the student want to change screen during exam
	 * 
	 * @param event
	 *            One of the menu items or log out button is clicked
	 */
	public void checkRunningExamInTheMenuBar(ActionEvent event) {
		checkRunningExam();
	}

	/**
	 * Initialize the combo box based on the data
	 * 
	 * @param comboBox
	 *            The subject combo box
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
	 * Builds the computerize exam
	 */
	private void computerizeExam() {
		submitExamButton.setVisible(true);
		submitExamButton.setDisable(false);
		computrizedScrollPane.setVisible(true);
		commentsTextArea.setText(activeExam.getExam().getFreeTextForExaminees());
		commentsTextArea.setVisible(true);
		if (!questionInComputerizeExamArray.isEmpty())
			questionInComputerizeExamArray.clear();
		int index = 0;
		for (QuestionInExam questionInExam : activeExam.getExam().getQuestions()) {
			QuestionInComputerizeExam questionInComputerizeExam = new QuestionInComputerizeExam(
					Integer.toString(++index) + ". " + questionInExam.getQuestion().getQuestionText(),
					questionInExam.getQuestion().getFirstPossibleAnswer(),
					questionInExam.getQuestion().getSecondPossibleAnswer(),
					questionInExam.getQuestion().getThirdPossibleAnswer(),
					questionInExam.getQuestion().getFourthPossibleAnswer(), questionInExam);
			questionInComputerizeExamArray.add(questionInComputerizeExam);
			examSheetVBox.getChildren().addAll(questionInComputerizeExam.getList());
			examSheetVBox.getChildren().add(new Text(""));
		}
	}

	/**
	 * Sets the window at the manual exam
	 */
	private void manualExam() {
		enterIDComputerizeExamButton.setVisible(false);
		examIDTextField.setVisible(false);
		uploadManualExam.setVisible(true);
		uploadManualExam.setDisable(false);
		submitExamButton.setVisible(false);
		startTimer();
		client.handleMessageFromClientUI(new ActiveExamHandle("#ManualExam", activeExam, client.getId()));
	}

	/**
	 * Sets the timer text
	 */
	private void setTimerDisplay() {
		timerDisplay.setText(
				String.format("%02d:%02d:%02d", secondTimer / 3600, (secondTimer % 3600) / 60, secondTimer % 60));
	}

	/**
	 * Creating Timeline for exam timer, as well if the time ends and close and
	 * submitting the exam
	 */
	private void startTimer() {
		stopWatchTimeline = new Timeline(new KeyFrame(Duration.seconds(1), (ActionEvent event) -> {
			if (secondTimer-- > 0) {
				setTimerDisplay();
			} else {
				Utilities_Client.popUpMethod("Time is over exam, will be submited");
				stopWatchTimeline.stop();
				if (activeExam.getType().equals("c")) {
					unWantedComputerizeSubmitExam();
				} else {
					unWantedManualSubmitExam();
				}

			}
		}));
		stopWatchTimeline.setCycleCount(Timeline.INDEFINITE);
		stopWatchTimeline.play();
	}

	/**
	 * Sets columns in the table view
	 */
	private void setColumnsInCheckedExams() {
		examNumberColInCheckedExam.setCellValueFactory(new PropertyValueFactory<>("examNum"));
		executionCodeColInCheckedExam.setCellValueFactory(new PropertyValueFactory<>("executionCode"));
		gradeColInCheckedExam.setCellValueFactory(new PropertyValueFactory<>("finalGrade"));
		generalCommentsColInCheckedExam.setCellValueFactory(new PropertyValueFactory<>("generalComments"));
	}

	/**
	 * Action for unwanted computerize submit exam
	 */
	private void unWantedComputerizeSubmitExam() {
		this.submitExamButton.setDisable(true);
		submittedExam = new SubmittedExam(activeExam.getDuration() - secondTimer / 60, studentInActiveExam);
		int num = 0;
		for (QuestionInComputerizeExam questionInComputerizeExam : questionInComputerizeExamArray) {
			String answer;
			if (questionInComputerizeExam.getToggleGroup().getSelectedToggle() != null) {
				answer = questionInComputerizeExam.getToggleGroup().getSelectedToggle().getUserData().toString();
			} else {
				answer = ("0");
			}
			submittedExam.addAnswer(new StudentAnswerInQuestion(activeExam.getExam().getSubjectID(),
					questionInComputerizeExam.getQuestionInExam().getQuestionNum(), Integer.toString(++num), answer,
					studentInActiveExam.getStudent()));
		}
		submittedExam.setSubmitted(0);
		client.handleMessageFromClientUI(new SubmittedExamHandle(Message.submittedExam, submittedExam));
		if (!submittedExam.getAnswers().isEmpty()) {
			System.out.println(submittedExam);
		}
	}

	/**
	 * Action for unwanted manual submit exam
	 */
	private void unWantedManualSubmitExam() {
		try {
			submittedExam = new SubmittedExam(activeExam.getDuration() - secondTimer / 60, studentInActiveExam);
			this.uploadManualExam.setDisable(true);
			Runtime.getRuntime().exec("cmd /c taskkill /f /im winword.exe");
			client.handleMessageFromClientUI(new SubmittedExamHandle(Message.submittedExam, submittedExam));
			client.handleMessageFromClientUI(new MyFileHandle("UploadExam", Utilities_Client
					.getWordFile(activeExam.getExecutionCode(), studentInActiveExam.getStudent().getId())));
			uploadManualExam.setDisable(true);
			Utilities_Client.popUpMethod("Exam Uploaded Successfully");
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	/**
	 * Clears order exam window
	 */
	private void clearOrderExam() {
		if (subjectComboBoxCheckedExam.getValue() != null) {
			setSubjectComboBox(subjectComboBoxCheckedExam);
		}
		if (courseComboBoxCheckedBox.getValue() != null) {
			courseComboBoxCheckedBox.getSelectionModel().clearSelection();
		}
	}

	/**
	 * Turning off all "Panes" at this controller
	 */
	private void turnOffAllPane() {
		clearOrderExam();
		tableViewCheckedExam.getItems().clear();
		anchorPaneShowExam.setVisible(false);
		vBoxShowExam.setVisible(false);
		aesAnchorPane.setVisible(false);
		welcomeAnchorPane.setVisible(false);
		examAnchorPane.setVisible(false);
		computerizeExamPane.setVisible(false);
		timerPane.setVisible(false);
		computrizedScrollPane.setVisible(false);
		checkedExamAnchorPane.setVisible(false);
	}

	/**
	 * Checking if the student want to change screen\log out during a running exam
	 */
	private void checkRunningExam() {
		Label text = new Label(
				"You still in the middle of the exam. If you press 'Yes', your exam will be submitted with your current answers. You sure you want to do this?");
		Stage primaryStage = new Stage();
		primaryStage.setTitle("AES7");
		primaryStage.getIcons().add(new Image("boundaries/Images/AES2.png"));
		primaryStage.setResizable(false);
		Popup popup = new Popup();
		popup.setX(700);
		popup.setY(400);
		GridPane layout = new GridPane();
		layout.setHgap(3);
		layout.setVgap(3);
		layout.setPadding(new Insets(1, 5, 1, 5));
		popup.getContent().addAll(text);
		Button yesButton = new Button("Yes");
		yesButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				primaryStage.hide();
				setSecondTimer(0);
			}
		});
		Button noButton = new Button("No");
		noButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				primaryStage.hide();
			}
		});
		layout.add(text, 4, 0);
		layout.add(yesButton, 3, 1);
		layout.add(noButton, 5, 1);
		layout.setStyle("-fx-background-color: cornsilk; -fx-padding: 10;");
		primaryStage.setScene(new Scene(layout));
		primaryStage.show();
	}

} // end of class