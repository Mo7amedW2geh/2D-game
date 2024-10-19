package main;

import object.Crate;
import object.Key;
import object.SmallChest;

public class AssetSetter {

    GamePanel gamePanel;
    public AssetSetter(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void setObject(){
        gamePanel.objects[0] = new Crate();
        gamePanel.objects[0].worldX = 25*gamePanel.tileSize;
        gamePanel.objects[0].worldY = 34*gamePanel.tileSize;

        gamePanel.objects[1] = new Crate();
        gamePanel.objects[1].worldX = 7*gamePanel.tileSize;
        gamePanel.objects[1].worldY = 40*gamePanel.tileSize;

        gamePanel.objects[2] = new SmallChest();
        gamePanel.objects[2].worldX = 28*gamePanel.tileSize;
        gamePanel.objects[2].worldY = 15*gamePanel.tileSize;

        gamePanel.objects[3] = new Key();
        gamePanel.objects[3].worldX = 4*gamePanel.tileSize;
        gamePanel.objects[3].worldY = 9*gamePanel.tileSize;
    }
}
