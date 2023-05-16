package classes;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import static utils.Constants.*;

import java.util.ArrayList;
import java.util.List;

public class Enemy extends ImageView{
	
	double vX;
	boolean collision_right;
	private static boolean enemyDead = false;
	List<String> inventaireEnemy = new ArrayList<String>();

	public Enemy(double x, double y) {
		setX(x);
		setY(y);
		setImage(ENEMY_IDLE_IMG);
	}
	
	public static void collisionEnemy(Player p, Enemy e, Pane pane) {
		if(enemyDead == false) {
			if (p.getY()>= e.getY() && p.getY()<e.getY()+P_HEIGHT) {
				if(p.vX > 0) {
					if (e.getX()<= p.getX()+P_WIDTH && e.getX()>= p.getX()) {
						Game.end(pane);
					}
				}else if(p.vX < 0) {
					if (e.getX()<= p.getX() && e.getX()>= p.getX()-P_WIDTH) {
						Game.end(pane);
					}
				}else {
					if ((e.getX()<= p.getX() && e.getX()>= p.getX()-P_WIDTH) || (e.getX()<= p.getX()+P_WIDTH && e.getX()>= p.getX())) {
						Game.end(pane);
					}
				}
			}
			// le + ou - 4 permet d'avoir une petite marge pour la position des Y dans le cas où le personnage tombe à 1 ou 2 pixels de différence par exemple
			if(p.getY()+P_HEIGHT >= e.getY()-4 && p.getY()+P_HEIGHT <= e.getY()+4) { 
				// 1ere condition : regarde le bord en bas à droite du personnage ; 2eme condition : regarde le bord en bas à gauche du personnage
				if ((p.getX()+P_WIDTH >= e.getX() && p.getX()+P_WIDTH <= e.getX()+P_WIDTH) || (p.getX() >= e.getX() && p.getX() <= e.getX()+P_WIDTH)){
					pane.getChildren().remove(e);
					enemyDead = true;
				}
			}	
		}
	}
	
	public static void automaticMove(Enemy e, List<MapEntity> tileMap, Pane pane) { //faire un timer qui fait bouger le perso 1 fois toutes les secondes
		e.collision_right = false;
		Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.02), event -> {
			if (e.collision_right == false) {
				if(Collisions.collisionPlatformXEnemy(e, tileMap, pane) == false) {
					e.vX = 1;
					e.setX(e.getX()+e.vX);
				}else {
					e.vX = 0;
					e.collision_right = true;
				}
			}else {
				if(Collisions.collisionPlatformXEnemy(e, tileMap, pane) == false) {
					e.vX = -1;
					e.setX(e.getX()+e.vX);
				}else {
					e.vX = 0;
					e.collision_right = false;
				}
			}
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
	}
	
}
