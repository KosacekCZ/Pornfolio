import javax.imageio.ImageIO;
import javax.swing.*;

public class PicFrame {
    int x;
    int y;
    JPanel jp;
    String path;

    public  PicFrame (int x, int y, JPanel jp, String path) {
        this.x = x;
        this.y = y;
        this.jp = jp;
        this.path = path;
    }
}
