package controllers;

import com.example.organizer_gui.HelloApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import model.exceptions.InvalidObjectToDelete;
import model.exceptions.MissingValuesOfParameter;
import model.organizerClasses.Meeting;
import model.organizerClasses.Person;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.ResourceBundle;


/**
 * Controller of main view of application
 *
 * @author Piotr Janowski
 * @version 1.1
 */
public class StartWindowController implements Initializable {
	
	/**
	 * Controller to manage containers.
	 */
	private MainController controller;
	
	/**
	 * Tableview of Meetings.
	 */
	@FXML
	private TableView<Meeting> tvMeetings;
	
	/**
	 * Tableview of contacts.
	 */
	@FXML
	private TableView<Person> tvContacts;
	
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		try {
			controller = new MainController("meetings.csv", "people.csv");
		} catch (ParseException | IOException | MissingValuesOfParameter e) {
			this.showError(e.getMessage());
		}
		//Set Meetings table
		TableColumn<Meeting, String> dateColumn = new TableColumn<>("Date");
		dateColumn.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
		
		TableColumn<Meeting, String> nameColumn = new TableColumn<>("Name");
		nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
		
		TableColumn<Meeting, String> durationColumn = new TableColumn<>("Duration");
		durationColumn.setCellValueFactory(cellData -> cellData.getValue().durationProperty());
		
		TableColumn<Meeting, String> placeColumn = new TableColumn<>("Place");
		placeColumn.setCellValueFactory(cellData -> cellData.getValue().placeProperty());
		tvMeetings.getColumns().addAll(dateColumn, nameColumn, durationColumn, placeColumn);
		
		//Set Contacts table
		TableColumn<Person, String> namePerColumn = new TableColumn<>("Name");
		namePerColumn.setCellValueFactory((cellData -> cellData.getValue().nameProperty()));
		
		TableColumn<Person, String> phoneNumberColumn = new TableColumn<>("Phone Number");
		phoneNumberColumn.setCellValueFactory((cellData -> cellData.getValue().phoneNumberProperty()));
		
		TableColumn<Person, String> emailColumn = new TableColumn<>("Email");
		emailColumn.setCellValueFactory((cellData -> cellData.getValue().emailProperty()));
		
		TableColumn<Person, String> addressColumn = new TableColumn<>("Address");
		addressColumn.setCellValueFactory((cellData -> cellData.getValue().addressProperty()));
		tvContacts.getColumns().addAll(namePerColumn, phoneNumberColumn, emailColumn, addressColumn);
		
		this.refreshMeetings();
		this.refreshPeople();
	}
	
	/**
	 * Method opens and manage new window to add new meeting.
	 *
	 * @param event
	 */
	@FXML
	private void createNewMeeting(ActionEvent event) {
		Stage stage = new Stage();
		FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("NewMeeting-view.fxml"));
		Parent root = null;
		try {
			root = fxmlLoader.load();
		} catch (IOException e) {
			this.showError(e.getMessage());
		}
		NewMeetingController newMeetingCont = fxmlLoader.getController();
		newMeetingCont.assignController(controller);
		stage.setTitle("New Event");
		stage.setScene(new Scene(root));
		stage.show();
	}
	
	/**
	 * Method opens and manage new window to add new contact.
	 *
	 * @param event
	 */
	@FXML
	private void createNewContact(ActionEvent event) {
		Stage stage = new Stage();
		FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("NewPerson-view.fxml"));
		Parent root = null;
		try {
			root = fxmlLoader.load();
		} catch (IOException e) {
			this.showError(e.getMessage());
		}
		NewPersonController newPersonCont = fxmlLoader.getController();
		newPersonCont.setController(controller);
		stage.setTitle("New Contact");
		stage.setScene(new Scene(root));
		stage.show();
	}
	
	/**
	 * Method called after click button "Refresh"
	 *
	 * @param event
	 */
	@FXML
	public void refMeet(ActionEvent event) {
		refreshMeetings();
	}
	
	/**
	 * Method called after click button "Refresh"
	 *
	 * @param event
	 */
	@FXML
	public void refPeop(ActionEvent event) {
		refreshPeople();
	}
	
	/**
	 * Method deletes chosen meeting.
	 *
	 * @param event
	 */
	@FXML
	public void deleteMeeting(ActionEvent event) {
		ObservableList<Meeting> selectedRow;
		selectedRow = tvMeetings.getSelectionModel().getSelectedItems();
		if (!selectedRow.isEmpty()) {
			try {
				controller.deleteMeeting(selectedRow.get(0).getName());
				this.refreshMeetings();
			} catch (InvalidObjectToDelete e) {
				this.showError(e.getMessage());
			}
		}
	}
	
	/**
	 * Method deletes chosen contact.
	 *
	 * @param event
	 */
	@FXML
	public void deletePerson(ActionEvent event) {
		ObservableList<Person> selectedRow;
		selectedRow = tvContacts.getSelectionModel().getSelectedItems();
		if (!selectedRow.isEmpty()) {
			try {
				controller.deletePerson(selectedRow.get(0).getName());
				this.refreshPeople();
			} catch (InvalidObjectToDelete e) {
				this.showError(e.getMessage());
			}
		}
	}
	
	/**
	 * Method refreshes tableview of meetings.
	 */
	private void refreshMeetings() {
		tvMeetings.getItems().clear();
		ObservableList<Meeting> items = FXCollections.observableList(controller.calendar());
		tvMeetings.getItems().addAll(items);
		
	}
	
	/**
	 * Method refreshes tableview of contacts.
	 */
	private void refreshPeople() {
		tvContacts.getItems().clear();
		ObservableList<Person> items = FXCollections.observableList(controller.contacts());
		tvContacts.getItems().addAll(items);
	}
	
	/**
	 * Method calls window with error message.
	 *
	 * @param errorText exception message
	 */
	private void showError(String errorText) {
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