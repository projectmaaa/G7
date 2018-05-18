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
import resources.Message;
import resources.Utilities;

public class LoginWindowController implements Initializable, IScreenController {

	// region Fields

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

	private Client client;

	private ScreensController myController;

	// end region -> Fields

	// region Setters

	public ScreensController getMyController() {
		return myController;
	}

	@Override
	public void setScreenParent(ScreensController screenParent) {
		myController = screenParent;
	}

	// end region -> Setters

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		fieldFlag = 0;
		this.client = MainApp.getClient();
		date.setText(Utilities.setDate());
	}

	// region Public Methods

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

	// end region -> Public Methods

	// region Private Methods

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
	@SuppressWarnings("unused")
	private void clearFields() {
		un.clear();
		pw.clear();
		loginLabel.setText("");
	}

	// end region -> Private Methods

}