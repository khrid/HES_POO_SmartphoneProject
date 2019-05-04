import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Drawer extends JPanel {
    private boolean isVisible = false;
    public Drawer() {

        ArrayList<App> apps = new ArrayList<App>();
        apps.add(new App("Contacts"));
        apps.add(new App("Galerie"));
        apps.add(new App("Contacts"));
        apps.add(new App("Galerie"));
        apps.add(new App("Contacts"));
        apps.add(new App("Galerie"));
        apps.add(new App("Contacts"));
        apps.add(new App("Galerie"));

        for (App a : apps
             ) {
            JButton btn = new JButton(a.getName());
            btn.setSize(new Dimension(100,50));
            add(btn);
        }

        System.out.println("Drawer created");
        //JLabel lblTest = new JLabel("DRAWER");
        //add(lblTest);
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
