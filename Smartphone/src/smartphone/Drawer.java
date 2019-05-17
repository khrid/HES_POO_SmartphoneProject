package smartphone;

import apps.App;
import apps.contacts.ContactsPanel;
import apps.gallery.GalleryPanel;
import apps.test.TestPanel;
import apps.test2.Test2Panel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Drawer extends AppPanel {
    private boolean isVisible = false;
    private JPanel pnlDrawer = new JPanel();
    private Smartphone parentSmartphone;
    private ArrayList<App> apps;

    public Drawer(Smartphone parentSmartphone) {
        setLayout(new BorderLayout());

        this.parentSmartphone=parentSmartphone;
        apps = new ArrayList<App>();
        apps.add(new App("Contacts", new ContactsPanel("Contacts")));
        apps.add(new App("Gallery", new GalleryPanel("Gallery")));
        apps.add(new App("Test", new TestPanel("Test")));
        apps.add(new App("Test2", new Test2Panel("Test2")));
        apps.add(new App("Maps", new AppPanel("Maps")));
        apps.add(new App("Message", new AppPanel("Message")));

        initializeDrawer();

        pnlDrawer.setOpaque(true);
        pnlDrawer.setLayout(new GridLayout(3,3));
        System.out.println("smartphone.Drawer created");
        add(pnlDrawer);
        //JLabel lblTest = new JLabel("DRAWER");
        //add(lblTest);
        setOpaque(false);
        setBackground(Color.YELLOW);
    }

    public void initializeDrawer(){
        pnlDrawer.removeAll();
        this.removeAll();
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
        add(pnlDrawer);
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
