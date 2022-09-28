import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class CustomJPanel extends JPanel {

    private final BufferedImage img;
    private final int w;
    private final int h;

    public CustomJPanel(String img, int w, int h){
        try {
            this.img = ImageIO.read(new File(img));
        } catch (IOException e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }
        this.w = w;
        this.h = h;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(img, 0, 0, w, h,null);
        g.dispose();
    }
}