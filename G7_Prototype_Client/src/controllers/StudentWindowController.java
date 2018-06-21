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

public class StudentWindowController implements Initializable, IScreenController {

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
	private Button sumbitExamButton;

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

	public int getSecondTimer() {
		return secondTimer;
	}

	/**
	 * Changes the time of exam.
	 */
	public void setSecondTimer(int secondTimer) {
		this.secondTimer = secondTimer;
	}

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

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		date.setText(Utilities_Client.setDate());
		this.client = MainAppClient.getClient();
		client.setStudentWindowController(this);
		questionInComputerizeExamArray = new ArrayList<QuestionInComputerizeExam>();
		setSubjectComboBox(subjectComboBoxCheckedExam);
		setColumnsInCheckedExams();
	}

	public ActiveExam getActiveExam() {
		return activeExam;
	}

	public void setActiveExam(ActiveExam activeExam) {
		this.activeExam = activeExam;
	}

	/**
	 * 
	 */
	public void setName() {
		name.setText(firstName + " " + lastName);
	}

	/**
	 * 
	 * @param event
	 */
	public void logOutButtonHandler(MouseEvent event) {
		if (secondTimer != -1 && secondTimer != 0)
			checkRunningExam();
		else { // if the student is in the middle of the exam & pressed 'No' in the submission
				// pop up than he\she wont exit the application
			if (stopWatchTimeline != null)
				stopWatchTimeline.stop();
			if (this.examIDTextField.isVisible()) {
				examIDTextField.clear();
				examIDTextField.setDisable(false);
				enterIDComputerizeExamButton.setDisable(false);
			}
			if (sumbitExamButton.isVisible()) {
				sumbitExamButton.setVisible(false);
			}
			if (uploadManualExam.isVisible()) {
				uploadManualExam.setDisable(false);
				uploadManualExam.setVisible(false);
			}
			if (!examSheetVBox.getChildren().isEmpty()) {
				System.out.println("not empty");
				examSheetVBox.getChildren().clear();
			}
			turnOffAllPane();
			aesAnchorPane.setVisible(true);
			welcomeAnchorPane.setVisible(true);
			this.client.handleMessageFromClientUI(Message.logout);
			screensController.setScreen(MainAppClient.loginScreenID);
			this.sumbitExamButton.setDisable(false);
		}
	}

	/**
	 * 
	 * @param event
	 */
	public void openExamHandler(ActionEvent event) {
		if (secondTimer != -1 && secondTimer != 0)
			checkRunningExam();
		else {
			clearOrderExam();
			turnOffAllPane();
			examAnchorPane.setVisible(true);
			welcomeAnchorPane.setVisible(false);
		}
	}

	/**
	 * 
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
	 * ************
	 * 
	 * @param mouseEvent
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
	 */
	public void enterIsClickedInEnterID(MouseEvent e) {
		checkStudentID();
	}

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
				computerizeExam();
			} else {
				Utilities_Client.popUpMethod("Wrong ID");
				examIDTextField.clear();
			}
		} else {
			Utilities_Client.popUpMethod("Please Enter ID");
		}
	}

	private void checkExamType() {
		setComputerizeExam();
		if (activeExam.getType().equals("c")) {
			this.examIDTextField.setVisible(true);
			this.enterIDComputerizeExamButton.setVisible(true);
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
	 * 
	 * @param mouseEvent
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
		sumbitExamButton.setDisable(true);

		submittedExam.setSubmitted(1);

		client.handleMessageFromClientUI(new SubmittedExamHandle(Message.submittedExam, submittedExam));
		if (!submittedExam.getAnswers().isEmpty()) {
			System.out.println(submittedExam);
		}
	}

	/**
	 * 
	 * @param mouseEvent
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

	public void turnCheckExamAnchorPane(ActionEvent actionEvent) {
		if (secondTimer != -1 && secondTimer != 0)
			checkRunningExam();
		else {
			turnOffAllPane();
			welcomeAnchorPane.setVisible(true);
			checkedExamAnchorPane.setVisible(true);
		}
	}

	/**
	 * 
	 * @param mouseEvent
	 */
	public void uploadManualExam(MouseEvent mouseEvent) {
		stopWatchTimeline.stop();
		submittedExam = new SubmittedExam(activeExam.getDuration() - secondTimer / 60, studentInActiveExam);
		client.handleMessageFromClientUI(new MyFileHandle("UploadExam",
				Utilities_Client.getWordFile(activeExam.getExecutionCode(), studentInActiveExam.getStudent().getId())));
		uploadManualExam.setDisable(true);
		Utilities_Client.popUpMethod("Exam Uploaded Successfully");
		client.handleMessageFromClientUI(new SubmittedExamHandle(Message.submittedExam, submittedExam));
	}

	/**
	 * 
	 * @param event
	 */
	public void openComputerizedExamHandler(ActionEvent event) {
		turnOffAllPane();
		examAnchorPane.setVisible(true);
	}

	/**
	 *
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
	 */
	public void enterIsClickedInExecuteExam(MouseEvent e) {
		checkExecutionCode();
	}

	/**
	 * When the user pressed enter key after entering the execution code
	 * 
	 * @param e
	 */
	public void executionCodeKeyHandler(KeyEvent e) {
		KeyCode code = e.getCode();
		if (code == KeyCode.ENTER)
			checkExecutionCode();
	}

	/**
	 * 
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
	 */
	public void checkRunningExamInTheMenuBar(ActionEvent event) {
		checkRunningExam();
	}

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
	 * 
	 */
	private void computerizeExam() {
		sumbitExamButton.setVisible(true);
		computrizedScrollPane.setVisible(true);
		commentsTextArea.setText(activeExam.getExam().getFreeTextForExaminees());
		commentsTextArea.setVisible(true);
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
	 * 
	 */
	private void manualExam() {
		enterIDComputerizeExamButton.setVisible(false);
		examIDTextField.setVisible(false);
		uploadManualExam.setVisible(true);
		sumbitExamButton.setVisible(false);
		startTimer();
		client.handleMessageFromClientUI(new ActiveExamHandle("#ManualExam", activeExam, client.getId()));
	}

	/**
	 * 
	 */
	private void setTimerDisplay() {
		timerDisplay.setText(
				String.format("%02d:%02d:%02d", secondTimer / 3600, (secondTimer % 3600) / 60, secondTimer % 60));
	}

	/**
	 * 
	 * @param time
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

	private void setColumnsInCheckedExams() {
		examNumberColInCheckedExam.setCellValueFactory(new PropertyValueFactory<>("examNum"));
		executionCodeColInCheckedExam.setCellValueFactory(new PropertyValueFactory<>("executionCode"));
		gradeColInCheckedExam.setCellValueFactory(new PropertyValueFactory<>("finalGrade"));
		generalCommentsColInCheckedExam.setCellValueFactory(new PropertyValueFactory<>("generalComments"));
	}

	/**
	 * 
	 */
	private void unWantedComputerizeSubmitExam() {
		this.sumbitExamButton.setDisable(true);
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
	 * 
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

	private void clearOrderExam() {
		if (subjectComboBoxCheckedExam.getValue() != null) {
			setSubjectComboBox(subjectComboBoxCheckedExam);
		}
		if (courseComboBoxCheckedBox.getValue() != null) {
			courseComboBoxCheckedBox.getSelectionModel().clearSelection();
		}
	}

	/**
	 * 
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
}
