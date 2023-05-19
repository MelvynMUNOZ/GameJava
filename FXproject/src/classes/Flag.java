package classes;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


/**
 * This class represents a flag in the game, which is a subclass of ImageView in JavaFX.
 * The flag can be positioned at a specified location on the game screen and can have a specific image.
 */
public class Flag extends ImageView {
	
	/**
     * Constructor for the Flag class.
     *
     * @param x the x-coordinate of the flag on the game screen
     * @param y the y-coordinate of the flag on the game screen
     * @param image the image of the flag to display
     */
	public Flag(double x, double y,Image image) {
		setX(x);
		setY(y);
		setImage(image);
	}
}
