package classes;

import static utils.Constants.PNJ_IMG;

import java.util.List;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Pnj extends ImageView{
	
	private static Text message = new Text("Prends cette plume\npour sauter plus\nhaut !");
	private static Polygon triangle = new Polygon(0, 0, 20, 20, 20, 0);
	private static Rectangle rectangle = new Rectangle(130, 60);


	public Pnj(double x, double y) {
		setX(x);
		setY(y);
		setImage(PNJ_IMG);
	}
	
	public static void proximitePnj(Player p, Pnj pn, int obj, Pane pane, List<String> inventaire) {
		if (inventaire.isEmpty()) {
			if ((Math.abs(p.getX()-pn.getX()) <= 40 ) && (Math.abs(p.getY()-pn.getY()) <= 40 )) {
				inventaire.add("Plume");
				
				// Création du rectangle
				rectangle.setFill(Color.WHITE);
		        rectangle.setStroke(Color.WHITE);
		        rectangle.setTranslateX(410);
		        rectangle.setTranslateY(490);

		        // Création du triangle
		        triangle.setFill(Color.WHITE);
		        triangle.setStroke(Color.WHITE);
		        triangle.setTranslateX(520);
		        triangle.setTranslateY(550);
		        
		     // Création du texte
		        message.setFont(Font.font("Arial", 14));
		        message.setTranslateX(415);
		        message.setTranslateY(508);
		        
		    

		        // Ajout de la forme et du texte au panneau
		        pane.getChildren().addAll(triangle, rectangle, message);
			}
		}else {
			pane.getChildren().removeAll(triangle, rectangle, message);
		}
		
	}
	
}