import smartphone.Smartphone;

import javax.swing.*;
import java.text.ParseException;

public class Main {
    public static void main(String[] args) throws ParseException {
        try { // Pour utiliser l'app    arence du système et non le look dégeu par défaut de Java
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch(Exception ignored){}
        Smartphone sr = new Smartphone();
        sr.setVisible(true);

    }
}
