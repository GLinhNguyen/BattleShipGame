//Rectangle
package Boards;/*coordinate class: defines a simple rectangle which location on the top left corner,
 * width(xCoor) and height (yCoor) to represent the size of the rectangle. */

public class Coordinate {
    // width of board
    protected int width;
    // height of board
    protected int height;

    // location of the top left corner point
    protected Location location;

    // constructor
    public Coordinate(Location location, int width, int height) {
        this.width = width;
        this.height = height;
        this.location = location;
    }
    /**
     * @param xCoor X coordinate of the top left corner.
     * @param yCoor Y coordinate of the top left corner.
     * @param width Width of the rectangle.
     * @param height Height of the rectangle.
     */
    public Coordinate(int xCoor, int yCoor, int width, int height) {
        this(new Location(xCoor, yCoor),width, height );
    }

    public Coordinate(Location location) {
    }

    // get width of board
    public int getWidth() {
        return width;
    }

    // get height of board
    public int getHeight() {
        return height;
    }

    // get position within board
    public Location getLocation() {
        return location;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    // check if the targetLocation is within the coordinate
    public boolean isLocationWithinCoordinate(Location targetLocation) {
        return targetLocation.x >= location.x && targetLocation.y >= location.y && targetLocation.x < location.x + width
                && targetLocation.y < location.y + height;
    }
}