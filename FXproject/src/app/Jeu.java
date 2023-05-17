package app;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Jeu extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = FXMLLoader.load(getClass().getResource("/map.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/jeu.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Jeu de plateforme");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
