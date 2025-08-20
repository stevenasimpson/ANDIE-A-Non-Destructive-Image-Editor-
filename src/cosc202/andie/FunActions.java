package cosc202.andie;
import java.awt.Desktop;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
 


/**
 * <p>
 * Actions represented in the FunActions class.
 * </p>
 * 
 * <p>
 * The FunActions class represents a collection of fun actions that can be performed on an image.
 * It provides methods to create a menu and buttons for these actions.
 * </p>
 */
public class FunActions {
    
    protected ArrayList<Action> actions;

    /**
     * <p>
     * Constructs a new FunActions object.
     * </p>
     */
    public FunActions() {
        actions = new ArrayList<Action>();
        actions.add(new NoiseAction(I18n.getKeyString("noise"), null, I18n.getKeyString("noiseDesc"), KeyEvent.VK_N));
        actions.add(new LuckyAction(I18n.getKeyString("lucky"), null, I18n.getKeyString("luckyDesc"), KeyEvent.VK_L));
        actions.add(new Photo(I18n.getKeyString("easterEgg"), null, I18n.getKeyString("easterEggDesc"), null));
        actions.add(new Funnybutton(I18n.getKeyString("funnyButton"), null, I18n.getKeyString("funnyButtonDesc"), null));
    }

    /**
     * <p>
     * Creates the menu for fun actions
     * </p>
     *
     * @return A menu of fun actions
     */
    public JMenu createMenu() {
        JMenu menu = new JMenu(I18n.getKeyString("fun"));
        for (Action action : actions) {
            JMenuItem item = new JMenuItem(action);
            menu.add(item);
        }
        return menu;
    }

