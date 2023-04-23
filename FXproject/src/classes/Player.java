package classes;

import static utils.Constants.*;

import javafx.scene.image.ImageView;

public class Player extends ImageView {

	public Player(double x, double y) {
		setX(x);
		setY(y);
		setImage(PLAYER_IDLE_IMG);
	}
	
}
