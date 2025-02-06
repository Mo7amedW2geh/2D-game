package game.graphics;

import game.core.World;
import game.entities.Player;

public class Screen {
    //Screen settings
    public static final int originalTileSize = 16; //16x16
    public static final int scale = 3;

    public static final int tileSize = originalTileSize * scale; //48x48
    public static final int maxScreenCol = 24;
    public static final int maxScreenRow = 14;
    public static final int screenWidth = maxScreenCol * tileSize;
    public static final int screenHeight = maxScreenRow * tileSize;
    public static final int FPS = 60;

    //utils
    private final Player player;
    private int screenX, screenY;
    private boolean isTileOnScreen;
    boolean atWorldLeft, atWorldRight, atWorldTop, atWorldBottom;

    public Screen(Player player){
        this.player = player;
    }

    //Handling world edges
    public void atWorldEdges(){
        atWorldLeft = player.worldX <= player.camera.getScreenX();
        atWorldRight = player.worldX >= World.width - player.camera.getScreenX() - tileSize * 2;
        atWorldTop = player.worldY <= player.camera.getScreenY();
        atWorldBottom = player.worldY >= World.height - player.camera.getScreenY() - tileSize * 2;
    }

    public void handelWorldEdges(float worldX, float worldY) {
        screenX = Math.round((atWorldLeft) ? worldX : (atWorldRight) ? worldX - (World.width - screenWidth) : (worldX - player.worldX + player.camera.getScreenX()));
        screenY = Math.round((atWorldTop) ? worldY : (atWorldBottom) ? worldY - (World.height - screenHeight) : (worldY - player.worldY + player.camera.getScreenY()));

        isTileOnScreen = (screenX + tileSize > 0 && screenX < screenWidth && screenY + tileSize > 0 && screenY < screenHeight);
    }

    public int getScreenX(){ return screenX; }
    public int getScreenY(){ return screenY; }
    public boolean isTileOnScreen(){ return isTileOnScreen; }
}
