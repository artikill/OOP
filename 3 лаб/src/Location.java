import java.util.Objects;

public class Location {
    public int xCoord;
    public int yCoord;

    public Location(int x, int y) {
        this.xCoord = x;
        this.yCoord = y;
    }

    public Location() {
        this(0, 0);
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            Location location = (Location)o;
            return this.xCoord == location.xCoord && this.yCoord == location.yCoord;
        } else {
            return false;
        }
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.xCoord, this.yCoord});
    }
}
