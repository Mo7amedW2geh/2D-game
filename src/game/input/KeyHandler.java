package game.input;

import game.core.Game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static game.core.Game.GameState.*;

public class KeyHandler implements KeyListener {

    private final Game game;
    public boolean upPressed, downPressed, leftPressed, rightPressed, spacePressed;

    public KeyHandler(Game game){
        this.game = game;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_W) {
            this.upPressed = true;
        }
        if (key == KeyEvent.VK_S) {
            this.downPressed = true;
        }
        if (key == KeyEvent.VK_A) {
            this.leftPressed = true;
        }
        if (key == KeyEvent.VK_D) {
            this.rightPressed = true;
        }
        if (key == KeyEvent.VK_SPACE) {
            this.spacePressed = true;
        }

        if (key == KeyEvent.VK_F1) {
            Game.showSolidArea = !Game.showSolidArea;
        }

        if (key == KeyEvent.VK_ESCAPE) {
            if(game.gameState == PLAY)
                game.gameState = PAUSE;
            else if(game.gameState == PAUSE)
                game.gameState = PLAY;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_W) {
            this.upPressed = false;
        }
        if (key == KeyEvent.VK_S) {
            this.downPressed = false;
        }
        if (key == KeyEvent.VK_A) {
            this.leftPressed = false;
        }
        if (key == KeyEvent.VK_D) {
            this.rightPressed = false;
        }
        if (key == KeyEvent.VK_SPACE) {
            this.spacePressed = false;
        }

    }
}
