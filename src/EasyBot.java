import java.util.Collections;

public class EasyBot {
    // randoming the order to move
    // playegrid reference the player 's grid to attack
    public EasyBot(SelectionGrid playerGrid) {
        super(playerGrid);
        Collections.shuffle(validMoves);
    }


     //Resets the AI by resetting the parent class, and then
     //reshuffling the refreshed list of valid moves.

    @Override
    public void reset() {
        super.reset();
        Collections.shuffle(validMoves);
    }

    //Takes the move from the top of the list and returns it and return A position from the valid moves list.
    
    @Override
    public Location selectMove() {
        Location nextMove = validMoves.get(0);
        validMoves.remove(0);
        return nextMove;
    }
}

