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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
import resources.*;

public class TeacherWindowController implements Initializable, IScreenController {

	// region Fields

	@FXML
	private AnchorPane mainAnchorPane;

	@FXML
	private AnchorPane welcomeAnchorPane;

	@FXML
	private AnchorPane backAnchorPane;

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
	private ComboBox<String> subjectComboBoxInEditOrRemove;

	// create exam

	@FXML
	private AnchorPane createExamAnchorPane;

	/***************************************************/

	@FXML
	private AnchorPane anchorPaneInCreateExamFirstWindow;

	@FXML
	private ComboBox<String> subjectInCreateExamComboBox;

	@FXML
	private ComboBox<String> courseInCreateExamComboBox;

	@FXML
	private TextField durationInCreateExamField;

	@FXML
	private Button createExamNextButtonFirstWindow;

	/***************************************************/

	@FXML
	private AnchorPane anchorPaneInCreateExamSecondWindow;

	@FXML
	private TableView<Question> tableViewInCreateExamAllQuestion;

	@FXML
	private TableColumn<Question, String> subjectIDColumnInCreateExamAllQuestions;

	@FXML
	private TableColumn<Question, String> questionNumColumnInCreateExamAllQuestions;

	@FXML
	private TableColumn<Question, String> authorColumnInCreateExamAllQuestions;

	@FXML
	private TableColumn<Question, String> questionTextColumnInCreateExamAllQuestions;

	@FXML
	private TableColumn<Question, String> possibleAnswersColumnInCreateExamAllQuestions;

	@FXML
	private TableColumn<Question, String> firstPossibleAnswerColumnInCreateExamAllQuestions;

	@FXML
	private TableColumn<Question, String> secondPossibleAnswerColumnInCreateExamAllQuestions;

	@FXML
	private TableColumn<Question, String> thirdPossibleAnswerColumnInCreateExamAllQuestions;

	@FXML
	private TableColumn<Question, String> fourthPossibleAnswerColumnInCreateExamAllQuestions;

	@FXML
	private TableColumn<Question, String> correctAnswerColumnInCreateExamAllQuestions;

	@FXML
	private TableView<QuestionInExam> tableViewInCreateExamQuestion;

	@FXML
	private TableColumn<Question, String> subjectIDColumnInCreateExam;

	@FXML
	private TableColumn<Question, String> questionNumberColumnInCreateExam;

	@FXML
	private TableColumn<Question, String> questionTextColumnInCreateExam;

	@FXML
	private TableColumn<QuestionInExam, Integer> pointsColumnInCreateExam;

	@FXML
	private Button createExamNextButtonSecondWindow;

	/***************************************************/

	@FXML
	private AnchorPane anchorPaneInCreateExamThirdWindow;

	@FXML
	private TextArea textAreaStudentsInCreateExam;

	@FXML
	private TextArea textAreaTeachersInCreateExam;

	@FXML
	private Button createExamButton;

	/***************************************************/

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

	@FXML
	private Button showExamsButtonInExamManagement;

	@FXML
	private TableColumn<Exam, String> subjectColInExamsManagement;

	@FXML
	private TableColumn<Exam, String> courseColInExamsManagement;

	@FXML
	private TableColumn<Exam, String> examNumColInExamsManagement;

	@FXML
	private TableColumn<Exam, String> authorColInExamsManagement;

	@FXML
	private TableColumn<Exam, String> durationColInExamsManagement;

	@FXML
	private TableColumn<Exam, String> textExamineesColInExamsManagement;

	@FXML
	private TableColumn<Exam, String> textTeachersColInExamsManagement;

	@FXML
	private TableView<Exam> tableViewInExamsManagement;
	
	@FXML
	private TextField executionCode;

	//

	private ScreensController screensController;

	private String firstName;

	private String lastName;

	private Client client;

