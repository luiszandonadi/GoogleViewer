/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package googleviewer.menuItems;

import com.googlecode.gmail4j.EmailAddress;
import com.googlecode.gmail4j.GmailClient;
import com.googlecode.gmail4j.GmailMessage;
import googleviewer.Email;
import googleviewer.services.ConnectionService;
import googleviewer.services.StartService;
import googleviewer.services.UtilService;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.SwingWorker;

/**
 *
 * @author luis
 */
public class MenuItemGmail extends MenuItem {

    private Image image, imageLoading;
    private TrayIcon trayIcon;
    private static URL iconNewMail = StartService.class.getResource("/googleviewer/images/mail.png");
    private static final Image imageNewMail = new ImageIcon(iconNewMail).getImage();
private String label;
    public MenuItemGmail(String label, final Image image, final Image imageLoading, final TrayIcon trayIcon) throws HeadlessException {
        super(label);
        this.label = label;
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
            protected Boolean doInBackground() {
                trayIcon.setImage(imageLoading);
                Boolean result = false;

                try {
                    ConnectionService instance = ConnectionService.getInstance();
                    GmailClient client = instance.getClient();
                    final List<GmailMessage> messages = client.getUnreadMessages();
                    String text = "";
                    List<Email> emails = new ArrayList<Email>();
                    for (GmailMessage message : messages) {
                        EmailAddress from = null;
                        try {
                            from = message.getFrom();
                        } catch (Exception e) {
                        }

                        Date sendDate = null;
                        try {
                            sendDate = message.getSendDate();
                        } catch (Exception e) {
                        }
                        String data = sendDate == null ? 
                            UtilService.getInstance().getDefaltBundle().getString("unknown_data")
                            : UtilService.getInstance().getDateFormat(true).format(sendDate);
                        String nome = from == null ? 
                            UtilService.getInstance().getDefaltBundle().getString("unknown_from")
                            : from.getName();
                        String texto = "<html>"
                                + data
                                + " <b><u>(" + nome
                                + ")</u></b> " + message.getSubject().replace("-", "")
                                + "</html>";

                        emails.add(new Email(texto, message.getLink()));

                    }
                    UtilService.getInstance().setEmails(emails);
                    Integer size = messages.size();
                    if (size == 0) {
                    setLabel(label);
                        result = false;
                    } else {
                        setLabel(label+" ("+size.toString()+")");
                        result = true;
                        trayIcon.displayMessage(
                                "<html>"
                                //                                    + java.util.ResourceBundle.getBundle("pt-br")
                                + UtilService.getInstance().getDefaltBundle().getString("mail_new_message").replace("?", size.toString()), text, TrayIcon.MessageType.INFO);
                    }

                } catch (Exception e) {
                    Logger.getLogger(MenuItemGmail.class.getName()).log(Level.SEVERE, null, e);
                }
                return result;
            }

            @Override
            protected void done() {
                Boolean get = false;
                try {
                    get = (Boolean) get();
                } catch (InterruptedException ex) {
                    Logger.getLogger(MenuItemGmail.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ExecutionException ex) {
                    Logger.getLogger(MenuItemGmail.class.getName()).log(Level.SEVERE, null, ex);
                }

                if (get) {
                    trayIcon.setImage(imageNewMail);
                } else {
                    trayIcon.setImage(image);
                }
                super.done();
            }
        };


    }
}


