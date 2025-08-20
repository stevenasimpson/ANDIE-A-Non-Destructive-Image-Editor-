package cosc202.andie;

import java.awt.image.BufferedImage;



/**
 * <p>
 * ImageOperation to rotate an image 180 degrees.
 * </p>
 * 
 * @author Steven Simpson
 * 
 */
public class Rotate180 implements ImageOperation, java.io.Serializable  {

    /**
     * Create a new Rotate180 operation.
     * 
     */
    Rotate180(){
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

        RotateLeft rL = new RotateLeft();
        BufferedImage rotL90 = rL.apply(input);
        BufferedImage rot180 = rL.apply(rotL90);

        return rot180; 
    }


}