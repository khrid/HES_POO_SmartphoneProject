import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        try { // Pour utiliser l'app    arence du système et non le look dégeu par défaut de Java
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch(Exception ignored){}
        Smartphone s = new Smartphone();
        s.setVisible(true);
        System.out.println("Test");
        System.out.println("Test 2");
    }
}
