import javax.imageio.ImageIO;
import javax.swing.*;

public class PicFrame {
    int x;
    int y;
    JPanel jp;
    JLabel jl;
    ImageIcon iic;
    String path;

    public  PicFrame (int x, int y, JPanel jp, String path, JLabel jl, ImageIcon iic) {
        this.x = x;
        this.y = y;
        this.jp = jp;
        this.path = path;
        this.jl = jl;
        this.iic = iic;
    }
}
