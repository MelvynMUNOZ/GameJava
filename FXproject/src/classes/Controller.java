package classes;

import static classes.TileMaps.tileMap;
import static classes.Game.*;
import static utils.Constants.*;
import static classes.AnimationSprites.*;

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
	public Text counter;
	@FXML
	private Pane inventaire1;
	
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
		enemy = new Enemy(800,568); // a replacer correctement avec la map
		//TODO : faire que si le perso rencontre le monstre -> game over
		game = new Game(player,enemy,tileMap,pane);
		pane.getChildren().addAll(tileMap);
		pane.getChildren().add(player);
		pane.getChildren().add(enemy);
		pane.getChildren().add(pnj);
		//ajout des pommes
		apples = new ImageView[9];
		apples[0] = new Items(73,211);
		apples[1] = new Items(85,430);
		apples[2] = new Items(140,550);
		apples[3] = new Items(270,430);
		apples[4] = new Items(280,235);
		apples[5] = new Items(370,235);
		apples[6] = new Items(495,405);
		apples[7] = new Items(675,140);
		apples[8] = new Items(745,260);
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
						System.out.println(counter);
						System.out.println(counter.getText());
						if(Integer.valueOf(counter.getText()) == 1) {
							//Game.victory(pane); //a appeler à un autre endroit
						}
						AnimationSprites animation_test = new AnimationSprites(32, 32, 200, 200);
						animation_test.animatedPlayer(player);
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
		enemy.automaticMove(enemy,tileMap,pane);
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
			}else if(player.inventaire.contains("Trampoline")){
				player.vY += P_JUMP_OBJ;
				player.inventaire.remove("Trampoline");
			}else {
				player.vY += P_JUMP;
			}
		}
		if (e.getCode() == KeyCode.E) {
			Pnj.proximitePnj(player, pnj, obj, pane, player.inventaire, inventaire1, Integer.valueOf(counter.getText()));
		}
	}
	
	private void handleKeyReleased(KeyEvent e) {
		if (e.getCode() == KeyCode.Q || e.getCode() == KeyCode.D) {
			player.vX = 0;
		}
	}
	

}
