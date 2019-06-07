package apps.gallery;

import smartphone.Smartphone;

import javax.swing.*;
import java.awt.*;

class GalleryShow extends JPanel {
    private GalleryItemPath gip;
    private JLabel lbl = new JLabel();
    GalleryShow(CardLayout cl, JPanel pnl, GalleryItemPath galleryItem, int gallerySize, Smartphone sm) {

        // Création de l'interface graphique
        setLayout(new BorderLayout());
        lbl.setHorizontalAlignment(JLabel.CENTER);
        JPanel pnlActions = new JPanel();
        JButton btnPrevious = new JButton("<");
        JButton btnBack = new JButton("Back");
        JButton btnSetAs = new JButton("Update home");
        JButton btnNext = new JButton(">");
        pnlActions.setLayout(new GridLayout(0, 4));
        pnlActions.add(btnPrevious);
        pnlActions.add(btnBack);
        pnlActions.add(btnSetAs);
        pnlActions.add(btnNext);
        add(lbl);
        add(pnlActions, BorderLayout.SOUTH);

        // Pour mettre l'image dans le label
        setGalleryItem(galleryItem);

        // Grisage des boutons pour les premières et dernières images
        if(this.gip.getId() == 0) {
            btnPrevious.setEnabled(false);
        } else if (this.gip.getId() == gallerySize-1) {
            btnNext.setEnabled(false);
        }

        /*
         * Listeners
         */

        // Clic sur bouton " > " (next)
        btnNext.addActionListener(e -> cl.show(pnl, "img"+ (this.gip.getId() + 1)));

        // Clic sur le bouton " < " (previous)
        btnPrevious.addActionListener(e -> cl.show(pnl, "img"+ (this.gip.getId() - 1)));


        // Clic sur le bouton "Back" pour revenir à la galerie
        btnBack.addActionListener(e -> cl.show(pnl, "gallery"));

        // Clic sur le bouton "Update home" pour changer le fond d'écran
        btnSetAs.addActionListener(e -> sm.updateBackground(gip));

    }

    private void setGalleryItem(GalleryItemPath gi) {
        //this.galleryItem = gi;
        this.gip = gi;
        lbl.setIcon(new ImageIcon(this.gip.getImage().getScaledInstance(380,500, Image.SCALE_SMOOTH)));
        lbl.setSize(new Dimension(GalleryController.DEFAULT_GALLERY_WIDTH,GalleryController.DEFAULT_GALLERY_HEIGHT));
        repaint();
    }
}
