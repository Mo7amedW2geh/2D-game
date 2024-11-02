package game.graphics;

import game.core.Tile;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Assets {

    public static Tile[] tiles;

    public static void initialize(){
        tilesInitializer();
        collisionInitializer();
    }

    private static void tilesInitializer(){
        int tileSize = Screen.originalTileSize;
        BufferedImage grassTileset = ImageLoader.loadImage("/tiles/grass_tileset.png");
        tiles = new Tile[50];

        for(int i = 0; i <= 35; i++){
            tiles[i] = new Tile();
        }

        assert grassTileset != null;
        //Grass
        tiles[0].image = grassTileset.getSubimage(tileSize, tileSize, tileSize, tileSize);
            //Variants
        tiles[1].image = grassTileset.getSubimage(0, tileSize * 5, tileSize, tileSize);
        tiles[2].image = grassTileset.getSubimage(tileSize, tileSize * 5, tileSize, tileSize);
        tiles[3].image = grassTileset.getSubimage(tileSize * 2, tileSize * 5, tileSize, tileSize);
        tiles[4].image = grassTileset.getSubimage(tileSize * 3, tileSize * 5, tileSize, tileSize);
        tiles[5].image = grassTileset.getSubimage(tileSize * 4, tileSize * 5, tileSize, tileSize);
        tiles[6].image = grassTileset.getSubimage(tileSize * 5, tileSize * 5, tileSize, tileSize);

        //Dirt
        tiles[7].image = grassTileset.getSubimage(tileSize * 2, tileSize * 3, tileSize, tileSize);
            //Borders
        tiles[8].image = grassTileset.getSubimage(0, 0, tileSize, tileSize);
        tiles[9].image = grassTileset.getSubimage(tileSize, 0, tileSize, tileSize);
        tiles[10].image = grassTileset.getSubimage(tileSize * 2, 0, tileSize, tileSize);
        tiles[11].image = grassTileset.getSubimage(0, tileSize, tileSize, tileSize);
        tiles[12].image = grassTileset.getSubimage(tileSize * 2, tileSize, tileSize, tileSize);
        tiles[13].image = grassTileset.getSubimage(0, tileSize * 2, tileSize, tileSize);
        tiles[14].image = grassTileset.getSubimage(tileSize, tileSize * 2, tileSize, tileSize);
        tiles[15].image = grassTileset.getSubimage(tileSize * 2, tileSize * 2, tileSize, tileSize);
            //Corners
        tiles[16].image = grassTileset.getSubimage(0, tileSize * 3, tileSize, tileSize);
        tiles[17].image = grassTileset.getSubimage(tileSize, tileSize * 3, tileSize, tileSize);
        tiles[18].image = grassTileset.getSubimage(0, tileSize * 4, tileSize, tileSize);
        tiles[19].image = grassTileset.getSubimage(tileSize, tileSize * 4, tileSize, tileSize);
            //Variants
        tiles[20].image = grassTileset.getSubimage(0, tileSize * 6, tileSize, tileSize);
        tiles[21].image = grassTileset.getSubimage(tileSize, tileSize * 6, tileSize, tileSize);
        tiles[22].image = grassTileset.getSubimage(tileSize * 2, tileSize * 6, tileSize, tileSize);

        //Water
        tiles[23].image = grassTileset.getSubimage(tileSize * 5, tileSize * 3, tileSize, tileSize);
            //Borders
        tiles[24].image = grassTileset.getSubimage(tileSize * 3, 0, tileSize, tileSize);
        tiles[25].image = grassTileset.getSubimage(tileSize * 4, 0, tileSize, tileSize);
        tiles[26].image = grassTileset.getSubimage(tileSize * 5, 0, tileSize, tileSize);
        tiles[27].image = grassTileset.getSubimage(tileSize * 3, tileSize, tileSize, tileSize);
        tiles[28].image = grassTileset.getSubimage(tileSize * 5, tileSize, tileSize, tileSize);
        tiles[29].image = grassTileset.getSubimage(tileSize * 3, tileSize * 2, tileSize, tileSize);
        tiles[30].image = grassTileset.getSubimage(tileSize * 4, tileSize * 2, tileSize, tileSize);
        tiles[31].image = grassTileset.getSubimage(tileSize * 5, tileSize * 2, tileSize, tileSize);
            //Corners
        tiles[32].image = grassTileset.getSubimage(tileSize * 3, tileSize * 3, tileSize, tileSize);
        tiles[33].image = grassTileset.getSubimage(tileSize * 4, tileSize * 3, tileSize, tileSize);
        tiles[34].image = grassTileset.getSubimage(tileSize * 3, tileSize * 4, tileSize, tileSize);
        tiles[35].image = grassTileset.getSubimage(tileSize * 4, tileSize * 4, tileSize, tileSize);



    }

    private static void collisionInitializer(){
        int tileSize = Screen.tileSize;
        int scale = Screen.scale;
        Rectangle full, rect1, rect2, rect3, rect4, rect5, rect6, rect7, rect8;

        full = new Rectangle(0, 0, tileSize, tileSize); //Full Tile
        rect1 = new Rectangle(0, 0, 16 * scale, 3 * scale); //Top
        rect2 = new Rectangle(0, 5 * scale, 16 * scale, 11 * scale); //Bottom
        rect3 = new Rectangle(0, 0, 3 * scale, 16 * scale); //Left Side
        rect4 = new Rectangle(13 * scale, 0, 3 * scale, 16 * scale); //Right Side
        rect5 = new Rectangle(13 * scale, 5 * scale, 3 * scale, 11 * scale); //Bottom Right Corner
        rect6 = new Rectangle(0, 5 * scale, 3 * scale, 11 * scale); //Bottom Left Corner
        rect7 = new Rectangle(13 * scale, 0, 3 * scale, 3 * scale); //Top Right Corner
        rect8 = new Rectangle(0, 0, 3 * scale, 3 * scale); //Top Left Corner

        //Water
        tiles[23].setCollision(full);
            //Borders
        tiles[24].setCollision(rect1, rect3);
        tiles[25].setCollision(rect1);
        tiles[26].setCollision(rect1, rect4);
        tiles[27].setCollision(rect3);
        tiles[28].setCollision(rect4);
        tiles[29].setCollision(rect2, rect3);
        tiles[30].setCollision(rect2);
        tiles[31].setCollision(rect2, rect4);
            //Corners
        tiles[32].setCollision(rect5);
        tiles[33].setCollision(rect6);
        tiles[34].setCollision(rect7);
        tiles[35].setCollision(rect8);
    }

}
