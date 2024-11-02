package game.objects;

import game.core.Game;
import game.graphics.Screen;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Object {

    public BufferedImage image;
    public int worldX, worldY;
    public String name;
    public boolean isSolid = false;
    public Rectangle[] collisionArea;

    public void setCollision(Rectangle... rects){
        collisionArea = rects;
    }

    public void draw(Graphics2D g2d, Game game) {

        game.screen.handelWorldEdges(worldX, worldY);
        if(game.screen.isTileOnScreen()) {
            g2d.drawImage(image, game.screen.getScreenX(), game.screen.getScreenY(), Screen.tileSize, Screen.tileSize, null);
            if (Screen.showSolidArea) {
                g2d.setColor(Color.red);
                for (Rectangle rect : collisionArea)
                    g2d.drawRect(game.screen.getScreenX() + rect.x, game.screen.getScreenY() + rect.y, rect.width, rect.height);
            }
        }
    }
}
