import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

public class Launcher extends JPanel {
    public Launcher() {
        System.out.println("Launcher created");
        JLabel lblTest = new JLabel("LAUNCHER");
        add(lblTest);
    }
}
