package smartphone;

import apps.gallery.GalleryItem;

import javax.swing.*;
import java.awt.*;

/**
 *
 */
class Launcher extends AppPanel {


    private JLabel lblBackground = new JLabel("");
    private JLabel lblHeure = new JLabel("HEURE");
    private JLabel lblDate = new JLabel("test");
    Launcher(CardLayout cards, JPanel pnlMulti) {
        super(cards, pnlMulti);
        setLayout(new BorderLayout());
        System.out.println("Launcher created");
        add(lblBackground, BorderLayout.CENTER);
        lblHeure.setForeground(Color.WHITE);
        lblHeure.setFont(new Font("Calibri", Font.BOLD, 40));
        Dimension size = lblHeure.getPreferredSize();
        lblHeure.setBounds(150, 50, size.width+50, size.height);
        lblBackground.add(lblHeure, BorderLayout.CENTER);
        lblDate.setForeground(Color.WHITE);
        lblDate.setFont(new Font("Calibri", Font.BOLD, 20));
        size = lblDate.getPreferredSize();
        lblDate.setBounds(150, 90, size.width+100, size.height);
        lblBackground.add(lblDate, BorderLayout.CENTER);
    }


    /**
     * Mets à jour le contenu des JLabel contenant la date et l'heure
     * @param clock Valeur de l'heure
     * @param date Valeur de la date
     */
    void updateClock(String clock, String date) {
        lblHeure.setText(clock);
        lblDate.setText(date);
    }

    /**
     * Permet de mettre à jour le fond d'écran du Smartphone
     * @param gi GalleryItem à utiliser comme image de fond d'écran
     */
    void updateBackground(GalleryItem gi) {
        lblBackground.setIcon(new ImageIcon(gi.getImage().getScaledInstance(400,600, Image.SCALE_SMOOTH)));
        lblBackground.setSize(new Dimension(400,600));
        repaint();
    }
}
