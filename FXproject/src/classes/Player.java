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
	
	//inventaire1.getStyleClass().add("inventary_actived");
	public void selectInventaire(int indice, Pane inventaire1, Pane inventaire2, Pane inventaire3) {
		switch (indice) {
			case 0:
				removeBorderInventaire(indice, inventaire1, inventaire2, inventaire3);
				inventaire1.getStyleClass().add("inventary_actived");
				break;
			case 1:
				removeBorderInventaire(indice, inventaire1, inventaire2, inventaire3);
				inventaire2.getStyleClass().add("inventary_actived");
				break;
			case 2:
				removeBorderInventaire(indice, inventaire1, inventaire2, inventaire3);
				inventaire3.getStyleClass().add("inventary_actived");
				break;
			default:
				break;
			
		}
				
	}
	
	private void removeBorderInventaire(int indice, Pane inventaire1, Pane inventaire2, Pane inventaire3) {
		if(inventaire1.getStyleClass().contains("inventary_actived")) {
			inventaire1.getStyleClass().remove("inventary_actived");
		}else if(inventaire2.getStyleClass().contains("inventary_actived")) {
			inventaire2.getStyleClass().remove("inventary_actived");
		}else if(inventaire3.getStyleClass().contains("inventary_actived")) {
			inventaire3.getStyleClass().remove("inventary_actived");
		}
	}
	
	public boolean checkInventaireSelected(int indice, Pane inventaire1, Pane inventaire2, Pane inventaire3,  List<String> inventaire) {
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
				if(inventaire3.getStyleClass().contains("inventary_actived")) { //ajouter ce qu'on veut mettre dans l'inventaire 3
					selected = true;
				}
				break;
			default:
				break;
		}
		return selected;
	}
	
}