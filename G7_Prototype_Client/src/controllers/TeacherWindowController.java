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
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Callback;
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
	private Button backButtonEdit;

	@FXML
	private AnchorPane questionsTableAnchorPaneInEditOrRemove;

	@FXML
	private TableView<Question> tableViewInEditOrRemove;

	@FXML
	private TableColumn<Question, String> subjectIDColumnInEditOrRemove;

	@FXML
	private TableColumn<Question, String> questionNumColumnInEditOrRemove;

	@FXML
	private TableColumn<Question, String> authorColumnInEditOrRemove;

	@FXML
	private TableColumn<Question, String> questionTextColumnInEditOrRemove;

	@FXML
	private TableColumn<Question, String> firstPossibleAnswerColumnInEditOrRemove;

	@FXML
	private TableColumn<Question, String> secondPossibleAnswerColumnInEditOrRemove;

	@FXML
	private TableColumn<Question, String> thirdPossibleAnswerColumnInEditOrRemove;

	@FXML
	private TableColumn<Question, String> fourthPossibleAnswerColumnInEditOrRemove;

	@FXML
	private TableColumn<Question, String> correctAnswerColumnInEditOrRemove;

	@FXML
	private TableColumn<Question, CheckBox> checkColumnInEditOrRemove;

	@FXML
	private TableView<Question> tableViewByInCreateExam;

	@FXML
	private TableColumn<Question, String> subjectIDColumnInCreateExam;

	@FXML
	private TableColumn<Question, CheckBox> checkColumnInCreateExam;

	@FXML
	private TableColumn<Question, String> questionNumberColumnInCreateExam;

	@FXML
	private TableColumn<Question, String> questionTextColumnInCreateExam;

	@FXML
	private TableColumn<Question, String> firstPossibleAnswerColumnInCreateExam;

	@FXML
	private TableColumn<Question, String> secondPossibleAnswerColumnInCreateExam;

	@FXML
	private TableColumn<Question, String> thirdPossibleAnswerColumnInCreateExam;

	@FXML
	private TableColumn<Question, String> fourthPossibleAnswerColumnInCreateExam;

	@FXML
	private TableColumn<Question, String> correctAnswerColumnInCreateExam;

	@FXML
	private TableColumn<Question, String> pointsColumnInCreateExam;

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
	private TextField fourthAnswerField;

	@FXML
	private Label correcthAnswer;

	@FXML
	private ComboBox<String> correctAnswerComboBox;

	@FXML
	private Button createQuestionButton;

	@FXML
	private Button clearButton;
	
	@FXML
 	private ComboBox<String> editComboBox;

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

	// exam management

	@FXML
	private AnchorPane examManagementAnchorPane;

	@FXML
	private ComboBox<String> subjectExamManagement;
	
	@FXML
	private Button examManagementUpdateButton;
	
	@FXML
	private Button activateButton;
	
	@FXML
	private Button changeTimeButton;
	
	@FXML
	private Button LockButton;
	
	@FXML
	private Button deleteExamButton;

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

	/**
	 * 
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		date.setText(Utilities.setDate());
		this.client = MainAppClient.getClient();
		setColumnsInEditOrRemove();
		setColumnInCreateExam();
		// setQuestionsTableInfoInEditOrRemove();
		initComboBoxCreateExam();
		tableViewByInCreateExam.setEditable(true);
		tableViewByInCreateExam.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		tableViewInEditOrRemove.setEditable(true);
		tableViewInEditOrRemove.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		initAddQuestionOption();
		client.setTeacherWindowController(this);
	}

	// region Public Methods

	/**
	 * 
	 * @param event
	 * @throws Exception
	 */
	public void logOutButtonHandler(ActionEvent event) throws Exception {
		if (questionsTableAnchorPaneInEditOrRemove.isVisible()) {
			tableViewInEditOrRemove.getItems().clear();
			setQuestionsTableInfoInEditOrRemove();
			questionsTableAnchorPaneInEditOrRemove.setVisible(false);
		}
		if (addQuestionAnchorPane.isVisible()) {
			clearAddQuestionFields();
			addQuestionAnchorPane.setVisible(false);
		}
		if (createExamAnchorPane.isVisible()) {
			tableViewByInCreateExam.getItems().clear();
			createExamAnchorPane.setVisible(false);
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
			tableViewInEditOrRemove.getItems().clear();
			// setQuestionsTableInfoInEditOrRemove();
			questionsTableAnchorPaneInEditOrRemove.setVisible(true);
			addQuestionAnchorPane.setVisible(false);
			createExamAnchorPane.setVisible(false);
			examManagementAnchorPane.setVisible(false);
			clearAddQuestionFields();
			welcomeAnchorPane.setVisible(false);
			editComboBox.setPromptText("Select Subject");
			editComboBox.getItems().addAll("Software", "Math", "Physics");
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
		newQuestions = tableViewInEditOrRemove.getItems();
		for (Question question : newQuestions) {
			String answerNumber = question.getCorrectAnswer();
			if (!answerNumber.equals("1") && !answerNumber.equals("2") && !answerNumber.equals("3")
					&& !answerNumber.equals("4")) {
				Utilities.popUpMethod("incorrect answer");
				tableViewInEditOrRemove.getItems().clear();
				setQuestionsTableInfoInEditOrRemove();
				System.out.println("answerNumber 1<-->4");
				return;
			}
			updateDB.add(question);
		}
		client.handleMessageFromClientUI(new QuestionsHandle("All", updateDB));
		Utilities.popUpMethod("save");
	}

	/**
	 * Remove button was pressed
	 */
	public void removeButtonHandler(ActionEvent event) {
		Label text;
		Stage primaryStage = new Stage();
		primaryStage.setTitle("AES7");
		primaryStage.getIcons().add(new Image("boundaries/Images/AES2.png"));
		primaryStage.setHeight(100);
		primaryStage.setWidth(250);
		primaryStage.setResizable(false);
		Popup popup = new Popup();
		popup.setX(700);
		popup.setY(400);
		GridPane layout = new GridPane();
		layout.setHgap(3);
		layout.setVgap(3);
		layout.setPadding(new Insets(0, 10, 0, 10));
		text = new Label("Are you sure?");
		popup.getContent().addAll(text);
		Button yesButton = new Button("Yes");
		yesButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				primaryStage.hide();
				ObservableList<Question> selectedQuestions = tableViewInEditOrRemove.getSelectionModel()
						.getSelectedItems();
				ArrayList<Question> questions = new ArrayList<Question>();
				questions.addAll(selectedQuestions);
				client.handleMessageFromClientUI(new QuestionsHandle("Delete", questions));
				tableViewInEditOrRemove.getItems().clear();
				setQuestionsTableInfoInEditOrRemove();
			}
		});
		Button noButton = new Button("No");
		noButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				primaryStage.hide();
			}
		});
		layout.add(text, 8, 0);
		layout.add(yesButton, 9, 1);
		layout.add(noButton, 10, 1);
		layout.setStyle("-fx-background-color: cornsilk; -fx-padding: 10;");
		primaryStage.setScene(new Scene(layout));
		primaryStage.show();
	}

	/**
	 * 'Add Question' was pressed
	 */
	public void openAddQuestion(ActionEvent event) {
		try {
			addQuestionAnchorPane.setVisible(true);
			questionsTableAnchorPaneInEditOrRemove.setVisible(false);
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
		// if the user didn't select a subject
		if (subjectComboBox.getValue() == null) {
			Utilities.popUpMethod("Select Subject");
			return;
		}
		// if the user didn't filled all the fields
		if (questionTextField.getText().isEmpty() || firstAnswerField.getText().isEmpty()
				|| secondAnswerField.getText().isEmpty() || thirdAnswerField.getText().isEmpty()
				|| fourthAnswerField.getText().isEmpty()) {
			Utilities.popUpMethod("Enter Text");
			return;
		}
		// if the user didn't select the correct answer
		if (correctAnswerComboBox.getValue() == null) {
			Utilities.popUpMethod("Select Answer");
			return;
		}
		Question question = new Question(subjectComboBox.getValue(), firstName + " " + lastName,
				questionTextField.getText(), firstAnswerField.getText(), secondAnswerField.getText(),
				thirdAnswerField.getText(), fourthAnswerField.getText(), correctAnswerComboBox.getValue());
		client.setQuestion(question);
		client.handleMessageFromClientUI(new QuestionsHandle("Add", question));
		Utilities.popUpMethod("add");
		clearAddQuestionFields();
	}

	public void openCreateExam(ActionEvent event) {
		try {
			tableViewByInCreateExam.getItems().clear();
			createExamAnchorPane.setVisible(true);
			addQuestionAnchorPane.setVisible(false);
			questionsTableAnchorPaneInEditOrRemove.setVisible(false);
			welcomeAnchorPane.setVisible(false);
			examManagementAnchorPane.setVisible(false);
			clearAddQuestionFields();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	/**
	 * Init for comboBox in CreateExam
	 */
	private void initComboBoxCreateExam() {
		subjectInCreateComboBox.getSelectionModel().clearSelection();
		subjectInCreateComboBox.setPromptText("Select Subject");
		subjectInCreateComboBox.getItems().addAll("Software", "Math", "Physics");
		courseInCreateComboBox.setPromptText("Select Course");
		courseInCreateComboBox.getItems().addAll("MLM", "MTM", "ATM", "OOP");
	}

	/**
	 * Update the table by subject for create exam
	 * 
	 * @param event
	 */
	public void updateTableInCreateExam(ActionEvent event) {
		if (subjectInCreateComboBox.getValue() != null) {
			client.getQuestionsFromDB().clear();
			client.handleMessageFromClientUI(Message.getQuestionBySubject + " " + subjectInCreateComboBox.getValue());
			tableViewByInCreateExam.getItems().clear();
			tableViewByInCreateExam.setItems(client.getQuestionsFromDB());
			return;
		}
		Utilities.popUpMethod("Select Subject");
	}

	/**
	 * 
	 * @param event
	 */
	public void updateTableInEditOrRemove(ActionEvent event) {
		setQuestionsTableInfoInEditOrRemove();
	}

	public void openExamManagement(ActionEvent event) {
		try {
			examManagementAnchorPane.setVisible(true);
			createExamAnchorPane.setVisible(false);
			addQuestionAnchorPane.setVisible(false);
			questionsTableAnchorPaneInEditOrRemove.setVisible(false);
			welcomeAnchorPane.setVisible(false);
			clearAddQuestionFields();
			subjectExamManagement.setPromptText("Select Subject");
			subjectExamManagement.getItems().addAll("Software", "Math", "Physics");
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	public void activateButtonHandler(ActionEvent event) {
		Label text = null;
		Stage primaryStage = new Stage();
		primaryStage.setTitle("AES7");
		primaryStage.getIcons().add(new Image("boundaries/Images/AES2.png"));
		Popup popup = new Popup();
		popup.setX(700);
		popup.setY(400);
		HBox layout = new HBox(10);
		text = new Label("Please enter an execution code:");
		popup.getContent().addAll(text);
		TextField executionCode = new TextField();
		executionCode.setEditable(true);
		Button okButton = new Button("OK");
		okButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				//get selected exam
				//create reference to ActiveExam
				//getText in TextField
				//update DB
				primaryStage.hide();
			}
		});
		Button cancelButton = new Button("Cancel");
		cancelButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				primaryStage.hide();
			}
		});
		layout.setStyle("-fx-background-color: cornsilk; -fx-padding: 10;");
		layout.getChildren().addAll(text, executionCode, okButton, cancelButton);
		primaryStage.setScene(new Scene(layout));
		primaryStage.show();
	}
	
	public void changeTimeButtonHandler(ActionEvent event) {
		Label text = null;
		Stage primaryStage = new Stage();
		primaryStage.setTitle("AES7");
		primaryStage.getIcons().add(new Image("boundaries/Images/AES2.png"));
		Popup popup = new Popup();
		popup.setX(700);
		popup.setY(400);
		HBox layout = new HBox(10);
		text = new Label("Please enter new exam duration:");
		popup.getContent().addAll(text);
		TextField newDuration = new TextField();
		newDuration.setEditable(true);
		Button okButton = new Button("OK");
		okButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				//get selected exam
				//getText in TextField
				//change exam duration to newDuration
				//update DB
				primaryStage.hide();
			}
		});
		Button cancelButton = new Button("Cancel");
		cancelButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				primaryStage.hide();
			}
		});
		layout.setStyle("-fx-background-color: cornsilk; -fx-padding: 10;");
		layout.getChildren().addAll(text, newDuration, okButton, cancelButton);
		primaryStage.setScene(new Scene(layout));
		primaryStage.show();
	}

	public void clearButtonPressed(ActionEvent event) {
		clearAddQuestionFields();
	}

	/**
	 * With this method you can jump between the fields in add question screen
	 * 
	 * @param event
	 */
	public void keyHandlerInAddQuestionScreen(KeyEvent event) {
		KeyCode code = event.getCode();
		if (code == KeyCode.ENTER) {
			if (questionTextField.isFocused())
				firstAnswerField.requestFocus();
			else if (firstAnswerField.isFocused())
				secondAnswerField.requestFocus();
			else if (secondAnswerField.isFocused())
				thirdAnswerField.requestFocus();
			else if (thirdAnswerField.isFocused())
				fourthAnswerField.requestFocus();
			else if (fourthAnswerField.isFocused())
				questionTextField.requestFocus();
		}
	}
	
	public void backToMainScreen(ActionEvent event) {
		welcomeAnchorPane.setVisible(true);
		addQuestionAnchorPane.setVisible(false);
		questionsTableAnchorPaneInEditOrRemove.setVisible(false);
		createExamAnchorPane.setVisible(false);
		examManagementAnchorPane.setVisible(false);
	}

	// end region -> Public Methods

	// region Private Methods

	/**
	 * Define the columns
	 */
	private void setColumnsInEditOrRemove() {
		subjectIDColumnInEditOrRemove.setCellValueFactory(new PropertyValueFactory<>("questionSubject"));
		questionNumColumnInEditOrRemove.setCellValueFactory(new PropertyValueFactory<>("questionNum"));
		authorColumnInEditOrRemove.setCellValueFactory(new PropertyValueFactory<>("author"));
		questionTextColumnInEditOrRemove.setCellValueFactory(new PropertyValueFactory<>("questionText"));
		firstPossibleAnswerColumnInEditOrRemove.setCellValueFactory(new PropertyValueFactory<>("firstPossibleAnswer"));
		secondPossibleAnswerColumnInEditOrRemove
				.setCellValueFactory(new PropertyValueFactory<>("secondPossibleAnswer"));
		thirdPossibleAnswerColumnInEditOrRemove.setCellValueFactory(new PropertyValueFactory<>("thirdPossibleAnswer"));
		fourthPossibleAnswerColumnInEditOrRemove
				.setCellValueFactory(new PropertyValueFactory<>("fourthPossibleAnswer"));
		correctAnswerColumnInEditOrRemove.setCellValueFactory(new PropertyValueFactory<>("correctAnswer"));

		// Callback<TableColumn<Question, String>, TableCell<Question, String>>
		// cellFactory = new DragSelectionCellFactory();
		// correctAnswerColumnInEditOrRemove.setCellFactory(cellFactory);
		// questionSubjectColumnInEditOrRemove.setCellFactory(cellFactory);
		// questionNumColumnInEditOrRemove.setCellFactory(cellFactory);
		// authorColumnInEditOrRemove.setCellFactory(cellFactory);
		// questionTextColumnInEditOrRemove.setCellFactory(cellFactory);
		// firstPossibleAnswerColumnInEditOrRemove.setCellFactory(cellFactory);
		// secondPossibleAnswerColumnInEditOrRemove.setCellFactory(cellFactory);
		// thirdPossibleAnswerColumnInEditOrRemove.setCellFactory(cellFactory);
		// fourthPossibleAnswerColumnInEditOrRemove.setCellFactory(cellFactory);

		// define the columns editable

		questionTextColumnInEditOrRemove.setCellFactory(TextFieldTableCell.forTableColumn());
		questionTextColumnInEditOrRemove.setOnEditCommit(new EventHandler<CellEditEvent<Question, String>>() {
			@Override
			public void handle(CellEditEvent<Question, String> t) {
				((Question) t.getTableView().getItems().get(t.getTablePosition().getRow()))
						.setQuestionText(t.getNewValue());
			}
		});
		firstPossibleAnswerColumnInEditOrRemove.setCellFactory(TextFieldTableCell.forTableColumn());
		firstPossibleAnswerColumnInEditOrRemove.setOnEditCommit(new EventHandler<CellEditEvent<Question, String>>() {
			@Override
			public void handle(CellEditEvent<Question, String> t) {
				((Question) t.getTableView().getItems().get(t.getTablePosition().getRow()))
						.setFirstPossibleAnswer(t.getNewValue());
			}
		});
		secondPossibleAnswerColumnInEditOrRemove.setCellFactory(TextFieldTableCell.forTableColumn());
		secondPossibleAnswerColumnInEditOrRemove.setOnEditCommit(new EventHandler<CellEditEvent<Question, String>>() {
			@Override
			public void handle(CellEditEvent<Question, String> t) {
				((Question) t.getTableView().getItems().get(t.getTablePosition().getRow()))
						.setSecondPossibleAnswer(t.getNewValue());
			}
		});
		thirdPossibleAnswerColumnInEditOrRemove.setCellFactory(TextFieldTableCell.forTableColumn());
		thirdPossibleAnswerColumnInEditOrRemove.setOnEditCommit(new EventHandler<CellEditEvent<Question, String>>() {
			@Override
			public void handle(CellEditEvent<Question, String> t) {
				((Question) t.getTableView().getItems().get(t.getTablePosition().getRow()))
						.setThirdPossibleAnswer(t.getNewValue());
			}
		});
		fourthPossibleAnswerColumnInEditOrRemove.setCellFactory(TextFieldTableCell.forTableColumn());
		fourthPossibleAnswerColumnInEditOrRemove.setOnEditCommit(new EventHandler<CellEditEvent<Question, String>>() {
			@Override
			public void handle(CellEditEvent<Question, String> t) {
				((Question) t.getTableView().getItems().get(t.getTablePosition().getRow()))
						.setFourthPossibleAnswer(t.getNewValue());
			}
		});
		correctAnswerColumnInEditOrRemove.setCellFactory(TextFieldTableCell.forTableColumn());
		correctAnswerColumnInEditOrRemove.setOnEditCommit(new EventHandler<CellEditEvent<Question, String>>() {
			@Override
			public void handle(CellEditEvent<Question, String> t) {
				((Question) t.getTableView().getItems().get(t.getTablePosition().getRow()))
						.setCorrectAnswer(t.getNewValue());
			}
		});
	}

	/**
	 * Define the columns by subject
	 */
	private void setColumnInCreateExam() {
		subjectIDColumnInCreateExam.setCellValueFactory(new PropertyValueFactory<>("questionSubject"));
		questionNumberColumnInCreateExam.setCellValueFactory(new PropertyValueFactory<>("questionNum"));
		questionTextColumnInCreateExam.setCellValueFactory(new PropertyValueFactory<>("questionText"));
		firstPossibleAnswerColumnInCreateExam.setCellValueFactory(new PropertyValueFactory<>("firstPossibleAnswer"));
		secondPossibleAnswerColumnInCreateExam.setCellValueFactory(new PropertyValueFactory<>("secondPossibleAnswer"));
		thirdPossibleAnswerColumnInCreateExam.setCellValueFactory(new PropertyValueFactory<>("thirdPossibleAnswer"));
		fourthPossibleAnswerColumnInCreateExam.setCellValueFactory(new PropertyValueFactory<>("fourthPossibleAnswer"));
		correctAnswerColumnInCreateExam.setCellValueFactory(new PropertyValueFactory<>("correctAnswer"));
		pointsColumnInCreateExam.setCellValueFactory(new PropertyValueFactory<>("points"));

		// define the columns editable

		pointsColumnInCreateExam.setCellFactory(TextFieldTableCell.forTableColumn());
		pointsColumnInCreateExam.setOnEditCommit(new EventHandler<CellEditEvent<Question, String>>() {
			@Override
			public void handle(CellEditEvent<Question, String> t) {
				((Question) t.getTableView().getItems().get(t.getTablePosition().getRow())).setPoints(t.getNewValue());
			}
		});
	}

	/**
	 * Updates the GUI questions table from the data base
	 */
	private void setQuestionsTableInfoInEditOrRemove() {
		client.handleMessageFromClientUI(Message.editOrRemove + " " + firstName + " " + lastName);
		tableViewInEditOrRemove.setItems(client.getQuestionsFromDB());
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
	 * clear the fields of the last question that was added or when clear button was
	 * pressed
	 */
	private void clearAddQuestionFields() {
		if (subjectComboBox.getValue() != null) {
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
		}
		if (correctAnswerComboBox.getValue() != null) {
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
		}
		if (!questionTextField.getText().isEmpty()) {
			questionTextField.clear();
		}
		if (!firstAnswerField.getText().isEmpty()) {
			firstAnswerField.clear();
		}
		if (!secondAnswerField.getText().isEmpty()) {
			secondAnswerField.clear();
		}
		if (!thirdAnswerField.getText().isEmpty()) {
			thirdAnswerField.clear();
		}
		if (!fourthAnswerField.getText().isEmpty()) {
			fourthAnswerField.clear();
		}
	}

	// end region -> Private Methods

	// private class DragSelectionCell extends TableCell<Question, String> {
	// public DragSelectionCell() {
	// setOnDragDetected(new EventHandler<MouseEvent>() {
	// @Override
	// public void handle(MouseEvent event) {
	// startFullDrag();
	// // getTableColumn().getTableView().getSelectionModel().select(getIndex(),
	// // getTableColumn());
	// }
	// });
	// setOnMouseDragEntered(new EventHandler<MouseDragEvent>() {
	// @Override
	// public void handle(MouseDragEvent event) {
	// getTableColumn().getTableView().getSelectionModel().select(getIndex(),
	// getTableColumn());
	// }
	// });
	// }
	// }
	//
	// private class DragSelectionCellFactory
	// implements Callback<TableColumn<Question, String>, TableCell<Question,
	// String>> {
	// @Override
	// public TableCell<Question, String> call(final TableColumn<Question, String>
	// col) {
	// return new DragSelectionCell();
	// }
	// }
}