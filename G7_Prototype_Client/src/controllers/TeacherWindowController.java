package controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import client.Client;
import client.MainAppClient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import resources.*;

public class TeacherWindowController implements Initializable, IScreenController {

	// region Fields

	@FXML
	private AnchorPane mainAnchorPane;
	
	@FXML
	private AnchorPane welcomeAnchorPane;

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
	private AnchorPane questionsTableAnchorPane;

	@FXML
	private TableView<Question> tableView;

	@FXML
	private TableColumn<Question, String> questionIDColumn;

	@FXML
	private TableColumn<Question, String> authorColumn;

	@FXML
	private TableColumn<Question, String> questionTextColumn;

	@FXML
	private TableColumn<Question, String> firstPossibleAnswerColumn;

	@FXML
	private TableColumn<Question, String> secondPossibleAnswerColumn;

	@FXML
	private TableColumn<Question, String> thirdPossibleAnswerColumn;

	@FXML
	private TableColumn<Question, String> fourthPossibleAnswerColumn;

	@FXML
	private TableColumn<Question, String> correctAnswerColumn;

	@FXML
	private Button saveButton;
	
	@FXML
	private Button removeButton;

	@FXML
	private AnchorPane addQuestionAnchorPane;

	@FXML
	private Label subject;

	@FXML
	private ComboBox<String> subjectComboBox;

	@FXML
	private Label questionText;

	@FXML
	private TextField questionTextField;

	@FXML
	private Label firstAnswer;

	@FXML
	private TextField firstAnswerField;

	@FXML
	private Label secondAnswer;

	@FXML
	private TextField secondAnswerField;

	@FXML
	private Label thirdAnswer;

	@FXML
	private TextField thirdAnswerField;

	@FXML
	private Label fourthAnswer;

	@FXML
	private TextField forthAnswerField;

	@FXML
	private Label correcthAnswer;

	@FXML
	private ComboBox<String> correctAnswerComboBox;

	@FXML
	private Button createQuestionButton;

	// create exam

	@FXML
	private AnchorPane createExamAnchorPane;

	@FXML
	private Label subjectInCreate;

	@FXML
	private ComboBox<String> subjectInCreateComboBox;

	@FXML
	private Label courseInCreate;

	@FXML
	private ComboBox<String> courseInCreateComboBox;

	@FXML
	private Label textStudentsInCreate;

	@FXML
	private TextField textStudentsInCreateField;

	@FXML
	private Label textTeachersInCreate;

	@FXML
	private TextField textTeachersInCreateField;

	@FXML
	private Label durationInCreate;

	@FXML
	private TextField durationInCreateField;

	@FXML
	private Button createExamButton;

	private ScreensController screensController;

	private Client client;

	// end region -> Fields

	// region Setters

	@Override
	public void setScreenParent(ScreensController screenParent) {
		screensController = screenParent;
	}

