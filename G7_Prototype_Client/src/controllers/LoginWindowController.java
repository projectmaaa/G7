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

	public void handleTab(KeyEvent event) {
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
		// login.addEventHandler(KeyEvent event, handleTab);
		date.setText(Utilities.setDate());
	}

	@Override
	public void start(Stage arg0) throws Exception {
		try {
			Stage stage = new Stage();
			// setStage(stage);
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
