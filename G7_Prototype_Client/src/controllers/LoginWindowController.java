package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import client.Client;
import client.MainApp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import resources.Message;
import resources.Utilities;

public class LoginWindowController implements Initializable, IScreenController {

	@FXML
	private PasswordField pw;

	@FXML
	private TextField un;

	@FXML
	private Button login;

	@FXML
	private Label loginLabel;

	@FXML
	private AnchorPane signIn;

	@FXML
	private Text date;

	private int fieldFlag;

	private static Stage stage;

	private Client client;

	private ScreensController myController;

	public ScreensController getMyController() {
		return myController;
	}

	public static Stage getStage() {
		return stage;
	}

	public static void closeStage() {
		getStage().close();
	}

	public void setStage(Stage stage) {
		LoginWindowController.stage = stage;
	}

	@FXML
	public void loginButtonHandler(ActionEvent event) {
		loginCheck();
	}

	/**
	 * This handler is for move between fields with tab.
	 * 
	 * @param event
	 */
	public void keyHandler(KeyEvent event) {
		KeyCode code = event.getCode();
		if (code == KeyCode.TAB) {
			switch (fieldFlag) {
			case 0:
				pw.requestFocus();
				fieldFlag = 1;
				break;
			case 1:
				login.requestFocus();
				fieldFlag = 2;
				break;
			case 2:
				fieldFlag = 0;
				un.requestFocus();
				break;
			}
		} else if (code == KeyCode.ENTER) {
			loginCheck();
		}
	}

	private void loginCheck() {
		if ((un.getText().isEmpty()) || (pw.getText().isEmpty())) {
			loginLabel.setText("Incorrect username or password.");
			loginLabel.setVisible(true);
		}
		client.handleMessageFromClientUI(Message.login + " " + un.getText() + " " + pw.getText());
	}

	/**
	 * This method clears the userName and password fields
	 */
	private void clearFields() {
		un.clear();
		pw.clear();
		loginLabel.setText("");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		fieldFlag = 0;
		this.client = MainApp.getClient();
		date.setText(Utilities.setDate());
	}

	@Override
	public void setScreenParent(ScreensController screenParent) {
		myController = screenParent;
	}

}