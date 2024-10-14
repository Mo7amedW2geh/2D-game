package object;

import main.GamePanel;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class SuperObject {

    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public int worldX, worldY;

    public void draw(Graphics2D g2d, GamePanel gamePanel) {

        //Handling world edges
        boolean atWorldLeft = gamePanel.player.worldX <= gamePanel.player.screenX;
        boolean atWorldRight = gamePanel.player.worldX >= gamePanel.worldWidth - gamePanel.player.screenX - gamePanel.tileSize*2;
        boolean atWorldTop = gamePanel.player.worldY <= gamePanel.player.screenY;
        boolean atWorldBottom = gamePanel.player.worldY >= gamePanel.worldHeight - gamePanel.player.screenY - gamePanel.tileSize*2;

        int screenX = (atWorldLeft) ? worldX : (atWorldRight) ? worldX - (gamePanel.worldWidth - gamePanel.screenWidth) : (worldX - gamePanel.player.worldX + gamePanel.player.screenX);
        int screenY = (atWorldTop) ? worldY : (atWorldBottom) ? worldY - (gamePanel.worldHeight - gamePanel.screenHeight) : (worldY - gamePanel.player.worldY + gamePanel.player.screenY);

        boolean isTileOnScreen = (screenX + gamePanel.tileSize > 0 && screenX < gamePanel.screenWidth && screenY + gamePanel.tileSize > 0 && screenY < gamePanel.screenHeight);

        if(isTileOnScreen) {
            g2d.drawImage(image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
        }
    }
}
