package cosc202.andie;

import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;


/** 
 * <p>
 * ImageOperation to apply sharpen filter
 * </p>
 * 
 * <p>
 * The Sharpen filter applies a filter to each individual pixel that increases
 * Tts strength compared to its neighbours, making the pixels stand out more.
 * It is implemented by a convolution.
 * </p>
 * 
 * @author Liam Walls
 * @version 1.0
 *  
*/
public class SharpenFilter implements ImageOperation, java.io.Serializable {
    

    /**
     * <p>
     * Construct a Sharpen Filter
     * </p>
     */
    SharpenFilter(){

    }
    /**
     * <p>
     * Apply a Sharpen Filter to an image
     * </p>
     * 
     * <p>
     * The Sharpen Filter is implemented using convolution.
     * The kernel is always 3x3 and the floats in the kernel are always the same formula
     * </p>
     * 
     * @param input The image to apply the sharpen filter
     * @return the sharpened image
     */
    public BufferedImage apply(BufferedImage input){
        // Define the sharpening kernel
        float[] arr = new float[]{0,-1/2.0f,0,-1/2.0f,3.0f,-1/2.0f,0,-1/2.0f,0};
        Kernel kernel = new Kernel(3, 3, arr);
        
        // Apply padding to the input image
        BufferedImage paddedInput = addPadding(input, kernel.getWidth() / 2);
        
        // Apply convolution operation
        ConvolveOp convOp = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);
        BufferedImage output = new BufferedImage(input.getColorModel(), input.copyData(null), input.isAlphaPremultiplied(), null);
        convOp.filter(paddedInput, output);
        
        return output;
    }

    /**
     * <p>
     * Add padding to an image
     * </p>
     *
     * <p>
     * This method adds padding to an image by copying the original image into a larger image
     * and filling the padding with the edge values of the original image.
     * </p>
     *
     * @param input The image to add padding to
     * @param paddingSize The size of the padding to add
     * @return The padded image
     */
    private BufferedImage addPadding(BufferedImage input, int paddingSize) {
        int width = input.getWidth();
        int height = input.getHeight();
        int paddedWidth = width + 2 * paddingSize;
        int paddedHeight = height + 2 * paddingSize;
        
        BufferedImage paddedImage = new BufferedImage(paddedWidth, paddedHeight, input.getType());
        
        // Copy original image into padded image
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                paddedImage.setRGB(x + paddingSize, y + paddingSize, input.getRGB(x, y));
            }
        }
        
        // Fill top and bottom padding
        for (int y = 0; y < paddingSize; y++) {
            for (int x = 0; x < paddedWidth; x++) {
                paddedImage.setRGB(x, y, input.getRGB(0, 0)); // Assuming top-left pixel value for padding
                paddedImage.setRGB(x, paddedHeight - 1 - y, input.getRGB(0, height - 1)); // Assuming bottom-left pixel value for padding
            }
        }
        
        // Fill left and right padding
        for (int x = 0; x < paddingSize; x++) {
            for (int y = paddingSize; y < paddedHeight - paddingSize; y++) {
                paddedImage.setRGB(x, y, input.getRGB(0, y - paddingSize)); // Assuming left-most pixel value for padding
                paddedImage.setRGB(paddedWidth - 1 - x, y, input.getRGB(width - 1, y - paddingSize)); // Assuming right-most pixel value for padding
            }
        }
        
        return paddedImage;
    }
}