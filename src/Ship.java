import java.awt.*;
import java.util.ArrayList;
import java.util.List;
public class Ship {
    private int size;
    private List<Point> position;
    private String orientation;
    private String status;
    private Coordinate coord;
    public static final String FLOAT = "float";
    public static final String SUNK = "sunk";
    public static final String HIT = "hit";
    public Ship(int size, String orientation) {
        this.size = size;
        this.orientation = orientation;
        this.position = new ArrayList<>();
        this.status = FLOAT;
    }
    public boolean place(Boards board1, int x, int y) {
        if (!isValidPlacement(board1, x, y)) {
            return false;
        }

        for (int i = 0; i < size; i++) {
            int newX = (orientation.equals("horizontal")) ? x + i : x;
            int newY = (orientation.equals("vertical")) ? y + i : y;

            position.add(new Coordinate(xCoor,yCoor));

        }
        return true;
    }
    private boolean isValidPlacement(Boards board, int x, int y) {
        // Check if placement fits within board and doesn't overlap
        if (x < 0 || y < 0 || x + (orientation.equals("horizontal") ? size - 1 : 0) >= board.getSize() ||
                y + (orientation.equals("vertical") ? size - 1 : 0) >= board.getSize()) {
            return false;
        }

        // Check for overlaps with other ships
        for (int i = 0; i < size; i++) {
            int newX = (orientation.equals("horizontal")) ? x + i : x;
            int newY = (orientation.equals("vertical")) ? y + i : y;
            if (board.isOccupied(newX, newY)) {
                return false;
            }
        }
        return true;
    }
    public boolean isSunk(){
        return status.equals(SUNK);
    }
    public  boolean isHit(){

    }
}
