/* location class: represent a single position x,y on a board.  */
public class Location {

    // horizontal moving unit vector
    public static final Location horizontal = new Location(1, 0);
    // vertical moving unit vector
    public static final Location vertical = new Location(0, 1);

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
        this.y = newlocation.y;
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

