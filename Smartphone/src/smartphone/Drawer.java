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
    private Smartphone parentSmartphone;
    public Drawer(Smartphone parentSmartphone) {
        setLayout(new BorderLayout());

        this.parentSmartphone=parentSmartphone;

        ArrayList<App> apps = new ArrayList<App>();
        //apps.add(new App("Contacts"));
        //apps.add(new App("Galerie"));
        apps.add(new App("Test", new TestPanel("Test")));
        apps.add(new App("Test2", new Test2Panel("Test2")));
        apps.add(new App("Google", new AppPanel("Google")));
        apps.add(new App("Gmail", new AppPanel("Gmail")));
        apps.add(new App("Maps", new AppPanel("Maps")));
        apps.add(new App("Message", new AppPanel("Message")));

        for (App a : apps
             ) {
            JButton btn = new JButton(a.getName());
            btn.setSize(new Dimension(20,20));
            btn.addActionListener(e -> {
                System.out.println(this);
                System.out.println("Clic bouton");
                this.removeAll();
                Drawer.this.add(a.getPanel(), BorderLayout.CENTER);
                System.out.println();
                parentSmartphone.hideQuickLaunch();
                revalidate();
                repaint();
            });
            pnlDrawer.add(btn);
        }

        pnlDrawer.setOpaque(true);
        pnlDrawer.setLayout(new GridLayout(3,3));
        System.out.println("smartphone.Drawer created");
        add(pnlDrawer);
        //JLabel lblTest = new JLabel("DRAWER");
        //add(lblTest);
        setOpaque(false);
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
