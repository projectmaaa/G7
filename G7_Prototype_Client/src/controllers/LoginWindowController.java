package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import resources.Utilities;
import userManagement.User;
import userManagement.Users;

public class LoginWindowController implements Initializable {

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

	/**
	 * This method get user and according to his type active the right window
	 * 
	 * @param user
	 * @throws Exception
	 */
	private void activeWindowType(User user) throws Exception {
		if (user.getType().equals("Teacher")) {
			user.setActiveUser(user);
			User.updateUserLogged(user.getiD(), 1);
			LoginWindow.closeStage();
			TeacherWindow maintainWindow = new TeacherWindow();
			maintainWindow.go();
		} else if (user.getType().equals("Principal")) {
			System.out.println("Principal Window In work");
			loginLabel.setText("Principal Window In work");
		} else if (user.getType().equals("Student")) {
			System.out.println("Student Window In work");
			loginLabel.setText("Student Window In work");
		} else {
			System.out.println("Undefinded type");
			loginLabel.setText("Undefinded type");
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		date.setText(Utilities.setDateS());
	}
}
