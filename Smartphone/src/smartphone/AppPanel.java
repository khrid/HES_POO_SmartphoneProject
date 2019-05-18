package smartphone;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class AppPanel extends JPanel {
    private String appName = "";
    protected CardLayout cards;
    protected JPanel pnlCards;
    public AppPanel(CardLayout cards, JPanel pnlCards) {
        this.cards = cards;
        this.pnlCards = pnlCards;
    }

    public AppPanel(String appName) {
        this.appName = appName;
        setBackground(new Color((int)(Math.random() * 0x1000000)));
        JLabel lblName = new JLabel(this.appName);
    }
}
