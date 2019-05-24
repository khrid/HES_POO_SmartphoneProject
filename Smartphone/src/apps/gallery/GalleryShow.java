package apps.gallery;

import javax.swing.*;
import java.awt.*;

public class GalleryShow extends JPanel {
    private Image image;
    JLabel lbl = new JLabel("TEST");
    public GalleryShow(CardLayout cl, JPanel pnl) {
        setLayout(new BorderLayout());
        JPanel pnlActions = new JPanel();
        JButton btnBack = new JButton("Back");
        pnlActions.setLayout(new GridLayout(0, 5));
        pnlActions.add(btnBack);
        add(lbl);
        add(pnlActions, BorderLayout.SOUTH);
        btnBack.addActionListener(e -> {
            cl.show(pnl, "gallery");
        });

    }

    public void setImage(Image image) {
        this.image = image;
        lbl.setIcon(new ImageIcon(this.image));
        repaint();
    }
}
