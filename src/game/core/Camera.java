package game.core;

import game.entities.Player;
import game.graphics.Screen;

public class Camera {

    Player player;
    private final int screenX, screenY;

    public Camera(Player player){
        this.player = player;
        screenX = (Screen.screenWidth/2) - Screen.tileSize;
        screenY = (Screen.screenHeight/2) - Screen.tileSize;
    }

    public float getDrawingXPosition(){
        boolean atWorldLeft = player.worldX <= screenX;
        boolean atWorldRight = player.worldX >= World.width - screenX - Screen.tileSize*2;
        return (atWorldLeft) ? player.worldX: (atWorldRight) ? player.worldX - (World.width - Screen.screenWidth) : screenX;
    }

    public float getDrawingYPosition(){
        boolean atWorldTop = player.worldY <= screenY;
        boolean atWorldBottom = player.worldY >= World.height - screenY - Screen.tileSize*2;
        return (atWorldTop) ? player.worldY : (atWorldBottom) ? player.worldY - (World.height - Screen.screenHeight) : screenY;
    }

    public int getScreenX() { return screenX; }
    public int getScreenY() { return screenY; }
}
