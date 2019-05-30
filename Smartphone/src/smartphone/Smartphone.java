package smartphone;

import apps.gallery.GalleryItemPath;
import ext.Kernel32;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Smartphone extends JFrame {

    private final CardLayout cards = new CardLayout();
    private final JPanel pnlMulti = new JPanel(cards);
    private Launcher pnlLauncher;
    private JPanel pnlQuicklaunch = new JPanel(new GridLayout(1, 5));
    private JLabel lblClock = new JLabel("", SwingConstants.LEFT);
    private JLabel lblBattery = new JLabel("", SwingConstants.RIGHT);

    public Smartphone() {

        // Pour stopper l'application au clic sur Close
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        // Titre de la fenêtre
        setTitle("MySmartphone");
        // Taille de l'écran
        setSize(new Dimension(400, 600));
        // Pour ne pas pouvoir redimensionner
        setResizable(false);

        // Construction du Launcher ( = homescreen)
        pnlLauncher = new Launcher(cards, pnlMulti);
        // Ajout du label qui contient l'image en fond d'écran
        JLabel lblBackground = new JLabel("");
        pnlLauncher.add(lblBackground);

        // Ajout du panel au cardLayout
        pnlMulti.add(pnlLauncher, "launcher");

        // Construction du Drawer ( => liste des applications)
        Drawer pnlDrawer = new Drawer(cards, pnlMulti, this);
        // Ajout du panel au cardLayout
        pnlMulti.add(pnlDrawer, "drawer");

        // Panel contenant le bouton Home
        JPanel pnlHomeButton = new JPanel();
        // Ajout du bouton sur le panel
        JButton btnHomeButton = new JButton("Home");
        pnlHomeButton.add(btnHomeButton);
        // Gestion de l'event sur le clic du bouton "Home"
        btnHomeButton.addActionListener(e -> {
            // On montre le panel "launcher" sur le cartLayout
            cards.show(pnlMulti, "launcher");
            // On reset également le cardLayout des applications
            pnlDrawer.getDrawerCards().show(pnlDrawer.getPnlApps(), "main");
            // On affiche le quicklaunch avec les boutons
            pnlQuicklaunch.setVisible(true);
            // on
            //pnlMulti.remove(pnlDrawer);
            //pnlMulti.add(new Drawer(cards, pnlMulti, this), "drawer");
        });


        // Ajout du panel contenant le cardLayout
        add(pnlMulti, BorderLayout.CENTER);

        // Ajout des faux boutons du quicklaunch + bouton apps
        pnlQuicklaunch.add(new JButton("Tél"));
        pnlQuicklaunch.add(new JButton("News"));
        pnlQuicklaunch.add(new JButton("Contacts"));
        pnlQuicklaunch.add(new JButton("Maps"));
        JButton btnApps = new JButton("Apps");
        pnlQuicklaunch.add(btnApps);

        // Event sur le clic du bouton Apps
        btnApps.addActionListener(e -> {
            // Affichage du panel "drawer"
            cards.show(pnlMulti, "drawer");
            // Masquage des boutons quicklaunch
            pnlQuicklaunch.setVisible(false);
        });

        // Formatter pour l'affichage de l'heure dans la barre du haut
        DateFormat format = new SimpleDateFormat("HH:mm:ss");
        // Affichage en gras
        //lblClock.setFont(lblClock.getFont().deriveFont(Font.BOLD));
        lblClock.setAlignmentY(JLabel.CENTER_ALIGNMENT);


        // Population de la bare du haut avec les labels
        JPanel pnlTop = new JPanel(new GridLayout(0, 5, 10, 10));
        pnlTop.add(lblClock);
        pnlTop.add(new JLabel(""));
        pnlTop.add(new JLabel(""));
        pnlTop.add(new JLabel(""));
        pnlTop.add(lblBattery);

        // Ajout dans le panel du sud : quicklaunch + home
        JPanel pnlSouth = new JPanel(new BorderLayout());
        pnlSouth.add(pnlQuicklaunch, BorderLayout.NORTH);
        pnlSouth.add(pnlHomeButton, BorderLayout.SOUTH);

        // Ajout des deux panels du haut et du bas dans le panel principal
        add(pnlTop, BorderLayout.NORTH);
        add(pnlSouth, BorderLayout.SOUTH);

        // Gestion du "fond d'écran", si on l'avait déjà configuré on le reprend
        File f = new File("D:/tmp/o.ser");
        ObjectInputStream is;
        if (f.exists() && !f.isDirectory()) {
            GalleryItemPath gipFromDisk = null;
            try {
                is = new ObjectInputStream(new FileInputStream("D:/tmp/o.ser"));
                gipFromDisk = (GalleryItemPath) is.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            assert gipFromDisk != null;
            pnlLauncher.updateBackground(gipFromDisk);
        }

        // Gestion de l'affichage du pourcentage de batterie + heure
        Kernel32.SYSTEM_POWER_STATUS batteryStatus = new Kernel32.SYSTEM_POWER_STATUS();
        Kernel32.INSTANCE.GetSystemPowerStatus(batteryStatus);
        // Pour avoir les infos dès le lancement, et non quelques secondes après
        lblClock.setText(format.format(new Date()));
        lblBattery.setText(batteryStatus.getBatteryLifePercent());
        // Configuration du timer de mise à jour des infos
        Timer t = new Timer(1000, e -> {
            lblClock.setText(format.format(new Date()));
            lblBattery.setText(batteryStatus.getBatteryLifePercent());
        });
        // Démarrage du timer
        t.start();
    }

    /**
     * Permet la mise à jour du fond d'écran du téléphone
     *
     * @param gi l'image à utiliser
     */
    public void updateBackground(GalleryItemPath gi) {
        // On met à jour le fond d'écran dans le panel
        pnlLauncher.updateBackground(gi);

        // on tente d'écrire l'image actuelle sérialisée dans un stream
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

    }
}
