package game.entities;

import game.core.Camera;
import game.graphics.Screen;
import game.core.Game;
import game.input.KeyHandler;

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

    public Camera camera = new Camera(this);

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
        int scale = Screen.scale;

        solidArea = new Rectangle(scale * 10, scale * 24, scale * 13, scale * 6);

        this.game = game;
        this.keyHandler = keyHandler;

        setDefaultValues();
        loadSpriteSheet();
        extractFrames();
    }

    public void setDefaultValues(){
        worldX = Screen.tileSize * 19;
        worldY = Screen.tileSize * 38;
        speed = 4;
        direction = Direction.FRONT;
        moveDirection = MoveDirection.FRONT;
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

        float diagonalSpeed = (float) (speed / Math.sqrt(2)); // Normalize speed for diagonal movement
        boolean moveUp = keyHandler.upPressed, moveDown = keyHandler.downPressed,
                    moveLeft = keyHandler.leftPressed,moveRight = keyHandler.rightPressed;

        //Check collision
        collisionOn = false;

        if (moveUp || moveDown || moveLeft || moveRight) {
            isIdle = false;

            // Check for movement direction
            if (moveUp && moveRight) {
                moveDirection = MoveDirection.BACK_RIGHT;
                if (game.collisionChecker.canMove(this, Direction.BACK, diagonalSpeed)){
                    worldY -= diagonalSpeed;
                }
                if (game.collisionChecker.canMove(this, Direction.RIGHT, diagonalSpeed)){
                    worldX += diagonalSpeed;
                }
                direction = direction.equals(Direction.RIGHT) ? Direction.RIGHT : Direction.BACK;
            } else if (moveUp && moveLeft) {
                moveDirection = MoveDirection.BACK_LEFT;
                if (game.collisionChecker.canMove(this, Direction.BACK, diagonalSpeed)){
                    worldY -= diagonalSpeed;
                }
                if (game.collisionChecker.canMove(this, Direction.LEFT, diagonalSpeed)){
                    worldX -= diagonalSpeed;
                }
                direction = direction.equals(Direction.LEFT) ? Direction.LEFT : Direction.BACK;
            } else if (moveDown && moveRight) {
                moveDirection = MoveDirection.FRONT_RIGHT;
                if (game.collisionChecker.canMove(this, Direction.FRONT, diagonalSpeed)){
                    worldY += diagonalSpeed;
                }
                if (game.collisionChecker.canMove(this, Direction.RIGHT, diagonalSpeed)){
                    worldX += diagonalSpeed;
                }
                direction = direction.equals(Direction.RIGHT) ? Direction.RIGHT : Direction.FRONT;
            } else if (moveDown && moveLeft) {
                moveDirection = MoveDirection.FRONT_LEFT;
                if (game.collisionChecker.canMove(this, Direction.FRONT, diagonalSpeed)){
                    worldY += diagonalSpeed;
                }
                if (game.collisionChecker.canMove(this, Direction.LEFT, diagonalSpeed)){
                    worldX -= diagonalSpeed;
                }
                direction = direction.equals(Direction.LEFT) ? Direction.LEFT : Direction.FRONT;
            }
            else if (moveUp) {
                moveDirection = MoveDirection.BACK;
                direction = Direction.BACK;
                if (game.collisionChecker.canMove(this, Direction.BACK, speed)) worldY -= speed;
            } else if (moveDown) {
                moveDirection = MoveDirection.FRONT;
                direction = Direction.FRONT;
                if (game.collisionChecker.canMove(this, Direction.FRONT, speed)) worldY += speed;
            } else if (moveRight) {
                moveDirection = MoveDirection.RIGHT;
                direction = Direction.RIGHT;
                if (game.collisionChecker.canMove(this, Direction.RIGHT, speed)) worldX += speed;
            } else {
                moveDirection = MoveDirection.LEFT;
                direction = Direction.LEFT;
                if (game.collisionChecker.canMove(this, Direction.LEFT, speed)) worldX -= speed;
            }
        } else {
            isIdle = true;
        }

        //Handle animation
        spriteCounter++;
        if(spriteCounter > Screen.FPS/6){
            spriteNum = (spriteNum + 1) % 6; // Loop through frames from 0 to 5
            spriteCounter = 0;
        }

    }

    public void draw(Graphics2D g2d){
        BufferedImage image = getCurrentAnimationFrame();
        int drawX = Math.round(camera.getDrawingXPosition());
        int drawY = Math.round(camera.getDrawingYPosition());

        if(Screen.showSolidArea) {
            g2d.setColor(Color.red);
            g2d.drawRect(drawX + solidArea.x, drawY + solidArea.y, solidArea.width, solidArea.height);
        }
        g2d.drawImage(image, drawX, drawY, 32*3, 32*3, null);
    }

    private BufferedImage getCurrentAnimationFrame() {
        BufferedImage image = null;
        if(isIdle){
            switch (direction) {
                case Direction.FRONT -> image = frontIdleSprite[spriteNum];
                case Direction.BACK -> image = backIdleSprite[spriteNum];
                case Direction.RIGHT -> image = rightIdleSprite[spriteNum];
                case Direction.LEFT -> image = leftIdleSprite[spriteNum];
            }
        }else {
            switch (direction) {
                case Direction.FRONT -> image = frontWalkSprite[spriteNum];
                case Direction.BACK -> image = backWalkSprite[spriteNum];
                case Direction.RIGHT -> image = rightWalkSprite[spriteNum];
                case Direction.LEFT -> image = leftWalkSprite[spriteNum];
            }
        }
        return image;
    }

}
