package cosc202.andie;

import java.awt.image.BufferedImage;



/**
 * <p>
 * ImageOperation to rotate an image left 90 degrees.
 * </p>
 * 
 * @author Steven Simpson
 * 
 */
public class RotateLeft implements ImageOperation, java.io.Serializable  {

    /**
     * <p>
     * Create a new RotateLeft operation.
     * </p>
     */
    RotateLeft(){
    }

    /**
     * <p>
     * Apply the rotate operation to an image.
     * </p>
     * 
     * @param input The image to be rotated.
     * @return The rotated image.
     */
    public BufferedImage apply(BufferedImage input){

        BufferedImage newImage = new BufferedImage(input.getHeight(), input.getWidth(), BufferedImage.TYPE_INT_ARGB);
        
        for(int x = 0; x < input.getWidth(); x++){
            for(int y = 0; y < input.getHeight(); y++){
                int rgb = input.getRGB(x, y);
                newImage.setRGB(y, input.getWidth()-1-x, rgb);
            }
        }
        return newImage; 

    }


}