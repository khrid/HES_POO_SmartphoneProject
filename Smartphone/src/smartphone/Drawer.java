package smartphone;

import apps.App;
import apps.test.TestPanel;
import apps.test2.Test2Panel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Drawer extends AppPanel {
    private boolean isVisible = false;
    private JPanel pnlDrawer = new JPanel();
    public Drawer() {
        setLayout(new BorderLayout());

        ArrayList<App> apps = new ArrayList<App>();
        //apps.add(new App("Contacts"));
        //apps.add(new App("Galerie"));
        apps.add(new App("Test", new TestPanel()));
        apps.add(new App("Test2", new Test2Panel()));

        for (App a : apps
             ) {
            JButton btn = new JButton(a.getName());
            //btn.setSize(new Dimension(100,50));
            btn.addActionListener(e -> {
                System.out.println(this);
                System.out.println("Clic bouton");
                this.removeAll();
                Drawer.this.add(a.getPanel(), BorderLayout.CENTER);
                revalidate();
                repaint();
            });
            pnlDrawer.add(btn, BorderLayout.NORTH);
        }

        System.out.println("smartphone.Drawer created");
        add(pnlDrawer);
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
