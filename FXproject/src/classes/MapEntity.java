package classes;

import static utils.Constants.*;

import javafx.scene.image.ImageView;

public class MapEntity extends ImageView {

	final EMapType type;
	
	public MapEntity(double x, double y, EMapType type) {
		setX(x);
		setY(y);
		this.type = type;
		setImage(switch (type){
			case SOLG -> SOLG_IMG;
			case SOLD -> SOLD_IMG;
			case SOLM -> SOLM_IMG;
			case TERREG -> TERREG_IMG;
			case TERREM -> TERREM_IMG;
			case TERRED -> TERRED_IMG;
			case IDLE -> PIQUEH_IMG;
			case IDLE2 -> PIQUEV_IMG;
			case PLATFORMEM -> PLATFORMEM_IMG;
			case PLATFORMEG -> PLATFORMEG_IMG;
			case PLATFORMED -> PLATFORMED_IMG;
			case PLATFORME -> PLATFORME_IMG;
			case PLATFORMEHG -> PLATFORMEHG_IMG;
			case PLATFORMEHD -> PLATFORMEHD_IMG;
			case PLATFORMEBG -> PLATFORMEBG_IMG;
			case PLATFORMEBD -> PLATFORMEBD_IMG;
			case BRIQUE -> BRIQUE_IMG;
			case BOISG -> BOISG_IMG;
			case BOISD -> BOISD_IMG;
			case BOISV -> BOISV_IMG;
			case BOISH -> BOISH_IMG;
			case COTEVG -> COTEVG_IMG;
			case COTEVD -> COTEVD_IMG;
			case COTEB -> COTEB_IMG;
			case COTE1 -> COTE1_IMG;
			case COTE2 -> COTE2_IMG;
			case COTE3 -> COTE3_IMG;
			case COTE4 -> COTE4_IMG;
			case COTE5 -> COTE5_IMG;
			case COTE6 -> COTE6_IMG;
			case GOLD -> GOLD_IMG;
			default -> EMPTY_IMG;
		});
	}
	
	public EMapType getType() {
	    return type;
	}
	
}
