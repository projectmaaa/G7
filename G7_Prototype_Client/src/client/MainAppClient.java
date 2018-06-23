package client;

import java.io.IOException;
import controllers.ScreensController;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import resources.Message;

/**
 * The class that loads and starts the client application
 * 
 * @author Group 7
 *
 */
public class MainAppClient extends Application {

	/******************** Attributes ********************/

	public static Client client;

	private ScreensController mainContainer = new ScreensController();

	public static String loginScreenID = "LoginScreen";

	public static String loginScreenFile = "/boundaries/LoginWindow.fxml";

	public static String teacherScreenID = "TeacherScreen";

	public static String teacherScreenFile = "/boundaries/TeacherWindow.fxml";

	public static String studentScreenID = "StudentScreen";

	public static String studentScreenFile = "/boundaries/StudentWindow.fxml";

	public static String principalScreenID = "PrincipalScreen";

	public static String principalScreenFile = "/boundaries/PrincipalWindow.fxml";

	private static String host = "";

	/******************** Getters and Setters ********************/

	/**
	 * 
	 * @return The client
	 */
	public static Client getClient() {
		return client;
	}

	/**
	 * 
	 * @param client
	 *            The Client
	 */
	public void setClient(Client client) {
		MainAppClient.client = client;
	}

	/**
	 * 
	 * @return The IP host
	 */
	public static String getHost() {
		return host;
	}

	/**
	 * 
	 * @param host
	 *            The host
	 */
	public static void setHost(String host) {
		MainAppClient.host = host;
	}

	/******************** Methods ********************/

	/**
	 * 
	 * @param args
	 *            The IP of the server
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		try {
			host = args[0];
		} catch (ArrayIndexOutOfBoundsException e) {
			host = "localhost";
		}
		launch(args);
	}

	/**
	 * start client method
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		client = new Client(host, 5555);
		client.setScreenParent(mainContainer);
		mainContainer.loadScreen(MainAppClient.loginScreenID, MainAppClient.loginScreenFile);
		mainContainer.loadScreen(MainAppClient.teacherScreenID, MainAppClient.teacherScreenFile);
		mainContainer.loadScreen(MainAppClient.studentScreenID, MainAppClient.studentScreenFile);
		mainContainer.loadScreen(MainAppClient.principalScreenID, MainAppClient.principalScreenFile);
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
			} catch (IOException event) {
				event.printStackTrace();
			}
			System.exit(0);
		});
		primaryStage.show();
	}

} // end of class