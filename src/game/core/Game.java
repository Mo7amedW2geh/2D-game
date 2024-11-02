package game.core;

import game.graphics.Screen;
import game.sounds.Sound;
import game.sounds.SoundManager;
import game.utils.AssetSetter;
import game.graphics.Display;
import game.input.KeyHandler;
import game.entities.Player;
import game.objects.Object;
import game.graphics.ScreenManager;
import game.utils.CollisionChecker;

import javax.swing.JPanel;

public class Game extends JPanel implements Runnable {
    private final Display display;

    //System
    private Thread gameThread;
    public World world = new World(this);
    private final KeyHandler keyHandler = new KeyHandler();

    //Entities and Objects
    public CollisionChecker collisionChecker = new CollisionChecker(this);
    public AssetSetter assetSetter = new AssetSetter(this);
    public Player player = new Player(this, keyHandler);
    public Object[] objects = new Object[10];

    //Graphics
    public ScreenManager screenManager = new ScreenManager(this);
    public Screen screen = new Screen(player);

    //Sounds
    public SoundManager soundManager = new SoundManager(this);
    public Sound sound = new Sound();

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

        soundManager.playMusic(0);
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
