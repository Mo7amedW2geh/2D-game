package main;

import javax.swing.JFrame;
import java.awt.Color;

public class Main {
    public static void main(String[] args) {

        //Frame settings
        JFrame window = new JFrame();
        window.setTitle("2D Game");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(920, 540);
        window.setResizable(false);

        GamePanel gamePanel = new GamePanel();
        gamePanel.setBackground(Color.white);
        window.add(gamePanel);
        window.pack();
        window.setLocationRelativeTo(null);

        window.setVisible(true);

        //Game start
        gamePanel.startGameThread();

    }
}