package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

public class StudentWindowController implements Initializable, IScreenController {

	@FXML
	private AnchorPane mainAnchorPane;
	
	@FXML
	private AnchorPane welconeAnchorPane;
	
	//manual exam
	
	@FXML
	private AnchorPane manualExamAnchorPane;
	
	//computerized exam
	
	@FXML
	private AnchorPane computerizedExamAnchorPane;
	
	
	@Override
	public void setScreenParent(ScreensController screenPage) {
		
		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		mainAnchorPane.setVisible(true);
		
	}
	
	private void openManualExamHandler(ActionEvent event) {
		manualExamAnchorPane.setVisible(true);
		computerizedExamAnchorPane.setVisible(false);
		welconeAnchorPane.setVisible(false);
	}
	
	private void openComputerizedExamHandler(ActionEvent event) {
		computerizedExamAnchorPane.setVisible(true);
		manualExamAnchorPane.setVisible(false);
		welconeAnchorPane.setVisible(false);
	}

}
