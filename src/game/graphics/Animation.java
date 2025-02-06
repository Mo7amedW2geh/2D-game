package game.graphics;

import game.entities.Entity;
import game.utils.ImageUtility;

import java.awt.image.BufferedImage;

public class Animation {
    private int spriteCounter = 0;
    public int spriteNum = 0;
    private final int framesNum, duration;

    private final BufferedImage[][] animationSprite;

    public Animation(int frames, int duration, int frameWidth, int frameHeight, int xOffset, int yOffset, String path){
        BufferedImage sheet = ImageUtility.loadImage(path);
        animationSprite = new BufferedImage[4][frames];
        this.duration = duration;
        framesNum = frames;

        for (int i = 0; i < 4; i++) {
            for(int j = 0; j < frames; j++) {
                assert sheet != null;
                animationSprite[i][j] = sheet.getSubimage(xOffset + frameWidth * j, yOffset + frameHeight * i, frameWidth, frameHeight);
                animationSprite[i][j] = ImageUtility.scaleImage(animationSprite[i][j], 32*3, 32*3);
            }
        }
    }

    public BufferedImage getCurrentAnimationFrame(Entity entity){
        BufferedImage image = null;

        switch (entity.direction) {
            case Entity.Direction.BACK -> image = animationSprite[0][spriteNum];
            case Entity.Direction.FRONT -> image = animationSprite[1][spriteNum];
            case Entity.Direction.RIGHT -> image = animationSprite[2][spriteNum];
            case Entity.Direction.LEFT -> image = animationSprite[3][spriteNum];
        }

        spriteCounter++;
        if(spriteCounter > duration){
            spriteNum = (spriteNum + 1) % framesNum; // Loop through frames from 0 to 5
            spriteCounter = 0;
        }

        return image;
    }
}
