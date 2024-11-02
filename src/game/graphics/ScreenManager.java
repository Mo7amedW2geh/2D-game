package game.graphics;

import game.core.Game;
import game.core.World;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class ScreenManager {

    Game game;

    public ScreenManager(Game game) {
        Assets.initialize();
        this.game = game;
    }

    public void draw(Graphics2D g2d){

    }
}
