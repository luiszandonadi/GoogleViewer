/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package googleviewer;

import googleviewer.services.StartService;
import googleviewer.services.UtilService;
import java.util.ResourceBundle;
import javax.swing.UIManager;

/**
 *
 * @author luis
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        StartService instance = StartService.getInstance();
        instance.init();
        try {
            ResourceBundle defaltBundle = UtilService.getInstance().getDefaltBundle();
            String laf = defaltBundle.getString("look_and_feel");
            UIManager.setLookAndFeel(laf);
        } catch (Exception e) {
            System.out.println("Error setting Motif LAF: " + e);
        }
    }
}
