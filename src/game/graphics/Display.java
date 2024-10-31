package game.graphics;

import game.core.Game;
import game.objects.SuperObject;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Display extends JPanel {
    private JFrame window;
    private final String title;
    private final int screenWidth;
    private final int screenHeight;
    private final Game game;

    public Display(String title, int width, int height, Game game){
        this.title = title;
        this.screenWidth = width;
        this.screenHeight = height;
        this.game = game;

        createDisplay();
    }

    public void createDisplay(){
        window = new JFrame(title);
        window.setSize(screenWidth, screenHeight);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setLocationRelativeTo(null);

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.white);
        this.setDoubleBuffered(true);
        this.setFocusable(true);

        window.add(this);
        window.pack();
        window.setVisible(true);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        //Tiles
        game.screenManager.draw(g2d);
        //Objects
        for (SuperObject object : game.objects) {
            if (object != null) object.draw(g2d, game);
        }
        //Player
        game.player.draw(g2d);

        g2d.dispose();

    }

    public JFrame getWindow(){ return window; }
}
