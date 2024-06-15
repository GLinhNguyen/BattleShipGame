package Functions;

import Boards.Location;

public class Move {
    protected Location location; // Location of the move (attack or ship placement)
    protected boolean isPlayerMove; // indicate if it's a player or AI move

    public Move(Location location, boolean isPlayerMove) {
        this.location = location;
        this.isPlayerMove = isPlayerMove;
    }
  
        public Location getLocation() {
            return location;
        }

        // Getter for isPlayerMove
        public boolean isPlayerMove() {
            return isPlayerMove;
        }

}
