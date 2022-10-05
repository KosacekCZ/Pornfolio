import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class GUI {

    BufferedImage img;
    int page = 0;
    File f = new File("vobrazky");
    PicFrame[] buffer;

    JFrame frame = new JFrame("ePortfolio");
    JPanel maximised = new JPanel();
    JPanel preview;
    JPanel gallery = new JPanel();
    JButton close = new JButton("×");
    JButton next = new JButton("->");
    JButton prev = new JButton("<-");

    public void init() {
        frame.setSize(1600, 900);
        frame.setBackground(Color.darkGray);
        frame.setDefaultCloseOperation(3);
        frame.setLayout(null);
        frame.setResizable(false);

        gallery.setLayout(null);
        gallery.setBounds(0, 0, frame.getWidth(), frame.getHeight() - 100);
        gallery.setBackground(Color.black);
        next.setBounds(1500, 800, 100, 100);
        prev.setBounds(0, 800, 100, 100);
        next.addActionListener(e -> {
            page++;
            System.out.println("Page " + page);
            gallery.removeAll();
            renderFrames();
            repaint();
        });
        prev.addActionListener(e -> {
            try {
                page--;
            } catch (Exception g) {
                System.out.println(g);
            }
            System.out.println("Page " + page);
            page++;
            gallery.removeAll();
            try {renderFrames();}
            catch (Exception f) {
                System.out.println(f);
            }
            repaint();
        });
        frame.add(maximised);
        frame.add(next);
        frame.add(prev);
        frame.add(gallery);
        frame.setVisible(true);
    }

    public PicFrame[] drawImages32() {
        PicFrame[] temp = new PicFrame[32];
        File[] fls = f.listFiles();
        // System.out.println(fls != null ? fls.length : 0);

        int x = 0;
        int y = 0;

        for (int i = 0; i < 32; i++) {
            // System.out.println(i + (page * 32));
            PicFrame mogusBogus = new PicFrame(x, y, new CustomJPanel(fls != null ? fls[i + (page * 31)].getPath() : null, 200, 200), fls != null ? fls[i + (page * 30)].toString() : null);
            mogusBogus.path = fls != null ? fls[i + (page * 31)].getPath() : null;
            mogusBogus.jp.setBounds(x, y, frame.getWidth() / 8, 200);
            mogusBogus.jp.setBackground(Color.white);
            mogusBogus.jp.addMouseListener(new MouseClickEvent(mogusBogus.path, this));
            temp[i] = mogusBogus;
            if (x < frame.getWidth() - 200) {
                x += 200;
            } else {
                x = 0;
                y += 200;
            }
            // System.out.println(x + " " + y);
        }
        return temp;
    }

    /* public HashMap<Integer, HashMap<Integer, PicFrame>> drawImages() {
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
                mogusBogus.jp.addMouseListener(new MouseClickEvent(mogusBogus.path, this));
                temp.get(i).put(j, mogusBogus);

                if (x <= frame.getWidth()) {
                    x += 200;
                } else {
                    x = 0;
                    y += 200;
                }
                pos++;
            }
        }
        return temp;
    } */

    public void renderFrames() {
        buffer = drawImages32();
        for (PicFrame pic : buffer) {
                gallery.add(pic.jp);
        }
        repaint();
    }

    public void maximise(String imgPath) {
        maximised.removeAll();
        System.out.println("mogus " + imgPath);
        maximised.setLayout(null);
        maximised.setBackground(Color.darkGray);
        maximised.setBounds(50, 25, frame.getWidth() - 100, frame.getHeight() - 100);


        preview = new CustomJPanel(imgPath, maximised.getWidth(), maximised.getHeight());
        preview.setBounds(0, 0, maximised.getWidth(), maximised.getHeight());

        close.addActionListener(e -> {
            maximised.setVisible(false);
        });
        close.setBounds(maximised.getWidth() - 50, 0, 50, 50);
        close.setFont(new Font("Arial", Font.BOLD, 24));

        maximised.add(preview);
        maximised.add(close);
        maximised.repaint();
        maximised.setVisible(true);
    }

    public void repaint() {
        frame.repaint();
    }
}