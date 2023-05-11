package classes;

import classes.Collisions.*;
import classes.Enemy;

import static utils.Constants.TILE_SIZE;

import java.util.List;

import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Game extends AnimationTimer {

	final Player player;
	final Enemy enemy;
	final List<MapEntity> tileMap;
	final Pane pane;
	
	public Game(Player player, Enemy enemy, List<MapEntity> tileMap, Pane pane) {
		this.player = player;
		this.enemy = enemy;
		this.tileMap = tileMap;
		this.pane = pane;
	}

	@Override
	public void handle(long time) {
		getInputs();
	}
	
	void getInputs() {
		player.moveY();
		Collisions.collisionPlatformY(player,tileMap,pane);
		player.moveX();
		Collisions.collisionPlatformX(player,tileMap,pane);
		Enemy.collisionEnemySide(player, enemy, pane);
	}
	
	public static void end(Pane pane) {
		pane.getChildren().clear();
		Image image = new Image("GAMEOVER.png");
		ImageView imageView = new ImageView(image);
		
		// Positionnement de l'ImageView au centre de la pane
        imageView.setLayoutX((pane.getWidth() - imageView.getBoundsInLocal().getWidth()) / 2);
        imageView.setLayoutY((pane.getHeight() - imageView.getBoundsInLocal().getHeight()) / 2);
        
        imageView.setPreserveRatio(true);
        
        pane.getChildren().add(imageView);
	}
	
	
	
}
