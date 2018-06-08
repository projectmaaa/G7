package controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import client.Client;
import client.MainAppClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import resources.ActiveExam;
import resources.Message;
import resources.QuestionInExam;
import resources.Student;
import resources.StudentInActiveExam;
import resources.QuestionInComputerizeExam;
import resources.Utilities;

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
	private AnchorPane manualExamAnchorPane;

	@FXML
	private TextField executionCodeTextField;

	/*********************************************************/

	@FXML
	private AnchorPane computerizedExamAnchorPane;

	/*********************************************************/

	@FXML
	private AnchorPane computerizeExamPane;

	@FXML
	private TextField examIDTextField;

	@FXML
	private Text examTextName;

	@FXML
	private Button checkIDComputerizeExamButton;

	@FXML
	private VBox examSheetVBox;

	@FXML
	private Button sumbitExamButton;

	private ArrayList<QuestionInComputerizeExam> QuestionInComputerizeExamArray;

	/*********************************************************/

	private StudentInActiveExam studentInActiveExam;

	/*********************************************************/

	@FXML
	private AnchorPane timerPane;

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
	public void openManualExamHandler(ActionEvent event) {
		turnOffAllPane();
		manualExamAnchorPane.setVisible(true);
	}

	public void checkExamID(MouseEvent e) {
		if (!examIDTextField.getText().isEmpty()) {
			if (examIDTextField.getText().equals(client.getId())) {
				studentInActiveExam = new StudentInActiveExam(new Student(client.getId(), firstName, lastName),
						activeExam);
				checkIDComputerizeExamButton.setDisable(true);
				examIDTextField.setDisable(true);
				examTextName.setText(firstName + " " + lastName);
				int index = 0;
				for (QuestionInExam questionInExam : activeExam.getExam().getQuestions()) {
					QuestionInComputerizeExam questionInComputerizeExam = new QuestionInComputerizeExam(
							Integer.toString(++index) + ". " + questionInExam.getQuestion().getQuestionText(),
							questionInExam.getQuestion().getFirstPossibleAnswer(),
							questionInExam.getQuestion().getSecondPossibleAnswer(),
							questionInExam.getQuestion().getThirdPossibleAnswer(),
							questionInExam.getQuestion().getFourthPossibleAnswer());
					QuestionInComputerizeExamArray.add(questionInComputerizeExam);
					examSheetVBox.getChildren().addAll(questionInComputerizeExam.getList());
					examSheetVBox.getChildren().add(new Text(""));
				}
			} else {
				Utilities.popUpMethod("Wrong ID");
			}
		} else {
			Utilities.popUpMethod("Please Enter ID");
		}
	}

	public void sumbitExam(MouseEvent mouseEvent) {
		for (QuestionInComputerizeExam questionInComputerizeExam : QuestionInComputerizeExamArray) {
			System.out.println(questionInComputerizeExam.getToggleGroup().getSelectedToggle().getUserData());

			// ;
		}
	}

	/**
	 * 
	 * @param event
	 */
	public void openComputerizedExamHandler(ActionEvent event) {
		turnOffAllPane();
		computerizedExamAnchorPane.setVisible(true);
	}

	public void checkExecutionCode(MouseEvent event) {
		String code = executionCodeTextField.getText();
		if (code != null) {
			client.handleMessageFromClientUI(Message.getExecutionCode + " " + code);
			turnOffAllPane();
			computerizeExamPane.setVisible(true);
			timerPane.setVisible(true);
		} else {
			Utilities.popUpMethod("Please Enter Execution Code");
		}
	}

	/**
	 * 
	 */
	private void turnOffAllPane() {
		aesAnchorPane.setVisible(false);
		welcomeAnchorPane.setVisible(false);
		manualExamAnchorPane.setVisible(false);
		computerizedExamAnchorPane.setVisible(false);
		computerizeExamPane.setVisible(false);
		timerPane.setVisible(false);
	}

}
