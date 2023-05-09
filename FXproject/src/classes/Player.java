package classes;

import static utils.Constants.*;
import static classes.Collisions.collide;
import static classes.TileMaps.tileMap;

import java.util.function.Predicate;
import javafx.scene.image.ImageView;

public class Player extends ImageView {
	
	double vX;
	double vY;

	public Player(double x, double y) {
		setX(x);
		setY(y);
		setImage(PLAYER_IMG);
	}
	
	
	// a voir si on met public ou private
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
	
}
