package cosc202.andie;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * <p>
 *  A class to crop an image based on the region selected
 * </p>
 *
 * @author Steven Simpson
 * */
public class OvalCrop extends ImagePanel implements ImageOperation, java.io.Serializable  {

    private Point startPoint;
    private Point endPoint;

    /**
     * <p>
     *  Constructor for the OvalCrop class
     * </p>
     *
     * @param startPoint The starting point of the selection
     * @param endPoint The ending point of the selection
     */
    public OvalCrop(Point startPoint, Point endPoint){
        this.startPoint = startPoint;
        this.endPoint = endPoint;

    }

    /**
     * <p>
     *  Method to apply the oval crop to the image
     * </p>
     *
     * @param input The image to apply the crop to
     * @return BufferedImage The cropped image
     */
    public BufferedImage apply(BufferedImage input){
        int x = Math.min(startPoint.x, endPoint.x);
        int y = Math.min(startPoint.y, endPoint.y);
        int width = Math.abs(startPoint.x - endPoint.x);
        int height = Math.abs(startPoint.y - endPoint.y);

        if(width>0 && height>0){
            //new image for the masking
            BufferedImage mask = new BufferedImage(input.getWidth(), input.getHeight(), BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = mask.createGraphics();
            //enabling smoother edges
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            //set background to transparent
            g2d.setBackground(new Color(0, 0, 0, 0));
            //clear the image with the transparent background
            g2d.clearRect(0, 0, mask.getWidth(), mask.getHeight());
            //set colour to Black (colour of oval)
            g2d.setColor(Color.BLACK);
            //draw and fill oval using start/end points of selection
            g2d.fillOval(x, y, width, height);

            BufferedImage output = new BufferedImage(input.getWidth(), input.getHeight(), BufferedImage.TYPE_INT_ARGB);

            // Get the graphics object from the output image
            g2d = output.createGraphics();

            // Draw the original image onto the result image using the mask
            g2d.drawImage(input, 0, 0, null);
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.DST_IN));
            g2d.drawImage(mask, 0, 0, null);

            // Dispose the graphics object
            g2d.dispose();

            output = output.getSubimage(x, y, width, height);
            return output;
        }
        return input;
}
}