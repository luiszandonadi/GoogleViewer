/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package googleviewer;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
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
    private Font font = new Font("Dialog", Font.PLAIN, 13);
    private Font fontBold = new Font("Dialog", Font.ITALIC, 13);
    private Color currentColor;

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

                    } catch (Exception ev) {
                    }
                }
            } else {
            }
            super.mouseClicked(e);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            setFont(font);
//            if (currentColor != null) {
//                setForeground(currentColor);
//            }
            super.mouseExited(e);
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            super.mouseDragged(e);
        }

        @Override
        public void mouseEntered(MouseEvent e) {
//            currentColor = getForeground();
//            setForeground(Color.BLACK);
            setFont(fontBold);
            super.mouseEntered(e);
        }
    };

    public LabelHyperlinked() {
        setFont(font);
        addMouseListener(adapter);
    }

    public LabelHyperlinked(Icon image) {
        super(image);
        setFont(font);
        addMouseListener(adapter);
    }

    public LabelHyperlinked(Icon image, int horizontalAlignment) {
        super(image, horizontalAlignment);
        setFont(font);
        addMouseListener(adapter);
    }

    public LabelHyperlinked(String text) {
        super(text);
        setFont(font);
        addMouseListener(adapter);
    }

    public LabelHyperlinked(String text, int horizontalAlignment) {
        super(text, horizontalAlignment);
        setFont(font);
        addMouseListener(adapter);
    }

    public LabelHyperlinked(String text, Icon icon, int horizontalAlignment) {
        super(text, icon, horizontalAlignment);
        setFont(font);
        addMouseListener(adapter);
    }
}
