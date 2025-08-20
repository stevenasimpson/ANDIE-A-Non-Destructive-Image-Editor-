package cosc202.andie;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

/**
 * <p>
 * Actions provided by the language menu.
 * </p>
 * 
 * <p>
 * The language menu contains actions that changes all text to the default
 * or user set language.
 * </p>
 * 
 * <p> 
 * <a href="https://creativecommons.org/licenses/by-nc-sa/4.0/">CC BY-NC-SA 4.0</a>
 * </p>
 * 
 * @author Ashwin Ellis
 * @version 1.0
 */
public class I18n {

    /** Data field for preferences object */
    public static Preferences prefs;
    /** Data field for ResourceBundle object */
    public static ResourceBundle bundle;

    /**
     * <p>
     * Construct a new I18n 
     * </p>
     */
    public I18n(){
        
    }
    
    /**
     * <p>
     * Initializes the preferences object,
     * Sets the default locale to NZ english,
     * Initializes the ResourceBundle object.
     * </p>
     */
    public static void setDefaultLocale(){
        prefs = Preferences.userNodeForPackage(Andie.class);
        Locale.setDefault(new Locale(prefs.get("language", "en"), prefs.get("country", "NZ")));
        bundle = ResourceBundle.getBundle("MessageBundle");
    }

    /**
     * <p>
     * Sets the preference for the locale to NZ, English.
     * </p>
     */
    public static void setEnglishLocale(){
        prefs.put("language", "en");
        prefs.put("country", "NZ");    
    }
    
    /**
     * <p>
     * Sets the preference for the locale to NZ, Maori.
     * </p>
     */
    public static void setMaoriLocale(){
        prefs.put("language", "mi");
        prefs.put("country", "NZ");    
    }

    /**
     * <p>
     * String accessor to return a certain string from the
     * current properties file.
     * </p>
     * 
     * @param key The key the user wishes to access from the current properties file.
     * @return The string from the current properties file provided from the users key.
     */
    public static String getKeyString(String key){
        if(key == null || bundle == null) return "";
        return bundle.getString(key);
    }
}
