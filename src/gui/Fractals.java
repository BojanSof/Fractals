package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Fractals extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("layout.fxml"));
		Scene scene = new Scene(root, 1280, 768);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Fractals");
		primaryStage.show();
		primaryStage.setMinWidth(750);
		primaryStage.setMinHeight(650);
	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}
}