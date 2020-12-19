import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AStarState {
    private Map2D map;
    private Map<Location, Waypoint> Opened = new HashMap();
    private Map<Location, Waypoint> Closed = new HashMap();

    public AStarState(Map2D map) {
        if (map == null) {
            throw new NullPointerException("map cannot be null");
        } else {
            this.map = map;
        }
    }

    public Map2D getMap() {
        return this.map;
    }

    public Waypoint getMinOpenWaypoint() {
        if (this.Opened.size() == 0) {
            return null;
        } else {
            ArrayList<Waypoint> waypoints = new ArrayList(this.Opened.values());
            float mincost = ((Waypoint)waypoints.get(0)).getTotalCost();
            Waypoint min = (Waypoint)waypoints.get(0);

            for(int i = 1; i < waypoints.size(); ++i) {
                if (((Waypoint)waypoints.get(i)).getTotalCost() < mincost) {
                    min = (Waypoint)waypoints.get(i);
                    mincost = min.getTotalCost();
                }
            }

            return min;
        }
    }

    public boolean addOpenWaypoint(Waypoint newWP) {
        if (this.Opened.get(newWP.getLocation()) == null) {
            this.Opened.put(newWP.getLocation(), newWP);
            return true;
        } else if (((Waypoint)this.Opened.get(newWP.getLocation())).getPreviousCost() > newWP.getPreviousCost()) {
            this.Opened.put(newWP.getLocation(), newWP);
            return true;
        } else {
            return false;
        }
    }

    public int numOpenWaypoints() {
        return this.Opened.size();
    }

    public void closeWaypoint(Location loc) {
        this.Closed.put(loc, (Waypoint)this.Opened.remove(loc));
    }

    public boolean isLocationClosed(Location loc) {
        return this.Closed.containsKey(loc);
    }
}