    /**
     * <p>
     * Creates the buttons for fun actions
     * </p>
     *
     * @return A list of buttons for fun actions
     */
    public ArrayList<JButton> createButtons() {

        ArrayList<JButton> buttons = new ArrayList<JButton>();
        JButton button = new JButton();

        ArrayList<Integer> options = new ArrayList<Integer>();
        options.add(0);

        for(int option = 0; option< actions.size(); option++){
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
     * An action that creates a completely randomized image.
     * </p>
     */
    public class NoiseAction extends ImageAction{

        /**
         * <p>
         * Creates a new noise action.
         * </p>
         *
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        NoiseAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
            if(mnemonic != null){
                putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(mnemonic, KeyEvent.CTRL_DOWN_MASK));
            }
        }

        /**
         * <p>
         * Callback for when the NoiseAction action is triggered.
         * </p>
         *
         * <p>
         * This method is called whenever the NoiseAction is triggered.
         * </p>
         *
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            target.getImage().apply(new RandomNoise());
            target.repaint();
            target.getParent().revalidate();
        }

    }

    /**
     * <p>
     * An action that applies a random number of random filters to the image.
     * </p>
     */
    public class LuckyAction extends ImageAction{

        /**
         * <p>
         * Creates a new lucky action.
         * </p>
         *
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        LuckyAction(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
            if(mnemonic != null){
                putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(mnemonic, KeyEvent.CTRL_DOWN_MASK + KeyEvent.SHIFT_DOWN_MASK));
            }
        }

        /**
         * <p>
         * Callback for when the LuckyAction action is triggered.
         * </p>
         *
         * <p>
         * This method is called whenever the LuckyAction is triggered.
         * </p>
         *
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
  
            Random outer = new Random();
            int x = outer.nextInt(7) + 3;
            //System.out.println("why " + x);
            Random rand = new Random();
            for(int i = 0; i < x; i++){
                int filter = rand.nextInt(22);
    
                switch (filter) {
                    case 1:
                        int wid = rand.nextInt(target.getImage().getCurrentImage().getWidth());
                        int hei = rand.nextInt(target.getImage().getCurrentImage().getHeight());
                        Point s = new Point(wid, hei);
                        wid = rand.nextInt(0-target.getImage().getCurrentImage().getWidth());
                        hei = rand.nextInt(0-target.getImage().getCurrentImage().getHeight());
                        Point t = new Point(wid, hei);
                        target.getImage().apply(new Crop(s, t));
                    case 2:
                        target.getImage().apply(new FlipVertical());
                    case 3:
                        target.getImage().apply(new FlipHorizontal());
                    case 4:
                        target.getImage().apply(new RotateLeft());
                    case 5:
                        target.getImage().apply(new RotateRight());
                    case 6:
                        target.getImage().apply(new Rotate180());
                    case 7:
                        target.getImage().apply(new MeanFilter());
                    case 8:
                        target.getImage().apply(new MedianFilter());
                    case 9:
                        target.getImage().apply(new GaussianFilter());
                    case 10:
                        target.getImage().apply(new SharpenFilter());
                    case 11:
                        target.getImage().apply(new SoftBlur());
                    case 12:
                        target.getImage().apply(new EmbossFilter(new float[]{0, 0, 0, 0, 0, -1, 0, 1, 0}));
                    case 13:
                        target.getImage().apply(new SobelFilter(enabled));
                    case 14:
                        target.getImage().apply(new RandomScatter(rand.nextInt(0-10)));
                    case 15:
                        target.getImage().apply(new BlockAveraging(rand.nextInt(1-target.getImage().getCurrentImage().getHeight()), 1-target.getImage().getCurrentImage().getWidth()));
                    case 16:
                        target.getImage().apply(new ConvertToGrey());
                    case 17:
                        target.getImage().apply(new ColourChannelCycle(1));
                    case 18:
                        target.getImage().apply(new ColourChannelCycle(2));
                    case 19:
                        target.getImage().apply(new BrightnessContrast(rand.nextInt(0-100), 0));
                    case 20:
                        target.getImage().apply(new BrightnessContrast(0, rand.nextInt(0-100)));
                    case 21:
                        target.getImage().apply(new RandomNoise());
                    case 22:
                        int widt = rand.nextInt(target.getImage().getCurrentImage().getWidth());
                        int heig = rand.nextInt(target.getImage().getCurrentImage().getHeight());
                        Point v = new Point(widt, heig);
                        widt = rand.nextInt(target.getImage().getCurrentImage().getWidth());
                        heig = rand.nextInt(target.getImage().getCurrentImage().getHeight());
                        Point w = new Point(widt, heig);
                        target.getImage().apply(new OvalCrop(v, w));
                }
                try {
                    Thread.sleep(500); // Delay for half a second (500 milliseconds)
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
            }
            target.repaint();
            target.getParent().revalidate();
        }
    }

    /**
     * <p>
     * An action that opens an image in the respository.
     * </p>
     */
    public class Photo extends ImageAction {

        /**
         * <p>
         * Creates a new load image action.
         * </p>
         *
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        Photo(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon, desc, mnemonic);
            if (mnemonic != null) {
                putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(mnemonic, KeyEvent.CTRL_DOWN_MASK));
            }
        }
        /**
         * <p>
         * Callback for when the Photo action is triggered.
         * </p>
         *
         * <p>
         * This method is called whenever the Easter egg button is triggered.
         * </p>
         *
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            try {
                String imageFilepath = "src/Easter.png";
                target.getImage().open(imageFilepath);
                target.repaint();
                target.getParent().revalidate();
            } catch (Exception ex) {
                System.exit(1);
            }
        }
    }

    /**
     * <p>
     * An action that open a URL to youtube to play a video
     * </p>
     */
     public class Funnybutton extends AbstractAction {
         /**
         * <p>
         * Opens a URL to youtube to play a video
         * </p>
         *
         * @param name The name of the action (ignored if null).
         * @param icon An icon to use to represent the action (ignored if null).
         * @param desc A brief description of the action  (ignored if null).
         * @param mnemonic A mnemonic key to use as a shortcut  (ignored if null).
         */
        Funnybutton(String name, ImageIcon icon, String desc, Integer mnemonic) {
            super(name, icon);
            putValue(SHORT_DESCRIPTION, desc);
            putValue(MNEMONIC_KEY, mnemonic);
            if (mnemonic != null) {
                putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(mnemonic, KeyEvent.CTRL_DOWN_MASK));
            }
        }
        /**
         * <p>
         * Callback for when the Funnybutton action is triggered.
         * </p>
         *
         * <p>
         * This method is called whenever the Funnybutton is triggered.
         * </p>
         *
         * @param e The event triggering this callback.
         */
        public void actionPerformed(ActionEvent e) {
            try {
                URI uri = new URI("https://www.youtube.com/watch?v=dQw4w9WgXcQ");
                if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                    Desktop.getDesktop().browse(uri);
                }
            } catch (IOException | URISyntaxException ex) {
                ex.printStackTrace();
            }
        }
    }
}
