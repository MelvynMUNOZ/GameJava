package classes;


import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import static utils.Constants.*;

public class Enemy extends ImageView{

    private double vX;
    
    private static boolean enemyDead = false;

    public Enemy(double x, double y) {
        setX(x);
        setY(y);
        setImage(ENEMY_IDLE_IMG);
    }

    // a voir si on met public ou private
    public void moveX() {
        setX(getX() + vX);
    }

    public static void collisionEnemySide(Player p, Enemy e, Pane pane) {
        if (enemyDead == false) {
    	if (p.getY()>= e.getY() && p.getY()<e.getY()+P_HEIGHT) {
            if(p.vX > 0) {
                if (e.getX()<= p.getX()+P_WIDTH && e.getX()>= p.getX()) {
                    Game.end(pane);
                }
            }else if(p.vX < 0) {
                if (e.getX()<= p.getX() && e.getX()>= p.getX()-P_WIDTH) {
                	Game.end(pane);
                }
            }
        }
        // le + ou - 4 permet d'avoir une petite marge pour la position des Y dans le cas où le personnage tombe à 1 ou 2 pixels de différence par exemple
        if(p.getY()+P_HEIGHT >= e.getY()-4 && p.getY()+P_HEIGHT <= e.getY()+4) { 
            // 1ere condition : regarde le bord en bas à droite du personnage ; 2eme condition : regarde le bord en bas à gauche du personnage
            if ((p.getX()+P_WIDTH >= e.getX() && p.getX()+P_WIDTH <= e.getX()+P_WIDTH) || (p.getX() >= e.getX() && p.getX() <= e.getX()+P_WIDTH)){
                pane.getChildren().remove(e);
                enemyDead = true;
            }
        }
    }
    }

}