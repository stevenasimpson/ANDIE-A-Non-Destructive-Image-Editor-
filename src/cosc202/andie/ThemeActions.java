package cosc202.andie;

import java.util.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * <p>
 * Actions provided by the theme menu.
 * </p>
 * 
 * <p>
 * The theme menu contains actions that changes the background color of the
 * application.
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
public class ThemeActions {

    /** A list of actions for the Theme menu. */
    protected ArrayList<Action> actions;

    /**
     * <p>
     * Create a set of Theme menu actions.
     * </p>
     */
    public ThemeActions() {

        actions = new ArrayList<Action>();

        actions.add(new DefaultThemeAction(I18n.getKeyString("default"), null, I18n.getKeyString("defaultDesc"), null));
        actions.add(new BlackThemeAction(I18n.getKeyString("black"), null, I18n.getKeyString("blackDesc"), null));
        actions.add(new RedThemeAction(I18n.getKeyString("red"), null, I18n.getKeyString("redDesc"), null));
        actions.add(new YellowThemeAction(I18n.getKeyString("yellow"), null, I18n.getKeyString("yellowDesc"), null));
        actions.add(new OrangeThemeAction(I18n.getKeyString("orange"), null, I18n.getKeyString("orangeDesc"), null));
        actions.add(new GreenThemeAction(I18n.getKeyString("green"), null, I18n.getKeyString("greenDesc"), null));
        actions.add(new BlueThemeAction(I18n.getKeyString("blue"), null, I18n.getKeyString("blueDesc"), null));
        actions.add(new PurpleThemeAction(I18n.getKeyString("purple"), null, I18n.getKeyString("purpleDesc"), null));
        actions.add(new PinkThemeAction(I18n.getKeyString("pink"), null, I18n.getKeyString("pinkDesc"), null));
        actions.add(new CustomThemeAction(I18n.getKeyString("custom"), null, I18n.getKeyString("customDesc"), null));

    }

    /**
     * <p>
     * Create a menu containing the list of Theme actions.
     * </p>
     * 
     * @return The Theme menu UI element.
     */
    public JMenu createMenu() {

        JMenu fileMenu = new JMenu(I18n.getKeyString("themes"));

        for (Action action : actions) {
            fileMenu.add(new JMenuItem(action));
        }

        return fileMenu;
    }

    /**
     * <p>
     * Create a new DefaultTheme action.
     * </p>
     *
     */
    public class DefaultThemeAction extends ImageAction {

        /**
         * <p>
         * Create a new DefaultTheme action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        DefaultThemeAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the DefaultTheme action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the DefaultTheme is triggered.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {

            try {
                Themes.setDefault();

            } catch (Exception ex) {
                System.exit(1);
            }
        }
    }

    /**
     * <p>
     * Create a new BlackTheme action.
     * </p>
     */
    public class BlackThemeAction extends ImageAction {

        /**
         * <p>
         * Create a new BlackTheme action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        BlackThemeAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the BlackTheme action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the BlackTheme is triggered.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {

            try {
                Themes.setBlack();

            } catch (Exception ex) {
                System.exit(1);
            }
        }
    }

    /**
     * <p>
     * Create a new RedTheme action.
     * </p>
     */
    public class RedThemeAction extends ImageAction {

        /**
         * <p>
         * Create a new RedTheme action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        RedThemeAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the RedTheme action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the RedTheme is triggered.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {

            try {
                Themes.setRed();

            } catch (Exception ex) {
                System.exit(1);
            }
        }
    }

    /**
     * <p>
     * Create a new OrangeTheme action.
     * </p>
     */
    public class OrangeThemeAction extends ImageAction {

        /**
         * <p>
         * Create a new OrangeTheme action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        OrangeThemeAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the OrangeTheme action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the OrangeTheme is triggered.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {

            try {
                Themes.setOrange();

            } catch (Exception ex) {
                System.exit(1);
            }
        }
    }

    /**
     * <p>
     * Create a new YellowTheme action.
     * </p>
     */
    public class YellowThemeAction extends ImageAction {

        /**
         * <p>
         * Create a new YellowTheme action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        YellowThemeAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the YellowTheme action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the YellowTheme is triggered.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {

            try {
                Themes.setYellow();

            } catch (Exception ex) {
                System.exit(1);
            }
        }
    }

    /**
     * <p>
     * Create a new GreenTheme action.
     * </p>
     *
     */
    public class GreenThemeAction extends ImageAction {

        /**
         * <p>
         * Create a new GreenTheme action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        GreenThemeAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the GreenTheme action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the GreenTheme is triggered.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {

            try {
                Themes.setGreen();

            } catch (Exception ex) {
                System.exit(1);
            }
        }
    }

    /**
     * <p>
     * Create a new BlueTheme action.
     * </p>
     */
    public class BlueThemeAction extends ImageAction {

        /**
         * <p>
         * Create a new BlueTheme action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        BlueThemeAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the BlueTheme action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the BlueTheme is triggered.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {

            try {
                Themes.setBlue();

            } catch (Exception ex) {
                System.exit(1);
            }
        }
    }

    /**
     * <p>
     * Create a new PurpleTheme action.
     * </p>
     */
    public class PurpleThemeAction extends ImageAction {

        /**
         * <p>
         * Create a new PurpleTheme action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        PurpleThemeAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the PurpleTheme action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the PurpleTheme is triggered.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {

            try {
                Themes.setPurple();

            } catch (Exception ex) {
                System.exit(1);
            }
        }
    }

    /**
     * <p>
     * Create a new PinkTheme action.
     * </p>
     */
    public class PinkThemeAction extends ImageAction {

        /**
         * <p>
         * Create a new PinkTheme action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        PinkThemeAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the PinkTheme action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the PinkTheme is triggered.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {

            try {
                Themes.setPink();

            } catch (Exception ex) {
                System.exit(1);
            }
        }
    }

    /**
     * <p>
     * Create a new CustomTheme action.
     * </p>
     */
    public class CustomThemeAction extends ImageAction {

        /**
         * <p>
         * Create a new CustomTheme action.
         * </p>
         * 
         * @param name     The name of the action (ignored if null).
         * @param icon     An icon to use to represent the action (ignored if null).
         * @param desc     A brief description of the action (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut (ignored if null).
         */
        CustomThemeAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
        }

        /**
         * <p>
         * Callback for when the CustomTheme action is triggered.
         * </p>
         * 
         * <p>
         * This method is called whenever the CustomTheme is triggered.
         * </p>
         * 
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {

            try {
                Themes.setCustom();

            } catch (Exception ex) {
                System.exit(1);
            }
        }
    }
}
