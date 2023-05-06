package classes;

import static utils.Constants.*;

import java.util.List;
import java.util.function.Predicate;

public class Collisions {

	private Collisions() {
		
	}
	
	//a voir si on met private ou public
	static boolean collide(Player p, MapEntity e) {
		return (p.getX() + PLAYER_W > e.getX() &&
				p.getY() + PLAYER_H > e.getY() &&
				p.getX() < e.getX() + TILE_SIZE &&
				p.getY() < e.getY() + TILE_SIZE);
	}
	
	static void collisionPlatformX(Player p, List<MapEntity> tileMap) {
		Predicate<MapEntity> pr = tile -> collide(p, tile);
		var op = tileMap.stream().filter(pr).findFirst();
		if (op.isPresent()) {
			var e = op.get();
			
			System.out.println(e.getType());
			
			if(p.vX > 0) {
				p.setX(e.getX() - PLAYER_W); //dans le cas où l'obstacle est à droite du player
			}else if(p.vX < 0) {
				p.setX(e.getX() + TILE_SIZE); //dans le cas où l'obstacle est à gauche du player
			}
		}
//	    boolean collision = false;
//	    for (MapEntity p : TileMaps.tileMap) {
//	        if (p.getType() == EMapType.SOL) {
//	            if (e.intersects(p)) {
//	                if (dx > 0) {
//	                    e.setX(p.getX() - e.getW());
//	                    op.replace(p.getIndex(), p.getIndex() + 1, "-"); // Remplacer la valeur de 'a' par '-'
//	                } else if (dx < 0) {
//	                    e.setX(p.getX() + p.getW());
//	                    op.replace(p.getIndex(), p.getIndex() + 1, "-"); // Remplacer la valeur de 'a' par '-'
//	                }
//	                collision = true;
//	            }
//	        }
//	    }
//	    return collision;
	}
	
	static void collisionPlatformY(Player p, List<MapEntity> tileMap) {
		Predicate<MapEntity> pr = tile -> collide(p, tile);
		var op = tileMap.stream().filter(pr).findFirst();
		if (op.isPresent()) {
			var e = op.get();
			if(p.vY > 0) {
				p.setY(e.getY() - PLAYER_H); //dans le cas où l'obstacle est à droite du player
			}else if(p.vY < 0) {
				p.setY(e.getY() + TILE_SIZE); //dans le cas où l'obstacle est à gauche du player
			}
			p.vY = 0; //on repasse la vitesse Y à 0 sinon la vitesse va augmenter trop vite dans la boucle de jeu
		}
	}
	
}
