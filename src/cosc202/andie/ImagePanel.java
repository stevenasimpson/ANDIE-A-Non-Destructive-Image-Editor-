package cosc202.andie;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

/**
 * <p>
 * UI display element for {@link EditableImage}s.
 * </p>
 * 
 * <p>
 * This class extends {@link JPanel} to allow for rendering of an image, as well as zooming
 * in and out. 
 * </p>
 * 
 * <p> 
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 * 
 * @author Steven Mills
 * @version 1.0
 */
public class ImagePanel extends JPanel implements MouseListener, MouseMotionListener {
    
    /**
     * The image to display in the ImagePanel.
     */
    private EditableImage image;

    /**
     * <p>
     * The zoom-level of the current view.
     * A scale of 1.0 represents actual size; 0.5 is zoomed out to half size; 1.5 is zoomed in to one-and-a-half size; and so forth.
     * </p>
     * 
     * <p>
     * Note that the scale is internally represented as a multiplier, but externally as a percentage.
     * </p>
     */
    private double scale;

    /** variables for drawing start point of the shape*/
    private Point startPoint = null;
    /** variables for drawing end point of the shape*/
    private Point endPoint = null;
    /** variable to assess if the mouse is pressed*/
    private boolean pressed = false;
    /** variable to assess if the mouse is released*/
    private boolean released = false;

    /** variable for selecting the crop action*/
    public boolean crop = false;
    /** variable for selecting the oval crop action*/
    public boolean ovalcrop = false;
    /** variable for selecting the active status of the mouse buttons/movement*/
    public boolean active = false;

    /** variable for selecting the draw action*/
    public boolean draw = false;
    /** variable for selecting the fill status*/
    public boolean fill = false;
    /** variable for selecting the shape to draw*/
    public String shape = null;
    /** variable for selecting the colour to draw*/
    public Color colour = null;
    /** variable for selecting the thickness of the line to draw*/
    public int thickness = 0;

    /**
     * <p>
     * Create a new ImagePanel.
     * </p>
     * 
     * <p>
     * Newly created ImagePanels have a default zoom level of 100%
     * </p>
     */
    public ImagePanel() {
        image = new EditableImage();
        scale = 1.0;
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    /**
     * <p>
     * Get the currently displayed image
     * </p>
     *
     * @return the image currently displayed.
     */
    public EditableImage getImage() {
        return image;
    }

    /**
     * <p>
     * Get the current zoom level as a percentage.
     * </p>
     * 
     * <p>
     * The percentage zoom is used for the external interface, where 100% is the original size, 50% is half-size, etc. 
     * </p>
     * @return The current zoom level as a percentage.
     */
    public double getZoom() {
        return 100*scale;
    }

    /**
     * <p>
     * Set the current zoom level as a percentage.
     * </p>
     * 
     * <p>
     * The percentage zoom is used for the external interface, where 100% is the original size, 50% is half-size, etc. 
     * The zoom level is restricted to the range [50, 200].
     * </p>
     * @param zoomPercent The new zoom level as a percentage.
     */
    public void setZoom(double zoomPercent) {
        if (zoomPercent < 50) {
            zoomPercent = 50;
        }
        if (zoomPercent > 200) {
            zoomPercent = 200;
        }
        scale = zoomPercent / 100;
    }


    /**
     * <p>
     * Gets the preferred size of this component for UI layout.
     * </p>
     * 
     * <p>
     * The preferred size is the size of the image (scaled by zoom level), or a default size if no image is present.
     * </p>
     * 
     * @return The preferred size of this component.
     */
    @Override
    public Dimension getPreferredSize() {
        if (image.hasImage()) {
            return new Dimension((int) Math.round(image.getCurrentImage().getWidth()*scale), 
                                 (int) Math.round(image.getCurrentImage().getHeight()*scale));
        } else {
            return new Dimension(450, 450);
        }
    }

    /** Add a mouse pressed method
     *
     * @param evt The mouse event
     * */
    @Override
    public void mousePressed(MouseEvent evt){
        if (!active){
            return;
        }

        startPoint = evt.getPoint();
        endPoint = startPoint;
        if(image.hasImage()){
            pressed = true;
        }
        released =  false;
    }

    /** Add a mouse released method
     *
     * @param evt The mouse event
     * */
    @Override
    public void mouseReleased(MouseEvent evt) {
        if (!active){
            return;
        }
        endPoint = evt.getPoint();
        if(image.hasImage()){
            released = true;
        }
        pressed = false;
        active = false;
    }

    /** Add a mouse dragged method
     *
     * @param evt The mouse event
     * */
    @Override
    public void mouseDragged(MouseEvent evt) {
        if (!active){
            return;
        }
        endPoint = evt.getPoint();
        repaint();
    }

    //Unused methods
    public void mouseEntered(MouseEvent evt){}
    public void mouseExited(MouseEvent evt){}
    public void mouseClicked(MouseEvent evt){}
    public void mouseMoved(MouseEvent evt){}


    /**
     * <p>
     * (Re)draw the component in the GUI.
     * </p>
     * 
     * @param g The Graphics component to draw the image on.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image.hasImage()) {
            Graphics2D g2  = (Graphics2D) g.create();
            g2.scale(scale, scale);
            g2.drawImage(image.getCurrentImage(), null, 0, 0);
            //draws a rectangle for current temporary selection
            if (pressed == true && draw == false) {
                g2.setColor(Color.RED);
                g2.drawRect(Math.min(startPoint.x, endPoint.x), Math.min(startPoint.y, endPoint.y),
                        Math.abs(startPoint.x - endPoint.x), Math.abs(startPoint.y - endPoint.y));
            } else if (pressed && draw){
                g2.setColor(colour);
                g2.setStroke(new BasicStroke(thickness));
                int x1 = Math.min(startPoint.x, endPoint.x);
                int y1 = Math.min(startPoint.y, endPoint.y);
                int x2 = Math.max(startPoint.x, endPoint.x);
                int y2 = Math.max(startPoint.y, endPoint.y);
                if (shape.equals(I18n.getKeyString("line"))){
                    g2.drawLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y);
                }
                else if (shape.equals(I18n.getKeyString("rectangle"))){
                    if (fill){
                        g2.fillRect(x1, y1, x2-x1, y2-y1);
                    }
                    else{
                        g2.drawRect(x1, y1, x2-x1, y2-y1);
                    }
                }
                else if (shape.equals(I18n.getKeyString("oval"))){
                    if (fill){
                        g2.fillOval(x1, y1, x2-x1, y2-y1);
                    }
                    else{
                        g2.drawOval(x1, y1, x2-x1, y2-y1);
                    }
                }
            }
            if (released == true){
                if (crop == true){
                    getImage().apply(new Crop(startPoint, endPoint));
                    repaint();
                    crop = false;
                    active = false;
                    startPoint = null;
                    endPoint = null;
                }
                if (ovalcrop == true){
                    getImage().apply(new OvalCrop(startPoint, endPoint));
                    repaint();
                    ovalcrop = false;
                    active = false;
                    startPoint = null;
                    endPoint = null;
                }

                if (draw == true){
                    getImage().apply(new Draw(shape, colour, fill, thickness, startPoint, endPoint));
                    repaint();
                    draw = false;
                    active = false;
                    startPoint = null;
                    endPoint = null;
                }
                pressed = false;
                released = false;
            }
            repaint();
            getParent().revalidate();
            g2.dispose();
        }
    }
}
