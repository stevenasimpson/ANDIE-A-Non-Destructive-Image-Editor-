package cosc202.andie;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;


/**
 * <p>
 * The Draw class represents a drawing operation that can be applied to an image.
 * It implements the ImageOperation interface and extends the ImagePanel class.
 * This class is also serializable.
 * </p>
 *
 * @author Liam Walls
 */
public class Draw extends ImagePanel implements ImageOperation, java.io.Serializable{

    private String shape;
    private Color colour;
    private boolean fill;
    private int size;
    private Point startPoint;
    private Point endPoint;

    /**
     * <p>
     * Constructs a new Draw object with the specified parameters.
     * </p>
     *
     * @param shape      the shape of the drawing (e.g., "line", "rectangle", "oval")
     * @param colour     the color of the drawing
     * @param fill       whether the shape should be filled or not
     * @param size       the size of the drawing
     * @param startPoint the starting point of the drawing
     * @param endPoint   the ending point of the drawing
     */
    public Draw(String shape, Color colour, boolean fill, int size, Point startPoint, Point endPoint){
        this.shape = shape;
        this.colour = colour;
        this.fill = fill;
        this.size = size;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }

    /**
     * <p>
     * Applies the drawing operation to the input image and returns the resulting image.
     * </p>
     * 
     * @param input the input image to apply the drawing operation to
     * @return the resulting image after applying the drawing operation
     */
    public BufferedImage apply(BufferedImage input){
        BufferedImage output = new BufferedImage(input.getColorModel(), input.copyData(null), input.isAlphaPremultiplied(), null);
        Graphics2D g = output.createGraphics();
        g.setColor(colour);
        g.setStroke(new BasicStroke(size));
        int x1 = Math.min(startPoint.x, endPoint.x);
        int y1 = Math.min(startPoint.y, endPoint.y);
        int x2 = Math.max(startPoint.x, endPoint.x);
        int y2 = Math.max(startPoint.y, endPoint.y);

        if (shape.equals(I18n.getKeyString("line"))){
            g.drawLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y);
        }
        else if (shape.equals(I18n.getKeyString("rectangle"))){
            if (fill){
                g.fillRect(x1, y1, x2-x1, y2-y1);
            }
            else{
                g.drawRect(x1, y1, x2-x1, y2-y1);
            }
        }
        else if (shape.equals(I18n.getKeyString("oval"))){
            if (fill){
                g.fillOval(x1, y1, x2-x1, y2-y1);
            }
            else{
                g.drawOval(x1, y1, x2-x1, y2-y1);
            }
        }
        g.dispose();
        return output;
    }





        

}
