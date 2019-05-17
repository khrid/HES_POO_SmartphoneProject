import smartphone.Smartphone;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        System.out.println("Starting smartphone...");

        try { // Pour utiliser l'app    arence du système et non le look dégeu par défaut de Java
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch(Exception ignored){}
        /*SmartphoneOld s = new SmartphoneOld();
        s.setVisible(true);*/
        Smartphone sr = new Smartphone();
        sr.setVisible(true);

    }
}
