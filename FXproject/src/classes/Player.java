package classes;

import static utils.Constants.*;
import static classes.Collisions.collide;
import static classes.TileMaps.tileMap;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Player extends ImageView {
	
	double vX;
	double vY;
	List<String> inventaire = new ArrayList<String>();

	public Player(double x, double y) {
		setX(x);
		setY(y);
		setImage(PLAYER_IMG);
	}
	
	public void moveX() {
		setX(getX() + vX);
	}
	
	public void moveY() {
		vY += GRAVITY;
		setY(getY() + vY);
	}
	
	public boolean canJump() {
		Predicate<MapEntity> pr = tile -> collide(this,tile);
		var op = tileMap.stream().filter(pr);
		setY(getY() + 1); //permet de savoir si un pixel en dessous c'est un obstacle
		return op.count() > 0;
	}
	
	/**
	 * Deplace la bordure rouge sur la cases d'inventaire précédente ou suivante
	 * @param indice		indice de la selection de l'objet
	 * @param inventaire1	pane qui contient l'inventaire 1
	 * @param inventaire2	pane qui contient l'inventaire 2
	 * @param inventaire3	pane qui contient l'inventaire 3
	 * @param inventaire4	pane qui contient l'inventaire 4
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
	 * Enlève le style css qui met une bordure rouge sur l'objet sélectionné
	 * @param indice		indice de la selection de l'objet
	 * @param inventaire1	pane qui contient l'inventaire 1
	 * @param inventaire2	pane qui contient l'inventaire 2
	 * @param inventaire3	pane qui contient l'inventaire 3
	 * @param inventaire3	pane qui contient l'inventaire 4
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
	 * Indique si l'objet de l'inventaire recherché est choisi ou non
	 * @param indice		indice de la selection de l'objet
	 * @param inventaire1	pane qui contient l'inventaire 1
	 * @param inventaire2	pane qui contient l'inventaire 2
	 * @param inventaire3	pane qui contient l'inventaire 3
	 * @param inventaire3	pane qui contient l'inventaire 4
	 * @param inventaire	l'inventaire du player
	 * @return 				boolean indique si l'objet de l'inventaire est sélectionné et présent
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