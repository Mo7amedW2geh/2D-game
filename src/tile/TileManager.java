package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Objects;

public class TileManager {

    GamePanel gamePanel;

    public BufferedImage tileSet;
    public int[][] mapTileNum;
    public Tile[] tile;
    Tile[] dirtBorder;
    Tile[] waterBorder;

    public TileManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

        tile = new Tile[25];
        dirtBorder = new Tile[15];
        waterBorder = new Tile[15];
        mapTileNum = new int[gamePanel.maxWorldCol][gamePanel.maxWorldRow];

        getTileImage();
        loadMap("/maps/world01.txt");
    }

    public void getTileImage(){

        try{

            tileSet = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/Sprites/Tileset/spr_grass_tileset.png")));

        }catch(Exception e){
            e.printStackTrace();
        }

        assert tileSet != null;

        //Grass
        for(int i = 0; i <= 16; i++){
            tile[i] = new Tile();
        }
        tile[0].image = tileSet.getSubimage(144, 112, 16, 16);
        tile[1].image = tileSet.getSubimage(32, 64, 16, 16);
        tile[2].image = tileSet.getSubimage(48, 48, 16, 16);
        tile[3].image = tileSet.getSubimage(48, 64, 16, 16);
        tile[4].image = tileSet.getSubimage(32, 48, 16, 16);
        tile[5].image = tileSet.getSubimage(112, 96, 16, 16);
        tile[6].image = tileSet.getSubimage(96, 112, 16, 16);

        //Dirt
        tile[7].image = tileSet.getSubimage(112, 144, 16, 16);
        tile[8].image = tileSet.getSubimage(112, 128, 16, 16);
        tile[9].image = tileSet.getSubimage(96, 144, 16, 16);
        tile[10].image = tileSet.getSubimage(96, 128, 16, 16);

        //Water
        tile[11].image = tileSet.getSubimage(272, 208, 16, 16);
        tile[11].collision = true;
//        tile[11].setCustomCollision(-3*gamePanel.scale, -11*gamePanel.scale, 22, 30);

        //Static Objects
        tile[12].image = tileSet.getSubimage(16, 304, 48, 64);
        tile[13].image = tileSet.getSubimage(64, 304, 48, 64);
        tile[14].image = tileSet.getSubimage(112, 304, 48, 80);

        tile[12].collision = true;
        tile[13].collision = true;
        tile[14].collision = true;

        tile[15].image = tileSet.getSubimage(176, 304, 96, 48);
        tile[16].image = tileSet.getSubimage(272, 272, 48, 96);



        //Dirt Border
        for(int i = 0; i <= 11; i++){
            dirtBorder[i] = new Tile();
        }
        dirtBorder[0].image = tileSet.getSubimage(144, 128, 16, 16);
        dirtBorder[1].image = tileSet.getSubimage(160, 112, 16, 16);
        dirtBorder[2].image = tileSet.getSubimage(128, 112, 16, 16);
        dirtBorder[3].image = tileSet.getSubimage(144, 96, 16, 16);
        dirtBorder[4].image = tileSet.getSubimage(256, 96, 16, 16);
        dirtBorder[5].image = tileSet.getSubimage(304, 96, 16, 16);
        dirtBorder[6].image = tileSet.getSubimage(256, 144, 16, 16);
        dirtBorder[7].image = tileSet.getSubimage(304, 144, 16, 16);
        dirtBorder[8].image = tileSet.getSubimage(160, 128, 16, 16);
        dirtBorder[9].image = tileSet.getSubimage(128, 128, 16, 16);
        dirtBorder[10].image = tileSet.getSubimage(160, 96, 16, 16);
        dirtBorder[11].image = tileSet.getSubimage(128, 96, 16, 16);

        //Water Border
        for(int i = 0; i <= 12; i++){
            waterBorder[i] = new Tile();
        }
        waterBorder[0].image = tileSet.getSubimage(208, 240, 16, 16);
        waterBorder[1].image = tileSet.getSubimage(240, 208, 16, 16);
        waterBorder[2].image = tileSet.getSubimage(192, 208, 16, 16);
        waterBorder[3].image = tileSet.getSubimage(208, 192, 16, 16);
        waterBorder[4].image = tileSet.getSubimage(256, 192, 16, 16);
        waterBorder[5].image = tileSet.getSubimage(320, 192, 16, 16);
        waterBorder[6].image = tileSet.getSubimage(256, 240, 16, 16);
        waterBorder[7].image = tileSet.getSubimage(320, 240, 16, 16);
        waterBorder[8].image = tileSet.getSubimage(240, 240, 16, 16);
        waterBorder[9].image = tileSet.getSubimage(192, 240, 16, 16);
        waterBorder[10].image = tileSet.getSubimage(240, 192, 16, 16);
        waterBorder[11].image = tileSet.getSubimage(192, 192, 16, 16);
        waterBorder[12].image = tileSet.getSubimage(224, 240, 16, 16);
    }

    public void loadMap(String filePath){

        try{

            InputStream inputStream = getClass().getResourceAsStream(filePath);

            assert inputStream != null;
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            int col = 0, row = 0;

            while (col < gamePanel.maxWorldCol && row < gamePanel.maxWorldRow){

                String line = bufferedReader.readLine();

                while(col < gamePanel.maxWorldCol){
                    String[] numbers = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }
                if(col == gamePanel.maxWorldCol){
                    col = 0;
                    row++;
                }
            }
            bufferedReader.close();

        } catch(Exception _){

        }

    }

    public void draw(Graphics2D g2d){

        int worldCol = 0, worldRow = 0;

        while(worldCol < gamePanel.maxWorldCol && worldRow < gamePanel.maxWorldRow){

            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * gamePanel.tileSize;
            int worldY = worldRow * gamePanel.tileSize;

            //Handling world edges
            boolean atWorldLeft = gamePanel.player.worldX <= gamePanel.player.screenX;
            boolean atWorldRight = gamePanel.player.worldX >= gamePanel.worldWidth - gamePanel.player.screenX - gamePanel.tileSize*2;
            boolean atWorldTop = gamePanel.player.worldY <= gamePanel.player.screenY;
            boolean atWorldBottom = gamePanel.player.worldY >= gamePanel.worldHeight - gamePanel.player.screenY - gamePanel.tileSize*2;

            int screenX = (atWorldLeft) ? worldX : (atWorldRight) ? worldX - (gamePanel.worldWidth - gamePanel.screenWidth) : (worldX - gamePanel.player.worldX + gamePanel.player.screenX);
            int screenY = (atWorldTop) ? worldY : (atWorldBottom) ? worldY - (gamePanel.worldHeight - gamePanel.screenHeight) : (worldY - gamePanel.player.worldY + gamePanel.player.screenY);

            boolean isTileOnScreen = (screenX + gamePanel.tileSize > 0 && screenX < gamePanel.screenWidth && screenY + gamePanel.tileSize > 0 && screenY < gamePanel.screenHeight);

            if(tileNum >=0 && tileNum <= 11 && isTileOnScreen) {
                g2d.drawImage(tile[tileNum].image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
            }

            try {
                if (tileNum == 0 && isTileOnScreen) { // Grass block

                    // Perform dirt and water checks
                    boolean[] dirtChecks = checkSurroundingTiles(worldCol, worldRow, 7, 10);  // 0:bottom, 1:right, 2:left, 3:top, 4:bottomRight, 5:bottomLeft, 6:topRight, 7:topLeft
                    boolean[] waterChecks = checkSurroundingTiles(worldCol, worldRow, 11, 11); // Same for water

                    // Draw Dirt Borders
                    drawBorders(g2d, dirtChecks, dirtBorder, screenX, screenY);
                    drawDiagonalBorders(g2d, dirtChecks, dirtBorder, screenX, screenY);
                    drawCornerBorders(g2d, dirtChecks, dirtBorder, screenX, screenY);

                    // Draw Water Borders
                    drawBorders(g2d, waterChecks, waterBorder, screenX, screenY);
                    drawDiagonalBorders(g2d, waterChecks, waterBorder, screenX, screenY);
                    drawCornerBorders(g2d, waterChecks, waterBorder, screenX, screenY);


                } else if (tileNum == 1 && isTileOnScreen) {  // water grass
                    boolean bottomWater = (worldRow + 1 < mapTileNum[0].length) && (mapTileNum[worldCol][worldRow + 1] == 11);
                    if (bottomWater) {
                        g2d.drawImage(waterBorder[12].image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            worldCol++;

            if(worldCol == gamePanel.maxWorldCol){

                worldCol = 0;
                worldRow++;

            }
        }

    }

    private boolean[] checkSurroundingTiles(int col, int row, int minTile, int maxTile) {
        return new boolean[] {
                (row + 1 < mapTileNum[0].length) && (mapTileNum[col][row + 1] >= minTile && mapTileNum[col][row + 1] <= maxTile), // Bottom
                (col + 1 < mapTileNum.length) && (mapTileNum[col + 1][row] >= minTile && mapTileNum[col + 1][row] <= maxTile),     // Right
                (col - 1 >= 0) && (mapTileNum[col - 1][row] >= minTile && mapTileNum[col - 1][row] <= maxTile),                   // Left
                (row - 1 >= 0) && (mapTileNum[col][row - 1] >= minTile && mapTileNum[col][row - 1] <= maxTile),                   // Top
                (col + 1 < mapTileNum.length && row + 1 < mapTileNum[0].length) && (mapTileNum[col + 1][row + 1] >= minTile && mapTileNum[col + 1][row + 1] <= maxTile), // BottomRight
                (col - 1 >= 0 && row + 1 < mapTileNum[0].length) && (mapTileNum[col - 1][row + 1] >= minTile && mapTileNum[col - 1][row + 1] <= maxTile),               // BottomLeft
                (col + 1 < mapTileNum.length && row - 1 >= 0) && (mapTileNum[col + 1][row - 1] >= minTile && mapTileNum[col + 1][row - 1] <= maxTile),                 // TopRight
                (col - 1 >= 0 && row - 1 >= 0) && (mapTileNum[col - 1][row - 1] >= minTile && mapTileNum[col - 1][row - 1] <= maxTile)                                 // TopLeft
        };
    }

    private void drawBorders(Graphics2D g2d, boolean[] checks, Tile[] borderImages, int x, int y) {
        if (checks[0]) g2d.drawImage(borderImages[0].image, x, y, gamePanel.tileSize, gamePanel.tileSize, null); // Bottom
        if (checks[1]) g2d.drawImage(borderImages[1].image, x, y, gamePanel.tileSize, gamePanel.tileSize, null); // Right
        if (checks[2]) g2d.drawImage(borderImages[2].image, x, y, gamePanel.tileSize, gamePanel.tileSize, null); // Left
        if (checks[3]) g2d.drawImage(borderImages[3].image, x, y, gamePanel.tileSize, gamePanel.tileSize, null); // Top
    }

    private void drawDiagonalBorders(Graphics2D g2d, boolean[] checks, Tile[] borderImages, int x, int y) {
        if (checks[4] && !checks[0] && !checks[1]) g2d.drawImage(borderImages[4].image, x, y, gamePanel.tileSize, gamePanel.tileSize, null); // BottomRight
        if (checks[5] && !checks[0] && !checks[2]) g2d.drawImage(borderImages[5].image, x, y, gamePanel.tileSize, gamePanel.tileSize, null); // BottomLeft
        if (checks[6] && !checks[3] && !checks[1]) g2d.drawImage(borderImages[6].image, x, y, gamePanel.tileSize, gamePanel.tileSize, null); // TopRight
        if (checks[7] && !checks[3] && !checks[2]) g2d.drawImage(borderImages[7].image, x, y, gamePanel.tileSize, gamePanel.tileSize, null); // TopLeft
    }

    private void drawCornerBorders(Graphics2D g2d, boolean[] checks, Tile[] borderImages, int x, int y) {
        if (checks[0] && checks[1]) g2d.drawImage(borderImages[8].image, x, y, gamePanel.tileSize, gamePanel.tileSize, null); // Bottom && Right
        if (checks[0] && checks[2]) g2d.drawImage(borderImages[9].image, x, y, gamePanel.tileSize, gamePanel.tileSize, null); // Bottom && Left
        if (checks[3] && checks[1]) g2d.drawImage(borderImages[10].image, x, y, gamePanel.tileSize, gamePanel.tileSize, null); // Top && Right
        if (checks[3] && checks[2]) g2d.drawImage(borderImages[11].image, x, y, gamePanel.tileSize, gamePanel.tileSize, null); // Top && Left
    }

}
