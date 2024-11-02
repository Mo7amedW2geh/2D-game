package game.utils;

import game.core.Game;
import game.entities.Entity;
import game.graphics.Screen;
import game.core.Tile;

import java.awt.Rectangle;

public class CollisionChecker {

    Game game;
    private final int tileSize = Screen.tileSize;
    private int index = 999;

    public CollisionChecker(Game game) {
        this.game = game;
    }

    public boolean canMove(Entity entity, Entity.Direction direction, float speed){
        entity.collisionOn = false;
        boolean isPlayer = (entity.getClass() == game.player.getClass());
        
        int entityLeftWorldX = (int) (entity.worldX + entity.solidArea.x);
        int entityRightWorldX = (int) (entity.worldX + entity.solidArea.x + entity.solidArea.width);
        int entityTopWorldY = (int) (entity.worldY + entity.solidArea.y);
        int entityBottomWorldY = (int) (entity.worldY + entity.solidArea.y + entity.solidArea.height);

        Rectangle solidArea = new Rectangle(entityLeftWorldX, entityTopWorldY, entity.solidArea.width, entity.solidArea.height);
        int entityLeftCol = entityLeftWorldX/ tileSize;
        int entityRightCol = entityRightWorldX/ tileSize;
        int entityTopRow = entityTopWorldY/ tileSize;
        int entityBottomRow = entityBottomWorldY/ tileSize;
        
        switch(direction){
            case BACK:
                entityTopRow = (int) ((entityTopWorldY - speed) / tileSize);
                checkObjectCollision(entity, isPlayer, solidArea, 0, -speed);
                checkTileCollision(entity, solidArea, entityLeftCol, entityTopRow, 0, -speed);
                checkTileCollision(entity, solidArea, entityRightCol, entityTopRow, 0, -speed);
                break;
            case FRONT:
                entityBottomRow = (int) ((entityBottomWorldY + speed) / tileSize);
                checkObjectCollision(entity, isPlayer, solidArea, 0, speed);
                checkTileCollision(entity, solidArea, entityLeftCol, entityBottomRow, 0, speed);
                checkTileCollision(entity, solidArea, entityRightCol, entityBottomRow, 0, speed);
                break;
            case RIGHT:
                entityRightCol = (int) ((entityRightWorldX + speed) / tileSize);
                checkObjectCollision(entity, isPlayer, solidArea, speed, 0);
                checkTileCollision(entity, solidArea, entityRightCol, entityTopRow, speed, 0);
                checkTileCollision(entity, solidArea, entityRightCol, entityBottomRow, speed, 0);
                break;
            case LEFT:
                entityLeftCol = (int) ((entityLeftWorldX - speed) / tileSize);
                checkObjectCollision(entity, isPlayer, solidArea, -speed, 0);
                checkTileCollision(entity, solidArea, entityLeftCol, entityTopRow, -speed, 0);
                checkTileCollision(entity, solidArea, entityLeftCol, entityBottomRow, -speed, 0);
                break;
        }
        return !entity.collisionOn;
    }

    private void checkTileCollision(Entity entity,Rectangle soldArea, int col, int row, float xOffset, float yOffset){
        Tile tile = game.world.getTile(col, row);
        for(Rectangle rect : tile.solidArea){
            float rectX = col * tileSize + rect.x - xOffset;
            float rectY = row * tileSize + rect.y - yOffset;
            if(soldArea.intersects(rectX, rectY, rect.width, rect.height) && tile.collision){
                entity.collisionOn = true;
                break;
            }
        }
    }

    private void checkObjectCollision(Entity entity, boolean player,Rectangle soldArea, float xOffset, float yOffset){
        index = 999;

        for(int i = 0; i < game.objects.length; i++){
            if (game.objects[i] == null) break;
            for(Rectangle rect : game.objects[i].collisionArea) {
                float rectX = game.objects[i].worldX + rect.x - xOffset;
                float rectY = game.objects[i].worldY + rect.y - yOffset;
                if(soldArea.intersects(rectX, rectY, rect.width, rect.height)){
                    if(player) { index = i; }
                    if(game.objects[i].isSolid){
                        entity.collisionOn = true;
                        break;
                    }
                }
            }
        }
    }

    public int getIndex() { return index; }
}
