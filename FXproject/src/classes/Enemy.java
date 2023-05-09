package classes;

import javafx.beans.property.DoubleProperty;
import javafx.scene.image.ImageView;

import static utils.Constants.*;

public class Enemy extends ImageView{
	
	private double vX;

	public Enemy(double x, double y) {
		setX(x);
		setY(y);
		setImage(ENEMY_IDLE_IMG);
	}
	
	// a voir si on met public ou private
	public void moveX() {
		setX(getX() + vX);
	}
	
	//fonction temporaire
	public void coord() {
		System.out.println(getX());
		System.out.println(getY());
	}
	
	public static void collisionEnemySide(Player p, Enemy e, double x, double y) {
		if (p.getY()>= e.getY() && p.getY()<e.getY()+TILE_SIZE) {
			if(p.vX > 0) {
				if (e.getX()<= x+TILE_SIZE && e.getX()>= x) {
					System.out.println("dead");
				}
			}else if(p.vX < 0) {
				if (e.getX()<= x && e.getX()>= x-TILE_SIZE) {
					System.out.println("dead");
				}
			}
		}
		if (p.vY != 0) {
			System.out.println(p.getY()+P_HEIGHT);
			System.out.println(e.getY());
			if (p.getY()+P_HEIGHT == e.getY()) {
				System.out.println("enemy not dead");
				if (p.getX() >= e.getX() && p.getX() < e.getX()+TILE_SIZE) {
					System.out.println("enemy dead");
				}
			}
		}
	}
	
}
