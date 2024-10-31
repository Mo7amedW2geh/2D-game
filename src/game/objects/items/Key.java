package game.objects.items;

import game.objects.SuperObject;

import javax.imageio.ImageIO;
import java.util.Objects;

public class Key extends SuperObject {

    public Key() {
        name = "key";
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/Sprites/Objects and buildings/Chest/Key.png")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
