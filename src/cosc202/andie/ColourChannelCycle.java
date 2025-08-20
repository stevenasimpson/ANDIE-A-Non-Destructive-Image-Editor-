package cosc202.andie;

import java.awt.image.*;

/**
 * <p>
 * ImageOperation to cycle the RGB values of an image.
 * </p>
 * 
 * <p>
 * Cycles the RGB values of an image so into a GRB or GBR etc format
 * Essentially exists as 5 separate operations to change the order of RGB into any other possible order.
 * </p>
 *  
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 * 
 * @author Jarrad Marshall
 * @version 1.0
 */

/**
 * <p>
 * This class represents a ColourChannelCycle operation.
 * </p>
 *
 * <p>
 * The operation to be performed is determined by the `operation` instance variable.
 * </p>
 */
public class ColourChannelCycle implements ImageOperation, java.io.Serializable {

    /** 
     * The chosen operation to perform on the RGB values out of 5
     * <p>
     * 1 shifts RGB to GBR (shift left)
     * 2 shifts RGB to BRG (shift right)
     * 3 shifts RGB to GRB (swap left)
     * 4 shifts RGB to RBG (swap right)
     * 5 shifts RGB to BGR (reverse)
     * </p>
     */
    private int operation;

    /**
     * <p>
     * Create a new ColourChannelCycle operation.
     * </p>
     * @param operation The chosen operation to perform out of 5
     */
    ColourChannelCycle(int operation) {
        this.operation = operation;
    }

    /**
     * <p>
     * Constructs a new ColourChannelCycle operation.
     * </p>
     *
     * <p>
     * The default operation is 1.
     * </p>
     */
    ColourChannelCycle() {
        this.operation = 1;
    }

    /**
     * <p>
     * Performs the chosen operation on the RGB values of an image.
     * </p>
     * 
    *
     * @param input The image on which the operation should be performed
     * @return The image after performing the operation
     */
    public BufferedImage apply(BufferedImage input) {
  
        for (int y = 0; y < input.getHeight(); ++y) {
            for (int x = 0; x < input.getWidth(); ++x) {
                int argb = input.getRGB(x, y);
                int a = (argb & 0xFF000000) >> 24;
                int r = (argb & 0x00FF0000) >> 16;
                int g = (argb & 0x0000FF00) >> 8;
                int b = (argb & 0x000000FF);

                if(operation==1) argb = (a << 24) | (b << 16) | (r << 8) | g; 
                if(operation==2) argb = (a << 24) | (g << 16) | (b << 8) | r;
                if(operation==3) argb = (a << 24) | (g << 16) | (r << 8) | b;
                if(operation==4) argb = (a << 24) | (r << 16) | (b << 8) | g;
                if(operation==5) argb = (a << 24) | (b << 16) | (g << 8) | r;

                input.setRGB(x, y, argb);
            }
        }
        
        return input;
    }
    
}
