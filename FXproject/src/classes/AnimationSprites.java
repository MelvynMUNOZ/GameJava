package classes;

import static utils.Constants.*;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;

public class AnimationSprites {

	private int spriteWidth; // Largeur de chaque image
	private int spriteHeight; // Hauteur de chaque image
	private int spriteStartX; // Position X de la premi�re image
	private int spriteStartY; // Position Y de la premi�re image
	
	public AnimationSprites(int spriteWidth, int spriteHeight, int spriteStartX, int spriteStartY) {
		this.spriteWidth = spriteWidth;
		this.spriteHeight = spriteHeight;
		this.spriteStartX = spriteStartX;
		this.spriteStartY = spriteStartY;
	}

	public ImageView[] animatedPlayer(Player p) {
		if (p.vX == 0 && p.vY == 0) {
			Image sprite_list = PLAYER_IDLE;
			// Cr�ation d'un tableau qui contiendra les images extraites du sprite sheet
			ImageView[] sprites = new ImageView[11];
			for (int i = 0; i < sprites.length; i++) {
			    // Extraction de l'image actuelle
			    PixelReader reader = sprite_list.getPixelReader();
			    WritableImage spriteImage = new WritableImage(reader, this.spriteStartX, this.spriteStartY, this.spriteWidth, this.spriteHeight);
			    // Cr�ation d'un ImageView pour l'image courante
			    sprites[i] = new ImageView(spriteImage);

			    // Mise � jour des coordonn�es pour l'extraction de l'image suivante
			    spriteStartX += spriteWidth;
			}
			return sprites;
		}
		return null;
		
	}


	
}
