package resources;

import java.awt.Desktop;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
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
 * This class contains methods for general client use.
 * 
 * @author Group 7
 *
 */
public class Utilities_Client {

	/******************** Methods ********************/

	/**
	 * This method return the date in string format
	 * 
	 * @return The current date in format dd/MM/yyyy
	 */
	public static String setDate() {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		return (dateFormat.format(date));
	}

	/**
	 * This method return the time in string format
	 * 
	 * @return The current time in format HH/mm/ss
	 */
	public static String getTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		return (sdf.format(timestamp));
	}

	/**
	 * This method show pop up screen
	 * 
	 * @param str
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
	 * This method is loading the word file
	 * 
	 * @param myFile
	 */
	public static void writeWordFile(MyFile myFile, Boolean open) {
		try {
			File file = new File(myFile.getFileName());
			FileOutputStream fileOutputStream = new FileOutputStream(file);
			fileOutputStream.write(myFile.getMybytearray());
			fileOutputStream.close();
			if (open) {
				if (!Desktop.isDesktopSupported()) {
					System.out.println("Desktop is not supported");
					return;
				}
				Desktop desktop = Desktop.getDesktop();
				if (file.exists())
					desktop.open(file);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * This method returns the word file of specific student
	 * 
	 * @param executionCode
	 * @param studentID
	 * @return
	 */
	public static MyFile getWordFile(String executionCode, String studentID) {
		MyFile myFile = new MyFile("./exams/" + executionCode + "_" + studentID + ".docx");
		File file = new File("./exams/" + executionCode + "_" + studentID + ".docx");
		try {
			byte[] byteArray = new byte[(int) file.length()];
			FileInputStream fileInputStream;
			fileInputStream = new FileInputStream(file);
			BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
			myFile.initArray(byteArray.length);
			myFile.setSize(byteArray.length);
			bufferedInputStream.read(myFile.getMybytearray(), 0, byteArray.length);
			bufferedInputStream.close();
		} catch (IOException e) {
			System.out.println(e);
		}
		return myFile;
	}

} // end of class