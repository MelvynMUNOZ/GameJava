package classes;

import static classes.TileMaps.tileMapNoWall;
import static classes.TileMaps.tileMap;
import static utils.Constants.*;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * The Controller class is used to manage the interactions between the user and the game.
 * It initializes and controls the game, the players, the enemies, the NPC, the flags, the items, and the inventory.
 */

public class Controller {
	
	private Player player; // The player controlled by the user
	private Game game; // Instance of the game
	
	private Enemy enemy; // The enemy in the game
	
	private Pnj pnj; // The NPC in the game
	
	private Flag flagB;  // The bottom flag in the game
	private Flag flagT;  // The top flag in the game
	
	private static int obj;  // The currently used object
	
	private static int inventaire_indice = 0;  // The inventory index
	
	private Stage stage_keys = new Stage();  // The stage of the keys
	
	private static boolean alreadyInit;
	
	private static boolean alreadyStart;
	
	@FXML
	public Pane pane;  // The main panel of the game
	
	@FXML
	public Text counter;  // The counter in the game
	
	@FXML
	private Pane inventaire1;  // The first inventory
	
	@FXML
	private Pane inventaire2;  // The second inventory
	
	@FXML
	private Pane inventaire3;  // The third inventory
	
	@FXML
	private Pane inventaire4;  // The fourth inventory
	
	/**
	 * Default constructor.
	 */
	public Controller(){}
	
	
	/**
	 * Sets the currently used object.
	 * @param val The value of the object to set.
	 */
	public static void setObj(int val) {
		obj = val;
	}
	
	/**
	 * Initializes the game by creating the entities, setting up the panel.
	 */
	@FXML
	private void init() {
		if (alreadyInit == false) {
			// Initializes the map, the player, and the counters
			TileMaps.initMap();
			player = new Player(50, 500);
			pnj = new Pnj(527,567);
			enemy = new Enemy(800,568); 
			flagB = new Flag(900 ,240 ,FLAGB_IMG);
			flagT = new Flag(900 ,216 ,FLAGT_IMG);
						
			game = new Game(player,enemy,tileMap,pane,counter,flagB, tileMapNoWall);
			pane.getChildren().addAll(tileMap);
			pane.getChildren().add(player);
			pane.getChildren().add(enemy);
			pane.getChildren().add(pnj);
			pane.getChildren().add(flagB);
			pane.getChildren().add(flagT);
			// Adding apples
			Apples.genereApple(pane, counter, player);
						
			// Allows to check the keys pressed by the user (to move the player)
			pane.setOnKeyPressed(this::handleKeyPressed);
			pane.setOnKeyReleased(this::handleKeyReleased);
			alreadyInit = true;
		}
	}
	
	/**
	 * Starts the game by requesting the panel's focus, starting the game and the enemy, and selecting the inventory.
	 */
	@FXML
	private void start() {
		if (alreadyStart == false) {
		//pop up "Attraper tous les fruits pour gagner la partie"
		pane.requestFocus();
		game.start();
		enemy.automaticMove(enemy,tileMap,pane);
		goal();
		player.selectInventaire(inventaire_indice, inventaire1, inventaire2, inventaire3, inventaire4);
		alreadyStart = true;
		}
	}
	
	
	/**
	 * Displays a pop-up window with the game controls when called.
	 */
	@FXML
    private void help() {
        if(stage_keys != null && stage_keys.isShowing()) {
            stage_keys.close();
        }
        else {
        	// Pop up display for the commands
            VBox keys = new VBox();
            Scene scene_keys = new Scene(keys);
            scene_keys.getStylesheets().add(getClass().getResource("/jeu.css").toExternalForm());
            stage_keys.setWidth(520);
            stage_keys.setHeight(250);
            stage_keys.setScene(scene_keys);
            stage_keys.setTitle("Help game keys");

            Label[] labels = new Label[7];
            labels[0] = new Label("Voici les touches pour jouer à ce jeu :");
            labels[1] = new Label("Q => déplacement vers la gauche");
            labels[2] = new Label("D => déplacement vers la droite");
            labels[3] = new Label("Z => saut");
            labels[4] = new Label("E => intéraction avec le PNJ");
            labels[5] = new Label("Flèche de gauche <  => décale la sélection de l'objet de l'inventaire à la case de gauche");
            labels[6] = new Label("Flèche de droite >  => décale la sélection de l'objet de l'inventaire à la case de droite");

            keys.setId("title");
            for (int i = 0; i<7; i++) {
                labels[i].getStyleClass().add("keys");
            }
            keys.getChildren().addAll(labels);

            stage_keys.show();
        }
    }
	
	/**
	 * Handles the key press events to move the player and interact with the game.
	 * @param e The key press event.
	 */
	private void handleKeyPressed(KeyEvent e) {
		if(e.getCode() == KeyCode.Q) {
			player.vX = -4;
		}
		if(e.getCode() == KeyCode.D) {
			player.vX = 4;
		}
		if (e.getCode() == KeyCode.Z && player.canJump()) {
			if (player.checkInventaireSelected(1, inventaire1, inventaire2, inventaire3, inventaire4, player.inventaire)) {
				player.vY += P_JUMP_OBJ;
			}else if(player.checkInventaireSelected(2, inventaire1, inventaire2, inventaire3, inventaire4, player.inventaire)){
				player.vY += P_JUMP_RESSORT;
				inventaire2.getChildren().clear();
				player.inventaire.remove("Trampoline");
			}else {
				player.vY += P_JUMP;
			}
		}
		if (e.getCode() == KeyCode.E) {
			Pnj.proximitePnj(player, pnj, obj, pane, player.inventaire, inventaire1, Integer.valueOf(counter.getText()), inventaire2);
		}
		if (e.getCode() == KeyCode.DIGIT2) {
			inventaire_indice++;
			inventaire_indice = inventaire_indice%4;
			player.selectInventaire(inventaire_indice, inventaire1, inventaire2, inventaire3, inventaire4);
		}
		if (e.getCode() == KeyCode.DIGIT1) {
			inventaire_indice--;
			inventaire_indice = inventaire_indice%4;
			if (inventaire_indice<0){
				inventaire_indice = inventaire_indice+4;
			}
			player.selectInventaire(inventaire_indice, inventaire1, inventaire2, inventaire3, inventaire4);
		}
	}
	
	private void handleKeyReleased(KeyEvent e) {
		if (e.getCode() == KeyCode.Q || e.getCode() == KeyCode.D) {
			player.vX = 0;
		}
	}
	
	
	/**
	 * Displays the goal of the game to the user.
	 */
	private void goal() {
        // Creation du nouveau pane contenant le message
        Pane pane_goal = new Pane();
        pane_goal.setPickOnBounds(false); 
        pane.getChildren().add(pane_goal);
        // Initialisation du label du nouveau pane
        Label goal = new Label("Atteignez le drapeau en mangeant toutes les pommes !");
        Label goal_suite = new Label("(Cliquer sur le bouton Help pour connaître les touches avec lesquels jouer)");
        goal.setId("goal");
        goal_suite.setId("goal");
        pane_goal.getChildren().add(goal);
        pane_goal.getChildren().add(goal_suite);
        goal.setLayoutX(240);
        goal.setLayoutY(320);
        goal_suite.setLayoutX(160);
        goal_suite.setLayoutY(350);
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(5), event -> {
            pane_goal.getChildren().remove(goal);
            pane_goal.getChildren().remove(goal_suite);
        }));
        timeline.setCycleCount(1);
        timeline.play();
    }

}
