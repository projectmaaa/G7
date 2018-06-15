package controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import client.Client;
import client.MainAppClient;
import javafx.beans.property.SimpleStringProperty;
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

	/* Active Exam Management */

	@FXML
	private AnchorPane activeExamManagementAnchorPane;

	@FXML
	private TableView<ActiveExam> activeExamsTableView;

	@FXML
	private TableColumn<ActiveExam, String> subjectNumberOfActiveExam;

	@FXML
	private TableColumn<ActiveExam, String> courseNumberOfAvticeExam;

	@FXML
	private TableColumn<ActiveExam, String> examNumberOfAvticeExam;

	@FXML
	private TableColumn<ActiveExam, String> executionCodeOfAvticeExam;

	@FXML
	private TableColumn<ActiveExam, Integer> durationOfAvticeExam;

	@FXML
	private TableColumn<ActiveExam, String> typeOfAvticeExam;

	@FXML
	private Button showActiveExamButtonInExamsManagement;

	@FXML
	private ComboBox<String> subjectsActiveExamManagement;

	@FXML
	private Button LockButton;

	@FXML
	private Button changeTimeButton;

	// confirm grades

	@FXML
	private AnchorPane confirmGradesAnchorPane;

	@FXML
	private TableView<CheckedExam> confirmGradeTableView;

	@FXML
	private TableColumn<CheckedExam, String> subjectColInConfirmGrades;

	@FXML
	private TableColumn<CheckedExam, String> courseColInConfirmGrades;

	@FXML
	private TableColumn<CheckedExam, String> examNumColInConfirmGrades;

	@FXML
	private TableColumn<CheckedExam, String> executionCodeColInConfirmGrades;

	@FXML
	private TableColumn<CheckedExam, String> studentIDColInConfirmGrades;

	@FXML
	private TableColumn<CheckedExam, Integer> gradeColInConfirmGrades;

	@FXML
	private Button approveButtonInConfirmGrade;

	@FXML
	private Button changeGradeButtonInConfirmGrades;

	@FXML
	private Button addCommentsButtonInConfirmGrades;

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
		date.setText(Utilities_Client.setDate());
		this.client = MainAppClient.getClient();
		setColumnsInEditOrRemove();
		setColumnInCreateExamAllQuestions();
		setColumnInCreateExamQuestions();
		setColumnsInExamsManagement();
		setColumnInConfirmGrades();
		setColumnsOfActiveExams();
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
			confirmGradesAnchorPane.setVisible(false);
			activeExamManagementAnchorPane.setVisible(false);
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
				Utilities_Client.popUpMethod("Please insert only numbers from 1 to 4 in the correct answer column");
				tableViewInEditOrRemove.getItems().clear();
				setQuestionsTableInfoInEditOrRemove();
				System.out.println("answerNumber 1<-->4");
				return;
			}
			updateDB.add(question);
		}
		client.handleMessageFromClientUI(new QuestionHandle("All", updateDB));
		Utilities_Client.popUpMethod("Question updated successfully");
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
				client.handleMessageFromClientUI(new QuestionHandle("Delete", questions));
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
			Utilities_Client.popUpMethod("Please Select Questions");
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
			Utilities_Client.popUpMethod("Please Select Questions");
	}

	public void checkTotalPointsInCreateExam(MouseEvent event) {
		int totalPoints = 0;
		for (QuestionInExam question : tableViewInCreateExamQuestion.getItems()) {
			int points = question.getPoints();
			if ((points < 1) || (points > 100)) {
				Utilities_Client.popUpMethod(
						"Wrong amount of points in question number" + " " + question.getQuestion().getQuestionNum());
				return;
			}
			totalPoints += points;
		}
		if (totalPoints != 100) {
			Utilities_Client.popUpMethod("The total amount of points isn't 100");
		} else {
			exam.setQuestions(tableViewInCreateExamQuestion.getItems());
			System.out.println(exam.getQuestions());
			anchorPaneInCreateExamSecondWindow.setVisible(false);
			anchorPaneInCreateExamThirdWindow.setVisible(true);
			clearFieldsInAddExamWindows("second");
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
		Utilities_Client.popUpMethod("Exam created successfully");
		anchorPaneInCreateExamThirdWindow.setVisible(false);
		clearFieldsInAddExamWindows("third");
		anchorPaneInCreateExamFirstWindow.setVisible(true);
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
			confirmGradesAnchorPane.setVisible(false);
			activeExamManagementAnchorPane.setVisible(false);
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
			Utilities_Client.popUpMethod("Please Select Subject");
			return;
		}
		// if the user didn't filled all the fields
		if (questionTextField.getText().isEmpty() || firstAnswerField.getText().isEmpty()
				|| secondAnswerField.getText().isEmpty() || thirdAnswerField.getText().isEmpty()
				|| fourthAnswerField.getText().isEmpty()) {
			Utilities_Client.popUpMethod("Please Enter Text");
			return;
		}
		// if the user didn't select the correct answer
		if (correctAnswerComboBox.getValue() == null) {
			Utilities_Client.popUpMethod("Please Select Answer");
			return;
		}
		Question question = new Question(subjectComboBox.getValue(), firstName + " " + lastName,
				questionTextField.getText(), firstAnswerField.getText(), secondAnswerField.getText(),
				thirdAnswerField.getText(), fourthAnswerField.getText(), correctAnswerComboBox.getValue());
		client.setQuestion(question);
		client.handleMessageFromClientUI(new QuestionHandle("Add", question));
		Utilities_Client.popUpMethod("Question Added successfully");
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
			confirmGradesAnchorPane.setVisible(false);
			activeExamManagementAnchorPane.setVisible(false);
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
		String subject = subjectInCreateExamComboBox.getValue();
		if (subject == null) {
			Utilities_Client.popUpMethod("You must select the subject first");
			return;
		}
		String course = courseInCreateExamComboBox.getValue();
		if (course == null) {
			Utilities_Client.popUpMethod("Please select for which course you creating the exam");
			return;
		}
		try {
			int duration = Integer.parseInt(durationInCreateExamField.getText());
			if (duration <= 0) {
				Utilities_Client.popUpMethod("You must enter valid duration before you proceed");
				return;
			}
			exam = new Exam(subject, course, duration, firstName + " " + lastName);
			anchorPaneInCreateExamFirstWindow.setVisible(false);
			client.getQuestionsFromDB().clear();
			anchorPaneInCreateExamSecondWindow.setVisible(true);
			setTableInCreateExamAllQuestions();
			clearFieldsInAddExamWindows("first");
		} catch (NumberFormatException e) {
			Utilities_Client.popUpMethod("You must enter valid duration before you proceed");
		}
	}

	/**
	 * Prevents from the user to select course if he\she didn't select a subject
	 * 
	 * @param event
	 */
	public void selectCourseComboBoxHandler(MouseEvent event) {
		String selectedSubject = subjectInCreateExamComboBox.getValue();
		if (selectedSubject == null) {
			Utilities_Client.popUpMethod("You must select the subject first");
			return;
		} else {
			client.handleMessageFromClientUI(Message.getCourses + " " + selectedSubject);
			courseInCreateExamComboBox.getItems().clear();
			courseInCreateExamComboBox.setItems(client.getCoursesFromDB()); // sets the courses that is under specific
																			// subject
			courseInCreateExamComboBox.setPromptText("Select Course");
			courseInCreateExamComboBox.setButtonCell(new ListCell<String>() {
				@Override
				protected void updateItem(String item, boolean empty) {
					super.updateItem(item, empty);
					if (empty || item == null) {
						setText("Select Course");
					} else {
						setText(item);
					}
				}
			});
		}
	}

	public void showExamsHandler(ActionEvent event) {
		if (examManagementAnchorPane.isVisible() && subjectExamManagement.getValue() != null)
			setTableInExamsManagement();
		else if (activeExamManagementAnchorPane.isVisible() && subjectsActiveExamManagement.getValue() != null)
			setTableInActiveExamManagement();
		else
			Utilities_Client.popUpMethod("Please Select the Subject");
	}

	private void setTableInExamsManagement() {
		client.getExamsFromDB().clear();
		client.handleMessageFromClientUI(Message.getExamBySubject + " " + subjectExamManagement.getValue());
		tableViewInExamsManagement.setItems(client.getExamsFromDB());
	}

	private void setTableInActiveExamManagement() {
		client.getActivatedUnlockedExams().clear();
		client.handleMessageFromClientUI(Message.getActiveExamsByActivator + " " + firstName + " " + lastName + " "
				+ subjectsActiveExamManagement.getValue());
		activeExamsTableView.setItems(client.getActivatedUnlockedExams());
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
			tableViewInExamsManagement.getItems().clear();
			examManagementAnchorPane.setVisible(true);
			backAnchorPane.setVisible(true);
			createExamAnchorPane.setVisible(false);
			addQuestionAnchorPane.setVisible(false);
			questionsTableAnchorPaneInEditOrRemove.setVisible(false);
			welcomeAnchorPane.setVisible(false);
			confirmGradesAnchorPane.setVisible(false);
			clearAddQuestionFields();
			setSubjectComboBox(subjectExamManagement);
			activeExamManagementAnchorPane.setVisible(false);
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
		executionCode.setPrefWidth(55);
		executionCode.setEditable(true);
		ComboBox<String> type = new ComboBox<String>();
		type.getSelectionModel().clearSelection();
		type.setPromptText("Select Type");
		type.getItems().addAll("Computerized", "Manual");
		Button okButton = new Button("OK");
		okButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Exam selectedExam = tableViewInExamsManagement.getSelectionModel().getSelectedItem();
				// check execution code
				String typedExecutionCode = executionCode.getText();
				if ((typedExecutionCode.length() != 4) || !typedExecutionCode.matches("[a-zA-Z0-9]*"))
					Utilities_Client.popUpMethod("Illegal execution code. please try again!");
				else if (!typedExecutionCode.matches(".*\\d+.*"))
					Utilities_Client.popUpMethod("The execution code must contain at least 1 numeric character");
				else if (!typedExecutionCode.matches(".*[a-zA-Z]+.*"))
					Utilities_Client.popUpMethod("The execution code must contain at least 1 alphabetic character");
				else if (type.getValue() == null)
					Utilities_Client.popUpMethod("Type was not selected. please try again!");
				else if (!executionCodeExist(typedExecutionCode)) {
					ActiveExam activeExam = new ActiveExam(selectedExam, typedExecutionCode, type.getValue(),
							firstName + " " + lastName);
					client.handleMessageFromClientUI(new ActiveExamHandle("Activate", activeExam));
					Utilities_Client.popUpMethod("Exam activated successfully!");
				} else
					Utilities_Client.popUpMethod("Exam with this execution Code already exist!");
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
		layout.getChildren().addAll(text, executionCode, type, okButton, cancelButton);
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
		text = new Label("Please enter an execution code:");
		executionCode = new TextField();
		executionCode.setPrefWidth(50);
		executionCode.setEditable(true);
		TextField newDuration = new TextField();
		newDuration.setPromptText("Enter new time here.");
		TextField reason = new TextField();
		reason.setPromptText("Enter reason here.");
		popup.getContent().addAll(text, reason);
		newDuration.setEditable(true);
		reason.setEditable(true);
		Button okButton = new Button("Send");
		okButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Exam selectedExam = tableViewInExamsManagement.getSelectionModel().getSelectedItem();
				// check executionCode
				ActiveExam activeExam = new ActiveExam(selectedExam, executionCode.getText());
				WaitingActiveExam waitingActiveExam = new WaitingActiveExam(activeExam,
						Integer.parseInt(newDuration.getText()), reason.getText());
				client.handleMessageFromClientUI(new WaitingActiveExamHandle("ChangeTime", waitingActiveExam));
				Utilities_Client.popUpMethod("Request sent to Principal!");
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
				Utilities_Client.popUpMethod("Exam locked successfully!");
				primaryStage.hide();
			}
		});

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

	public void openConfirmGrades(ActionEvent event) {
		confirmGradesAnchorPane.setVisible(true);
		welcomeAnchorPane.setVisible(false);
		backAnchorPane.setVisible(false);
		addQuestionAnchorPane.setVisible(false);
		questionsTableAnchorPaneInEditOrRemove.setVisible(false);
		createExamAnchorPane.setVisible(false);
		examManagementAnchorPane.setVisible(false);
		activeExamManagementAnchorPane.setVisible(false);
		setTableInConfirmGrades();
	}

	public void approveButtonHandler(ActionEvent event) {
		Label text = null;
		Stage primaryStage = new Stage();
		primaryStage.setTitle("AES7");
		primaryStage.getIcons().add(new Image("boundaries/Images/AES2.png"));
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
				CheckedExam selectedExam = confirmGradeTableView.getSelectionModel().getSelectedItem();
				client.handleMessageFromClientUI(new CheckedExamHandle("Approve", selectedExam));
				client.handleMessageFromClientUI(new CheckedExamHandle("Remove", selectedExam));
				setTableInConfirmGrades();
				Utilities_Client.popUpMethod("Exam sent to Student!");
				primaryStage.hide();
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

	public void changeGradeButtonHandler(ActionEvent event) {
		Label text = null;
		Stage primaryStage = new Stage();
		primaryStage.setTitle("AES7");
		primaryStage.getIcons().add(new Image("boundaries/Images/AES2.png"));
		Popup popup = new Popup();
		popup.setX(700);
		popup.setY(400);
		HBox layout = new HBox(10);
		text = new Label("Please enter new grade:");
		popup.getContent().addAll(text);
		TextField newGrade = new TextField();
		TextField reasons = new TextField();
		newGrade.setPrefWidth(50);
		newGrade.setEditable(true);
		reasons.setPromptText("Enter reason here");
		reasons.setEditable(true);
		Button saveButton = new Button("Save");
		saveButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				CheckedExam selectedExam = confirmGradeTableView.getSelectionModel().getSelectedItem();
				if (reasons.getText().equals("") || newGrade.getText().equals("")) {
					Utilities_Client.popUpMethod("Some fields are missing. Try again!");
					primaryStage.hide();
				} else {
					selectedExam.setGrade(Integer.parseInt(newGrade.getText()));
					selectedExam.setCommentsOfChangeGrade(reasons.getText());
					client.handleMessageFromClientUI(new CheckedExamHandle("ChangeGrade", selectedExam));
					setTableInConfirmGrades();
					Utilities_Client.popUpMethod("Grade changed successfully!");
					primaryStage.hide();
				}
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
		layout.getChildren().addAll(text, newGrade, reasons, saveButton, cancelButton);
		primaryStage.setScene(new Scene(layout));
		primaryStage.show();
	}

	public void addcommentsButtonHandler(ActionEvent event) {
		Stage primaryStage = new Stage();
		primaryStage.setTitle("AES7");
		primaryStage.getIcons().add(new Image("boundaries/Images/AES2.png"));
		Popup popup = new Popup();
		popup.setX(700);
		popup.setY(400);
		HBox layout = new HBox(10);
		TextField comments = new TextField();
		comments.setPromptText("Enter comment here");
		comments.setEditable(true);
		Button saveButton = new Button("Save");
		saveButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				CheckedExam selectedExam = confirmGradeTableView.getSelectionModel().getSelectedItem();
				if (comments.getText().equals("")) {
					Utilities_Client.popUpMethod("Some fields are missing. Try again!");
					primaryStage.hide();
				} else {
					selectedExam.setComments(comments.getText());
					client.handleMessageFromClientUI(new CheckedExamHandle("AddComments", selectedExam));
					setTableInConfirmGrades();
					Utilities_Client.popUpMethod("Comment added successfully!");
					primaryStage.hide();
				}
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
		layout.getChildren().addAll(comments, saveButton, cancelButton);
		primaryStage.setScene(new Scene(layout));
		primaryStage.show();
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
		confirmGradesAnchorPane.setVisible(false);
		activeExamManagementAnchorPane.setVisible(false);
	}

	/**
	 * checks if the execution code already exists in the DB
	 * 
	 * @param code
	 * @return
	 */
	public boolean executionCodeExist(String code) {
		client.handleMessageFromClientUI(new ExecutionCodeHandle("Check", code));
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return client.getExecutionCodeExistFlag();
	}

	/**
	 * When Active Exam management is pressed
	 * 
	 * @param event
	 */
	public void openActiveExamManagement(ActionEvent event) {
		welcomeAnchorPane.setVisible(false);
		addQuestionAnchorPane.setVisible(false);
		clearAddQuestionFields();
		questionsTableAnchorPaneInEditOrRemove.setVisible(false);
		createExamAnchorPane.setVisible(false);
		examManagementAnchorPane.setVisible(false);
		confirmGradesAnchorPane.setVisible(false);
		setSubjectComboBox(subjectsActiveExamManagement);
		activeExamsTableView.getItems().clear();
		backAnchorPane.setVisible(true);
		activeExamManagementAnchorPane.setVisible(true);
	}

	// end region -> Public Methods

	// region Private Methods

	private void setTableInConfirmGrades() {
		client.getCheckedExamsFromDB().clear();
		client.handleMessageFromClientUI(Message.getCheckedExams);
		confirmGradeTableView.setItems(client.getCheckedExamsFromDB());
	}

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

	private void setColumnInConfirmGrades() {
		subjectColInConfirmGrades.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()
				.getSubmittedExam().getStudentInActiveExam().getActiveExam().getExam().getSubjectID()));
		courseColInConfirmGrades.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()
				.getSubmittedExam().getStudentInActiveExam().getActiveExam().getExam().getCourseID()));
		examNumColInConfirmGrades.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()
				.getSubmittedExam().getStudentInActiveExam().getActiveExam().getExam().getExamNum()));
		executionCodeColInConfirmGrades.setCellValueFactory(cellData -> new SimpleStringProperty(
				cellData.getValue().getSubmittedExam().getStudentInActiveExam().getActiveExam().getExecutionCode()));
		studentIDColInConfirmGrades.setCellValueFactory(cellData -> new SimpleStringProperty(
				cellData.getValue().getSubmittedExam().getStudentInActiveExam().getStudent().getId()));
		gradeColInConfirmGrades.setCellValueFactory(new PropertyValueFactory<>("grade"));

	}

	/**
	 * Sets the columns of the activated exams table view
	 */
	private void setColumnsOfActiveExams() {
		subjectNumberOfActiveExam.setCellValueFactory(
				cellData -> new SimpleStringProperty(cellData.getValue().getExam().getSubjectID()));
		courseNumberOfAvticeExam
				.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getExam().getCourseID()));
		examNumberOfAvticeExam
				.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getExam().getExamNum()));
		executionCodeOfAvticeExam.setCellValueFactory(new PropertyValueFactory<>("executionCode"));
		durationOfAvticeExam.setCellValueFactory(new PropertyValueFactory<>("duration"));
		typeOfAvticeExam.setCellValueFactory(new PropertyValueFactory<>("type"));
	}

	/**
	 * Updates the GUI questions table from the data base
	 */
	private void setQuestionsTableInfoInEditOrRemove() {
		String subject = subjectComboBoxInEditOrRemove.getValue();
		if (subject != null) {
			client.handleMessageFromClientUI(Message.editOrRemove + " " + firstName + " " + lastName + " " + subject);
		} else {
			Utilities_Client.popUpMethod("Please select the subject");
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
	 * Clears the add exam screen according to the requested window
	 * 
	 * @param window
	 */
	private void clearFieldsInAddExamWindows(String window) {
		// clear the first window fields
		if (window.equals("first")) {
			subjectInCreateExamComboBox.getSelectionModel().clearSelection();
			subjectInCreateExamComboBox.setPromptText("Select Subject");
			subjectInCreateExamComboBox.setButtonCell(new ListCell<String>() {
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
			courseInCreateExamComboBox.getSelectionModel().clearSelection();
			courseInCreateExamComboBox.setPromptText("Select Course");
			courseInCreateExamComboBox.setButtonCell(new ListCell<String>() {
				@Override
				protected void updateItem(String item, boolean empty) {
					super.updateItem(item, empty);
					if (empty || item == null) {
						setText("Select Course");
					} else {
						setText(item);
					}
				}
			});
			durationInCreateExamField.clear();
		} else if (window.equals("second")) // clears the table in the second window
			tableViewInCreateExamQuestion.getItems().clear();
		else if (window.equals("third")) { // clears the the text fields in the third window
			textAreaStudentsInCreateExam.clear();
			textAreaTeachersInCreateExam.clear();
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
		comboBox.setButtonCell(new ListCell<String>() {
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
		client.handleMessageFromClientUI(Message.getSubjects);
		comboBox.setItems(client.getSubjectsFromDB());
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