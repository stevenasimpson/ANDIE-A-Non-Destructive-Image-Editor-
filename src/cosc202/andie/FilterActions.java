package cosc202.andie;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.KeyStroke;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JLabel;

/**
 * <p>
 * Actions provided by the Filter menu.
 * </p>
 * 
 * <p>
 * The Filter menu contains actions that update each pixel in an image based on
 * some small local neighbourhood. 
 * This includes a mean filter (a simple blur) in the sample code, but more operations will need to be added.
 * </p>
 * 
 * <p> 
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 * 
 * @author Steven Mills
 * @version 1.0
 */
public class FilterActions {
    
    /** A list of actions for the Filter menu. */
    protected ArrayList<Action> actions;

    /**
     * <p>
     * Create a set of Filter menu actions.
     * </p>
     */
    public FilterActions() {

        actions = new ArrayList<Action>();
        actions.add(new MeanFilterAction(I18n.getKeyString("meanFilter"), null, I18n.getKeyString("meanFilterDesc"), Integer.valueOf(KeyEvent.VK_1)));
        actions.add(new SoftBlurAction(I18n.getKeyString("softBlur"), null, I18n.getKeyString("softBlurDesc"), Integer.valueOf(KeyEvent.VK_2)));
        actions.add(new SharpenFilterAction(I18n.getKeyString("sharpenFilter"), new ImageIcon(this.getClass().getClassLoader().getResource("sharpen.png")), I18n.getKeyString("sharpenFilterDesc"), Integer.valueOf(KeyEvent.VK_3)));
        actions.add(new GaussianFilterAction(I18n.getKeyString("gaussianFilter"), null,I18n.getKeyString("gaussianFilterDesc"), Integer.valueOf(KeyEvent.VK_4)));
        actions.add(new MedianFilterAction(I18n.getKeyString("medianFilter"), null, I18n.getKeyString("medianFilterDesc"), Integer.valueOf(KeyEvent.VK_5)));
        actions.add(new RandomScatterAction(I18n.getKeyString("randomScatter"), null, I18n.getKeyString("randomScatterDesc"), Integer.valueOf(KeyEvent.VK_P)));
        actions.add(new BlockAveragingAction(I18n.getKeyString("blockAveraging"), null, I18n.getKeyString("blockAveragingDesc"), KeyEvent.VK_A));

        // Sobel filter actions
        actions.add(new SobelFilterAction(I18n.getKeyString("sobelHorizontal"), null, I18n.getKeyString("sobelHorizontalDesc"), Integer.valueOf(KeyEvent.VK_H), true));
        actions.add(new SobelFilterAction(I18n.getKeyString("sobelVertical"), null, I18n.getKeyString("sobelVerticalDesc"), Integer.valueOf(KeyEvent.VK_V), false));

        // emboss filter actions
        actions.add(new EmbossFilterAction(I18n.getKeyString("embossNorth"), null, I18n.getKeyString("embossNorthDesc"), KeyEvent.VK_1, new float[]{0, 0, 0, 1, 0, -1, 0, 0, 0}));
        actions.add(new EmbossFilterAction(I18n.getKeyString("embossEast"), null, I18n.getKeyString("embossEastDesc"), KeyEvent.VK_2, new float[]{1, 0, -1, 0, 0, 0, -1, 0, 0}));
        actions.add(new EmbossFilterAction(I18n.getKeyString("embossSouth"), null, I18n.getKeyString("embossSouthDesc"), KeyEvent.VK_3, new float[]{0, -1, 0, 0, 0, 0, 0, 1, 0}));
        actions.add(new EmbossFilterAction(I18n.getKeyString("embossWest"), null, I18n.getKeyString("embossWestDesc"), KeyEvent.VK_4, new float[]{0, 0, 1, 0, 0, -1, 0, 0, 0}));
        actions.add(new EmbossFilterAction(I18n.getKeyString("embossNorthEast"), null, I18n.getKeyString("embossNorthEastDesc"), KeyEvent.VK_5, new float[]{0, 0, 0, 0, 0, -1, 0, 1, 0}));
        actions.add(new EmbossFilterAction(I18n.getKeyString("embossNorthWest"), null, I18n.getKeyString("embossNorthWestDesc"), KeyEvent.VK_6, new float[]{0, 0, 0, 0, 0, 1, 0, -1, 0}));
        actions.add(new EmbossFilterAction(I18n.getKeyString("embossSouthEast"), null, I18n.getKeyString("embossSouthEastDesc"), KeyEvent.VK_7, new float[]{0, 1, 0, 0, 0, 0, 0, -1, 0}));
        actions.add(new EmbossFilterAction(I18n.getKeyString("embossSouthWest"), null, I18n.getKeyString("embossSouthWestDesc"), KeyEvent.VK_8, new float[]{0, -1, 0, 0, 0, 0, 0, 1, 0}));
        
    }

