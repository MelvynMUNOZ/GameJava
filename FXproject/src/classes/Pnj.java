package classes;

import static utils.Constants.PNJ_IMG;

import java.util.List;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

/**
 *Class representing a Non-Player Character (NPC) in a game.
 *It extends the ImageView class to be displayed in a JavaFX graphical interface.
 */
public class Pnj extends ImageView{
	
	private static Text message = new Text("Prends cette plume\npour sauter plus\nhaut !");
	private static Polygon triangle = new Polygon(0, 0, 20, 20, 20, 0);
	private static Rectangle rectangle = new Rectangle(130, 60);  //(largeur,hauteur)
	

	/**
	 * Constructor for an NPC.
	 *
	 * @param x the x position of the NPC
	 * @param y the y position of the NPC
	 */
	public Pnj(double x, double y) {
		setX(x);
		setY(y);
		setImage(PNJ_IMG);
	}
	
	/**
	 * Method to manage the proximity between a player and an NPC.
	 * If the player is close to the NPC, certain actions are triggered.
	 *
	 * @param p the player
	 * @param pn the NPC
	 * @param obj an object
	 * @param pane the javafx pane
	 * @param inventory the inventory list of the player
	 * @param inventory1 the first inventory
	 * @param counter_apple the apple counter
	 * @param inventory2 the second inventory
	 */
	public static void proximitePnj(Player p, Pnj pn, int obj, Pane pane, List<String> inventaire, Pane inventaire1, int counter_apple, Pane inventaire2) {
		if (inventaire.isEmpty()) {
			if ((Math.abs(p.getX()-pn.getX()) <= 40 ) && (Math.abs(p.getY()-pn.getY()) <= 40 )) { //pour etre dans la zone proche du pnj
				if (counter_apple<4) {
		        	//rectangle
					rectangle.setFill(Color.WHITE);
			        rectangle.setStroke(Color.WHITE);
			        rectangle.setTranslateX(355);
			        rectangle.setTranslateY(480);
			        
			        rectangle.setWidth(185);
			        rectangle.setHeight(70);

			        //triangle
			        triangle.setFill(Color.WHITE);
			        triangle.setStroke(Color.WHITE);
			        triangle.setTranslateX(520);
			        triangle.setTranslateY(550);
			        
			        //message
			        message.setFont(Font.font("Arial", 14));
			        message.setTranslateX(355);
			        message.setTranslateY(495);
		        	
					message.setText("Prend ce ressort.\nSort de ce trou et va prendre\ntoutes les pommes avant\nde revenir me voir.");
					inventaire.add("Trampoline");
					
					
					Image image = new Image("Trampoline.png");
                    ImageView imageView = new ImageView(image);

                    inventaire2.getChildren().add(imageView);
					
				}else {
					rectangle.setFill(Color.WHITE);
			        rectangle.setStroke(Color.WHITE);
			        rectangle.setTranslateX(410);
			        rectangle.setTranslateY(490);
			        
			        rectangle.setWidth(130);
			        rectangle.setHeight(60);

			        triangle.setFill(Color.WHITE);
			        triangle.setStroke(Color.WHITE);
			        triangle.setTranslateX(520);
			        triangle.setTranslateY(550);
			        
			        message.setFont(Font.font("Arial", 14));
			        message.setTranslateX(415);
			        message.setTranslateY(508);
					
					message.setText("Prends cette plume\npour sauter plus\nhaut !");
					inventaire.add("Plume");
					
					Image image = new Image("Plume.png");
					ImageView imageView = new ImageView(image);
					
					inventaire1.getChildren().add(imageView);
				}

		        pane.getChildren().addAll(triangle, rectangle, message);
		        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(4), event -> {
		        	pane.getChildren().removeAll(triangle, rectangle, message);
				}));
				timeline.setCycleCount(1);
		        timeline.play();
				
			}
		}
	}
}