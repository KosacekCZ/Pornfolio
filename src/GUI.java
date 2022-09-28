import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;

public class GUI {

    BufferedImage img;
    int page = 0;
    File f = new File("vobrazky");
    HashMap<Integer, HashMap<Integer, PicFrame>> inv = new HashMap<>();

    JFrame frame = new JFrame("Portfolio 1.0.0");
    JPanel maximised = new JPanel();

    public void init() {
        frame.setSize(1600, 900);
        frame.setDefaultCloseOperation(3);
        frame.setLayout(null);
        frame.setResizable(false);
        inv = drawImages();
        frame.add(maximised);
        frame.setVisible(true);
    }

    public HashMap<Integer, HashMap<Integer, PicFrame>> drawImages() {
        HashMap<Integer, HashMap<Integer, PicFrame>> temp = new HashMap<>();
        int x = 0;
        int y = 0;
        int pos = 0;
        File[] fls = f.listFiles();

        for (int i = 0; i < Math.ceil((fls != null ? fls.length : 0) / 32.0); i++) {
            temp.put(i, new HashMap<>());

            for (int j = 0; j < Math.min(32, (fls != null ? fls.length : 0) - (i * 32L)); j++) {

                PicFrame mogusBogus = new PicFrame(x, y, new CustomJPanel(fls != null ? fls[pos].getPath() : null, 200, 200) {}, fls != null ? fls[pos].toString() : null, new JLabel(), new ImageIcon());

                mogusBogus.path = fls != null ? fls[pos].getPath() : null;

                mogusBogus.jp.setBounds(x, y, frame.getWidth() / 8, 200);
                mogusBogus.jp.setBackground(Color.white);
                mogusBogus.jp.addMouseListener(new MouseClickEvent(mogusBogus, this));
                mogusBogus.jp.add(mogusBogus.jl);
                temp.get(i).put(j, mogusBogus);

                if (x < frame.getWidth()) {
                    x += 200;
                } else {
                    x = 0;
                    y += 200;
                }
                // System.out.println(x + " " + y);
                pos++;
            }
        }
        return temp;
    }

    public void renderFrames() {
        for (Integer x : inv.get(page).keySet()) {
            if ((inv.get(page).get(x)) != null) {
                frame.add(inv.get(page).get(x).jp);
            }
        }
    }

    public void maximise(String path) {
        maximised.setBounds(50, 50, frame.getWidth() - 100, frame.getHeight() - 100);
        maximised.add(new CustomJPanel(path, frame.getWidth() - 100, frame.getHeight() - 100));
        maximised.setVisible(true);
    }

    public void repaint() {
        frame.repaint();
        frame.setVisible(true);
    }
}