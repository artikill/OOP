public class Map2D {
    private int width;
    private int height;
    private int[][] cells;
    private Location start;
    private Location finish;

    public Map2D(int width, int height) {
        if (width > 0 && height > 0) {
            this.width = width;
            this.height = height;
            this.cells = new int[width][height];
            this.start = new Location(0, height / 2);
            this.finish = new Location(width - 1, height / 2);
        } else {
            throw new IllegalArgumentException("width and height must be positive values; got " + width + "x" + height);
        }
    }

    private void checkCoords(int x, int y) {
        if (x >= 0 && x <= this.width) {
            if (y < 0 || y > this.height) {
                throw new IllegalArgumentException("y must be in range [0, " + this.height + "), got " + y);
            }
        } else {
            throw new IllegalArgumentException("x must be in range [0, " + this.width + "), got " + x);
        }
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public boolean contains(int x, int y) {
        return x >= 0 && x < this.width && y >= 0 && y < this.height;
    }

    public boolean contains(Location loc) {
        return this.contains(loc.xCoord, loc.yCoord);
    }

    public int getCellValue(int x, int y) {
        this.checkCoords(x, y);
        return this.cells[x][y];
    }

    public int getCellValue(Location loc) {
        return this.getCellValue(loc.xCoord, loc.yCoord);
    }

    public void setCellValue(int x, int y, int value) {
        this.checkCoords(x, y);
        this.cells[x][y] = value;
    }

    public Location getStart() {
        return this.start;
    }

    public void setStart(Location loc) {
        if (loc == null) {
            throw new NullPointerException("loc cannot be null");
        } else {
            this.start = loc;
        }
    }

    public Location getFinish() {
        return this.finish;
    }

    public void setFinish(Location loc) {
        if (loc == null) {
            throw new NullPointerException("loc cannot be null");
        } else {
            this.finish = loc;
        }
    }
}
