package tile;

import game.Game;

import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Objects;

public class TileManager {

    Game game;

    public BufferedImage tileSet;
    public int[][] mapTileNum;
    public Tile[] tile;
    Tile[] dirtBorder;
    Tile[] higherGroundBorder;
    Tile[] waterBorder;

    public TileManager(Game game) {
        this.game = game;

        tile = new Tile[25];
        dirtBorder = new Tile[15];
        higherGroundBorder = new Tile[15];
        waterBorder = new Tile[15];
        mapTileNum = new int[game.maxWorldCol][game.maxWorldRow];

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
        for(int i = 0; i <= 3; i++){
            tile[i] = new Tile();
        }
        tile[0].image = tileSet.getSubimage(144, 112, 16, 16);
        tile[1].image = tileSet.getSubimage(192, 48, 16, 16);
        tile[1].collision = true;

//        tile[1].image = tileSet.getSubimage(32, 64, 16, 16);
//        tile[2].image = tileSet.getSubimage(48, 48, 16, 16);
//        tile[3].image = tileSet.getSubimage(48, 64, 16, 16);
//        tile[4].image = tileSet.getSubimage(32, 48, 16, 16);
//        tile[5].image = tileSet.getSubimage(112, 96, 16, 16);
//        tile[6].image = tileSet.getSubimage(96, 112, 16, 16);

        //Dirt
        tile[2].image = tileSet.getSubimage(112, 144, 16, 16);
//        tile[8].image = tileSet.getSubimage(112, 128, 16, 16);
//        tile[9].image = tileSet.getSubimage(96, 144, 16, 16);
//        tile[10].image = tileSet.getSubimage(96, 128, 16, 16);

        //Water
        tile[3].image = tileSet.getSubimage(272, 208, 16, 16);
        tile[3].collision = true;

        //Static Objects
//        tile[12].image = tileSet.getSubimage(16, 304, 48, 64);
//        tile[13].image = tileSet.getSubimage(64, 304, 48, 64);
//        tile[14].image = tileSet.getSubimage(112, 304, 48, 80);
//
//        tile[12].collision = true;
//        tile[13].collision = true;
//        tile[14].collision = true;
//
//        tile[15].image = tileSet.getSubimage(176, 304, 96, 48);
//        tile[16].image = tileSet.getSubimage(272, 272, 48, 96);



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

        //Higher Dirt Border
        for(int i = 0; i <= 11; i++){
            higherGroundBorder[i] = new Tile();
        }
        higherGroundBorder[0].image = tileSet.getSubimage(208, 32, 16, 16);
        higherGroundBorder[1].image = tileSet.getSubimage(176, 48, 16, 16);
        higherGroundBorder[2].image = tileSet.getSubimage(224, 48, 16, 16);
        higherGroundBorder[3].image = tileSet.getSubimage(208, 80, 16, 16);
        higherGroundBorder[8].image = tileSet.getSubimage(240, 144, 16, 16);
        higherGroundBorder[9].image = tileSet.getSubimage(176, 144, 16, 16);
        higherGroundBorder[10].image = tileSet.getSubimage(240, 96, 16, 16);
        higherGroundBorder[11].image = tileSet.getSubimage(176, 96, 16, 16);
        higherGroundBorder[4].image = tileSet.getSubimage(176, 32, 16, 16);
        higherGroundBorder[5].image = tileSet.getSubimage(224, 32, 16, 16);
        higherGroundBorder[6].image = tileSet.getSubimage(176, 80, 16, 16);
        higherGroundBorder[7].image = tileSet.getSubimage(224, 80, 16, 16);

        //Water Border
        for(int i = 0; i <= 11; i++){
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
    }

    public void loadMap(String filePath){

        try{

            InputStream inputStream = getClass().getResourceAsStream(filePath);

            assert inputStream != null;
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            int col = 0, row = 0;

            while (col < game.maxWorldCol && row < game.maxWorldRow){

                String line = bufferedReader.readLine();

                while(col < game.maxWorldCol){
                    String[] numbers = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }
                if(col == game.maxWorldCol){
                    col = 0;
                    row++;
                }
            }
            bufferedReader.close();

        } catch(Exception _){

        }

    }

    public Tile getTile(int x, int y){
        if(x < 0 || y < 0 || x >= game.maxWorldCol || y >= game.maxWorldRow) {
            return tile[0];
        }
        Tile t = tile[mapTileNum[x][y]];
        if(t == null) {
            return tile[0];
        }
        return t;
    }

    public void draw(Graphics2D g2d){

        int worldCol = 0, worldRow = 0;

        while(worldCol < game.maxWorldCol && worldRow < game.maxWorldRow){

            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * game.tileSize;
            int worldY = worldRow * game.tileSize;

            //Handling world edges
            boolean atWorldLeft = game.player.worldX <= game.player.screenX;
            boolean atWorldRight = game.player.worldX >= game.worldWidth - game.player.screenX - game.tileSize*2;
            boolean atWorldTop = game.player.worldY <= game.player.screenY;
            boolean atWorldBottom = game.player.worldY >= game.worldHeight - game.player.screenY - game.tileSize*2;

            int screenX = (atWorldLeft) ? worldX : (atWorldRight) ? worldX - (game.worldWidth - game.screenWidth) : (worldX - game.player.worldX + game.player.screenX);
            int screenY = (atWorldTop) ? worldY : (atWorldBottom) ? worldY - (game.worldHeight - game.screenHeight) : (worldY - game.player.worldY + game.player.screenY);

            boolean isTileOnScreen = (screenX + game.tileSize > 0 && screenX < game.screenWidth && screenY + game.tileSize > 0 && screenY < game.screenHeight);

            if(tileNum >=0 && tileNum <= 3 && isTileOnScreen) {
                g2d.drawImage(tile[tileNum].image, screenX, screenY, game.tileSize, game.tileSize, null);
            }

            try {
                if ((tileNum == 0 || tileNum == 1) && isTileOnScreen) { // Grass block

                    // Perform dirt and water checks
                    boolean[] dirtChecks = checkSurroundingTiles(worldCol, worldRow, 2, 2);  // 0:bottom, 1:right, 2:left, 3:top, 4:bottomRight, 5:bottomLeft, 6:topRight, 7:topLeft
                    boolean[] waterChecks = checkSurroundingTiles(worldCol, worldRow, 3, 3); // Same for water

                    // Draw Dirt Borders
                    drawBorders(g2d, dirtChecks, dirtBorder, screenX, screenY);
                    drawDiagonalBorders(g2d, dirtChecks, dirtBorder, screenX, screenY);
                    drawCornerBorders(g2d, dirtChecks, dirtBorder, screenX, screenY);

                    // Draw Water Borders
                    drawBorders(g2d, waterChecks, waterBorder, screenX, screenY);
                    drawDiagonalBorders(g2d, waterChecks, waterBorder, screenX, screenY);
                    drawCornerBorders(g2d, waterChecks, waterBorder, screenX, screenY);
                }
                if (tileNum == 0 && isTileOnScreen) {

                    // Perform higher ground checks
                    boolean[] higherGroundChecks = checkSurroundingTiles(worldCol, worldRow, 1, 1);

                    //Draw Higher Ground Borders
                    drawBorders(g2d, higherGroundChecks, higherGroundBorder, screenX, screenY);
                    drawDiagonalBorders(g2d, higherGroundChecks, higherGroundBorder, screenX, screenY);
                    drawCornerBorders(g2d, higherGroundChecks, higherGroundBorder, screenX, screenY);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            worldCol++;

            if(worldCol == game.maxWorldCol){

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
        if (checks[0]) g2d.drawImage(borderImages[0].image, x, y, game.tileSize, game.tileSize, null); // Bottom
        if (checks[1]) g2d.drawImage(borderImages[1].image, x, y, game.tileSize, game.tileSize, null); // Right
        if (checks[2]) g2d.drawImage(borderImages[2].image, x, y, game.tileSize, game.tileSize, null); // Left
        if (checks[3]) g2d.drawImage(borderImages[3].image, x, y, game.tileSize, game.tileSize, null); // Top
    }

    private void drawDiagonalBorders(Graphics2D g2d, boolean[] checks, Tile[] borderImages, int x, int y) {
        if (checks[4] && !checks[0] && !checks[1]) g2d.drawImage(borderImages[4].image, x, y, game.tileSize, game.tileSize, null); // BottomRight
        if (checks[5] && !checks[0] && !checks[2]) g2d.drawImage(borderImages[5].image, x, y, game.tileSize, game.tileSize, null); // BottomLeft
        if (checks[6] && !checks[3] && !checks[1]) g2d.drawImage(borderImages[6].image, x, y, game.tileSize, game.tileSize, null); // TopRight
        if (checks[7] && !checks[3] && !checks[2]) g2d.drawImage(borderImages[7].image, x, y, game.tileSize, game.tileSize, null); // TopLeft
    }

    private void drawCornerBorders(Graphics2D g2d, boolean[] checks, Tile[] borderImages, int x, int y) {
        if (checks[0] && checks[1]) g2d.drawImage(borderImages[8].image, x, y, game.tileSize, game.tileSize, null); // Bottom && Right
        if (checks[0] && checks[2]) g2d.drawImage(borderImages[9].image, x, y, game.tileSize, game.tileSize, null); // Bottom && Left
        if (checks[3] && checks[1]) g2d.drawImage(borderImages[10].image, x, y, game.tileSize, game.tileSize, null); // Top && Right
        if (checks[3] && checks[2]) g2d.drawImage(borderImages[11].image, x, y, game.tileSize, game.tileSize, null); // Top && Left
    }

}
