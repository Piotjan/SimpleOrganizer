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
import model.exceptions.InvalidPhoneNumber;
import model.exceptions.MissingValuesOfParameter;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller of NewContact screen.
 *
 * @author Piotr Janowski
 * @version 1.0
 */
public class NewPersonController implements Initializable {
	
	/**
	 * Controller to manage containers.
	 */
	private MainController controller;
	
	/**
	 * Text field with name of contact.
	 */
	@FXML
	private TextField tfName;
	
	/**
	 * Text field with phone number of contact.
	 */
	@FXML
	private TextField tfPhone;
	
	/**
	 * Text field with email of contact.
	 */
	@FXML
	private TextField tfEmail;
	
	/**
	 * Text field with address of contact.
	 */
	@FXML
	private TextField tfAddress;
	
	/**
	 * Button to confirm data of new contact.
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
	 * @param cont cotroller with meeting container.
	 */
	public void setController(MainController cont) {
		controller = cont;
	}
	
	/**
	 * Method which manage adding new contact with parameters from user.
	 *
	 * @param event
	 */
	@FXML
	private void addPerson(ActionEvent event) {
		String params = "";
		params += tfName.getText() + ';';
		params += tfPhone.getText() + ';';
		params += tfEmail.getText() + ';';
		params += tfAddress.getText() + ';';
		try {
			controller.addPerson(params);
		} catch (MissingValuesOfParameter | InvalidPhoneNumber | InvalidParameter e) {
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
