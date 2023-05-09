package classes;

import static utils.Constants.*;
import static classes.EMapType.IDLE;
import static classes.EMapType.IDLE2;

import java.util.List;
import java.util.function.Predicate;

public class Collisions {

	private Collisions() {
		
	}
	
	//a voir si on met private ou public
	public static boolean collide(Player p, MapEntity e) {
		return (p.getX() + P_WIDTH > e.getX() &&
				p.getY() + P_HEIGHT > e.getY() &&
				p.getX() < e.getX() + TILE_SIZE &&
				p.getY() < e.getY() + TILE_SIZE);
	}
	
	public static void collisionPlatformX(Player p, List<MapEntity> tileMap) {
		Predicate<MapEntity> pr = tile -> collide(p, tile);
		var op = tileMap.stream().filter(pr).findFirst();
		if (op.isPresent()) {
			var e = op.get();
			if (e.getType()==IDLE || e.getType() == IDLE2) {
                System.out.println("1");
            }			
			if(p.vX > 0) {
				p.setX(e.getX() - P_WIDTH); //dans le cas où l'obstacle est à droite du player
			}else if(p.vX < 0) {
				p.setX(e.getX() + TILE_SIZE); //dans le cas où l'obstacle est à gauche du player
			}
		}
	}
	
	static void collisionPlatformY(Player p, List<MapEntity> tileMap) {
		Predicate<MapEntity> pr = tile -> collide(p, tile);
		var op = tileMap.stream().filter(pr).findFirst();
		if (op.isPresent()) {
			var e = op.get();
			if (e.getType()==IDLE || e.getType() == IDLE2) {
                System.out.println("1");
            }
			if(p.vY > 0) {
				p.setY(e.getY() - P_HEIGHT); //dans le cas où l'obstacle est à droite du player
			}else if(p.vY < 0) {
				p.setY(e.getY() + TILE_SIZE); //dans le cas où l'obstacle est à gauche du player
			}
			p.vY = 0; //on repasse la vitesse Y à 0 sinon la vitesse va augmenter trop vite dans la boucle de jeu
		}
	}
	
}
