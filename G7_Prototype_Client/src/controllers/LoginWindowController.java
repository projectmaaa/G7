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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import resources.Message;
import resources.Utilities_Client;

/**
 * 
 * @author Group7
 *
 */
public class LoginWindowController implements Initializable, IScreenController {

	/**
	 *  region Fields
	 */

	@FXML
	private PasswordField pw;

	@FXML
	private TextField un;

	@FXML
	private Button login;

	@FXML
	private Text loginText;

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
	
	/**
	 * get My Controller
	 * @return
	 */

	public ScreensController getMyController() {
		return myController;
	}

	/**
	 * set Screen Parent
	 */
	
	@Override
	public void setScreenParent(ScreensController screenParent) {
		myController = screenParent;
	}

	// end region -> Setters

	/**
	 * initialize client screen
	 */
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		fieldFlag = 0;
		this.client = MainAppClient.getClient();
		this.client.setLoginWindowController(this);
		date.setText(Utilities_Client.setDate());
	}

	// region Public Methods
	
	/**
	 * login Button Handler
	 * @param event
	 */

	public void loginButtonHandler(ActionEvent event) {
		loginText.setText("");
		loginCheck();
	}

	/**
	 * define open setting to start
	 * @param event
	 */
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

	/**
	 * save setting 
	 * @param event
	 */
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

	/**
	 * set Login Status
	 * @param msg
	 */
	public void setLoginStatus(String msg) {
		loginText.setVisible(true);
		loginText.setText(msg);
	}

	// end region -> Public Methods

	// region Private Methods
	
	/**
	 * check login user details
	 */

	private void loginCheck() {
		if ((!un.getText().isEmpty()) && (!pw.getText().isEmpty())) {
			if (anchorPaneSetting.isVisible()) {
				anchorPaneSetting.setVisible(false);
			}
			client.handleMessageFromClientUI(Message.login + " " + un.getText() + " " + pw.getText());
			client.setId(un.getText());
			clearFields();
			return;
		}
		setLoginStatus("Incorrect username or password.");
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
		loginText.setText("");
	}

	// end region -> Private Methods

}