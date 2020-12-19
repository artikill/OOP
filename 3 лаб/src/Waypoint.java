public class Waypoint {
    Location loc;
    Waypoint prevWaypoint;
    private float prevCost;
    private float remainingCost;

    public Waypoint(Location loc, Waypoint prevWaypoint) {
        this.loc = loc;
        this.prevWaypoint = prevWaypoint;
    }

    public Location getLocation() {
        return this.loc;
    }

    public Waypoint getPrevious() {
        return this.prevWaypoint;
    }

    public void setCosts(float prevCost, float remainingCost) {
        this.prevCost = prevCost;
        this.remainingCost = remainingCost;
    }

    public float getPreviousCost() {
        return this.prevCost;
    }

    public float getRemainingCost() {
        return this.remainingCost;
    }

    public float getTotalCost() {
        return this.prevCost + this.remainingCost;
    }
}