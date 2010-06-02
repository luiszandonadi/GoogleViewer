/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package googleviewer.menuItems;

import com.googlecode.gmail4j.GmailClient;
import com.googlecode.gmail4j.GmailMessage;
import googleviewer.Email;
import googleviewer.services.ConnectionService;
import googleviewer.services.UtilService;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingWorker;

/**
 *
 * @author luis
 */
public class MenuItemGmail extends MenuItem {

    private Image image, imageLoading;
    private TrayIcon trayIcon;

    public MenuItemGmail(String label, final Image image, final Image imageLoading, final TrayIcon trayIcon) throws HeadlessException {
        super(label);
        this.image = image;
        this.imageLoading = imageLoading;
        this.trayIcon = trayIcon;
        this.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
                swingWorkerImpl().execute();
            }
        });



        /*
         * Tempo Padrão 10 minutos ou 600000 milisegundos
         */
        int tempo = 600000;
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                swingWorkerImpl().execute();
            }
        }, 1000, tempo);
    }

    private SwingWorker swingWorkerImpl() {
        return new SwingWorker() {

            @Override
            protected Object doInBackground() {
                trayIcon.setImage(imageLoading);


                try {
                    ConnectionService instance = ConnectionService.getInstance();
                    GmailClient client = instance.getClient();
                    final List<GmailMessage> messages = client.getUnreadMessages();
                    String text = "";
                    List<Email> emails = new ArrayList<Email>();
                    for (GmailMessage message : messages) {

                        String texto ="<html>"
                                + UtilService.getInstance().getDateFormat(true).format(message.getSendDate())
                                + " <b><u>(" + message.getFrom().getName()
                                + ")</u></b> " + message.getSubject()
                                + "</html>";

                        emails.add(new Email(texto, message.getLink()));

                    }
                    UtilService.getInstance().setEmails(emails);
                    Integer size = messages.size();
                    trayIcon.displayMessage(
                            "<html>"
                            //                                    + java.util.ResourceBundle.getBundle("pt-br")
                            + UtilService.getInstance().getDefaltBundle().getString("mail_new_message").replace("?", size.toString()), text, TrayIcon.MessageType.INFO);

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


    }
}


