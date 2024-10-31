package game.core;

import game.graphics.Assets;
import game.graphics.Screen;
import game.graphics.Tile;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class World {
    //World settings
    public static final int maxWorldCol = 50;
    public static final int maxWorldRow = 50;
    public static final int width = maxWorldCol * Screen.tileSize;
    public static final int height = maxWorldRow * Screen.tileSize;

    Game game;
    public int[][] mapTileNum;

    public World(Game game) {
        this.game = game;

        mapTileNum = new int[maxWorldCol][maxWorldRow];
        loadMap("/maps/world01.txt");
    }

    public void loadMap(String filePath){
        try{
            InputStream inputStream = getClass().getResourceAsStream(filePath);
            int col = 0, row = 0;

            assert inputStream != null;
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            while (col < maxWorldCol && row < maxWorldRow){
                String line = bufferedReader.readLine();
                while(col < maxWorldCol){
                    String[] numbers = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }
                if(col == maxWorldCol){
                    col = 0;
                    row++;
                }
            }
            bufferedReader.close();

        } catch(Exception _){}
    }

    public Tile getTile(float x, float y){
        if(x < 0 || y < 0 || x >= maxWorldCol || y >= maxWorldRow) {
            return Assets.tiles[0];
        }
        Tile tile = Assets.tiles[mapTileNum[(int) x][(int) y]];
        if(tile == null) {
            return Assets.tiles[0];
        }
        return tile;
    }
}
