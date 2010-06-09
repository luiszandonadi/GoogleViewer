package googleviewer;

import com.sun.awt.AWTUtilities;
import googleviewer.services.UtilService;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Shape;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.Window;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.RoundRectangle2D;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author luis
 */
public class Balao extends JWindow {

    private JLabel balloonTitle;
    private JPanel panelContent;
    private Image image;
    private TrayIcon trayIcon;
    private Balao instance;

    public Balao(final Image image, String titulo, TrayIcon component, String conteudo) {
        super();
        this.instance = this;
        try {
            Class<?> awtUtilitiesClass = Class.forName("com.sun.awt.AWTUtilities");
            Method mSetWindowOpacity = awtUtilitiesClass.getMethod("setWindowOpacity", Window.class, float.class);
            mSetWindowOpacity.invoke(null, this, Float.valueOf(0.94f));
        } catch (NoSuchMethodException ex) {
            ex.printStackTrace();
        } catch (SecurityException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
        } catch (InvocationTargetException ex) {
            ex.printStackTrace();
        }

        addComponentListener(new ComponentAdapter() {

            @Override
            public void componentResized(ComponentEvent e) {
                Shape shape = null;
                shape = new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 15, 15);
                AWTUtilities.setWindowShape(instance, shape);

            }
        });
        this.trayIcon = component;
        this.image = image;
        setAlwaysOnTop(true);
        JPanel contentPane = new JPanel(new BorderLayout());
//        contentPane.setBorder(
//                BorderFactory.createLineBorder(Color.BLACK, 1));
        balloonTitle = new JLabel(titulo);
        Toolkit defaultToolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = defaultToolkit.getScreenSize();
        try {
            URL icon = Balao.class.getResource("/googleviewer/images/cancel.png");
            balloonTitle.setIcon(new ImageIcon(icon));

        } catch (Exception ex) {
            Logger.getLogger(Balao.class.getName()).log(Level.SEVERE, null, ex);
        }
        balloonTitle.setOpaque(true);
//        balloonTitle.setBackground(Color.decode("#43328F"));
//        balloonTitle.setBackground(Color.decode("#C10000"));
//        balloonTitle.setBackground(Color.decode("#C9604F"));
        balloonTitle.setBackground(Color.decode("#7B7B7B"));
        balloonTitle.setForeground(Color.WHITE);
        balloonTitle.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));

//        Font font = ;
        balloonTitle.setFont(new Font(null).deriveFont(Font.BOLD));

        panelContent = new JPanel(new GridLayout(0, 1));
        panelContent.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
//        panelContent.setBackground(Color.decode("#FFFBCF"));
        panelContent.setBackground(Color.decode("#FFFFFF"));
        contentPane.add(balloonTitle, BorderLayout.NORTH);
        contentPane.add(panelContent, BorderLayout.CENTER);
        getContentPane().add(contentPane);

        balloonTitle.setFocusable(true);
        setVisible(true);
        //        setLocation(location.x + 500, location.y + 25);
        //        setLocation(location.x + 200, location.y);
        //        setLocationRelativeTo(null);

        Double width = screenSize.getWidth() / 4;
        setLocation(width.intValue(), 0);

        MouseListener mouseListener = new MouseListener() {

            public void mouseClicked(MouseEvent e) {
                dispose();
            }

            public void mousePressed(MouseEvent e) {
            }

            public void mouseReleased(MouseEvent e) {
            }

            public void mouseEntered(MouseEvent e) {
            }

            public void mouseExited(MouseEvent e) {
            }
        };

        this.addMouseListener(mouseListener);
        int count = 0;
        List<Email> emails = UtilService.getInstance().getEmails();
        if (emails != null) {
            for (Email email : emails) {
                Color c = new Color(126, 152, 255);
                if (count % 2 > 0) {
                    c = new Color(120, 121, 126);
                }
                count++;
                LabelHyperlinked label = new LabelHyperlinked(email.getTexto());
                label.setUrl(email.getUrl());
                label.setForeground(c);
                label.addMouseListener(mouseListener);
                panelContent.add(label);


            }
        }

        /*
         * Tempo Padrão 20 segundos.
         */
        final long tempo = 20000;

        Thread thread = new Thread(new Runnable() {

            public void run() {
                try {
                    Thread.sleep(tempo);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Balao.class.getName()).log(Level.SEVERE, null, ex);
                }
                dispose();
            }
        });
        thread.start();
        pack();
        balloonTitle.setHorizontalTextPosition(JLabel.LEFT);
        balloonTitle.setIconTextGap(this.getSize().width-190);
    }
}

