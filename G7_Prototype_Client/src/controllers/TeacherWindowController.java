package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import client.Client;
import client.MainApp;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import resources.Question;
import resources.Utilities;

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

	@FXML
	private MenuItem editOrRemoveQuestion;
	@FXML
	private TableView<Question> tableView;
	@FXML
	private TableColumn<Question, String> questionIDColumn;
	@FXML
	private TableColumn<Question, String> authorColumn;
	@FXML
	private TableColumn<Question, String> questionTextColumn;
	@FXML
	private TableColumn<Question, String> possibleAnswersColumn;
	@FXML
	private TableColumn<Question, String> correctAnswerColumn;

	// private boolean clickedOnMaintainQuestion;
	private ScreensController screensController;
	private Client client;
	private static Stage stage;

	// private TeacherWindowController controller;

	// @SuppressWarnings("deprecation")
	// public static void start() throws Exception {
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
	// }

	public static Stage getStage() {
		return stage;
	}

	public static void setStage(Stage stage) {
		TeacherWindowController.stage = stage;
	}

	public static void closeStage() {
		getStage().close();
	}

	public void logOutButtonHandler(ActionEvent event) throws Exception {
		screensController.setScreen(MainApp.screen1ID);
		// TeacherWindowController.getStage().close();
		// LoginWindowController.getStage().show();
		// LoginWindowController.getStage().sizeToScene();
		// User.updateUserLogged(User.getActiveUser().getiD(), 0);
	}

	/* Edit\Remove Question was pressed */
	@SuppressWarnings("unchecked")
	public void openEditorRemove(ActionEvent event) {
		try {
			System.out.println("Edit\\Remove Was pressed");
			setColumns();
			client.handleMessageFromClientUI(resources.Message.EditorRemove);
			ObservableList<Question> questions=(ObservableList<Question>) client.getQuestions();
			tableView.setItems(questions);
			tableView.setVisible(true);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	/* define the columns */
	private void setColumns() {
		questionIDColumn.setCellValueFactory(new PropertyValueFactory<>("questionID"));
		authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
		questionTextColumn.setCellValueFactory(new PropertyValueFactory<>("questionText"));
		possibleAnswersColumn.setCellValueFactory(new PropertyValueFactory<>("possibleAnswers"));
		correctAnswerColumn.setCellValueFactory(new PropertyValueFactory<>("correctAnswer"));
	}

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
		tableView.setVisible(false);
		this.client = MainApp.getClient();
	}

	@Override
	public void setScreenParent(ScreensController screenParent) {
		screensController = screenParent;
	}

}