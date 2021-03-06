package googleviewer.views;

import com.l2fprod.common.swing.JCollapsiblePane;
import googleviewer.Proxy;
import googleviewer.services.UtilService;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import org.jdesktop.swingx.JXHyperlink;
import org.jdesktop.swingx.hyperlink.LinkModelAction;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * FrameLogin.java
 *
 * Created on May 28, 2010, 12:38:37 PM
 */
/**
 *
 * @author luis
 */
public abstract class FrameLogin extends javax.swing.JFrame {

    /** Creates new form FrameLogin */
    public FrameLogin() {

        initComponents();
        buttonLogin.addActionListener(action(this));
        setLocationRelativeTo(null);
        final ResourceBundle defaltBundle = UtilService.getInstance().getDefaltBundle();
        labelSenha.setText(defaltBundle.getString("password"));
        labelLogin.setText(defaltBundle.getString("user"));
        jXHeader1.setTitle(defaltBundle.getString("auth_title"));
        jButton1.setText(defaltBundle.getString("close"));
        jCheckBox1.setText(defaltBundle.getString("use_proxy"));
        labelProxy.setText(defaltBundle.getString("proxy"));
        labelProxyPort.setText(defaltBundle.getString("proxy_port"));
        pack();
    }

    public JTextField getTextFieldLogin() {
        return textFieldLogin;
    }

    public JPasswordField getTextFieldPassword() {
        return textFieldPassword;
    }

    public void setError(String error) {
        labelOutput.setText(error);
    }

    public Proxy getProxy() {
        if (checkBoxProxy.isSelected()) {
            return new Proxy(textFieldProxy.getText(), Integer.valueOf(textFieldProxyPort.getText()));
        } else {
            return null;
        }
    }

    public abstract ActionListener action(FrameLogin frameLogin);

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jCheckBox1 = new javax.swing.JCheckBox();
        jXPanel1 = new org.jdesktop.swingx.JXPanel();
        jXHeader1 = new org.jdesktop.swingx.JXHeader();
        jOutlookBar1 = new com.l2fprod.common.swing.JOutlookBar();
        jPanel1 = new javax.swing.JPanel();
        labelLogin = new javax.swing.JLabel();
        textFieldLogin = new javax.swing.JTextField();
        labelSenha = new javax.swing.JLabel();
        textFieldPassword = new javax.swing.JPasswordField();
        labelOutput = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        buttonLogin = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        labelProxy = new javax.swing.JLabel();
        textFieldProxy = new javax.swing.JTextField();
        labelProxyPort = new javax.swing.JLabel();
        textFieldProxyPort = new javax.swing.JTextField();
        checkBoxProxy = new javax.swing.JCheckBox();

        jCheckBox1.setText("Usar Proxy");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(204, 204, 255));
        setUndecorated(true);

        jXHeader1.setBackground(new java.awt.Color(51, 102, 255));
        jXHeader1.setTitle("Autenticação");

        jOutlookBar1.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);

        jPanel1.setBackground(new java.awt.Color(254, 254, 254));

        labelLogin.setText("Email:");

        labelSenha.setText("Senha:");

        labelOutput.setFont(new java.awt.Font("DejaVu Sans", 1, 13));
        labelOutput.setForeground(new java.awt.Color(204, 0, 0));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/googleviewer/images/door_out.png"))); // NOI18N
        jButton1.setText("Fechar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        buttonLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/googleviewer/images/accept.png"))); // NOI18N
        buttonLogin.setText("Login");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelLogin)
                            .addComponent(labelSenha))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(labelOutput, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(textFieldPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
                                .addComponent(textFieldLogin, javax.swing.GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)))
                        .addGap(25, 25, 25))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(55, 55, 55))))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {textFieldLogin, textFieldPassword});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textFieldLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelLogin))
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textFieldPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelSenha))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelOutput, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {textFieldLogin, textFieldPassword});

        jOutlookBar1.addTab("", jPanel1);

        jPanel2.setBackground(new java.awt.Color(254, 254, 254));

        labelProxy.setText("Proxy");

        labelProxyPort.setText("Porta");

        checkBoxProxy.setFont(new java.awt.Font("DejaVu Sans", 1, 14));
        checkBoxProxy.setText("Usar Proxy?");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelProxyPort, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelProxy, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textFieldProxy, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textFieldProxyPort, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(checkBoxProxy, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(110, 110, 110))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelProxy)
                    .addComponent(textFieldProxy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelProxyPort)
                    .addComponent(textFieldProxyPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addComponent(checkBoxProxy)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jOutlookBar1.addTab("Configurações", jPanel2);

        javax.swing.GroupLayout jXPanel1Layout = new javax.swing.GroupLayout(jXPanel1);
        jXPanel1.setLayout(jXPanel1Layout);
        jXPanel1Layout.setHorizontalGroup(
            jXPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jXPanel1Layout.createSequentialGroup()
                .addGroup(jXPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jXHeader1, javax.swing.GroupLayout.DEFAULT_SIZE, 308, Short.MAX_VALUE)
                    .addComponent(jOutlookBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jXPanel1Layout.setVerticalGroup(
            jXPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jXPanel1Layout.createSequentialGroup()
                .addComponent(jXHeader1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jOutlookBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jXPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jXPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        // TODO add your handling code here:
        boolean selected = jCheckBox1.isSelected();
        if (!selected) {
//            jCollapsiblePane1.setCollapsed(false);
        } else {
//            jCollapsiblePane1.setCollapsed(true);
        }
    }//GEN-LAST:event_jCheckBox1ActionPerformed
    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new FrameLogin().setVisible(true);
//            }
//        });
//    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonLogin;
    private javax.swing.JCheckBox checkBoxProxy;
    private javax.swing.JButton jButton1;
    private javax.swing.JCheckBox jCheckBox1;
    private com.l2fprod.common.swing.JOutlookBar jOutlookBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private org.jdesktop.swingx.JXHeader jXHeader1;
    private org.jdesktop.swingx.JXPanel jXPanel1;
    private javax.swing.JLabel labelLogin;
    private javax.swing.JLabel labelOutput;
    private javax.swing.JLabel labelProxy;
    private javax.swing.JLabel labelProxyPort;
    private javax.swing.JLabel labelSenha;
    private javax.swing.JTextField textFieldLogin;
    private javax.swing.JPasswordField textFieldPassword;
    private javax.swing.JTextField textFieldProxy;
    private javax.swing.JTextField textFieldProxyPort;
    // End of variables declaration//GEN-END:variables
}
