package apps.gallery;

import smartphone.AppPanel;
import smartphone.Smartphone;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.logging.Logger;

public class GalleryPanel extends AppPanel {

    private static Logger logger = Logger.getLogger(GalleryPanel.class.getName());
    private CardLayout galleryCards = new CardLayout();
    private JPanel pnlMain = new JPanel(galleryCards);
    private Color colBackground = Color.decode("#696a6d");

    public GalleryPanel(String appName, Smartphone sm) {
        super(appName);
        JPanel pnlGallery = new JPanel();
        GridLayout grlGallery = new GridLayout(0, 3, 10, 10);
        pnlGallery.setLayout(grlGallery);
        pnlGallery.setBackground(colBackground);
        pnlGallery.setOpaque(true);


        System.out.println("Creating GalleryPanel");
        GalleryController gc = new GalleryController(GalleryController.MODE_LOCAL);

        for (GalleryItemPath gi : gc.getItems()) {

            JLabel lbl = new JLabel();
            lbl.setSize(new Dimension(GalleryController.DEFAULT_GALLERY_WIDTH, GalleryController.DEFAULT_GALLERY_HEIGHT));
            lbl.setIcon(new ImageIcon(gi.getResizedIimage(GalleryController.DEFAULT_GALLERY_WIDTH, GalleryController.DEFAULT_GALLERY_HEIGHT)));
            pnlGallery.add(lbl);

            GalleryShow gs = new GalleryShow(galleryCards, pnlMain, gi, gc.getGallerySize(), sm);
            String id = String.valueOf(gi.getId());
            pnlMain.add(gs, "img" + (id));
            lbl.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    galleryCards.show(pnlMain, "img" + id);
                }
            });
        }

        pnlGallery.setOpaque(true);
        JScrollPane scpGallery = new JScrollPane(pnlGallery);
        setLayout(new BorderLayout());


        pnlMain.add(scpGallery, "gallery");
        add(pnlMain);
        scpGallery.setBackground(colBackground);
        scpGallery.setOpaque(true);
        galleryCards.show(pnlMain, "gallery");

    }
}
