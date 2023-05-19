package app;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


/**
 * The {@code Jeu} class extends the {@code Application} class from the JavaFX library.
 * It represents a platform game that is launched as a standalone JavaFX application.
 * The user interface of the game is loaded from an FXML file, and a CSS file is used to style the UI.
 */
public class Jeu extends Application {
	
	/**
	 * The {@code start} method is called after the {@code init} method has returned, and after the system is ready for the application to begin running.
	 * It is typically used to set up the user interface of the application.
	 * 
	 * @param primaryStage the primary stage for this application, onto which the application scene can be set.
	 * The primary stage will be embedded in the browser if the application was launched as an applet.
	 * Applications may create other stages, if needed, but they will not be primary stages.
	 *
	 * @throws Exception if anything goes wrong during the start of the application, such as failure to load the FXML file.
	 */
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
	
	/**
	 * The main entry point for all JavaFX applications.
	 * The start method is called after the init method has returned, and after the system is ready for the application to begin running.
	 * 
	 * @param args the command line arguments. An array of the raw command-line arguments passed to the program.
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
