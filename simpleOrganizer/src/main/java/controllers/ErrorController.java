package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Class of exceptions in GUI.
 *
 * @author Piotr Janowski
 * @version 1.0
 */
public class ErrorController {
	
	/**
	 * Label with exception message.
	 */
	@FXML
	private Label lErrorMessage;
	
	/**
	 * Button to close the window.
	 */
	@FXML
	private Button bOk;
	
	/**
	 * Method assigns error message to label.
	 *
	 * @param errorText exception message
	 */
	public void showError(String errorText){
		lErrorMessage.setText(errorText);
	}
	
	/**
	 * Method manage "Ok" button and closes the window.
	 *
	 * @param event
	 */
	@FXML
	private void goBack(ActionEvent event) {
		Stage stage = (Stage) bOk.getScene().getWindow();
		stage.close();
	}
}
