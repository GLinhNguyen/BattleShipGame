package Functions;/*coordinate class: defines a simple rectangle which location on the top left corner,
 * width(xCoor) and height (yCoor) to represent the size of the rectangle. */

public class Coordinate {
    // width of board
    protected int xCoor;
    // height of board
    protected int yCoor;

    // location of the top left corner point
    protected Location location;

    // constructor
    public Coordinate(int width, int height, Location location) {
        this.xCoor = width;
        this.yCoor = height;
        this.location = location;
    }

    public Coordinate(int width, int height, int x, int y) {
        this(width, height, new Location(x, y));
    }

    // get width of board
    public int getXCoor() {
        return xCoor;
    }

    // get height of board
    public int getYCoor() {
        return yCoor;
    }

    // get position within board
    public Location getLocation() {
        return location;
    }

    public void setXCoor(int xCoor) {
        this.xCoor = xCoor;
    }

    public void setYCoor(int yCoor) {
        this.yCoor = yCoor;
    }

    // check if the targetLocation is within the coordinate
    public boolean isLocationWithinCoordinate(Location targetLocation) {
        return targetLocation.x >= location.x && targetLocation.y >= location.y && targetLocation.x < location.x + xCoor
                && targetLocation.y < location.y + yCoor;
    }
}