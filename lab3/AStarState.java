package lab3;
import java.util.HashMap;
import java.util.Map;

/**
 * This class stores the basic state necessary for the A* algorithm to compute a
 * path across a map.  This state includes a collection of "open waypoints" and
 * another collection of "closed waypoints."  In addition, this class provides
 * the basic operations that the A* pathfinding algorithm needs to perform its
 * processing.
 **/
public class AStarState
{
    /** This is a reference to the map that the A* algorithm is navigating. **/
    private Map2D map;
    private Map<Location, Waypoint> open_waypoints
            = new HashMap<Location, Waypoint> ();

    /** Initialize a map of all closed waypoints and their locations. **/
    private Map<Location, Waypoint> closed_waypoints
            = new HashMap<Location, Waypoint> ();

    /**
     * Initialize a new state object for the A* pathfinding algorithm to use.
     **/
    public AStarState(Map2D map)
    {
        if (map == null)
            throw new NullPointerException("map cannot be null");

        this.map = map;
    }

    /** Returns the map that the A* pathfinder is navigating. **/
    public Map2D getMap()
    {
        return map;
    }

    /**
     * This method scans through all open waypoints, and returns the waypoint
     * with the minimum total cost.  If there are no open waypoints, this method
     * returns <code>null</code>.
     **/
    public Waypoint getMinOpenWaypoint()
    {
        if (numOpenWaypoints() == 0) {
            return null;
        }

        Waypoint minWaypoint = null;
        double minValue = Float.MAX_VALUE;

        for(Map.Entry<Location, Waypoint> entry: open_waypoints.entrySet()) {
            double totalCost = entry.getValue().getTotalCost();
            System.out.println(entry.getKey() + " => " + entry.getValue());

            if (minValue > totalCost) {
                minValue = totalCost;
                minWaypoint = entry.getValue();
            }

        }

        return minWaypoint;
    }

    /**
     * This method adds a waypoint to (or potentially updates a waypoint already
     * in) the "open waypoints" collection.  If there is not already an open
     * waypoint at the new waypoint's location then the new waypoint is simply
     * added to the collection.  However, if there is already a waypoint at the
     * new waypoint's location, the new waypoint replaces the old one <em>only
     * if</em> the new waypoint's "previous cost" value is less than the current
     * waypoint's "previous cost" value.
     **/
    public boolean addOpenWaypoint(Waypoint newWP)
    {
        // TODO:  Implement.
        Location loc = newWP.getLocation();


        if (open_waypoints.containsKey(loc)) {
            Waypoint current_wp = open_waypoints.get(loc);

            // Если стоимость путешествия дешевле - заменяем
            if (newWP.getPreviousCost() < current_wp.getPreviousCost()) {
                open_waypoints.put(loc, newWP);
                return true;
            }


        } else {
            open_waypoints.put(loc, newWP);
            return true;
        }

        return false;
    }


    /** Returns the current number of open waypoints. **/
    public int numOpenWaypoints()
    {
        return open_waypoints.size();
    }


    /**
     * This method moves the waypoint at the specified location from the
     * open list to the closed list.
     **/
    public void closeWaypoint(Location loc)
    {
        Waypoint wp = open_waypoints.remove(loc);
        closed_waypoints.put(loc, wp);
    }

    public boolean isLocationClosed(Location loc)
    {
        return closed_waypoints.containsKey(loc);
    }
}
