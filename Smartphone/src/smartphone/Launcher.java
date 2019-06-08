package smartphone;

import apps.gallery.GalleryController;
import apps.gallery.GalleryItemPath;

import javax.swing.*;
import java.awt.*;

public class Launcher extends AppPanel {


    JLabel lblBackground = new JLabel("");
    JLabel lblHeure = new JLabel("HEURE");
    JLabel lblDate = new JLabel("test");
    public Launcher(CardLayout cards, JPanel pnlMulti) {
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

    public void updateClock(String clock, String date) {
        lblHeure.setText(clock);
        lblDate.setText(date);
    }


    public void updateBackground(GalleryItemPath gi) {
        lblBackground.setIcon(new ImageIcon(gi.getImage().getScaledInstance(400,600, Image.SCALE_SMOOTH)));
        lblBackground.setSize(new Dimension(400,600));
        repaint();
    }
}
