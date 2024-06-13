package Functions;

public class Move {
    private Location location; // Location of the move (attack or ship placement)
    private boolean isPlayerMove; // indicate if it's a player or AI move

    public Move(Location location, boolean isPlayerMove) {
        this.location = location;
        this.isPlayerMove = isPlayerMove;
    }

}
