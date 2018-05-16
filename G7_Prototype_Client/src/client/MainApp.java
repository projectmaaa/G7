package client;

import java.io.File;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
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

	public static void main(String[] args) throws IOException {

		MainApp.client = new Client("localhost", Client.DEFAULT_PORT);
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/boundaries/LoginWindow.fxml"));
			Parent root = (Parent) loader.load();
			Scene scene = new Scene(root);
			Image image = new Image(new File("src/boundaries.Images/AES2.PNG").toURI().toString());
			primaryStage.getIcons().add(image);
			primaryStage.setResizable(false);
			primaryStage.setScene(scene);
			primaryStage.setTitle("AES");
			primaryStage.sizeToScene();
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
