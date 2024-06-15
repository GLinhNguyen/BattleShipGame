//rectangle
package Boards;

/* location class: represent a single position x,y on a board.  */
public class Location {

    /**
     * Down moving unit vector.
     */
    public static final Location DOWN = new Location(0,1);
    /**
     * Up moving unit vector.
     */
    public static final Location UP = new Location(0,-1);
    /**
     * Left moving unit vector.
     */
    public static final Location LEFT = new Location(-1,0);
    /**
     * Right moving unit vector.
     */
    public static final Location RIGHT = new Location(1,0);
    /**
     * Zero unit vector.
     */
    public static final Location ZERO = new Location(0,0);

    // value of location
    public int x;
    public int y;

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // create new location
    public Location(Location newlocation) {
        this.x = newlocation.x;
        this.y = newlocation.y;}

    public Location(Location location, int x2, int y2) {
        //TODO Auto-generated constructor stub
    }

    // set location to the specified x and y coordinate
    public void setLocation(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // create method to update current location by adding or subtracting from other
    // location
    public void add(Location otherLocation) {
        this.x += otherLocation.x;
        this.y += otherLocation.y;
    }

    public void subtract(Location otherLocation) {
        this.x -= otherLocation.x;
        this.y -= otherLocation.y;
    }

    // calculate distance from two location
    public double distance(Location otherLocation) {
        return Math.sqrt(Math.pow(x - otherLocation.x, 2) + Math.pow(y - otherLocation.y, 2));
    }

    // multiplies both components of the location by an amount
    public void multiply(int amount) {

        x *= amount;
        y *= amount;
    }
    public Location getLocation() {
        return new Location(x, y);
    }

    /**
     * Compares the Position object against another object.
     * Any non-Position object will return false. Otherwise compares x and y for
     * equality.
     *
     * @param o Object to compare this Position against.
     * @return True if the object o is equal to this position for both x and y.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Location location = (Location) o;
        return x == location.x && y == location.y;
    }

    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}

