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
 * 
 * @author G7
 *
 */
public class MainAppServer extends Application {

	// region Constants

	public static Server server;

	public static String loginScreenFile = "/boundaries/ServerWindow.fxml";

	// end region -> Constants

	/**
	 * 
	 * @return
	 */
	public static Server getServer() {
		return server;
	}

	/**
	 * 
	 * @param server
	 */
	public static void setServer(Server server) {
		MainAppServer.server = server;
	}

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		int port = 0; // Port to listen on
		try {
			port = Integer.parseInt(args[0]); // Get port from command line
		} catch (Throwable t) {
			port = 5555; // Set port to 5555
		}
		server = new Server(port);
		// try {
		// server.listen(); // Start listening for connections
		// } catch (Exception ex) {
		// System.out.println("ERROR - Could not listen for clients!");
		// }
		launch(args);
	}

	/**
	 * @param primaryStage
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
			// System.out.println("Close");
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

}
