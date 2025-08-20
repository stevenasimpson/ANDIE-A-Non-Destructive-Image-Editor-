package cosc202.andie;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.Action;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.KeyStroke;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.colorchooser.AbstractColorChooserPanel;

 /**
 * <p>
 * Actions provided by the Colour menu.
 * </p>
 *
 * <p>
 * The Colour menu contains actions that affect the colour of each pixel directly
 * without reference to the rest of the image.
 * This includes conversion to greyscale in the sample code, but more operations will need to be added.
 * </p>
  *
 * <p>
 * This class is responsible for creating and managing a set of colour-related actions.
 * Each action represents a different operation that can be performed on an image, such as converting to greyscale, inverting colours, etc.
 * </p>
 *
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 *
 * @author Steven Mills
 * @version 1.0
 */
public class ColourActions {
    
    /**
     * A list of actions that can be performed. Each action is represented as an instance of the Action class.
     */
    protected ArrayList<Action> actions;

    /**
     * <p>
     * Create a set of Colour menu actions.
     * </p>
     */
    public ColourActions() {

        actions = new ArrayList<Action>();
        actions.add(new ConvertToGreyAction(I18n.getKeyString("greyscale"), new ImageIcon(this.getClass().getClassLoader().getResource("greyscale.png")), I18n.getKeyString("greyscaleDesc"), Integer.valueOf(KeyEvent.VK_B)));
        actions.add(new InvertColourAction(I18n.getKeyString("invert"), new ImageIcon(this.getClass().getClassLoader().getResource("invert.png")), I18n.getKeyString("invertDesc"), Integer.valueOf(KeyEvent.VK_I)));
        actions.add(new ColourChannelCycleAction(I18n.getKeyString("colourchannelcycle"), new ImageIcon(this.getClass().getClassLoader().getResource("colourCycle.png")), I18n.getKeyString("colourchannelcycleDesc"), Integer.valueOf(KeyEvent.VK_C)));
        actions.add(new DrawAction(I18n.getKeyString("draw"), new ImageIcon(this.getClass().getClassLoader().getResource("draw.png")), I18n.getKeyString("drawDesc"), Integer.valueOf(KeyEvent.VK_D)));
        actions.add(new BrightnessContrastAction(I18n.getKeyString("brightnessContrast"), new ImageIcon(this.getClass().getClassLoader().getResource("brightnessContrast.png")), I18n.getKeyString("brightnessContrastDesc"), Integer.valueOf(KeyEvent.VK_B)));
    }

