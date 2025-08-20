package cosc202.andie;

import java.awt.Image;
import java.awt.image.BufferedImage;



/** 
* <p>
 * ImageOperation to resize an image based on a scaling percentage supplied.
 * </p>
 * 
 * @author Steven Simpson
 * 
 */
public class ResizeImage implements ImageOperation, java.io.Serializable  {

    /** Value used for scaling the image.*/
    private int scalePercentage;
    
    /**
     * <p>
     * Create a new ResizeImage operation.
     * </p>
     * 
     * @param scalePercentage The percentage used for scaling the image.
     */
    ResizeImage(int scalePercentage){
        this.scalePercentage = scalePercentage;
    }

    /**
     * <p>
     * Apply the resizing to an image.
     * </p>
     * 
     * @param input The image to be resized.
     * @return The resized image.
     */
    public BufferedImage apply(BufferedImage input){

        double scale = this.scalePercentage / 100.0;
        BufferedImage resized = input;
        int newWidth = (int)(input.getWidth()*scale);
        int newHeight = (int)(input.getHeight()*scale);
        int scaleMethod = 0;
                
        if (scale < 1){
            scaleMethod = Image.SCALE_AREA_AVERAGING;
        } else {
            scaleMethod = Image.SCALE_SMOOTH;
        }
        

        Image outputImage = input.getScaledInstance(newWidth, newHeight, scaleMethod);
        resized = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
        resized.getGraphics().drawImage(outputImage, 0, 0, null);

        return resized;  

    }


}