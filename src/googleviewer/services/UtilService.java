/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package googleviewer.services;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * @author luis
 */
public class UtilService {

    private SimpleDateFormat defaultDateFormat;
    private static UtilService instance;
    private ResourceBundle bundle;
    private Locale defaltLocale = Locale.getDefault();

    public static UtilService getInstance() {
        if (instance == null) {
            instance = new UtilService();
        }

        return instance;
    }

    public Locale getDefaltLocale() {
        return defaltLocale;
    }

    

    public SimpleDateFormat getDateFormat() {
        if (defaultDateFormat == null) {
            defaultDateFormat = new SimpleDateFormat(getDefaltBundle().getString("date_time_mask"));

        }
        return defaultDateFormat;
    }

    public UtilService() {
    }

    public ResourceBundle getDefaltBundle() {
       
        if (!defaltLocale.toString().equals("pt_BR") ) {
            defaltLocale = new Locale("en", "US");
        }
        bundle = ResourceBundle.getBundle("MessagesBundle",defaltLocale);

        return bundle;
    }
}
