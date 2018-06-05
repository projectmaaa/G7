package resources;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * This class will have method for general use
 * 
 *
 */
public class Utilities {

	// region Public Methods

	/**
	 * This method will return the date
	 * 
	 * @return String date format (dd/MM/yyyy)
	 */
	public static String setDate() {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		return (dateFormat.format(date));
	}

	/**
	 * This method will show pop up screen
	 * 
	 * @param str
	 *            add/save/incorrent answer/Select Subject/Enter Text/Select
	 *            Answer/Select Course/Duration/Points/TotalPoints
	 */
	public static void popUpMethod(String str) {
		Label text = null;
		Stage primaryStage = new Stage();
		primaryStage.setTitle("AES7");
		primaryStage.setResizable(false);
		primaryStage.setHeight(80);
		primaryStage.setWidth(300);
		primaryStage.initStyle(StageStyle.UNDECORATED);
		Popup popup = new Popup();
		popup.setX(700);
		popup.setY(400);
		BorderPane border = new BorderPane();
		// HBox layout = new HBox(10);
		Button okButton = new Button("OK");
		primaryStage.getIcons().add(new Image("boundaries/Images/AES2.png"));
		okButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				primaryStage.hide();
			}
		});
		border.setStyle(
				"-fx-background-color: cornsilk; -fx-padding: 10; -fx-border-color: black; -fx-border-width: 1;");
		/*
		 * // layout.setStyle("-fx-background-color: cornsilk; -fx-padding: 10;"); //
		 * String[] strArray = str.split(" "); // switch (strArray[0]) { // case "add":
		 * // text = new Label("Question added successfully!"); // break; // case
		 * "save": // text = new Label("Question updated successfully!"); // break; //
		 * case "IncorrectAnswer": // text = new Label("Please insert only numbers from
		 * 1 to 4 in the 'Correct // Answer' column!"); // break; // case
		 * "SelectSubject": // text = new Label("Please select subject"); // break; //
		 * case "SelectCourse": // text = new Label("Please select course"); // break;
		 * // case "EnterText": // text = new Label("Please fill all the text fields");
		 * // break; // case "Exam": // text = new Label("Exam created successfully!");
		 * // break; // case "SelectAnswer": // text = new
		 * Label("Please select the correct answer for the question"); // break; // case
		 * "Duration": // text = new Label("Please fill the correct amount of time"); //
		 * break; // case "Points": // text = new
		 * Label("Wrong amount of points in question number " + strArray[1]); //
		 * primaryStage.setHeight(100); // primaryStage.setWidth(400); // break; // case
		 * "TotalPoints": // text = new Label("The total amount of points isn't 100");
		 * // primaryStage.setHeight(100); // primaryStage.setWidth(400); // break; //
		 * case "SelectQuestions": // text = new Label("Please Select Questions"); //
		 * break; // }
		 */
		try {
			text = new Label(str);
		} catch (NullPointerException e) {
			text = new Label("No Message Sent");
		}
		popup.getContent().addAll(text);
		// layout.getChildren().addAll(text, okButton);
		BorderPane.setAlignment(text, Pos.CENTER);
		border.setTop(text);
		border.setCenter(okButton);
		primaryStage.setScene(new Scene(border));
		primaryStage.show();
	}

	// end region -> Public Methods

} // end of class utilities