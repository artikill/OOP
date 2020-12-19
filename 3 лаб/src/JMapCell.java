import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JComponent;

public class JMapCell extends JComponent {
    private static final Dimension CELL_SIZE = new Dimension(12, 12);
    boolean endpoint;
    boolean passable;
    boolean path;

    public JMapCell(boolean pass) {
        this.endpoint = false;
        this.passable = true;
        this.path = false;
        this.setPreferredSize(CELL_SIZE);
        this.setPassable(pass);
    }

    public JMapCell() {
        this(true);
    }

    public void setEndpoint(boolean end) {
        this.endpoint = end;
        this.updateAppearance();
    }

    public void setPassable(boolean pass) {
        this.passable = pass;
        this.updateAppearance();
    }

    public boolean isPassable() {
        return this.passable;
    }

    public void togglePassable() {
        this.setPassable(!this.isPassable());
    }

    public void setPath(boolean path) {
        this.path = path;
        this.updateAppearance();
    }

    private void updateAppearance() {
        if (this.passable) {
            this.setBackground(Color.WHITE);
            if (this.endpoint) {
                this.setBackground(Color.CYAN);
            } else if (this.path) {
                this.setBackground(Color.GREEN);
            }
        } else {
            this.setBackground(Color.RED);
        }

    }

    protected void paintComponent(Graphics g) {
        g.setColor(this.getBackground());
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
    }
}