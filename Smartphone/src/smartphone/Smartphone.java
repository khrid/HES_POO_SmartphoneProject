package smartphone;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Smartphone extends JFrame {
    private JButton btnHome = new JButton("Home");
    private Launcher launcher = new Launcher();
    private JButton btnDrawer = new JButton("Show apps");
    private Drawer drawer = new Drawer(this);

    private JPanel panelButtons = new JPanel();
    private JPanel panelQuickLaunch = new JPanel();

    private void goHome(){
        drawer.initializeDrawer();
        drawer.setVisible(false);
        remove(drawer);
        add(launcher);
        btnDrawer.setText("Show apps");
        showQuickLaunch();
    }

    public Smartphone() {
        setDefaultCloseOperation(EXIT_ON_CLOSE); // pour stopper le process lors du clic sur la croix rouge
        setTitle("MySmartphone"); // titre de la fenêtre
        setSize(new Dimension(400,600));

        btnHomeListener dl = new btnHomeListener();
        btnHome.addActionListener(dl);

        DrawerListener d2 = new DrawerListener();
        btnDrawer.addActionListener(d2);

        add(launcher, BorderLayout.CENTER);

        panelButtons.setLayout(new BorderLayout());

        panelQuickLaunch.setLayout(new GridLayout(1,5));

        panelQuickLaunch.add(new JButton("Tél"));
        panelQuickLaunch.add(new JButton("News"));
        panelQuickLaunch.add(new JButton("Contacts"));
        panelQuickLaunch.add(new JButton("Maps"));
        panelQuickLaunch.add(btnDrawer);

        panelButtons.add(panelQuickLaunch, BorderLayout.NORTH);
        panelButtons.add(btnHome, BorderLayout.SOUTH);

        add(panelButtons, BorderLayout.SOUTH);

    }

    public void hideQuickLaunch(){
        panelQuickLaunch.setVisible(false);
    }

    public void showQuickLaunch(){
        panelQuickLaunch.setVisible(true);
    }

    public class btnHomeListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            goHome();
            System.out.println("Button Home clicked");
            revalidate();
            repaint();
        }
    }

    public class DrawerListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // A VOIR
            System.out.println(drawer.getParent());
            System.out.println(this);

            if(!drawer.isVisible()) {
                remove(launcher);
                add(drawer);
                btnDrawer.setText("Hide apps");
                drawer.setVisible(true);
            } else {
                goHome();
            }
            revalidate();
            repaint();
        }
    }

}
