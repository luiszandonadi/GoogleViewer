package googleviewer.menuItems;

import googleviewer.views.FrameCalendarEventos;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author luis
 */
public class MenuItemCalendarEventos extends MenuItem {

    public MenuItemCalendarEventos(String label, final Image image, final Image imageLoading, final TrayIcon trayIcon) throws HeadlessException {
        super(label);
        this.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {

                FrameCalendarEventos frame = new FrameCalendarEventos(image, imageLoading, trayIcon);

                frame.loadTable(null, null);




            }
        });

    }
}
