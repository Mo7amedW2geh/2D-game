package game.UI;

import game.core.Game;
import game.graphics.Screen;
import game.objects.items.Key;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import static game.core.Game.GameState.*;

public class UI {

    Game game;
    Graphics2D g2d;
    Font arialFont_20;
    Font arialFont_80;
    BufferedImage keyImage;

    Boolean displayMessage = false;
    String message = "";
    int messageCounter = 0;
    Color messageColor;

    public UI(Game game){
        this.game = game;
        Key key = new Key();
        keyImage = key.image;

        arialFont_20 = new Font("Arial", Font.PLAIN, 20);
        arialFont_80 = new Font("Arial", Font.BOLD, 80);
    }

    public void displayMessage(String text, Color color){
        message = text;
        messageColor = color;
        displayMessage = true;
    }

    public void draw(Graphics2D g2d){

        this.g2d = g2d;

        g2d.setFont(arialFont_20);
        g2d.setColor(Color.white);

        //Play Screen
        if(game.gameState == PLAY) {
            drawPlayScreen();
        }
        //Pause Screen
        else if(game.gameState == PAUSE){
            drawPauseScreen();
        }
    }

    private void drawPlayScreen(){

        g2d.drawImage(keyImage, 30, 10, Screen.tileSize, Screen.tileSize, null);
        g2d.drawString(String.valueOf(game.player.hasKey), 70, 52);

        //Message
        if (displayMessage) {
            g2d.setColor(messageColor);
            g2d.setFont(g2d.getFont().deriveFont(30f));
            g2d.drawString(message, textXCenter(message), Screen.screenHeight / 2 - 200);

            messageCounter++;
            if (messageCounter > 100) {
                displayMessage = false;
                messageCounter = 0;
            }
        }

    }

    private void drawPauseScreen(){

        String text = "Paused";

        g2d.setFont(g2d.getFont().deriveFont(Font.BOLD, 80f));

        g2d.drawString(text, textXCenter(text), Screen.screenHeight / 2);

    }

    private int textXCenter(String text){
        return (Screen.screenWidth / 2) - (((int) g2d.getFontMetrics().getStringBounds(text, g2d).getWidth()) / 2);
    }
}
