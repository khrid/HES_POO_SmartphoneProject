package apps.gallery;

import smartphone.Smartphone;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;

public class GalleryController {
    // Emplacement par défaut de la galerie
    public static final String GALLERY_LOCATION = Smartphone.ROOT_DIR+"gallery/";
    // Taille des miniatures dans la galerie
    static final int DEFAULT_GALLERY_WIDTH = 110;
    static final int DEFAULT_GALLERY_HEIGHT = 110;
    // Eléments de la galerie
    private ArrayList<GalleryItem> items = new ArrayList<>();

    /**
     * Constructeur
     */
    public GalleryController() {
        buildGalleryItems();
    }

    /**
     * Création de la galerie avec le contenu du dossier source
     */
    void buildGalleryItems() {
        this.items.clear();
        File fi = new File(GALLERY_LOCATION);
        if (fi.listFiles() != null) {
            for (File f : (Objects.requireNonNull(fi.listFiles()))) {
                GalleryItem gip = new GalleryItem(f.getName());
                gip.setId(items.size());
                items.add(gip);
            }
        }
    }

    /**
     * Renvoie les éléments de la galerie
     * @return ArrayList de GalleryItem
     */
    ArrayList<GalleryItem> getItems() {
        return this.items;
    }

    /**
     * Renvoie la taille de la galerie
     * @return int
     */
    int getGallerySize() {
        return this.items.size();
    }

    /**
     * Permet d'ajouter une image à la galerie
     * @param selectedFile File le fichier sélectionné par le JFileChooser
     */
    void addNewImage(File selectedFile) {
        String filePath = selectedFile.getAbsolutePath();
        InputStream inStream = null;
        OutputStream outStream = null;
        try {
            File source = new File(filePath);
            File dest = new File(GALLERY_LOCATION + selectedFile.getName());
            inStream = new FileInputStream(source);
            outStream = new FileOutputStream(dest);

            byte[] buffer = new byte[1024];

            int length;
            while ((length = inStream.read(buffer)) > 0) {
                outStream.write(buffer, 0, length);
            }

            inStream.close();
            outStream.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        buildGalleryItems();
    }

    /**
     * Supprime un élément de la galerie
     * @param id int l'élément à supprimer
     */
    void removeItem(int id) {
        File f1 = new File(GALLERY_LOCATION + this.items.get(id).getPath());
        this.items.remove(id);
        f1.delete();
    }
}
