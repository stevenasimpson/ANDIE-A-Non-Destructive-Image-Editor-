package cosc202.andie;

import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

/**
 * <p>
 * ImageOperation to apply a Mean (simple blur) filter.
 * </p>
 * 
 * <p>
 * A Emboss filter simulates the effect of light and shadow to create a three-dimensional appearance in an image.
 * It applies convolution with a predefined kernel that calculates the difference in intensity between neighboring pixels.
 * The filter can create the illusion of raising or pressing the image into a surface.
 * </p>
 * 
 * <p> 
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 * 
 * @see java.awt.image.ConvolveOp
 * @author Neil Flores
 * @version 1.0
 */
public class EmbossFilter implements ImageOperation, java.io.Serializable {

    private final float[] kernel;

    /**
     * <p>
     * Create a new EmbossFilter operation.
     * </p>
     *
     * @param kernel The array of float values in the kernel
     */
    public EmbossFilter(float[] kernel) {
        this.kernel = kernel;
    }

    /**
     * <p>
     * Apply emboss filter to an image.
     * </p>
     * 
     *
     * @param input The image to be embossed
     * @return The resulting embossed image.
     */
    @Override
    public BufferedImage apply(BufferedImage input) {
        Kernel embossKernel = new Kernel(3, 3, kernel);
        ConvolveOp convolveOp = new ConvolveOp(embossKernel);
        BufferedImage output = new BufferedImage(input.getWidth(), input.getHeight(), input.getType());
        convolveOp.filter(input, output);
        return output;
    }

    /**
     * <p>
     * Create an emboss filter to apply to an image.
     * </p>
     * 
     * @param kernel The array of float values in the kernel
     * @return A new EmbossFilter.
     */
    public static EmbossFilter createEmbossFilter(float[] kernel) {
        return new EmbossFilter(kernel);
    }
}
