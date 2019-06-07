package apps.gallery;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Objects;

public class GalleryController {
    public static final String GALLERY_PATH = "images/gallery/";
    public static final int MODE_LOCAL = 0;
    public static final int MODE_WEB = 1;
    public static final int SOURCE_WEB_QTY = 20;
    public static final int DEFAULT_GALLERY_WIDTH = 110;
    public static final int DEFAULT_GALLERY_HEIGHT = 110;
    public static final int DEFAULT_WEB_WIDTH = 500;
    public static final int DEFAULT_WEB_HEIGHT = 500;
    private int mode = MODE_LOCAL;
    private ArrayList<GalleryItemPath> items = new ArrayList<>();

    public GalleryController(int mode) {
        this.mode = mode;
        buildGalleryItems();
    }

    private void buildGalleryItems() {
        if (this.mode == MODE_LOCAL) {
            ClassLoader classLoader = getClass().getClassLoader();
            String path = null;
            try {
                path = URLDecoder.decode(classLoader.getResource("images/gallery/").getFile(), "UTF-8");

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            File fi = new File(path);
            for (File f : Objects.requireNonNull(fi.listFiles())) {
                //System.out.println(f.getName());
                BufferedImage img = null;
                try {
                    img = ImageIO.read(f);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                GalleryItemPath gip = new GalleryItemPath(f.getName());
                gip.setId(items.size());
                items.add(gip);

            }

        } /*else if (this.mode == MODE_WEB) {
            Image image = null;
            URL url = null;

            for (int i = 0; i < SOURCE_WEB_QTY; i++) {
                try {
                    url = new URL("https://picsum.photos/" + DEFAULT_WEB_WIDTH + "/" + DEFAULT_WEB_HEIGHT + "");
                    image = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //ImageIcon imageIcon = new ImageIcon(image);
                GalleryItem gi = new GalleryItem(image);
                gi.setId(items.size());
                items.add(gi);
            }
        }*/
    }

    public ArrayList<GalleryItemPath> getItems() {
        return items;
    }

    public ArrayList<GalleryItem> getImagesForGallery() {
        ArrayList<GalleryItem> resized = new ArrayList<>();
        for (GalleryItemPath gi : this.items) {
            resized.add(new GalleryItem(gi.getResizedIimage(DEFAULT_GALLERY_WIDTH, DEFAULT_GALLERY_HEIGHT)));
        }
        return resized;
    }

    public int getGallerySize(){
        return this.items.size();
    }

    public GalleryItemPath getRandomImage() {
        return this.items.get(4);
    }
}
