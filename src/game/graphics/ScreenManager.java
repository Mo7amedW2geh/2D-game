package game.graphics;

import game.core.Game;
import game.core.World;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class ScreenManager {

    Game game;

    public ScreenManager(Game game) {
        Assets.initialize();
        this.game = game;
    }

    public void draw(Graphics2D g2d){
        int tileSize = Screen.tileSize;
        int worldCol = 0, worldRow = 0;

        while(worldCol < World.maxWorldCol && worldRow < World.maxWorldRow){

            int tileNum = game.world.mapTileNum[worldCol][worldRow];

            int worldX = worldCol * tileSize;
            int worldY = worldRow * tileSize;

            game.screen.handelWorldEdges(worldX, worldY);
            if(game.screen.isTileOnScreen()) {
                g2d.drawImage(Assets.tiles[tileNum].image, game.screen.getScreenX(), game.screen.getScreenY(), tileSize, tileSize, null);
                if (Screen.showSolidArea && Assets.tiles[tileNum].collision) {
                    g2d.setColor(Color.red);
                    for (Rectangle rect : Assets.tiles[tileNum].solidArea)
                        g2d.drawRect(game.screen.getScreenX() + rect.x, game.screen.getScreenY() + rect.y, rect.width, rect.height);
                }
            }

            worldCol++;

            if(worldCol == World.maxWorldCol){

                worldCol = 0;
                worldRow++;

            }
        }
    }
}
