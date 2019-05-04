import javax.swing.*;
import java.awt.*;

public class Drawer extends JPanel {
    private boolean isVisible = false;
    public Drawer() {

        JLabel lblTest = new JLabel("DRAWER");
        add(lblTest);
        setBackground(Color.YELLOW);
    }

    @Override
    public boolean isVisible() {
        return isVisible;
    }

    @Override
    public void setVisible(boolean visible) {
        isVisible = visible;
    }
}
