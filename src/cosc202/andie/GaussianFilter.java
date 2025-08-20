package cosc202.andie;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

/**
 * <p>
 * ImageOperation to apply a Gaussian Filter
 * </p>
 * 
 * <p>
 * A Gaussian Filter blurs an image by applying an equation to each pixel in a kernel, getting weaker
 * The further from the centre of the kernel you get.  It is implemented by convolution.
 * </p>
 * 
 * @see java.awt.image.ConvolveOp
 * @author Liam Walls
 * @version 1.0
 */
public class GaussianFilter implements ImageOperation, java.io.Serializable {
    /**
     * The size of the filter to apply. A radius of 1 means a 3x3 filter.
     */
    private int radius;


    /**
     * <p>
     * Constructs a Gaussian Filter
     * </p>
     * 
     * <p>
     * The size of the filter is the radius of the convolution kernel used.
     * the edge of the kernel is made using the formula radius*2 + 1
     * larger filters give a stronger blurring effect
     * </p>
     * 
     * @param radius the radius of the newly constructed Gaussian Filter
     */
    GaussianFilter(int radius){
        this.radius = radius;
    }

    /**
     * <p>
     * Construct a gaussian filter with the default size
     * </p>
     * 
     * <p>
     * By default, a gaussian filter has radius of 1
     * </p>
     */
    GaussianFilter(){
        this(1);
    }

    /**
     * <p>
     * Applies a Gaussian filter to the image.
     * </p>
     * 
     * <p>
     * The Gaussian filter is applied using convolution.
     * The size of the Gaussian kernels is determined by radius
     * </p>
     * 
     * @param input the image to apply the Gaussian filter to
     * @return the blurred image
     */
    public BufferedImage apply(BufferedImage input){
        int size = (2 * radius + 1) * (2 * radius + 1);
        float t = radius / 3.0f;
        float[] array = new float[size];
        int count = 0;
        for(int x = -(radius); x <= radius; x++){
            for(int y = -(radius); y <= radius; y++){
                array[count] = g(Math.abs(x), Math.abs(y), t);
                count++;
            }
        }
        
        // Normalize the kernel
        float total = 0;
        for(int i = 0; i < size; i++){
            total = total + array[i];
        }
        for(int k = 0; k < size; k++){
            array[k] = array[k] / total;
        }
        
        // Create the kernel and convolution operation
        Kernel kernel = new Kernel(2 * radius + 1, 2 * radius + 1, array);
        ConvolveOp convOp = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);
        
        // Apply padding to the input image
        BufferedImage paddedInput = addPadding(input, radius);
        
        // Apply convolution operation
        BufferedImage output = new BufferedImage(input.getColorModel(), input.copyData(null), input.isAlphaPremultiplied(), null);
        convOp.filter(paddedInput, output);
        
        return output;
    }

    /**
     * <p>
     * Adds padding to an image.
     * </p>
     *
     * @author Neil Flores
     * @param input the image to add padding to
     * @param paddingSize the size of the padding to add
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

    /**
     * <p>
     * A method used to return a value after being run through the gaussian equation. 
     * This results in a pixel being more blurred the closer to the center it is.
     * </p>
     * 
     * @param x the x coordinate of the pixel in the kernel
     * @param y the y coordinate of the pixel in the kernel
     * @param t theta, which is equal to radius/3
     * @return the returned value for input into the filter 
     */
    private float g(int x, int y, float t){
        double ans = (1/(2*Math.PI*Math.pow(t,2))  *  Math.pow(Math.exp(1), - ((Math.pow(x,2)+Math.pow(y,2))/(2*Math.pow(t,2)))));
        float f = (float)ans;
        System.out.println();
        return f;
    }

}
