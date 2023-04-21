package classes;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class Controller {
	
	@FXML
	private Pane pane;
	@FXML
	private Text score;
	
	@FXML
	private Text level;
	// a voir pour le dernier si on les remplace ou les supprime
	@FXML
	private Text lives;
	
	public Controller(){
		
	}
	
	private void initialize() {
		
	}
	
	@FXML
	private void init() {
		//initialise la map, le jeur et les compteurs
	}
	
	@FXML
	private void start() {
		//pop up "Attraper tous les fruits pour gagner la partie"
	}
	
	@FXML
	private void restart() {
		// relance la map initiale et remet tous les conteurs à 0
	}
	
	@FXML
	private void help() {
		//affichage pop up pour les commandes et le but du jeu
	}

}
