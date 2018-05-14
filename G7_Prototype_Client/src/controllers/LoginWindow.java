package controllers;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class LoginWindow implements Initializable{
	
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

	private static Stage stage;
	
	public void loginButtonHandler(ActionEvent event) throws Exception {
		try {
			Users.updateList();
			User user = userExist();
			if ((user != null) && (user.getLogged() == 0)) {
				clearFields();
				loginLabel.setVisible(false);
				activeWindowType(user);
			} else if (user.getLogged() == 1) {
				loginLabel.setText("This user is already logged in."); // syntax fix by AsafYus
			}
		} catch (NullPointerException ex) {
			loginLabel.setText("Incorrect username or password."); // syntax fix by AsafYus
		} finally {
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
	private User userExist() throws NullPointerException {
		User user = Users.getUser(un.getText());
		if (user != null) {
			if (user.getPassWord().equals(pw.getText())) {
				return user;
			}
		}
		return null;
	}

	/**
	 * This method clears the userName and password fields
	 */
	private void clearFields() {
		un.clear();
		pw.clear();
		loginLabel.setText("");
	}


	@SuppressWarnings("deprecation")
	public void start(Stage stage) {
		try {
			setStage(stage);
			URL url = new File("src/application/LoginWindow.fxml").toURL();
			Parent root = FXMLLoader.load(url);
			Scene scene = new Scene(root);
			Image image = new Image(new File("src/AES2.PNG").toURI().toString());
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

	public static Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		LoginWindow.stage = stage;
	}

	public static void closeStage() {
		getStage().close();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		date.setText(Utilities.setDateS());		
	}


}