package classes;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Flag extends ImageView {
	
	public Flag(double x, double y,Image image) {
		setX(x);
		setY(y);
		setImage(image);
	}
}
