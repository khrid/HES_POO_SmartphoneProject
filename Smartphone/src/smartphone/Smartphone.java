package smartphone;

import apps.gallery.GalleryController;
import apps.gallery.GalleryItem;
import ext.Kernel32;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;

public class Smartphone extends JFrame {

    public static final String ROOT_DIR = "C:/tmp/smartphone/";
    private final CardLayout cards = new CardLayout();
    private final JPanel pnlMulti = new JPanel(cards);
    private Launcher pnlLauncher;
    private JPanel pnlQuicklaunch = new JPanel(new GridLayout(1, 5, 5, 0));
    private JLabel lblClock = new JLabel("", SwingConstants.LEFT);
    private JLabel lblBattery = new JLabel("", SwingConstants.RIGHT);
    private GalleryController gc;


    public Smartphone() throws ParseException {

        // Pour stopper l'application au clic sur Close
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        // Titre de la fenêtre
        setTitle("MySmartphone");
        // Taille de l'écran
        setSize(new Dimension(400, 600));
        // Pour ne pas pouvoir redimensionner
        setResizable(false);

        // Check structure de fichiers
        File directory = new File(ROOT_DIR);
        if (!directory.exists()) {
            directory.mkdirs();
            String[] subdirs = {"gallery", "contacts", "res"};
            for (int i = 0; i < subdirs.length; i++) {
                new File(ROOT_DIR + subdirs[i]).mkdir();
            }
            try {
                File contactFile = new File(ROOT_DIR+"/contacts/contacts.xml");
                contactFile.createNewFile();
                String contactXml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><contacts></contacts>";

                Files.write(Paths.get(ROOT_DIR+"contacts/contacts.xml"), Collections.singleton(contactXml), StandardCharsets.UTF_8);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        gc = new GalleryController();

        // Construction du Launcher ( = homescreen)
        pnlLauncher = new Launcher(cards, pnlMulti);
        // Ajout du label qui contient l'image en fond d'écran
        JLabel lblBackground = new JLabel("");
        pnlLauncher.add(lblBackground);
        //pnlLauncher.setBackground(Color.YELLOW);

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

        // Formatter pour l'affimchage de l'heure dans la barre du haut
        DateFormat formatHms = new SimpleDateFormat("HH:mm:ss");
        DateFormat formatH = new SimpleDateFormat("HH:mm");
        DateFormat formatDate = new SimpleDateFormat("E d MMMMM");
        // Affichage en gras
        //lblClock.setFont(lblClock.getFont().deriveFont(Font.BOLD));
        lblClock.setAlignmentY(JLabel.CENTER_ALIGNMENT);


        // Population de la bare du haut avec les labels
        JPanel pnlTop = new JPanel(new GridLayout(0, 5));
        Color colBackground = Color.decode("#696a6d");
        pnlTop.setBackground(colBackground);
        pnlTop.setOpaque(true);
        pnlTop.add(lblClock);
        pnlTop.add(new JLabel(""));
        pnlTop.add(new JLabel(""));
        pnlTop.add(new JLabel(""));
        pnlTop.add(lblBattery);
        pnlTop.setBorder(new EmptyBorder(5, 5, 5, 5));

        lblClock.setForeground(Color.WHITE);
        lblBattery.setForeground(Color.WHITE);
        lblClock.setFont(lblClock.getFont().deriveFont(Font.BOLD, 10f));
        lblBattery.setFont(lblBattery.getFont().deriveFont(Font.BOLD, 10f));


        // Ajout dans le panel du sud : quicklaunch + home
        JPanel pnlSouth = new JPanel(new BorderLayout());
        pnlSouth.add(pnlQuicklaunch, BorderLayout.NORTH);
        pnlSouth.add(pnlHomeButton, BorderLayout.SOUTH);
        pnlQuicklaunch.setBackground(colBackground);
        pnlQuicklaunch.setOpaque(true);
        pnlQuicklaunch.setBorder(new EmptyBorder(5, 5, 5, 5));
        pnlHomeButton.setBackground(colBackground);
        pnlHomeButton.setOpaque(true);

        // Ajout des deux panels du haut et du bas dans le panel principal
        add(pnlTop, BorderLayout.NORTH);
        add(pnlSouth, BorderLayout.SOUTH);

        setBackground(colBackground);

        // Gestion du "fond d'écran", si on l'avait déjà configuré on le reprend
        File f = new File(ROOT_DIR+"/res/background.ser");
        ObjectInputStream is;
        if (f.exists() && !f.isDirectory()) {
            GalleryItem gipFromDisk = null;
            try {
                is = new ObjectInputStream(new FileInputStream(f));
                gipFromDisk = (GalleryItem) is.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            assert gipFromDisk != null;
            if (Files.exists(Paths.get(GalleryController.GALLERY_LOCATION+ gipFromDisk.getPath()))) {
                pnlLauncher.updateBackground(gipFromDisk);
            }
        }

        // Gestion de l'affichage du pourcentage de batterie + heure
        Kernel32.SYSTEM_POWER_STATUS batteryStatus = new Kernel32.SYSTEM_POWER_STATUS();
        Kernel32.INSTANCE.GetSystemPowerStatus(batteryStatus);
        // Pour avoir les infos dès le lancement, et non quelques secondes après
        lblClock.setText(formatHms.format(new Date()));
        pnlLauncher.updateClock(formatH.format(new Date()), formatDate.format(new Date()));
        lblBattery.setText(batteryStatus.getBatteryLifePercent());
        // Configuration du timer de mise à jour des infos
        Timer t = new Timer(1000, e -> {
            lblClock.setText(formatHms.format(new Date()));
            pnlLauncher.updateClock(formatH.format(new Date()), formatDate.format(new Date()));
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
    public void updateBackground(GalleryItem gi) {
        // On met à jour le fond d'écran dans le panel
        pnlLauncher.updateBackground(gi);

        // Pour s'assurer que le fichier background existe
        //URL u = getClass().getClassLoader().getResource("res/background.ser");
        File f = new File(ROOT_DIR+"/res/background.ser");
        try {
            f.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // on tente d'écrire l'image actuelle sérialisée dans un stream
        try {
            ObjectOutputStream oos = new ObjectOutputStream(
                    new FileOutputStream(f));
            oos.writeObject(gi);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public GalleryController getGalleryController() {
        return this.gc;
    }
}
