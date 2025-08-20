package cosc202.andie;

import java.awt.BorderLayout;
import java.awt.Image;
import java.util.ArrayList;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;

/**
 * <p>
 * Main class for A Non-Destructive Image Editor (ANDIE).
 * </p>
 * 
 * <p>
 * This class is the entry point for the program.
 * It creates a Graphical User Interface (GUI) that provides access to various image editing and processing operations.
 * </p>
 * 
 * <p>
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 * 
 * <p>
 * All Toolbar Icons used under free license from Google Fonts.
 * https://fonts.google.com/icons
 * https://www.apache.org/licenses/LICENSE-2.0.html
 * </p>
 * 
 * @author Steven Mills
 * @version 1.0
 *
 * 
 */
public class Andie{

    /**
     * <p>
     * Constructs a new Andie.
     * </p>
     */
    public Andie(){}

    private static JToolBar toolBar;

    public static JFrame frame;
    public static ImagePanel imagePanel;

    /**
     * <p>
     * Launches the main GUI for the ANDIE program.
     * </p>
     * 
     * <p>
     * This method sets up an interface consisting of an active image (an {@code ImagePanel})
     * and various menus which can be used to trigger operations to load, save, edit, etc. 
     * These operations are implemented {@link ImageOperation}s and triggered via
     * {@code ImageAction}s grouped by their general purpose into menus.
     * </p>
     * 
     * @see ImagePanel
     * @see ImageAction
     * @see ImageOperation
     * @see FileActions
     * @see EditActions
     * @see ViewActions
     * @see FilterActions
     * @see ColourActions
     * 
     * @throws Exception if something goes wrong.
     */
    private static void createAndShowGUI() throws Exception {

        // Set up the main GUI frame
        frame = new JFrame("ANDIE");

        frame.setBackground(Themes.getBackground());

        Image image = ImageIO.read(Andie.class.getClassLoader().getResource("icon.png"));
        frame.setIconImage(image);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // The main content area is an ImagePanel
        imagePanel = new ImagePanel();

        imagePanel.setBackground(Themes.getBackground());

        ImageAction.setTarget(imagePanel);
        JScrollPane scrollPane = new JScrollPane(imagePanel);
        frame.add(scrollPane, BorderLayout.CENTER);

        //Added closing dialog box for saving
        frame.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent closingEvent){
                int dialogButton = JOptionPane.YES_NO_OPTION;
                int dialogOption = JOptionPane.showConfirmDialog(null, I18n.getKeyString("saveCurrEdits"), I18n.getKeyString("confirmEx"), dialogButton);

                if(dialogOption == JOptionPane.YES_OPTION){
                    try {
                        imagePanel.getImage().save();
                    } catch (Exception ex) {
                        System.exit(1);
                    }
                    System.exit(0);
                }
                else {
                    System.exit(0);
                }
            }
        });

        // Add in menus for various types of action the user may perform.
        JMenuBar menuBar = new JMenuBar();

        //Create a JToolBar for buttons to be added onto
        toolBar = new JToolBar(); 
        frame.add(toolBar, BorderLayout.NORTH);//adds toolbar to the frame

        // File menus are pretty standard, so things that usually go in File menus go here.
        FileActions fileActions = new FileActions();
        menuBar.add(fileActions.createMenu());
        addButtons(fileActions.createButtons());

        // Likewise Edit menus are very common, so should be clear what might go here.
        EditActions editActions = new EditActions();
        menuBar.add(editActions.createMenu());
        addButtons(editActions.createButtons());

        // View actions control how the image is displayed, but do not alter its actual content
        ViewActions viewActions = new ViewActions();
        menuBar.add(viewActions.createMenu());
        addButtons(viewActions.createButtons());

        // Filters apply a per-pixel operation to the image, generally based on a local window
        FilterActions filterActions = new FilterActions();
        menuBar.add(filterActions.createMenu());
        addButtons(filterActions.createButtons());

        // Actions that affect the representation of colour in the image
        ColourActions colourActions = new ColourActions();
        menuBar.add(colourActions.createMenu());
        addButtons(colourActions.createButtons());

        // Actions that allow the user to specify and use macros
        MacroActions macroActions = new MacroActions();
        menuBar.add(macroActions.createMenu());
        addButtons(macroActions.createButtons());

        // Actions that change the language of the program
        LanguageActions languageActions = new LanguageActions();
        menuBar.add(languageActions.createMenu());

        // Actions that change the appearance of the editor
        ThemeActions themeActions = new ThemeActions();
        menuBar.add(themeActions.createMenu());

        // Actions that are just for fun
        FunActions funActions = new FunActions();
        menuBar.add(funActions.createMenu());
        
        frame.setJMenuBar(menuBar);
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * <p>
     * Adds buttons to the toolbar.
     * </p>
     * @param buttons An arraylist of buttons to add to the toolbar
     */
    public static void addButtons(ArrayList<JButton> buttons){
        
        if (!buttons.isEmpty()){
            for(JButton button: buttons){
                toolBar.add(button);
            }
        }
    }



    /**
     * <p>
     * Main entry point to the ANDIE program.
     * </p>
     * 
     * <p>
     * Creates and launches the main GUI in a separate thread.
     * As a result, this is essentially a wrapper around {@code createAndShowGUI()}.
     * </p>
     * 
     * @param args Command line arguments, not currently used
     * @throws Exception If something goes awry
     * @see #createAndShowGUI()
     */
    public static void main(String[] args) throws Exception {

        I18n.setDefaultLocale();

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    createAndShowGUI();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    System.exit(1);
                }
            }
        });
    }
}
