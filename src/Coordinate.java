public class Coordinate {
    private int xCoor;
    private int yCoor;

    // constructor
    public Coordinate(int x, int y) {
        xCoor = x;
        yCoor = y;
    }

    public Coordinate() {
        xCoor = 1;
        yCoor = 1;
    }

    // getter method
    public int getX() {
        return xCoor;
    }

    public int getY() {
        return yCoor;
    }

    // return coordinate as string by using to string method
    public String toString() {
        return "(" + xCoor + "," + yCoor + ")";
    }
}