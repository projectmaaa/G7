package controllers;

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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
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

	private ArrayList<QuestionInComputerizeExam> QuestionInComputerizeExamArray;

	@FXML
	private ScrollPane computrizedScrollPane;

	/*********************************************************/

	private StudentInActiveExam studentInActiveExam;

	private SubmittedExam submittedExam;

	/*********************************************************/

	@FXML
	private AnchorPane timerPane;

	@FXML
	private Text timerDisplay;

	private int secondTimer;

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

	public void setSecondTimer() {
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
		date.setText(Utilities.setDate());
		this.client = MainAppClient.getClient();
		client.setStudentWindowController(this);
		QuestionInComputerizeExamArray = new ArrayList<QuestionInComputerizeExam>();
		// sumbitExamButton.setDisable(true);
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
	public void logOutButtonHandler(ActionEvent event) {
		aesAnchorPane.setVisible(true);
		turnOffAllPane();
		this.client.handleMessageFromClientUI(Message.logout);
		screensController.setScreen(MainAppClient.loginScreenID);
	}

	/**
	 * 
	 * @param event
	 */
	public void openExamHandler(ActionEvent event) {
		turnOffAllPane();
		examAnchorPane.setVisible(true);
		welcomeAnchorPane.setVisible(true);
	}

	public void checkExecutionCodeForNull() {
		if (activeExam == null) {
			Utilities.popUpMethod("Wrong Code");
		} else {
			setTimer();
			setComputerizeExam();
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
	 * Checks if the inserted student ID is the same as the connected user
	 */
	public void checkStudentID() {
		if (!examIDTextField.getText().isEmpty()) {
			if (examIDTextField.getText().equals(client.getId())) {
				startTimer();
				studentInActiveExam = new StudentInActiveExam(new Student(client.getId(), firstName, lastName),
						activeExam);
				client.handleMessageFromClientUI(
						new StudentInActiveExamHandle(Message.studentInActiveExam, studentInActiveExam));
				enterIDComputerizeExamButton.setDisable(true);
				examIDTextField.setDisable(true);
				if (activeExam.getType().equals("c")) {
					sumbitExamButton.setVisible(true);
					computrizedScrollPane.setVisible(true);
					int index = 0;
					for (QuestionInExam questionInExam : activeExam.getExam().getQuestions()) {
						QuestionInComputerizeExam questionInComputerizeExam = new QuestionInComputerizeExam(
								Integer.toString(++index) + ". " + questionInExam.getQuestion().getQuestionText(),
								questionInExam.getQuestion().getFirstPossibleAnswer(),
								questionInExam.getQuestion().getSecondPossibleAnswer(),
								questionInExam.getQuestion().getThirdPossibleAnswer(),
								questionInExam.getQuestion().getFourthPossibleAnswer(), questionInExam);
						QuestionInComputerizeExamArray.add(questionInComputerizeExam);
						examSheetVBox.getChildren().addAll(questionInComputerizeExam.getList());
						examSheetVBox.getChildren().add(new Text(""));
					}
				} else {
					sumbitExamButton.setLayoutY(50);
					sumbitExamButton.setLayoutX(370);
					sumbitExamButton.setVisible(true);
					client.handleMessageFromClientUI(new ActiveExamHandle("#ManualExam", activeExam, client.getId()));
				}
			} else {
				Utilities.popUpMethod("Wrong ID");
				examIDTextField.clear();
			}
		} else {
			Utilities.popUpMethod("Please Enter ID");
		}
	}

	public void sumbitExam(MouseEvent mouseEvent) {
		submittedExam = new SubmittedExam(10, studentInActiveExam);
		int num = 0;
		for (QuestionInComputerizeExam questionInComputerizeExam : QuestionInComputerizeExamArray) {
			if (questionInComputerizeExam.getToggleGroup().getSelectedToggle() != null) {
				submittedExam.addAnswer(new StudentAnswerInQuestion(activeExam.getExam().getSubjectID(),
						questionInComputerizeExam.getQuestionInExam().getQuestionNum(), Integer.toString(++num),
						questionInComputerizeExam.getToggleGroup().getSelectedToggle().getUserData().toString(),
						studentInActiveExam.getStudent()));
			} else {
				Utilities.popUpMethod("Question : " + Integer.toString(++num) + " No Answer.");
				submittedExam.getAnswers().clear();
				break;
			}
		}
		client.handleMessageFromClientUI(new SubmittedExamHandle(Message.submittedExam, submittedExam));
		if (!submittedExam.getAnswers().isEmpty()) {
			System.out.println(submittedExam);
		}
	}

	/**
	 * 
	 * @param event
	 */
	public void openComputerizedExamHandler(ActionEvent event) {
		turnOffAllPane();
		examAnchorPane.setVisible(true);
	}

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
			Utilities.popUpMethod("Please Enter Execution Code");
		}
		executionCodeTextField.clear();
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
		Timeline stopWatchTimeline = new Timeline(new KeyFrame(Duration.seconds(1), (ActionEvent event) -> {
			if (secondTimer-- > 0) {
				setTimerDisplay();
			}
		}));
		stopWatchTimeline.setCycleCount(Timeline.INDEFINITE);
		stopWatchTimeline.play();
	}

	/**
	 * 
	 */
	private void turnOffAllPane() {
		aesAnchorPane.setVisible(false);
		welcomeAnchorPane.setVisible(false);
		examAnchorPane.setVisible(false);
		computerizeExamPane.setVisible(false);
		timerPane.setVisible(false);
	}

}
