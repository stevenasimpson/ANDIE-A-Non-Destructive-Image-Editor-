package cosc202.andie;

import javax.swing.JOptionPane;
import java.awt.Color;
import java.util.prefs.Preferences;
import javax.swing.JColorChooser;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import javax.swing.JPanel;

/**
 * <p>
 * Actions provided by the theme menu.
 * </p>
 * 
 * <p>
 * The theme menu contains actions that changes the background color of the application.
 * </p>
 * 
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 * 
 * @author Ashwin Ellis
 * @version 1.0
 *
 * 
 */
public class Themes {

    /** Datafield for preferences object */
    private static Preferences prefs = Preferences.userRoot().node("Andie.ColorPreferences");; 

    /** Datafield for Color objects */
    private static Color white = Color.WHITE;
    private static Color black = Color.BLACK;
    private static Color red = Color.RED;
    private static Color yellow = Color.YELLOW;
    private static Color orange = Color.ORANGE;
    private static Color green = Color.GREEN;
    private static Color blue = Color.BLUE;
    private static Color purple = new Color(128, 0, 128);
    private static Color pink = Color.PINK;

    /**
     * Private constructor to hide the implicit public one.
     */
    private Themes() {
        // This constructor is intentionally left blank.
    }

    /**
     * <p>
     * Store the background color in the preferences.
     * </p>
     * 
     * @param background The color to be stored.
     */
    public static void storeBackground(Color background) {
        prefs.putInt("background", background.getRGB());
    }

    /**
     * <p>
     * Get the background color from the preferences.
     * </p>
     * 
     * @return The background color.
     */
    public static Color getBackground() {
        int color = prefs.getInt("background", Color.WHITE.getRGB()); 
        return new Color(color);
    }

    /**
     * <p>
     * Set the default background color.
     * </p>
     */
    public static void setDefault(){
        storeBackground(white);
        JOptionPane.showConfirmDialog(null, I18n.getKeyString("restart"), "", JOptionPane.DEFAULT_OPTION);
    }

    /**
     * <p>
     * Set the black background color.
     * </p>
     */
    public static void setBlack(){
        storeBackground(black);
        JOptionPane.showConfirmDialog(null, I18n.getKeyString("restart"), "", JOptionPane.DEFAULT_OPTION);
    }

    /**
     * <p>
     * Set the red background color.
     * </p>
     */
    public static void setRed(){
        storeBackground(red);
        JOptionPane.showConfirmDialog(null, I18n.getKeyString("restart"), "", JOptionPane.DEFAULT_OPTION);
    }

    /**
     * <p>
     * Set the orange background color.
     * </p>
     */
    public static void setOrange(){
        storeBackground(orange);
        JOptionPane.showConfirmDialog(null, I18n.getKeyString("restart"), "", JOptionPane.DEFAULT_OPTION);
    }

    /**
     * <p>
     * Set the yellow background color.
     * </p>
     */
    public static void setYellow(){
        storeBackground(yellow);
        JOptionPane.showConfirmDialog(null, I18n.getKeyString("restart"), "", JOptionPane.DEFAULT_OPTION);

    }
    
    /**
     * <p>
     * Set the green background color.
     * </p>
     */
    public static void setGreen(){
        storeBackground(green);
        JOptionPane.showConfirmDialog(null, I18n.getKeyString("restart"), "", JOptionPane.DEFAULT_OPTION);
    }
    
    /**
     * <p>
     * Set the blue background color.
     * </p>
     */
    public static void setBlue(){
        storeBackground(blue);
        JOptionPane.showConfirmDialog(null, I18n.getKeyString("restart"), "", JOptionPane.DEFAULT_OPTION);
    }

    /**
     * <p>
     * Set the purple background color.
     * </p>
     */
    public static void setPurple(){
        storeBackground(purple);
        JOptionPane.showConfirmDialog(null, I18n.getKeyString("restart"), "", JOptionPane.DEFAULT_OPTION);
    }

    /**
     * <p>
     * Set the pink background color.
     * </p>
     */
    public static void setPink(){
        storeBackground(pink);
        JOptionPane.showConfirmDialog(null, I18n.getKeyString("restart"), "", JOptionPane.DEFAULT_OPTION);
    }

    /**
     * <p>
     *  Set the black theme for the application.
     * </p>
     */
    public static void setCustom(){
        Color custom = Color.BLACK;

        JPanel panel = new JPanel();
    
        JColorChooser colorChooser = new JColorChooser();

        AbstractColorChooserPanel[] panels = colorChooser.getChooserPanels();
        for(AbstractColorChooserPanel accp : panels){
            if(!accp.getDisplayName().equals("Swatches")){
                colorChooser.removeChooserPanel(accp);
            }
        }

        colorChooser.setPreviewPanel(new JPanel());

        panel.add(colorChooser);

        JOptionPane.showConfirmDialog(null, panel, I18n.getKeyString("choseAColour"), JOptionPane.DEFAULT_OPTION);

        storeBackground(colorChooser.getColor());
        JOptionPane.showConfirmDialog(null, I18n.getKeyString("restart"), "", JOptionPane.DEFAULT_OPTION);
    }

}

