package game.utils;

import game.core.Game;
import game.graphics.Screen;
import game.objects.environmental.Crate;
import game.objects.items.Key;
import game.objects.environmental.SmallChest;

public class AssetSetter {

    Game game;

    public AssetSetter(Game game) {
        this.game = game;
    }
    
    public void setObjects(){
        int tileSize = Screen.tileSize;

        game.objects[0] = new Crate();
        game.objects[0].worldX = 25* tileSize;
        game.objects[0].worldY = 34* tileSize;

        game.objects[1] = new Crate();
        game.objects[1].worldX = 7* tileSize;
        game.objects[1].worldY = 40* tileSize;

        game.objects[2] = new SmallChest();
        game.objects[2].worldX = 28* tileSize;
        game.objects[2].worldY = 15* tileSize;

        game.objects[3] = new Key();
        game.objects[3].worldX = 4* tileSize;
        game.objects[3].worldY = 9* tileSize;
    }
}
