package smartphone;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SmartphoneOld extends JFrame {
    /*private JButton btnHome = new JButton("Home");
    private Launcher launcher = new Launcher(null, null);
    private JButton btnDrawer = new JButton("Show apps");
    private Drawer drawer = new Drawer();

    private JPanel panelButtons = new JPanel();
    private JPanel panelQuickLaunch = new JPanel();*/


    public SmartphoneOld() {
        /*setDefaultCloseOperation(EXIT_ON_CLOSE); // pour stopper le process lors du clic sur la croix rouge
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

        add(panelButtons, BorderLayout.SOUTH);*/

    }

    /*public void hideQuickLaunch(){
        panelQuickLaunch.setVisible(false);
        //panelButtons.remove(panelQuickLaunch);
    }

    public class btnHomeListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            remove(launcher);
            remove(drawer);
            launcher=null;
            drawer=null;
            launcher = new Launcher(null, null);
            drawer = new Drawer();
            add(launcher);

            panelQuickLaunch.setVisible(true);

            revalidate();
            repaint();
        }
    }

    public class DrawerListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if(!drawer.isVisible()) {
                remove(launcher);
                add(drawer);
                btnDrawer.setText("Hide apps");
            } else {
                remove(drawer);
                add(launcher);
                btnDrawer.setText("Show apps");
            }
            drawer.setVisible(!drawer.isVisible());
            revalidate();
            repaint();
        }
    }
*/
}
