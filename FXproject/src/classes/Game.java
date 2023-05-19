package classes;

import java.util.List;

import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;


/**
 * Main game class managing game logic, animations, and event handling.
 * It extends AnimationTimer in order to execute a game loop.
 */
public class Game extends AnimationTimer {

	final Player player;
	final Enemy enemy;
	final List<MapEntity> tileMap;
	final List<MapEntity> tileMapNoWall;
	final Pane pane;
	final Text counter;
	final Flag flag;
	
	private static boolean noWall;
	
	/**
     * Constructor for the Game class.
     *
     * @param player the player
     * @param enemy the enemy
     * @param tileMap the game map
     * @param pane the javafx pane
     * @param counter the text counter
     * @param flag the flag
     * @param tileMapNoWALL 
     */
	public Game(Player player, Enemy enemy, List<MapEntity> tileMap, Pane pane, Text counter, Flag flag, List<MapEntity> tileMapNoWall) {
		this.player = player;
		this.enemy = enemy;
		this.tileMap = tileMap;
		this.pane = pane;
		this.counter = counter;
		this.flag = flag;
		this.tileMapNoWall = tileMapNoWall;
	}
	
	/**
     * Overriding the handle method of the AnimationTimer class.
     * It is called on every frame of the animation.
     *
     * @param time the elapsed time since the last call in nanoseconds
     */
	@Override
	public void handle(long time) { //verifie en boucle
		Enemy.collisionEnemy(player,enemy,pane); 
		getInputs();
	}
	
	/**
     * Method to handle player inputs.
     */
	void getInputs() {
		if (noWall == true) {
			player.moveY();
			Collisions.collisionPlatformY(player,tileMapNoWall,pane);
			player.moveX();
			Collisions.collisionPlatformX(player,tileMapNoWall,pane);
			Collisions.flagCollision(player, flag, pane, counter);
		}else {
			player.moveY();
			Collisions.collisionPlatformY(player,tileMap,pane);
			player.moveX();
			Collisions.collisionPlatformX(player,tileMap,pane);
			Collisions.flagCollision(player, flag, pane, counter);
		}
	}
	
	/**
     * Displays a game over screen in case of defeat.
     *
     * @param pane the javafx pane
     */
	public static void end(Pane pane) {
		pane.getChildren().clear();
		Image image = new Image("GAMEOVER.png");
		ImageView imageView = new ImageView(image);
		
		// Positioning the ImageView in the center of the pane
        imageView.setLayoutX((pane.getWidth() - imageView.getBoundsInLocal().getWidth()) / 2);
        imageView.setLayoutY((pane.getHeight() - imageView.getBoundsInLocal().getHeight()) / 2);
        
        imageView.setPreserveRatio(true);
        
        pane.getChildren().add(imageView);
	}
	
	/**
     * Displays a victory screen in case of win.
     *
     * @param pane the javafx pane
     */
	public static void victory(Pane pane) {
		pane.getChildren().clear();
		Image image = new Image("VICTORY.png");
		ImageView imageView = new ImageView(image);
		
		// Positioning the ImageView in the center of the pane
        imageView.setLayoutX((pane.getWidth() - imageView.getBoundsInLocal().getWidth()) / 2);
        imageView.setLayoutY((pane.getHeight() - imageView.getBoundsInLocal().getHeight()) / 2);
        
        imageView.setPreserveRatio(true);
        
        pane.getChildren().add(imageView);
		
	}
	
	/**
     * Method to toggle the NoWall state (enable/disable).
     */
	public static void setNoWALL () {
		if (noWall == true) {
			noWall = false;
		}else {
			noWall = true;
		}
	}
	
	
}