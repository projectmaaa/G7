package server;

import java.io.IOException;
import java.sql.SQLException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * The class that loads and starts the server application
 * 
 * @author Group 7
 *
 */
public class MainAppServer extends Application {

	/******************** Attributes ********************/

	public static Server server;

	public static String loginScreenFile = "/boundaries/ServerWindow.fxml";

	/******************** Getters & Setters ********************/

	/**
	 * 
	 * @return The server
	 */
	public static Server getServer() {
		return server;
	}

	/**
	 * 
	 * @param server
	 *            Server
	 */
	public static void setServer(Server server) {
		MainAppServer.server = server;
	}

	/******************** Methods ********************/

	/**
	 * 
	 * @param args
	 *            The IP
	 */
	public static void main(String[] args) {
		int port = 0; // Port to listen on
		try {
			port = Integer.parseInt(args[0]); // Get port from command line
		} catch (Throwable t) {
			port = 5555; // Set port to 5555
		}
		server = new Server(port);
		launch(args);
	}

	/**
	 * @param primaryStage
	 *            The stage to load
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader myLoader = new FXMLLoader(getClass().getResource(loginScreenFile));
		Parent loadScreen = (Parent) myLoader.load();
		Scene scene = new Scene(loadScreen);
		primaryStage.setScene(scene);
		Image image = new Image("boundaries/Images/AES2.png");
		primaryStage.getIcons().add(image);
		primaryStage.setResizable(false);
		primaryStage.setTitle("AES7-Server");
		primaryStage.sizeToScene();
		primaryStage.setOnCloseRequest(e -> {
			try {
				if (server.isListening()) {
					server.close();
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			try {
				if (!server.getConnection().isClosed()) {
					server.getConnection().close();
					System.out.println("db connection closed");
				}
			} catch (SQLException sqlException) {
				sqlException.printStackTrace();
			} catch (NullPointerException nullPointerException) {
			}
			System.exit(0);
		});
		primaryStage.show();
	}

} // end of class