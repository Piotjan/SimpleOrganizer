module com.example.organizer_gui {
	requires javafx.controls;
	requires javafx.fxml;
	
	
	opens com.example.organizer_gui to javafx.fxml;
	exports com.example.organizer_gui;
	exports controllers;
	opens controllers to javafx.fxml;
}