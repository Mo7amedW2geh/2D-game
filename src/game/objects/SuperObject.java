package game.objects;

import game.core.Game;
import game.graphics.Screen;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class SuperObject {

    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public int worldX, worldY;

    public void draw(Graphics2D g2d, Game game) {

        game.screen.handelWorldEdges(worldX, worldY);
        if(game.screen.isTileOnScreen()) {
            g2d.drawImage(image, game.screen.getScreenX(), game.screen.getScreenY(), Screen.tileSize, Screen.tileSize, null);
        }
    }
}
