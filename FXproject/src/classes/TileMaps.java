package classes;

import static classes.EMapType.*;
import static utils.Constants.*;

import java.util.ArrayList;
import java.util.List;

public class TileMaps {

	protected static final String[] map= 
		{"-giiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiih",
	   "-k-------------------------------------j",
	   "-k-------------------------------------j",
	   "-k---------------------------2---------j",
	   "-k--------------------------456--------j",
	   "-k-------------------------------------j",
	   "-k2--3---------------------------------j",
	   "-k78---------22------------------------j",
	   "-k90-------456456------------2------tacj",
	   "-k------------------------tabbbc----tdej",
	   "-k------------------------tdeeef----tdrq",
	   "-k------------------------tdeeef----tdl-",
	   "-k------------------------tdeeef----tdl-",
	   "-k------------------------tdeeef----tdl-",
	   "-k----------------22------tdeeef----tdl-",
	   "-k2-----22------abbbbc----tdeeef----tdl-",
	   "-k346---45633---deeeef----tdeeef----tdl-",
	   "-k--------------deeeef----tdeeef----tdl-",
	   "-k--------------df--df----tdeeef----tdl-",
	   "-k--------------df-3df----tdeeef----tdl-",
	   "ns------22222222debbef----tdeeef----tdl-",
	   "k1111111abbbbbbceeeeef---abeeeef----tdl-",
	   "k1111111deeeeeeeee3eabbbbbbbce3eabbbcel-",
	   "ommmmmmmmmmmmmmmmmmmmpeeeeermmmmmmmmmmq-",
	   "---------------------ommmmmq------------",};
		
		protected static List<MapEntity> tileMap = new ArrayList<>();
		
		private TileMaps() {}
		
		public static void initMap() {
			for(var i=0; i<25; i++) {
				for (var j=0; j<40; j++) {
					var type = switch (map[i].charAt(j)) {
						case 'a' -> SOLG;
						case 'b' -> SOLM;
						case 'c' -> SOLD;
						case 'd' -> TERREG;
						case 'e' -> TERREM;
						case 'f' -> TERRED;
						case 'g' -> BOISG;
						case 'h' -> BOISD;
						case 'i' -> BOISH;
						case 'j' -> BOISV;
						case 'k' -> COTEVG;
						case 'l' -> COTEVD;
						case 'm' -> COTEB;
						case 'n' -> COTE2;
						case 'o' -> COTE3;
						case 'p' -> COTE4;
						case 'q' -> COTE5;
						case 'r' -> COTE6;
						case 's' -> COTE1;
						case 't' -> IDLE2;
						case '1' -> BRIQUE;
						case '2' -> IDLE;
						case '3' -> PLATFORME;
						case '4' -> PLATFORMEG;
						case '5' -> PLATFORMEM;
						case '6' -> PLATFORMED;
						case '7' -> PLATFORMEHG;
						case '8' -> PLATFORMEHD;
						case '9' -> PLATFORMEBG;
						case '0' -> PLATFORMEBD;
						default  -> EMPTY;
					};if (type != EMPTY) {
				          tileMap.add(new MapEntity(j * TILE_SIZE, (i+3) * TILE_SIZE, type));
			        }
				}
			}
		}
}

