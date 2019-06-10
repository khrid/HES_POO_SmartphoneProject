package apps.gallery;

import smartphone.AppPanel;
import smartphone.Smartphone;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GalleryPanel extends AppPanel {

    private CardLayout galleryCards = new CardLayout();
    private JPanel pnlGallery = new JPanel();
    private JPanel pnlMain = new JPanel(galleryCards);
    private JPanel pnlActions = new JPanel(new GridLayout(0, 4));

    public GalleryPanel(String appName, Smartphone sm) {
        super(appName);
        GridLayout grlGallery = new GridLayout(0, 3, 10, 10);
        pnlGallery.setLayout(grlGallery);
        Color colBackground = Color.decode("#c6c9d1");
        pnlGallery.setBackground(colBackground);
        pnlGallery.setOpaque(true);
        refreshGallery(sm);
        pnlGallery.setOpaque(true);
        JScrollPane scpGallery = new JScrollPane(pnlGallery);
        setLayout(new BorderLayout());


        pnlMain.add(scpGallery, "gallery");
        add(pnlMain);
        scpGallery.setBackground(colBackground);
        scpGallery.setOpaque(true);
        galleryCards.show(pnlMain, "gallery");


        JButton btnAddPhotos = new JButton("Add photo");

        pnlActions.add(new JPanel());
        pnlActions.add(new JPanel());
        pnlActions.add(new JPanel());
        pnlActions.add(btnAddPhotos);
        pnlActions.setBackground(colBackground);
        pnlActions.setOpaque(true);
        add(pnlActions, BorderLayout.SOUTH);

        btnAddPhotos.addActionListener(e -> {
            JFileChooser jfc = new JFileChooser();

            jfc.setAcceptAllFileFilterUsed(false);
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "jpg", "png", "tif");
            jfc.setFileFilter(filter);

            int retour = jfc.showOpenDialog(this);
            if (retour == JFileChooser.APPROVE_OPTION) {
                sm.getGalleryController().addNewImage(jfc.getSelectedFile());
                refreshGallery(sm);
            }// pas de fichier choisi
        });
    }

    void refreshGallery(Smartphone sm) {
        pnlGallery.removeAll();
        for (GalleryItem gi : sm.getGalleryController().getItems()) {
            JLabel lbl = new JLabel();
            lbl.setSize(new Dimension(GalleryController.DEFAULT_GALLERY_WIDTH, GalleryController.DEFAULT_GALLERY_HEIGHT));
            lbl.setIcon(new ImageIcon(gi.getResizedIimage(GalleryController.DEFAULT_GALLERY_WIDTH, GalleryController.DEFAULT_GALLERY_HEIGHT)));
            pnlGallery.add(lbl);

            GalleryShow gs = new GalleryShow(galleryCards, pnlMain, gi, sm.getGalleryController().getGallerySize(), sm, pnlActions, this);
            String id = String.valueOf(gi.getId());
            pnlMain.add(gs, "img" + (id));
            lbl.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    galleryCards.show(pnlMain, "img" + id);
                    pnlActions.setVisible(false);
                }
            });
        }
        repaint();
    }
}
