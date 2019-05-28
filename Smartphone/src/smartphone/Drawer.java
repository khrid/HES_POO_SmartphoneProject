package smartphone;

import apps.App;
import apps.contacts.ContactsMain;
import apps.gallery.GalleryPanel;
import apps.test.TestPanel;

import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.util.ArrayList;

public class Drawer extends AppPanel {
    private boolean isVisible = false;
    private CardLayout drawerCards = new CardLayout();
    private JPanel pnlApps = new JPanel(drawerCards);
    private JPanel pnlDrawer = new JPanel(new GridLayout(3, 3));

    public Drawer(CardLayout cards, JPanel pnlCards) throws ParseException {
        super(cards, pnlCards);
        setLayout(new BorderLayout());


        ArrayList<App> apps = new ArrayList<>();
        apps.add(new App("Contacts", new ContactsMain("Contacts")));
        apps.add(new App("Gallery", new GalleryPanel("Gallery")));
        apps.add(new App("Test3", new TestPanel("Test3")));
        apps.add(new App("Test4", new TestPanel("Test4")));
        apps.add(new App("Test5", new TestPanel("Test5")));
        apps.add(new App("Test6", new TestPanel("Test6")));

        for (App a : apps) {
            pnlApps.add(a.getPanel(), a.getName());
            JButton btn = new JButton(a.getName());
            btn.addActionListener(e -> {
                drawerCards.show(pnlApps, a.getName());
                System.out.println("Changing active app : "+a.getName()+" is active.");
            });

            pnlDrawer.add(btn);

        }
        pnlApps.add(pnlDrawer, "main");
        add(pnlApps);
        drawerCards.show(pnlApps, "main");
        setOpaque(false);
    }

    public CardLayout getDrawerCards() {
        return drawerCards;
    }

    public JPanel getPnlApps() {
        return pnlApps;
    }
}
