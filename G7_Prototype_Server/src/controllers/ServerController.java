package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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

/**
 * 
 * @author G7
 *
 */
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

	private Boolean saveX = false;

	private double startX;

	private double endX;

	private TranslateTransition translateAnimationServer = new TranslateTransition(Duration.seconds(0.25));

	private TranslateTransition translateAnimationDB = new TranslateTransition(Duration.seconds(0.25));

	// end region -> Fields

	/**
	 * 
	 * @param event
	 */
	public void openSetting(MouseEvent event) {
		if (!turnSettings) {
			portField.setText(Integer.toString(server.getPort()));
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

	/**
	 * 
	 * @param event
	 */
	public void saveSettings(MouseEvent event) {
		if (server.getPort() != Integer.parseInt(portField.getText())) {
			server.setPort(Integer.parseInt(portField.getText()));
			System.out.println("New Port is : " + server.getPort());
			if (serverButton.getText().equals("On")) {
				try {
					server.close();
					server.listen();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		if (!(server.getUserNameDBcon().equals(dbUserNameField.getText()))
				|| !(server.getPassWordDBcon().equals(dbPasswordField.getText()))) {
			server.setUserNameDBcon(dbUserNameField.getText());
			server.setPassWordDBcon(dbPasswordField.getText());
			System.out.println("New Connection is : " + server.getUserNameDBcon() + " " + server.getPassWordDBcon());
			if (dbButton.getText().equals("On")) {
				server.setConnection(SqlUtilities.connection(server.getUserNameDBcon(), server.getPassWordDBcon()));
			}
		}
		clearSettings();
		anchorPaneSetting.setVisible(false);
	}

	/**
	 * 
	 * @param event
	 */
	public void turnOnOffServer(MouseEvent event) {
		if (!saveX) {
			setX();
		}
		if ((serverButton.localToScreen(serverButton.getBoundsInLocal()).getMinX() <= startX + 5)
				&& (serverButton.localToScreen(serverButton.getBoundsInLocal()).getMinX() >= startX - 5)) {
			if (serverButton.getText().equals("Off")) {
				translateAnimationServer.setByX(50);
				serverButton.setText("On");
				try {
					server.listen(); // Start listening for connections
				} catch (Exception ex) {
					System.out.println("ERROR - Could not listen for clients!");
				}
			}
		} else if ((serverButton.localToScreen(serverButton.getBoundsInLocal()).getMinX() <= endX + 5)
				&& (serverButton.localToScreen(serverButton.getBoundsInLocal()).getMinX() >= endX - 5)) {
			translateAnimationServer.setByX(-50);
			serverButton.setText("Off");
			try {
				server.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		translateAnimationServer.play();
		// System.out.println(serverButton.localToScreen(serverButton.getBoundsInLocal()).getMinX());
	}

	/**
	 * 
	 * @param event
	 */
	public void turnOnOffDB(MouseEvent event) {
		if (!saveX) {
			setX();
		}
		if ((dbButton.localToScreen(dbButton.getBoundsInLocal()).getMinX() <= startX + 5)
				&& (dbButton.localToScreen(dbButton.getBoundsInLocal()).getMinX() >= startX - 5)) {
			if (dbButton.getText().equals("Off")) {
				translateAnimationDB.setByX(50);
				server.setConnection(SqlUtilities.connection(server.getUserNameDBcon(), server.getPassWordDBcon()));
				dbButton.setText("On");
			}
		} else if ((dbButton.localToScreen(dbButton.getBoundsInLocal()).getMinX() <= endX + 5)
				&& (dbButton.localToScreen(dbButton.getBoundsInLocal()).getMinX() >= endX - 5)) {
			if (dbButton.getText().equals("On")) {
				translateAnimationDB.setByX(-50);
				dbButton.setText("Off");
				try {
					if (!server.getConnection().isClosed()) {
						server.getConnection().close();
						System.out.println("db connection closed");
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}
		translateAnimationDB.play();
		// System.out.println(dbButton.localToScreen(dbButton.getBoundsInLocal()).getMinX());
	}

	/**
	 * 
	 */
	private void clearSettings() {
		portField.setText(null);
		dbUserNameField.setText(null);
		dbPasswordField.setText(null);
	}

	/**
	 * 
	 */
	private void setX() {
		saveX = true;
		startX = serverButton.localToScreen(serverButton.getBoundsInLocal()).getMinX();
		endX = serverButton.localToScreen(serverButton.getBoundsInLocal()).getMinX() + 50;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.server = MainAppServer.getServer();
		translateAnimationServer.setNode(serverButton);
		translateAnimationDB.setNode(dbButton);
	}
}
