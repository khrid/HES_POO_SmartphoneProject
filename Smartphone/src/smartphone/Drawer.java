package smartphone;

import apps.App;
import apps.contacts.ContactsMain;
import apps.gallery.GalleryPanel;
import apps.test.TestPanel;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.text.ParseException;
import java.util.ArrayList;

public class Drawer extends AppPanel {
    private boolean isVisible = false;
    private CardLayout drawerCards = new CardLayout();
    private JPanel pnlApps = new JPanel(drawerCards);
    private JPanel pnlDrawer = new JPanel(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();


    public Drawer(CardLayout cards, JPanel pnlCards, Smartphone sm) throws ParseException {
        super(cards, pnlCards);
        setLayout(new BorderLayout());

        c.gridx = c.gridy = 0;

        ArrayList<App> apps = new ArrayList<>();
        apps.add(new App("Contacts", new ContactsMain("Contacts")));
        apps.add(new App("Gallery", new GalleryPanel("Gallery", sm)));
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
            btn.setPreferredSize(new Dimension(100,100));

            c.insets = new Insets(10,10,10,10);
            pnlDrawer.add(btn, c);
            if(c.gridx < 2) {
                c.gridx++;
            } else {
                c.gridx = 0;
                c.gridy++;
            }
        }
        pnlApps.add(pnlDrawer, "main");
        add(pnlApps);
        drawerCards.show(pnlApps, "main");
        setOpaque(true);
        repaint();
    }

    public CardLayout getDrawerCards() {
        return drawerCards;
    }

    public JPanel getPnlApps() {
        return pnlApps;
    }
}
