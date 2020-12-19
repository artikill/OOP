import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

    public class AStarApp {
        private int width;
        private int height;
        private Location startLoc;
        private Location finishLoc;
        private JMapCell[][] mapCells;

        public AStarApp(int w, int h) {
            if (w <= 0) {
                throw new IllegalArgumentException("w must be > 0; got " + w);
            } else if (h <= 0) {
                throw new IllegalArgumentException("h must be > 0; got " + h);
            } else {
                this.width = w;
                this.height = h;
                this.startLoc = new Location(2, h / 2);
                this.finishLoc = new Location(w - 3, h / 2);
            }
        }

        private void initGUI() {
            JFrame frame = new JFrame("Pathfinder");
            frame.setDefaultCloseOperation(3);
            Container contentPane = frame.getContentPane();
            contentPane.setLayout(new BorderLayout());
            GridBagLayout gbLayout = new GridBagLayout();
            GridBagConstraints gbConstraints = new GridBagConstraints();
            gbConstraints.fill = 1;
            gbConstraints.weightx = 1.0D;
            gbConstraints.weighty = 1.0D;
            gbConstraints.insets.set(0, 0, 1, 1);
            JPanel mapPanel = new JPanel(gbLayout);
            mapPanel.setBackground(Color.GRAY);
            this.mapCells = new JMapCell[this.width][this.height];
            AStarApp.MapCellHandler cellHandler = new AStarApp.MapCellHandler();

            for(int y = 0; y < this.height; ++y) {
                for(int x = 0; x < this.width; ++x) {
                    this.mapCells[x][y] = new JMapCell();
                    gbConstraints.gridx = x;
                    gbConstraints.gridy = y;
                    gbLayout.setConstraints(this.mapCells[x][y], gbConstraints);
                    mapPanel.add(this.mapCells[x][y]);
                    this.mapCells[x][y].addMouseListener(cellHandler);
                }
            }

            contentPane.add(mapPanel, "Center");
            JButton findPathButton = new JButton("Find Path");
            findPathButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    AStarApp.this.findAndShowPath();
                }
            });
            contentPane.add(findPathButton, "South");
            frame.pack();
            frame.setVisible(true);
            this.mapCells[this.startLoc.xCoord][this.startLoc.yCoord].setEndpoint(true);
            this.mapCells[this.finishLoc.xCoord][this.finishLoc.yCoord].setEndpoint(true);
        }

        private void start() {
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    AStarApp.this.initGUI();
                }
            });
        }

        private void findAndShowPath() {
            Map2D map = new Map2D(this.width, this.height);
            map.setStart(this.startLoc);
            map.setFinish(this.finishLoc);

            for(int y = 0; y < this.height; ++y) {
                for(int x = 0; x < this.width; ++x) {
                    this.mapCells[x][y].setPath(false);
                    if (this.mapCells[x][y].isPassable()) {
                        map.setCellValue(x, y, 0);
                    } else {
                        map.setCellValue(x, y, 2147483647);
                    }
                }
            }

            for(Waypoint wp = AStarPathfinder.computePath(map); wp != null; wp = wp.getPrevious()) {
                Location loc = wp.getLocation();
                this.mapCells[loc.xCoord][loc.yCoord].setPath(true);
            }

        }

        public static void main(String[] args) {
            AStarApp app = new AStarApp(40, 30);
            app.start();
        }

        private class MapCellHandler implements MouseListener {
            private boolean modifying;
            private boolean makePassable;

            private MapCellHandler() {
            }

            public void mousePressed(MouseEvent e) {
                this.modifying = true;
                JMapCell cell = (JMapCell)e.getSource();
                this.makePassable = !cell.isPassable();
                cell.setPassable(this.makePassable);
            }

            public void mouseReleased(MouseEvent e) {
                this.modifying = false;
            }

            public void mouseEntered(MouseEvent e) {
                if (this.modifying) {
                    JMapCell cell = (JMapCell)e.getSource();
                    cell.setPassable(this.makePassable);
                }

            }

            public void mouseExited(MouseEvent e) {
            }

            public void mouseClicked(MouseEvent e) {
            }
        }
    }