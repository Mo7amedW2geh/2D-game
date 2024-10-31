package game.objects.environmental;

import game.objects.SuperObject;

import javax.imageio.ImageIO;
import java.util.Objects;

public class SmallChest extends SuperObject {

    public SmallChest(){
        name = "Chest";
        try{
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/Sprites/Objects and buildings/Chest/spr_small_chest.png")));
        }catch(Exception e){
            e.printStackTrace();
        }

    }

}
