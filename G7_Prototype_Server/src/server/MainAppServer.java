package server;

import java.io.File;
import java.io.IOException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class MainAppServer extends Application {

	// region Constants

	public static Server server;

	public static String loginScreenFile = "/boundaries/ServerWindow.fxml";

	// end region -> Constants

	public static Server getServer() {
		return server;
	}

	public static void setServer(Server server) {
		MainAppServer.server = server;
	}

	public static void main(String[] args) {
		int port = 0; // Port to listen on
		try {
			port = Integer.parseInt(args[0]); // Get port from command line
		} catch (Throwable t) {
			port = Server.DEFAULT_PORT; // Set port to 5555
		}
		server = new Server(port);
		// try {
		// server.listen(); // Start listening for connections
		// } catch (Exception ex) {
		// System.out.println("ERROR - Could not listen for clients!");
		// }
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader myLoader = new FXMLLoader(getClass().getResource(loginScreenFile));
		Parent loadScreen = (Parent) myLoader.load();
		Scene scene = new Scene(loadScreen);
		primaryStage.setScene(scene);
		Image image = new Image(new File("/G7_Prototype_Server/src/boundaries/Images/AES2.PNG").toURI().toString());
		primaryStage.getIcons().add(image);
		primaryStage.setResizable(false);
		primaryStage.setTitle("AES");
		primaryStage.sizeToScene();
		primaryStage.setOnCloseRequest(e -> {
			System.out.println("Close");
			try {
				server.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			Platform.exit();
		});
		primaryStage.show();

	}

}
