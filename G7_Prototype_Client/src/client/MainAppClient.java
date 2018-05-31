package client;

import java.io.IOException;
import controllers.ScreensController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import resources.Message;

public class MainAppClient extends Application {

	// region Constants

	public static Client client;

	private ScreensController mainContainer = new ScreensController();

	public static String loginScreenID = "LoginScreen";

	public static String loginScreenFile = "/boundaries/LoginWindow.fxml";

	public static String teacherScreenID = "TeacherScreen";

	public static String teacherScreenFile = "/boundaries/TeacherWindow.fxml";

	public static String studentScreenID = "StudentScreen";

	public static String studentScreenFile = "/boundaries/StudentWindow.fxml";

	private static String host = "";

	// end region -> Constants

	// region Setters

	public static Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		MainAppClient.client = client;
	}

	public static String getHost() {
		return host;
	}

	public static void setHost(String host) {
		MainAppClient.host = host;
	}

	// end region -> Setters

	public static void main(String[] args) throws IOException {
		try {
			host = args[0];
		} catch (ArrayIndexOutOfBoundsException e) {
			host = "localhost";
		}
		launch(args);
	}

	// region Public Methods

	@Override
	public void start(Stage primaryStage) throws Exception {
		client = new Client(host, 5555);
		client.setScreenParent(mainContainer);
		mainContainer.loadScreen(MainAppClient.loginScreenID, MainAppClient.loginScreenFile);
		mainContainer.loadScreen(MainAppClient.teacherScreenID, MainAppClient.teacherScreenFile);
		mainContainer.loadScreen(MainAppClient.studentScreenID, MainAppClient.studentScreenFile);
		mainContainer.setScreen(MainAppClient.loginScreenID);
		Group root = new Group();
		root.getChildren().addAll(mainContainer);
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		Image image = new Image("boundaries/Images/AES2.png");
		primaryStage.getIcons().add(image);
		primaryStage.setResizable(false);
		primaryStage.setTitle("AES7-Client");
		primaryStage.sizeToScene();
		primaryStage.setOnCloseRequest(e -> {
			try {
				System.out.println("Close");
				client.handleMessageFromClientUI(Message.logout);
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