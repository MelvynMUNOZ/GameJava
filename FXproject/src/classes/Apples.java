package classes;

import static utils.Constants.APPLE_IMG;

import javafx.animation.AnimationTimer;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

/**
 * Class representing apples to collect in a game.
 * Inherits from ImageView to graphically represent the apples.
 */
public class Apples extends ImageView {
	
	/**
     * Array of ImageView representing the apples to be collected in the game.
     */
	private static ImageView[] apples; // The items to be collected in the game
	
	/**
     * Constructor to create a new apple.
     *
     * @param x the horizontal position of the apple
     * @param y the vertical position of the apple
     */
	public Apples(double x, double y) {
		setX(x);
		setY(y);
		setImage(APPLE_IMG);
	}
	
	/**
     * Generates apples in the game, adds them to the panel, and checks for collisions with the player.
     *
     * @param pane the panel to which to add the apples
     * @param counter the text counter to display the number of apples collected
     * @param player the player to check for collisions
     */
	public static void genereApple (Pane pane, Text counter, Player player) {
		apples = new ImageView[10];
		apples[0] = new Apples(73,211);
		apples[1] = new Apples(85,430);
		apples[2] = new Apples(140,550);
		apples[3] = new Apples(270,430);
		apples[4] = new Apples(280,235);
		apples[5] = new Apples(370,235);
		apples[6] = new Apples(495,405);
		apples[7] = new Apples(675,140);
		apples[8] = new Apples(745,260);
		apples[9] = new Apples(810,568);
		pane.getChildren().addAll(apples);
		// Start an AnimationTimer to check for item collision after each frame
		AnimationTimer timer = new AnimationTimer() {
			@Override
			public void handle(long time) {
				for (int i = 0; i < apples.length; i++) {
					if (apples[i] != null && apples[i].getBoundsInParent().intersects(player.getBoundsInParent())) {
						pane.getChildren().remove(apples[i]);
						apples[i] = null;
						counter.setText(String.valueOf(Integer.valueOf(counter.getText())+1));
					}
				}
			}
			
		};
		timer.start();
	}

}
