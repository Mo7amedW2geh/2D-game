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

        int tileNum1, tileNum2;

        switch(entity.direction){
            case "front":
                entityBottomRow = (entityBottomWorldY + entity.speed)/gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gamePanel.tileManager.mapTileNum[entityRightCol][entityBottomRow];
                if(gamePanel.tileManager.tile[tileNum1].collision || gamePanel.tileManager.tile[tileNum2].collision){
                    entity.collisionOn = true;
                }
                break;
            case "back":
                entityTopRow = (entityTopWorldY - entity.speed)/gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gamePanel.tileManager.mapTileNum[entityRightCol][entityTopRow];
                if(gamePanel.tileManager.tile[tileNum1].collision || gamePanel.tileManager.tile[tileNum2].collision){
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed)/gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gamePanel.tileManager.mapTileNum[entityLeftCol][entityBottomRow];
                if(gamePanel.tileManager.tile[tileNum1].collision || gamePanel.tileManager.tile[tileNum2].collision){
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.speed)/gamePanel.tileSize;
                tileNum1 = gamePanel.tileManager.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = gamePanel.tileManager.mapTileNum[entityRightCol][entityBottomRow];

                if(gamePanel.tileManager.tile[tileNum1].collision || gamePanel.tileManager.tile[tileNum2].collision){
                    entity.collisionOn = true;
                }
                break;
        }

    }

    private void checkTileCollision(Entity entity, int tileNum, int tileCol, int tileRow) {
        Tile tile = gamePanel.tileManager.tile[tileNum];

        if (tile.collision) {
            entity.collisionOn = true;
        }

        // Check custom collision
        if (tile.customCollision) {
            int tileLeftWorldX = (tileNum % gamePanel.maxWorldCol) * gamePanel.tileSize + tile.collisionXOffset;
            int tileTopWorldY = (tileNum / gamePanel.maxWorldCol) * gamePanel.tileSize + tile.collisionYOffset;
            int tileRightWorldX = tileLeftWorldX + tile.collisionWidth;
            int tileBottomWorldY = tileTopWorldY + tile.collisionHeight;

            if (entity.worldX + entity.solidArea.x < tileRightWorldX &&
                    entity.worldX + entity.solidArea.x + entity.solidArea.width > tileLeftWorldX &&
                    entity.worldY + entity.solidArea.y < tileBottomWorldY &&
                    entity.worldY + entity.solidArea.y + entity.solidArea.height > tileTopWorldY) {
                entity.collisionOn = true; // Trigger collision
            }
        }
    }
}
