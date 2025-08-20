package cosc202.andie;

import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

/**
 * <p>
 * ImageOperation to apply a Sobel filter. (both the horizontal and vertical kernels separately to the input image)
 * </p>
 * 
 * <p>
 * A Sobel filter  convolution with two 3x3 kernels, one detecting horizontal changes
 *  and the other detecting vertical changes in intensity.
 * By combining the results of these two convolutions, the Sobel filter highlights edges in an image.
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
public class SobelFilter implements ImageOperation, java.io.Serializable {

    private final float[] horizontalKernel = {-1, 0, 1, -2, 0, 2, -1, 0, 1};
    private final float[] verticalKernel = {-1, -2, -1, 0, 0, 0, 1, 2, 1};
    private final boolean isHorizontal;

    /**
     * <p>
     * Constructs a SobelFilter with the specified orientation.
     * </p>
     *
     * @param isHorizontal true if the horizontal kernel should be applied, false if the vertical kernel should be applied.
     */
    public SobelFilter(boolean isHorizontal) {
        this.isHorizontal = isHorizontal;
    }

    /**
     * <p>
     * Applies the Sobel filter to the input image.
     * </p>
     *
     * @param input the image to apply the filter to.
     * @return the image with the Sobel filter applied.
     */
    @Override
    public BufferedImage apply(BufferedImage input) {
        float[] kernel = isHorizontal ? horizontalKernel : verticalKernel;
        BufferedImage output = applyKernel(input, kernel);
        return output;
    }

    /**
     * <p>
     * Applies the specified kernel to the input image.
     * </p>
     *
     * @param input the image to apply the kernel to.
     * @param kernel the kernel to apply to the image.
     * @return the image with the kernel applied.
     */
    private BufferedImage applyKernel(BufferedImage input, float[] kernel) {
        Kernel sobelKernel = new Kernel(3, 3, kernel);
        ConvolveOp convolveOp = new ConvolveOp(sobelKernel);
        BufferedImage output = new BufferedImage(input.getWidth(), input.getHeight(), input.getType());
        convolveOp.filter(input, output);
        return output;
    }
}