package apps.gallery;

import javax.swing.*;
import java.awt.*;

public class GalleryItem {
    private Image image;

    public GalleryItem(Image image) {
        this.image = image;
    }

    public Image getImage() {
        return image;
    }

    public Image getResizedIimage(int width, int height) {
        return this.image.getScaledInstance(width, height,
                Image.SCALE_SMOOTH);
    }
}
