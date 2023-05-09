package classes;

import static utils.Constants.*;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;

public class Items extends ImageView {
	
	public Items(double x, double y) {
		setX(x);
		setY(y);
		setImage(APPLE_IMG);
	}

}
