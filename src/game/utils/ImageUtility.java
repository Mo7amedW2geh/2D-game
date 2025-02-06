package game.utils;

import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Objects;

public class ImageUtility {

    public static BufferedImage loadImage(String path) {
        try{
            return ImageIO.read(Objects.requireNonNull(ImageUtility.class.getResourceAsStream(path)));
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static BufferedImage scaleImage(BufferedImage original, int width, int height) {
        BufferedImage scaledImage = new BufferedImage(width, height, original.getType());
        Graphics2D g2d = scaledImage.createGraphics();
        g2d.drawImage(original, 0, 0, width, height, null);
        g2d.dispose();

        return scaledImage;
    }
}