    /**
     * <p>
     * Create a menu containing the list of Filter actions.
     * </p>
     * 
     * @return The filter menu UI element.
     */
    public JMenu createMenu() {


        JMenu fileMenu = new JMenu(I18n.getKeyString("filter"));
        JMenu subMenu = new JMenu(I18n.getKeyString("embossFilters"));
        ImageIcon embossIcon = new ImageIcon(this.getClass().getClassLoader().getResource("emboss.png"));
        subMenu.setIcon(embossIcon);

        int count = 0;
        for (Action action : actions){
            if(count < (actions.size()-8)) {
                fileMenu.add(new JMenuItem(action));
                count++;
            } else {
                subMenu.add(new JMenuItem(action));
                count++;
            }
        }
        fileMenu.add(subMenu);
        return fileMenu;
    }

/**
     * <p>
     * Creates the buttons to add to the toolbar
     * </p>
     * 
     * @return The buttons to add to the toolbar.
     */
    public ArrayList<JButton> createButtons(){

        ArrayList<JButton> buttons = new ArrayList<JButton>();//new array of buttons to add to the toolbar

        ArrayList<Integer> options = new ArrayList<Integer>(); //array of options for the buttons
        //options.add(2);//option for sharpen
        
        for(int option = 0; option < actions.size(); option++){
            JButton button = new JButton(actions.get(option));
            if(options.contains(option)){
                button.setVerticalTextPosition(SwingConstants.BOTTOM);
                button.setHorizontalTextPosition(SwingConstants.CENTER);
                buttons.add(button);
            }
        }
        return buttons;
    }

    /**
     * <p>
     * Action to blur an image with a mean filter.
     * </p>
     * 
     * @see MeanFilter
     */
    public class MeanFilterAction extends ImageAction {

        /**
         * <p>
         * Create a new mean-filter action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        MeanFilterAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
            if(mnemonic != null)  putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(mnemonic, KeyEvent.CTRL_DOWN_MASK));
        }

        /**
         * <p>
         * Callback for when the convert-to-grey action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the MeanFilterAction is triggered.
         * It prompts the user for a filter radius, then applies an appropriately sized {@link MeanFilter}.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {

            // Determine the radius - ask the user.
            int radius = 1;

            // Pop-up dialog box to ask for the radius value.
            SpinnerNumberModel radiusModel = new SpinnerNumberModel(1, 1, 10, 1);
            JSpinner radiusSpinner = new JSpinner(radiusModel);
            int option = JOptionPane.showOptionDialog(null, radiusSpinner, I18n.getKeyString("enterFilterRadius"), JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

            // Check the return value from the dialog box.
            if (option == JOptionPane.CANCEL_OPTION) {
                return;
            } else if (option == JOptionPane.OK_OPTION) {
                radius = radiusModel.getNumber().intValue();
            }

            // Create and apply the filter
            target.getImage().apply(new MeanFilter(radius));
            target.repaint();
            target.getParent().revalidate();
        }

    }



    /**
     * <p>
     * Action to blur an image with a soft blur filter.
     * </p>
     * 
     * @see SoftBlur
     */
    public class SoftBlurAction extends ImageAction { 
        
        /**
         * <p>
         * Create a new mean-filter action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        SoftBlurAction(String name, ImageIcon icon, String desc, Integer mnemonic) { 
            super(name, icon, desc, mnemonic); 
            if(mnemonic != null)  putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(mnemonic, KeyEvent.CTRL_DOWN_MASK));
        } 

        /**
         * <p>
         * Callback for when the convert-to-grey action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the MeanFilterAction is triggered.
         * It prompts the user for a filter radius, then applies an appropriately sized {@link MeanFilter}.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) { 
            // Create and apply the filter 
            target.getImage().apply(new SoftBlur()); 
            target.repaint(); 
            target.getParent().revalidate(); 
        } 
    }

    /**
     * <p>
     * Action to blur an image with a soft blur filter.
     * </p>
     * 
     * @see SharpenFilter
     */
    public class SharpenFilterAction extends ImageAction{

