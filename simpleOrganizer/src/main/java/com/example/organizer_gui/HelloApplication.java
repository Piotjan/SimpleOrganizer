package com.example.organizer_gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Main class of program.
 *
 * @author Piotr Janowski
 * @version 1.0
 */
public class HelloApplication extends Application {
	@Override
	public void start(Stage stage) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("main-view.fxml"));
		Scene scene = new Scene(fxmlLoader.load());
		stage.setTitle("myOrganizer");
		stage.setScene(scene);
		stage.show();
	}
	
	/**
	 * Main method of program
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		launch();
	}
}