package smartphone;

import apps.gallery.GalleryController;
import apps.gallery.GalleryItemPath;

import javax.swing.*;
import java.awt.*;

public class Launcher extends AppPanel {


    JLabel lblBackground = new JLabel("");
    public Launcher(CardLayout cards, JPanel pnlMulti) {
        super(cards, pnlMulti);
        System.out.println("Launcher created");
        add(lblBackground);
    }


    public void updateBackground(GalleryItemPath gi) {
        lblBackground.setIcon(new ImageIcon(gi.getImage().getScaledInstance(380,500, Image.SCALE_SMOOTH)));
        lblBackground.setSize(new Dimension(GalleryController.DEFAULT_GALLERY_WIDTH,GalleryController.DEFAULT_GALLERY_HEIGHT));
        repaint();
    }
}
