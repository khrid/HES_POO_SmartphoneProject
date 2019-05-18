package apps.gallery;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;

public class GalleryController {
    public static final int MODE_LOCAL = 0;
    public static final int MODE_WEB = 1;
    public static final int SOURCE_WEB_QTY = 20;
    public static final int DEFAULT_GALLERY_WIDTH = 100;
    public static final int DEFAULT_GALLERY_HEIGHT = 100;
    public static final int DEFAULT_WEB_WIDTH = 500;
    public static final int DEFAULT_WEB_HEIGHT = 500;
    private int mode = MODE_LOCAL;
    private ArrayList<GalleryItem> items = new ArrayList<>();

    public GalleryController(int mode) {
        this.mode = mode;
        buildGalleryItems();
    }

    private void buildGalleryItems() {
        if (this.mode == MODE_LOCAL) {
            ClassLoader classLoader = getClass().getClassLoader();
            File fi = new File(classLoader.getResource("images/gallery/").getFile());
            for (File f : Objects.requireNonNull(fi.listFiles())) {
                System.out.println(f.getName());
                BufferedImage img = null;
                try {
                    img = ImageIO.read(f);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //Image dimg = img.getScaledInstance(DEFAULT_WIDTH, DEFAULT_HEIGHT,
                //        Image.SCALE_SMOOTH);
                //ImageIcon image = new ImageIcon(dimg);
                GalleryItem gi = new GalleryItem(img);
                items.add(gi);
            }

        } else if (this.mode == MODE_WEB) {
            Image image = null;
            URL url = null;

            for (int i = 0; i < SOURCE_WEB_QTY; i++) {
                try {
                    url = new URL("https://picsum.photos/"+DEFAULT_WEB_WIDTH+"/"+DEFAULT_WEB_HEIGHT+"");
                    image = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //ImageIcon imageIcon = new ImageIcon(image);
                GalleryItem gi = new GalleryItem(image);
                items.add(gi);
            }
        }
    }

    public ArrayList<GalleryItem> getItems() {
        return items;
    }

    public ArrayList<GalleryItem> getImagesForGallery() {
        ArrayList<GalleryItem> resized = new ArrayList<>();
        for (GalleryItem gi : this.items) {
            resized.add(new GalleryItem(gi.getResizedIimage(DEFAULT_GALLERY_WIDTH, DEFAULT_GALLERY_HEIGHT)));
        }
        return resized;
    }
}
