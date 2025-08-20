package cosc202.andie;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * <p>
 * Block averaging operation to pixelate an image.
 * </p>
 * 
 * <p>
 * This class is used to pixelate an image by averaging the color values of 
 * a user defined block size.
 * </p>
 * 
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA
 * 4.0</a>
 * </p>
 * 
 * @author Ashwin Ellis
 * @version 1.0
 *
 * 
 */
public class BlockAveraging implements ImageOperation, java.io.Serializable {

    //User defined block sizes
    private int blockSizeX;
    private int blockSizeY;

    /**
     * <p>
     * Create a new BlockAveraging operation.
     * </p>
     *
     * @param blockSizeX The width of the block.
     * @param blockSizeY The height of the block.
     */
    BlockAveraging(int blockSizeX, int blockSizeY) {
        this.blockSizeX = blockSizeX;
        this.blockSizeY = blockSizeY;
    }

    /**
     * <p> 
     * Apply the averaging operation to an image.
     * </p>
     * 
     * @param input The image to be pixelated.
     * @return The pixelated image.
     */
    public BufferedImage apply(BufferedImage input) {

        //Get the width and height of the image
        int width = input.getWidth();
        int height = input.getHeight();

        //Set the block width and height to the user defined block sizes
        int blockWidth = blockSizeX;
        int blockHeight = blockSizeY;

        //Set the initial y value to 0
        int y = 0;

        while (y < height) {

            for (int x = 0; x < width; x += blockWidth) {

                //If the block width or height is greater than the image width or height, 
                // set the block width or height to the image width or height.
                if (x + blockWidth > width) {
                    blockWidth = width - x;
                }

                //If the block width or height is greater than the image width or height,
                // set the block width or height to the image width or height.
                if (y + blockHeight > height) {
                    blockHeight = height - y;
                }

                //Set the mean pixel value of the block
                setMeanPixel(input, x, y, x + blockWidth, y + blockHeight);

            }  

            y += blockHeight;
        }

        return input;
    }

    /**
     * <p>
     * Set the mean pixel value of a block.
     * </p>
     * @param input The image to set the mean pixel value to.
     * @param x1 The x coordinate of the top left corner of the block.
     * @param y1 The y coordinate of the top left corner of the block.
     * @param x2 The x coordinate of the bottom right corner of the block.
     * @param y2 The y coordinate of the bottom right corner of the block.
     */
    private static void setMeanPixel(BufferedImage input, int x1, int y1, int x2, int y2) {

        //Get the mean pixel value of the block
        int color = getMeanPixel(input, x1, y1, x2, y2);

        //Nested for loop looping through the block
        for (int x = x1; x < x2; x++) {
            for (int y = y1; y < y2; y++) {

                //Set the mean pixel value to the block
                input.setRGB(x, y, color);

            }
        }
    }   

    /**
     * <p>
     * Get the mean pixel value of a block.
     * </p>
     * 
     * @param input The image to get the mean pixel value from.
     * @param x1 The x coordinate of the top left corner of the block.
     * @param y1 The y coordinate of the top left corner of the block.
     * @param x2 The x coordinate of the bottom right corner of the block.
     * @param y2 The y coordinate of the bottom right corner of the block.
     * @return The mean pixel value of the block.
     */
    private static int getMeanPixel(BufferedImage input, int x1, int y1, int x2, int y2) {

        //Create an array list for the red, green, blue, and alpha values
        ArrayList<Integer> reds = new ArrayList<>();
        ArrayList<Integer> greens = new ArrayList<>();
        ArrayList<Integer> blues = new ArrayList<>();
        ArrayList<Integer> alphas = new ArrayList<>();

        //Nested for loop looping through the block
        for (int x = x1; x < x2; x++) {
            for (int y = y1; y < y2; y++) {

                //Add colours to an array list
                Color color = new Color(input.getRGB(x, y));
                reds.add(color.getRed());
                greens.add(color.getGreen());
                blues.add(color.getBlue());
                alphas.add(color.getAlpha());

            }
        }

        //Get the mean red, green, blue, and alpha values
        int meanRed = getMeanArrayList(reds);
        int meanGreen = getMeanArrayList(greens);
        int meanBlue = getMeanArrayList(blues);
        int meanAlpha = getMeanArrayList(alphas);

        return new Color(meanRed, meanGreen, meanBlue, meanAlpha).getRGB();
    }

    /**
     * <p>
     * Method to return the mean of an array list
     * </p>
     * 
     * @param arr The array list to get the mean value from
     * @return The mean value of the array list
     */
    private static int getMeanArrayList(ArrayList<Integer> arr) {

        double meanValue = 0;

        //Loop through the array list and add the values
        for (int i = 0; i < arr.size(); i++) {
            meanValue += arr.get(i);
        }

        //Divide the sum of the values by the size of the array list
        meanValue = meanValue / arr.size();

        return (int) Math.round(meanValue);
    }
}
