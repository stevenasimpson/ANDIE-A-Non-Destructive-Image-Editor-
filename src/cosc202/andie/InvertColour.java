package cosc202.andie;

import java.awt.image.*;

/**
 * <p>
 * ImageOperation to invert an image colour.
 * </p>
 * 
 * 
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 * 
 * @author Jarrad Marshall
 * @version 1.0
 */
public class InvertColour implements ImageOperation, java.io.Serializable {

    /**
     * <p>
     * Create a new InvertColour operation.
     * </p>
     */
    InvertColour() {

    }

    /**
     * <p>
     * Inverts the colours of an image.
     * </p>
     *
     * <p>
     * This method iterates over each pixel in the input image, extracts the ARGB values, 
     * inverts the RGB components, and sets the new ARGB value back to the image.
     * </p>
     *
     * @param input The input image whose colours should be inverted
     * @return The image after inverting the colours
     */
    public BufferedImage apply(BufferedImage input) {
  
        for (int y = 0; y < input.getHeight(); ++y) {
            for (int x = 0; x < input.getWidth(); ++x) {
                // The ARGB value of the pixel at (x, y)
                int argb = input.getRGB(x, y);
                // The alpha component of the pixel
                int a = (argb & 0xFF000000) >> 24;
                // The red component of the pixel
                int r = (argb & 0x00FF0000) >> 16;
                // The green component of the pixel
                int g = (argb & 0x0000FF00) >> 8;
                // The blue component of the pixel
                int b = (argb & 0x000000FF);

                r = 255-r;
                g = 255-g;
                b = 255-b;

                argb = (a << 24) | (r << 16) | (g << 8) | b;
                input.setRGB(x, y, argb);
            }
        }
        
        return input;
    }
    
}
