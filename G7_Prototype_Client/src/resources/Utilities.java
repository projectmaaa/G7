package resources;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.stage.Popup;
import javafx.stage.Stage;

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
	 */
	public static void popUpMethod(String str) {
		Label text = null;
		Stage primaryStage = new Stage();
		primaryStage.setTitle("AES7");
		Popup popup = new Popup();
		popup.setX(700);
		popup.setY(400);
		HBox layout = new HBox(10);
		Button okButton = new Button("OK");
		primaryStage.getIcons().add(new Image("boundaries/Images/AES2.png"));
		okButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				primaryStage.hide();
			}
		});
		layout.setStyle("-fx-background-color: cornsilk; -fx-padding: 10;");
		switch (str) {
		case "add":
			text = new Label("Question added successfully!");
			popup.getContent().addAll(text);
			break;
		case "save":
			text = new Label("Question updated successfully!");
			popup.getContent().addAll(text);
			break;
		case "incorrect answer":
			text = new Label("Please insert only numbers from 1 to 4 in the 'Correct Answer' column!");
			popup.getContent().addAll(text);
			break;
		case "Select Subject":
			text = new Label("Please select subject");
			popup.getContent().addAll(text);
			break;
		case "Enter Text":
			text = new Label("Please fill all the text fields");
			popup.getContent().addAll(text);
			break;
		case "Select Answer":
			text = new Label("Please select the correct answer for the question");
			popup.getContent().addAll(text);
			break;
		}
		layout.getChildren().addAll(text, okButton);
		primaryStage.setScene(new Scene(layout));
		primaryStage.show();
	}

	// end region -> Public Methods

} // end of class utilities