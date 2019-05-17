package apps.gallery;

import smartphone.AppPanel;

import java.awt.*;

public class GalleryPanel extends AppPanel {
    public GalleryPanel(String appName) {
        super(appName);
        System.out.println("Creating GalleryPanel");
        setBackground(Color.pink);
    }
}
