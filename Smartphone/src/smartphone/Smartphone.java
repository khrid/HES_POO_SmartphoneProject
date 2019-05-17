package smartphone;

import javax.swing.*;
import java.awt.*;

public class Smartphone extends JFrame {

    private final CardLayout cards = new CardLayout();
    private final JPanel pnlMulti = new JPanel(cards);
    private static String activePanel = "launcher";
    private JPanel pnlQuicklaunch = new JPanel(new GridLayout(1, 5));
    private JButton btnHomeButton = new JButton("Home");
    private JPanel pnlSouth = new JPanel(new BorderLayout());
    private JButton btnApps = new JButton("Apps");

    public Smartphone() {

        setDefaultCloseOperation(EXIT_ON_CLOSE); // pour stopper le process lors du clic sur la croix rouge
        setTitle("MySmartphone"); // titre de la fenêtre
        setSize(new Dimension(400, 600));
        setResizable(false);

        Launcher pnlLauncher = new Launcher(cards, pnlMulti);
        pnlMulti.add(pnlLauncher, "launcher");

        Drawer pnlDrawer = new Drawer(cards, pnlMulti);
        pnlMulti.add(pnlDrawer, "drawer");

        JPanel pnlHomeButton = new JPanel();
        pnlHomeButton.add(btnHomeButton);
        btnHomeButton.addActionListener(e -> {
            cards.show(pnlMulti, "launcher");
            pnlDrawer.getDrawerCards().show(pnlDrawer.getPnlApps(), "main");
            System.out.println("Home button clicked, resetting Drawer.");
            pnlQuicklaunch.setVisible(true);
        });


        add(pnlMulti, BorderLayout.CENTER);

        pnlQuicklaunch.add(new JButton("Tél"));
        pnlQuicklaunch.add(new JButton("News"));
        pnlQuicklaunch.add(new JButton("Contacts"));
        pnlQuicklaunch.add(new JButton("Maps"));
        pnlQuicklaunch.add(btnApps);

        btnApps.addActionListener(e -> {
            System.out.println(Smartphone.getActivePanel());
            System.out.println("Clic show apps");
            cards.show(pnlMulti, "drawer");
            setActivePanel("drawer");
            System.out.println(Smartphone.getActivePanel());
            pnlQuicklaunch.setVisible(false);
        });


        pnlSouth.add(pnlQuicklaunch, BorderLayout.NORTH);
        pnlSouth.add(pnlHomeButton, BorderLayout.SOUTH);
        add(pnlSouth, BorderLayout.SOUTH);
    }

    public static String getActivePanel() {
        return activePanel;
    }

    public static void setActivePanel(String activePanel) {
        Smartphone.activePanel = activePanel;
    }
}
