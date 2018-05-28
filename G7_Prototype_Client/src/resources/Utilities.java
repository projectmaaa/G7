package resources;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Popup;
import javafx.stage.Stage;

/**
 * This class will have method for general use
 * 
 *
 */
public class Utilities {

	private static boolean answer;

	private static boolean whileFlag;
	
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
	 */

	public static void popUpMethod(String str) {
		Label text;
		Stage primaryStage = new Stage();
		primaryStage.setTitle("AES7Popup");
		Popup popup = new Popup();
		popup.setX(700);
		popup.setY(400);
		HBox layout = new HBox(10);
		switch (str) {
		case "add":
			text = new Label("Question added successfully!");
			popup.getContent().addAll(text);
			Button okButton = new Button("OK");
			okButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					primaryStage.hide();
				}
			});
			layout.setStyle("-fx-background-color: cornsilk; -fx-padding: 10;");
			layout.getChildren().addAll(text, okButton);
			break;
		case "save":
			text = new Label("Question updated successfully!");
			popup.getContent().addAll(text);
			Button saveButton = new Button("OK");
			saveButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					primaryStage.hide();
				}
			});
			layout.setStyle("-fx-background-color: cornsilk; -fx-padding: 10;");
			layout.getChildren().addAll(text, saveButton);
			break;
		case "incorrect answer":
			text = new Label("Please enter correct answer from 1 to 4 only!");
			popup.getContent().addAll(text);
			Button correctionButton = new Button("OK");
			correctionButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					primaryStage.hide();
				}
			});
			layout.setStyle("-fx-background-color: cornsilk; -fx-padding: 10;");
			layout.getChildren().addAll(text, correctionButton);
			break;
		}
		primaryStage.setScene(new Scene(layout));
		primaryStage.show();
	}

	// end region -> Public Methods
	
	public static boolean isAnswer() {
		return answer;
	}

	public static void setAnswer(boolean answer) {
		Utilities.answer = answer;
	}

	public static boolean isWhileFlag() {
		return whileFlag;
	}

	public static void setWhileFlag(boolean whileFlag) {
		Utilities.whileFlag = whileFlag;
	}

} // end of class utilities
