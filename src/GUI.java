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

    JFrame frame = new JFrame("ePortfolio");
    JPanel maximised = new JPanel();
    JPanel preview;
    JButton close = new JButton("×");

    public void init() {
        frame.setSize(1600, 900);
        frame.setBackground(Color.darkGray);
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
            // System.out.println(Math.min(32, (fls != null ? fls.length : 0) - (i * 32L)));
            for (int j = 0; j < Math.min(32, (fls != null ? fls.length : 0) - (i * 32L)); j++) {
                // System.out.println(fls != null ? fls[pos].toString() : null);


                PicFrame mogusBogus = new PicFrame(x, y, new CustomJPanel(fls != null ? fls[pos].getPath() : null, 200, 200) {}, fls != null ? fls[pos].toString() : null);

                mogusBogus.path = fls != null ? fls[pos].getPath() : null;
                mogusBogus.jp.setBounds(x, y, frame.getWidth() / 8, 200);
                mogusBogus.jp.setBackground(Color.white);
                mogusBogus.jp.addMouseListener(new MouseClickEvent(mogusBogus, this));
                temp.get(i).put(j, mogusBogus);

                if (x <= frame.getWidth()) {
                    x += 200;
                } else {
                    x = 0;
                    y += 200;
                }
                // System.out.println(x + " " + y);
                pos++;
            }
        }
        repaint();
        return temp;
    }

    public void renderFrames() {

        for (Integer x : inv.get(page).keySet()) {
            System.out.println(inv.get(page).get(x).path + " " + x);
            if ((inv.get(page).get(x)) != null) {
                frame.add(inv.get(page).get(x).jp);
            }
        }
        drawImages();
    }

    public void maximise(String imgPath) {
        System.out.println(imgPath);
        maximised.setLayout(null);
        maximised.setBackground(Color.darkGray);
        maximised.setBounds(50, 25, frame.getWidth() - 100, frame.getHeight() - 100);
        maximised.setVisible(true);


        preview = new CustomJPanel(imgPath, maximised.getWidth(), maximised.getHeight());
        preview.setBounds(0, 0, maximised.getWidth(), maximised.getHeight());
        preview.setVisible(true);

        close.addActionListener(e -> {
            maximised.setVisible(false);
        });
        close.setBounds(maximised.getWidth() - 50, 0, 50, 50);
        close.setFont(new Font("Arial", Font.BOLD, 24));

        maximised.add(preview);
        maximised.add(close);
    }

    public void repaint() {
        frame.repaint();
        frame.setVisible(true);
    }
}