package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import client.Client;
import client.MainAppClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
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

	@FXML
	private Button settingsSaveButton;

	@FXML
	private Button settingsCancelButton;

	@FXML
	private AnchorPane anchorPaneSetting;

	@FXML
	private TextField portField;

	@FXML
	private TextField hostField;

	private int fieldFlag;

	private Boolean turnSettings = false;

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
		this.client = MainAppClient.getClient();
		date.setText(Utilities.setDate());
	}

	// region Public Methods

	public void loginButtonHandler(ActionEvent event) {
		loginCheck();
	}

	public void openSetting(MouseEvent event) {
		if (!turnSettings) {
			hostField.setText(MainAppClient.getClient().getHost());
			portField.setText(Integer.toString(MainAppClient.getClient().getPort()));
			anchorPaneSetting.setVisible(true);
			turnSettings = true;
		} else {
			clearSettings();
			anchorPaneSetting.setVisible(false);
			turnSettings = false;
		}
	}

	public void saveSettings(MouseEvent event) {
		if (!(MainAppClient.getClient().getHost().equals(hostField.getText()))
				|| (MainAppClient.getClient().getPort() != (Integer.parseInt(portField.getText())))) {
			MainAppClient.getClient().setHost(hostField.getText());
			MainAppClient.getClient().setPort(Integer.parseInt(portField.getText()));
			System.out.println("New HostName : " + MainAppClient.getClient().getHost() + " New Port : "
					+ MainAppClient.getClient().getPort());
			try {
				MainAppClient.getClient().closeConnection();
				MainAppClient.getClient().openConnection();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		clearSettings();
		anchorPaneSetting.setVisible(false);
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

	public void setUserAlreadyConnected() {
		loginLabel.setText("User Already Connected");
	}

	// end region -> Public Methods

	// region Private Methods

	private void loginCheck() {
		if ((un.getText().isEmpty()) || (pw.getText().isEmpty())) {
			loginLabel.setText("Incorrect username or password.");
			loginLabel.setVisible(true);
		}
		if (anchorPaneSetting.isVisible()) {
			anchorPaneSetting.setVisible(false);
		}
		client.handleMessageFromClientUI(Message.login + " " + un.getText() + " " + pw.getText());
		clearFields();
	}

	private void clearSettings() {
		portField.setText(null);
		hostField.setText(null);
	}

	/**
	 * This method clears the userName and password fields
	 */
	private void clearFields() {
		un.clear();
		pw.clear();
		loginLabel.setText("");
	}

	// end region -> Private Methods

}