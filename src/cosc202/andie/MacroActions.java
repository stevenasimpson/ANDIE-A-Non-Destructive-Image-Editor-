package cosc202.andie;

import java.util.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * <p>
 * Actions provided by the Macro menu.
 * </p>
 * 
 * <p>
 * The Macro menu provides actions for opening and saving macros which can be applied to any image.
 * </p>
 * 
 * @author Steven Simpson
 * @version 1.0
 */
public class MacroActions {
    
    /** A list of actions for the Macro menu. */
    protected ArrayList<Action> actions;

    /**
     * <p>
     * Create a set of Macro menu actions.
     * </p>
     */
    public MacroActions() {

        actions = new ArrayList<Action>();
        actions.add(new MacroOpenAction(I18n.getKeyString("macroOpen"), new ImageIcon(this.getClass().getClassLoader().getResource("macroOpen.png")), I18n.getKeyString("macroOpenDesc"), Integer.valueOf(KeyEvent.VK_O)));
        actions.add(new MacroStartAction(I18n.getKeyString("macroStart"), new ImageIcon(this.getClass().getClassLoader().getResource("macroStart.png")), I18n.getKeyString("macroStartDesc"), Integer.valueOf(KeyEvent.VK_S)));
        actions.add(new MacroStopAction(I18n.getKeyString("macroStop"), new ImageIcon(this.getClass().getClassLoader().getResource("macroStop.png")), I18n.getKeyString("macroStopDesc"), Integer.valueOf(KeyEvent.VK_Q)));
        
    }

    /**
     * <p>
     * Create a menu containing the list of Macro actions.
     * </p>
     * 
     * @return The Macro menu UI element.
     */
    public JMenu createMenu() {

        JMenu fileMenu = new JMenu(I18n.getKeyString("macro"));

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
        options.add(0);//option for start recording
        options.add(1);//option for stop recording / save
        options.add(2);//option for open macro
        
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
     * Action to open a macro from file.
     * </p>
     * 
     * @see EditableImage#openMacro(String)
     */
    public class MacroOpenAction extends ImageAction {

        /**
         * <p>
         * Create a new macro-open action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        MacroOpenAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
            putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(mnemonic, KeyEvent.CTRL_DOWN_MASK + KeyEvent.SHIFT_DOWN_MASK));
        }

        /**
         * <p>
         * Callback for when the maco-open action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the MacroOpenAction is triggered.
         * It prompts the user to select a file and opens it as a macro to apply to an image.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(target);

            if (result == JFileChooser.APPROVE_OPTION) {
                try {
                    String filepath = fileChooser.getSelectedFile().getCanonicalPath();
                    target.getImage().openMacro(filepath);
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
     * Action to start recording actions to a macro.
     * </p>
     * 
     * @see EditableImage apply()
     */
    public class MacroStartAction extends ImageAction {

        /**
         * <p>
         * Create a new macro-start action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        MacroStartAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
            putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(mnemonic, KeyEvent.CTRL_DOWN_MASK + KeyEvent.SHIFT_DOWN_MASK));
        }

        /**
         * <p>
         * Callback for when the macro-start action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the MacroStartAction is triggered.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            try {
                target.getImage().setRecording(true);      
                JOptionPane.showConfirmDialog(null, I18n.getKeyString("macroStart"), I18n.getKeyString("recordingStart"), JOptionPane.DEFAULT_OPTION);     
            } catch (Exception ex) {
                System.exit(1);
            }
        }
    }

    /**
     * <p>
     * Action to stop recording actions and save the operations as a macro.
     * </p>
     * 
     * @see EditableImage - apply()
     */
    public class MacroStopAction extends ImageAction {

        /**
         * <p>
         * Create a new macro-save action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        MacroStopAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
            putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(mnemonic, KeyEvent.CTRL_DOWN_MASK + KeyEvent.SHIFT_DOWN_MASK));
        }

        /**
         * <p>
         * Callback for when the macro-save action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the MacroSaveAction is triggered.
         * It saves the macro to a filepath.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            try {
                target.getImage().setRecording(false);
                
                int dialogButton = JOptionPane.YES_NO_OPTION;
                int dialogOption = JOptionPane.showConfirmDialog(null, I18n.getKeyString("marcoRecStop"), I18n.getKeyString("recordingStop"), dialogButton);

                if (dialogOption == JOptionPane.YES_OPTION) {
                    JFileChooser fileChooser = new JFileChooser();
                    int result = fileChooser.showSaveDialog(target);

                    if (result == JFileChooser.APPROVE_OPTION) {
                        try {
                            String imageFilepath = fileChooser.getSelectedFile().getCanonicalPath();
                            target.getImage().saveMacro(imageFilepath);
                        } catch (Exception ex) {
                            System.exit(1);
                        }
                    }   
                }

            } catch (Exception ex) {
                System.exit(1);
            }
        }
    }

}
