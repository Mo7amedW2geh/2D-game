package game.core;

import game.graphics.Screen;
import game.utils.AssetSetter;
import game.graphics.Display;
import game.input.KeyHandler;
import game.entities.Player;
import game.objects.SuperObject;
import game.graphics.ScreenManager;
import game.utils.CollisionChecker;

import javax.swing.JPanel;

public class Game extends JPanel implements Runnable {
    private final Display display;

    //utils
    private Thread gameThread;
    private final KeyHandler keyHandler = new KeyHandler();
    public CollisionChecker collisionChecker = new CollisionChecker(this);
    public AssetSetter assetSetter = new AssetSetter(this);
    public Player player = new Player(this, keyHandler);
    public ScreenManager screenManager = new ScreenManager(this);
    public Screen screen = new Screen(player);
    public World world = new World(this);
    public SuperObject[] objects = new SuperObject[10];

    //Main panel
    public Game() {
        display = new Display("2D Game", Screen.screenWidth, Screen.screenHeight, this);
        display.getWindow().addKeyListener(keyHandler);
        display.addKeyListener(keyHandler);
        assetSetter.setObjects();
    }

    public void start(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    //GameLoop
    @Override
    public void run() {
        double drawInterval = (1000000000f) / Screen.FPS, delta = 0;
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
            if(timer > 1000000000){
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
