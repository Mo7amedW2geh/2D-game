package game.objects.environmental;

import game.graphics.ImageLoader;
import game.graphics.Screen;
import game.objects.Object;

import java.awt.Rectangle;

public class Crate extends Object {

    public Crate(){
        name = "Crate";
        image = ImageLoader.loadImage("/tiles/Sprites/Objects and buildings/Barrels and crates/spr_crate1.png");
        Rectangle rect = new Rectangle(2 * Screen.scale, 5 * Screen.scale, 12 * Screen.scale, 9 * Screen.scale);
        setCollision(rect);
        isSolid = true;
    }


}
