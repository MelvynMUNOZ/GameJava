package classes;

import static utils.Constants.APPLE_IMG;


import javafx.scene.image.ImageView;

public class Items extends ImageView {
	
	public Items(double x, double y) {
		setX(x);
		setY(y);
		setImage(APPLE_IMG);
	}

}
