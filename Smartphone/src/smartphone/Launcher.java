package smartphone;

import javax.swing.*;
import java.awt.*;

public class Launcher extends JPanel {
    public Launcher() {
        System.out.println("smartphone.Launcher created");
        JLabel lblTest = new JLabel("LAUNCHER");
        add(lblTest);
        setBackground(Color.RED);
    }
}
