package boundaries;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import resources.QuestionInExam;

public class QuestionInComputerizeExam {

	private Label labelfirst;

	private Label ans1, ans2, ans3, ans4;

	private RadioButton radio1, radio2, radio3, radio4;

	private Button button;

	private QuestionInExam questionInExam;

	private ToggleGroup toggleGroup;

	private ObservableList<Node> list = FXCollections.observableArrayList();

	/**
	 * 
	 * @param questionText
	 * @param ans1
	 * @param ans2
	 * @param ans3
	 * @param ans4
	 * @param questionInExam
	 */
	public QuestionInComputerizeExam(String questionText, String ans1, String ans2, String ans3, String ans4,
			QuestionInExam questionInExam) {
		this.labelfirst = new Label(questionText);
		this.radio1 = new RadioButton(ans1);
		radio1.setUserData("1");
		this.radio2 = new RadioButton(ans2);
		radio2.setUserData("2");
		this.radio3 = new RadioButton(ans3);
		radio3.setUserData("3");
		this.radio4 = new RadioButton(ans4);
		radio4.setUserData("4");

		this.radio1.setOnAction(e -> button.setDisable(false));
		this.radio2.setOnAction(e -> button.setDisable(false));
		this.radio3.setOnAction(e -> button.setDisable(false));
		this.radio4.setOnAction(e -> button.setDisable(false));

		toggleGroup = new ToggleGroup();

		radio1.setToggleGroup(toggleGroup);
		radio2.setToggleGroup(toggleGroup);
		radio3.setToggleGroup(toggleGroup);
		radio4.setToggleGroup(toggleGroup);

		button = new Button("Submit");
		button.setDisable(true);
		this.questionInExam = questionInExam;
		list.addAll(labelfirst, radio1, radio2, radio3, radio4);
	}

	/**
	 * 
	 * @param questionText
	 * @param ans1
	 * @param ans2
	 * @param ans3
	 * @param ans4
	 */
	public QuestionInComputerizeExam(String questionText, String ans1, String ans2, String ans3, String ans4) {
		this.labelfirst = new Label(questionText);
		this.ans1 = new Label(ans1);
		this.ans2 = new Label(ans2);
		this.ans3 = new Label(ans3);
		this.ans4 = new Label(ans4);
		list.addAll(labelfirst, this.ans1, this.ans2, this.ans3, this.ans4);
	}

	public ToggleGroup getToggleGroup() {
		return toggleGroup;
	}

	public void setToggleGroup(ToggleGroup toggleGroup) {
		this.toggleGroup = toggleGroup;
	}

	public Label getLabelfirst() {
		return labelfirst;
	}

	public void setLabelfirst(Label labelfirst) {
		this.labelfirst = labelfirst;
	}

	public RadioButton getRadio1() {
		return radio1;
	}

	public void setRadio1(RadioButton radio1) {
		this.radio1 = radio1;
	}

	public RadioButton getRadio2() {
		return radio2;
	}

	public void setRadio2(RadioButton radio2) {
		this.radio2 = radio2;
	}

	public RadioButton getRadio3() {
		return radio3;
	}

	public void setRadio3(RadioButton radio3) {
		this.radio3 = radio3;
	}

	public RadioButton getRadio4() {
		return radio4;
	}

	public void setRadio4(RadioButton radio4) {
		this.radio4 = radio4;
	}

	public Button getButton() {
		return button;
	}

	public void setButton(Button button) {
		this.button = button;
	}

	public ObservableList<Node> getList() {
		return list;
	}

	public void setList(ObservableList<Node> list) {
		this.list = list;
	}

	public QuestionInExam getQuestionInExam() {
		return questionInExam;
	}

	public void setQuestionInExam(QuestionInExam questionInExam) {
		this.questionInExam = questionInExam;
	}

	public void setTextOnRed(String ans) {
		switch (ans) {
		case "1":
			ans1.setStyle("-fx-background-color : red");
			break;
		case "2":
			ans2.setStyle("-fx-background-color : red");
			break;
		case "3":
			ans3.setStyle("-fx-background-color : red");
			break;
		case "4":
			ans4.setStyle("-fx-background-color : red");
			break;
		}
	}

	public void setTextOnGreen(String ans) {
		switch (ans) {
		case "1":
			ans1.setStyle("-fx-background-color : green");
			break;
		case "2":
			ans2.setStyle("-fx-background-color : green");
			break;
		case "3":
			ans3.setStyle("-fx-background-color : green");
			break;
		case "4":
			ans4.setStyle("-fx-background-color : green");
			break;
		}
	}

}
