package game.objects.environmental;

import game.utils.ImageUtility;
import game.graphics.Screen;
import game.objects.Object;

import java.awt.Rectangle;

public class SmallChest extends Object {

    public SmallChest(){
        name = "Chest";
        image = ImageUtility.loadImage("/tiles/Sprites/Objects and buildings/Chest/spr_small_chest.png");
        Rectangle rect = new Rectangle(0, 5 * Screen.scale, 16 * Screen.scale, 9 * Screen.scale);
        setCollision(rect);
        isSolid = true;
    }

}