    /**
     * <p>
     * Create a menu containing the list of Colour actions.
     * </p>
     * 
     * @return The colour menu UI element.
     */
    public JMenu createMenu() {

        JMenu fileMenu = new JMenu(I18n.getKeyString("colour"));

        for(Action action: actions) {
            fileMenu.add(new JMenuItem(action));
        }

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
        JButton button = new JButton();

        ArrayList<Integer> options = new ArrayList<Integer>(); //array of options for the buttons
        //options.add(0);//option for Greyscale
        //options.add(1);//option for Invert
        //options.add(2);//option for Colour Cycle
        options.add(3);//option for Draw
        options.add(4);//option for Brightness/Contrast

        
        for(int option = 0; option < actions.size(); option++){
            button = new JButton(actions.get(option));
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
     * Action to convert an image to greyscale.
     * </p>
     * 
     * @see ConvertToGrey
     */
    public class ConvertToGreyAction extends ImageAction {

        /**
         * <p>
         * Create a new convert-to-grey action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        ConvertToGreyAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
            if(mnemonic != null) putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(mnemonic, KeyEvent.CTRL_DOWN_MASK));
        }

        /**
         * <p>
         * Callback for when the convert-to-grey action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the ConvertToGreyAction is triggered.
         * It changes the image to greyscale.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            target.getImage().apply(new ConvertToGrey());
            target.repaint();
            target.getParent().revalidate();
        }

    }

    /**
     * <p>
     * Action to invert the colours in an image.
     * </p>
     * 
     * @see InvertColour
     */
    public class InvertColourAction extends ImageAction {

        /**
         * <p>
         * Create a new invert-colour action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        InvertColourAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
            if(mnemonic != null)  putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(mnemonic, KeyEvent.CTRL_DOWN_MASK));
        }

        /**
         * <p>
         * This method is called whenever the InvertColour action is triggered.
         * </p>
         *
         * <p>
         * It applies the InvertColour operation to the target image and repaints the target.
         * </p>
         *
         * @param e The event triggering this callback
         */
        public void actionPerformed(ActionEvent e) {
            target.getImage().apply(new InvertColour());
            target.repaint();
            target.getParent().revalidate();
        }

    }
    /**
     * <p>
     * Action to change the brightness and contrast of an image.
     * </p>
     * 
     * @see BrightnessContrast
     */
    public class BrightnessContrastAction extends ImageAction {
        /**
         * <p>
         * Create a new brightness/contrast action.
         * </p>
         *
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        BrightnessContrastAction(String name, ImageIcon icon, String desc, Integer mnemonic){
            super(name, icon, desc, mnemonic);
            if(mnemonic != null)  putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(mnemonic, KeyEvent.CTRL_DOWN_MASK + KeyEvent.SHIFT_DOWN_MASK));
        }

        /**
         * <p>
         * This method is called whenever the BrightnessContrastAction is triggered.
         * </p>
         *
         * <p>
         * It prompts the user to enter the brightness and contrast adjustments, checks the user's response, and applies the adjustments if the user clicked OK.
         * </p>
         *
         * @param e The event triggering this callback
         */
        public void actionPerformed(ActionEvent e) {
            int bright = 0;
            int con = 0;

            SpinnerNumberModel brightModel = new SpinnerNumberModel(0, -100, 100, 1);
            SpinnerNumberModel conModel = new SpinnerNumberModel(0, -100, 100, 1);
            JSpinner brightSpinner = new JSpinner(brightModel);
            JSpinner conSpinner = new JSpinner(conModel);

            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(2, 1));
            panel.add(new JLabel(I18n.getKeyString("brightness" ) + ":"));
            panel.add(brightSpinner);
            panel.add(new JLabel(I18n.getKeyString("contrast" ) + ":"));
            panel.add(conSpinner);

            int option = JOptionPane.showOptionDialog(null, panel, I18n.getKeyString("differences"), JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

            if (option == JOptionPane.CANCEL_OPTION) {
                return;
            } else if (option == JOptionPane.OK_OPTION) {
                bright = brightModel.getNumber().intValue();
                con = conModel.getNumber().intValue();
            }

            target.getImage().apply(new BrightnessContrast(bright, con));
            target.repaint();
            target.getParent().revalidate();
        }
    }

    /**
     * <p>
     * Action to cycle the colour channels of an image.
     * </p>
     * 
     * @see ColourChannelCycle
     */
    public class ColourChannelCycleAction extends ImageAction {

        /**
         * <p>
         * Create a new colour-channel-cycle action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        ColourChannelCycleAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
            if(mnemonic != null)  putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(mnemonic, KeyEvent.CTRL_DOWN_MASK));
        }

        /**
         * <p>
         * Callback for when the colour-channel-cycle action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the ColourChannelCycle is triggered.
         * It cycles the image RGB values.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            JPanel shifts = new JPanel();
            JButton shiftLeft = new ShiftButton(I18n.getKeyString("shiftleft"), 1, target);
            JButton shiftRight = new ShiftButton(I18n.getKeyString("shiftright"), 2, target);
            JButton swapRG = new ShiftButton(I18n.getKeyString("swapRG"), 3, target);
            JButton swapGB = new ShiftButton(I18n.getKeyString("swapGB"), 4, target);
            JButton swapRB = new ShiftButton(I18n.getKeyString("swapRB"), 5, target);
            shifts.add(shiftLeft);
            shifts.add(shiftRight);

            JPanel swaps = new JPanel();
            swaps.add(swapRG);
            swaps.add(swapGB);
            swaps.add(swapRB);

            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(2, 1));
            panel.add(shifts);
            panel.add(swaps);

            JOptionPane.showOptionDialog(null, panel, I18n.getKeyString("colourchannelcycle"), JOptionPane.CLOSED_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

        }

    }

    /**
     * <p>
     * Action button used to present options to the user.
     * </p>
     * 
     */
    private class ShiftButton extends JButton implements ActionListener{
        
        /** Switch for choosing the correct operation to perform*/
        int operation = 1;
        /** The target image panel*/
        ImagePanel target;
        
        /**
         * <p>
         * Create a new button.
         * </p>
         * 
         * @param name The name of the button (ignored if null).
         * @param operation A button used to display the action (ignored if null).
         * @param target The Image panel for buttons to go in.
         */
        private ShiftButton(String name, int operation, ImagePanel target){
            super(name);
            this.operation = operation;
            this.target = target;
            addActionListener(this);
        }

        /** <p>
         * Callback for when the ShiftButton action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the ShiftButton is triggered.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        @Override
        public void actionPerformed(ActionEvent e){
            target.getImage().apply(new ColourChannelCycle(operation));
            target.repaint();
            target.getParent().revalidate();
        }
    }

    /**
     * <p>
     * This class extends ImageAction and represents a draw action.
     * </p>
     */
    public class DrawAction extends ImageAction {

        /**
         * <p>
         * Constructs a new DrawAction with the specified name, icon, description, and mnemonic.
         * </p>
         *
         * @param name The name of the action
         * @param icon The icon of the action
         * @param desc A brief description of the action (ignored if null)
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null)
         */
        DrawAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
            if(mnemonic != null)  putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(mnemonic, KeyEvent.CTRL_DOWN_MASK));
        }

        /**
         * <p>
         * This method is called whenever the DrawAction is triggered.
         * </p>
         *
         * <p>
         * It prompts the user to select the shape, color, and fill option for the drawing, and applies the drawing to the target image.
         * </p>
         *
         * @param e The event triggering this callback
         */
        public void actionPerformed(ActionEvent e) {

            JPanel shapes = new JPanel();
            shapes.setLayout(new BoxLayout(shapes, BoxLayout.Y_AXIS));

            String[] shapeOptions = {I18n.getKeyString("line"), I18n.getKeyString("rectangle"), I18n.getKeyString("oval")};
            JComboBox<String> shape = new JComboBox<String>(shapeOptions);
            shape.setAlignmentY(Component.CENTER_ALIGNMENT);
            shapes.add(shape);

            JColorChooser colour = new JColorChooser();
            //select only swatches panel
            AbstractColorChooserPanel[] panels = colour.getChooserPanels();
            for (AbstractColorChooserPanel accp : panels) {
                if (!accp.getDisplayName().equals("Swatches")) {
                    colour.removeChooserPanel(accp);
                }
            }

            //remove preview panel
            colour.setPreviewPanel(new JPanel());
            shapes.add(colour, BorderLayout.CENTER);

            JCheckBox fill = new JCheckBox(I18n.getKeyString("fill"));
            fill.setVisible(enabled);
            fill.setAlignmentY(Component.CENTER_ALIGNMENT);
            shapes.add(fill);

            JSlider width = new JSlider(JSlider.HORIZONTAL, 1, 10, 1);
            width.setMajorTickSpacing(1);
            width.setMinorTickSpacing(2);
            width.setPaintTicks(true);
            width.setPaintLabels(true);
            width.setAlignmentY(Component.CENTER_ALIGNMENT);
            shapes.add(width);


            JOptionPane.showOptionDialog(null, shapes, I18n.getKeyString("draw"), JOptionPane.CLOSED_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
            target.shape = (String) shape.getSelectedItem();
            target.colour = colour.getColor();
            target.fill = fill.isSelected();
            target.thickness = width.getValue();
            target.draw = true;
            target.active = true;

            target.repaint();
            target.getParent().revalidate();
        }

    }

}
