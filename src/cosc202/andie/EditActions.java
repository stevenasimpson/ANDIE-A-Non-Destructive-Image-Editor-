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

 /**
 * <p>
 * Actions provided by the Edit menu.
 * </p>
 * 
 * <p>
 * The Edit menu is very common across a wide range of applications.
 * There are a lot of operations that a user might expect to see here.
 * In the sample code there are Undo and Redo actions, but more may need to be added.
 * </p>
 * 
 * <p> 
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 * 
 * @author Steven Mills
 * @version 1.0
 */
public class EditActions {
    
    /** A list of actions for the Edit menu. */
    protected ArrayList<Action> actions;

    /**
     * <p>
     * Create a set of Edit menu actions.
     * </p>
     */
    public EditActions() {

        actions = new ArrayList<Action>();
        actions.add(new UndoAction(I18n.getKeyString("undo"), new ImageIcon(this.getClass().getClassLoader().getResource("undo.png")), I18n.getKeyString("undoDesc"), Integer.valueOf(KeyEvent.VK_Z)));
        actions.add(new RedoAction(I18n.getKeyString("redo"), new ImageIcon(this.getClass().getClassLoader().getResource("redo.png")), I18n.getKeyString("redoDesc"), Integer.valueOf(KeyEvent.VK_Y)));
        actions.add(new RepeatAction(I18n.getKeyString("repeat"), new ImageIcon(this.getClass().getClassLoader().getResource("repeat.png")), I18n.getKeyString("repeatDesc"), Integer.valueOf(KeyEvent.VK_Z)));

        actions.add(new CropAction(I18n.getKeyString("crop"), new ImageIcon(this.getClass().getClassLoader().getResource("crop.png")), I18n.getKeyString("cropDesc"), Integer.valueOf(KeyEvent.VK_F)));
        actions.add(new OvalCropAction(I18n.getKeyString("ovalcrop"), new ImageIcon(this.getClass().getClassLoader().getResource("ovalcrop.png")), I18n.getKeyString("ovalcropDesc"), Integer.valueOf(KeyEvent.VK_F)));

        actions.add(new ResizeAction(I18n.getKeyString("resize"), new ImageIcon(this.getClass().getClassLoader().getResource("resize.png")), I18n.getKeyString("resizeDesc"), Integer.valueOf(KeyEvent.VK_R)));
        actions.add(new FlipVerticalAction(I18n.getKeyString("flipVertical"), new ImageIcon(this.getClass().getClassLoader().getResource("flipVert.png")), I18n.getKeyString("flipVerticalDesc"), Integer.valueOf(KeyEvent.VK_V)));
        actions.add(new FlipHorizontalAction(I18n.getKeyString("flipHorizontal"), new ImageIcon(this.getClass().getClassLoader().getResource("flipHoriz.png")), I18n.getKeyString("flipHorizontalDesc"), Integer.valueOf(KeyEvent.VK_H)));
        actions.add(new RotateLeftAction(I18n.getKeyString("rotateLeft90"), new ImageIcon(this.getClass().getClassLoader().getResource("rotLeft.png")), I18n.getKeyString("rotateLeftDesc"), Integer.valueOf(KeyEvent.VK_L)));
        actions.add(new RotateRightAction(I18n.getKeyString("rotateRight90"), new ImageIcon(this.getClass().getClassLoader().getResource("rotRight.png")), I18n.getKeyString("rotateRightDesc"), Integer.valueOf(KeyEvent.VK_K)));
        actions.add(new Rotate180Action(I18n.getKeyString("rotate180"), new ImageIcon(this.getClass().getClassLoader().getResource("rot180.png")), I18n.getKeyString("rotate180Desc"), Integer.valueOf(KeyEvent.VK_J)));
    }

    /**
     * <p>
     * Create a menu containing the list of Edit actions.
     * </p>
     * 
     * @return The edit menu UI element.
     */
    public JMenu createMenu() {

        JMenu editMenu = new JMenu(I18n.getKeyString("edit"));

        for (Action action: actions) {
            editMenu.add(new JMenuItem(action));
        }

        return editMenu;
    }

