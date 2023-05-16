package classes;

import static classes.TileMaps.tileMapPotion;
import static classes.TileMaps.tileMap;
import classes.Flag;
import static classes.Game.*;
import static utils.Constants.*;
import static classes.AnimationSprites.*;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Controller {
	
	private Player player;
	private Game game;
	
	private ImageView[] apples;
	
	private Enemy enemy;
	
	private Pnj pnj;
	
	private Flag flagB;
	private Flag flagT;
	
	private static int obj;
	
	private Stage stage_keys = new Stage();
	
	@FXML
	public Pane pane;
	
	@FXML
	public Text counter;
	
	@FXML
	private Pane inventaire1;
	
	@FXML
	private Pane inventaire2;
	
	@FXML
	private Pane inventaire3;
	
	public Controller(){}
	
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
		flagB = new Flag(900 ,240 ,FLAGB_IMG);
		flagT = new Flag(900 ,216 ,FLAGT_IMG);
		
		game = new Game(player,enemy,tileMap,pane,counter,flagB, tileMapPotion);
		pane.getChildren().addAll(tileMap);
		pane.getChildren().add(player);
		pane.getChildren().add(enemy);
		pane.getChildren().add(pnj);
		pane.getChildren().add(flagB);
		pane.getChildren().add(flagT);
		//ajout des pommes
		apples = new ImageView[10];
		apples[0] = new Items(73,211);
		apples[1] = new Items(85,430);
		apples[2] = new Items(140,550);
		apples[3] = new Items(270,430);
		apples[4] = new Items(280,235);
		apples[5] = new Items(370,235);
		apples[6] = new Items(495,405);
		apples[7] = new Items(675,140);
		apples[8] = new Items(745,260);
		apples[9] = new Items(810,568);
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
		goal();
	}
	
	@FXML
    private void help() {
        if(stage_keys != null && stage_keys.isShowing()) {
            stage_keys.close();
        }
        else {
            //affichage pop up pour les commandes
            VBox keys = new VBox();
            Scene scene_keys = new Scene(keys);
            scene_keys.getStylesheets().add(getClass().getResource("/jeu.css").toExternalForm());
            stage_keys.setWidth(300);
            stage_keys.setHeight(200);
            stage_keys.setScene(scene_keys);
            stage_keys.setTitle("Help game keys");

            Label[] labels = new Label[5];
            labels[0] = new Label("Voici les touches pour jouer à ce jeu :");
            labels[1] = new Label("Q => déplacement vers la gauche");
            labels[2] = new Label("D => déplacement vers la droite");
            labels[3] = new Label("Z => saut");
            labels[4] = new Label("E => intéraction avec le PNJ");

            keys.setId("title");
            for (int i = 0; i<5; i++) {
                labels[i].getStyleClass().add("keys");
            }
            keys.getChildren().addAll(labels);

            stage_keys.show();
        }
    }
	
	private void handleKeyPressed(KeyEvent e) {
		if(e.getCode() == KeyCode.Q) {
			player.vX = -4;
		}
		if(e.getCode() == KeyCode.D) {
			player.vX = 4;
		}
		if (e.getCode() == KeyCode.Z && player.canJump()) {
			if (player.inventaire.contains("Plume")) { //On regarde si dans l'inventaire, l'objet Plume est présent
				player.vY += P_JUMP_OBJ;
			}else if(player.inventaire.contains("Trampoline")){
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
	}
	
	private void handleKeyReleased(KeyEvent e) {
		if (e.getCode() == KeyCode.Q || e.getCode() == KeyCode.D) {
			player.vX = 0;
		}
	}
	
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
