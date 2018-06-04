package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import client.Client;
import client.MainAppClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import resources.Message;
import resources.Question;
import resources.Utilities;

public class PrincipalWindowController implements Initializable, IScreenController{

	private ScreensController screensController;

	private String firstName;

	private String lastName;

	private Client client;

	@FXML
	private AnchorPane mainAnchorPane;

	@FXML
	private Button logoutButton;

	@FXML
	private AnchorPane welconeAnchorPane;

	@FXML
	private Text welcomeText;

	@FXML
	private Text date;

	@FXML
	private Text name;

	@FXML
	private AnchorPane welcomeAnchorPane;

	@FXML
	private MenuBar menuBar;

	//questions pool
	
	@FXML
	private MenuItem questionsPool;
	
	@FXML
	private AnchorPane questionsPoolAnchorPane;
	
	@FXML
	private Label subjectInQuestionsPoolLabel;
	
	@FXML
	private ComboBox<String> subjectComboBoxInQuestionsPool;
	
	@FXML
	private Button updateTableButtonInQuestionPool;
	
	@FXML
	private TableView<Question> tableViewInQuestionsPool;
	
	@FXML
	private TableColumn<Question, String> subjectIDColumnInQuestionsPool;

	@FXML
	private TableColumn<Question, String> questionNumColumnInQuestionsPool;

	@FXML
	private TableColumn<Question, String> authorColumnInQuestionsPool;

	@FXML
	private TableColumn<Question, String> questionTextColumnInQuestionsPool;

	@FXML
	private TableColumn<Question, String> possibleAnswersColumnInQuestionsPool;

	@FXML
	private TableColumn<Question, String> firstPossibleAnswerColumnInQuestionsPool;

	@FXML
	private TableColumn<Question, String> secondPossibleAnswerColumnInQuestionsPool;

	@FXML
	private TableColumn<Question, String> thirdPossibleAnswerColumnInQuestionsPool;

	@FXML
	private TableColumn<Question, String> fourthPossibleAnswerColumnInQuestionsPool;

	@FXML
	private TableColumn<Question, String> correctAnswerColumnInQuestionsPool;

	//exams pool
	
	@FXML
	private AnchorPane examsPoolAnchorPane;
	
	@FXML
	private Label subjectInExamsPoolLabel;
	
	@FXML
	private ComboBox<String> subjectComboBoxInExamPool;

	@Override
	public void setScreenParent(ScreensController screenParent) {
		screensController = screenParent;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	/* --------------------- setters & getters ---------------------- */
	
	public void setName() {
		name.setText(firstName + " " + lastName);
	}

	
	/* --------------------- public methods ---------------------- */

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		date.setText(Utilities.setDate());
		this.client = MainAppClient.getClient();
		//client.setPrincipalWindowController(this);	
	}

	public void logOutButtonHandler(ActionEvent event) {
		welcomeAnchorPane.setVisible(true);
		this.client.handleMessageFromClientUI(Message.logout);
		screensController.setScreen(MainAppClient.loginScreenID);
	}
	
	//question tab was pressed
	
	public void openQuestionPool(ActionEvent event) {
		questionsPoolAnchorPane.setVisible(true);
		welcomeAnchorPane.setVisible(false);
		examsPoolAnchorPane.setVisible(false);
		setSubjectComboBox(subjectComboBoxInQuestionsPool);
	}
	
	//Update table button was clicked
	
	public void updateTableHandler(ActionEvent event) {
		setTableInCreateExamAllQuestions();
	}
	
	public void openExamPool(ActionEvent event) {
		examsPoolAnchorPane.setVisible(true);
		questionsPoolAnchorPane.setVisible(false);
		welcomeAnchorPane.setVisible(false);
		setSubjectComboBox(subjectComboBoxInExamPool);
	}
	
	
	/* --------------------- private methods ---------------------- */
	
	private void setSubjectComboBox(ComboBox<String> comboBox) {
		comboBox.getSelectionModel().clearSelection();
		comboBox.setPromptText("Select Subject");
		comboBox.getItems().addAll("Software", "Math", "Physics");
	}

	private void setTableInCreateExamAllQuestions() {
		client.getQuestionsFromDB().clear();
		client.handleMessageFromClientUI(Message.getQuestionBySubject + " " + subjectComboBoxInQuestionsPool.getValue());
		//initTablesInCreateExam(true, false);
		tableViewInQuestionsPool.setItems(client.getQuestionsFromDB());
	}
}
