package googleviewer.services;

import googleviewer.Balao;
import googleviewer.Proxy;
import googleviewer.menuItems.MenuItemCalendarEventos;
import googleviewer.views.FrameLogin;
import googleviewer.menuItems.MenuItemGmail;
import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.SwingWorker;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author luis
 */
public class StartService {

    public static StartService instance;
    private TrayIcon trayIcon;
    private PopupMenu popupMenu;
    private SystemTray tray;
    private static URL icon = StartService.class.getResource("/googleviewer/images/icon_red.png");
    private static URL iconLoading = StartService.class.getResource("/googleviewer/images/ajax-loader_5.gif");
    private static URL iconClosed = StartService.class.getResource("/googleviewer/images/off.png");
    private static final Image image = new ImageIcon(icon).getImage();
    private static final Image imageLoading = new ImageIcon(iconLoading).getImage();
    private static final Image imageClosed = new ImageIcon(iconClosed).getImage();
    private MenuItemGmail menuItemGmail;
    private MenuItem menuItemLogin;
    private MenuItemCalendarEventos menuItemMensagem;
    private MenuItem menuItemLogout;

    public static StartService getInstance() {
        if (instance == null) {
            instance = new StartService();
        }
        return instance;
    }

    public boolean doLogin() {
        FrameLogin frameLogin = new FrameLogin() {

            @Override
            public ActionListener action(FrameLogin frameLogin) {
                final FrameLogin frame = frameLogin;
                return new ActionListener() {

                    public void actionPerformed(ActionEvent e) {

                        SwingWorker swingWorker = new SwingWorker() {

                            @Override
                            protected Boolean doInBackground() throws Exception {
                                trayIcon.setImage(imageLoading);
                                frame.setVisible(false);
                                final String login = frame.getTextFieldLogin().getText();
                                final String password = String.valueOf(frame.getTextFieldPassword().getPassword());
                                final Proxy proxy = frame.getProxy();
                                boolean doLogin = LoginService.getInstance().doLogin(login, password, proxy);
                                if (doLogin) {
                                    addMenus();
                                    dispose();
                                    return true;

                                } else {
                                    return false;
                                }

                            }

                            @Override
                            protected void done() {
                                try {
                                    Boolean result = (Boolean) get();
                                    if (!result) {
                                        trayIcon.setImage(imageClosed);
                                        frame.setVisible(true);
                                        String erro = UtilService.getInstance().getDefaltBundle().getString("login_error");
                                        frame.setError(erro);
                                    } else {
                                        trayIcon.setImage(image);
                                    }

                                } catch (InterruptedException ex) {
                                    Logger.getLogger(StartService.class.getName()).log(Level.SEVERE, null, ex);
                                } catch (ExecutionException ex) {
                                    Logger.getLogger(StartService.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                super.done();
                            }
                        };
                        swingWorker.execute();

                    }
                };
            }
        };
        frameLogin.setVisible(true);
        return true;
    }

    public void init() {
        if (SystemTray.isSupported()) {
            tray = SystemTray.getSystemTray();
            String titulo = UtilService.getInstance().getDefaltBundle().getString("title");
            trayIcon = new TrayIcon(imageClosed, titulo) {

                @Override
                public void displayMessage(String caption, String text, MessageType messageType) {
                    new Balao(caption, this, text);
                }
            };
            trayIcon.setImageAutoSize(true);
            popupMenu = new PopupMenu(titulo);
            MenuItem menuItemFechar = new MenuItem(UtilService.getInstance().getDefaltBundle().getString("close"));
            menuItemFechar.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent ae) {
                    System.exit(0);
                }
            });
            menuItemLogin = new MenuItem("Login");
            menuItemLogin.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent ae) {
                    doLogin();
                }
            });




            popupMenu.add(menuItemLogin);
            popupMenu.add(menuItemFechar);
            trayIcon.setPopupMenu(popupMenu);
            try {
                tray.add(trayIcon);
            } catch (AWTException ex) {
                Logger.getLogger(StartService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    private void addMenus() {
        String tituloGmail = UtilService.getInstance().getDefaltBundle().getString("check_gmail");
        menuItemGmail = new MenuItemGmail(tituloGmail, image, imageLoading, trayIcon);
        String tituloEventos = UtilService.getInstance().getDefaltBundle().getString("check_events");
        menuItemMensagem = new MenuItemCalendarEventos(tituloEventos, image, imageLoading, trayIcon);


        menuItemLogout = new MenuItem("Logout");
        menuItemLogout.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
                doLogout();
            }
        });




        popupMenu.add(menuItemLogout);
        popupMenu.add(menuItemGmail);
        popupMenu.add(menuItemMensagem);
        popupMenu.remove(menuItemLogin);
    }

    private void doLogout() {
        trayIcon.setImage(imageClosed);
        popupMenu.remove(menuItemLogout);
        popupMenu.remove(menuItemGmail);
        popupMenu.remove(menuItemMensagem);
        popupMenu.add(menuItemLogin);
        LoginService.getInstance().doLogout();


    }
}
