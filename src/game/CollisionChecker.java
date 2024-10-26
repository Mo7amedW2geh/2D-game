package game;

import entity.Entity;
import tile.Tile;

public class CollisionChecker {

    Game game;

    CollisionChecker(Game game) {
        this.game = game;
    }

    public void checkTile(Entity entity){
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX/ game.tileSize;
        int entityRightCol = entityRightWorldX/ game.tileSize;
        int entityTopRow = entityTopWorldY/ game.tileSize;
        int entityBottomRow = entityBottomWorldY/ game.tileSize;

        int tileNum1, tileNum2, tileNum3, tileNum4;
        Tile tile1, tile2, tile3, tile4;

        switch(entity.moveDirection){
            case "front":
                // Check bottom collision (front)
                entityBottomRow = (entityBottomWorldY + entity.speed + 24)/ game.tileSize;
                tile1 = game.tileManager.getTile(entityLeftCol, entityBottomRow);
                tile2 = game.tileManager.getTile(entityRightCol, entityBottomRow);
                // Set collision if any of the tiles have collision
                if(tile1.collision || tile2.collision){
                    entity.collisionOn = true;
                }
                break;
            case "back":
                entityTopRow = (entityTopWorldY - entity.speed - 4)/ game.tileSize;
                tile1 = game.tileManager.getTile(entityLeftCol, entityTopRow);
                tile2 = game.tileManager.getTile(entityRightCol, entityTopRow);
                if(tile1.collision || tile2.collision){
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed - 8)/ game.tileSize;
                tile1 = game.tileManager.getTile(entityLeftCol, entityTopRow);
                tile2 = game.tileManager.getTile(entityLeftCol, entityBottomRow);
                if(tile1.collision || tile2.collision){
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.speed + 4)/ game.tileSize;
                tile1 = game.tileManager.getTile(entityRightCol, entityTopRow);
                tile2 = game.tileManager.getTile(entityRightCol, entityBottomRow);
                if(tile1.collision || tile2.collision){
                    entity.collisionOn = true;
                }
                break;
            case "front-left":
                entityBottomRow = (entityBottomWorldY + entity.speed + 24)/ game.tileSize;
                tile1 = game.tileManager.getTile(entityLeftCol, entityBottomRow);
                tile2 = game.tileManager.getTile(entityRightCol, entityBottomRow);
                entityLeftCol = (entityLeftWorldX - entity.speed - 8)/ game.tileSize;
                tile3 = game.tileManager.getTile(entityLeftCol, entityTopRow);
                tile4 = game.tileManager.getTile(entityLeftCol, entityBottomRow);
                if (tile1.collision || tile2.collision || tile3.collision || tile4.collision) {
                    entity.collisionOn = true;
                }
                break;
            case "front-right":
                entityBottomRow = (entityBottomWorldY + entity.speed + 24)/ game.tileSize;
                tile1 = game.tileManager.getTile(entityLeftCol, entityBottomRow);
                tile2 = game.tileManager.getTile(entityRightCol, entityBottomRow);
                entityRightCol = (entityRightWorldX + entity.speed + 4)/ game.tileSize;
                tile3 = game.tileManager.getTile(entityRightCol, entityTopRow);
                tile4 = game.tileManager.getTile(entityRightCol, entityBottomRow);
                if (tile1.collision || tile2.collision || tile3.collision || tile4.collision) {
                    entity.collisionOn = true;
                }
                break;
            case "back-left":
                entityTopRow = (entityTopWorldY - entity.speed - 4)/ game.tileSize;
                tile1 = game.tileManager.getTile(entityLeftCol, entityTopRow);
                tile2 = game.tileManager.getTile(entityRightCol, entityTopRow);
                entityLeftCol = (entityLeftWorldX - entity.speed - 8)/ game.tileSize;
                tile3 = game.tileManager.getTile(entityLeftCol, entityTopRow);
                tile4 = game.tileManager.getTile(entityLeftCol, entityBottomRow);
                if (tile1.collision || tile2.collision || tile3.collision || tile4.collision) {
                    entity.collisionOn = true;
                }
                break;
            case "back-right":
                entityTopRow = (entityTopWorldY - entity.speed - 4)/ game.tileSize;
                tile1 = game.tileManager.getTile(entityLeftCol, entityTopRow);
                tile2 = game.tileManager.getTile(entityRightCol, entityTopRow);
                entityRightCol = (entityRightWorldX + entity.speed + 4)/ game.tileSize;
                tile3 = game.tileManager.getTile(entityRightCol, entityTopRow);
                tile4 = game.tileManager.getTile(entityRightCol, entityBottomRow);
                if (tile1.collision || tile2.collision || tile3.collision || tile4.collision) {
                    entity.collisionOn = true;
                }
                break;

        }
    }
}
