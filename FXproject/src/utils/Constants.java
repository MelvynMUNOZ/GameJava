package utils;

import javafx.scene.image.Image;

public class Constants {

//	public static final double TILE_SIZE = 16;
	
//	public static final Image SOL_IMG = new Image("sol1.png");
//	// declarer les autres images
//	public static final Image EMPTY_IMG = new Image("empty.png");
	
	//pour tester avec les images de 32 pixels
	
	public static final double TILE_SIZE = 32;
	public static final Image SOL_IMG = new Image("sol(32x32).png");
	// declarer les autres images
	public static final Image EMPTY_IMG = new Image("empty(32x32).png");
	public static final Image PLAYER_IDLE_IMG = new Image("frog_idle.png");
	public static final Image APPLE_IMG = new Image("apple.png");
	
	public static final double GRAVITY = 0.5;
	public static final double JUMP = -10;
	
	public static final double PLAYER_H = 32;
	public static final double PLAYER_W = 32;
	
	private Constants() {
		
	}
	
}