        /**
         * <p>
         * Create a new mean-filter action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        SharpenFilterAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
            if(mnemonic != null)  putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(mnemonic, KeyEvent.CTRL_DOWN_MASK));
        }

        /**
         * <p>
         * Callback for when the convert-to-grey action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the MeanFilterAction is triggered.
         * It prompts the user for a filter radius, then applies an appropriately sized {@link MeanFilter}.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e){
            target.getImage().apply(new SharpenFilter());
            target.repaint();
            target.getParent().revalidate();
        }

    }

    /**
     * <p>
     * Action to add a gaussian filter to the image.
     * </p>
     * 
     * @see GaussianFilter
     */
    public class GaussianFilterAction extends ImageAction{

        /**
         * <p>
         * Create a new gaussian-filter action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        GaussianFilterAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
            if(mnemonic != null)  putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(mnemonic, KeyEvent.CTRL_DOWN_MASK));
        }

        /**
         * <p>
         * Callback for when the Gaussian filter action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the GaussianFilterAction is triggered.
         * It prompts the user for a filter radius, then applies an appropriately sized {@link GaussianFilter}.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {

            // Determine the radius - ask the user.
            int radius = 1;

            // Pop-up dialog box to ask for the radius value.
            SpinnerNumberModel radiusModel = new SpinnerNumberModel(1, 1, 10, 1);
            JSpinner radiusSpinner = new JSpinner(radiusModel);
            int option = JOptionPane.showOptionDialog(null, radiusSpinner, I18n.getKeyString("enterFilterRadius"), JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

            // Check the return value from the dialog box.
            if (option == JOptionPane.CANCEL_OPTION) {
                return;
            } else if (option == JOptionPane.OK_OPTION) {
                radius = radiusModel.getNumber().intValue();
            }

            // Create and apply the filter
            target.getImage().apply(new GaussianFilter(radius));
            target.repaint();
            target.getParent().revalidate();
        }

    }

    /**
     * <p>
     * Action to add a median filter to the image.
     * </p>
     * 
     * @see MedianFilter
     */
    public class MedianFilterAction extends ImageAction{

        /**
         * <p>
         * Creates a new median-filter action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        MedianFilterAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
            if(mnemonic != null)  putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(mnemonic, KeyEvent.CTRL_DOWN_MASK));
        }

        /**
         * <p>
         * Callback for when the MedianFilter action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the MedianFilterAction is triggered.
         * It prompts the user for a filter radius, then applies an appropriately sized {@link MedianFilter}.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {

            // Determine the radius - ask the user.
            int radius = 1;


            // Pop-up dialog box to ask for the radius value.


//            SpinnerNumberModel blockSizeOne = new SpinnerNumberModel(1, 1, 10, 1);
//            SpinnerNumberModel blockSizeTwo = new SpinnerNumberModel(1, 1, 10, 1);
//
//            JSpinner blockOneSpinner = new JSpinner(blockSizeOne);
//            JSpinner blockTwoSpinner = new JSpinner(blockSizeTwo);
//
//            JPanel panel = new JPanel();
//            panel.setLayout(new GridLayout(2, 1));
//            //panel.add(new JLabel("Brightness:"));
//            panel.add(blockOneSpinner);
//            //panel.add(new JLabel("Contrast:"));
//            panel.add(blockTwoSpinner);


            SpinnerNumberModel radiusModel = new SpinnerNumberModel(1, 1, 10, 1);
            JSpinner radiusSpinner = new JSpinner(radiusModel);
        
     
            int option = JOptionPane.showOptionDialog(null, radiusSpinner, I18n.getKeyString("enterFilterRadius"), JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

            // Check the return value from the dialog box.
            if (option == JOptionPane.CANCEL_OPTION) {
                return;
            } else if (option == JOptionPane.OK_OPTION) {
                radius = radiusModel.getNumber().intValue();
            }

            // Create and apply the filter
            target.getImage().apply(new MedianFilter(radius));
            target.repaint();
            target.getParent().revalidate();
        }

    }

    /**
     * <p>
     * Action to add a emboss filter to the image.
     * </p>
     * 
     * @see EmbossFilter
     */    
    public class EmbossFilterAction extends ImageAction {

        private final float[] kernel;
    
        /**
         * <p>
         * Create a new emboss-filter action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         * @param kernel The kernel to be used to create the emboss filter.
         */
        EmbossFilterAction(String name, ImageIcon icon, String desc, Integer mnemonic, float[] kernel) {
            super(name, icon, desc, mnemonic);
            if(mnemonic != null)  putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(mnemonic, KeyEvent.CTRL_DOWN_MASK + KeyEvent.SHIFT_DOWN_MASK));
            this.kernel = kernel;
        }
        
        /**
         * <p>
         * Callback for when the emboss filter action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the EmbossFilterAction is triggered.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            target.getImage().apply(new EmbossFilter(kernel));
            target.repaint();
            target.getParent().revalidate();
        }
    }

    /**
     * <p>
     * Action to add a Sobel filter to the image.
     * </p>
     * 
     * @see SobelFilter
     */    
    public class SobelFilterAction extends ImageAction {

