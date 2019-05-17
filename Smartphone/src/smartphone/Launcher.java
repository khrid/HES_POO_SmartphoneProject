package smartphone;

import javax.swing.*;
import java.awt.*;

public class Launcher extends AppPanel {


    public Launcher(CardLayout cards, JPanel pnlMulti) {
        super(cards, pnlMulti);
        System.out.println("Launcher created");
        JLabel lblTest = new JLabel("LAUNCHER");
        add(lblTest);
    }
}
