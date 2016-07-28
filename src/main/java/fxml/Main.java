package fxml;

import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args) {
		Application.launch();
	}

	@Override
	public void start(Stage primaryStage) {
		try {
			Pane pane = FXMLLoader.load(Main.class
					.getResource("borderLayout.fxml"));
			 pane =
			 FXMLLoader.load(Main.class.getResource("anchorLayout.fxml"));
			 
			 pane =
			 FXMLLoader.load(Main.class.getResource("gridLayout.fxml"));
			 
			Scene scene = new Scene(pane);
			scene.getStylesheets().add("javafx/scene.css");
			primaryStage.setScene(scene);
			primaryStage.setTitle(pane.getClass().getSimpleName());
			primaryStage.show();
		} catch (Exception ex) {
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}