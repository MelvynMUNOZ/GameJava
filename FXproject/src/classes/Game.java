package classes;

import java.util.List;

import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class Game extends AnimationTimer {

	final Player player;
	final Enemy enemy;
	final List<MapEntity> tileMap;
	final List<MapEntity> tileMapPotion;
	final Pane pane;
	final Text counter;
	final Flag flag;
	
	private static boolean potion;
	
	public Game(Player player, Enemy enemy, List<MapEntity> tileMap, Pane pane, Text counter, Flag flag, List<MapEntity> tileMapPotion) {
		this.player = player;
		this.enemy = enemy;
		this.tileMap = tileMap;
		this.pane = pane;
		this.counter = counter;
		this.flag = flag;
		this.tileMapPotion = tileMapPotion;
	}

	@Override
	public void handle(long time) { //verifie en boucle
		Enemy.collisionEnemy(player,enemy,pane); 
		getInputs();
	}
	
	void getInputs() {
		if (potion == true) {
			player.moveY();
			Collisions.collisionPlatformY(player,tileMapPotion,pane);
			player.moveX();
			Collisions.collisionPlatformX(player,tileMapPotion,pane);
			Collisions.flagCollision(player, flag, pane, counter);
		}else {
			player.moveY();
			Collisions.collisionPlatformY(player,tileMap,pane);
			player.moveX();
			Collisions.collisionPlatformX(player,tileMap,pane);
			Collisions.flagCollision(player, flag, pane, counter);
		}
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
	
	public static void victory(Pane pane) {
		pane.getChildren().clear();
		Image image = new Image("VICTORY.png");
		ImageView imageView = new ImageView(image);
		
		// Positionnement de l'ImageView au centre de la pane
        imageView.setLayoutX((pane.getWidth() - imageView.getBoundsInLocal().getWidth()) / 2);
        imageView.setLayoutY((pane.getHeight() - imageView.getBoundsInLocal().getHeight()) / 2);
        
        imageView.setPreserveRatio(true);
        
        pane.getChildren().add(imageView);
		
	}
	
	public static void setPotion () {
		if (potion == true) {
			potion = false;
		}else {
			potion = true;
		}
	}
	
	
}