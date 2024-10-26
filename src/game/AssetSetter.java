package game;

import object.Crate;
import object.Key;
import object.SmallChest;

public class AssetSetter {

    Game game;
    public AssetSetter(Game game) {
        this.game = game;
    }
    
    public void setObjects(){
        game.objects[0] = new Crate();
        game.objects[0].worldX = 25* game.tileSize;
        game.objects[0].worldY = 34* game.tileSize;

        game.objects[1] = new Crate();
        game.objects[1].worldX = 7* game.tileSize;
        game.objects[1].worldY = 40* game.tileSize;

        game.objects[2] = new SmallChest();
        game.objects[2].worldX = 28* game.tileSize;
        game.objects[2].worldY = 15* game.tileSize;

        game.objects[3] = new Key();
        game.objects[3].worldX = 4* game.tileSize;
        game.objects[3].worldY = 9* game.tileSize;
    }
}
