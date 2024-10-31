package game.objects.environmental;

import game.objects.SuperObject;

import javax.imageio.ImageIO;
import java.util.Objects;

public class Crate extends SuperObject {

    public Crate(){
        name = "Crate";
        try{
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/Sprites/Objects and buildings/Barrels and crates/spr_crate1.png")));
        }catch(Exception e){
            e.printStackTrace();
        }

    }


}
