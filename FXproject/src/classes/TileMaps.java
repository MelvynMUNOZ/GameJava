package classes;

import static classes.EMapType.*;
import static utils.Constants.*;

import java.util.ArrayList;
import java.util.List;


/**
 * This class provides methods to initialize the game map.
 * It creates and places different entities on the map according to the predefined configuration.
 */
public class TileMaps {
	// The character array representing the configuration of the map
	protected static final String[] map= 
		{"-giiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiih",
	   "-k-------------------------------------j",
	   "-k-------------------------------------j",
	   "-k---------------------------2---------j",
	   "-k--------------------------456--------j",
	   "-k-------------------------------------j",
	   "-k2--3---------------------------------j",
	   "-k78-----------------------------------j",
	   "-k90---------22--------------2------tacj",
	   "-k---------456456----------abbbc----tdej",
	   "-k------------------------tdeeef----tdrq",
	   "-k------------------------tdeeef----tdl-",
	   "-k------------------------tdeeef----tdl-",
	   "-k------------------------tdeeef3---tdl-",
	   "-k----------------22------tdeeef----tdl-",
	   "-k2-----22------abbbbc----tdeeef----tdl-",
	   "-k346---45633---deeeef----tdeeef----tdl-",
	   "-k--------------deeeef----tdeeef----tdl-",
	   "-k--------------df--df----tdeeef3---tdl-",
	   "-k--------------df-3df----tdeeefu---tdl-",
	   "ns------22222222debbef----tdeeef----tdl-",
	   "k1111111abbbbbbceeeeef---abeeeef----tdl-",
	   "k1111111deeeeeeeee3eabbbbbbbce3eabbbcel-",
	   "ommmmmmmmmmmmmmmmmmmmpeeeeermmmmmmmmmmq-",
	   "---------------------ommmmmq------------",};
		
		// The list of entities on the map
		protected static List<MapEntity> tileMap = new ArrayList<>();
		
		// The list of entities on the map where a potion can appear
	    protected static List<MapEntity> tileMapNoWall = new ArrayList<>();

	    // Private constructor to prevent instantiation of this class
		private TileMaps() {}
		
		/**
	     * Initializes the game map.
	     * Creates and places different entities on the map according to the predefined configuration.
	     */
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
						case 'u' -> GOLD;
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
					if (type == BRIQUE || type == SOLG || type == SOLM || type == SOLD || type == PLATFORME || type == PLATFORMEG || type == PLATFORMEM || type == PLATFORMED || type == PLATFORMEHG || type == PLATFORMEHD || type == PLATFORMEBG || type == PLATFORMEBD || type == GOLD
							|| type == COTEVD || type == COTEVG || type == COTEB || type == COTE1 || type == COTE2 || type == COTE3 || type == COTE4 || type == COTE5 || type == COTE6 || type == BOISG || type == BOISD || type == BOISH || type == BOISV || type == IDLE || type == IDLE2) {
						tileMapNoWall.add(new MapEntity(j * TILE_SIZE, (i+3) * TILE_SIZE, type));
					}
				}
			}
		}
}

