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
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.Stage;
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
	
	//exam management
	
	@FXML
	private AnchorPane examManagementAnchorPane;
	
	//

	private ScreensController screensController;

	private String firstName;

	private String lastName;

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
		examManagementAnchorPane.setVisible(false);
		initAddQuestionOption();
		client.setTeacherWindowController(this);
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
		welcomeText.setText("Welcome");
		welcomeAnchorPane.setVisible(true);
		this.client.handleMessageFromClientUI(Message.logout);
		screensController.setScreen(MainAppClient.loginScreenID);
	}

	public void setNameAndLastName(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
		welcomeText.setText(welcomeText.getText() + " " + this.firstName + " " + this.lastName);
	}

	/**
	 * Edit\Remove Question was pressed
	 */
	public void openEditorRemove(ActionEvent event) {
		try {
			tableView.getItems().clear();
			setQuestionsTableInfo();
			questionsTableAnchorPane.setVisible(true);
			addQuestionAnchorPane.setVisible(false);
			createExamAnchorPane.setVisible(false);
			examManagementAnchorPane.setVisible(false);
			clearAddQuestionFields();
			welcomeAnchorPane.setVisible(false);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	/**
	 * sends message to the server to update the data base
	 */
	public void saveButtonHandler(ActionEvent event) {
		ObservableList<Question> newQuestions = FXCollections.observableArrayList();
		ArrayList<Question> updateDB = new ArrayList<Question>();
		newQuestions = tableView.getItems();
		for (Question question : newQuestions) {
			int answerNumber = Integer.parseInt(question.getCorrectAnswer());
			if (answerNumber < 1 || answerNumber > 4) {
				Utilities.popUpMethod("incorrect answer");
				tableView.getItems().clear();
				setQuestionsTableInfo();
				questionsTableAnchorPane.setVisible(true);
				System.out.println("answerNumber 1<-->4");
				return;
			}
			updateDB.add(question);
		}
		client.handleMessageFromClientUI(updateDB);
		Utilities.popUpMethod("save");
	}

	/**
	 * Remove button was pressed
	 */
	public void removeButtonHandler(ActionEvent event) {
		Label text;
		Stage primaryStage = new Stage();
		primaryStage.setTitle("AES7Popup");
		Popup popup = new Popup();
		popup.setX(700);
		popup.setY(400);
		HBox layout = new HBox(10);
		text = new Label("Are you sure?");
		popup.getContent().addAll(text);
		Button yesButton = new Button("Yes");
		yesButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				primaryStage.hide();
				Question question = tableView.getSelectionModel().getSelectedItem();
				question.setAuthor(null);
				client.handleMessageFromClientUI(question);
				tableView.getItems().clear();
				setQuestionsTableInfo();
				questionsTableAnchorPane.setVisible(true);
			}
		});
		Button noButton = new Button("No");
		noButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				primaryStage.hide();
			}
		});
		layout.setStyle("-fx-background-color: cornsilk; -fx-padding: 10;");
		layout.getChildren().addAll(text, yesButton, noButton);
		primaryStage.setScene(new Scene(layout));
		primaryStage.show();
	}

	/**
	 * 'Add Question' was pressed
	 */
	public void openAddQuestion(ActionEvent event) {
		try {
			addQuestionAnchorPane.setVisible(true);
			questionsTableAnchorPane.setVisible(false);
			welcomeAnchorPane.setVisible(false);
			createExamAnchorPane.setVisible(false);
			examManagementAnchorPane.setVisible(false);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	/**
	 * 'Create question' button was pressed - add Question to DB
	 */
	public void addNewQuestion(ActionEvent event) {
		Question question = new Question(subjectComboBox.getValue(), firstName + " " + lastName,
				questionTextField.getText(), firstAnswerField.getText(), secondAnswerField.getText(),
				thirdAnswerField.getText(), forthAnswerField.getText(), correctAnswerComboBox.getValue());
		client.setQuestion(question);
		client.handleMessageFromClientUI(question);
		Utilities.popUpMethod("add");
		clearAddQuestionFields();
	}

	public void openCreateExam(ActionEvent event) {
		try {
			createExamAnchorPane.setVisible(true);
			addQuestionAnchorPane.setVisible(false);
			questionsTableAnchorPane.setVisible(false);
			welcomeAnchorPane.setVisible(false);
			examManagementAnchorPane.setVisible(false);
			subjectInCreateComboBox.setPromptText("Select Subject");
			subjectInCreateComboBox.getItems().addAll("Software", "Math", "Physics");
			courseInCreateComboBox.setPromptText("Select Course");
			courseInCreateComboBox.getItems().addAll("MLM", "MTM", "ATM", "OOP");
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	public void openExamManagement(ActionEvent event) {
		try {
			examManagementAnchorPane.setVisible(true);
			createExamAnchorPane.setVisible(false);
			addQuestionAnchorPane.setVisible(false);
			questionsTableAnchorPane.setVisible(false);
			welcomeAnchorPane.setVisible(false);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	// end region -> Public Methods

	// region Private Methods

	/**
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

	/**
	 * Updates the GUI questions table from the data base
	 */
	private void setQuestionsTableInfo() {
		questionsTableAnchorPane.setVisible(false);
		client.handleMessageFromClientUI(Message.editOrRemove);
		tableView.setItems(client.getQuestionsFromDB());
	}

	/**
	 * init's the fields of the 'Add Question' option
	 */
	private void initAddQuestionOption() {
		subjectComboBox.setPromptText("Select Subject");
		subjectComboBox.getItems().addAll("Software", "Math", "Physics");
		correctAnswerComboBox.setPromptText("Select");
		correctAnswerComboBox.getItems().addAll("1", "2", "3", "4");
	}

	/**
	 * clear the fields of the last question that was added
	 */
	private void clearAddQuestionFields() {
		subjectComboBox.getSelectionModel().clearSelection();
		subjectComboBox.setPromptText("Select Subject");
		subjectComboBox.setButtonCell(new ListCell<String>() {
			@Override
			protected void updateItem(String item, boolean empty) {
				super.updateItem(item, empty);
				if (empty || item == null) {
					setText("Select Subject");
				} else {
					setText(item);
				}
			}
		});
		correctAnswerComboBox.getSelectionModel().clearSelection();
		correctAnswerComboBox.setPromptText("Select");
		correctAnswerComboBox.setButtonCell(new ListCell<String>() {
			@Override
			protected void updateItem(String item, boolean empty) {
				super.updateItem(item, empty);
				if (empty || item == null) {
					setText("Select");
				} else {
					setText(item);
				}
			}
		});
		questionTextField.clear();
		firstAnswerField.clear();
		secondAnswerField.clear();
		thirdAnswerField.clear();
		forthAnswerField.clear();
	}

	// end region -> Private Methods

}