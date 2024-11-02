package game.objects.items;

import game.graphics.ImageLoader;
import game.graphics.Screen;
import game.objects.Object;

import java.awt.Rectangle;

public class Key extends Object {

    public Key() {
        name = "Key";
        image = ImageLoader.loadImage("/tiles/Sprites/Objects and buildings/Chest/Key.png");
        Rectangle rect = new Rectangle(0, 4 * Screen.scale, 16 * Screen.scale, 8 * Screen.scale);
        setCollision(rect);
    }
}
