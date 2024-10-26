package object;

import game.Game;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class SuperObject {

    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public int worldX, worldY;

    public void draw(Graphics2D g2d, Game game) {

        //Handling world edges
        boolean atWorldLeft = game.player.worldX <= game.player.screenX;
        boolean atWorldRight = game.player.worldX >= game.worldWidth - game.player.screenX - game.tileSize*2;
        boolean atWorldTop = game.player.worldY <= game.player.screenY;
        boolean atWorldBottom = game.player.worldY >= game.worldHeight - game.player.screenY - game.tileSize*2;

        int screenX = (atWorldLeft) ? worldX : (atWorldRight) ? worldX - (game.worldWidth - game.screenWidth) : (worldX - game.player.worldX + game.player.screenX);
        int screenY = (atWorldTop) ? worldY : (atWorldBottom) ? worldY - (game.worldHeight - game.screenHeight) : (worldY - game.player.worldY + game.player.screenY);

        boolean isTileOnScreen = (screenX + game.tileSize > 0 && screenX < game.screenWidth && screenY + game.tileSize > 0 && screenY < game.screenHeight);

        if(isTileOnScreen) {
            g2d.drawImage(image, screenX, screenY, game.tileSize, game.tileSize, null);
        }
    }
}
