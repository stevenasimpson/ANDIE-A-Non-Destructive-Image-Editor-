package cosc202.andie;

import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.util.Arrays;

/**
 * <p>
 * ImageOperation to apply a Mean (simple blur) filter.
 * </p>
 * 
 * <p>
 * A Mean filter blurs an image by replacing each pixel by the average of the
 * pixels in a surrounding neighbourhood, and can be implemented by a convolution.
 * </p>
 * 
 * <p> 
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 * 
 * @see java.awt.image.ConvolveOp
 * @author Steven Mills
 * @version 1.0
 */
public class MeanFilter implements ImageOperation, java.io.Serializable {
    
    /**
     * The size of filter to apply. A radius of 1 is a 3x3 filter, a radius of 2 a 5x5 filter, and so forth.
     */
    private int radius;

    /**
     * <p>
     * Construct a Mean filter with the given size.
     * </p>
     * 
     * <p>
     * The size of the filter is the 'radius' of the convolution kernel used.
     * A size of 1 is a 3x3 filter, 2 is 5x5, and so on.
     * Larger filters give a stronger blurring effect.
     * </p>
     * 
     * @param radius The radius of the newly constructed MeanFilter
     */
    MeanFilter(int radius) {
        this.radius = radius;    
    }

    /**
     * <p>
     * Construct a Mean filter with the default size.
     * </p>
     * 
     * <p>
     * By default, a Mean filter has radius 1.
     * </p>
     * 
     * @see MeanFilter(int)
     */
    MeanFilter() {
        this(1);
    }

    /**
     * <p>
     * Apply a Mean filter to an image.
     * </p>
     * 
     * <p>
     * As with many filters, the Mean filter is implemented via convolution.
     * The size of the convolution kernel is specified by the radius.
     * Larger radii lead to stronger blurring.
     * </p>
     * 
     * @param input The image to apply the Mean filter to.
     * @param offset The offset to apply to the resulting image.
     *
     * @return The resulting (blurred) image.
     */
    public BufferedImage apply(BufferedImage input, int offset) {
        int size = (2*radius+1) * (2*radius+1);
        float [] array = new float[size];
        Arrays.fill(array, 1.0f/size);

        Kernel kernel = new Kernel(2*radius+1, 2*radius+1, array);
        ConvolveOp convOp = new ConvolveOp(kernel);
        BufferedImage output = new BufferedImage(input.getColorModel(), input.copyData(null), input.isAlphaPremultiplied(), null);
        convOp.filter(input, output);

           // Apply the offset to adjust for negative values if necessary
           applyOffsetAndNormalize(output, offset);


        return output;
    }

    /**
     * <p>
     * Apply an offset to an image and normalize the result.
     * </p>
     *
     * @param image The image to apply the offset and normalize to.
     * @param offset The offset to apply to the image.
     */
    private void applyOffsetAndNormalize(BufferedImage image, int offset) {
        int width = image.getWidth();
        int height = image.getHeight();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb = image.getRGB(x, y);
                int red = ((rgb >> 16) & 0xFF) + offset;
                int green = ((rgb >> 8) & 0xFF) + offset;
                int blue = (rgb & 0xFF) + offset;

                // Normalize to ensure values are within [0, 255]
                red = normalizeColour(red);
                green = normalizeColour(green);
                blue = normalizeColour(blue);

                image.setRGB(x, y, (red << 16) | (green << 8) | blue);
            }
        }
    }
    /**
     * <p>
     * Normalize a colour value to ensure it is within the range [0, 255].
     * </p>
     * @param value The colour value to normalize.
     *
     * @return The normalized colour value.
     */
    private int normalizeColour(int value) {
        return Math.min(255, Math.max(0, value));
    }

    /**
     * <p>
     *  Reapplying the filter with an offset.
     * </p>
     *
     * @param input The image to apply the Mean filter to.
     */
    public BufferedImage apply(BufferedImage input) {
        return apply(input, 128);  // Assume an offset that centers around 128 for demonstration
    }

 
}
