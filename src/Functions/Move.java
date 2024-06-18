package Functions;

import Boards.Location;

public class Move {
    protected Location location;
    protected boolean isPlayerMove;
    private int placingShipIndex;

    public Move(Location location, boolean isPlayerMove, int placingShipIndex) {
        this.location = location;
        this.isPlayerMove = isPlayerMove;
        this.placingShipIndex = placingShipIndex;
    }

    public Location getLocation() {
        return location;
    }

    public boolean isPlayerMove() {
        return isPlayerMove;
    }

    public int getPlacingShipIndex() {
        return placingShipIndex;
    }
}
