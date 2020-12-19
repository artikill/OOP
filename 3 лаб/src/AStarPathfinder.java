public class AStarPathfinder {
    public static final float COST_LIMIT = 1000000.0F;

    public AStarPathfinder() {
    }

    public static Waypoint computePath(Map2D map) {
        AStarState state = new AStarState(map);
        Location finishLoc = map.getFinish();
        Waypoint start = new Waypoint(map.getStart(), (Waypoint)null);
        start.setCosts(0.0F, estimateTravelCost(start.getLocation(), finishLoc));
        state.addOpenWaypoint(start);
        Waypoint finalWaypoint = null;
        boolean foundPath = false;

        while(!foundPath && state.numOpenWaypoints() > 0) {
            Waypoint best = state.getMinOpenWaypoint();
            if (best.getLocation().equals(finishLoc)) {
                finalWaypoint = best;
                foundPath = true;
            }

            takeNextStep(best, state);
            state.closeWaypoint(best.getLocation());
        }

        return finalWaypoint;
    }

    private static void takeNextStep(Waypoint currWP, AStarState state) {
        Location loc = currWP.getLocation();
        Map2D map = state.getMap();

        for(int y = loc.yCoord - 1; y <= loc.yCoord + 1; ++y) {
            for(int x = loc.xCoord - 1; x <= loc.xCoord + 1; ++x) {
                Location nextLoc = new Location(x, y);
                if (map.contains(nextLoc) && nextLoc != loc && !state.isLocationClosed(nextLoc)) {
                    Waypoint nextWP = new Waypoint(nextLoc, currWP);
                    float prevCost = currWP.getPreviousCost() + estimateTravelCost(currWP.getLocation(), nextWP.getLocation());
                    prevCost += (float)map.getCellValue(nextLoc);
                    if (prevCost < 1000000.0F) {
                        nextWP.setCosts(prevCost, estimateTravelCost(nextLoc, map.getFinish()));
                        state.addOpenWaypoint(nextWP);
                    }
                }
            }
        }

    }

    private static float estimateTravelCost(Location currLoc, Location destLoc) {
        int dx = destLoc.xCoord - currLoc.xCoord;
        int dy = destLoc.yCoord - currLoc.yCoord;
        return (float)Math.sqrt((double)(dx * dx + dy * dy));
    }
}

