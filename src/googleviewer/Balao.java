package googleviewer;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.MenuContainer;
import java.awt.Point;
import java.awt.TrayIcon;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Locale;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;
import javax.swing.SwingUtilities;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

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

    public Balao(String titulo, TrayIcon component, String conteudo) {
        super();
        setAlwaysOnTop(true);
        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.setBorder(
                BorderFactory.createLineBorder(Color.BLACK, 1));
        balloonTitle = new JLabel(titulo);
        balloonTitle.setOpaque(true);
        balloonTitle.setBackground(Color.decode("#43328F"));
        balloonTitle.setForeground(Color.WHITE);
        balloonTitle.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));

        Font font = new Font(null).deriveFont(Font.BOLD);
        balloonTitle.setFont(font);

        panelContent = new JPanel(new GridLayout(0, 1));
        panelContent.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
        panelContent.setBackground(Color.decode("#FFFBCF"));
        contentPane.add(balloonTitle, BorderLayout.NORTH);
        contentPane.add(panelContent, BorderLayout.CENTER);
        getContentPane().add(contentPane);

        balloonTitle.setFocusable(true);
        JLabel jLabel = new JLabel();
        panelContent.add(jLabel);
        setVisible(true);
        Point location = getOwner().getLocation();

        setLocation(location.x+500,location.y+25);
        this.addMouseListener(new MouseListener() {

            public void mouseClicked(MouseEvent e) {
                setVisible(false);
            }

            public void mousePressed(MouseEvent e) {
            }

            public void mouseReleased(MouseEvent e) {
            }

            public void mouseEntered(MouseEvent e) {
            }

            public void mouseExited(MouseEvent e) {
            }
        });
        
        jLabel.setText(conteudo);
        pack();
    }
}

