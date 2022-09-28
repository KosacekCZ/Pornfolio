import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseClickEvent implements MouseListener {

    private final PicFrame mogus;
    private final GUI g;
    private final String path;

    public MouseClickEvent (PicFrame mogus, GUI g) {
        this.mogus = mogus;
        this.g = g;
        this.path = mogus.path;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        g.maximise(path);
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
