package cosc202.andie;
import java.awt.Point;
import java.awt.image.BufferedImage;

/**
 * <p>
 * A class to crop an image based on the region selected
 * </p>
 * @author Liam Walls
 * */
public class Crop extends ImagePanel implements ImageOperation, java.io.Serializable  {

    /**
     * The starting point of the crop.
     */
    private Point startPoint;
    /**
     * The end point of the crop.
     */
    private Point endPoint;


    /**
     * <p>
     * Constructs a new Crop.
     * </p>
     * @param startPoint the starting point of the crop
     * @param endPoint the end point of the crop
     */
    public Crop(Point startPoint, Point endPoint){
        this.startPoint = startPoint;
        this.endPoint = endPoint;

    }

    /**
     * <p>
     * Applies the crop to the image.
     * </p>
     * 
     * @param input the image to crop
     * @return the cropped image
     */
    public BufferedImage apply(BufferedImage input){
        int x = Math.min(startPoint.x, endPoint.x);
        int y = Math.min(startPoint.y, endPoint.y);
        int width = Math.abs(startPoint.x - endPoint.x);
        int height = Math.abs(startPoint.y - endPoint.y);

        if(width>0 && height>0){
            BufferedImage output = input.getSubimage(x, y, width, height);
//                    new BufferedImage(width, height, input.getType());
//            int[] rgb = input.getRGB(x, y, width, height, null,0, width);
//            output.setRGB(0, 0, width, height, rgb, 0, width);
//            System.out.println("Cropped");
            return output;
        }
        return input;
}
}