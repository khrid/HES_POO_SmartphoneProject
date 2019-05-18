import smartphone.Smartphone;

import javax.swing.*;
import java.util.logging.Logger;

public class Main {
    private static Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        logger.info("Starting smartphone");

        try { // Pour utiliser l'app    arence du système et non le look dégeu par défaut de Java
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch(Exception ignored){}
        /*SmartphoneOld s = new SmartphoneOld();
        s.setVisible(true);*/
        Smartphone sr = new Smartphone();
        sr.setVisible(true);

    }
}
