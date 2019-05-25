package apps.gallery;

import java.awt.*;
import java.util.Random;

public class GalleryItem {
    private Image image;
    private int id;

    public GalleryItem(Image image) {
        this.image = image;
        //this.id = new Random().nextInt(Integer.MAX_VALUE);;
    }

    public Image getImage() {
        return image;
    }

    public Image getResizedIimage(int width, int height) {
        return this.image.getScaledInstance(width, height,
                Image.SCALE_SMOOTH);
    }

    @Override
    public String toString() {
        return this.image.toString();
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
