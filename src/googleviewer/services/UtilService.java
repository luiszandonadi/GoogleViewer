/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package googleviewer.services;


import googleviewer.Email;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * @author luis
 */
public class UtilService {

    private static UtilService instance;
    private ResourceBundle bundle;
    private Locale defaltLocale = Locale.getDefault();
    private List<Email> emails;

    public static UtilService getInstance() {
        if (instance == null) {
            instance = new UtilService();
        }

        return instance;
    }

    public List<Email> getEmails() {
        return emails;
    }

    public void setEmails(List<Email> emails) {
        this.emails = emails;
    }

    


    public Locale getDefaltLocale() {
        return defaltLocale;
    }

    public SimpleDateFormat getDateFormat(boolean time) {


        String mask = time ? "date_time_mask" : "date_only_mask";
//        if (defaultDateFormat == null) {
//            defaultDateFormat =
//
//        }
        return new SimpleDateFormat(getDefaltBundle().getString(mask));
    }

    public UtilService() {
    }

    public ResourceBundle getDefaltBundle() {

        if (!defaltLocale.toString().equals("pt_BR")) {
            defaltLocale = new Locale("en", "US");
        }
        bundle = ResourceBundle.getBundle("MessagesBundle", defaltLocale);

        return bundle;
    }
}
