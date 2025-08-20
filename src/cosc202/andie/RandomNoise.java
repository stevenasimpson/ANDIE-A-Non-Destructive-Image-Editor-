package cosc202.andie;
import java.awt.image.BufferedImage;
import java.util.Random;


/**
 * <p>
 * ImageOperation to apply a random pixel value filter to an image.
 * </p>
 *
 * @author Liam Walls
 */
public class RandomNoise implements ImageOperation, java.io.Serializable{

    /**
     * <p>
     * Create a new RandomNoise operation.
     * </p>
     */
    public RandomNoise(){}

    /**
     * <p>
     * Apply the random noise filter to an image.
     * </p>
     *
     * @param input The image to be resized.
     * @return The resized image.
     */
    public BufferedImage apply(BufferedImage input){
        BufferedImage output = new BufferedImage(input.getColorModel(), input.copyData(null), input.isAlphaPremultiplied(), null);
        Random rand = new Random();
        for (int x = 0; x < output.getWidth(); x++){
            for (int y = 0; y < output.getHeight(); y++){
                int r = rand.nextInt(256);
                int g = rand.nextInt(256);
                int b = rand.nextInt(256);
                int a = rand.nextInt(256);
                int pixel = (a << 24) | (r << 16) | (g << 8) | b;
                output.setRGB(x, y, pixel);
            }
        }
        return output;
    }
    
}
