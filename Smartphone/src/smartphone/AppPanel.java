package smartphone;

import javax.swing.*;
import java.awt.*;

public class AppPanel extends JPanel {
    private String appName = "";
    public AppPanel() {
        setBackground(Color.GREEN);
    }

    public AppPanel(String appName) {
        this.appName = appName;
        setBackground(Color.GREEN);
        JLabel lblName = new JLabel(this.appName);
        add(lblName);
    }
}
