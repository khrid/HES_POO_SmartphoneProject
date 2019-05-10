package smartphone;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Launcher extends AppPanel {

    public Launcher() {
        System.out.println("smartphone.Launcher created");
        JLabel lblTest = new JLabel("LAUNCHER");
        add(lblTest);
        setBackground(Color.RED);

    }
}
