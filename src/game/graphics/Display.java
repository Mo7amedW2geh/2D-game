package game.graphics;

import game.core.Game;
import game.objects.Object;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Display extends JPanel {
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
        JFrame window = new JFrame(title);
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

        //long drawStart = System.nanoTime();

        //Tiles
        game.world.draw(g2d);
        //Objects
        for (Object object : game.objects) {
            if (object != null) object.draw(g2d, game);
        }
        //Player
        game.player.draw(g2d);
        game.ui.draw(g2d);

        //long drawEnd = System.nanoTime();
        //long drawTime = drawEnd - drawStart;
        //System.out.println("Draw Time: " + drawTime);

        g2d.dispose();

    }
}