	// end region -> Setters

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		date.setText(Utilities.setDate());
		this.client = MainAppClient.getClient();
		setColumns();
		setQuestionsTableInfo();
		tableView.setEditable(true);
		addQuestionAnchorPane.setVisible(false);
		createExamAnchorPane.setVisible(false);
		initAddQuestionOption();
	}

	// region Public Methods

	public void logOutButtonHandler(ActionEvent event) throws Exception {
		if (tableView.isVisible()) {
			tableView.getItems().clear();
			setQuestionsTableInfo();
		}
		if (addQuestionAnchorPane.isVisible()) {
			clearAddQuestionFields();
			addQuestionAnchorPane.setVisible(false);
		}
		welcomeAnchorPane.setVisible(true);
		this.client.handleMessageFromClientUI(Message.logout);
		screensController.setScreen(MainAppClient.loginScreenID);
	}

	/*
	 * Edit\Remove Question was pressed
	 */
	public void openEditorRemove(ActionEvent event) {
		try {
			tableView.getItems().clear();
			setQuestionsTableInfo();
			questionsTableAnchorPane.setVisible(true);
			addQuestionAnchorPane.setVisible(false);
			createExamAnchorPane.setVisible(false);
			clearAddQuestionFields();
			welcomeAnchorPane.setVisible(false);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	/*
	 * sends message to the server to update the data base
	 */
	public void saveButtonHandler(ActionEvent event) {
		ObservableList<Question> newQuestions = FXCollections.observableArrayList();
		ArrayList<Question> updateDB = new ArrayList<Question>();
		newQuestions = tableView.getItems();
		for (Question question : newQuestions) {
			int answerNumber = Integer.parseInt(question.getCorrectAnswer());
			if (answerNumber < 1 || answerNumber > 4) {
				// add error window
				System.out.println("answerNumber 1<-->4");
				return;
			}
			updateDB.add(question);
		}
		client.handleMessageFromClientUI(updateDB);
	}

	/*
	 * Remove button was pressed
	 */
	public void removeButtonHandler(ActionEvent event) {
		Question question = tableView.getSelectionModel().getSelectedItem();
		question.setAuthor(null);
		client.handleMessageFromClientUI(question);
		tableView.getItems().clear();
		setQuestionsTableInfo();
		questionsTableAnchorPane.setVisible(true);
	}

	/*
	 * 'Add Question' was pressed
	 */
	public void openAddQuestion(ActionEvent event) {
		try {
			addQuestionAnchorPane.setVisible(true);
			questionsTableAnchorPane.setVisible(false);
			welcomeAnchorPane.setVisible(false);
			createExamAnchorPane.setVisible(false);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	/*
	 * 'Create question' button was pressed - add Question to DB
	 */

	public void addNewQuestion(ActionEvent event) {
		Question question = new Question(subjectComboBox.getValue(), "Malki Grossman", questionTextField.getText(),
				firstAnswerField.getText(), secondAnswerField.getText(), thirdAnswerField.getText(),
				forthAnswerField.getText(), correctAnswerComboBox.getValue());
		client.handleMessageFromClientUI(question);
	}

	public void openCreateExam(ActionEvent event) {
		try {
			createExamAnchorPane.setVisible(true);
			addQuestionAnchorPane.setVisible(false);
			questionsTableAnchorPane.setVisible(false);
			welcomeAnchorPane.setVisible(false);
			subjectInCreateComboBox.setPromptText("Select Subject");
			subjectInCreateComboBox.getItems().addAll("Software", "Math", "Physics");
			courseInCreateComboBox.setPromptText("Select Course");
			courseInCreateComboBox.getItems().addAll("MLM", "MTM", "ATM", "OOP");
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	// end region -> Public Methods

	// region Private Methods

	/*
	 * Define the columns
	 */
	private void setColumns() {
		questionIDColumn.setCellValueFactory(new PropertyValueFactory<>("questionID"));
		authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
		questionTextColumn.setCellValueFactory(new PropertyValueFactory<>("questionText"));
		firstPossibleAnswerColumn.setCellValueFactory(new PropertyValueFactory<>("firstPossibleAnswer"));
		secondPossibleAnswerColumn.setCellValueFactory(new PropertyValueFactory<>("secondPossibleAnswer"));
		thirdPossibleAnswerColumn.setCellValueFactory(new PropertyValueFactory<>("thirdPossibleAnswer"));
		fourthPossibleAnswerColumn.setCellValueFactory(new PropertyValueFactory<>("fourthPossibleAnswer"));
		correctAnswerColumn.setCellValueFactory(new PropertyValueFactory<>("correctAnswer"));

		// define the columns editable

		questionTextColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		questionTextColumn.setOnEditCommit(new EventHandler<CellEditEvent<Question, String>>() {
			@Override
			public void handle(CellEditEvent<Question, String> t) {
				((Question) t.getTableView().getItems().get(t.getTablePosition().getRow()))
						.setQuestionText(t.getNewValue());
			}
		});
		firstPossibleAnswerColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		firstPossibleAnswerColumn.setOnEditCommit(new EventHandler<CellEditEvent<Question, String>>() {
			@Override
			public void handle(CellEditEvent<Question, String> t) {
				((Question) t.getTableView().getItems().get(t.getTablePosition().getRow()))
						.setFirstPossibleAnswer(t.getNewValue());
			}
		});
		secondPossibleAnswerColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		secondPossibleAnswerColumn.setOnEditCommit(new EventHandler<CellEditEvent<Question, String>>() {
			@Override
			public void handle(CellEditEvent<Question, String> t) {
				((Question) t.getTableView().getItems().get(t.getTablePosition().getRow()))
						.setSecondPossibleAnswer(t.getNewValue());
			}
		});
		thirdPossibleAnswerColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		thirdPossibleAnswerColumn.setOnEditCommit(new EventHandler<CellEditEvent<Question, String>>() {
			@Override
			public void handle(CellEditEvent<Question, String> t) {
				((Question) t.getTableView().getItems().get(t.getTablePosition().getRow()))
						.setThirdPossibleAnswer(t.getNewValue());
			}
		});
		fourthPossibleAnswerColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		fourthPossibleAnswerColumn.setOnEditCommit(new EventHandler<CellEditEvent<Question, String>>() {
			@Override
			public void handle(CellEditEvent<Question, String> t) {
				((Question) t.getTableView().getItems().get(t.getTablePosition().getRow()))
						.setFourthPossibleAnswer(t.getNewValue());
			}
		});
		correctAnswerColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		correctAnswerColumn.setOnEditCommit(new EventHandler<CellEditEvent<Question, String>>() {
			@Override
			public void handle(CellEditEvent<Question, String> t) {
				((Question) t.getTableView().getItems().get(t.getTablePosition().getRow()))
						.setCorrectAnswer(t.getNewValue());
			}
		});
	}

	/*
	 * Updates the GUI questions table from the data base
	 */
	private void setQuestionsTableInfo() {
		questionsTableAnchorPane.setVisible(false);
		client.handleMessageFromClientUI(Message.EditorRemove);
		tableView.setItems(client.getQuestionsFromDB());
	}

	/*
	 * init's the fields of the 'Add Question' option
	 */
	private void initAddQuestionOption() {
		subjectComboBox.setPromptText("Select Subject");
		subjectComboBox.getItems().addAll("Software", "Math", "Physics");
		correctAnswerComboBox.setPromptText("Select");
		correctAnswerComboBox.getItems().addAll("1", "2", "3", "4");
	}

	/*
	 * clear the fields of the last question that was added
	 */
	private void clearAddQuestionFields() {
		subjectComboBox.getSelectionModel().clearSelection();
		subjectComboBox.setPromptText("Select Subject");
		correctAnswerComboBox.getSelectionModel().clearSelection();
		correctAnswerComboBox.setPromptText("Select");
		questionTextField.clear();
		firstAnswerField.clear();
		secondAnswerField.clear();
		thirdAnswerField.clear();
		forthAnswerField.clear();
	}

	// end region -> Private Methods

}