	private Exam exam;

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
		backAnchorPane.setVisible(false);
		date.setText(Utilities.setDate());
		this.client = MainAppClient.getClient();
		setColumnsInEditOrRemove();
		setColumnInCreateExamAllQuestions();
		setColumnInCreateExamQuestions();
		setColumnsInExamsManagement();
		initAnchorPaneInCreateExamFirstWindow();
		tableViewInCreateExamQuestion.setEditable(true);
		tableViewInCreateExamQuestion.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		tableViewInEditOrRemove.setEditable(true);
		tableViewInEditOrRemove.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		tableViewInCreateExamAllQuestion.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
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
			tableViewInCreateExamAllQuestion.getItems().clear();
			tableViewInCreateExamQuestion.getItems().clear();
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
			setSubjectComboBox(subjectComboBoxInEditOrRemove);
			tableViewInEditOrRemove.getItems().clear();
			questionsTableAnchorPaneInEditOrRemove.setVisible(true);
			backAnchorPane.setVisible(true);
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
		newQuestions = tableViewInEditOrRemove.getItems();
		for (Question question : newQuestions) {
			String answerNumber = question.getCorrectAnswer();
			if (!answerNumber.equals("1") && !answerNumber.equals("2") && !answerNumber.equals("3")
					&& !answerNumber.equals("4")) {
				Utilities.popUpMethod("IncorrectAnswer");
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

	@FXML
	void redArrowUp(MouseEvent event) {
		ObservableList<QuestionInExam> regretQuestions = tableViewInCreateExamQuestion.getSelectionModel()
				.getSelectedItems();
		if (!regretQuestions.isEmpty()) {
			ArrayList<QuestionInExam> questionsToDelete = new ArrayList<>();
			questionsToDelete.addAll(regretQuestions);
			for (QuestionInExam question : regretQuestions)
				if (question.getPoints() != 0)
					question.setPoints(0);
			tableViewInCreateExamQuestion.getItems().removeAll(questionsToDelete);
			for (QuestionInExam question : questionsToDelete)
				tableViewInCreateExamAllQuestion.getItems().add(question.getQuestion());
			initTablesInCreateExam(true, true);
		} else // if the teacher didn't choose anything
			Utilities.popUpMethod("SelectQuestions");
	}

	@FXML
	void greenArrowDown(MouseEvent event) {
		ObservableList<Question> questionsFromAllTable = tableViewInCreateExamAllQuestion.getSelectionModel()
				.getSelectedItems();
		if (!questionsFromAllTable.isEmpty()) {
			ArrayList<Question> questionsToAdd = new ArrayList<>();
			questionsToAdd.addAll(questionsFromAllTable);
			tableViewInCreateExamAllQuestion.getItems().removeAll(questionsToAdd);
			for (Question question : questionsToAdd)
				tableViewInCreateExamQuestion.getItems().add(new QuestionInExam(exam, question));
			initTablesInCreateExam(true, true);
		} else // if the teacher didn't choose anything
			Utilities.popUpMethod("SelectQuestions");
	}

	public void checkTotalPointsInCreateExam(MouseEvent event) {
		int totalPoints = 0;
		for (QuestionInExam question : tableViewInCreateExamQuestion.getItems()) {
			int points = question.getPoints();
			if ((points < 1) || (points > 100)) {
				Utilities.popUpMethod("Points" + " " + question.getQuestion().getQuestionNum());
				return;
			}
			totalPoints += points;
		}
		if (totalPoints != 100) {
			Utilities.popUpMethod("TotalPoints");
		} else {
			exam.setQuestions(tableViewInCreateExamQuestion.getItems());
			System.out.println(exam.getQuestions());
			anchorPaneInCreateExamSecondWindow.setVisible(false);
			anchorPaneInCreateExamThirdWindow.setVisible(true);
		}
	}

	public void createFinalExam(MouseEvent event) {
		if (!textAreaStudentsInCreateExam.getText().isEmpty()) {
			exam.setFreeTextForExaminees(textAreaStudentsInCreateExam.getText());
		}
		if (!textAreaTeachersInCreateExam.getText().isEmpty()) {
			exam.setFreeTextForTeacherOnly(textAreaTeachersInCreateExam.getText());
		}
		client.handleMessageFromClientUI(new ExamHandle(Message.exam, exam));
		Utilities.popUpMethod("Exam");
	}

	/**
	 * 
	 * @param allquestions
	 * @param selected
	 */
	private void initTablesInCreateExam(boolean allquestions, boolean selected) {
		if (allquestions) {
			tableViewInCreateExamAllQuestion.refresh();
		}
		if (selected) {
			tableViewInCreateExamQuestion.refresh();
		}
	}

	/**
	 * 'Add Question' was pressed
	 */
	public void openAddQuestion(ActionEvent event) {
		try {
			addQuestionAnchorPane.setVisible(true);
			backAnchorPane.setVisible(true);
			questionsTableAnchorPaneInEditOrRemove.setVisible(false);
			welcomeAnchorPane.setVisible(false);
			createExamAnchorPane.setVisible(false);
			examManagementAnchorPane.setVisible(false);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	public void realodTablesInCreateExam(MouseEvent event) {
		initTablesInCreateExam(true, true);
	}

	/**
	 * 'Create question' button was pressed - add Question to DB
	 */
	public void addNewQuestion(ActionEvent event) {
		// if the user didn't select a subject
		if (subjectComboBox.getValue() == null) {
			Utilities.popUpMethod("SelectSubject");
			return;
		}
		// if the user didn't filled all the fields
		if (questionTextField.getText().isEmpty() || firstAnswerField.getText().isEmpty()
				|| secondAnswerField.getText().isEmpty() || thirdAnswerField.getText().isEmpty()
				|| fourthAnswerField.getText().isEmpty()) {
			Utilities.popUpMethod("EnterText");
			return;
		}
		// if the user didn't select the correct answer
		if (correctAnswerComboBox.getValue() == null) {
			Utilities.popUpMethod("SelectAnswer");
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
			tableViewInCreateExamAllQuestion.getItems().clear();
			createExamAnchorPane.setVisible(true);
			anchorPaneInCreateExamFirstWindow.setVisible(true);
			anchorPaneInCreateExamSecondWindow.setVisible(false);
			anchorPaneInCreateExamThirdWindow.setVisible(false);
			backAnchorPane.setVisible(true);
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
	 * 
	 * @param event
	 */
	public void createExamInit(ActionEvent event) {
		Boolean flag = true;
		String subject = subjectInCreateExamComboBox.getValue();
		if (subject == null) {
			flag = false;
			Utilities.popUpMethod("SelectSubject");
		}
		String course = courseInCreateExamComboBox.getValue();
		if (course == null) {
			flag = false;
			Utilities.popUpMethod("SelectCourse");
		}
		try {
			int duration = Integer.parseInt(durationInCreateExamField.getText());
			if (duration <= 0) {
				flag = false;
				Utilities.popUpMethod("Duration");
			}
			if (flag) {
				exam = new Exam(subject, course, duration, firstName + " " + lastName);
				anchorPaneInCreateExamFirstWindow.setVisible(false);
				client.getQuestionsFromDB().clear();
				anchorPaneInCreateExamSecondWindow.setVisible(true);
				setTableInCreateExamAllQuestions();
			}
		} catch (NumberFormatException e) {
			flag = false;
			Utilities.popUpMethod("Duration");
		}
	}

	public void showExamsHandler(ActionEvent event) {
		setTableInExamsManagement();
	}

	private void setTableInExamsManagement() {
		client.getExamsFromDB().clear();
		client.handleMessageFromClientUI(Message.getExamBySubject + " " + subjectExamManagement.getValue());
		tableViewInExamsManagement.setItems(client.getExamsFromDB());
	}

	private void setColumnsInExamsManagement() {
		subjectColInExamsManagement.setCellValueFactory(new PropertyValueFactory<>("subjectID"));
		courseColInExamsManagement.setCellValueFactory(new PropertyValueFactory<>("courseID"));
		examNumColInExamsManagement.setCellValueFactory(new PropertyValueFactory<>("examNum"));
		authorColInExamsManagement.setCellValueFactory(new PropertyValueFactory<>("teacherName"));
		durationColInExamsManagement.setCellValueFactory(new PropertyValueFactory<>("examDuration"));
		textExamineesColInExamsManagement.setCellValueFactory(new PropertyValueFactory<>("freeTextForExaminees"));
		textTeachersColInExamsManagement.setCellValueFactory(new PropertyValueFactory<>("freeTextForTeacherOnly"));

	}

	/**
	 * Initialize for comboBox in CreateExam
	 */
	private void initAnchorPaneInCreateExamFirstWindow() {
		setSubjectComboBox(subjectInCreateExamComboBox);
		setCourseComboBox(courseInCreateExamComboBox);
		durationInCreateExamField.setText(null);
	}

	/**
	 * Update the table by subject for create exam
	 * 
	 * @param event
	 */
	private void setTableInCreateExamAllQuestions() {
		client.getQuestionsFromDB().clear();
		client.handleMessageFromClientUI(Message.getQuestionBySubject + " " + subjectInCreateExamComboBox.getValue());
		initTablesInCreateExam(true, false);
		tableViewInCreateExamAllQuestion.setItems(client.getQuestionsFromDB());
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
			backAnchorPane.setVisible(true);
			createExamAnchorPane.setVisible(false);
			addQuestionAnchorPane.setVisible(false);
			questionsTableAnchorPaneInEditOrRemove.setVisible(false);
			welcomeAnchorPane.setVisible(false);
			clearAddQuestionFields();
			setSubjectComboBox(subjectExamManagement);
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
		executionCode = new TextField();
		executionCode.setPrefWidth(50);
		executionCode.setEditable(true);
		Button okButton = new Button("OK");
		okButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Exam selectedExam = tableViewInExamsManagement.getSelectionModel().getSelectedItem();
				// check executionCode
				ActiveExam activeExam = new ActiveExam(selectedExam, executionCode.getText());
				client.handleMessageFromClientUI(new ActiveExamHandle("Activate", activeExam));
				//tableViewInExamsManagement.getItems().clear();
				Utilities.popUpMethod("Exam activated successfully!");
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
//		primaryStage.setHeight(100);
//		primaryStage.setWidth(250);
//		primaryStage.setResizable(false);
		Popup popup = new Popup();
		popup.setX(700);
		popup.setY(400);
		HBox layout = new HBox(10);
		text = new Label("Please enter an execution code:");
		executionCode = new TextField();
		executionCode.setPrefWidth(50);
		executionCode.setEditable(true);
		TextField newDuration = new TextField("Enter new time here.");
		TextField reason = new TextField("Enter reason here.");
		popup.getContent().addAll(text, reason);
//		newDuration.setPrefWidth(45);
		newDuration.setEditable(true);
		reason.setEditable(true);
		Button okButton = new Button("Send");
		okButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
//				Exam selectedExam = tableViewInExamsManagement.getSelectionModel().getSelectedItem();
//				// check executionCode
//				ActiveExam activeExam = new ActiveExam(selectedExam, executionCode.getText());
//				client.handleMessageFromClientUI(new ActiveExamHandle("ChangeTime", activeExam));
//				//tableViewInExamsManagement.getItems().clear();
//				Utilities.popUpMethod("Exam activated successfully!");
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
		layout.getChildren().addAll(text, executionCode, newDuration, reason, okButton, cancelButton);
		primaryStage.setScene(new Scene(layout));
		primaryStage.show();
	}
	
	public void lockButtonHandler(ActionEvent event) {
		Label text = null;
		Stage primaryStage = new Stage();
		primaryStage.setTitle("AES7");
		primaryStage.getIcons().add(new Image("boundaries/Images/AES2.png"));
		Popup popup = new Popup();
		popup.setX(700);
		popup.setY(400);
		HBox layout = new HBox(10);
		text = new Label("Please enter the execution code of the exam:");
		popup.getContent().addAll(text);
		executionCode = new TextField();
		executionCode.setPrefWidth(50);
		executionCode.setEditable(true);
		Button okButton = new Button("OK");
		okButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Exam selectedExam = tableViewInExamsManagement.getSelectionModel().getSelectedItem();
				// check executionCode
				ActiveExam activeExam = new ActiveExam(selectedExam, executionCode.getText());
				client.handleMessageFromClientUI(new ActiveExamHandle("Lock", activeExam));
				//tableViewInExamsManagement.getItems().clear();
				Utilities.popUpMethod("Exam locked successfully!");
				primaryStage.hide();
			}
		});
//		Button cancelButton = new Button("Cancel");
//		cancelButton.setOnAction(new EventHandler<ActionEvent>() {
//			@Override
//			public void handle(ActionEvent event) {
//				primaryStage.hide();
//			}
//		});
		
		Button closeButton = new Button("Close");
		closeButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				primaryStage.hide();
			}
		});
		layout.setStyle("-fx-background-color: cornsilk; -fx-padding: 10;");
		layout.getChildren().addAll(text, executionCode, okButton, closeButton);
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

	public void backToMainScreen(MouseEvent event) {
		welcomeAnchorPane.setVisible(true);
		backAnchorPane.setVisible(false);
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
		subjectIDColumnInEditOrRemove.setCellValueFactory(new PropertyValueFactory<>("subjectID"));
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
	private void setColumnInCreateExamAllQuestions() {
		subjectIDColumnInCreateExamAllQuestions.setCellValueFactory(new PropertyValueFactory<>("subjectID"));
		questionNumColumnInCreateExamAllQuestions.setCellValueFactory(new PropertyValueFactory<>("questionNum"));
		authorColumnInCreateExamAllQuestions.setCellValueFactory(new PropertyValueFactory<>("author"));
		questionTextColumnInCreateExamAllQuestions.setCellValueFactory(new PropertyValueFactory<>("questionText"));
		firstPossibleAnswerColumnInCreateExamAllQuestions
				.setCellValueFactory(new PropertyValueFactory<>("firstPossibleAnswer"));
		secondPossibleAnswerColumnInCreateExamAllQuestions
				.setCellValueFactory(new PropertyValueFactory<>("secondPossibleAnswer"));
		thirdPossibleAnswerColumnInCreateExamAllQuestions
				.setCellValueFactory(new PropertyValueFactory<>("thirdPossibleAnswer"));
		fourthPossibleAnswerColumnInCreateExamAllQuestions
				.setCellValueFactory(new PropertyValueFactory<>("fourthPossibleAnswer"));
		correctAnswerColumnInCreateExamAllQuestions.setCellValueFactory(new PropertyValueFactory<>("correctAnswer"));
	}

	/**
	 * 
	 */
	private void setColumnInCreateExamQuestions() {
		subjectIDColumnInCreateExam.setCellValueFactory(new PropertyValueFactory<>("subjectID"));
		questionNumberColumnInCreateExam.setCellValueFactory(new PropertyValueFactory<>("questionNum"));
		questionTextColumnInCreateExam.setCellValueFactory(new PropertyValueFactory<>("questionText"));
		pointsColumnInCreateExam.setCellValueFactory(new PropertyValueFactory<>("points"));

		// define the columns editable

		pointsColumnInCreateExam.setCellFactory(
				TextFieldTableCell.<QuestionInExam, Integer>forTableColumn(new IntegerStringConverter()));
		pointsColumnInCreateExam.setOnEditCommit(new EventHandler<CellEditEvent<QuestionInExam, Integer>>() {
			@Override
			public void handle(CellEditEvent<QuestionInExam, Integer> t) {
				((QuestionInExam) t.getTableView().getItems().get(t.getTablePosition().getRow()))
						.setPoints(t.getNewValue());
			}
		});
	}

	/**
	 * Updates the GUI questions table from the data base
	 */
	private void setQuestionsTableInfoInEditOrRemove() {
		String subject = subjectComboBoxInEditOrRemove.getValue();
		if (subject != null) {
			client.handleMessageFromClientUI(Message.editOrRemove + " " + firstName + " " + lastName + " " + subject);
		} else {
			Utilities.popUpMethod("SelectSubject");
		}
		tableViewInEditOrRemove.setItems(client.getQuestionsFromDB());
	}

	/**
	 * init's the fields of the 'Add Question' option
	 */
	private void initAddQuestionOption() {
		setSubjectComboBox(subjectComboBox);
		setSelectComboBox(correctAnswerComboBox);
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

	/**
	 * <p>
	 * Select Subject
	 * </p>
	 * Software, Math, Physics.
	 * 
	 * @param comboBox
	 */
	private void setSubjectComboBox(ComboBox<String> comboBox) {
		comboBox.getSelectionModel().clearSelection();
		comboBox.setPromptText("Select Subject");
		client.handleMessageFromClientUI(Message.getSubjects);
		comboBox.setItems(client.getSubjects());
	}

	/**
	 * <p>
	 * Select Course
	 * </p>
	 * MLM, MTM, ATM, OOP.
	 * 
	 * @param comboBox
	 */
	private void setCourseComboBox(ComboBox<String> comboBox) {
		comboBox.getSelectionModel().clearSelection();
		comboBox.setPromptText("Select Course");
		client.handleMessageFromClientUI(Message.getCourses);
		comboBox.setItems(client.getCourses());
	}

	/**
	 * <p>
	 * Select
	 * </p>
	 * 1, 2, 3, 4.
	 * 
	 * @param comboBox
	 */
	private void setSelectComboBox(ComboBox<String> comboBox) {
		comboBox.getSelectionModel().clearSelection();
		comboBox.setPromptText("Select");
		comboBox.getItems().addAll("1", "2", "3", "4");
	}

	// end region -> Private Methods

}