package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import client.MainApp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import resources.Utilities;
//import userManagement.User;

public class TeacherWindowController implements Initializable, IScreenController {

	@FXML
	private AnchorPane anchorPane;

	@FXML
	private Text TextGroup7;

	@FXML
	private ImageView Group7Logo;

	@FXML
	private Button logoutButton;

	@FXML
	private Text date;

	@FXML
	private Text welcomeText;

	private ScreensController myController;

	// @FXML
	// private Button maintainQuestionButton;

	// @FXML
	// private Button addQuestion;

	// @FXML
	// private MenuItem editOrRemoveQuestion;
	// @FXML
	// private TableView<Question> tableView;
	// @FXML
	// private TableColumn questionIDColumn;
	// @FXML
	// private TableColumn authorColumn;
	// @FXML
	// private TableColumn questionTextColumn;
	// @FXML
	// private TableColumn possibleAnswersColumn;
	// @FXML
	// private TableColumn correctAnswerColumn;
	//
	// private boolean clickedOnMaintainQuestion;

	private static Stage stage;

	// private TeacherWindowController controller;

	@SuppressWarnings("deprecation")
	public static void start() throws Exception {
		// try {
		// Stage stage = new Stage();
		// setStage(stage);
		// URL url = new File("src/boundaries/TeacherWindow.fxml").toURL();
		// Parent root1 = FXMLLoader.load(url);
		// Scene scene = new Scene(root1);
		// Image image = new Image(new
		// File("src/boundaries.Images/AES2.PNG").toURI().toString());
		// stage.getIcons().add(image);
		// stage.setResizable(false);
		// stage.sizeToScene();
		// stage.setScene(scene);
		// stage.setTitle("AES");
		// stage.show();
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
	}

	public static Stage getStage() {
		return stage;
	}

	public static void setStage(Stage stage) {
		TeacherWindowController.stage = stage;
	}

	public static void closeStage() {
		getStage().close();
	}

	// @FXML
	// private ScrollPane scrollPaneEditOrRemoveQuestions;

	public void logOutButtonHandler(ActionEvent event) throws Exception {
		myController.setScreen(MainApp.screen1ID);
		// TeacherWindowController.getStage().close();
		// LoginWindowController.getStage().show();
		// LoginWindowController.getStage().sizeToScene();
		// User.updateUserLogged(User.getActiveUser().getiD(), 0);
	}
	//
	// /* Edit\Remove Question was pressed */
	// public void openEditorRemove(ActionEvent event) {
	// try {
	// ObservableList<Question> data = controller.getQuestions();
	// clickedOnMaintainQuestion = true;
	// System.out.println("Enter");
	// tableView.setVisible(true);
	// tableView.setItems(data);
	// Platform.runLater(() -> {
	// tableView.refresh();
	// });
	// } catch (Throwable e) {
	// e.printStackTrace();

	// public void maintainQuestionHideOption(MouseEvent event) {
	// try {
	// if (clickedOnMaintainQuestion) {
	// System.out.println("leave");
	// anchorPane.getChildren().remove(addQuestion);
	// anchorPane.getChildren().remove(editOrRemoveQuestion);
	// clickedOnMaintainQuestion = false;
	// }
	// } catch (ClassCastException | IllegalArgumentException e) {
	// e.printStackTrace();
	// } finally {
	// User.updateUserLogged(User.getActiveUser().getiD(), 0);
	// }
	// }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// clickedOnMaintainQuestion = false;
		// controller = new TeacherController();
		// welcomeText.setText(welcomeText.getText() + " " +
		// User.getActiveUser().getFirstName() + " "
		// + User.getActiveUser().getLastName());
		date.setText(Utilities.setDate());
		// tableView.setVisible(false);
	}

	@Override
	public void setScreenParent(ScreensController screenParent) {
		myController = screenParent;
	}

}