package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity {

    GamePanel gamePanel;
    KeyHandler keyHandler;

    public final int screenX;
    public final int screenY;

    public BufferedImage frontWalkSpriteSheet, backWalkSpriteSheet, leftWalkSpriteSheet, rightWalkSpriteSheet;
    public BufferedImage frontIdleSpriteSheet, backIdleSpriteSheet, leftIdleSpriteSheet, rightIdleSpriteSheet;

    public BufferedImage[] frontWalkSprite = new BufferedImage[6];
    public BufferedImage[] backWalkSprite = new BufferedImage[6];
    public BufferedImage[] leftWalkSprite = new BufferedImage[6];
    public BufferedImage[] rightWalkSprite = new BufferedImage[6];

    public BufferedImage[] frontIdleSprite = new BufferedImage[12];
    public BufferedImage[] backIdleSprite = new BufferedImage[12];
    public BufferedImage[] leftIdleSprite = new BufferedImage[12];
    public BufferedImage[] rightIdleSprite = new BufferedImage[12];

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        screenX = (gamePanel.screenWidth/2) - gamePanel.tileSize;
        screenY = (gamePanel.screenHeight/2) - gamePanel.tileSize;

        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;

        setDefaultValues();
        loadSpriteSheet();
        extractFrames();
    }

    public void setDefaultValues(){
        worldX = gamePanel.tileSize * 19;
        worldY = gamePanel.tileSize * 38;
        speed = 4;
        direction = "front";
        isIdle = true;
    }

    public void loadSpriteSheet() {

        try {
            frontWalkSpriteSheet = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/Front animations/spr_player_front_walk.png")));
            backWalkSpriteSheet = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/Back animations/spr_player_back_walk.png")));
            leftWalkSpriteSheet = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/Side animations/spr_player_left_walk.png")));
            rightWalkSpriteSheet = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/Side animations/spr_player_right_walk.png")));

            frontIdleSpriteSheet = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/Front animations/spr_player_front_idle.png")));
            backIdleSpriteSheet = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/Back animations/spr_player_back_idle.png")));
            leftIdleSpriteSheet = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/Side animations/spr_player_left_idle.png")));
            rightIdleSpriteSheet = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/Side animations/spr_player_right_idle.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void extractFrames() {
        int frameWidth =32, frameHeight =32, xOffset = 16, yOffset=16;

        for (int i = 0; i < 6; i++) {
            extractFrames(frameWidth, frameHeight, xOffset, yOffset, i, frontWalkSprite, frontWalkSpriteSheet, backWalkSprite, backWalkSpriteSheet, leftWalkSprite, leftWalkSpriteSheet, rightWalkSprite, rightWalkSpriteSheet);
        }
        for (int i = 0; i < 12; i++) {
            extractFrames(frameWidth, frameHeight, xOffset, yOffset, i, frontIdleSprite, frontIdleSpriteSheet, backIdleSprite, backIdleSpriteSheet, leftIdleSprite, leftIdleSpriteSheet, rightIdleSprite, rightIdleSpriteSheet);
        }
    }

    private void extractFrames(int frameWidth, int frameHeight, int xOffset, int yOffset, int i, BufferedImage[] frontSprite, BufferedImage frontSpriteSheet, BufferedImage[] backSprite, BufferedImage backSpriteSheet, BufferedImage[] leftSprite, BufferedImage leftSpriteSheet, BufferedImage[] rightSprite, BufferedImage rightSpriteSheet) {
        frontSprite[i] = frontSpriteSheet.getSubimage(xOffset + 64 * i, yOffset, frameWidth, frameHeight);
        backSprite[i] = backSpriteSheet.getSubimage(xOffset + 64 * i, yOffset, frameWidth, frameHeight);
        leftSprite[i] = leftSpriteSheet.getSubimage(xOffset + 64 * i, yOffset, frameWidth, frameHeight);
        rightSprite[i] = rightSpriteSheet.getSubimage(xOffset + 64 * i, yOffset, frameWidth, frameHeight);
    }

    public void update(){

        int diagonalSpeed = 3; // Normalize speed for diagonal movement

        if (keyHandler.upPressed || keyHandler.downPressed || keyHandler.leftPressed || keyHandler.rightPressed) {
            isIdle = false;

            // Check for diagonal movement
            if (keyHandler.upPressed && keyHandler.leftPressed) {
                direction = "back"; // Add a new direction for diagonal movement
                worldY -= diagonalSpeed;
                worldX -= diagonalSpeed;
            } else if (keyHandler.upPressed && keyHandler.rightPressed) {
                direction = "back"; // Add a new direction for diagonal movement
                worldY -= diagonalSpeed;
                worldX += diagonalSpeed;
            } else if (keyHandler.downPressed && keyHandler.leftPressed) {
                direction = "front"; // Add a new direction for diagonal movement
                worldY += diagonalSpeed;
                worldX -= diagonalSpeed;
            } else if (keyHandler.downPressed && keyHandler.rightPressed) {
                direction = "front"; // Add a new direction for diagonal movement
                worldY += diagonalSpeed;
                worldX += diagonalSpeed;
            } else {
                if (keyHandler.upPressed) {
                    direction = "back";
                    worldY -= speed;
                }
                if (keyHandler.downPressed) {
                    direction = "front";
                    worldY += speed;
                }
                if (keyHandler.leftPressed) {
                    direction = "left";
                    worldX -= speed;
                }
                if (keyHandler.rightPressed) {
                    direction = "right";
                    worldX += speed;
                }
            }
        } else {
            isIdle = true;
        }

        //Handle animation
        spriteCounter++;
        if(spriteCounter > gamePanel.FPS/6){
            spriteNum = (spriteNum + 1) % 6; // Loop through frames from 0 to 5
            spriteCounter = 0;
        }

    }

    public void draw(Graphics2D g2d){

        BufferedImage image = null;
        if(isIdle){
            switch (direction) {
                case "front" -> image = frontIdleSprite[spriteNum];
                case "back" -> image = backIdleSprite[spriteNum];
                case "left" -> image = leftIdleSprite[spriteNum];
                case "right" -> image = rightIdleSprite[spriteNum];
            };
        }else {
            switch (direction) {
                case "front" -> image = frontWalkSprite[spriteNum];
                case "back" -> image = backWalkSprite[spriteNum];
                case "left" -> image = leftWalkSprite[spriteNum];
                case "right" -> image = rightWalkSprite[spriteNum];
            };
        }


        g2d.drawImage(image, screenX, screenY, 32*3, 32*3, null);

    }

}