    /**
     * <p>
     * Creates the buttons to add to the toolbar
     * </p>
     *
     * @author Steven Simpson
     * @return The buttons to add to the toolbar.
     */
    public ArrayList<JButton> createButtons(){

        ArrayList<JButton> buttons = new ArrayList<JButton>();//new array of buttons to add to the toolbar

        ArrayList<Integer> options = new ArrayList<Integer>(); //array of options for the buttons
        options.add(0);//option for undo
        options.add(1);//option for redo
        options.add(2);//option for repeat
        options.add(3);//option for crop
        options.add(4);//option for oval crop
        options.add(5);//option for resize
        options.add(6);//option for flip vert
        options.add(7);//option for flip horiz
        options.add(8);//option for rot left
        options.add(9);//option for rot right
        options.add(10);//option for rot 180
        
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
     * Action to undo an {@link ImageOperation}.
     * </p>
     * 
     * @see EditableImage#undo()
     */
    public class UndoAction extends ImageAction {

        /**
         * <p>
         * Create a new undo action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        UndoAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
            if(mnemonic != null)  putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(mnemonic, KeyEvent.CTRL_DOWN_MASK));
        }

        /**
         * <p>
         * Callback for when the undo action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the UndoAction is triggered.
         * It undoes the most recently applied operation.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            target.getImage().undo();
            target.repaint();
            target.getParent().revalidate();
        }
    }

     /**
     * <p>
     * Action to redo an {@link ImageOperation}.
     * </p>
     * 
     * @see EditableImage#redo()
     */   
    public class RedoAction extends ImageAction {

        /**
         * <p>
         * Create a new redo action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        RedoAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
            if(mnemonic != null)  putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(mnemonic, KeyEvent.CTRL_DOWN_MASK));
        }

        
        /**
         * <p>
         * Callback for when the redo action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the RedoAction is triggered.
         * It redoes the most recently undone operation.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            target.getImage().redo();
            target.repaint();
            target.getParent().revalidate();
        }
    }

    /**
     * <p>
     * Action to reapply the previous {@link ImageAction}
     * </p>
     * 
     * @see EditableImage#repeat()
     */
    public class RepeatAction extends ImageAction {

        /**
         * <p>
         * Create a new redo action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        RepeatAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
            if(mnemonic != null)  putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(mnemonic, KeyEvent.CTRL_DOWN_MASK + KeyEvent.SHIFT_DOWN_MASK));
        }

         /**
         * <p>
         * Callback for when the repeat action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the RepeatAction is triggered.
         * It reapplies the most recently done operation.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            target.getImage().repeat();
            target.repaint();
            target.getParent().revalidate();
        }

    }

    /**
     * <p>
     * Action to resize an image.
     * </p>
     * 
     * 
     * @author Steven Simpson 
     */
    public class ResizeAction extends ImageAction {

        /**
         * <p>
         * Create a new resize action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        ResizeAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
            if(mnemonic != null)  putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(mnemonic, KeyEvent.CTRL_DOWN_MASK));
        }

        /**
         * <p>
         * Callback for when the resize action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the ResizeAction is triggered.
         * It Resizes the image.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {

            // Determine the scale percentage - ask the user.
            int scalePercentage = 1;

            // Pop-up dialog box to ask for the scale value.
            SpinnerNumberModel scalePercentageModel = new SpinnerNumberModel(50, 50, 200, 1);
            JSpinner scalePercentageSpinner = new JSpinner(scalePercentageModel);
            int option = JOptionPane.showOptionDialog(null, scalePercentageSpinner, I18n.getKeyString("enterResizePercentage"), JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

            // Check the return value from the dialog box.
            if (option == JOptionPane.CANCEL_OPTION) {
                return;
            } else if (option == JOptionPane.OK_OPTION) {
                scalePercentage = scalePercentageModel.getNumber().intValue();
            }

            target.getImage().apply(new ResizeImage(scalePercentage));
            target.repaint();
            target.getParent().revalidate();
        }

    }

    /**
     * <p>
     * Action to flip an image along its horizontal axis.
     * </p>
     * 
     * @author Steven Simpson 
     */
    public class FlipVerticalAction extends ImageAction {

        /**
         * <p>
         * Create a new resize action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        FlipVerticalAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
            if(mnemonic != null)  putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(mnemonic, KeyEvent.CTRL_DOWN_MASK));
        }

        /**
         * <p>
         * Callback for when the flipvertical action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the FlipVerticalAction is triggered.
         * It flips the image.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {

            target.getImage().apply(new FlipVertical());
            target.repaint();
            target.getParent().revalidate();
        }

    }

    /**
     * <p>
     * Action to flip an image along its horizontal axis.
     * </p>
     * 
     * @author Steven Simpson 
     */
    public class FlipHorizontalAction extends ImageAction {

        /**
         * <p>
         * Create a new resize action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        FlipHorizontalAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
            if(mnemonic != null)  putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(mnemonic, KeyEvent.CTRL_DOWN_MASK));
        }

        /**
         * <p>
         * Callback for when the fliphorizontal action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the FlipHorizontalAction is triggered.
         * It flips the image.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {

            target.getImage().apply(new FlipHorizontal());
            target.repaint();
            target.getParent().revalidate();
        }

    }

    /**
     * <p>
     * Action to rotate an image left 90 degrees.
     * </p>
     * 
     * @author Steven Simpson 
     */
    public class RotateLeftAction extends ImageAction {

        /**
         * <p>
         * Create a new rotate left action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        RotateLeftAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
            if(mnemonic != null)  putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(mnemonic, KeyEvent.CTRL_DOWN_MASK));
        }

        /**
         * <p>
         * Callback for when the RotateLeft action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the RotateLeftAction is triggered.
         * It rotates the image left.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {

            target.getImage().apply(new RotateLeft());
            target.repaint();
            target.getParent().revalidate();
        }

    }

    /**
     * <p>
     * Action to rotate an image right 90 degrees.
     * </p>
     * 
     * @author Steven Simpson 
     */
    public class RotateRightAction extends ImageAction {

        /**
         * <p>
         * Create a new rotate right action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        RotateRightAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
            if(mnemonic != null)  putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(mnemonic, KeyEvent.CTRL_DOWN_MASK));
        }

        /**
         * <p>
         * Callback for when the RotateRight action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the RotateRightAction is triggered.
         * It rotates the image left.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {

            target.getImage().apply(new RotateRight());
            target.repaint();
            target.getParent().revalidate();
        }

    }

    /**
     * <p>
     * Action to rotate an image 180 degrees.
     * </p>
     * 
     * @author Steven Simpson 
     */
    public class Rotate180Action extends ImageAction {

        /**
         * <p>
         * Create a new rotate 180 degrees action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        Rotate180Action(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
            if(mnemonic != null)  putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(mnemonic, KeyEvent.CTRL_DOWN_MASK));
        }

        /**
         * <p>
         * Callback for when the Rotate180 action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the Rotate180Action is triggered.
         * It rotates the image left.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {

            target.getImage().apply(new Rotate180());
            target.repaint();
            target.getParent().revalidate();
        }

    }

     /**
      * <p>
      * Action to crop an image to a rectangular selection.
      * </p>
      *
      * @author Liam Walls
      */
    public class CropAction extends ImageAction{

         /**
          * <p>
          * Create a new crop action.
          * </p>
          *
          * @param name The name of the action (ignored if null).
          * @param icon An icon to use to represent the action (ignored if null).
          * @param desc A brief description of the action  (ignored if null).
          * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
          */
        CropAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
            if(mnemonic != null)  putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(mnemonic, KeyEvent.CTRL_DOWN_MASK));
        }

         /**
          * <p>
          * Callback for when the crop action is triggered.
          * </p>
          *
          * <p>
          * This method is called whenever the crop action is triggered.
          * </p>
          *
          * @param evt The event triggering this callback.
          */
        public void actionPerformed(ActionEvent evt){

            target.crop = true;
            //target.getImage().apply(new BrightnessContrast(-15, 0));
            target.active = true;
            target.repaint();
            target.getParent().revalidate();
        }
    }

     /**
      * <p>
      * Action to crop an image to an oval selection.
      * </p>
      *
      * @author Steven Simpson
      */
    public class OvalCropAction extends ImageAction{

         /**
          * <p>
          * Create a new oval crop action.
          * </p>
          *
          * @param name The name of the action (ignored if null).
          * @param icon An icon to use to represent the action (ignored if null).
          * @param desc A brief description of the action  (ignored if null).
          * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
          */
        OvalCropAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
            if(mnemonic != null)  putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(mnemonic, KeyEvent.CTRL_DOWN_MASK + KeyEvent.SHIFT_DOWN_MASK));
        }

         /**
          * <p>
          * Callback for when the oval crop action is triggered.
          * </p>
          *
          * <p>
          * This method is called whenever the oval crop action is triggered.
          * It rotates the image left.
          * </p>
          *
          * @param evt The event triggering this callback.
          */
        public void actionPerformed(ActionEvent evt){

            target.ovalcrop = true;
            //target.getImage().apply(new BrightnessContrast(-15, 0));
            target.active = true;
            target.repaint();
            target.getParent().revalidate();
        }
    }
}
