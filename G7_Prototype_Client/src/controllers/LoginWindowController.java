package controllers;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import resources.Utilities;

public class LoginWindowController extends Application implements Initializable {

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

	public static Stage getStage() {
		return stage;
	}

	public static void closeStage() {
		getStage().close();
	}

	public void setStage(Stage stage) {
		LoginWindowController.stage = stage;
	}

	public void loginButtonHandler(ActionEvent event) {
		if ((un.getText().isEmpty()) || (pw.getText().isEmpty())) {
			loginLabel.setText("Incorrect username or password.");
			loginLabel.setVisible(true);
		}
		

	}

	/**
	 * This method check if the user exist in the database.
	 * 
	 * <pre>
	 * If yes return the user.
	 * 
	 * <pre>
	 * Else return null.
	 */
	// private User userExist() throws NullPointerException {
	// User user = Users.getUser(un.getText());
	// if (user != null) {
	// if (user.getPassWord().equals(pw.getText())) {
	// return user;
	// }
	// }
	// return null;
	// }

	/**
	 * This handler is for move between fields with tab.
	 * 
	 * @param event
	 */
	public void tabHandler(KeyEvent event) {
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
		}
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
		date.setText(Utilities.setDate());
	}

	@SuppressWarnings("deprecation")
	@Override
	public void start(Stage arg0) throws Exception {
		try {
			Stage stage = new Stage();
			setStage(stage);
			URL url = new File("src/boundaries/LoginWindow.fxml").toURL();
			Parent root = FXMLLoader.load(url);
			Scene scene = new Scene(root);
			Image image = new Image(new File("src/boundaries.Images/AES2.PNG").toURI().toString());
			stage.getIcons().add(image);
			stage.setResizable(false);
			stage.setScene(scene);
			stage.setTitle("AES");
			stage.sizeToScene();
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void go() {
		launch();
	}
}
