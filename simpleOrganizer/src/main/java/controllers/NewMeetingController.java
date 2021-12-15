package controllers;

import com.example.organizer_gui.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.exceptions.InvalidParameter;
import model.exceptions.MissingValuesOfParameter;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.ResourceBundle;

/**
 * Controller of NewMeeting screen.
 *
 * @author Piotr Janowski
 * @versio 1.0
 */
public class NewMeetingController implements Initializable {
	
	/**
	 * Controller to manage containers.
	 */
	private MainController controller;
	
	/**
	 * Text filed with name of meeting.
	 */
	@FXML
	private TextField tfName;
	
	/**
	 * Text field with date of meeting.
	 */
	@FXML
	private TextField tfDate;
	
	/**
	 * Text filed with duration time of meeting.
	 */
	@FXML
	private TextField tfDuration;
	
	/**
	 * Text field with place of meeting
	 */
	@FXML
	private TextField tfPlace;
	
	/**
	 * Button to confirm data of new meeting.
	 */
	@FXML
	private Button bConfirm;
	
	/**
	 * Button to go back to main screen.
	 */
	@FXML
	private Button bBack;
	
	
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
	
	}
	
	/**
	 * Method which assigns controller with meeting container.
	 *
	 * @param con cotroller with meeting container.
	 */
	public void assignController(MainController con) {
		controller = con;
	}
	
	/**
	 * Method which manage adding new meeting with parameters from user.
	 *
	 * @param event
	 */
	@FXML
	private void addMeeting(ActionEvent event) {
		String params = "";
		params += tfName.getText() + ';';
		params += tfDate.getText() + ';';
		params += tfDuration.getText() + ';';
		params += tfPlace.getText() + ';';
		try {
			controller.addMeeting(params);
		} catch (MissingValuesOfParameter | ParseException | InvalidParameter e) {
			this.showError(e.getMessage());
		}
		Stage stage = (Stage) bConfirm.getScene().getWindow();
		stage.close();
	}
	
	/**
	 * Method which closes the screen.
	 *
	 * @param event
	 */
	@FXML
	private void goBack(ActionEvent event) {
		Stage stage = (Stage) bBack.getScene().getWindow();
		stage.close();
	}
	
	/**
	 * Method calls window with error message.
	 *
	 * @param errorText exception message
	 */
	private void showError(String errorText){
		Stage stage = new Stage();
		FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Error-view.fxml"));
		Parent root = null;
		try {
			root = fxmlLoader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		ErrorController errorCont = fxmlLoader.getController();
		errorCont.showError(errorText);
		stage.setTitle("Error");
		stage.setScene(new Scene(root));
		stage.show();
	}
	
	
	
}
