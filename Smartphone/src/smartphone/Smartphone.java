package smartphone;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Smartphone extends JFrame {
    private JButton btnDrawer = new JButton("Show apps");
    private Launcher launcher = new Launcher();
    private Drawer drawer = new Drawer();
    public Smartphone() {
        setDefaultCloseOperation(EXIT_ON_CLOSE); // pour stopper le process lors du clic sur la croix rouge
        setTitle("MySmartphone"); // titre de la fenêtre
        setSize(new Dimension(400,600));

        DrawerListener dl = new DrawerListener();
        btnDrawer.addActionListener(dl);

        add(null, BorderLayout.NORTH);
        add(launcher, BorderLayout.CENTER);
        add(btnDrawer, BorderLayout.SOUTH);
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
}
