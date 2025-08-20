package cosc202.andie;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
/**
 * <p>
 * ImageOperation to apply a median blur to an image
 * </p>
 * 
 * <p>
 * A median filter replaces a pixel with the
 *  most common pixel in a given radius around it
 * </p>
 * 
 * @author Liam Walls
 * @version 1.0
 */
public class MedianFilter implements ImageOperation{
    /**
     * The size of the sorted area applied to each pixel.  
     * A radius of 1 is a 3x3 area
     */
    private int radius;
    /**
     * <p>
     * Constructs a median filter with the given size
     * </p>
     * 
     * <p>
     * The size of the filter is the radius of the searched area.
     * A size of 1 is a 3x3 filter, 2 is a 5x5 filter, etc
     * </p>
     * 
     * @param radius the radius of the constructed filter
     */
    MedianFilter(int radius){
        this.radius = radius;
    }
    /**
     * <p>
     * Construct a median filter with the default size
     * </p>
     * <p>
     * By default the size of the radius is 1
     * </p>
     */
    MedianFilter(){
        this(1);
    }
    /**
     * <p>
     * Apply a median filter to an image
     * </p>
     * 
     * <p>
     * The median filter is applied using a new buffered image that is constructed
     * and returned by the function.
     * The larger the supplied radius the more blurred the image
     * </p>
     * 
     * @param input the image to apply the median filter to
     * @return the blurred image
     */
    public BufferedImage apply(BufferedImage input) {

        BufferedImage output = new BufferedImage(input.getColorModel(), input.copyData(null), input.isAlphaPremultiplied(), null);
        for (int i = radius; i < output.getHeight() - radius; i++) {
            for (int j = radius; j < output.getWidth() - radius; j++) {
                output.setRGB(j, i, getMedianPixel(input, j, i, radius));
            }
        }
        
        return output;
    }

    /**
     * <p>
     * A function that takes a pixel and replaces it with a new pixel made of
     * the median red, blue, green and alpha values in the pixels in a radius around it.
     * </p>
     * @param input the image to supply the pixels around it
     * @param j the coordinates of the pixel being altered
     * @param i the coordinates of the pixel being altered
     * @param radius the radius of the area being set to median around the pixel
     * @return an int representing the colour of the new pixel
     */
    private static int getMedianPixel(BufferedImage input, int j, int i, int radius) {
        ArrayList<Integer> reds = new ArrayList<>();
        ArrayList<Integer> greens = new ArrayList<>();
        ArrayList<Integer> blues = new ArrayList<>();
        ArrayList<Integer> alphas = new ArrayList<>();
        for (int y = -radius; y <= radius; y++) {
            for (int x = -radius; x <= radius; x++) {
                Color color = new Color(input.getRGB(j + x, i + y));
                reds.add(color.getRed());
                greens.add(color.getGreen());
                blues.add(color.getBlue());
                alphas.add(color.getAlpha());
            }
        }

        Collections.sort(reds);
        Collections.sort(blues);
        Collections.sort(greens);
        Collections.sort(alphas);
        int medianRed = reds.get(reds.size() / 2);
        int medianBlue = blues.get(blues.size() /2);
        int medianGreen = greens.get(greens.size() / 2);
        int medianAlpha = alphas.get(alphas.size() /2);
        return new Color(medianRed, medianGreen, medianBlue, medianAlpha).getRGB();
    }

}
