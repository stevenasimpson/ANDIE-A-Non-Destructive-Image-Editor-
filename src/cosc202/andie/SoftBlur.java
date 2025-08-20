package cosc202.andie;

import java.awt.image.*;

/**
 * <p>
 * ImageOperation to add a Soft Blur to an image.
 * </p>
 * 
 * @author Steven Mills
 * 
 */
public class SoftBlur implements ImageOperation, java.io.Serializable {

    /**
     * <p>
     * Construct a SoftBlur Filter
     * </p>
     */
    SoftBlur(){
        // Any construction code goes here
    }

    /**
     * <p>
     * Apply a SoftBlur Filter to an image
     * </p>
     * 
     * <p>
     * The SoftBlur Filter is implemented using convolution.
     * </p>
     * 
     * @param input The image to apply the softblur filter
     * @return the blurred image
     */
    public BufferedImage apply (BufferedImage input) { 
        // The values for the kernel as a 9-element array 
        float [] array = { 0 , 1/8.0f, 0 , 
                            1/8.0f, 1/2.0f, 1/8.0f, 
                            0 ,1/8.0f, 0 }; 
        // Make a 3x3 filter from the array 
        Kernel kernel = new Kernel(3, 3, array); 
        // Apply this as a convolution- same code as in MeanFilter 
        ConvolveOp convOp = new ConvolveOp(kernel);

        BufferedImage output = new BufferedImage(input.getColorModel(), 
                                    input.copyData(null), 
                                    input.isAlphaPremultiplied(), null);
         
        convOp.filter(input, output); 
        // And we're done 
        return output; 
    
        }

}
