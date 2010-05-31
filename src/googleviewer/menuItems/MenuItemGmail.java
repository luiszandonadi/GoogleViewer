/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package googleviewer.menuItems;

import com.googlecode.gmail4j.GmailClient;
import com.googlecode.gmail4j.GmailMessage;
import googleviewer.services.LoginService;
import googleviewer.services.UtilService;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingWorker;

/**
 *
 * @author luis
 */
public class MenuItemGmail extends MenuItem {

    public MenuItemGmail(String label, final Image image, final Image imageLoading, final TrayIcon trayIcon) throws HeadlessException {
        super(label);
        this.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {

                SwingWorker swingWorker = new SwingWorker() {

                    @Override
                    protected Object doInBackground() {
                        trayIcon.setImage(imageLoading);


                        try {
                            LoginService instance = LoginService.getInstance();
                            GmailClient client = instance.getClient();
                            final List<GmailMessage> messages = client.getUnreadMessages();
                            String text = "<html><ul style='padding:0;margin:0;list-style-type:none;'> ";
                            int count = 0;
                            for (GmailMessage message : messages) {
                                String color = "blue";
                                if (count % 2 > 0) {
                                    color = "black";
                                }
                                count++;

                                text += "<li><font size=-1 color=" + color + ">"
                                        + UtilService.getInstance().getDateFormat().format(message.getSendDate())
                                        + " <b>(" + message.getFrom().getName()
                                        + ")</b> " + message.getSubject()
                                        + "</font></li>";
                            }
                            text += "</ul> ";
                            Integer size = messages.size();
                            trayIcon.displayMessage(
                                    "<html>"
//                                    + java.util.ResourceBundle.getBundle("pt-br")
                                    +UtilService.getInstance().getDefaltBundle()
                                    .getString("mail_new_message").replace("?", size.toString()), text, TrayIcon.MessageType.INFO);

                        } catch (Exception e) {
                            Logger.getLogger(MenuItemGmail.class.getName()).log(Level.SEVERE, null, e);
                        }
                        return null;
                    }

                    @Override
                    protected void done() {
                        trayIcon.setImage(image);
                        super.done();
                    }
                };
                swingWorker.execute();

            }
        });
    }
}
