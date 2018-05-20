package client;

import java.io.File;
import java.io.IOException;

import controllers.ScreensController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class MainApp extends Application {

	// region Constants

	public static Client client;
	public static String loginScreenID = "LoginScreen";
	public static String loginScreenFile = "/boundaries/LoginWindow.fxml";
	public static String teacherScreenID = "TeacherScreen";
	public static String teacherScreenFile = "/boundaries/TeacherWindow.fxml";
	private static String host = "";

	// end region -> Constants

	// region Setters

	public static Client getClient() {
		return client;
	}

	public static void setClient(Client client) {
		MainApp.client = client;
	}

	public static void main(String[] args) throws IOException {
		try {
			host = args[0];
		} catch (ArrayIndexOutOfBoundsException e) {
			host = "localhost";
		}
		launch(args);
	}

	// end region -> Setters

	// region Public Methods

	@Override
	public void start(Stage primaryStage) throws Exception {
		ScreensController mainContainer = new ScreensController();
		MainApp.client = new Client(host, Client.DEFAULT_PORT, mainContainer);
		mainContainer.loadScreen(MainApp.loginScreenID, MainApp.loginScreenFile);
		mainContainer.loadScreen(MainApp.teacherScreenID, MainApp.teacherScreenFile);
		mainContainer.setScreen(MainApp.loginScreenID);
		Group root = new Group();
		root.getChildren().addAll(mainContainer);
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		Image image = new Image(new File("src/boundaries.Images/AES2.PNG").toURI().toString());
		primaryStage.getIcons().add(image);
		primaryStage.setResizable(false);
		primaryStage.setTitle("AES");
		primaryStage.sizeToScene();
		primaryStage.setOnCloseRequest(e -> {
			try {
				System.out.println("Close");
				client.closeConnection();
				Platform.exit();
			} catch (IOException event) {
				event.printStackTrace();
			}
		});
		primaryStage.show();
	}

	// end region -> Public Methods

}