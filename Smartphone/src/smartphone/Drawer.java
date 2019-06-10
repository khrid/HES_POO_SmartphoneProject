package smartphone;

import apps.App;
import apps.contacts.ContactsMain;
import apps.gallery.GalleryPanel;

import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.util.ArrayList;

class Drawer extends AppPanel {
    private CardLayout drawerCards = new CardLayout();
    private JPanel pnlApps = new JPanel(drawerCards);

    Drawer(CardLayout cards, JPanel pnlCards, Smartphone sm) throws ParseException {
        super(cards, pnlCards);
        setLayout(new BorderLayout());
        Color colBackground = Color.decode("#c6c9d1");
        pnlApps.setBackground(colBackground);
        JPanel pnlDrawer = new JPanel(new GridBagLayout());
        pnlDrawer.setBackground(colBackground);

        GridBagConstraints c = new GridBagConstraints();
        c.gridx = c.gridy = 0;
        c.anchor = GridBagConstraints.NORTH;

        ArrayList<App> apps = new ArrayList<>();
        apps.add(new App("Contacts", new ContactsMain("Contacts")));
        apps.add(new App("Gallery", new GalleryPanel("Gallery", sm)));

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

    CardLayout getDrawerCards() {
        return drawerCards;
    }

    JPanel getPnlApps() {
        return pnlApps;
    }
}
