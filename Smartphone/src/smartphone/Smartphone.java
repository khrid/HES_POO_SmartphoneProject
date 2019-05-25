package smartphone;

import apps.gallery.GalleryController;
import apps.gallery.GalleryItem;
import apps.gallery.GalleryItemPath;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.logging.Logger;

public class Smartphone extends JFrame {

    private static Logger logger = Logger.getLogger(Smartphone.class.getName());
    private final CardLayout cards = new CardLayout();
    private final JPanel pnlMulti = new JPanel(cards);
    private static String activePanel = "launcher";
    private Launcher pnlLauncher;
    private JPanel pnlQuicklaunch = new JPanel(new GridLayout(1, 5));
    private JLabel lblBackground = new JLabel("");
    private JButton btnHomeButton = new JButton("Home");
    private JPanel pnlTop = new JPanel(new GridLayout(0,5));
    private JPanel pnlSouth = new JPanel(new BorderLayout());
    private JButton btnApps = new JButton("Apps");

    public Smartphone() {

        setDefaultCloseOperation(EXIT_ON_CLOSE); // pour stopper le process lors du clic sur la croix rouge
        setTitle("MySmartphone"); // titre de la fenêtre
        setSize(new Dimension(400, 600));
        setResizable(false);

        pnlLauncher = new Launcher(cards, pnlMulti);
        pnlLauncher.add(lblBackground);
        pnlMulti.add(pnlLauncher, "launcher");

        Drawer pnlDrawer = new Drawer(cards, pnlMulti, this);
        pnlMulti.add(pnlDrawer, "drawer");

        JPanel pnlHomeButton = new JPanel();
        pnlHomeButton.add(btnHomeButton);
        btnHomeButton.addActionListener(e -> {
            cards.show(pnlMulti, "launcher");
            pnlDrawer.getDrawerCards().show(pnlDrawer.getPnlApps(), "main");
            logger.info("Home button clicked, resetting Drawer.");
            pnlQuicklaunch.setVisible(true);
            pnlMulti.remove(pnlDrawer);
            pnlMulti.add(new Drawer(cards, pnlMulti, this), "drawer");
        });


        add(pnlMulti, BorderLayout.CENTER);

        pnlQuicklaunch.add(new JButton("Tél"));
        pnlQuicklaunch.add(new JButton("News"));
        pnlQuicklaunch.add(new JButton("Contacts"));
        pnlQuicklaunch.add(new JButton("Maps"));
        pnlQuicklaunch.add(btnApps);

        btnApps.addActionListener(e -> {
            logger.info("Clic show apps");
            cards.show(pnlMulti, "drawer");
            setActivePanel("drawer");
            pnlQuicklaunch.setVisible(false);
        });

        pnlTop.add(new JLabel("hevs.ch"));
        pnlTop.add(new JLabel(""));
        pnlTop.add(new JLabel(""));
        pnlTop.add(new JLabel(""));
        pnlTop.add(new JLabel("09:40"));

        pnlTop.setBackground(new Color((int)(Math.random() * 0x1000000)));

        pnlSouth.add(pnlQuicklaunch, BorderLayout.NORTH);
        pnlSouth.add(pnlHomeButton, BorderLayout.SOUTH);

        add(pnlTop, BorderLayout.NORTH);
        add(pnlSouth, BorderLayout.SOUTH);


        File f = new File("D:/tmp/o.ser");
        ObjectInputStream is = null;
        GalleryItemPath gipFormDisk = null;
        if(f.exists() && !f.isDirectory()) {
            // do something
            try {
                is = new ObjectInputStream(new FileInputStream("D:/tmp/o.ser"));
                gipFormDisk = (GalleryItemPath) is.readObject();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            pnlLauncher.updateBackground(gipFormDisk);
        }
    }

    public static String getActivePanel() {
        return activePanel;
    }

    public static void setActivePanel(String activePanel) {
        Smartphone.activePanel = activePanel;
        logger.info("Active panel : "+Smartphone.getActivePanel());
    }

    public void updateBackground(GalleryItemPath gi) {
        // TODO gérer sérialisation

        try {
            ObjectOutputStream oos = new ObjectOutputStream(
                    // By using "FileOutputStream" we will
                    // Write it to a File in the file system
                    // It could have been a Socket to another
                    // machine, a database, an in memory array, etc.
                    new FileOutputStream(new File("D:/tmp/o.ser")));

            oos.writeObject(gi);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        pnlLauncher.updateBackground(gi);
    }
}
