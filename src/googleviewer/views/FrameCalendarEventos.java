package googleviewer.views;

import com.google.gdata.client.calendar.CalendarQuery;
import com.google.gdata.client.calendar.CalendarService;
import com.google.gdata.data.DateTime;
import com.google.gdata.data.PlainTextConstruct;
import com.google.gdata.data.calendar.CalendarEventEntry;
import com.google.gdata.data.calendar.CalendarEventFeed;
import com.google.gdata.data.extensions.Reminder;
import com.google.gdata.data.extensions.Reminder.Method;
import com.google.gdata.data.extensions.When;
import com.google.gdata.util.ServiceException;
import googleviewer.Main;
import googleviewer.TableModel;
import googleviewer.services.ConnectionService;
import googleviewer.services.UtilService;
import java.awt.Image;
import java.awt.TrayIcon;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerDateModel;
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
    private CalendarEventEntry currentEventEntry;
    private SpinnerDateModel smi, smf;
    private Calendar calendar = new GregorianCalendar(defaltBundle.getDefaltLocale());

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
        smi = new SpinnerDateModel(calendar.getTime(), null, null, Calendar.HOUR_OF_DAY);
        smf = new SpinnerDateModel(calendar.getTime(), null, null, Calendar.HOUR_OF_DAY);

        spinnerInicio.setModel(smi);
        spinnerFim.setModel(smf);

        JSpinner.DateEditor dei = new JSpinner.DateEditor(spinnerInicio, "HH:mm");
        JSpinner.DateEditor def = new JSpinner.DateEditor(spinnerFim, "HH:mm");
        spinnerInicio.setEditor(dei);
        spinnerFim.setEditor(def);


        jButton5.setVisible(false);
        labelLoading.setVisible(false);
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
                CalendarService service = ConnectionService.getInstance().getCalendarService();
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

                        int minimum = calendar.getMinimum(Calendar.DAY_OF_MONTH);
                        int maximum = calendar.getMaximum(Calendar.DAY_OF_MONTH);

                        Date inicio = calendar.getTime();
                        inicio.setDate(minimum);
                        Date fim = calendar.getTime();
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
                    jTable1.setModel(new TableModel(eventos, colunas.split(","), defaltBundle));




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

        jOutlookBar1 = new com.l2fprod.common.swing.JOutlookBar();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        textFieldTitulo = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        textAreaDescricao = new javax.swing.JTextArea();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jCheckBox1 = new javax.swing.JCheckBox();
        jButton5 = new javax.swing.JButton();
        labelOutput = new javax.swing.JLabel();
        labelLoading = new javax.swing.JLabel();
        datePickerInicio = new org.jdesktop.swingx.JXDatePicker();
        datePickerFim = new org.jdesktop.swingx.JXDatePicker();
        spinnerInicio = new javax.swing.JSpinner();
        spinnerFim = new javax.swing.JSpinner();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(254, 254, 254));

        jPanel1.setBackground(new java.awt.Color(254, 254, 254));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/googleviewer/images/find.png"))); // NOI18N
        jButton1.setText("Pesquisar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));

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
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(261, 261, 261)
                .addComponent(jButton1)
                .addContainerGap(300, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 612, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
                .addContainerGap())
        );

        jOutlookBar1.addTab("", new javax.swing.ImageIcon(getClass().getResource("/googleviewer/images/clock.png")), jPanel1); // NOI18N

        jPanel2.setBackground(new java.awt.Color(254, 254, 254));

        jLabel1.setText("Título");

        jLabel2.setText("Descrição");

        textAreaDescricao.setColumns(20);
        textAreaDescricao.setRows(5);
        jScrollPane2.setViewportView(textAreaDescricao);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/googleviewer/images/disk.png"))); // NOI18N
        jButton2.setText("Gravar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/googleviewer/images/add.png"))); // NOI18N
        jButton3.setText("Novo");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/googleviewer/images/arrow_rotate_clockwise.png"))); // NOI18N
        jButton4.setText("Voltar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jCheckBox1.setText("Dia Inteiro");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/googleviewer/images/delete.png"))); // NOI18N
        jButton5.setText("Deletar");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        labelOutput.setFont(new java.awt.Font("DejaVu Sans", 1, 12));
        labelOutput.setForeground(new java.awt.Color(0, 102, 255));

        labelLoading.setIcon(new javax.swing.ImageIcon(getClass().getResource("/googleviewer/images/loader_big.gif"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2))
                                .addGap(75, 75, 75)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
                                    .addComponent(textFieldTitulo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
                                    .addComponent(labelOutput, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE))
                                .addGap(50, 50, 50))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jButton3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton5)
                                .addGap(82, 82, 82)))
                        .addComponent(labelLoading)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jCheckBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(247, 247, 247))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(textFieldTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel2))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton2)
                            .addComponent(jButton3)
                            .addComponent(jButton4)
                            .addComponent(jButton5))
                        .addGap(18, 18, 18)
                        .addComponent(labelOutput, javax.swing.GroupLayout.DEFAULT_SIZE, 14, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(217, 217, 217)
                        .addComponent(labelLoading, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jOutlookBar1.addTab("", new javax.swing.ImageIcon(getClass().getResource("/googleviewer/images/clock_edit.png")), jPanel2); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(datePickerInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spinnerInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                .addComponent(datePickerFim, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(spinnerFim, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
            .addComponent(jOutlookBar1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 673, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(datePickerInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spinnerInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(datePickerFim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spinnerFim, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jOutlookBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        labelLoading.setText("");
        loadTable(datePickerInicio.getDate(), datePickerFim.getDate());
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        labelLoading.setText("");
        int selectedRow = jTable1.getSelectedRow();
        TableModel model = (TableModel) jTable1.getModel();
        currentEventEntry = model.getCalendarEventEntry(selectedRow);
        textFieldTitulo.setText(currentEventEntry.getTitle().getPlainText());
        textAreaDescricao.setText(currentEventEntry.getPlainTextContent());
        List<When> times = currentEventEntry.getTimes();
        for (When when : times) {
            final DateTime startTime = when.getStartTime();
            final DateTime endTime = when.getEndTime();
            if (startTime.isDateOnly() || endTime.isDateOnly()) {
                jCheckBox1.setSelected(true);
            } else {
                jCheckBox1.setSelected(false);
            }
            Date inicio = new Date(startTime.getValue());
            datePickerInicio.setDate(inicio);
            smi.setValue(inicio);
            Date fim = new Date(endTime.getValue());
            datePickerFim.setDate(fim);
            smf.setValue(fim);
        }

        jButton5.setVisible(true);
        jOutlookBar1.setSelectedIndex(1);
    }//GEN-LAST:event_jTable1MouseClicked

    private void LimparCampos() {
        Date date = calendar.getTime();
        currentEventEntry = null;
        textFieldTitulo.setText("");
        textAreaDescricao.setText("");
        datePickerInicio.setDate(date);
        datePickerFim.setDate(date);
        jButton5.setVisible(false);
        smi.setValue(date);
        smf.setValue(date);
        jCheckBox1.setSelected(false);
    }

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        jOutlookBar1.setSelectedIndex(0);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        LimparCampos();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        SwingWorker swingWorker = new SwingWorker() {

            @Override
            protected CalendarEventEntry doInBackground() throws Exception {
                anime(true);
                jButton2.setEnabled(false);
                jButton3.setEnabled(false);
                URL postUrl = null;
                String operation = "update";

                String menssagem = "<html><br/>";

                if (datePickerInicio.getDate() == null) {
                    menssagem += "<p>Data de Início Não pode Ser Vazia.</p><br/>";
                }
                if (datePickerFim.getDate() == null) {
                    menssagem += "<p>Data de Fim Não pode Ser Vazia.</p><br/>";
                }
                if (textFieldTitulo.getText().equals("")) {
                    menssagem += "<p>Título Não Pode Ser Vazio.</p><br/>";
                }
                if (!menssagem.equals("<html><br/>")) {
                    new DialogMensagemValidacao(instance, menssagem);
                } else {

                    if (currentEventEntry == null) {
                        currentEventEntry = new CalendarEventEntry();
                        operation = "create";
                    }

                    currentEventEntry.setTitle(new PlainTextConstruct(textFieldTitulo.getText()));
                    currentEventEntry.setContent(new PlainTextConstruct(textAreaDescricao.getText()));

                    //TODO: needs refactor!!!!!!!!
                    Date dateInicio = datePickerInicio.getDate();
                    Date dateFim = datePickerFim.getDate();
                    boolean dateOnly = jCheckBox1.isSelected();
                    dateInicio.setHours(smi.getDate().getHours());
                    dateFim.setHours(smf.getDate().getHours());
                    dateInicio.setMinutes(smi.getDate().getMinutes());
                    dateFim.setMinutes(smf.getDate().getMinutes());
                    DateTime startTime = new DateTime(dateInicio, TimeZone.getDefault());
                    DateTime endTime = new DateTime(dateFim, TimeZone.getDefault());
                    startTime.setDateOnly(dateOnly);
                    endTime.setDateOnly(dateOnly);
                    When eventTimes = new When();
                    eventTimes.setStartTime(startTime);
                    eventTimes.setEndTime(endTime);

                    CalendarService calendarService = ConnectionService.getInstance().getCalendarService();
                    if (operation.equals("update")) {
                        try {
                            currentEventEntry.getTimes().clear();
                            currentEventEntry.addTime(eventTimes);
                            postUrl = new URL(currentEventEntry.getEditLink().getHref());
//                            CalendarEventEntry update = currentEventEntry.update();
                            CalendarEventEntry update = calendarService.update(postUrl, currentEventEntry);
                            return update;
                        } catch (IOException ex) {
                            Logger.getLogger(FrameCalendarEventos.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (ServiceException ex) {
                            Logger.getLogger(FrameCalendarEventos.class.getName()).log(Level.SEVERE, null, ex);
                        }


                    } else if (operation.equals("create")) {
                        try {
                            String url = "http://www.google.com/calendar/feeds/?/private/full".replace("?", ConnectionService.getInstance().getEmail());
                            postUrl = new URL(url);
                            Method methodType = Method.EMAIL;
                            Reminder reminder = new Reminder();
                            reminder.setDays(5);
                            reminder.setMethod(methodType);
                            List<Reminder> reminders = currentEventEntry.getReminder();
                            reminders = new ArrayList<Reminder>();
                            reminders.add(reminder);
                            currentEventEntry.addTime(eventTimes);
                            CalendarEventEntry insert = calendarService.insert(postUrl, currentEventEntry);
                            return insert;
                        } catch (Exception ex) {
                            Logger.getLogger(FrameCalendarEventos.class.getName()).log(Level.SEVERE, null, ex);
                            return null;
                        }

                    }
                }
                return null;
            }

            @Override
            protected void done() {
                try {
                    if (get() == null) {
                        labelOutput.setText("<html><font color=red>Erro...Evento não gravado</font></html>");
                    } else {
                        labelOutput.setText("Registro gravado com sucesso!");
                    }
                } catch (InterruptedException ex) {
                    Logger.getLogger(FrameCalendarEventos.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ExecutionException ex) {
                    Logger.getLogger(FrameCalendarEventos.class.getName()).log(Level.SEVERE, null, ex);
                }
                jButton2.setEnabled(true);
                jButton3.setEnabled(true);
                anime(false);
                LimparCampos();
                loadTable(null, null);
                super.done();
            }
        };

        swingWorker.execute();

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed

        SwingWorker swingWorker = new SwingWorker() {

            @Override
            protected Boolean doInBackground() throws Exception {
                jButton5.setEnabled(false);
                anime(true);
                try {
                    currentEventEntry.delete();
                    return true;
                } catch (IOException ex) {
                    Logger.getLogger(FrameCalendarEventos.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ServiceException ex) {
                    Logger.getLogger(FrameCalendarEventos.class.getName()).log(Level.SEVERE, null, ex);
                }
                return false;
            }

            @Override
            protected void done() {
                anime(false);
                Boolean b = false;
                try {
                    b = (Boolean) get();
                } catch (InterruptedException ex) {
                    Logger.getLogger(FrameCalendarEventos.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ExecutionException ex) {
                    Logger.getLogger(FrameCalendarEventos.class.getName()).log(Level.SEVERE, null, ex);
                }

                if (b) {
                    labelOutput.setText("Evento removido com sucesso!");
                } else {
                    labelOutput.setText("<html><font color=red>Erro...Evento não removido</font></html>");
                }
                jButton5.setEnabled(true);
                LimparCampos();
                loadTable(null, null);
                super.done();
            }
        };
        swingWorker.execute();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        // TODO add your handling code here:
        smi.setValue(new Date());
        smf.setValue(new Date());
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void anime(boolean b) {
        labelLoading.setVisible(b);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.jdesktop.swingx.JXDatePicker datePickerFim;
    private org.jdesktop.swingx.JXDatePicker datePickerInicio;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private com.l2fprod.common.swing.JOutlookBar jOutlookBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel labelLoading;
    private javax.swing.JLabel labelOutput;
    private javax.swing.JSpinner spinnerFim;
    private javax.swing.JSpinner spinnerInicio;
    private javax.swing.JTextArea textAreaDescricao;
    private javax.swing.JTextField textFieldTitulo;
    // End of variables declaration//GEN-END:variables
}
