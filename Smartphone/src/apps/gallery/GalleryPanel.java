package apps.gallery;

import smartphone.AppPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.logging.Logger;

public class GalleryPanel extends AppPanel {

    private static Logger logger = Logger.getLogger(GalleryPanel.class.getName());
    private CardLayout galleryCards = new CardLayout();
    private JPanel pnlMain = new JPanel(galleryCards);
    public GalleryPanel(String appName) {
        super(appName);
        JPanel pnlGallery = new JPanel();
        GridLayout grlGallery = new GridLayout(0, 3, 10, 10);
        pnlGallery.setLayout(grlGallery);

        System.out.println("Creating GalleryPanel");
        GalleryController gl = new GalleryController(GalleryController.MODE_LOCAL);

        for (GalleryItem gi : gl.getItems()) {
            JLabel lbl = new JLabel();
            lbl.setSize(new Dimension(GalleryController.DEFAULT_GALLERY_WIDTH,GalleryController.DEFAULT_GALLERY_HEIGHT));
            lbl.setIcon(new ImageIcon(gi.getResizedIimage(GalleryController.DEFAULT_GALLERY_WIDTH,GalleryController.DEFAULT_GALLERY_HEIGHT)));
            pnlGallery.add(lbl);

            lbl.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    System.out.println("lbl clicked");
                    GalleryShow gs = new GalleryShow(galleryCards, pnlMain);
                    gs.setImage(gi.getImage());
                    pnlMain.add(gs, "test");
                    galleryCards.show(pnlMain,"test");
                }
            });
        }

        pnlGallery.setOpaque(false);
        JScrollPane scpGallery = new JScrollPane(pnlGallery);
        //scpGallery.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        setLayout(new BorderLayout());
        //add(scpGallery, BorderLayout.CENTER);


        pnlMain.add(scpGallery, "gallery");
        add(pnlMain);
        //galleryCards.show(pnlMain, "main");

    }
}
