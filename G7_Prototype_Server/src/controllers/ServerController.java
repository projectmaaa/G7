package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import server.MainAppServer;
import server.Server;
import server.SqlUtilities;

public class ServerController implements Initializable {

	// region Fields

	@FXML
	private ImageView settingsButton;

	@FXML
	private Button serverButton;

	@FXML
	private Button dbButton;

	@FXML
	private AnchorPane anchorPaneSetting;

	@FXML
	private Button settingsSaveButton;

	@FXML
	private Button settingsCancelButton;

	@FXML
	private TextField portField;

	@FXML
	private TextField dbUserNameField;

	@FXML
	private TextField dbPasswordField;

	private Server server;

	private Boolean turnSettings = false;

	private TranslateTransition translateAnimationServer = new TranslateTransition(Duration.seconds(0.25));

	private TranslateTransition translateAnimationDB = new TranslateTransition(Duration.seconds(0.25));

	// end region -> Fields

	public void openSetting(MouseEvent event) {
		if (!turnSettings) {
			portField.setText(Integer.toString(Server.DEFAULT_PORT));
			dbUserNameField.setText(server.getUserNameDBcon());
			dbPasswordField.setText(server.getPassWordDBcon());
			anchorPaneSetting.setVisible(true);
			turnSettings = true;
		} else {
			clearSettings();
			anchorPaneSetting.setVisible(false);
			turnSettings = false;
		}
	}

	public void saveSettings(MouseEvent event) {
		server.setPort(Integer.parseInt(portField.getText()));
		server.setUserNameDBcon(dbUserNameField.getText());
		server.setPassWordDBcon(dbPasswordField.getText());
		clearSettings();
		anchorPaneSetting.setVisible(false);
	}

	public void turnOnOffServer(MouseEvent event) {
		if (serverButton.getText().equals("Off")) {
			translateAnimationServer.setByX(50);
			serverButton.setText("On");
			try {
				server.listen(); // Start listening for connections
			} catch (Exception ex) {
				System.out.println("ERROR - Could not listen for clients!");
			}
		} else {
			translateAnimationServer.setByX(-50);
			serverButton.setText("Off");
			try {
				server.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		translateAnimationServer.play();
	}

	public void turnOnOffDB(MouseEvent event) {
		if (dbButton.getText().equals("Off")) {
			translateAnimationDB.setByX(50);
			translateAnimationDB.play();
			server.setConnection(SqlUtilities.connection(server.getUserNameDBcon(), server.getPassWordDBcon()));
			dbButton.setText("On");
		}
	}

	private void clearSettings() {
		portField.setText(null);
		dbUserNameField.setText(null);
		dbPasswordField.setText(null);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.server = MainAppServer.getServer();
		translateAnimationServer.setNode(serverButton);
		translateAnimationDB.setNode(dbButton);
	}
}
