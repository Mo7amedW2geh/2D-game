package tile;

import java.awt.image.BufferedImage;

public class Tile {

    public BufferedImage image;
    public boolean collision = false;// General collision flag
    public boolean customCollision = false; // Custom collision flag

    // Define custom collision area (relative to the tile's position)
    public int collisionXOffset; // Horizontal offset (distance from the left side of the tile)
    public int collisionYOffset; // Vertical offset (distance from the top side of the tile)
    public int collisionWidth; // Custom collision width
    public int collisionHeight; // Custom collision height

    public void setCustomCollision(int x, int y, int width, int height){
        customCollision = true;
        collisionXOffset = x;
        collisionYOffset = y;
        collisionWidth = width;
        collisionHeight = height;
    }

}