        public final boolean isHorizontal;
    
        /**
         * <p>
         * Create a new sobel-filter action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         * @param isHorizontal The flag to determine if the filter is horizontal or vertical.
         */
        SobelFilterAction(String name, ImageIcon icon, String desc, Integer mnemonic, boolean isHorizontal) {
            super(name, icon, desc, mnemonic);
            if(mnemonic != null)  putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(mnemonic, KeyEvent.CTRL_DOWN_MASK + KeyEvent.SHIFT_DOWN_MASK));
            this.isHorizontal = isHorizontal;
        }
        
        /**
         * <p>
         * Callback for when the sobel filter action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the SobelFilterAction is triggered.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            // Create and apply the Sobel filter
            if (isHorizontal) {
                target.getImage().apply(new SobelFilter(true)); 
            } else {
                target.getImage().apply(new SobelFilter(false)); 
            }
            target.repaint();
            target.getParent().revalidate();
        }
    }

    /**
     * <p>
     * Action to add a RandomScatter filter to the image.
     * </p>
     *
     * @see RandomScatter
     */
    public class RandomScatterAction extends ImageAction{

        /**
         * <p>
         * Create a new random-scatter  action.
         * </p>
         *
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        RandomScatterAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
            if(mnemonic != null)  putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(mnemonic, KeyEvent.CTRL_DOWN_MASK + KeyEvent.SHIFT_DOWN_MASK));
        }

        /**
         * <p>
         * Callback for when the random scatter action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the RandomScatterAction is triggered.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            
            // Determine the radius - ask the user.
            int radius = 1;


            // Pop-up dialog box to ask for the radius value.
            SpinnerNumberModel radiusModel = new SpinnerNumberModel(1, 1, 10, 1);
            JSpinner radiusSpinner = new JSpinner(radiusModel);
            int option = JOptionPane.showOptionDialog(null, radiusSpinner, I18n.getKeyString("enterFilterRadius"), JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

            // Check the return value from the dialog box.
            if (option == JOptionPane.CANCEL_OPTION) {
                return;
            } else if (option == JOptionPane.OK_OPTION) {
                radius = radiusModel.getNumber().intValue();
            }

            // Create and apply the filter
            target.getImage().apply(new RandomScatter(radius));
            target.repaint();
            target.getParent().revalidate();
        }
    }

    /**
     * <p>
     * tbd
     * </p>
     * 
     * @see BlockAveraging
     */    
    public class BlockAveragingAction extends ImageAction {
        
        /**
         * <p>
         * Create a new emboss-filter action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        BlockAveragingAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
            if(mnemonic != null) putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(mnemonic, KeyEvent.CTRL_DOWN_MASK + KeyEvent.SHIFT_DOWN_MASK));
        }
        
        /**
         * <p>
         * Callback for when the sobel filter action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the SobelFilterAction is triggered.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {

            int blockSizeX = 0;
            int blockSizeY = 0;

            int width = target.getImage().getCurrentImage().getWidth();
            int height = target.getImage().getCurrentImage().getHeight();

            SpinnerNumberModel blockModelX = new SpinnerNumberModel(1, 1, width, 1);
            SpinnerNumberModel blockModelY = new SpinnerNumberModel(1, 1, height, 1);
            JSpinner blockSpinnerX = new JSpinner(blockModelX);
            JSpinner blockSpinnerY = new JSpinner(blockModelY);

            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(2, 1));
            panel.add(new JLabel(I18n.getKeyString("blockWidth")));
            panel.add(blockSpinnerX);
            panel.add(new JLabel(I18n.getKeyString("blockHeight")));
            panel.add(blockSpinnerY);

            int option = JOptionPane.showOptionDialog(null, panel, I18n.getKeyString("blockText"), JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

            if (option == JOptionPane.CANCEL_OPTION) {
                return;
            } else if (option == JOptionPane.OK_OPTION) {

                blockSizeX = blockModelX.getNumber().intValue();
                blockSizeY = blockModelY.getNumber().intValue();

                if(target.getImage().getCurrentImage().getWidth()% blockSizeX != 0 || target.getImage().getCurrentImage().getHeight() % blockSizeX  != 0){
                    JOptionPane.showMessageDialog(null, I18n.getKeyString("badBlock"));
                } 

                target.getImage().apply(new BlockAveraging(blockSizeX, blockSizeY));
                target.repaint();
                target.getParent().revalidate();
            }
        }
    }
}

