package cosc202.andie;

import java.util.*;
import java.awt.event.*;
import javax.swing.*;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

/**
 * <p>
 * Actions provided by the File menu.
 * </p>
 * 
 * <p>
 * The File menu is very common across applications, 
 * and there are several items that the user will expect to find here.
 * Opening and saving files is an obvious one, but also exiting the program.
 * </p>
 * 
 * <p> 
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 * 
 * @author Steven Mills
 * @version 1.0
 */
public class FileActions {
    
    /** A list of actions for the File menu. */
    protected ArrayList<Action> actions;

    /**
     * <p>
     * Create a set of File menu actions.
     * </p>
     */
    public FileActions() {

        actions = new ArrayList<Action>();
        actions.add(new FileOpenAction(I18n.getKeyString("open"), new ImageIcon(this.getClass().getClassLoader().getResource("open.png")), I18n.getKeyString("openDesc"), Integer.valueOf(KeyEvent.VK_O)));
        actions.add(new FileSaveAction(I18n.getKeyString("save"), new ImageIcon(this.getClass().getClassLoader().getResource("save.png")), I18n.getKeyString("saveDesc"), Integer.valueOf(KeyEvent.VK_S)));
        actions.add(new FileSaveAsAction(I18n.getKeyString("saveAs"), new ImageIcon(this.getClass().getClassLoader().getResource("saveAs.png")), I18n.getKeyString("saveAsDesc"), Integer.valueOf(KeyEvent.VK_A)));
        actions.add(new FileExitAction(I18n.getKeyString("exit"), new ImageIcon(this.getClass().getClassLoader().getResource("exit.png")), I18n.getKeyString("exitDesc"), Integer.valueOf(KeyEvent.VK_W)));
        actions.add(new FileExportAction(I18n.getKeyString("export"), new ImageIcon(this.getClass().getClassLoader().getResource("export.png")), I18n.getKeyString("exportDesc"), Integer.valueOf(KeyEvent.VK_E))); 
    }

