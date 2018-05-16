package client;

import java.io.File;
import java.io.IOException;

import controllers.ScreensController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class MainApp extends Application {

	public static Client client;

	public static Client getClient() {
		return client;
	}

	public static void setClient(Client client) {
		MainApp.client = client;
	}

	public static String screen1ID = "LoginScreen";
	public static String screen1File = "/boundaries/LoginWindow.fxml";
	public static String screen2ID = "TeacherScreen";
	public static String screen2File = "/boundaries/TeacherWindow.fxml";

	public static void main(String[] args) throws IOException {

		// MainApp.client = new Client("localhost", Client.DEFAULT_PORT);
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		ScreensController mainContainer = new ScreensController();
		MainApp.client = new Client("localhost", Client.DEFAULT_PORT, mainContainer);
		mainContainer.loadScreen(MainApp.screen1ID, MainApp.screen1File);
		mainContainer.loadScreen(MainApp.screen2ID, MainApp.screen2File);
		mainContainer.setScreen(MainApp.screen1ID);
		Group root = new Group();
		root.getChildren().addAll(mainContainer);
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		Image image = new Image(new File("src/boundaries.Images/AES2.PNG").toURI().toString());
		primaryStage.getIcons().add(image);
		primaryStage.setResizable(false);
		primaryStage.setTitle("AES");
		primaryStage.sizeToScene();
		primaryStage.show();
		// try {
		// // FXMLLoader loader = new FXMLLoader(;
		// Parent root =
		// FXMLLoader.load(getClass().getResource("/boundaries/LoginWindow.fxml"));
		// Scene scene = new Scene(root);

		// primaryStage.setScene(scene);

		// primaryStage.show();
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
	}

}
