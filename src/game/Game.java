package game;

import entity.Player;
import object.SuperObject;
import tile.TileManager;

import javax.swing.JPanel;

public class Game extends JPanel implements Runnable {
    private final Display display;

    //Screen settings
    public final int originalTileSize = 16; //16x16
    public final int scale = 3;

    public int tileSize = originalTileSize * scale; //48x48
    public final int maxScreenCol = 24;
    public final int maxScreenRow = 14;
    public int screenWidth = maxScreenCol * tileSize;
    public int screenHeight = maxScreenRow * tileSize;
    public final int FPS = 60;

    //World settings
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = maxWorldCol * tileSize;
    public final int worldHeight = maxWorldRow * tileSize;

    //Tools
    private Thread gameThread;
    private final KeyHandler keyHandler = new KeyHandler();
    public CollisionChecker collisionChecker = new CollisionChecker(this);
    public AssetSetter assetSetter = new AssetSetter(this);
    public Player player = new Player(this, keyHandler);
    public TileManager tileManager = new TileManager(this);
    public SuperObject[] objects = new SuperObject[10];

    //Main panel
    public Game() {
        display = new Display("2D Game", screenWidth, screenHeight, this);
        display.addKeyListener(keyHandler);
    }

    public void start(){
        gameThread = new Thread(this);
        assetSetter.setObjects();
        gameThread.start();
    }

    //GameLoop
    @Override
    public void run() {
        double drawInterval = (1000000000.0) / FPS, delta = 0;
        long lastTime = System.nanoTime(), timer = 0, currentTime;
        int frames = 0;

        while(gameThread.isAlive()){
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += currentTime - lastTime;
            lastTime = currentTime;

            if(delta >= 1) {
                //Update information like player position
                this.update();

                //draw the screen with the updated information
                display.repaint();

                delta--;
                frames++;
            }
            if(timer >= 1000000000){
                System.out.println("FPS: " + frames);
                frames = 0;
                timer = 0;
            }
        }
    }

    public void update(){

        player.update();

    }
}
