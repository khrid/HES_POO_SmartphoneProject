package smartphone;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Launcher extends JPanel {

    private JButton btnDrawer = new JButton("Show apps");
    private Drawer drawer = new Drawer();

    public Launcher() {
        System.out.println("smartphone.Launcher created");
        JLabel lblTest = new JLabel("LAUNCHER");
        add(lblTest);
        setBackground(Color.RED);

        DrawerListener dl = new DrawerListener();
        btnDrawer.addActionListener(dl);

        add(btnDrawer, BorderLayout.SOUTH);
    }

    public class DrawerListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if(!drawer.isVisible()) {
                //remove(launcher);
                add(drawer);
                btnDrawer.setText("Hide apps");
            } else {
                remove(drawer);
                //add(launcher);
                btnDrawer.setText("Show apps");
            }
            drawer.setVisible(!drawer.isVisible());
            revalidate();
            repaint();
        }
    }
}
