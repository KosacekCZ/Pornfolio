import javax.swing.*;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Objects;

public class ImageLoader {
    HashMap<Integer, File> imageMap = new HashMap<>();
    File f = new File("vobrazky");
    File[] tempJpg;
    Path path;

    void fillArray() {
        for (int i = 0; i < Objects.requireNonNull(f.listFiles()).length; i++) {

            // imageMap.put(i, new ImageIcon(Files.readAllBytes()));
        }
    }

    public File[] imageLoader() {
        File[] imageArray = new File[imageMap.size()];
        for (int i = 0; i < imageMap.size(); i++) {
            imageArray[i] = imageMap.get(i);
        }
        return imageArray;
    }
}


