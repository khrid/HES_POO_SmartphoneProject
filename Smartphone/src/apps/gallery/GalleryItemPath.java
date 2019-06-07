package apps.gallery;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class GalleryItemPath implements Serializable {
    private static final long serialVersionUID = -2475636784399927553L;
    private String path;
    private int id;


    public GalleryItemPath(String path) {
        this.path = path;
    }

    public BufferedImage getImage() {
        BufferedImage img = null;
        ClassLoader classLoader = getClass().getClassLoader();
        String location = null;
        try {
            //System.out.println("dans galleryitempath => images/gallery/"+this.path  );
            location = URLDecoder.decode(classLoader.getResource("images/gallery/"+this.path).getFile(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        File f = new File(location);
        //System.out.println(f.getName());
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
