package cosc202.andie;

import java.awt.image.*;

/**
 * <p>
 * ImageOperation to edit the supposed Brightness and or Contrast of an image.
 * </p>
 * 
 * <p>
 * This class is used to edit the brightness and contrast of an image.
 * </p>
 *
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * 
 * @author Jarrad Marshall
 * @version 1.0
 */
public class BrightnessContrast implements ImageOperation, java.io.Serializable{

    //percentage changes to brightness and contrast
    private double brightness;
    private double contrast;

    /**
     * <p>
     * Create a new BrightnessContrast operation.
     * </p>
     * @param brightness The percentage change to the brightness of the image
     * @param contrast The percentage change to the contrast of the image
     */
    BrightnessContrast(int brightness, int contrast) {
        this.brightness = (int)brightness;
        this.contrast = (int)contrast;
    }

    /**
     * <p>
     * Apply a BrightnessContrast operation to an image.
     * </p>
     * 
     * <p>
     * This method iterates over each pixel in the input image, extracts the ARGB values, 
     * and applies the brightness and contrast adjustments to each of the RGB components.
     * </p>
     *
     * @param input The input image to which the brightness and contrast adjustments should be applied
     * @return The image after applying the brightness and contrast adjustments
     */
    public BufferedImage apply(BufferedImage input) {
        for (int y = 0; y < input.getHeight(); ++y) {
            for (int x = 0; x < input.getWidth(); ++x) {
                int argb = input.getRGB(x, y);
                int a = (argb & 0xFF000000) >> 24;
                int r = (argb & 0x00FF0000) >> 16;
                int g = (argb & 0x0000FF00) >> 8;
                int b = (argb & 0x000000FF);

                r = updatePixel(r);
                g = updatePixel(g);
                b = updatePixel(b);

                argb = (a << 24) | (r << 16) | (g << 8) | b;
                input.setRGB(x, y, argb);
            }
        }
        return input;
    }


    /**
     * <p>
     * Update the value of a pixel component (red, green, or blue) based on the brightness and contrast settings.
     * </p>
     *
     * @param p The original value of the pixel component
     * @return The updated value of the pixel component after applying the brightness and contrast adjustments
     */
    private int updatePixel(int p) {
        double a = p;
        a = (1+(contrast/100))*(a-127.5)+(127.5*(1+(brightness/100)));
        if(a < 0) {
            a = 0;
        }
        if(a > 255) {
            a = 255;
        }
        return (int)a;
    }
    
}
