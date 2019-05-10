package smartphone;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Smartphone extends JFrame {
    //private JButton btnDrawer = new JButton("Show apps"); --> to delete
    private JButton btnHome = new JButton("Home");
    private Launcher launcher = new Launcher();
    //private Drawer drawer = new Drawer(); --> to delete
    public Smartphone() {
        setDefaultCloseOperation(EXIT_ON_CLOSE); // pour stopper le process lors du clic sur la croix rouge
        setTitle("MySmartphone"); // titre de la fenêtre
        setSize(new Dimension(400,600));

        btnHomeListener dl = new btnHomeListener();
        btnHome.addActionListener(dl);

        add(launcher, BorderLayout.CENTER);
        add(btnHome, BorderLayout.SOUTH);

    }

    public Drawer getDrawer() {
        return drawer;
    }

    public class btnHomeListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            remove(launcher);
            launcher=null;
            launcher = new Launcher();
            add(launcher);
            revalidate();
            repaint();
        }
    }
}