    /**
     * <p>
     * Create a menu containing the list of File actions.
     * </p>
     * 
     * @return The File menu UI element.
     */
    public JMenu createMenu() {

        JMenu fileMenu = new JMenu(I18n.getKeyString("file"));

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

        ArrayList<Integer> options = new ArrayList<Integer>(); //array of options for the buttons
        options.add(0);//option for open
        options.add(1);//option for save
        options.add(2);//option for save as
        options.add(3);//option for exit
        options.add(4);//option for export
        
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
     * Action to open an image from file.
     * </p>
     * 
     * @see EditableImage#open(String)
     */
    public class FileOpenAction extends ImageAction {

        /**
         * <p>
         * Create a new file-open action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        FileOpenAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
            putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(mnemonic, KeyEvent.CTRL_DOWN_MASK));
        }

        /**
         * <p>
         * Callback for when the file-open action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the FileOpenAction is triggered.
         * It prompts the user to select a file and opens it as an image.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            //Added dialog box for saving prior to opening a new image
            if (target.getImage().getCurrentImage() != null) {

                int dialogButton = JOptionPane.YES_NO_OPTION;
                int dialogOption = JOptionPane.showConfirmDialog(null, I18n.getKeyString("saveCurrEdits"), I18n.getKeyString("confirmCloseCurrImage"), dialogButton);

                if(dialogOption == JOptionPane.YES_OPTION){
                    try {
                        target.getImage().save();
                        JOptionPane.showMessageDialog(target, I18n.getKeyString("fileSaveSuccess"), I18n.getKeyString("saveSuccess"), JOptionPane.INFORMATION_MESSAGE);

                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(target, (I18n.getKeyString("errorSave") + ex), I18n.getKeyString("error"), JOptionPane.ERROR_MESSAGE);
                    }
                }
            }

            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(target);

            if (result == JFileChooser.APPROVE_OPTION) {
                try {
                    String imageFilepath = fileChooser.getSelectedFile().getCanonicalPath();
                    target.getImage().open(imageFilepath);
                } catch (Exception ex) {
                    System.exit(1);
                }
            }

            target.repaint();
            target.getParent().revalidate();
        }

    }

    /**
     * <p>
     * Action to save an image to its current file location.
     * </p>
     * 
     * @see EditableImage#save()
     */
    public class FileSaveAction extends ImageAction {

        /**
         * <p>
         * Create a new file-save action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        FileSaveAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
            putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(mnemonic, KeyEvent.CTRL_DOWN_MASK));
        }

        /**
         * <p>
         * Callback for when the file-save action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the FileSaveAction is triggered.
         * It saves the image to its original filepath.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            try {
                target.getImage().save();
                JOptionPane.showMessageDialog(target, I18n.getKeyString("fileSaveSuccess"), I18n.getKeyString("saveSuccess"), JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(target, (I18n.getKeyString("errorSave") + ex), I18n.getKeyString("error"), JOptionPane.ERROR_MESSAGE);
            }
        }

    }

    /**
     * <p>
     * Action to save an image to a new file location.
     * </p>
     * 
     * @see EditableImage#saveAs(String)
     */
    public class FileSaveAsAction extends ImageAction {

        /**
         * <p>
         * Create a new file-save-as action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        FileSaveAsAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
            putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(mnemonic, KeyEvent.CTRL_DOWN_MASK));
        }

         /**
         * <p>
         * Callback for when the file-save-as action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the FileSaveAsAction is triggered.
         * It prompts the user to select a file and saves the image to it.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showSaveDialog(target);

            if (result == JFileChooser.APPROVE_OPTION) {
                try {
                    String imageFilepath = fileChooser.getSelectedFile().getCanonicalPath();
                    target.getImage().saveAs(imageFilepath);
                    JOptionPane.showMessageDialog(target, "File saved successfully", "Save-As Successful", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    System.exit(1);
                }
            }
        }

    }

    /**
     * <p>
     * Action to quit the ANDIE application.
     * </p>
     */
    public class FileExitAction extends ImageAction {

        /**
         * <p>
         * Create a new file-exit action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        FileExitAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
            putValue(SHORT_DESCRIPTION, desc);
            putValue(MNEMONIC_KEY, mnemonic);
            if(mnemonic != null) putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(mnemonic, KeyEvent.CTRL_DOWN_MASK));
        }

        /**
         * <p>
         * Callback for when the file-exit action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the FileExitAction is triggered.
         * It quits the program.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            
            int dialogButton = JOptionPane.YES_NO_OPTION;
            int dialogOption = JOptionPane.showConfirmDialog(null, I18n.getKeyString("saveCurrEdits"), I18n.getKeyString("confirmEx"), dialogButton);

            if(dialogOption == JOptionPane.YES_OPTION){
                try {
                    target.getImage().save();
    
                } catch (Exception ex) {
                    System.exit(1);
                }
                System.exit(0);
            }
            else {
                System.exit(0);
            }

            
        }

    }

    /**
     * <p>
     * Action to export an image with its changes permanently incorporated.
     * </p>
     */
    public class FileExportAction extends ImageAction {

        /**
         * <p>
         * Create a new file-export action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        public FileExportAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
            if(mnemonic != null) putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(mnemonic, KeyEvent.CTRL_DOWN_MASK));
        } //this is a constructor to initialize the four keys, using super from imageAction

        @Override
        public void actionPerformed(ActionEvent e) { //called when action is triggered, i.e. when clicked, 
            JFileChooser fileChooser = new JFileChooser();//creates jFilechooser, 
            int result = fileChooser.showSaveDialog(target);//the result if user clicks save
    
            if (result == JFileChooser.APPROVE_OPTION) {//if user clicks save than export in try block
                try {
                    String imageFilepath = fileChooser.getSelectedFile().getCanonicalPath(); // retrieves the selected file's path and gets the current image from the target (an ImagePanel).
                    BufferedImage image = target.getImage().getCurrentImage(); 
                    File outputFile = new File(imageFilepath + ".png"); //  creates a File object representing the output file, appending ".png" to the file path to ensure that the image is exported as a PNG file.
                    ImageIO.write(image, "png", outputFile); //writes the image data to the output file using ImageIO.write, exporting it as a PNG file.
                } catch (Exception ex) {
                    ex.printStackTrace(); // catches the exception and prints the stack trace for debugging purposes. 
                }
            }
        }


    }
}
