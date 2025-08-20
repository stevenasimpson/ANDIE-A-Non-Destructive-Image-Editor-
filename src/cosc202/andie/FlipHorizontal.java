package cosc202.andie;

import java.awt.image.BufferedImage;



/**
 * <p>
 * ImageOperation to flip an image along the vertical axis.
 * </p>
 * 
 * @author Steven Simpson
 * 
 */
public class FlipHorizontal implements ImageOperation, java.io.Serializable  {

    /**
     * <p>
     * Create a new FlipHorizontal operation.
     * </p>
     * 
     */
    FlipHorizontal(){
    
    }

    /**
     * <p>
     * Apply the flip operation to an image.
     * </p>
     * 
     * @param input The image to be flipped.
     * @return The flipped image.
     */
    public BufferedImage apply(BufferedImage input){

        BufferedImage outputImage = new BufferedImage(input.getWidth(), input.getHeight(), BufferedImage.TYPE_INT_ARGB);
        
        for(int x = 0; x < input.getWidth(); x++){
            for(int y = 0; y < input.getHeight(); y++){
                int rgb = input.getRGB(x, y);
                outputImage.setRGB(input.getWidth()-1-x, y, rgb);
            }
        }
        return outputImage;

    }
}