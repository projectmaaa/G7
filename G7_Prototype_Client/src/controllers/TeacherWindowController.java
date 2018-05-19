package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import client.Client;
import client.MainApp;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import resources.Question;
import resources.Utilities;

public class TeacherWindowController implements Initializable, IScreenController {

	// region Fields

	@FXML
	private AnchorPane anchorPane;

	@FXML
	private Text TextGroup7;

	@FXML
	private ImageView Group7Logo;

	@FXML
	private Button logoutButton;

	@FXML
	private Button saveTableChangesButton;

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
		tableView.setVisible(false);
		saveTableChangesButton.setVisible(false);
		this.client = MainApp.getClient();
		client.handleMessageFromClientUI(resources.Message.EditorRemove);
		setColumns();
		tableView.setItems(client.getQuestionsFromDB());
		tableView.setEditable(true);
	}

	// region Public Methods

	public void logOutButtonHandler(ActionEvent event) throws Exception {
		if (tableView.isVisible()) {
			tableView.setVisible(false);
		}
		screensController.setScreen(MainApp.screen1ID);
	}

	/**
	 * Edit\Remove Question was pressed
	 */
	public void openEditorRemove(ActionEvent event) {
		try {
			// System.out.println("Edit\\Remove Was pressed");
			tableView.setVisible(true);
			saveTableChangesButton.setVisible(true);
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
		possibleAnswersColumn.setCellValueFactory(new PropertyValueFactory<>("possibleAnswers"));
		correctAnswerColumn.setCellValueFactory(new PropertyValueFactory<>("correctAnswer"));

		// define the columns editable

		questionIDColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		questionIDColumn.setOnEditCommit(new EventHandler<CellEditEvent<Question, String>>() {
			@Override
			public void handle(CellEditEvent<Question, String> t) {
				((Question) t.getTableView().getItems().get(t.getTablePosition().getRow()))
						.setQuestionID(t.getNewValue());
			}
		});
		authorColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		authorColumn.setOnEditCommit(new EventHandler<CellEditEvent<Question, String>>() {
			@Override
			public void handle(CellEditEvent<Question, String> t) {
				((Question) t.getTableView().getItems().get(t.getTablePosition().getRow())).setAuthor(t.getNewValue());
			}
		});
		questionTextColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		questionTextColumn.setOnEditCommit(new EventHandler<CellEditEvent<Question, String>>() {
			@Override
			public void handle(CellEditEvent<Question, String> t) {
				((Question) t.getTableView().getItems().get(t.getTablePosition().getRow()))
						.setQuestionText(t.getNewValue());
			}
		});
		possibleAnswersColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		possibleAnswersColumn.setOnEditCommit(new EventHandler<CellEditEvent<Question, String>>() {
			@Override
			public void handle(CellEditEvent<Question, String> t) {
				((Question) t.getTableView().getItems().get(t.getTablePosition().getRow()))
						.setPossibleAnswers(t.getNewValue());
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

	// end region -> Private Methods

}