import apps.gallery.GalleryItem;
import org.junit.jupiter.api.Test;
import smartphone.Smartphone;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class SerializationTest {

    @Test
    public void testSerialization() {
        String file = Smartphone.ROOT_DIR+"/res/background.ser";
        File f = new File(file);
        if(f.exists()) {
            ObjectInputStream is;
            try {
                is = new ObjectInputStream(new FileInputStream(f));
                GalleryItem gipFromDisk = (GalleryItem) is.readObject();
                assert GalleryItem.class.equals(gipFromDisk.getClass());
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
