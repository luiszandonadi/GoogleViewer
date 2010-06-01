/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package googleviewer;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.net.URI;
import javax.swing.Icon;
import javax.swing.JLabel;

/**
 *
 * @author luis
 */
public class LabelHyperlinked extends JLabel {

    private String url;

    public void setUrl(String url) {
        this.url = url;
    }
    MouseAdapter adapter = new MouseAdapter() {

        @Override
        public void mouseClicked(MouseEvent e) {
            if (url != null) {
                if (Desktop.isDesktopSupported()) {
                    Desktop desktop = Desktop.getDesktop();
                    try {
                        desktop.browse(new URI(url));

                        super.mouseClicked(e);

                    } catch (Exception ev) {
                    }
                }
            } else {
            }
        }

        @Override
        public void mouseExited(MouseEvent e) {
            setBackground(Color.red);
            setForeground(Color.BLUE);
            super.mouseExited(e);
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            setBackground(Color.red);
            setForeground(Color.BLUE);
            super.mouseDragged(e);
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            setBackground(Color.red);
            setForeground(Color.BLUE);
            super.mouseEntered(e);
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            setForeground(Color.BLUE);
            super.mouseMoved(e);
        }

        @Override
        public void mousePressed(MouseEvent e) {
            super.mousePressed(e);
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            super.mouseReleased(e);
        }

        @Override
        public void mouseWheelMoved(MouseWheelEvent e) {
            setForeground(Color.BLUE);
            super.mouseWheelMoved(e);
        }
    };

    public LabelHyperlinked() {
        addMouseListener(adapter);
    }

    public LabelHyperlinked(Icon image) {
        super(image);
        addMouseListener(adapter);
    }

    public LabelHyperlinked(Icon image, int horizontalAlignment) {
        super(image, horizontalAlignment);
        addMouseListener(adapter);
    }

    public LabelHyperlinked(String text) {
        super(text);
        addMouseListener(adapter);
    }

    public LabelHyperlinked(String text, int horizontalAlignment) {
        super(text, horizontalAlignment);
        addMouseListener(adapter);
    }

    public LabelHyperlinked(String text, Icon icon, int horizontalAlignment) {
        super(text, icon, horizontalAlignment);
        addMouseListener(adapter);
    }
}
