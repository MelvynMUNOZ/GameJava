package classes;

import static classes.TileMaps.tileMap;
import static classes.Items.*;
import static utils.Constants.*;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.AnimationTimer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class Controller {
	
	private Player player;
	private Game game;
	
	private ImageView[] apples;
	
	@FXML
	private Pane pane;
	@FXML
	private Text counter;
	
	@FXML
	private Text level;
	// a voir pour le dernier si on les remplace ou les supprime
	@FXML
	private Text lives;
	
//	private boolean QKeyPressed = false;
//	private boolean DKeyPressed = false;
//    private boolean ZKeyPressed = false;
	
	//final KeyCombination keyZandQ = new KeyCodeCombination(KeyCode.Z, KeyCode.Q);
	
	public Controller(){
		
	}
	
	private void initialize() {
		
	}
	
	@FXML
	private void init() {
		//initialise la map, le joueur et les compteurs
		TileMaps.initMap();
		player = new Player(10, 478);
		game = new Game(player,tileMap);
		pane.getChildren().addAll(tileMap);
		System.out.println(player);
		pane.getChildren().add(player);
		//ajout des pommes
		apples = new ImageView[5];
		apples[0] = new Items(10,550);
		apples[1] = new Items(60,550);
		apples[2] = new Items(110,550);
		apples[3] = new Items(160,550);
		apples[4] = new Items(210,550);
		pane.getChildren().addAll(apples);
		// On démare un AnimationTimer pour verifier la collision avec les items apres chaque frame
		AnimationTimer timer = new AnimationTimer() {
			@Override
			public void handle(long time) {
				for (int i = 0; i < apples.length; i++) {
					if (apples[i] != null && apples[i].getBoundsInParent().intersects(player.getBoundsInParent())) {
						pane.getChildren().remove(apples[i]);
						apples[i] = null;
						counter.setText(String.valueOf(Integer.valueOf(counter.getText())+1));
					}
				}
			}
			
		};
		timer.start();
//		ObservableList<Items> items = FXCollections.observableArrayList();
//		items.add(new Items(5, 458));
//		items.add(new Items(15,600));
//		ListView<Items> listViewItems = new ListView<>(items);
//		System.out.println(listViewItems);
//		listViewItems.setItems(items);
//		System.out.println(listViewItems);
//		listViewItems.setItems(items);
//		pane.getChildren().add(listViewItems);
//		Items apple = new Items(200,500,true);
//		pane.getChildren().add(apple);
//		ListView<Items> items = Items.init_items();
//		Items items = new Items(10,200,true);
//		List<Items> itemsList = items.createItemsListView();
//		ObservableList<Items> itemsObservableList = FXCollections.observableArrayList(itemsList);
//		ListView<Items> listViewItems = new ListView<>(itemsObservableList);
//		pane.getChildren().add(listViewItems);

//		pane.getChildren().add(listViewItems);
		
		// permet de vérifier les touches enfoncées par l'utilisateur (pour déplacer le joueur)
		pane.setOnKeyPressed(this::handleKeyPressed);
		pane.setOnKeyReleased(this::handleKeyReleased);
		
	}
	
	@FXML
	private void start() {
		//pop up "Attraper tous les fruits pour gagner la partie"
		pane.requestFocus();
		game.start();
	}
	
	@FXML
	private void restart() {
		// relance la map initiale et remet tous les conteurs à 0
	}
	
	@FXML
	private void help() {
		//affichage pop up pour les commandes et le but du jeu
	}
	
	private void handleKeyPressed(KeyEvent e) {
		if(e.getCode() == KeyCode.Q) {
			player.vX = -5;
		}
		if(e.getCode() == KeyCode.D) {
			player.vX = 5;
		}
		if (e.getCode() == KeyCode.Z && player.canJump()) {
			player.vY += JUMP;
		}
	}
	
	private void handleKeyReleased(KeyEvent e) {
		if (e.getCode() == KeyCode.Q || e.getCode() == KeyCode.D) {
			player.vX = 0;
		}
	}
	
//	private void handle(long time) {
//		for (int i = 0; i < apples.length; i++) {
//			if (apples[i] != null && apples[i].getBoundsInParent().intersects(player.getBoundsInParent())) {
//				pane.getChildren().remove(apples[i]);
//				apples[i] = null;
//			}
//		}
//	}

}
