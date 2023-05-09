package classes;

import classes.Collisions.*;
import classes.Enemy;

import static utils.Constants.TILE_SIZE;

import java.util.List;

import javafx.animation.AnimationTimer;

public class Game extends AnimationTimer {

	final Player player;
	final Enemy enemy;
	final List<MapEntity> tileMap;
	
	public Game(Player player, Enemy enemy, List<MapEntity> tileMap) {
		this.player = player;
		this.enemy = enemy;
		this.tileMap = tileMap;
	}

	@Override
	public void handle(long time) {
		getInputs();
	}
	
	void getInputs() {
		player.moveY();
		Collisions.collisionPlatformY(player,tileMap);
		Enemy.collisionEnemySide(player, enemy, player.getX(), player.getY());
		player.moveX();
		Collisions.collisionPlatformX(player,tileMap);
		
	}
	
	
	
}
