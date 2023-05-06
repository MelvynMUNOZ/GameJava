package classes;

import classes.Collisions.*;

import java.util.List;

import javafx.animation.AnimationTimer;

public class Game extends AnimationTimer {

	final Player player;
	final List<MapEntity> tileMap;
	
	public Game(Player player, List<MapEntity> tileMap) {
		this.player = player;
		this.tileMap = tileMap;
	}

	@Override
	public void handle(long time) {
		getInputs();
	}
	
	void getInputs() {
		player.moveY();
		Collisions.collisionPlatformY(player,tileMap);
		player.moveX();
		Collisions.collisionPlatformX(player,tileMap);
		
	}
	
	
	
}
