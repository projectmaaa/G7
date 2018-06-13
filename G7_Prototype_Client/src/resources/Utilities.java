package resources;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Timestamp;
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

	public static String getTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		return (sdf.format(timestamp));
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
		primaryStage.setWidth(400);
		primaryStage.initStyle(StageStyle.UNDECORATED);
		Popup popup = new Popup();
		popup.setX(700);
		popup.setY(400);
		BorderPane border = new BorderPane();
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
		try {
			text = new Label(str);
		} catch (NullPointerException e) {
			text = new Label("No Message Sent");
		}
		popup.getContent().addAll(text);
		BorderPane.setAlignment(text, Pos.CENTER);
		border.setTop(text);
		border.setCenter(okButton);
		primaryStage.setScene(new Scene(border));
		primaryStage.show();
	}

	/**
	 * 
	 * @param myFile
	 */
	public static void writeWordFile(MyFile myFile) {
		try {
			File file = new File(myFile.getFileName());
			
			FileOutputStream fileOutputStream = new FileOutputStream(file);
			fileOutputStream.write(myFile.getMybytearray());
			fileOutputStream.close();
			if (!Desktop.isDesktopSupported()) {
				System.out.println("Desktop is not supported");
				return;
			}
			Desktop desktop = Desktop.getDesktop();
			if (file.exists())
				desktop.open(file);
		
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	// end region -> Public Methods

} // end of class utilities