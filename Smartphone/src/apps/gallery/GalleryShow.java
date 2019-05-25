package apps.gallery;

import javax.swing.*;
import java.awt.*;

public class GalleryShow extends JPanel {
    private Image image;
    private GalleryItem galleryItem;
    private int gallerySize = 0;
    JLabel lbl = new JLabel();
    public GalleryShow(CardLayout cl, JPanel pnl, GalleryItem galleryItem) {
        setGalleryItem(galleryItem);
        setLayout(new BorderLayout());
        lbl.setHorizontalAlignment(JLabel.CENTER);
        JPanel pnlActions = new JPanel();
        JButton btnPrevious = new JButton("<");
        JButton btnBack = new JButton("Back");
        JButton btnNext = new JButton(">");
        pnlActions.setLayout(new GridLayout(0, 5));
        pnlActions.add(btnPrevious);
        pnlActions.add(new JPanel());
        pnlActions.add(btnBack);
        pnlActions.add(new JPanel());
        pnlActions.add(btnNext);
        add(lbl);
        add(pnlActions, BorderLayout.SOUTH);
        btnBack.addActionListener(e -> {
            cl.show(pnl, "gallery");
        });

        if(this.galleryItem.getId() == 0) {
            btnPrevious.setEnabled(false);
        } else if (this.galleryItem.getId() == this.gallerySize-1) {
            btnNext.setEnabled(false);
        }
        System.out.println(this.gallerySize);

        // TODO implémenter grisage bouton
        btnNext.addActionListener(e -> {
            //revalidate();
            //repaint();
            cl.show(pnl, "img"+ (this.galleryItem.getId() + 1));
            System.out.println("img"+ (this.galleryItem.getId() + 1));
            //cl.next(pnl);
        });

        btnPrevious.addActionListener(e -> {
            cl.show(pnl, "img"+ (this.galleryItem.getId() - 1));
            System.out.println("img"+ (this.galleryItem.getId() - 1));

        });

    }

    public void setImage(Image image) {
        this.image = image;
        lbl.setIcon(new ImageIcon(this.galleryItem.getImage().getScaledInstance(380,500, Image.SCALE_SMOOTH)));
        lbl.setSize(new Dimension(GalleryController.DEFAULT_GALLERY_WIDTH,GalleryController.DEFAULT_GALLERY_HEIGHT));
        //lbl.setIcon(new ImageIcon(gi.getResizedIimage(GalleryController.DEFAULT_GALLERY_WIDTH,GalleryController.DEFAULT_GALLERY_HEIGHT)));
        repaint();
    }

    public void setGallerySize(int size) {
        this.gallerySize = size;
    }

    public void setGalleryItem(GalleryItem gi) {
        this.galleryItem = gi;
        lbl.setIcon(new ImageIcon(this.galleryItem.getImage().getScaledInstance(380,500, Image.SCALE_SMOOTH)));
        lbl.setSize(new Dimension(GalleryController.DEFAULT_GALLERY_WIDTH,GalleryController.DEFAULT_GALLERY_HEIGHT));
        //lbl.setIcon(new ImageIcon(gi.getResizedIimage(GalleryController.DEFAULT_GALLERY_WIDTH,GalleryController.DEFAULT_GALLERY_HEIGHT)));
        repaint();
    }
}
