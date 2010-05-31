package googleviewer.views;

import com.google.gdata.client.calendar.CalendarQuery;
import com.google.gdata.client.calendar.CalendarService;
import com.google.gdata.data.DateTime;
import com.google.gdata.data.calendar.CalendarEventEntry;
import com.google.gdata.data.calendar.CalendarEventFeed;
import com.google.gdata.util.AuthenticationException;
import com.google.gdata.util.ServiceException;
import googleviewer.Main;
import googleviewer.TableModel;
import googleviewer.services.LoginService;
import googleviewer.services.StartService;
import googleviewer.services.UtilService;
import java.awt.Image;
import java.awt.TrayIcon;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.SwingWorker;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * NewJFrame.java
 *
 * Created on May 27, 2010, 12:35:39 PM
 */
/**
 *
 * @author luis
 */
public class FrameCalendarEventos extends javax.swing.JFrame {

    private static Image original, loading;
    private static TrayIcon tray;
    FrameCalendarEventos instance;
final UtilService defaltBundle = UtilService.getInstance();
    /** Creates new form NewJFrame */
    public FrameCalendarEventos(Image originalIcon, Image loadingIcon, TrayIcon trayIcon) {
        original = originalIcon;
        loading = loadingIcon;
        tray = trayIcon;
        initComponents();
        setLocationRelativeTo(null);
        instance = this;
        datePickerInicio.setLocale(defaltBundle.getDefaltLocale());
        datePickerFim.setLocale(defaltBundle.getDefaltLocale());
        jButton1.setText(defaltBundle.getDefaltBundle().getString("button_find"));
    }

    public JTable getTable() {
        return jTable1;

    }

    public void loadTable(final Date inicio, final Date fim) {


        SwingWorker swingWorker = new SwingWorker() {

            @Override
            protected Boolean doInBackground() throws Exception {
                

                Boolean result = true;
                tray.setImage(loading);
                CalendarService service = LoginService.getInstance().getCalendarService();
                URL feedUrl = null;
                try {
                    feedUrl = new URL("http://www.google.com/calendar/feeds/default/private/full");
                } catch (MalformedURLException ex) {
                    result = false;
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
                CalendarEventFeed resultFeed;


                try {

                    CalendarQuery myQuery = new CalendarQuery(feedUrl);


                    if (inicio == null || fim == null) {
                        Calendar c = new GregorianCalendar(defaltBundle.getDefaltLocale());
                        int minimum = c.getMinimum(Calendar.DAY_OF_MONTH);
                        int maximum = c.getMaximum(Calendar.DAY_OF_MONTH);

                        Date inicio = c.getTime();
                        inicio.setDate(minimum);
                        Date fim = c.getTime();
                        fim.setDate(maximum);
                        myQuery.setMinimumStartTime(new DateTime(inicio.getTime()));
                        myQuery.setMaximumStartTime(new DateTime(fim.getTime()));
                        datePickerInicio.setDate(inicio);
                        datePickerFim.setDate(fim);
                    } else {
                        myQuery.setMinimumStartTime(new DateTime(inicio.getTime()));
                        myQuery.setMaximumStartTime(new DateTime(fim.getTime()));
                    }

                    resultFeed = service.getFeed(myQuery, CalendarEventFeed.class);

                    List<CalendarEventEntry> eventos = resultFeed.getEntries();
                    String colunas = defaltBundle.getDefaltBundle().getString("collunms");
                    jTable1.setModel(new TableModel(eventos, colunas.split(","), defaltBundle.getDateFormat()));




                } catch (IOException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    result = false;
                } catch (ServiceException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    result = false;
                }



                return result;
            }

            @Override
            protected void done() {
                instance.setVisible(true);
                tray.setImage(original);
                super.done();
            }
        };
        swingWorker.execute();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        datePickerInicio = new org.jdesktop.swingx.JXDatePicker();
        datePickerFim = new org.jdesktop.swingx.JXDatePicker();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jButton1.setText("Pesquisar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(datePickerInicio, javax.swing.GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(datePickerFim, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 486, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(datePickerInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(datePickerFim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        loadTable(datePickerInicio.getDate(), datePickerFim.getDate());
    }//GEN-LAST:event_jButton1ActionPerformed
    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new NewJFrame().setVisible(true);
//            }
//        });
//    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.jdesktop.swingx.JXDatePicker datePickerFim;
    private org.jdesktop.swingx.JXDatePicker datePickerInicio;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
