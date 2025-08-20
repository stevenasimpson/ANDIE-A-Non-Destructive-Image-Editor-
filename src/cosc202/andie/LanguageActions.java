package cosc202.andie;

import java.util.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

/**
 * <p>
 * Actions provided by the Language menu.
 * </p>
 * 
 * <p>
 * The Language menu will offer different
 * languages for the user to change into.
 * </p>
 * 
 * @author Ashwin Ellis
 * @version 1.0
 */
public class LanguageActions{
    
    /** A list of actions for the Language menu. */
    protected ArrayList<Action> actions;

    /**
     * <p>
     * Create a set of Language menu actions.
     * </p>
     */
    public LanguageActions() {

        actions = new ArrayList<Action>();
        actions.add(new LanguageEnglishAction("English", null, I18n.getKeyString("englishLangDesc"), null));
        actions.add(new LanguageMaoriAction("Maori", null, I18n.getKeyString("maoriLangDesc"), null));
    }

    /**
     * <p>
     * Create a menu containing the list of Language actions.
     * </p>
     * 
     * @return The language menu UI element.
     */
    public JMenu createMenu() {
        

        JMenu fileMenu = new JMenu(I18n.getKeyString("lang"));

        for(Action action: actions) {
            fileMenu.add(new JMenuItem(action));
        }

        return fileMenu;
    }

    /**
     * <p>
     * Create a new LanguageEnglish action.
     * </p>
     *
     */
    public class LanguageEnglishAction extends ImageAction{

        /**
         * <p>
         * Create a new language action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        LanguageEnglishAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }
    
        /**
         * <p>
         * Callback for when the LanguageEnglish action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the LanguageEnglish is triggered.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {

            try {
                I18n.setEnglishLocale();
                JOptionPane.showConfirmDialog(null, "Restart to confirm changes.", "", JOptionPane.DEFAULT_OPTION);

            } catch (Exception ex) {
                System.exit(1);
            }
                
        }
    }

    /**
    * <p>
    * Create a new LanguageMaori action.
    * </p>
    *
    */
    public class LanguageMaoriAction extends ImageAction{

        /**
        * <p>
         * Create a new fLanguageMaori action.
         * </p>
         * 
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        LanguageMaoriAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }
    
        /**
        * <p>
         * Callback for when the LanguageMaori action is triggered.
         * </p>
         *              
         * <p>
         * This method is called whenever the LanguageMaoriAction is triggered.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {

            try {
                I18n.setMaoriLocale();
                JOptionPane.showConfirmDialog(null, "Timata ano ki te whakau i nga huringa.", "", JOptionPane.DEFAULT_OPTION);
               
            } catch (Exception ex) {
                System.exit(1);
            }
        }
    }        
}
