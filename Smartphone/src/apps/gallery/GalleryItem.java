package apps.gallery;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

public class GalleryItem implements Serializable {
    private static final long serialVersionUID = -2475636784399927553L;
    private String path;
    private int id;


    public GalleryItem(String path) {
        this.path = path;
    }

    public BufferedImage getImage() {
        BufferedImage img = null;
        File f = new File(GalleryController.GALLERY_LOCATION + this.path);
        try {
            img = ImageIO.read(f);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return img;
    }

    public Image getResizedIimage(int width, int height) {
        return this.getImage().getScaledInstance(width, height,
                Image.SCALE_SMOOTH);
    }

    public String getPath() {
        return path;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
