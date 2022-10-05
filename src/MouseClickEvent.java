import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseClickEvent implements MouseListener {

    private final GUI g;
    private String path;

     public MouseClickEvent (String path, GUI g) {
        this.path = path;
        this.g = g;
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