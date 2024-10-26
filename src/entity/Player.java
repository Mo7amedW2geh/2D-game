package entity;

import game.Game;
import game.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity {

    Game game;
    KeyHandler keyHandler;

    public final int screenX, screenY;

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

    public Player(Game game, KeyHandler keyHandler) {
        screenX = (game.screenWidth/2) - game.tileSize;
        screenY = (game.screenHeight/2) - game.tileSize;

        solidArea = new Rectangle(game.scale * 10, game.scale * 23, game.scale * 13, game.scale * 8);

        this.game = game;
        this.keyHandler = keyHandler;

        setDefaultValues();
        loadSpriteSheet();
        extractFrames();
    }

    public void setDefaultValues(){
        worldX = game.tileSize * 19;
        worldY = game.tileSize * 38;
        speed = 4;
        faceDirection = "front";
        moveDirection = "front";
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
        boolean moveUp = keyHandler.upPressed, moveDown = keyHandler.downPressed,
                    moveLeft = keyHandler.leftPressed,moveRight = keyHandler.rightPressed;

        //Check collision
        collisionOn = false;
        game.collisionChecker.checkTile(this);

        if (moveUp || moveDown || moveLeft || moveRight) {
            isIdle = false;

            // Check for movement direction
            if (moveUp && moveRight) {
                moveDirection = "back-right";
                if (!collisionOn){
                    worldY -= diagonalSpeed;
                    worldX += diagonalSpeed;
                }
                faceDirection = faceDirection.equals("right") ? "right" : "back";
            } else if (moveUp && moveLeft) {
                moveDirection = "back-left";
                if (!collisionOn){
                    worldY -= diagonalSpeed;
                    worldX -= diagonalSpeed;
                }
                faceDirection = faceDirection.equals("left") ? "left" : "back";
            } else if (moveDown && moveRight) {
                moveDirection = "front-right";
                if (!collisionOn){
                    worldY += diagonalSpeed;
                    worldX += diagonalSpeed;
                }
                faceDirection = faceDirection.equals("right") ? "right" : "front";
            } else if (moveDown && moveLeft) {
                moveDirection = "front-left";
                if (!collisionOn){
                    worldY += diagonalSpeed;
                    worldX -= diagonalSpeed;
                }
                faceDirection = faceDirection.equals("left") ? "left" : "front";
            }
            else if (moveUp) {
                moveDirection = "back";
                faceDirection = "back";
                if (!collisionOn) worldY -= speed;
            } else if (moveDown) {
                moveDirection = "front";
                faceDirection = "front";
                if (!collisionOn) worldY += speed;
            } else if (moveRight) {
                moveDirection = "right";
                faceDirection = "right";
                if (!collisionOn) worldX += speed;
            } else {
                moveDirection = "left";
                faceDirection = "left";
                if (!collisionOn) worldX -= speed;
            }
        } else {
            isIdle = true;
        }



        //Handle animation
        spriteCounter++;
        if(spriteCounter > game.FPS/6){
            spriteNum = (spriteNum + 1) % 6; // Loop through frames from 0 to 5
            spriteCounter = 0;
        }

    }

    public void draw(Graphics2D g2d){
        BufferedImage image = getCurrentAnimationFrame();

        int drawX = getDrawingXPosition();
        int drawY = getDrawingYPosition();

        g2d.drawImage(image, drawX, drawY, 32*3, 32*3, null);
        g2d.setColor(Color.red);
        g2d.drawRect(drawX + solidArea.x, drawY + solidArea.y, solidArea.width, solidArea.height);

    }

    private int getDrawingXPosition(){
        boolean atWorldLeft = worldX <= screenX;
        boolean atWorldRight = worldX >= game.worldWidth - screenX - game.tileSize*2;
        return (atWorldLeft) ? worldX: (atWorldRight) ? worldX - (game.worldWidth - game.screenWidth) : screenX;
    }

    private int getDrawingYPosition(){
        boolean atWorldTop = worldY <= screenY;
        boolean atWorldBottom = worldY >= game.worldHeight - screenY - game.tileSize*2;
        return (atWorldTop) ? worldY : (atWorldBottom) ? worldY - (game.worldHeight - game.screenHeight) : screenY;
    }

    private BufferedImage getCurrentAnimationFrame() {
        BufferedImage image = null;
        if(isIdle){
            switch (faceDirection) {
                case "front" -> image = frontIdleSprite[spriteNum];
                case "back" -> image = backIdleSprite[spriteNum];
                case "left" -> image = leftIdleSprite[spriteNum];
                case "right" -> image = rightIdleSprite[spriteNum];
            }
        }else {
            switch (faceDirection) {
                case "front" -> image = frontWalkSprite[spriteNum];
                case "back" -> image = backWalkSprite[spriteNum];
                case "left" -> image = leftWalkSprite[spriteNum];
                case "right" -> image = rightWalkSprite[spriteNum];
            }
        }
        return image;
    }

}
