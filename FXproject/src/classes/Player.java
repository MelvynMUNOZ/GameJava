package classes;

import static utils.Constants.*;
import static classes.Collisions.collide;
import static classes.TileMaps.tileMap;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;



/**
 * This class represents a player in the game.
 * The player is represented by an image and has a position and a speed on the X and Y axes.
 * It also has an inventory for storing objects.
 */
public class Player extends ImageView {
	
	double vX; // Speed on the X axis
	double vY; // Speed on the Y axis
	List<String> inventaire = new ArrayList<String>(); // Player's inventory
	
	
	/**
	 * Player constructor.
	 *
	 * @param x the initial position of the player on the X axis
	 * @param y the initial position of the player on the Y axis
	 */
	public Player(double x, double y) {
		setX(x);
		setY(y);
		setImage(PLAYER_IMG);
	}
	

	/**
	 * Moves the player on the X axis according to its speed.
	 */
	public void moveX() {
		setX(getX() + vX);
	}
	
	/**
	 * Moves the player on the Y axis according to its speed and gravity.
	 */
	public void moveY() {
		vY += GRAVITY;
		setY(getY() + vY);
	}
	
	/**
	 * Checks if the player can jump by verifying if there is an obstacle below him.
	 *
	 * @return true if the player can jump, false otherwise
	 */
	public boolean canJump() {
		Predicate<MapEntity> pr = tile -> collide(this,tile);
		var op = tileMap.stream().filter(pr);
		setY(getY() + 1); //permet de savoir si un pixel en dessous c'est un obstacle
		return op.count() > 0;
	}
	
	/**
	 * Moves the red border to the previous or next inventory slot
	 * @param index			index of the object selection
	 * @param inventory1	pane containing inventory 1
	 * @param inventory2	pane containing inventory 2
	 * @param inventory3	pane containing inventory 3
	 * @param inventory4	pane containing inventory 4
	 */
	public void selectInventaire(int indice, Pane inventaire1, Pane inventaire2, Pane inventaire3, Pane inventaire4) {
		switch (indice) {
			case 0:
				removeBorderInventaire(indice, inventaire1, inventaire2, inventaire3, inventaire4);
				inventaire1.getStyleClass().add("inventary_actived");
				break;
			case 1:
				removeBorderInventaire(indice, inventaire1, inventaire2, inventaire3, inventaire4);
				inventaire2.getStyleClass().add("inventary_actived");
				break;
			case 2:
				removeBorderInventaire(indice, inventaire1, inventaire2, inventaire3, inventaire4);
				inventaire3.getStyleClass().add("inventary_actived");
				break;
			case 3:
				removeBorderInventaire(indice, inventaire1, inventaire2, inventaire3, inventaire4);
				inventaire4.getStyleClass().add("inventary_actived");
				break;
			default:
				break;
			
		}
	}
	
	/**
	 * Removes the CSS style that places a red border on the selected item
	 * @param index			index of the object selection
	 * @param inventory1	pane containing inventory 1
	 * @param inventory2	pane containing inventory 2
	 * @param inventory3	pane containing inventory 3
	 * @param inventory4	pane containing inventory 4
	 */
	private void removeBorderInventaire(int indice, Pane inventaire1, Pane inventaire2, Pane inventaire3, Pane inventaire4) {
		if(inventaire1.getStyleClass().contains("inventary_actived")) {
			inventaire1.getStyleClass().remove("inventary_actived");
		}else if(inventaire2.getStyleClass().contains("inventary_actived")) {
			inventaire2.getStyleClass().remove("inventary_actived");
		}else if(inventaire3.getStyleClass().contains("inventary_actived")) {
			inventaire3.getStyleClass().remove("inventary_actived");
		}else if(inventaire4.getStyleClass().contains("inventary_actived")) {
			inventaire4.getStyleClass().remove("inventary_actived");
		}
	}
	
	/**
	 * Indicates whether the searched inventory item is selected or not
	 * @param index			index of the object selection
	 * @param inventory1	pane containing inventory 1
	 * @param inventory2	pane containing inventory 2
	 * @param inventory3	pane containing inventory 3
	 * @param inventory4	pane containing inventory 4
	 * @param inventory		the player's inventory
	 * @return 				boolean indicating whether the inventory item is selected and present
	 */
	public boolean checkInventaireSelected(int indice, Pane inventaire1, Pane inventaire2, Pane inventaire3, Pane inventaire4,  List<String> inventaire) {
		boolean selected = false;
		switch (indice) {
			case 1:
				if(inventaire1.getStyleClass().contains("inventary_actived") && inventaire.contains("Plume")) {
					selected = true;
				}
				break;
			case 2:
				if(inventaire2.getStyleClass().contains("inventary_actived") && inventaire.contains("Trampoline")) {
					selected = true;
				}
				break;
			case 3:
				if(inventaire3.getStyleClass().contains("inventary_actived")) { //ajouter la vérification de l'inventaire en fonction de l'objet
					selected = true;
				}
				break;
			case 4:
				if(inventaire4.getStyleClass().contains("inventary_actived")) { //ajouter la vérification de l'inventaire en fonction de l'objet
					selected = true;
				}
				break;
			default:
				break;
		}
		return selected;
	}
	
}