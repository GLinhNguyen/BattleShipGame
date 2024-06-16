package BotThings;
import Functions.*;

import java.util.Collections;

import Boards.Location;
import Boards.SelectionGrid;

public class EasyBot extends Bot {
    public EasyBot(SelectionGrid playerGrid) {
        super(playerGrid);
        Collections.shuffle(possibleMoves);
    }

    public void reset() {
        super.reset();
        Collections.shuffle(possibleMoves);
    }

    @Override
    public Location selectMove() {
        Location nextMove = possibleMoves.get(0);
        possibleMoves.remove(0);
        return nextMove;
    }

    @Override
    public void takeTurn() {
        Location move = selectMove();
        opponent.applyMove(move);
    }
}
