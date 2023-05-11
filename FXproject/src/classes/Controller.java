package classes;

import static classes.TileMaps.tileMap;
import static utils.Constants.*;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class Controller {
	
	private Player player;
	private Game game;
	
	private ImageView[] apples;
	
	private Enemy enemy;
	
	private Pnj pnj;
	
	private static int obj;
	
	@FXML
	public Pane pane;
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
	
	public static void setObj(int val) {
		obj = val;
	}
	
	@FXML
	private void init() {
		//initialise la map, le joueur et les compteurs
		TileMaps.initMap();
		player = new Player(50, 500);
		pnj = new Pnj(527,567);
		enemy = new Enemy(100,550); // a replacer correctement avec la map
		//TODO : faire que si le perso rencontre le monstre -> game over
		game = new Game(player,enemy,tileMap,pane);
		pane.getChildren().addAll(tileMap);
		pane.getChildren().add(player);
		pane.getChildren().add(enemy);
		pane.getChildren().add(pnj);
		//ajout des pommes
		apples = new ImageView[5];
		apples[0] = new Items(60,550);
		apples[1] = new Items(100,550);
		apples[2] = new Items(140,550);
		apples[3] = new Items(85,430);
		apples[4] = new Items(270,430);
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
			if (player.inventaire.contains("Plume")) { //On regarde si dans l'inventaire, l'objet Plume est présent
				player.vY += P_JUMP_OBJ;
			}else {
				player.vY += P_JUMP;
			}
		}
		if (e.getCode() == KeyCode.E) {
			Pnj.proximitePnj(player, pnj, obj, pane, player.inventaire);
		}
	}
	
	private void handleKeyReleased(KeyEvent e) {
		if (e.getCode() == KeyCode.Q || e.getCode() == KeyCode.D) {
			player.vX = 0;
		}
	}
	

}
