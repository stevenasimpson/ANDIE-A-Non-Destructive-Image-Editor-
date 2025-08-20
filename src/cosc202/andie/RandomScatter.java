package cosc202.andie;

import java.awt.image.*;

/**
 * <p>
 * This class implements the ImageOperation interface and provides a method to apply a random scatter operation to an image.
 * </p>
 */
public class RandomScatter implements ImageOperation, java.io.Serializable{

    /**
     * The radius within which the scatter operation should be applied.
     */
    private int rad;

    /**
     * <p>
     * Constructs a new RandomScatter with the specified radius.
     * </p>
     *
     * @param rad The radius within which the scatter operation should be applied
     */
    RandomScatter(int rad){
        this.rad = rad;
    }

    /**
     * <p>
     * Picks a random target within the specified radius.
     * </p>
     *
     * @return A random target within the specified radius
     */
    private int pickTarget(){
        return rad - (int) Math.round(Math.random()*rad*2);
    }

    /**
     * <p>
     * Applies the random scatter operation to an image.
     * </p>
     *
     * <p>
     * This method iterates over each pixel in the input image and applies the scatter operation to it.
     * </p>
     *
     * @param input The input image to which the scatter operation should be applied
     * @return The image after applying the scatter operation
     */
    public BufferedImage apply(BufferedImage input) {
        BufferedImage output = input;
        for (int y = 0; y < input.getHeight(); ++y) {
            for(int x = 0; x < input.getWidth(); ++x) {
                int tarX;
                int tarY;

                do{
                    tarX = x + pickTarget();
                } while(tarX < 0 || tarX >= input.getWidth());

                do{
                    tarY = y + pickTarget();
                } while(tarY < 0 || tarY >= input.getHeight());

                output.setRGB(x, y, input.getRGB(tarX, tarY));
            }
        }
        return output;
    }
}
