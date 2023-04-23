package classes;

import static utils.Constants.*;

import javafx.scene.image.ImageView;

public class MapEntity extends ImageView {

	final EMapType type;
	
	public MapEntity(double x, double y, EMapType type) {
		setX(x);
		setY(y);
		this.type = type;
		setImage(switch(type) {
			case SOL -> SOL_IMG;
			default  -> EMPTY_IMG;
		});
	}
	
}
