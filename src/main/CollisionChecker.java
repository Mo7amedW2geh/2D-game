package main;

import entity.Entity;
import tile.Tile;

public class CollisionChecker {

    GamePanel gamePanel;

    CollisionChecker(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void checkTile(Entity entity){
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX/gamePanel.tileSize;
        int entityRightCol = entityRightWorldX/gamePanel.tileSize;
        int entityTopRow = entityTopWorldY/gamePanel.tileSize;
        int entityBottomRow = entityBottomWorldY/gamePanel.tileSize;

        int tileNum1, tileNum2, tileNum3, tileNum4;

        switch(entity.moveDirection){
            case "front":
                // Check bottom collision (front)
                entityBottomRow = (entityBottomWorldY + entity.speed + 24)/gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gamePanel.tileManager.mapTileNum[entityRightCol][entityBottomRow];
                // Set collision if any of the tiles have collision
                if(gamePanel.tileManager.tile[tileNum1].collision || gamePanel.tileManager.tile[tileNum2].collision){
                    entity.collisionOn = true;
                }
                break;
            case "back":
                entityTopRow = (entityTopWorldY - entity.speed - 4)/gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gamePanel.tileManager.mapTileNum[entityRightCol][entityTopRow];
                if(gamePanel.tileManager.tile[tileNum1].collision || gamePanel.tileManager.tile[tileNum2].collision){
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed - 8)/gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gamePanel.tileManager.mapTileNum[entityLeftCol][entityBottomRow];
                if(gamePanel.tileManager.tile[tileNum1].collision || gamePanel.tileManager.tile[tileNum2].collision){
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.speed + 4)/gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = gamePanel.tileManager.mapTileNum[entityRightCol][entityBottomRow];
                if(gamePanel.tileManager.tile[tileNum1].collision || gamePanel.tileManager.tile[tileNum2].collision){
                    entity.collisionOn = true;
                }
                break;
            case "front-left":
                entityBottomRow = (entityBottomWorldY + entity.speed + 24)/gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gamePanel.tileManager.mapTileNum[entityRightCol][entityBottomRow];
                entityLeftCol = (entityLeftWorldX - entity.speed - 8)/gamePanel.tileSize;
                tileNum3 = gamePanel.tileManager.mapTileNum[entityLeftCol][entityTopRow];
                tileNum4 = gamePanel.tileManager.mapTileNum[entityLeftCol][entityBottomRow];
                if (gamePanel.tileManager.tile[tileNum1].collision || gamePanel.tileManager.tile[tileNum2].collision
                        || gamePanel.tileManager.tile[tileNum3].collision || gamePanel.tileManager.tile[tileNum4].collision) {
                    entity.collisionOn = true;
                }
                break;
            case "front-right":
                entityBottomRow = (entityBottomWorldY + entity.speed + 24)/gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gamePanel.tileManager.mapTileNum[entityRightCol][entityBottomRow];
                entityRightCol = (entityRightWorldX + entity.speed + 4)/gamePanel.tileSize;
                tileNum3 = gamePanel.tileManager.mapTileNum[entityRightCol][entityTopRow];
                tileNum4 = gamePanel.tileManager.mapTileNum[entityRightCol][entityBottomRow];
                if (gamePanel.tileManager.tile[tileNum1].collision || gamePanel.tileManager.tile[tileNum2].collision
                        || gamePanel.tileManager.tile[tileNum3].collision || gamePanel.tileManager.tile[tileNum4].collision) {
                    entity.collisionOn = true;
                }
                break;
            case "back-left":
                entityTopRow = (entityTopWorldY - entity.speed - 4)/gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gamePanel.tileManager.mapTileNum[entityRightCol][entityTopRow];
                entityLeftCol = (entityLeftWorldX - entity.speed - 8)/gamePanel.tileSize;
                tileNum3 = gamePanel.tileManager.mapTileNum[entityLeftCol][entityTopRow];
                tileNum4 = gamePanel.tileManager.mapTileNum[entityLeftCol][entityBottomRow];
                if (gamePanel.tileManager.tile[tileNum1].collision || gamePanel.tileManager.tile[tileNum2].collision
                        || gamePanel.tileManager.tile[tileNum3].collision || gamePanel.tileManager.tile[tileNum4].collision) {
                    entity.collisionOn = true;
                }
                break;
            case "back-right":
                entityTopRow = (entityTopWorldY - entity.speed - 4)/gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gamePanel.tileManager.mapTileNum[entityRightCol][entityTopRow];
                entityRightCol = (entityRightWorldX + entity.speed + 4)/gamePanel.tileSize;
                tileNum3 = gamePanel.tileManager.mapTileNum[entityRightCol][entityTopRow];
                tileNum4 = gamePanel.tileManager.mapTileNum[entityRightCol][entityBottomRow];
                if (gamePanel.tileManager.tile[tileNum1].collision || gamePanel.tileManager.tile[tileNum2].collision
                        || gamePanel.tileManager.tile[tileNum3].collision || gamePanel.tileManager.tile[tileNum4].collision) {
                    entity.collisionOn = true;
                }
                break;

        }
    }
}
