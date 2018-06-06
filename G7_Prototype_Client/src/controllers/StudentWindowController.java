package controllers;

import java.net.URL;
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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import resources.ActiveExam;
import resources.Message;
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

	/*********************************************************/

	@FXML
	private AnchorPane manualExamAnchorPane;

	@FXML
	private TextField executionCodeTextField;

	/*********************************************************/

	@FXML
	private AnchorPane computerizedExamAnchorPane;

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
		manualExamAnchorPane.setVisible(false);
		computerizedExamAnchorPane.setVisible(false);
		welcomeAnchorPane.setVisible(true);
		this.client.handleMessageFromClientUI(Message.logout);
		screensController.setScreen(MainAppClient.loginScreenID);
	}

	/**
	 * 
	 * @param event
	 */
	public void openManualExamHandler(ActionEvent event) {
		welcomeAnchorPane.setVisible(false);
		manualExamAnchorPane.setVisible(true);
		computerizedExamAnchorPane.setVisible(false);
	}

	/**
	 * 
	 * @param event
	 */
	public void openComputerizedExamHandler(ActionEvent event) {
		welcomeAnchorPane.setVisible(false);
		computerizedExamAnchorPane.setVisible(true);
		manualExamAnchorPane.setVisible(false);
	}

	public void checkExecutionCode(MouseEvent event) {
		String code = executionCodeTextField.getText();
		if (code != null) {
			client.handleMessageFromClientUI(Message.getExecutionCode + " " + code);
		} else {
			Utilities.popUpMethod("Duration");
		}
	}

	private void popUpexam() {
		Stage primaryStage = new Stage();
		primaryStage.setTitle("AES7");
		primaryStage.setResizable(false);		
		primaryStage.initStyle(StageStyle.UNDECORATED);

		
		
		
		
		
		
		
		
	}

}
