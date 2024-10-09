package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public boolean upPressed, downPressed, leftPressed, rightPressed;

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

    }
}
