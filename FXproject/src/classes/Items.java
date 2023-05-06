package classes;

import static utils.Constants.*;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;

public class Items extends ImageView {
	
//	public boolean visible;
	
	public Items(double x, double y) {
		setX(x);
		setY(y);
		setImage(APPLE_IMG);
	}
	
//	public ListView<Items> createItemsListView() {
//	    ObservableList<Items> items = FXCollections.observableArrayList();
//	    items.add(new Items(100, 500, true));
//	    items.add(new Items(150, 500, true));
//	    items.add(new Items(150, 500, true));
//	    ListView<Items> listView = new ListView<>(items);
//	    return listView;
//	}
	
//	public static ListView<String> init_items() {
//		Items apple1 = new Items(100, 500, true);
//		Items apple2 = new Items(150, 500, true);
//		Items apple3 = -;
//		Items apple4 = new Items(250, 500, true);
//		Items apple5 = new Items(300, 500, true);
//		Items apple6 = new Items(350, 500, true);
//		ObservableList<Items> items = FXCollections.observableArrayList();
//		items.add(apple1);
//		items.add(apple2);
//		items.add(apple3);
//		items.add(apple4);
//		items.add(apple5);
//		items.add(apple6);
//		ListView<String> listView = new ListView<>(items);
//		return listView;
//	}

}
