package classes;

import static utils.Constants.*;
import static classes.EMapType.IDLE;
import static classes.EMapType.IDLE2;
import static classes.EMapType.GOLD;


import java.util.List;
import java.util.function.Predicate;



import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

/**
 * This class provides static methods for handling collisions in a game.
 */
public class Collisions {

	// Private constructor to prevent instantiation of this class
	private Collisions() {
		
	}
	
	/**
     * Checks if a player collides with a map entity.
     *
     * @param p the player
     * @param e the map entity
     * @return true if the player and the entity collide, false otherwise
     */
	public static boolean collide(Player p, MapEntity e) {
		return (p.getX() + P_WIDTH > e.getX() &&
				p.getY() + P_HEIGHT > e.getY() &&
				p.getX() < e.getX() + TILE_SIZE &&
				p.getY() < e.getY() + TILE_SIZE);
	}
	
	/**
     * Checks if an enemy collides with a map entity.
     *
     * @param enemy the enemy
     * @param e the map entity
     * @return true if the enemy and the entity collide, false otherwise
     */
	public static boolean collideEnemy(Enemy enemy, MapEntity e) {
		return (enemy.getX() + P_WIDTH > e.getX() &&
				enemy.getY() + P_HEIGHT > e.getY() &&
				enemy.getX() < e.getX() + TILE_SIZE &&
				enemy.getY() < e.getY() + TILE_SIZE);
	}
	
	
	/**
     * Handles the player's collisions with the map entities on the X axis.
     *
     * @param p the player
     * @param tileMap the list of map entities
     * @param pane the javafx pane
     */
	public static void collisionPlatformX(Player p, List<MapEntity> tileMap, Pane pane) {
		Predicate<MapEntity> pr = tile -> collide(p, tile);
		var op = tileMap.stream().filter(pr).findFirst();
		if (op.isPresent()) {
			var e = op.get();
			if (e.getType()==IDLE || e.getType() == IDLE2) {
                Game.end(pane);
            }			
			if(p.vX > 0) {
				p.setX(e.getX() - P_WIDTH); //dans le cas où l'obstacle est à droite du player
			}else if(p.vX < 0) {
				p.setX(e.getX() + TILE_SIZE); //dans le cas où l'obstacle est à gauche du player
			}
		}
	}
	
	
	/**
     * Checks if an enemy collides with a map entity on the X axis.
     *
     * @param enemy the enemy
     * @param tileMap the list of map entities
     * @param pane the javafx pane
     * @return true if the enemy collides with an entity on the X axis, false otherwise
     */
	public static boolean collisionPlatformXEnemy(Enemy enemy, List<MapEntity> tileMap, Pane pane) {
		Predicate<MapEntity> pr = tile -> collideEnemy(enemy, tile);
		var op = tileMap.stream().filter(pr).findFirst();
		boolean collisionX = false;
		if (op.isPresent()) {		
			if(enemy.vX != 0) {
				collisionX = true;
			}
		}
		return collisionX;
	}
	
	
	/**
     * Handles the player's collisions with the map entities on the Y axis.
     *
     * @param p the player
     * @param tileMap the list of map entities
     * @param pane the javafx pane
     */
	static void collisionPlatformY(Player p, List<MapEntity> tileMap, Pane pane) {
		Predicate<MapEntity> pr = tile -> collide(p, tile);
		var op = tileMap.stream().filter(pr).findFirst();
		if (op.isPresent()) {
			var e = op.get();
			if (e.getType()==IDLE || e.getType() == IDLE2) {
                Game.end(pane);
            }
			if (e.getType() == GOLD) {
				Game.setNoWALL();
			}
			if(p.vY > 0) {
				p.setY(e.getY() - P_HEIGHT); //dans le cas où l'obstacle est à droite du player
			}else if(p.vY < 0) {
				p.setY(e.getY() + TILE_SIZE); //dans le cas où l'obstacle est à gauche du player
			}
			p.vY = 0; //on repasse la vitesse Y à 0 sinon la vitesse va augmenter trop vite dans la boucle de jeu
		}
	}
	
	
	/**
     * Handles the player's collision with the flag.
     *
     * @param p the player
     * @param flag the flag
     * @param pane the javafx pane
     * @param counter the text counter
     */
	static void flagCollision(Player p, Flag flag, Pane pane, Text counter) {
		if ((Math.abs(p.getX()-flag.getX()) <= 20 ) && (Math.abs(p.getY()-flag.getY()) <= 20 )) {
			if (counter.getText().equals("10")) {
				Game.victory(pane);
			}else {
				System.out.println("Va chercher les autres pommes");
			}
		}
	}
	
}